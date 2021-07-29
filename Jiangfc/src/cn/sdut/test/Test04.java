package cn.sdut.test;

import java.util.Scanner;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

public class Test04 {
	public static void main(String[] args) {
		//写一个最简单的解决方式，利用脚本来解决
		ScriptEngineManager sem = new ScriptEngineManager();
		ScriptEngine eng = sem.getEngineByName("JavaScript");
		
		Scanner sc = new Scanner(System.in);
		
		try {
			String str = sc.next();
			System.out.println(eng.eval(str));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
