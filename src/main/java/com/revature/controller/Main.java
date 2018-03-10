package com.revature.controller;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;
import org.apache.log4j.Logger;

import com.revature.exception.CheckException;
import com.revature.model.User;
import com.revature.service.UserDAO;
import com.revature.service.UserDAOImpl;
import com.revature.util.ConnectionUtil;

/**
 * Create an instance of your controller and launch your application.
 * 
 * Try not to have any logic at all on this class.
 */
public class Main {
	//static String pass;
	private static Logger logger = Logger.getLogger(Main.class);

	public static UserDAO getUserDAO() {
		return new UserDAOImpl();
	}

	public static void main(String[] args) throws SQLException,InputMismatchException {

	    try (Connection connection = ConnectionUtil.getConnection()) {
	    	User user = new User();
	    	System.out.println("Do you have an account with our bank?"
	    			+ "\nPress y for yes and n for no");
	    	Scanner sc = new Scanner(System.in);
	    	
            while (sc.hasNext()) {
	    	
	    	/*User Registration*/
	    	String answer= sc.next().toLowerCase();
	    	if(answer.equals("n")) {
	    	System.out.println("Enter your user name \n");
	    	String uname= sc.next().toLowerCase();
	    	if(new CheckException().checkAvailabilty(uname)) {
	    		System.out.println("\nUSERNAME ALREADY EXISTS.Try Again!");
	    		break;
	    	}
	    	user.setUsername(uname);
	    	System.out.println("Enter your pass word\n");
	    	String pw= sc.next().toLowerCase();
	    	user.setPassword(pw);
	    	System.out.println("Enter your Deposit amount");
	    	Long am= sc.nextLong();
	    	user.setBalance(am);
	    	getUserDAO().addUser(user);
	    	System.out.println("Congratulation!!Your present balance is :"+user.getBalance());
	    	}
	    	
	    	/*Logging into an old Account*/
	    	
	    	if(answer.equals("y")) {
		/* Asking for User ID */
	        System.out.println("Enter Your UserName \n");
			String ID = sc.next().toLowerCase();
			if(!new CheckException().checkAvailabilty(ID)) {
	    		System.out.println("USERNAME DOESN'T MATCH.\nYOu are logged Out");
	    		break;
	    	}
			
			
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
			 user.setBalance(balance - m);//fix update in setBalance
			 
			 System.out.println("your new balance is after withdrawal!" + user.getBalance());
			 getUserDAO().UpdateBalance(user.getBalance(), pass);
			 break;
				    }                 }
			 
			 /*Checking Balance*/
			 if(input.equals("b")) {
			System.out.println("your balance is :" + user.getBalance());
				 }
			 
			 /*Deposit Money  fix in USERDAOIMPL*/ 
			 if(input.equals("d")) {
				 logger.info("Enter deposit amount");
				 Long d = sc.nextLong(); 
				 user.setBalance(balance+d);
				 System.out.println("your new balance is " + user.getBalance());
				 getUserDAO().UpdateBalance(user.getBalance(), user.getUsername());
				 }
			 else {
				 System.out.println("You are logged out!");
				 break;
				               }
			 }
	    	}
	      
	        sc.close();

		} catch (SQLException e) {
			logger.error("Couldn't connect to the database", e);
		}

	}
}
