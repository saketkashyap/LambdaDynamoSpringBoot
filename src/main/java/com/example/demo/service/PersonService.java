package com.example.demo.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.example.demo.dao.IPersonRepository;
import com.example.demo.pojo.Person;

@Service
public class PersonService {

	private IPersonRepository personRepo;
	
	public PersonService(IPersonRepository personRepo) {
		this.personRepo = personRepo;
	}
   
	public String savePerson(Person person) {
		personRepo.savePerson(person);
		return "Success";
	}
	
	
	  public Person getPerson(UUID id) {
	    return personRepo.getPerson(id.toString());
	  }
	 
	
	
}
