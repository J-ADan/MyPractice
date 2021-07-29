package cn.sdut.test;

import java.util.Scanner;

public class Test01 {
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		int n = sc.nextInt();
		
		// 一个正整数的连续系列的最大数应该为他的一半(不为整数的应向上取整)
		// 一个正整数n，最多有 (n + 1) / 2 个连续的序列，即最大数为( n + 1 ) / 2

		int max = (n + 1) / 2;

		int time = 0;// 记录第几种情况

		for (int i = 1; i <= max; i++) {
			int sum = 0;// 记录连续数的和
			int end = 0;// 记录结束位置
			for (int j = i; j <= max; j++) {
				sum += j;
				if (sum == n) {
					end = j;
					time++;
					break;
				}
			}

			for (int k = i; k <= end; k++) {
				if (k == i) {
					System.out.print("第" + time + "种：" + k);
				} else {
					System.out.print(" " + k);
				}
			}
			System.out.println();// 输出换行
		}
	}
}
