package cn.sdut.test;

import java.util.Scanner;

public class Test05 {
	public static void main(String[] args) {
		// 没有想到一个完整的算法来解决此类型的问题，所以只能把自己的
		// 解题的思路，写成代码，但是仅限于像题目一模一样的条件的四组数据。

		Scanner sc = new Scanner(System.in);

		int end[] = new int[4];// 用来存储最终数据

		int arr[][] = new int[4][4];// 定义一个矩阵来存储数据
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				arr[i][j] = sc.nextInt();
			}
		}

		// 首先判断第一组，即第二行第三行，因为第二行两个正确，且有一个位置正确，第三行，有一个正确
		// 所以有相同的数即为正确的数，且位置在第二行所在的位置
		int index = 0;// 确定数字所在的列。
		int taget = 0;
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if (arr[1][i] == arr[2][j] && i != j) {
					end[i] = arr[1][i];
					index = i;
					taget = end[i];
				}
			}
		}
		int m = arr[0][index];
		int n = arr[3][index];
		// 第二步，即确定一个位置后，删除位置正确的两行的该列数据，并删除与之相等的数据
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if (arr[i][j] == m || arr[i][j] == n) {
					arr[i][j] = -1;
				}
			}
		}

		// 第三步，删除矩阵中确定行数正确数字之外的矩阵中与之相等的数字
		int b[] = new int[2];
		int in = 0;
		for (int i = 0; i < 4; i++) {
			if (arr[2][i] != taget && arr[2][i] != -1) {
				b[in++] = arr[2][i];
			}
		}
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if (arr[i][j] == b[0] || arr[i][j] == b[1]) {
					arr[i][j] = -1;
				}
			}
		}

		// 第四步，对比特殊的两行，即第一行与第四行，删除矛盾的数字
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if (arr[0][i] == arr[3][j]) {
					arr[0][i] = -1;
					arr[3][j] = -1;
				}
			}
		}
//		for (int i = 0; i < 4; i ++) {
//			for (int j = 0; j < 4; j ++) {
//				System.out.print(arr[i][j] + " ");
//			}
//			System.out.println();
//		}
		// 第五步，确定end最终的数字，即第一行或第四行只剩下一个数字的一行
		int count1 = 0;
		int count4 = 0;
		int index1 = 0;
		int index4 = 0;
		for (int i = 0; i < 4; i++) {
			if (arr[0][i] != -1) {
				count1++;
				index1 = i;
			}
		}
		for (int i = 0; i < 4; i++) {
			if (arr[3][i] != -1) {
				count4++;
				index4 = i;
			}
		}
		int h = 0;
		if (count1 == 1) {
			end[index1] = arr[0][index1];
		} else {
			h = 0;
		}
		if (count4 == 1) {
			end[index4] = arr[3][index4];
		} else {
			h = 3;
		}

		// 第六步，第一行或者第四行，剩下元素多的行，分情况讨论，第二行剩下的不确定位置的数，随之改变位置
		int key = 0;
		for (int i = 0; i < 4; i++) {
			if (arr[1][i] != -1 && i != index) {
				key = arr[1][i];
			}
		}
		for (int i = 0; i < 4; i++) {
			if (arr[h][i] != -1) {
				end[i] = arr[h][i];
				for (int j = 0; j < 4; j++) {
					if (end[j] == 0) {
						end[j] = key;
					}
				}
				for (int k : end) {
					System.out.print(k + " ");
				}
				System.out.println();
				end[i] = 0;
			} else {
				continue;
			}
		}

	}
}
