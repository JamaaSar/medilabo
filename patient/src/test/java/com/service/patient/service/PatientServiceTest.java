package com.service.patient.service;

import com.service.patient.dto.PatientDTO;
import com.service.patient.entity.Patient;
import com.service.patient.exceptions.BadRequestException;
import com.service.patient.exceptions.NotFoundException;
import com.service.patient.mapper.PatientMapper;
import com.service.patient.repository.PatientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PatientServiceTest {

    @Mock
    private PatientRepository repository;
    @Mock
    private PatientMapper mapper;
    @InjectMocks
    private PatientService service;
    private PatientDTO patientDTO;
    private Patient patient;
    private List<PatientDTO> patientDTOList;
    private List<Patient> patientList;

    @BeforeEach
    public void setUp() {

        patient = new Patient();
        patientDTO = new PatientDTO();
        patient.setNom("nom");
        patient.setPrenom("prenom");
        patient.setGenre("f");
        patient.setAdressePostale("67100");
        patient.setDateDeNaissance(new Date("12/12/2000"));
        patient.setNumeroDeTelephone("03333333");

        patientDTO.setNom(patient.getNom());
        patientDTO.setPrenom(patient.getPrenom());
        patientDTO.setGenre(patient.getGenre());
        patientDTO.setAdressePostale(patient.getAdressePostale());
        patientDTO.setDateDeNaissance(patient.getDateDeNaissance());
        patientDTO.setNumeroDeTelephone(patient.getNumeroDeTelephone());
        patientDTOList = new ArrayList<>();
        patientList = new ArrayList<>();
        patientDTOList.add(patientDTO);
        patientList.add(patient);
    }

    @Test
    void getAllTest() {
        when(repository.findAll()).thenReturn(patientList);
        when(mapper.toPatientDtoList(anyList())).thenReturn(patientDTOList);
        List<PatientDTO> result = service.getAll();
        assertEquals(patientDTOList.size(), result.size());
    }

    @Test
    void findByIdTest() {
        when(repository.findById(any())).thenReturn(Optional.ofNullable(patient));
        when(mapper.toPatientDto(any())).thenReturn(patientDTO);
        PatientDTO resultat = service.findById(1);
        assertEquals(patient.getNom(),resultat.getNom());
    }

    @Test
    public void testFindByIdThrowsNotFoundExceptionWhenPatientNotFound() {

        NotFoundException exception = assertThrows(NotFoundException.class, () -> {
            service.findById(10);
        });

        assertEquals("Patient  introuvable", exception.getMessage());
    }

    @Test
    void saveTest() {
        PatientDTO resultat = service.save(patientDTO);
        verify(repository, times(1)).save(any());
        assertNotNull(resultat);
    }

    @Test
    public void testSaveThrowsExceptionWhenSaveFails() {
        when(repository.save(any())).thenThrow(new BadRequestException("Échec de l'enregistrement du patient"));

        BadRequestException exception = assertThrows(BadRequestException.class, () -> {
            service.save(patientDTO);
        });

        assertEquals("Échec de l'enregistrement du patient", exception.getMessage());
    }

    @Test
    void updateTest() {
        when(repository.findById(any())).thenReturn(Optional.ofNullable(patient));
        when(mapper.toPatientDto(any())).thenReturn(patientDTO);
        PatientDTO resultat = service.update(1, patientDTO);
        verify(repository, times(1)).save(any());
        assertNotNull(resultat);
    }

    @Test
    public void testUpdateThrowsNotFoundExceptionWhenUpdateFails() {

        NotFoundException exception = assertThrows(NotFoundException.class, () -> {
            service.update(11,patientDTO);
        });

        assertEquals("Patient  introuvable", exception.getMessage());
    }

    @Test
    public void testUpdateThrowsBadRequestExceptionWhenUpdateFails() {
        when(repository.findById(any())).thenReturn(Optional.ofNullable(patient));
        when(repository.save(any())).thenThrow(new BadRequestException("Échec de l'enregistrement du patient"));

        BadRequestException exception = assertThrows(BadRequestException.class, () -> {
            service.update(any(),patientDTO);
        });

        assertEquals("Échec de la mise à jour du patient", exception.getMessage());
    }

    @Test
    void updateNomNummTest() {
        PatientDTO patientDTO1 = new PatientDTO();
        patientDTO.setNom(null);
        when(repository.findById(any())).thenReturn(Optional.ofNullable(patient));
        when(mapper.toPatientDto(any())).thenReturn(patientDTO);
        PatientDTO resultat = service.update(1, patientDTO1);
        verify(repository, times(1)).save(any());
        assertNotNull(resultat);
    }

    @Test
    void deleteByIdTest() {
        service.deleteById(1);
        verify(repository, times(1)).deleteById(1     );
    }

}
