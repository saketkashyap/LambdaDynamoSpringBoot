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

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class PersonRepositoryImpl implements IPersonRepository{

	 private DynamoDB dynamoDbOrDaxClient;
	 
	    private String DYNAMODB_TABLE_NAME = "Person";
	    public PersonRepositoryImpl(DynamoDB dbClient) {
	    	this.dynamoDbOrDaxClient = dbClient;
	    }
	    
	@Override
	public PutItemOutcome savePerson(Person person) throws ConditionalCheckFailedException{
		
		System.out.println("first name"+person.getFirstName());
        return this.dynamoDbOrDaxClient.getTable(DYNAMODB_TABLE_NAME)
          .putItem(
            new PutItemSpec().withItem(new Item()
              .withString("firstName", person.getFirstName())
              .withString("lastName", person.getLastName())
              .withString("personId",person.getPersonId().toString())
              ));
    
	}

	//with DynamoDB or Dax client
	@Override
	public Person getPerson(String id) {
		System.out.println("getting person");
		// TODO Auto-generated method stub
		 long startTime = System.nanoTime();
		 double duration = 0;
		QuerySpec spec  = new QuerySpec().
				withKeyConditionExpression("personId = :person_id").
				withValueMap(new ValueMap()
						.withString(":person_id", id));
		ItemCollection<QueryOutcome> items = this.dynamoDbOrDaxClient.
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
		duration = (System.nanoTime() - startTime);
        log.info("FetchTime = " + duration  + " nano seconds");
		return null;
	}
	

}
