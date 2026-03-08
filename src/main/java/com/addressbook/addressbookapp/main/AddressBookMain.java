package com.addressbook.addressbookapp.main;
import java.util.*;

import com.addressbook.addressbookapp.model.AddressBook;
import com.addressbook.addressbookapp.model.AddressBookSystem;
import com.addressbook.addressbookapp.model.Contact;

public class AddressBookMain {
	static Scanner sc = new Scanner(System.in);
	static AddressBookSystem system = new AddressBookSystem();
	
	public static void main(String[] args) {
		AddressBook addressBook = new AddressBook();
		while(true) {
			System.out.println("\n--------------------Address Book System App--------------------");
			System.out.println("Add Contact -> Enter '1': ");
			System.out.println("Update Contact -> Enter '2': ");
			System.out.println("Delete Contact -> Enter '3': ");
			System.out.println("View All Contact -> Enter '4': ");
			System.out.println("Exit -> Enter '0': ");
			System.out.println("--------------------------------------------------");
			
			int choise = sc.nextInt();
			sc.nextLine();
			
			if(choise==0) {
				System.out.println("Thanks for using our services!");
				break;
			}
			
			switch(choise) {
				case 1:
					System.out.println("--Welcome to Address Book Program--");
					System.out.println("Enter AddressBook name: ");
			        String bookName = sc.nextLine();

			        system.addAddressBook(bookName);

			        addressBook = system.getAddressBook(bookName);

			        // Add contact
			        addressBook.addContact(takeInput());
					break;
				case 2:
					System.out.println("Enter first name to edit contact: ");
					String updateName = sc.nextLine();
					
					if(!addressBook.findByName(updateName)) {
						System.out.println("First name not found! so we can't update!");
						return;
					}else {
						addressBook.editContactByName(updateName, takeInput());
					}
					break;
				case 3:
					System.out.println("Enter first name to delete contact : ");
					String deleteName = sc.nextLine();
					
					if(!addressBook.findByName(deleteName)) {
						System.out.println("First name not found! so we can't delete");
						return;
					}else {
						addressBook.deleteContactByName(deleteName);		
					}
					break;
				case 4: 
					addressBook.getAllContact();
					break;
				default:
					System.out.println("Invalid choise!");
			}
		}
	}
	
	public static Contact takeInput() {
		System.out.println("Enter first name: ");
		String firstName = sc.nextLine();
		
		
		System.out.println("Enter last name: ");
		String lastName = sc.nextLine();
		
		//check equals
		
		
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