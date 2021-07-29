package cn.sdut.test;

import java.util.Scanner;

public class Test02 {
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		System.out.println("输入为0结束");
		// 是否为回文数可以用前后数字对照判断，也可以将正整数倒置挨个判断。
		// 倒置分为字符串倒置与整数倒置
		// 一共想起来三种方法，写三个方法来分别实现
		while (true) {
			int n = sc.nextInt();

			if (n == 0) {
				break;
			}

			palindrome01(n);// 字符串倒置方法
			palindrome02(n);// 正整数倒置方法
			palindrome03(n);// 前后判断方法
		}

	}

	public static void palindrome01(int n) {// 字符串倒置方法

		String str1 = Integer.toString(n);
		// 倒置要用的StringBuilder
		StringBuilder str2 = new StringBuilder(str1);
		str2.reverse();
		int count = 0;
		for (int i = 0; i < str1.length(); i++) {
			if (str1.charAt(i) != str2.charAt(i)) {
				System.out.println("不是回文数");
				break;
			} else {
				count++;
			}
		}
		if (count == str1.length()) {
			System.out.println("是回文数");
		}
	}

	public static void palindrome02(int n) {// 正整数倒置方法
		int sum = 0;
		int i = 0;
		int m = n;

		while (true) {
			i = n % 10;// 取余，即把最后一位取下。
			sum = sum * 10 + i;
			n = n / 10;// 把最后一位舍去。

			if (n == 0) {
				break;
			}
		}

		if (m == sum) {
			System.out.println("是回文数");
		} else {
			System.out.println("不是回文数");
		}
	}

	public static void palindrome03(int n) {// 前后判断方法
		String str1 = Integer.toString(n);

		int count = 0;// 默认为回文数

		for (int i = 0; i < str1.length() / 2; i++) {
			if (str1.charAt(i) != str1.charAt(str1.length() - 1 - i)) {
				count = 1;
			}
		}

		if (count == 0) {
			System.out.println("是回文数");
		} else {
			System.out.println("不是回文数");
		}
	}
}
