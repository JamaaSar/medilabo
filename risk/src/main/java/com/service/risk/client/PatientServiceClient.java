package com.service.risk.client;


import com.service.risk.dto.PatientDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "patient", url = "${patient.url}")
public interface PatientServiceClient {
    @GetMapping("/{id}")
    PatientDTO get(@PathVariable Integer id);

}
