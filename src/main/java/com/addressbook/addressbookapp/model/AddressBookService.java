package com.addressbook.addressbookapp.model;

import java.util.ArrayList;
import java.util.List;

public class AddressBookService {
	private List<Contact>addressBookMemory = new ArrayList<>();
	
	public void updateMemory(List<Contact>contacts) {
		addressBookMemory.addAll(contacts);
	}
	
	public List<Contact> getContacts(){
		return addressBookMemory;
	}
	
}