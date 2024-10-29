package com.example.kata.restAPI.RestTemplates.client;

import com.example.kata.restAPI.RestTemplates.model.User;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

public class UserRestClient {

    private static final String URL = "http://94.198.50.185:7081/api/users";
    private final RestTemplate restTemplate = new RestTemplate();
    private String sessionId;

    public void execute() {
        String part1 = addUser();
        String part2 = updateUser();
        String part3 = deleteUser();
        System.out.println("Result " + part1 + part2 + part3);
    }

    private String getSessionId() {
        if (sessionId == null) {
            ResponseEntity<String> response = restTemplate.exchange(URL, HttpMethod.GET, null, String.class);
            sessionId = response.getHeaders().getFirst(HttpHeaders.SET_COOKIE);
        }
        return sessionId;
    }

    private HttpHeaders getHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Cookie", getSessionId());
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }

    private String addUser() {
        User user = new User();
        user.setId(3L);
        user.setName("James");
        user.setLastName("Brown");
        user.setAge((byte) 30);

        HttpEntity<User> request = new HttpEntity<>(user, getHeaders());
        ResponseEntity<String> response = restTemplate.exchange(URL, HttpMethod.POST, request, String.class);
        return response.getBody();
    }

    private String updateUser() {
        User user = new User();
        user.setId(3L);
        user.setName("Thomas");
        user.setLastName("Shelby");
        user.setAge((byte) 30);

        HttpEntity<User> request = new HttpEntity<>(user, getHeaders());
        ResponseEntity<String> response = restTemplate.exchange(URL, HttpMethod.PUT, request, String.class);
        return response.getBody();
    }

    private String deleteUser() {
        HttpEntity<Void> request = new HttpEntity<>(getHeaders());
        ResponseEntity<String> response = restTemplate.exchange(URL + "/3", HttpMethod.DELETE, request, String.class);
        return response.getBody();
    }
}