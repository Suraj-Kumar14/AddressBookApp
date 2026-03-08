package com.addressbook.addressbookapp.main;

import java.util.*;

import com.addressbook.addressbookapp.model.AddressBook;
import com.addressbook.addressbookapp.model.Contact;

public class AddressBookMain {

    static Scanner sc = new Scanner(System.in);
    static AddressBook addressBook = new AddressBook();

    public static void main(String[] args) {

        while (true) {

            System.out.println("\n===== Address Book Menu =====");
            System.out.println("1. Add Contact");
            System.out.println("2. Edit Contact");
            System.out.println("3. Delete Contact");
            System.out.println("4. Exit");
            System.out.println("Enter your choice: ");

            int choice = sc.nextInt();
            sc.nextLine(); 

            switch (choice) {

                case 1:
                    addressBook.addContact(takeInput());
                    break;

                case 2:
                    System.out.println("Enter first name to edit contact: ");
                    String updateName = sc.nextLine();

                    if (!addressBook.findByName(updateName)) {
                        System.out.println("First name not found! So we can't update!");
                    } else {
                        addressBook.editContactByName(updateName, takeInput());
                        System.out.println("Contact Updated Successfully");
                    }
                    break;

                case 3:
                    System.out.println("Enter first name to delete contact: ");
                    String deleteName = sc.nextLine();

                    if (!addressBook.findByName(deleteName)) {
                        System.out.println("First name not found! So we can't delete");
                    } else {
                        addressBook.deleteContactByName(deleteName);
                        System.out.println("Contact Deleted Successfully");
                    }
                    break;

                case 4:
                    System.out.println("Exiting Address Book...");
                    System.exit(0);

                default:
                    System.out.println("Invalid choice! Try again.");
            }
        }
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