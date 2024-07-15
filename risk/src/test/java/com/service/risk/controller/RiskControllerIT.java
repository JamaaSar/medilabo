package com.service.risk.controller;

import com.service.risk.client.NoteServiceClient;
import com.service.risk.client.PatientServiceClient;
import com.service.risk.dto.NoteDTO;
import com.service.risk.dto.PatientDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Date;


import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class RiskControllerIT {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;
    @MockBean
    private PatientServiceClient patientServiceClient;
    @MockBean
    private NoteServiceClient noteServiceClient;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .build();
    }

    @Test
    @DisplayName("get test")
    public void getTest() throws Exception {
        PatientDTO patient =  new PatientDTO();
        patient.setId(1);
        patient.setNom("nom");
        patient.setPrenom("prenom");
        patient.setGenre("f");
        patient.setAdressePostale("67100");
        patient.setDateDeNaissance(new Date("12/12/2000"));
        patient.setNumeroDeTelephone("03333333");
        NoteDTO note = new NoteDTO();
        note.setPatientId(1);
        note.setPatientName("prenom");
        note.setNoteObservation("observation");
        when(patientServiceClient.get(1)).thenReturn(patient);
        when(noteServiceClient.get(1)).thenReturn(note);

        mockMvc.perform(get( "/risk/{id}", patient.getId())
                        .with(csrf()))
                .andExpect(status().isOk());
    }

}
