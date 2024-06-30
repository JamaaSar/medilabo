package com.service.patient.service;

import com.service.patient.dto.PatientDTO;
import com.service.patient.entity.Patient;
import com.service.patient.mapper.PatientMapper;
import com.service.patient.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PatientService {
    private final PatientRepository repository;
    private final PatientMapper mapper;

    public List<PatientDTO> getAll() {
        return mapper.toPatientDtoList(repository.findAll());
    }
    public PatientDTO findById(Integer id) {
        Patient patient = repository.findById(id)
                .orElseThrow();
        return mapper.toPatientDto(patient);
    }
    public PatientDTO save(PatientDTO patientDTO) {
        Patient patient = mapper.toDtoPatient(patientDTO);
        repository.save(patient);
        return patientDTO;
    }
    public PatientDTO update(Integer id, PatientDTO patientDTO) {
        Patient patient = repository.findById(id)
                .orElseThrow();
        if (patientDTO.getPrenom() != null) {
            patient.setPrenom(patientDTO.getPrenom());
        }
        if (patientDTO.getNom() != null) {
            patient.setNom(patientDTO.getNom());
        }
        if (patientDTO.getDateDeNaissance() != null) {
            patient.setDateDeNaissance(patientDTO.getDateDeNaissance());
        }
        if (patientDTO.getGenre() != null) {
            patient.setGenre(patientDTO.getGenre());
        }
        if (patientDTO.getAdressePostale() != null) {
            patient.setAdressePostale(patientDTO.getAdressePostale());
        }
        if (patientDTO.getNumeroDeTelephone() != null) {
            patient.setNumeroDeTelephone(patientDTO.getNumeroDeTelephone());
        }
        repository.save(patient);
        return mapper.toPatientDto(patient);
    }
    public void deleteById(Integer id) {
        repository.deleteById(id);
    }

}
