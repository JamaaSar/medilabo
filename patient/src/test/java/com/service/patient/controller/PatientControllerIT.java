package com.service.patient.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.service.patient.dto.PatientDTO;
import com.service.patient.repository.PatientRepository;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;

import java.util.Date;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ActiveProfiles("test")
public class PatientControllerIT {
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;
    @Autowired
    private PatientRepository repository;

    @Autowired
    private EntityManager entityManager;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .build();
    }

    @AfterEach
    public void afterEach() {
        entityManager.clear();
    }

    @Test
    @DisplayName("get test")
    public void getTest() throws Exception {

        mockMvc.perform(get("/patient/1")
                        .with(csrf()))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("get all test")
    public void getAllTest() throws Exception {

        mockMvc.perform(get("/patient")
                        .with(csrf()))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("delete test")
    public void deleteTest() throws Exception {

        mockMvc.perform(delete("/patient/1")
                        .with(csrf()))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("update test")
    public void updateTest() throws Exception {

        PatientDTO patient = new PatientDTO();
        patient.setNom("update");
        patient.setPrenom("update");
        patient.setGenre("f");
        patient.setAdressePostale("67100");
        patient.setDateDeNaissance(new Date("12/12/2000"));
        patient.setNumeroDeTelephone("03333333");

        mockMvc.perform(put("/patient/2")
                        .with(csrf())
                        .content(asJsonString(patient))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("add test")
    public void addTest() throws Exception {

        PatientDTO patient = new PatientDTO();
        patient.setNom("add");
        patient.setPrenom("add");
        patient.setGenre("f");
        patient.setAdressePostale("67100");
        patient.setDateDeNaissance(new Date("12/12/2000"));
        patient.setNumeroDeTelephone("03333333");

        mockMvc.perform(post("/patient")
                        .with(csrf())
                        .content(asJsonString(patient))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk());
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}