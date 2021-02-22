package com.example.demo.dao;

import com.amazonaws.services.dynamodbv2.document.PutItemOutcome;
import com.amazonaws.services.dynamodbv2.model.ConditionalCheckFailedException;
import com.example.demo.pojo.Person;

public interface IPersonRepository {

	public PutItemOutcome savePerson(Person person) throws ConditionalCheckFailedException;
	
	public Person getPerson(String id);
}
