package com.service.note.client;

import com.service.note.dto.PatientDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "patient", url = "${patient.url}")
public interface PatientServiceClient {
    @GetMapping("/{id}")
    PatientDTO get(@PathVariable Integer id);
}
