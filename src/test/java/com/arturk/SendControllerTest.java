package com.arturk;

import com.arturk.domain.Person;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@WireMockTest(httpPort = 8080)
public class SendControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void createPersonTest() throws Exception {
        String name = "Artur";
        String age = "20";
        Person person = createPerson(name, age);
        stubFor(WireMock.get("/listenPerson?name=Artur&age=20").willReturn(aResponse().withBody(objectMapper.writeValueAsString(person))
                .withHeader("Content-Type","application/json").withStatus(200)));
        mockMvc.perform(post("/createPerson").param("name", name).param("age", age))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Artur"))
                .andExpect(jsonPath("$.nationality").value("Ukrainian"))
                .andExpect(jsonPath("$.age").value("20"));

    }

    private Person createPerson(String name, String age) {
        Person person = new Person();
        person.setName(name);
        person.setAge(Integer.parseInt(age));
        person.setNationality("Ukrainian");
        return person;
    }
}
