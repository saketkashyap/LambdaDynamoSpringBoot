package com.example.demo.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.ItemCollection;
import com.amazonaws.services.dynamodbv2.document.PutItemOutcome;
import com.amazonaws.services.dynamodbv2.document.QueryOutcome;
import com.amazonaws.services.dynamodbv2.document.spec.PutItemSpec;
import com.amazonaws.services.dynamodbv2.document.spec.QuerySpec;
import com.amazonaws.services.dynamodbv2.document.utils.ValueMap;
import com.amazonaws.services.dynamodbv2.model.ConditionalCheckFailedException;
import com.example.demo.pojo.Person;

@Component
public class PersonRepositoryImpl implements IPersonRepository{

	 private DynamoDB dynamoDb;
	    private String DYNAMODB_TABLE_NAME = "Person";
	    public PersonRepositoryImpl(DynamoDB db) {
	    	this.dynamoDb = db;
	    }
	    
	@Override
	public PutItemOutcome savePerson(Person person) throws ConditionalCheckFailedException{
		System.out.println("first name"+person.getFirstName());
        return this.dynamoDb.getTable(DYNAMODB_TABLE_NAME)
          .putItem(
            new PutItemSpec().withItem(new Item()
              .withString("firstName", person.getFirstName())
              .withString("lastName", person.getLastName())
              .withString("personId",person.getPersonId().toString())
              ));
    
	}

	@Override
	public Person getPerson(String id) {
		// TODO Auto-generated method stub
		
		QuerySpec spec  = new QuerySpec().
				withKeyConditionExpression("personId = :person_id").
				withValueMap(new ValueMap()
						.withString(":person_id", id));
		ItemCollection<QueryOutcome> items = this.dynamoDb.
				getTable(DYNAMODB_TABLE_NAME).query(spec);
		List<Person>personList = new ArrayList<>();
		Iterator<Item> iterator = items.iterator();
		while (iterator.hasNext()) {
		   Item item = iterator.next();
		  Person person = new Person();
		  person.setFirstName(String.valueOf(item.get("firstName")));
		  person.setLastName(String.valueOf(item.get("lastName")));
		  person.setPersonId(UUID.fromString(String.valueOf(item.get("personId"))));
		  personList.add(person);
		}
		if(personList.size() == 1) {
			return personList.get(0);
		}
		return null;
	}

}
