package com.addressbook.addressbookapp.repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.addressbook.addressbookapp.model.Contact;

public class AddressBookDBService {

    private static final String URL = "jdbc:mysql://localhost:3306/addressbookdb";
    private static final String USER = "root";
    private static final String PASSWORD = "Suraj@123";

    public Connection getConnection() throws SQLException {

        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
    
    public List<Contact> getAllContacts() {

        List<Contact> list = new ArrayList<>();

        try(Connection con = getConnection()) {

            String query = "SELECT * FROM contacts";

            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while(rs.next()) {

                Contact contact = new Contact(
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("address"),
                        rs.getString("city"),
                        rs.getString("state"),
                        rs.getString("zip"),
                        rs.getString("phone"),
                        rs.getString("email")
                );

                list.add(contact);
            }

        } catch(Exception e) {
            e.printStackTrace();
        }

        return list;
    }
}