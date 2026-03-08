package com.addressbook.addressbookapp.main;
import java.util.*;

import com.addressbook.addressbookapp.model.AddressBook;
import com.addressbook.addressbookapp.model.Contact;

public class AddressBookMain {
	static Scanner sc = new Scanner(System.in);
	static AddressBook addressBook = new AddressBook();
	public static void main(String[] args) {
		addressBook.addContact(takeInput());
		
		System.out.println("Enter first name to edit contact: ");
		String updateName = sc.nextLine();
		
		if(!addressBook.findByName(updateName)) {
			System.out.println("First name not found!");
			return;
		}
		
		addressBook.editContactByName(updateName, takeInput());
	}
	
	public static Contact takeInput() {
		System.out.println("Enter first name: ");
		String firstName = sc.nextLine();
		
		System.out.println("Enter last name: ");
		String lastName = sc.nextLine();
		
		System.out.println("Enter address: ");
		String address = sc.nextLine();
		
		System.out.println("Enter city name: ");
		String city = sc.nextLine();
		
		System.out.println("Enter state name: ");
		String state = sc.nextLine();
		
		System.out.println("Enter zip: ");
		String zip = sc.next();
		sc.nextLine();
		
		System.out.println("Enter phone number: ");
		String phoneNumber = sc.nextLine();
		
		System.out.println("Enter email: ");
		String email = sc.nextLine();
		
		return new Contact(firstName, lastName, address, city, state, zip, phoneNumber, email);
	}
}