package com.service.patient.controller;


import com.service.patient.dto.PatientDTO;
import com.service.patient.service.PatientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for managing patients.
 */
@RestController
@RequestMapping("/patient")
public class PatientController {

    @Autowired
    private PatientService service;

    /**
     * Retrieves all patients.
     *
     * @return a ResponseEntity containing a list of all patients.
     */
    @GetMapping
    public ResponseEntity<List<PatientDTO>> getAll() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(service.getAll());
    }

    /**
     * Retrieves a patient by their ID.
     *
     * @param id the ID of the patient to retrieve.
     * @return a ResponseEntity containing the patient details.
     */
    @GetMapping("/{id}")
    public ResponseEntity<PatientDTO> get(@PathVariable("id") Integer id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(service.findById(id));
    }

    /**
     * Deletes a patient by their ID.
     *
     * @param id the ID of the patient to delete.
     */
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Integer id) {
        service.deleteById(id);
    }

    /**
     * Updates a patient by their ID.
     *
     * @param id the ID of the patient to update.
     * @param patientDTO the new patient details.
     * @return a ResponseEntity containing the updated patient details.
     */
    @PutMapping("/{id}")
    public ResponseEntity<PatientDTO> update(@PathVariable("id") Integer id,
                       @RequestBody @Valid PatientDTO patientDTO ) {
        return ResponseEntity.status(HttpStatus.OK).
                body(service.update(id,patientDTO));
    }

    /**
     * Adds a new patient.
     *
     * @param patientDTO the details of the patient to add.
     * @return a ResponseEntity containing the added patient details.
     */
    @PostMapping
    public ResponseEntity<PatientDTO> add(@RequestBody @Valid PatientDTO patientDTO ) {
        return ResponseEntity.status(HttpStatus.OK).
                body(service.save(patientDTO));
    }


}
