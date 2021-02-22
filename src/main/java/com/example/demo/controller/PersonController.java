package com.example.demo.controller;

import java.util.UUID;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.pojo.Person;
import com.example.demo.service.PersonService;

@RestController
@RequestMapping("/person")
public class PersonController {

	private PersonService personService;
	
	public PersonController(PersonService service) {
		this.personService = service;
	}
	@PostMapping("/save")
	public String savePerson(@RequestBody Person request) {
		
		if(request.getPersonId()== null) {
			request.setPersonId(UUID.randomUUID());
		}
		return personService.savePerson(request);
		
	}
	
	
	  @GetMapping("/fetch/{id}") 
	  public Person getPerson(@PathVariable("id") UUID id) { 
		  
		  return personService.getPerson(id);
	   }
	 
}
