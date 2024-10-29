package com.example.kata.restAPI.RestTemplates;

import com.example.kata.restAPI.RestTemplates.client.UserRestClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RestTemplatesApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestTemplatesApplication.class, args);
		UserRestClient client = new UserRestClient();
		client.execute();
	}
}
