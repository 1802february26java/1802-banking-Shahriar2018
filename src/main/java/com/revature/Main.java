package com.revature;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.revature.model.User;
import com.revature.repository.UserDAO;
import com.revature.repository.UserDAOImpl;
import com.revature.util.ConnectionUtil;

/**
 * Create an instance of your controller and launch your application.
 * 
 * Try not to have any logic at all on this class.
 */
public class Main {
	private static Logger logger = Logger.getLogger(Main.class);

	public static UserDAO getUserDAO() {
		return new UserDAOImpl();
	}

	public static void main(String[] args) {

	    try (Connection connection = ConnectionUtil.getConnection()) {
	    	logger.info("Enter Your UserName\n");
			Scanner sc = new Scanner(System.in);
	        while (sc.hasNext()) {
		/* Asking for User ID */
			String ID = sc.next().toLowerCase();
			User user = new User();
			
		/* Pulling UP password and balance based on userID */
			user = getUserDAO().LogIn(ID);
			Long balance = user.getBalance();
			String pass = user.getPassword();
		
			
		/* Matching Entered password with saved password */
			logger.info("Enter Your Password \n");
			String newpass = sc.next().toLowerCase();
             if(pass.equals(newpass)) {
     	    	logger.info("Your Are Logged In");

			}else {
			System.out.println("PassWord Doesn't Match!Try Again");
		    break;
			}
             
             /*Starting Transaction */
			logger.info("press w for withdrawal,"
					+ "d for deposit "
					+ "b for balance"
					+ "and any other key to exit");
			String input = sc.next().toLowerCase();
			  
			 /*Money Withdrawal*/
			 if (input.equals("w"))   {
			 logger.info("Enter withdrawal amount");
			 Long m = sc.nextLong();
			  if (m > balance) {
			  System.out.println("You don't have that much money in your account");
				                  } 
			  else {
			 user.setBalance(balance - m);
			 System.out.println("your new balance is after withdrawal!" + user.getBalance());
			 break;
				    }                 }
			 
			 /*Checking Balance*/
			 if(input.equals("b")) {
			System.out.println("your balance is :" + user.getBalance());
				 }
			 
			 /*Deposit Money*/
			 if(input.equals("d")) {
				 logger.info("Enter deposit amount");
				 Long d = sc.nextLong(); 
				 user.setBalance(balance+d);
				 System.out.println("your new balance is " + user.getBalance());
				 }
			 else {
				 System.out.println("You are logged out!");
				 break;
				               }
			 }
	        
	      
	        sc.close();

		} catch (SQLException e) {
			logger.error("Couldn't connect to the database", e);
		}

	}
}
