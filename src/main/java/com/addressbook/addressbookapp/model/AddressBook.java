package com.addressbook.addressbookapp.model;

import java.util.*;

public class AddressBook {
	List<Contact> contactList = new ArrayList<>();
	
 
    public void addContact(Contact contact) {

        boolean duplicate = contactList.stream()
                .anyMatch(c -> c.equals(contact));

        if (duplicate) {
            System.out.println("Duplicate Contact Found!");
            return;
        }

        contactList.add(contact);
        System.out.println("Contact Added Successfully");
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
				System.out.println("contact udpated.");
				return;
			}
		}
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
		Contact removeContact = null;
		for(Contact c : contactList) {
			if(c.getFirstName().equalsIgnoreCase(name)) {
				removeContact = c;
				break;
			}
		}
		if(removeContact!=null) {
			contactList.remove(removeContact);
			System.out.println("contact deleted!");
			return;
		}else {
			System.out.println("contact not found by name!");
		}
	}
	
}