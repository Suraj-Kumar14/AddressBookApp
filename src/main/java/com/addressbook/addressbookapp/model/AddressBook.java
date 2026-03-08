package com.addressbook.addressbookapp.model;

import java.util.ArrayList;
import java.util.List;

public class AddressBook {
	List<Contact> contactList = new ArrayList<>();
	
	//add contact
	public void addContact(Contact contact) {
		contactList.add(contact);
	}
}