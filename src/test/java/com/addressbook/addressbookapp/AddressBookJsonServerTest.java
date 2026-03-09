package com.addressbook.addressbookapp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.addressbook.addressbookapp.model.AddressBookService;
import com.addressbook.addressbookapp.model.Contact;
import static io.restassured.RestAssured.*;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class AddressBookJsonServerTest {
	AddressBookService service = new AddressBookService();
	List<Contact> addressBookMemory = new ArrayList<>();
	
	@Test
    public void readContactsFromJsonServer() {

        Response response =
                given()
                .when()
                .get("http://localhost:3000/contacts")
                .then()
                .statusCode(200)
                .extract()
                .response();

        Contact[] contactArray = response.as(Contact[].class);

        List<Contact> contactList = Arrays.asList(contactArray);

        service.updateMemory(contactList);

        service.getContacts().forEach(System.out::println);
    }
	
	@Test
	public void addMultipleContacts() {
		 List<Contact> newContacts = List.of(
	                new Contact("Ravi","Kumar","addr1","Hyderabad","TS","500001","9991112222","ravi@gmail.com"),
	                new Contact("Rahul","Sharma","addr2","Delhi","DL","110001","9992223333","rahul@gmail.com")
	        );
		 
		 newContacts.forEach(contact->{
			 Contact responseContact = 
					 given()
					 	.contentType(ContentType.JSON)
					 	.body(contact)
					 .when()
					 	.post("http://localhost:3000/contacts")
					 .then()
					 	.statusCode(201)
					 	.extract()
					 	.as(Contact.class);
			 addressBookMemory.add(responseContact);
		 });
		 
		 System.out.println("Contact in memory");
		 addressBookMemory.forEach(System.out::println);
	}
	
	@Test
    public void updateContact() {

        int contactId = 2;

        Contact updatedContact =
                new Contact(
                        "Rahul",
                        "Sharma",
                        "addr2",
                        "Mumbai",
                        "MH",
                        "400001",
                        "9992223333",
                        "rahul@gmail.com");

        Contact responseContact =
                given()
                    .contentType(ContentType.JSON)
                    .body(updatedContact)
                .when()
                    .put("http://localhost:3000/contacts/" + contactId)
                .then()
                    .statusCode(200)
                    .extract()
                    .as(Contact.class);

        for(int i = 0; i < addressBookMemory.size(); i++) {

            if(addressBookMemory.get(i).getUserId() == contactId) {
                addressBookMemory.set(i, responseContact);
                break;
            }
        }

        System.out.println("Updated Memory:");
        addressBookMemory.forEach(System.out::println);
    }
	
	@Test
	public void deleteContact() {

	    int contactId = 2;

	    given()
	        .when()
	        .delete("http://localhost:3000/contacts/" + contactId)
	        .then()
	        .statusCode(200);

	    addressBookMemory.removeIf(contact -> contact.getUserId() == contactId);

	    System.out.println("Memory after deletion:");
	    addressBookMemory.forEach(System.out::println);
	}
}