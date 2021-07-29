package cn.sdut.test.test08.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import cn.sdut.test.test08.model.CardModel;
import cn.sdut.test.test08.model.Card_Book;

public class Card_BookDao {
	public int add(Connection con, Card_Book card_Book) throws Exception{
		String sql = "insert into card_book values(null,?,?,?,?)";
		
		PreparedStatement past = con.prepareStatement(sql);
		
		past.setString(1, card_Book.getCardid());
		past.setString(2, card_Book.getCardname());
		past.setString(3, card_Book.getBookname());
		past.setString(4, card_Book.getBookwriter());
		
		return past.executeUpdate();
	}
	
	
	public ResultSet cardlist (Connection con, Card_Book card_Book) throws Exception{
		String sql = "select * from card_book where cardid=?";
		PreparedStatement past = con.prepareStatement(sql);
		past.setString(1, card_Book.getCardid());
		return past.executeQuery();
	}
	
	public ResultSet booklist (Connection con, Card_Book card_Book) throws Exception{
		String sql = "select * from card_book where bookname=?";
		PreparedStatement past = con.prepareStatement(sql);
		past.setString(1, card_Book.getBookname());
		return past.executeQuery();
	}
}
