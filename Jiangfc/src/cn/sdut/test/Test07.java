package cn.sdut.test;

import java.util.Random;
import java.util.Scanner;

public class Test07 {
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		Random ra = new Random();

		System.out.println("请输入生成随机数的个数：");
		int n = sc.nextInt();
		System.out.println("请输入随机数范围：x，y");
		int x = sc.nextInt();
		int y = sc.nextInt();

		int[] a = new int[y - x + 1];

		if (x == 0) {
			for (int i = 0; i < n; i++) {
				int i0 = ra.nextInt(y);
				a[i0]++;
			}
		} else {
			for (int i = 0; i < n; i++) {
				int i0 = ra.nextInt(y - x + 1) + (x);// 因为Random的取值是前闭后开的，所以这里要+1
				a[i0 - x]++;
			}
		}
		
		printArr(a, x);
		
		
	}
	
	public static void printArr(int a[], int x) {
		int index[] = new int[a.length];
		int count[] = new int[a.length];
		
		for (int i = 0; i < a.length; i ++) {
			index[i] = i;
			count[i] = a[i];
		}
		
		int taget = 0;
		for (int i = 0; i < a.length - 1; i++) {
			for (int j = 0; j < a.length - 1 - i; j++) {
				if (count[j] > count[j + 1]) {
					taget = index[j];
					index[j] = index[j + 1];
					index[j + 1] = taget;
					taget = count[j];
					count[j] = count[j + 1];
					count[j + 1] = taget;
				}else {
					continue;
				}
			}
		}
		
		//循环输出最多的情况，因为会出现并列次数最多的情况
		for (int i = a.length - 1; i >= 0 ; i --) {
			if (count[i] == count[a.length-1]) {
				System.out.println("最大整数为：" + (index[i] + x) + " 次数为：" + count[i]);
			}else if (count[i] < count[a.length-1]) {
				break;
			}
		}
		
		System.out.println("排序为：");
		for (int i = 0; i < a.length; i ++) {
			System.out.print(index[i] + x);
			System.out.println(" " + count[i]);
		}
	}
}
