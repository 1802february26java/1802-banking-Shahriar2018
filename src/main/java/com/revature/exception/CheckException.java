package com.revature.exception;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.NameAlreadyBoundException;
import javax.naming.NameNotFoundException;
import javax.security.auth.login.AccountException;

import com.revature.util.ConnectionUtil;

@SuppressWarnings("serial")
public class CheckException extends Exception{
	Connection connection = null;	
	PreparedStatement stmt = null;	
	public boolean checkAvailabilty(String uname) throws NameAlreadyBoundException, AccountException,SQLException{
		
		boolean t=false;;
	
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
				 throw new NameAlreadyBoundException("UserName Already exists ! Please try again.");
				
				}
			else 
				t=false;
		 }
		return t;
		
	}
	         public boolean  minDeposit100(long m) throws AccountException {
	        	 boolean t=true;
	        	  if (m<100) {
	        		  t=true;
	        		  throw new AccountException("\nMinimum deposit has to be atleast 100 for new clients\n");
	              }
	        	  else
	        		  t=false;
				return t;
	        	  
	        	
	        	 
	         }
	}
	

