package com.addressbook.addressbookapp.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SingletonConnection {
	private static Connection connection;
	private static SingletonConnection instance;
	
	private static final String URL="jdbc:mysql://localhost:3306/addressbookdb";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "Suraj@123";
	
	private SingletonConnection() {
		try {
			this.connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public static SingletonConnection getInstance() {
		if(instance==null) {
			instance=new SingletonConnection();
		}
		return instance;
	}
	
	public Connection getConnection() {
		return connection;
	}
}
