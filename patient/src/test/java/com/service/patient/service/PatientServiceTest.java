package com.service.patient.service;

import com.service.patient.dto.PatientDTO;
import com.service.patient.entity.Patient;
import com.service.patient.mapper.PatientMapper;
import com.service.patient.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.stereotype.Service;

import java.util.Date;

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
    }
    void getAllTest() {

    }
    void findByIdTest() {

    }
    void saveTest() {

    }
    void updateTest() {

    }
    void deleteByIdTest() {

    }

}
