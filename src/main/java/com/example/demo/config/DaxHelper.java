package com.example.demo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

import com.amazon.dax.client.dynamodbv2.AmazonDaxClientBuilder;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;

@Configuration
public class DaxHelper {
	
	@Value("${aws.dax.endpoint}")
	private String daxEndpoint;
	
	@Bean
	DynamoDB getDynamoOrDaxClient() {
		if(StringUtils.hasLength(daxEndpoint)) {
			return getDaxClient();
		}
		else {
			return getDynamoDBClient();
		}
	}
	
	
	DynamoDB getDynamoDBClient() {
        System.out.println("Creating DynamoDB client ");
       
        
        AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard()
                .withRegion(Regions.US_EAST_1)
                .build();
        return new DynamoDB(client);
     }

	
    DynamoDB getDaxClient() {
        System.out.println("Creating a DAX client with cluster endpoint " + daxEndpoint);
        AmazonDaxClientBuilder daxClientBuilder = AmazonDaxClientBuilder.standard();
        daxClientBuilder.withRegion(Regions.US_EAST_1).withEndpointConfiguration(daxEndpoint);
        AmazonDynamoDB client = daxClientBuilder.build();
        return new DynamoDB(client);
     }
}
