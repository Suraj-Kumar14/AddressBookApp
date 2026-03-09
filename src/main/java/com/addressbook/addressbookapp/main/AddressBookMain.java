package com.addressbook.addressbookapp.main;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.addressbook.addressbookapp.database.SQLQuery;
import com.addressbook.addressbookapp.model.Contact;

public class AddressBookMain {
	static Scanner sc = new Scanner(System.in);	
	
	public static void main(String[] args) {
		SQLQuery query=new SQLQuery();
		
		while(true) {
			System.out.println("==========Address Book System App===========");
			System.out.println("1. Add Contact");
			System.out.println("2. View all contact");
			System.out.println("0. Exit");
			System.out.print("Enter choice: ");
			int choice=sc.nextInt();
			
			if(choice==0) {
				System.out.println("Thanks for using our services!");
				break;
			}
			
			switch(choice) {
				case 1:
					System.out.print("Enter how many contacts you want to add: ");
					int n=sc.nextInt();
					if(n<=0) {
						System.out.println("Please Enter positive number!");
						break;
					}
					query.addContact(takeInput(n));
					break;
					
				case 2:
					query.viewAllContact();
					break;
					
				default:
					System.out.println("Invalid choice!");
			}
			
		}
	}
	
	public static List<Contact> takeInput(int n) {
		List<Contact> contactList=new ArrayList<>();
		while(n-->0)
		{		
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
			
			contactList.add(new Contact(firstName,lastName,address,city,state,zip,phoneNumber,email));
		}
		return contactList;
	}
}