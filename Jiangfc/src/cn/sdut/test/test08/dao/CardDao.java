package cn.sdut.test.test08.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import cn.sdut.test.test08.model.CardModel;

public class CardDao {
	
	public int add(Connection con, CardModel card) throws Exception{
		String sql = "insert into card values(null,?,?)";
		
		PreparedStatement past = con.prepareStatement(sql);
		
		past.setString(1, card.getCardId());
		past.setString(2, card.getCardname());
		
		return past.executeUpdate();
	}
	
	public ResultSet list (Connection con, CardModel cardModel) throws Exception{
		String sql = "select * from card where cardId=?";
		PreparedStatement past = con.prepareStatement(sql);
		past.setString(1, cardModel.getCardId());
		
		return past.executeQuery();
	}
	
}
