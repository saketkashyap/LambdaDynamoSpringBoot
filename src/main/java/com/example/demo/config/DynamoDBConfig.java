package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;

@Configuration
public class DynamoDBConfig {

	 
	@Bean
	 public DynamoDB initDynamoDbClient() {
	        AmazonDynamoDBClient client = new AmazonDynamoDBClient();
	        client.setRegion(Region.getRegion(Regions.US_EAST_1));
	       return new DynamoDB(client);
	    }
}
