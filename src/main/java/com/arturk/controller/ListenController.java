package com.arturk.controller;

import com.arturk.domain.Person;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
public class ListenController {

    @RequestMapping(value = "/listenXML", method = RequestMethod.POST, consumes = MediaType.APPLICATION_XML_VALUE)
    public void listenJsonXML(@RequestBody Person person) {
        System.out.println("listenJsonXML method " + person);
    }

    @RequestMapping(value = "/listenJson", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void listenJsonRest(@RequestBody Person person) {
        System.out.println("listenJsonRest method " + person);
    }

    @RequestMapping(value = "/listenPerson", method = RequestMethod.GET)
    public Person createPerson(@RequestParam String name, @RequestParam int age) {
        return new Person(age, name, "Ukrainian");
    }
}
