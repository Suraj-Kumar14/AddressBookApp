package com.addressbook.addressbookapp.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.addressbook.addressbookapp.model.Contact;

public class SQLQuery {
	SingletonConnection singleton = SingletonConnection.getInstance();
	Connection connection = singleton.getConnection();
	
	//add new contact to database
	public void addContact(List<Contact>contactList) {
		ExecutorService executor = Executors.newFixedThreadPool(5);
		
		for(Contact contact : contactList) {
			executor.execute(()->{
				Connection conn=null;
				
				try {
					conn = SingletonConnection.getInstance().getConnection();
					conn.setAutoCommit(false); //start transaction
					String sql = "INSERT INTO contact (first-name, last_name, address, city, state, zip, phone_number, email) VALUES (?,?,?,?,?,?,?,?)" ;
					PreparedStatement ps=connection.prepareStatement(sql);
					
					ps.setString(1,contact.getFirstName());
					ps.setString(2,contact.getLastName());
					ps.setString(3,contact.getAddress());
					ps.setString(4, contact.getCity());
					ps.setString(5, contact.getState());
					ps.setString(6, contact.getZip());
					ps.setString(7, contact.getPhoneNumber());
					ps.setString(8, contact.getEmail());
					ps.executeUpdate();
					
					conn.commit(); //success
					System.out.println("Contact added by "+Thread.currentThread().getName()+" : "+contact.getFirstName());
					
				}catch(SQLException e) {
					try {
						if(conn != null) {
							conn.rollback();
						}
					}catch(SQLException ex) {
						System.out.println(ex.getMessage());
					}
				}
			});
		}
		executor.shutdown();
		System.out.println("===========All Contact task submitted=========");	
	}
	
	//view all contact to database
	public void viewAllContact() {
		try {
			String sql="SELECT * FROM contact";
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet result = ps.executeQuery();
			
			while(result.next()) {
				Contact c = new Contact(result.getString("first_name"), result.getString("last_name"), result.getString("address"), result.getString("city"), result.getString("state"), result.getString("zip"), result.getString("phone_number"),result.getString("email"));
				System.out.println(c);
			}
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}
	}
}
