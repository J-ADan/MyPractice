package cn.sdut.test.test08.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class MysqlUtil {
	//链接地址
		private static String URL = "jdbc:mysql://localhost:3306/db_book?useSSL=true&useUnicode=true&characterEncoding=UTF-8";
		//驱动名称
		private static String DRIVER = "com.mysql.jdbc.Driver";
		//用户名
		private static String USER = "root";
		//密码
		private static String PASSWORD = "8023";
		
		//获取数据库链接
		public Connection getCon() throws Exception{
			Class.forName(DRIVER);
			Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
			return con;
		}
		
		//关闭数据库连接
		public void closeCon(Connection con) throws Exception {
			if (con != null) {
				con.close();
			}
		}
		
		//测试
		public static void main(String[] args) {
			MysqlUtil mysql = new MysqlUtil();
			
			try {
				mysql.getCon();
				System.out.println("连接成功");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("数据库链接失败");
			}
		}
}
