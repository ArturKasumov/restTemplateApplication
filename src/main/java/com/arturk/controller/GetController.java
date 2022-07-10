package com.arturk.controller;

import com.arturk.domain.Person;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class GetController {


    @RequestMapping(value = "/getXML", method = RequestMethod.GET, produces = MediaType.APPLICATION_XML_VALUE)
    @ResponseBody
    public Person getXmlRest(){
        return new Person(20, "Artur", "Ukrainian");
    }

    @RequestMapping(value = "/getJson", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Person getJsonRest(){
        return new Person(20, "Artur", "Ukrainian");
    }

    @RequestMapping(value = "/getPersonForOtherOperation", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Person getPerson() {
        return new Person(20, "Artur", "Ukrainian");
    }
}
