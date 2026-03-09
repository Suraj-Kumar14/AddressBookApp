package com.addressbook.addressbookapp;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.addressbook.addressbookapp.model.AddressBookService;
import com.addressbook.addressbookapp.model.Contact;
import static io.restassured.RestAssured.*;

import io.restassured.response.Response;

public class AddressBookJsonServerTest {
	AddressBookService service = new AddressBookService();
	
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
}