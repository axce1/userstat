package com.example.userstat.controller;

import com.example.userstat.model.Visitor;
import com.example.userstat.repository.VisitorRepository;
import com.example.userstat.service.VisitorServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.sql.Date;
import java.time.LocalDate;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@SpringBootTest
@WebAppConfiguration
//@WebMvcTest(controllers = MainController.class)
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