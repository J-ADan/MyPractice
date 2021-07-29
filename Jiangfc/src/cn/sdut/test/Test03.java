package cn.sdut.test;

public class Test03 {
	public static void main(String[] args) {
		//两种方法，一种相比较而言简单，但是耗时
		//第二种相比较而言难，但是快
		
		number01();
		number02();
		
	}
	public static void number01() {
		for (int i = 1; i < 10; i ++) {
			for (int j = 1; j < 10; j ++) {
				if (i != j) {
					int m = j * 10 + j;
					int n = i * 10 + i;
					int sum = n * 100 + m;
					
					double x = Math.sqrt(sum);
					int y = (int) Math.sqrt(sum);
					
					if (x == y) {
						System.out.println(sum);
					}else {
						continue;
					}
					
				}else {
					continue;
				}
			}
		}
	}
	
	public static void number02() {
		for (int i = 1000; i < 10000; i ++) {
			double x = Math.sqrt(i);
			int y = (int) Math.sqrt(i);
			
			if (x == y) {
				int m = i % 10;
				int k = i /10 % 10;
				int n = (i /100) %10;
				int j = i / 1000;
				
				if ((m == k)&&(n == j)&&(m != n)) {
					System.out.println(i);
				}else {
					continue;
				}
			}else {
				continue;
			}
		}
	}
}
