package com.service.note.service;

import com.service.note.client.PatientServiceClient;
import com.service.note.dto.NoteDTO;
import com.service.note.dto.PatientDTO;
import com.service.note.dto.UpdateNoteDTO;
import com.service.note.entity.Note;
import com.service.note.exceptions.BadRequestException;
import com.service.note.exceptions.NotFoundException;
import com.service.note.mapper.NoteMapper;
import com.service.note.repository.NoteRepository;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class NoteServiceTest {

    @Mock
    private NoteRepository repository;
    @Mock
    private NoteMapper mapper;
    @Mock
    private PatientServiceClient patientServiceClient;
    @InjectMocks
    private NoteService service;
    private NoteDTO noteDTO;
    private Note note;
    private List<NoteDTO> noteDTOList;
    private List<Note> noteList;
    private UpdateNoteDTO updateNoteDTO;
    private PatientDTO patientDTO;

    private static final String id = "60c72b2f9b1d8b3b4c8e69d1";

    @BeforeEach
    public void setUp() {

        note = new Note();
        noteDTO = new NoteDTO();
        note.setPatientId(1);
        note.setPatientName("prenom");
        note.setNoteObservation("observation");

        noteDTO.setPatientId(note.getPatientId());
        noteDTO.setPatientName(note.getPatientName());
        noteDTO.setNoteObservation(note.getNoteObservation());

        noteDTOList = new ArrayList<>();
        noteList = new ArrayList<>();
        noteDTOList.add(noteDTO);
        noteList.add(note);
        updateNoteDTO = new UpdateNoteDTO();
        updateNoteDTO.setNoteObservation("observation");

        patientDTO = new PatientDTO();

        patientDTO.setNom("nom");
        patientDTO.setPrenom("prenom");
        patientDTO.setGenre("f");
        patientDTO.setAdressePostale("67100");
        patientDTO.setDateDeNaissance(new Date("12/12/2000"));
        patientDTO.setNumeroDeTelephone("03333333");
    }

    @Test
    void getAllTest() {
        when(repository.findAll()).thenReturn(noteList);
        when(mapper.toNoteDtoList(anyList())).thenReturn(noteDTOList);
        List<NoteDTO> result = (List<NoteDTO>) service.getAll();
        assertEquals(noteDTOList.size(), result.size());
    }

    @Test
    void findByIdTest() {
        when(repository.findById(any())).thenReturn(Optional.ofNullable(note));
        when(mapper.toNoteDto(any())).thenReturn(noteDTO);
        NoteDTO resultat =
                service.findById(id);
        assertEquals(note.getNoteObservation(),resultat.getNoteObservation());
    }

    @Test
    void findByPatientIdTest() {
        when(repository.findByPatientId(1)).thenReturn(noteList);
        when(mapper.toNoteDtoList(any())).thenReturn(noteDTOList);
        List<NoteDTO> resultat = service.findByPatientId(1);
        assertEquals(noteList.size(),resultat.size());
    }

    @Test
    public void findByPatientIdTestThrowsNotFoundExceptionWhenNotestNotFound() {
        when(repository.findByPatientId(any())).thenThrow(new NotFoundException("Aucun note trouvé pour le patient"));

        NotFoundException exception = assertThrows(NotFoundException.class, () -> {
            service.findByPatientId(10);
        });

        assertEquals("Aucun note trouvé pour le patient", exception.getMessage());
    }

    @Test
    public void testFindByIdThrowsNotFoundException() {
        when(repository.findById(any())).thenThrow(new NotFoundException("Note " +
                "introuvable"));

        NotFoundException exception = assertThrows(NotFoundException.class, () -> {
            service.findById(id);
        });

        assertEquals("Note introuvable", exception.getMessage());
    }

    @Test
    public void testFindByIdThrowsIllegalArgumentException() {

        NotFoundException exception = assertThrows(NotFoundException.class, () -> {
            service.findById(id);
        });

        assertEquals("Note  introuvable", exception.getMessage());
    }

    @Test
    void saveTest() {
        when(patientServiceClient.get(any())).thenReturn(patientDTO);
        NoteDTO resultat = service.save(1, updateNoteDTO);
        verify(repository, times(1)).save(any());
        assertNotNull(resultat);
    }

    @Test
    public void testSaveThrowsExceptionWhenSaveFails() {
        when(patientServiceClient.get(any())).thenReturn(patientDTO);
        when(repository.save(any())).thenThrow(new BadRequestException("Échec de l'enregistrement de la note"));

        BadRequestException exception = assertThrows(BadRequestException.class, () -> {
            service.save(10, updateNoteDTO);
        });

        assertEquals("Échec de l'enregistrement de la note", exception.getMessage());
    }

    @Test
    void updateTest() {
        when(repository.findById(any())).thenReturn(Optional.ofNullable(note));
        when(mapper.toNoteDto(any())).thenReturn(noteDTO);
        NoteDTO resultat = service.update(id, updateNoteDTO);
        verify(repository, times(1)).save(any());
        assertNotNull(resultat);
    }

    @Test
    public void testUpdateThrowsNotFoundExceptionWhenUpdateFails() {

        NotFoundException exception = assertThrows(NotFoundException.class, () -> {
            service.update(id, updateNoteDTO);
        });

        assertEquals("Note introuvable", exception.getMessage());
    }

    @Test
    public void testUpdateThrowsBadRequestExceptionWhenUpdateFails() {
        when(repository.findById(any())).thenReturn(Optional.ofNullable(note));
        when(repository.save(any())).thenThrow(new BadRequestException("Échec de l'enregistrement de la note"));

        BadRequestException exception = assertThrows(BadRequestException.class, () -> {
            service.update(id, updateNoteDTO);
        });

        assertEquals("Échec de la mise à jour de la note", exception.getMessage());
    }

    @Test
    void updateNoteNullTest() {
        UpdateNoteDTO update = new UpdateNoteDTO();
        when(repository.findById(any())).thenReturn(Optional.ofNullable(note));
        when(mapper.toNoteDto(any())).thenReturn(noteDTO);
        NoteDTO resultat = service.update(id, update);
        assertNotNull(resultat);
    }

    @Test
    void deleteByIdTest() {
        service.deleteById(id);
        verify(repository, times(1)).deleteById(new ObjectId(id));
    }

}
