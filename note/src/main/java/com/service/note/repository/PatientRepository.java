package com.service.note.repository;


import com.service.note.dto.PatientDTO;
import org.springframework.stereotype.Repository;


@Repository
public interface PatientRepository {

    PatientDTO getPatientById(Integer id);
}
