package com.addressbook.addressbookapp.io;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.addressbook.addressbookapp.model.Contact;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JsonFileService {
	 private static final String FILE_NAME = "dataFiles/contacts.json";
	 
	 public void writeContacts(List<Contact> contacts) {
		 Gson gson = new GsonBuilder().setPrettyPrinting().create();
		 
		 try ( FileWriter write = new FileWriter(FILE_NAME)){
			
			 gson.toJson(contacts,write);
			 System.out.println("Contacts written to JSON file.");
		 }
		 catch(IOException e) {
			 System.out.println(e.getMessage());
		 }
	 }
	 
	 public List<Contact> readContacts() {
		 Gson gson = new Gson();
		 try (FileReader read = new FileReader(FILE_NAME)){
			 Contact[] contacts = gson.fromJson(read, Contact[].class);
			 return Arrays.asList(contacts);
		 }
		 catch(IOException e) {
			 System.out.println(e.getMessage());
		 }
		 return new ArrayList<>();
	 }
}