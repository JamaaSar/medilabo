package com.service.risk.repository;

import com.service.risk.dto.PatientDTO;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository {

    PatientDTO getPatientById(Integer id);
}
