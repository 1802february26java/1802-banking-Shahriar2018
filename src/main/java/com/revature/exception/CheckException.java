package com.revature.exception;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.revature.model.User;
import com.revature.util.ConnectionUtil;

public class CheckException {
	PreparedStatement stmt = null;
	User user1 = null;
	Connection connection =null;
	public boolean checkAvailabilty(String uname) {	
		boolean t=false;;
		try {
			connection = ConnectionUtil.getConnection();
			String sql = " SELECT * FROM BANKUSERS WHERE B_UserName=? ";			
			stmt = connection.prepareStatement(sql);
			//System.out.println("Select statement is being executed");
			stmt.setString(1, uname);
			ResultSet rs = stmt.executeQuery();
			 while(rs.next()) {
				//user1 = new User();
				//user1.setPassword(rs.getString("B_PASSWORD"));
				//user1.setUsername(rs.getString("B_UserName"));
				String same=rs.getString("B_UserName");
				//System.out.println("user name here in check is "+same);
				if(uname.equals(same)) {
					
					t=true;
					break;
					}
				else {
					t=false;
				}
				
				}
			
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			/**
		     * Prints this throwable and its backtrace to the
		     * standard error stream. This method prints a stack trace for this*/
		} finally {
			closeResources();
		}
		
		return t;
		
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
	


