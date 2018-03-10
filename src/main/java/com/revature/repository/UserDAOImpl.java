package com.revature.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.revature.model.User;
import com.revature.util.ConnectionUtil;

public  class UserDAOImpl implements UserDAO {
	private static Logger logger = Logger.getLogger(UserDAOImpl.class);
	Connection connection = null;	
	PreparedStatement stmt = null;	
	
	@Override
	public User LogIn(String uid) {
		User user1 = null;
		try {Connection connection =null;
			connection = ConnectionUtil.getConnection();
			String sql = " SELECT * FROM BANKUSERS WHERE B_UserName like ?";			
			stmt = connection.prepareStatement(sql);
			stmt.setString(1,uid);
			System.out.println(stmt);
			logger.info("User LogIn is being executed");
			
			ResultSet rs = stmt.executeQuery();
			
			if (rs.next()) {
				user1 = new User();
				user1.setPassword(rs.getString("B_PASSWORD"));
				user1.setUsername(rs.getString("B_UserName"));
				user1.setBalance(rs.getLong("B_BALANCE"));
				
}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources();
		}
		
		return user1;
		
	}
		@Override
		public boolean UpdateBalance(long balance,String pass) {
		try {Connection connection =null;
		connection = ConnectionUtil.getConnection();
		String sql = "UPDATE BankUsers" + 
				"SET  B_BALANCE = ?" + 
				"WHERE B_PASSWORD=? ;";			
		stmt = connection.prepareStatement(sql);
		stmt.setLong(1,balance);
		stmt.setString(2,pass);
		System.out.println(stmt);
		logger.info("User Balance is being updated");
		if (stmt.executeUpdate() != 0)
			return true;
		else
			return false;		
	} catch (SQLException e) {
		e.printStackTrace();
		return false;
	} finally {
		closeResources();
	}
			
		}
		
		private void closeResources() {
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException e) {
				System.out.println("Could not close statement!");
				e.printStackTrace();
			}
			
			try {
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				System.out.println("Could not close connection!");
				e.printStackTrace();
			}


}
}