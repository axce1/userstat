package com.example.userstat.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@SpringBootTest
@WebAppConfiguration
class MainControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @BeforeEach
    void setup() {
        this.mockMvc = webAppContextSetup(webApplicationContext).build();
    }

    @Test
    void addVisitor() throws Exception {
        mockMvc.perform(get("/visitor?id=1&page_id=42"))
                .andExpect(status().isOk());
        mockMvc.perform(get("/visitor?id=1&page_id=42"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].description", is("allDay")))
                .andExpect(jsonPath("$[0].count", is(1)));

    }

    @Test
    void statPeriod() throws Exception {
        mockMvc.perform(get("/stat?from=2020-07-01&to=2020-12-12")
                .contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].description", is("allInPeriod")))
                .andExpect(jsonPath("$[0].count", is(2)))
                .andExpect(jsonPath("$[1].description", is("uniqueInPeriod")))
                .andExpect(jsonPath("$[1].count", is(1)))
                .andExpect(jsonPath("$[2].description", is("regularInPeriod")))
                .andExpect(jsonPath("$[2].count", is(0)));

    }
}