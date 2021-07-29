package cn.sdut.test.test08.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import cn.sdut.test.test08.model.BookModel;
import cn.sdut.test.test08.util.StringUtil;

public class AllBooksDao {
	public ResultSet list(Connection con, BookModel bookModel) throws Exception{
		StringBuffer sb = new StringBuffer("select * from book");
		if (!StringUtil.isEmpty(bookModel.getBookname())) {
			sb.append(" and bookname like '%" + bookModel.getBookname() +"%'");
		}
		
		PreparedStatement past = con.prepareStatement(sb.toString().replaceFirst("and", "where"));
		
		return past.executeQuery();
	}
	
	public int update(Connection con, BookModel bookModel) throws Exception{
		String sql = "update book set yorn=1 where bookname=?";
		PreparedStatement past = con.prepareStatement(sql);
		past.setString(1, bookModel.getBookname());
		return past.executeUpdate();
	}
	
	public int plus(Connection con, BookModel bookModel) throws Exception{
		String sql = "update book set count=count+1 where bookname=?";
		PreparedStatement past = con.prepareStatement(sql);
		past.setString(1, bookModel.getBookname());
		return past.executeUpdate();
	}
	public int update1(Connection con, BookModel bookModel) throws Exception{
		String sql = "update book set yorn=0 where bookname=?";
		PreparedStatement past = con.prepareStatement(sql);
		past.setString(1, bookModel.getBookname());
		return past.executeUpdate();
	}
}