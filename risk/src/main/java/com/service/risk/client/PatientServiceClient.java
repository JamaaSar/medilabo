package com.service.risk.client;

import com.service.patient.dto.PatientDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "patient", url = "http://localhost:8081/patient")
public interface PatientServiceClient {
    @GetMapping("/{id}")
    PatientDTO get(@PathVariable Integer id);

}
