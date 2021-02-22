package com.example.demo.pojo;

import java.util.UUID;

public class Person {
	private String firstName;
    private String lastName;
    private UUID personId;
    
    
	public UUID getPersonId() {
		return personId;
	}
	public void setPersonId(UUID personId) {
		this.personId = personId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
    
    
}
