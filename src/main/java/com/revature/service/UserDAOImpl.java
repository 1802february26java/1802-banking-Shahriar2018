package com.revature.service;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.log4j.Logger;
import com.revature.model.User;
import com.revature.util.ConnectionUtil;

public  class UserDAOImpl implements UserDAO {
	private static Logger logger = Logger.getLogger(UserDAOImpl.class);
	Connection connection = null;	
	PreparedStatement stmt = null;	
	PreparedStatement stmt1 = null;	
	
	@Override
	public User LogIn(String uid) {
		User user1 = null;
		try {Connection connection =null;
			connection = ConnectionUtil.getConnection();
			String sql = " SELECT * FROM BANKUSERS WHERE B_UserName like ?";			
			stmt = connection.prepareStatement(sql);
			stmt.setString(1,uid);
			logger.info("User LogIn is being executed........");
			
			ResultSet rs = stmt.executeQuery();
			
			if ( rs.next()) {
				user1 = new User();
				user1.setPassword(rs.getString("B_PASSWORD"));
				user1.setUsername(rs.getString("B_UserName"));
				user1.setBalance(rs.getLong("B_BALANCE"));
				}
			
		} catch (SQLException e) {
			System.out.println("User doesn't exist");
			//e.printStackTrace();
		} finally {
			closeResources();
		}
		
		return user1;
		
	}
		@Override
		public boolean UpdateBalance(long balance,String uname) {
			User user=new User();
		try {Connection connection =null;
		connection = ConnectionUtil.getConnection();
		String sql = "UPDATE BankUsers SET  B_BALANCE = ? WHERE   B_UserName=?" ;
		stmt = connection.prepareStatement(sql);
        stmt.setLong(1,balance);
		stmt.setString(2,uname);
        logger.info("User Balance is being updated");
		if (stmt.executeUpdate()!=0)
			return true;
		else
			return false;		
	} catch (SQLException e) {
		logger.info("Cann't be updated.Mistake in programming.");
		e.printStackTrace();
		return false;
	} finally {
		closeResources();
	}
			
		}
		@Override
		public boolean addUser(User user) {
			
			try {
				Connection connection =null;
				connection = ConnectionUtil.getConnection();
				String sql = "INSERT INTO BankUsers VALUES (?, ?, ?)";
				stmt = connection.prepareStatement(sql);	
				stmt.setString(1, user.getUsername());
				stmt.setString(2, user.getPassword());
				stmt.setLong(3, user.getBalance());
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
		@Override
		public boolean checkAvailabilty(String uname) {	
			PreparedStatement stmt = null;
			User user1 = null;
			boolean t=true;;
			try {Connection connection =null;
				connection = ConnectionUtil.getConnection();
				String sql = " SELECT * FROM BANKUSERS  ";			
				stmt = connection.prepareStatement(sql);
				//System.out.println("Select statement is being executed");
				stmt.setString(1, uname);
				ResultSet rs = stmt.executeQuery();
				 while(rs.next()) {
					user1 = new User();
					//user1.setPassword(rs.getString("B_PASSWORD"));
					user1.setUsername(rs.getString("B_UserName"));
					String same=rs.getString("B_UserName");
					//System.out.println("user name here in check is "+same);
					if(uname.equals(same)) {
						
						t=true;
						break;
						}
					else {
						t=false;
					}
					//user1.setBalance(rs.getLong("B_BALANCE"));
					}
				
				
				
			} catch (SQLException e) {
				
				e.printStackTrace();
			} finally {
				
			}
			
			return t;
			
			}
		
}