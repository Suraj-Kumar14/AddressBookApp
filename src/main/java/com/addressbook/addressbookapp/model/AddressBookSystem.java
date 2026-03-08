package com.addressbook.addressbookapp.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class AddressBookSystem {
	Map<String, AddressBook> addressbooksystem = new HashMap<>();
	
	public void addAddressBook(String name) {
		if(addressbooksystem.containsKey(name)) {
			System.out.println("Address book already exist!");
			return;
		}
		
		addressbooksystem.put(name, new AddressBook());
	}
	
	public boolean exists(String name) {
	    return addressbooksystem.containsKey(name);
	}
	
	public AddressBook getAddressBook(String name) {
		return addressbooksystem.get(name);
	}
	
	public void listAllAddressBooks() {
		addressbooksystem.keySet().forEach(System.out::println);
	}
}