package cn.sdut.test.test08.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import cn.sdut.test.test08.model.BookModel;

public class IntoBookDao {
	public int add(Connection con, BookModel bookModel) throws Exception {

		String sql = "insert into book values(null,?,?,0,0)";

		PreparedStatement past = con.prepareStatement(sql);

		past.setString(1, bookModel.getBookname());
		past.setString(2, bookModel.getBookwriter());

		return past.executeUpdate();
	}
}
