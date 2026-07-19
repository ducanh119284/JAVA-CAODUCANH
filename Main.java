import java.util.ArrayList;
import java.util.Date;
import java.util.List;

class BanNop {
    private String maBanNop;
    private String loaiTaiLieu;
    private String duongDanFile;
    private Date thoiGianNop;
    private String nhanXetCuaGV;

    // Constructor
    public BanNop(String maBanNop, String loaiTaiLieu, String duongDanFile) {
        this.maBanNop = maBanNop;
        this.loaiTaiLieu = loaiTaiLieu;
        this.duongDanFile = duongDanFile;
        this.thoiGianNop = new Date(); // Tu dong lay thoi gian hien tai khi tao ban nop
    }

    // Getters and Setters
    public String getMaBanNop() { return maBanNop; }
    public String getLoaiTaiLieu() { return loaiTaiLieu; }
    public String getDuongDanFile() { return duongDanFile; }
    public Date getThoiGianNop() { return thoiGianNop; }
    
    public String getNhanXet() { return nhanXetCuaGV; }
    public void setNhanXet(String nhanXet) { this.nhanXetCuaGV = nhanXet; }

    // Hien thi thong tin ban nop
    public void inThongTin() {
        System.out.println("  - Loai: " + loaiTaiLieu + " | Link: " + duongDanFile + " | Ngay: " + thoiGianNop);
        if (nhanXetCuaGV != null) {
            System.out.println("    -> Nhan xet cua GV: " + nhanXetCuaGV);
        }
    }
}

class DoAn {
    private String maDoAn;
    private String tenDeTai;
    private String trangThai;
    private SinhVien sinhVienThucHien;
    private GiangVien giangVienHuongDan;
    private List<BanNop> danhSachBanNop;
    private Date hanChot;
    private float diemTongKet;

    // Constructor
    public DoAn(String maDoAn, String tenDeTai, Date hanChot) {
        this.maDoAn = maDoAn;
        this.tenDeTai = tenDeTai;
        this.hanChot = hanChot;
        this.trangThai = "Cho duyet";
        this.danhSachBanNop = new ArrayList<>(); // Khoi tao danh sach trong
    }

    // Getters and Setters
    public String getMaDoAn() { return maDoAn; }
    public String getTenDeTai() { return tenDeTai; }
    public String getTrangThai() { return trangThai; }
    public float getDiemTongKet() { return diemTongKet; }
    public List<BanNop> getDanhSachBanNop() { return danhSachBanNop; }

    public void capNhatTrangThai(String trangThaiMoi) { 
        this.trangThai = trangThaiMoi; 
    }
    
    public void setDiemTongKet(float diem) { 
        this.diemTongKet = diem; 
    }

    public void phanCongSinhVien(SinhVien sv) {
        this.sinhVienThucHien = sv;
    }

    public void phanCongGiangVien(GiangVien gv) {
        this.giangVienHuongDan = gv;
    }

    // Hanh vi them ban nop
    public void themBanNop(BanNop banNop) {
        this.danhSachBanNop.add(banNop);
    }

    // Kiem tra deadline
    public boolean kiemTraHanNop() {
        Date ngayHienTai = new Date();
        return ngayHienTai.before(hanChot); // Tra ve true neu nop dung han
    }
}

class SinhVien {
    private String maSV;
    private String hoTen;
    private String lop;

    public SinhVien(String maSV, String hoTen, String lop) {
        this.maSV = maSV;
        this.hoTen = hoTen;
        this.lop = lop;
    }

    public String getHoTen() { return hoTen; }

    // Sinh vien nop tai lieu len do an
    public void nopTaiLieu(DoAn doAn, BanNop banNop) {
        if (doAn.kiemTraHanNop()) {
            doAn.themBanNop(banNop);
            System.out.println("[THONG BAO] Sinh vien " + this.hoTen + " da nop: " + banNop.getLoaiTaiLieu());
        } else {
            System.out.println("[CANH BAO] Da qua han nop bai cho do an " + doAn.getTenDeTai());
        }
    }

    // Xem diem tong ket
    public void xemDiem(DoAn doAn) {
        System.out.println("Diem do an cua " + this.hoTen + " la: " + doAn.getDiemTongKet());
    }
}

class GiangVien {
    private String maGV;
    private String hoTen;

    public GiangVien(String maGV, String hoTen) {
        this.maGV = maGV;
        this.hoTen = hoTen;
    }

    public String getHoTen() { return hoTen; }

    // Tai tai lieu ve
    public void taiTaiLieu(BanNop banNop) {
        System.out.println("[TAI XUONG] Giang vien " + this.hoTen + " dang tai file tu: " + banNop.getDuongDanFile());
    }

    // Viet nhan xet cho 1 ban nop cu the
    public void nhanXetBanNop(BanNop banNop, String nhanXet) {
        banNop.setNhanXet(nhanXet);
    }

    // Cham diem ket thuc do an
    public void chamDiem(DoAn doAn, float diem) {
        doAn.setDiemTongKet(diem);
        doAn.capNhatTrangThai("Da hoan thanh");
        System.out.println("[CHAM DIEM] Giang vien " + this.hoTen + " da cham " + diem + " diem cho do an: " + doAn.getTenDeTai());
    }
}

// CLASS PUBLIC DUY NHẤT (Tên phải trùng với tên file Main.java)
public class Main {
    public static void main(String[] args) {
        // 1. Tao du lieu gia lap (Sinh vien, Giang vien)
        SinhVien sv = new SinhVien("SV01", "Nguyen Van A", "CNTT01");
        GiangVien gv = new GiangVien("GV01", "Tien si Tran B");

        // 2. Tao mot do an (Set deadline la 30 ngay sau)
        Date deadline = new Date(System.currentTimeMillis() + (30L * 24 * 60 * 60 * 1000));
        DoAn doAnTotNghiep = new DoAn("DA001", "Xay dung website ban hang", deadline);
        doAnTotNghiep.phanCongSinhVien(sv);
        doAnTotNghiep.phanCongGiangVien(gv);

        System.out.println("--- QUA TRINH SINH VIEN NOP BAI ---");
        // 3. Sinh vien nop De cuong
        BanNop deCuong = new BanNop("BN01", "De cuong chi tiet", "/uploads/decuong.pdf");
        sv.nopTaiLieu(doAnTotNghiep, deCuong);

        // 4. Sinh vien nop Bao cao cuoi ky (Source code)
        BanNop sourceCode = new BanNop("BN02", "Source Code Final", "github.com/nguyenvana/doan");
        sv.nopTaiLieu(doAnTotNghiep, sourceCode);

        System.out.println("\n--- QUA TRINH GIANG VIEN XU LY ---");
        // 5. Giang vien vao he thong, xem cac ban nop va cham diem
        for (BanNop bn : doAnTotNghiep.getDanhSachBanNop()) {
            gv.taiTaiLieu(bn);
            if (bn.getLoaiTaiLieu().equals("De cuong chi tiet")) {
                gv.nhanXetBanNop(bn, "De cuong lam tot, can lam ro phan API.");
            }
        }
        
        gv.chamDiem(doAnTotNghiep, 8.5f);

        System.out.println("\n--- TONG KET ---");
        // 6. Sinh vien vao xem diem va lich su nop bai
        sv.xemDiem(doAnTotNghiep);
        System.out.println("Trang thai do an: " + doAnTotNghiep.getTrangThai());
        System.out.println("Lich su nop bai:");
        for (BanNop bn : doAnTotNghiep.getDanhSachBanNop()) {
            bn.inThongTin();
        }
    }
}