package com.service.note.client;

import com.service.note.dto.PatientDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;


/**
 * Feign client for accessing patient services.
 * This client provides methods to interact with the patient service
 * for retrieving patient information. The URL for the patient service
 * is configured through application properties.
 */
@FeignClient(name = "patient", url = "${patient.url}")
public interface PatientServiceClient {
    @GetMapping("/{id}")
    PatientDTO get(@PathVariable Integer id);
}
