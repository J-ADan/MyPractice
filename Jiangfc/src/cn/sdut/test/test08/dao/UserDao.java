package cn.sdut.test.test08.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import cn.sdut.test.test08.model.UserModel;

/**
 * 用户dao类
 * 用来实现登录功能
 * @author J-ADan
 *
 */
public class UserDao {
	public UserModel login(Connection con, UserModel user) throws Exception {
		UserModel resultUser = null;//返回信息（包含用户的所有信息）
		String sql = "select * from user where username=? and userpassword=?";
		//接收返回的所有信息
		PreparedStatement pstm = con.prepareStatement(sql);
		pstm.setString(1, user.getUsername());
		pstm.setString(2, user.getPassword());
		ResultSet rs = pstm.executeQuery();
		
		//获取返回的数据
		if (rs.next()) {
			//实例化对象
			resultUser = new UserModel();
			resultUser.setId(rs.getInt("id"));
			resultUser.setUsername("username");
			resultUser.setPassword("userpassword");
		}
		return resultUser;
	}
}
