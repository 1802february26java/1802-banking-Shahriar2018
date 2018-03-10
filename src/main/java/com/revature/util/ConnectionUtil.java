package com.revature.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.apache.log4j.Logger;
public class ConnectionUtil  {
	private static Logger logger = Logger.getLogger(ConnectionUtil.class);
	private static final String URL= "jdbc:oracle:thin:@banking2018.ctqxu0ey9hj6.us-east-1.rds.amazonaws.com:1521:ORCL";
	private static final String CONNECTION_PASSWORD = "mohammed2018";
	private static final String CONNECTION_USERNAME= "mohammed2018";
	private static Connection connection=null;
	public static synchronized Connection getConnection() throws SQLException {
		if (connection == null) {
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				logger.info("Connection successful");
			} catch (ClassNotFoundException e) {
				logger.error("Couldn't connect to the database", e);
				e.printStackTrace();
			}
			connection = DriverManager.getConnection(URL, CONNECTION_USERNAME, CONNECTION_PASSWORD);
		}
		
		//If connection was closed then retrieve a new connection
		if (connection.isClosed()){
			System.out.println("Opening new connection...");
			connection = DriverManager.getConnection(URL, CONNECTION_USERNAME, CONNECTION_PASSWORD);
		}
		return connection;
	}

	
	}
