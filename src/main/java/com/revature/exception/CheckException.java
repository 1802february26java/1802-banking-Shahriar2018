package com.revature.exception;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.revature.model.User;
import com.revature.util.ConnectionUtil;

public class CheckException {
	public boolean checkAvailabilty(String uname) {	
		PreparedStatement stmt = null;
		User user1 = null;
		boolean t=true;;
		try {Connection connection =null;
			connection = ConnectionUtil.getConnection();
			String sql = " SELECT * FROM BANKUSERS ";			
			stmt = connection.prepareStatement(sql);
			System.out.println("Select statement is being executed");
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				user1 = new User();
				user1.setPassword(rs.getString("B_PASSWORD"));
				user1.setUsername(rs.getString("B_UserName"));
				String same=rs.getString("B_UserName");
				//System.out.println("user name here in check is "+same);
				if(uname.equals(rs.getString(same))) {
					t=true;
					}
				else {
					t=false;
				}
				user1.setBalance(rs.getLong("B_BALANCE"));
				}
			
			
			
		} catch (SQLException e) {
			
			//e.printStackTrace();
		} finally {
			
		}
		
		return t;
		
		}


		
	}
	


