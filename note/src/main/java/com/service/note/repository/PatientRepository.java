package com.service.note.repository;


import com.service.note.dto.PatientDTO;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Repository
public interface PatientRepository {

    PatientDTO getPatientById(Integer id);
}
