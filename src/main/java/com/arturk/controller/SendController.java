package com.arturk.controller;

import com.arturk.domain.Person;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@RestController
public class SendController {

    private final RestTemplate restTemplate;

    public SendController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @RequestMapping(value = "/sendXML", method = RequestMethod.GET)
    public void sendXML(){
        Person person = new Person(20, "Artur", "Ukrainian");
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Type", "application/xml");
        HttpEntity<Person> httpEntity = new HttpEntity<>(person, httpHeaders);
        restTemplate.exchange("http://localhost:8080/listenXML", HttpMethod.POST, httpEntity, String.class);
    }

    @RequestMapping(value = "/sendJson", method = RequestMethod.GET)
    public void sendJson(){
        Person person = new Person(20, "Artur", "Ukrainian");
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Type", "application/json");
        HttpEntity<Person> httpEntity = new HttpEntity<>(person, httpHeaders);
        restTemplate.exchange("http://localhost:8080/listenJson", HttpMethod.POST, httpEntity, String.class);
    }

    @RequestMapping(value = "/getPerson", method = RequestMethod.GET)
    public String getPersonPerson(){
        ResponseEntity<String> responseEntity = restTemplate.getForEntity("http://localhost:8080/getPersonForOtherOperation", String.class);
        return responseEntity.getBody();
    }

    @RequestMapping(value = "/createPerson", method = RequestMethod.POST)
    public Person createPerson(@RequestParam String name, @RequestParam String age){
        Map<String, String> variables = new HashMap<>();
        variables.put("name", name);
        variables.put("age", age);
        ResponseEntity<Person> responseEntity = restTemplate.getForEntity("http://localhost:8080/listenPerson?name={name}&age={age}", Person.class, variables);
        return responseEntity.getBody();
    }
}
