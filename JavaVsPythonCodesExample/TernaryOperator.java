package thesandbox2;

import java.util.Scanner;

public class test {

	public static void main(String[] args) {
		try (Scanner sc = new Scanner(System.in)) {
			int a = sc.nextInt();
			int b = sc.nextInt();
			int c = sc.nextInt();
			int d = (b < a) ? a - b : (b < c ? b - c : (((a < b) && (c != b)) || ((b + c) != a && !true)) ? a + b + c : 0);
			System.out.print(d);
		}
	}

}
