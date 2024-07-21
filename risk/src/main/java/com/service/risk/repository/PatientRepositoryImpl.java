package com.service.risk.repository;

import com.service.risk.client.PatientServiceClient;
import com.service.risk.dto.PatientDTO;
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