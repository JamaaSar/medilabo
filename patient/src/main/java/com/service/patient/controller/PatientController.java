package com.service.patient.controller;


import com.service.patient.dto.PatientDTO;
import com.service.patient.entity.Patient;
import com.service.patient.service.PatientService;
import jakarta.validation.Valid;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patient")
public class PatientController {

    @Autowired
    private PatientService service;

    @GetMapping
    public ResponseEntity<List<PatientDTO>> getAll() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(service.getAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<PatientDTO> get(@PathVariable("id") Integer id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(service.findById(id));
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Integer id) {
        service.deleteById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PatientDTO> update(@PathVariable("id") Integer id,
                       @RequestBody @Valid PatientDTO patientDTO ) {
        return ResponseEntity.status(HttpStatus.OK).
                body(service.update(id,patientDTO));
    }
    @PostMapping
    public ResponseEntity<PatientDTO> ajouter(@RequestBody @Valid PatientDTO patientDTO ) {
        System.out.println(patientDTO);
        return ResponseEntity.status(HttpStatus.OK).
                body(service.save(patientDTO));
    }


}
