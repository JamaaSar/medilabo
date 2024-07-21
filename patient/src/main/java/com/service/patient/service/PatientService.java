package com.service.patient.service;

import com.service.patient.dto.PatientDTO;
import com.service.patient.entity.Patient;
import com.service.patient.exceptions.BadRequestException;
import com.service.patient.exceptions.NotFoundException;
import com.service.patient.mapper.PatientMapper;
import com.service.patient.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class for managing patients.
 * This service provides methods for CRUD operations on Patient entities.
 * It uses PatientRepository to interact with the database.
 * And PatientMapper convert between entity and DTO objects.
 *
 */
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
                .orElseThrow(() -> new NotFoundException("Patient  introuvable"));
        return mapper.toPatientDto(patient);
    }
    public PatientDTO save(PatientDTO patientDTO) {
        Patient patient = mapper.toDtoPatient(patientDTO);
        try {
            repository.save(patient);
        } catch (BadRequestException e) {
            throw new BadRequestException("Échec de l'enregistrement du patient");
        }
        return patientDTO;
    }
    public PatientDTO update(Integer id, PatientDTO patientDTO) {
        Patient patient = repository.findById(id)
               .orElseThrow(() -> new NotFoundException("Patient  introuvable"));
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
        try {
            repository.save(patient);
        } catch (BadRequestException e) {
            throw new BadRequestException("Échec de la mise à jour du patient");
        }
        return mapper.toPatientDto(patient);
    }
    public void deleteById(Integer id) {
        repository.deleteById(id);
    }

}
