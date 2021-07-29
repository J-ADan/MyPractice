package cn.sdut.test.test08.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import cn.sdut.test.test08.model.BookModel;

public class BookDao {
	public ResultSet list(Connection con, BookModel bookModel) throws Exception{
		String sql = "select * from book order by count DESC";
		PreparedStatement past = con.prepareStatement(sql);
		
		return past.executeQuery();
	}
}
