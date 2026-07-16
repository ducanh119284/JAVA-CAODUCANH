public class BTBUOI2 {
	public static void main(String[] args) {
		System.out.println("n\tbinh phuong\tlap phuong");
		for (int n = 1; n <= 10; n++) {
			int binhPhuong = n * n;
			int lapPhuong = n * n * n;
			System.out.println(n + "\t" + binhPhuong + "\t\t" + lapPhuong);
		}
	}
}