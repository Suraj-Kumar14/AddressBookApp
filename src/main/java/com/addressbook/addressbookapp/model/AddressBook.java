package com.addressbook.addressbookapp.model;

import java.util.ArrayList;
import java.util.List;

public class AddressBook {
	List<Contact> contactList = new ArrayList<>();
	
	//add contact
	public void addContact(Contact contact) {
		contactList.add(contact);
	}
	
	//editContactByName
	public void editContactByName(String name, Contact contact) {
		for(Contact c : contactList) {
			if(c.getFirstName().equalsIgnoreCase(name)) {
				c.setFirstName(contact.getFirstName());
				c.setLastName(contact.getLastName());
				c.setAddress(contact.getAddress());
				c.setCity(contact.getCity());
				c.setState(contact.getState());
				c.setZip(contact.getZip());
				c.setPhoneNumber(contact.getPhoneNumber());
				c.setEmail(contact.getEmail());
				System.out.println("contact udpated");
				return;
			}
		}
			System.out.println("contact not found by name: "+name);
	}
		
	public boolean findByName(String name) {
		for(Contact c : contactList) {
			if(c.getFirstName().equalsIgnoreCase(name)) {
				return true;
			}
		}
		return false;
	}
	
	public void deleteContactByName(String name) {
		for(Contact c : contactList) {
			if(c.getFirstName().equalsIgnoreCase(name)) {
				contactList.remove(c);
				return;
			}
		}
	}
}