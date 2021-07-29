package cn.sdut.test;

import java.util.ArrayList;
import java.util.Scanner;

public class Test09 {
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("输入矩阵的行列数n：");
		int n = sc.nextInt();
		
		int [][] matrix = new int [n][n];
		
		//矩阵的生成
		for (int i = 0; i < n; i ++) {
			for (int j = 0; j < n; j ++) {
				matrix[i][j] = n * i + j + 1;
			}
		}
		
		ArrayList list = printMatrix(matrix);
		
		//遍历输出集合
		for (int i = 0; i < list.size(); i ++) {
			if (i == 0) {
				System.out.print(list.get(i));
			}else {
				System.out.print(" " + list.get(i));
			}
		}
	}
	
	//矩阵的顺时针遍历
	public static ArrayList<Integer> printMatrix(int[][] matrix) {
        ArrayList<Integer> list = new ArrayList<>();
        if (matrix == null || matrix.length == 0) {
        	return list;
        }
        //定义四个变量
        int top = 0;//顶部的矩阵第0行
        int dowm = matrix.length - 1;//最底部矩阵的第n - 1行
        int left = 0;//最左侧的第0列
        int right = matrix[0].length - 1;//最右侧第n - 1列
        while (true) {
            //顶部，遍历并且都存入list中
            for (int i = left; i <= right; i++) {
                list.add(matrix[top][i]);
            }
            top++;//顶部一定要下移
            if (top > dowm) {//判断是否到底，如果是就跳出
            	break;
            }
            //对最右面进行处理，一定要从顶部下移后的哪一行开始，即i=top，最后一定为上移后的
            for (int i = top; i <= dowm; i++) {
                list.add(matrix[i][right]);
            }
            right--;//右边处理完左移
            if (left > right) {//判断网左是否到底
            	break;
            }
            //最底层处理，一定要从右侧座椅后开始，即i=right，结束一定为左移后的
            for (int i = right; i >= left; i--) {
                list.add(matrix[dowm][i]);
            }
            dowm--;//底层上移
            if (top > dowm) {
            	break;
            }
            //从最左边向右遍历，一定是从上移后的开始，并且结尾要是右移后的
            for (int i = dowm; i >= top; i--) {
                list.add(matrix[i][left]);
            }
            left++;//向右移动
            if (left > right) {
            	break;
            }
        }
        return list;
    }

}
