package com.service.note.repository;

import com.service.note.client.PatientServiceClient;
import com.service.note.dto.PatientDTO;
import feign.FeignException;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;


@Repository
@AllArgsConstructor
public class PatientRepositoryImpl implements PatientRepository {

    private final PatientServiceClient patientProvider;

    @Override
    public PatientDTO getPatientById(Integer id) {
        try {
            PatientDTO patient = patientProvider.get(id);
            return patient;
        } catch (FeignException.FeignClientException e) {
            if (e.status() == 404)
                return null ;
            else throw e;
        }
    }

}