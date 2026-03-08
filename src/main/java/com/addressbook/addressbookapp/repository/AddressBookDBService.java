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
    
    // UC17
    public boolean updateContact(String name, String address, String city, String state, String zip, String phone, String email) {
        String query = "UPDATE contacts SET address=?, city=?, state=?, zip=?, phone=?, email=? WHERE first_name=?";

        try(Connection con = getConnection()) {

            PreparedStatement ps = con.prepareStatement(query);

            ps.setString(1, address);
            ps.setString(2, city);
            ps.setString(3, state);
            ps.setString(4, zip);
            ps.setString(5, phone);
            ps.setString(6, email);
            ps.setString(7, name);

            int result = ps.executeUpdate();

            return result > 0;

        } catch(Exception e) {
            e.printStackTrace();
        }

        return false;
    }
    
    //UC18
    public List<Contact> getContactsByDateRange(String startDate, String endDate) {

        List<Contact> list = new ArrayList<>();

        String query = "SELECT * FROM contacts WHERE date_added BETWEEN ? AND ?";

        try(Connection con = getConnection()) {

            PreparedStatement ps = con.prepareStatement(query);

            ps.setString(1, startDate);
            ps.setString(2, endDate);

            ResultSet rs = ps.executeQuery();

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