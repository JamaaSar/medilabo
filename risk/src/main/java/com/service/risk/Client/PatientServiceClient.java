package com.service.risk.Client;

import com.service.note.dto.NoteDTO;
import com.service.patient.dto.PatientDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "patient", url = "http://localhost:8081/patient")
public interface PatientServiceClient {
    @GetMapping("/{id}")
    PatientDTO get(@PathVariable Integer id);

}
