package com.service.note.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.service.note.client.PatientServiceClient;
import com.service.note.dto.NoteDTO;
import com.service.note.dto.PatientDTO;
import com.service.note.dto.UpdateNoteDTO;
import com.service.note.entity.Note;
import com.service.note.repository.NoteRepository;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ActiveProfiles("test")
public class NoteControllerIT {
    private MockMvc mockMvc;
    @Autowired
    private WebApplicationContext context;
    @Autowired
    private NoteRepository repository;
    @MockBean
    private PatientServiceClient patientServiceClient;
    private PatientDTO patient;
    private static final String id = "60c72b2f9b1d8b3b4c8e69d1";
    private UpdateNoteDTO updateNoteDTO;
    private Note note;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .build();
        patient =  new PatientDTO();
        patient.setNom("nom");
        patient.setPrenom("prenom");
        patient.setGenre("f");
        patient.setAdressePostale("67100");
        patient.setDateDeNaissance(new Date("12/12/2000"));
        patient.setNumeroDeTelephone("03333333");
        updateNoteDTO = new UpdateNoteDTO();
        updateNoteDTO.setNoteObservation("observation");
        note = new Note();
        note.setId(new ObjectId(id));
        note.setPatientId(1);
        note.setPatientName("prenom");
        note.setNoteObservation("observation");
    }
    @Test
    @DisplayName("get test")
    public void getTest() throws Exception {

        List<Note> allNote = repository.findAll();
        Note note1 = allNote.getFirst();
        String noteId = String.valueOf(note1.getId());

        mockMvc.perform(get("/note/{id}", noteId)
                        .with(csrf()))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("get by patient id test")
    public void getByPatientIdTest() throws Exception {

        mockMvc.perform(get("/note/patient/1")
                        .with(csrf()))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("delete test")
    public void deleteTest() throws Exception {

        mockMvc.perform(delete("/note/60c72b2f9b1d8b3b4c8e69d1")
                        .with(csrf()))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("update test")
    public void updateTest() throws Exception {

        NoteDTO noteDTO = new NoteDTO();
        noteDTO.setNoteObservation("update");
        noteDTO.setPatientName("update");
        noteDTO.setPatientId(12);
        List<Note> allNote = repository.findAll();
        Note note1 = allNote.getFirst();
        String noteId = String.valueOf(note1.getId());
        when(patientServiceClient.get(note1.getPatientId())).thenReturn(patient);

        mockMvc.perform(put("/note/{id}", noteId)
                        .with(csrf())
                        .content(asJsonString(updateNoteDTO))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("add test")
    public void addTest() throws Exception {
        List<Note> allNote = repository.findAll();
        Note note1 = allNote.getFirst();
        when(patientServiceClient.get(note1.getPatientId())).thenReturn(patient);

        mockMvc.perform(post("/note/patient/{id}",note1.getPatientId())
                        .with(csrf())
                        .content(asJsonString(updateNoteDTO))
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