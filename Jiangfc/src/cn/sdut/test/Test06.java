package cn.sdut.test;

import java.util.Scanner;

public class Test06 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		while (sc.hasNextLine()) {
			String s = sc.nextLine();
			String str = maxString(s);
			System.out.println("最长数字串长度为：" + str.length() + "  " + "最长数字串为：" + str);
		}

	}

	public static String maxString(String s) {
		char[] input = s.toCharArray();
		String str = "";// 定义一个新的字符串来存取数字
		for (int i = 0; i < input.length; i++) {
			if (input[i] <= '9' && input[i] >= '0') {
				str += input[i];
			} else {
				str += " ";
			}
		}
		String[] result = str.split(" ");// 分割为字符串数组
		int max = result[0].length();
		for (int i = 1; i < result.length; i++) {
			if (result[i].length() > max) {
				max = result[i].length();
				str = result[i];
			}
		}
		return str;
	}
}
