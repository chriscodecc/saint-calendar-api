package com.dev.saintcalendar.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.assertj.MockMvcTester.MockMvcRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.dev.saintcalendar.dto.SaintRequest;
import com.dev.saintcalendar.model.Saint;
import com.dev.saintcalendar.service.SaintService;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.http.MediaType;

@WebMvcTest(SaintController.class)
public class SaintControllerTest {
    
    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockitoBean
    SaintService service;

    @Test
    void shouldReturnTheListsOfAllSaintsThisMonth()throws Exception{
        Saint s1 = new Saint( 
            "test1",
            1,
            2,
            "patronage",
            "seom text",
            "tropar",
            "kondak",
            true);

        List<Saint> saintList = new ArrayList<Saint>();
        saintList.add(s1);
/**
        when(service.findByMonth(2)).thenReturn(saintList);
        mockMvc.perform(
            MockMvcRequestBuilders.get("http://localhost:8080/api/saints/2")
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("test1"));**/
    }

    @Test
    void createAndSaveANewSaint() throws Exception{

        Saint s1 = new Saint( 
            "test1",
            1,
            2,
            "patronage",
            "seom text",
            "tropar",
            "kondak",
            true);

        SaintRequest request = new SaintRequest(
            "test1",
            1, 
            2, 
            "patronage", 
            "seom text", 
            "tropar", 
            "kondak", 
            true);

        
        when(service.saveSaint(any(SaintRequest.class)))
            .thenReturn(s1);

        mockMvc.perform(
                MockMvcRequestBuilders.post("/api/saints/newSaint")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(asJsonString(request)))
                                .andExpect(status().is(200))
                                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("test1"));;
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }   
}
