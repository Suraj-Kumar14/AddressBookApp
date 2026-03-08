package com.addressbook.addressbookapp.main;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import com.addressbook.addressbookapp.io.JsonFileService;
import com.addressbook.addressbookapp.model.AddressBook;
import com.addressbook.addressbookapp.model.AddressBookSystem;
import com.addressbook.addressbookapp.model.Contact;

public class AddressBookMain {
	static Scanner sc = new Scanner(System.in);
	static AddressBookSystem system = new AddressBookSystem();
	static JsonFileService jsonService = new JsonFileService();
	
	public static void main(String[] args) {
		AddressBook addressBook = new AddressBook();
		while(true) {
			System.out.println("\n--------------------Address Book System App--------------------");
			System.out.println("1. Add Contact");
			System.out.println("2. Update Contact");
			System.out.println("3. Delete Contact");
			System.out.println("4. View All Contact");
			System.out.println("5. Search person by city");
			System.out.println("6. Search person by state");
			System.out.println("7. Count Contact by city");
			System.out.println("8. Sort Contact by name");
			System.out.println("9. Sort Contact by ZIP");
			System.out.println("10. Read Contact From File");
			System.out.println("11. Read Contact From CSV File");
			System.out.println("12. Read Contact from JSON file");
			System.out.println("0. Exit");
			System.out.println("--------------------------------------------------");
			
			int choice = sc.nextInt();
			sc.nextLine();
			
			if(choice==0) {
				System.out.println("Thanks for using our services!");
				break;
			}
			
			switch(choice) {
			
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
					
				case 5:
					System.out.println("Enter person first name and last name");
					String name = sc.nextLine();
					System.out.println("Enter city name to search: ");
					String city = sc.nextLine();
					addressBook.searchPerson(name, city);
					break;
					
				case 6:
					System.out.println("Enter the state name: ");
					String statemName = sc.nextLine();
					addressBook.viewByState(statemName);
					break;
					
				case 7:
					System.out.println("Enter city name: ");
					String cityName = sc.next();
					addressBook.countNumberByCity(cityName);
					break;
					
				case 8:
					addressBook = chooseAddressBook();
					addressBook.sortByName();
					break;
					
				case 9:
					addressBook = chooseAddressBook();
					addressBook.sortByZIP();
					break;
	
				case 10:
					System.out.println("Enter AddressBook name: ");
			        String bookNames = sc.nextLine();
					if(!system.exists(bookNames)) {
			            system.addAddressBook(bookNames);
			        }

			        addressBook = system.getAddressBook(bookNames);
					readContactFromFile(addressBook,"dataFiles/contacts.txt");
					break;

				case 11:
					System.out.println("Enter AddressBook name: ");
			        String bookNamesCSV = sc.nextLine();
					if(!system.exists(bookNamesCSV)) {
			            system.addAddressBook(bookNamesCSV);
			        }

			        addressBook = system.getAddressBook(bookNamesCSV);
			        readContactFromFile(addressBook, "dataFiles/contacts.csv");
			        break;
			        
				case 12:
					List<Contact> contacts = jsonService.readContacts();
					System.out.println(contacts);
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
	
	public static AddressBook chooseAddressBook() {
		System.out.println("Enter AddressBook name:");
		String bookName = sc.nextLine();

		AddressBook book = system.getAddressBook(bookName);
		while(book==null) {
			System.out.println("AddressBook not found! please choise these following!");
			System.out.println("====================");
			system.listAllAddressBooks();
			System.out.println("====================");
			while(true) {
				System.out.println("\nEnter AddressBook name:");
				String bookN = sc.nextLine();
				book = system.getAddressBook(bookN);
				if(bookN!=null) {
					break;
				}
			}
		}
		return book;
	}
	
	public static void readContactFromFile(AddressBook addressBook, String filePath) {
		try (BufferedReader read = new BufferedReader(new InputStreamReader(new FileInputStream(filePath)))) {
			String line;
			while((line = read.readLine())!=null) {
				String data[] = line.split(",");
				if(data.length==8) {
					addressBook.addContact(new Contact(data[0],data[1],data[2],data[3],data[4],data[5],data[6], data[7]));
				}else {
					System.out.println("Contact data not correct format in file!");
					return;
				}
			}
		}
		catch(IOException e) {
			System.out.println(e.getMessage());
		}
	}
}