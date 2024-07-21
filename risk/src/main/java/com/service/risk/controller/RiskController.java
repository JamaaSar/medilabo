package com.service.risk.controller;

import com.service.risk.service.RiskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


/**
 * REST controller for managing risk assessments.
 *
 * This controller provides endpoints for calculating risk assessments related
 * to patients. The risk calculations are performed by the RiskService service.
 */
@RestController
@RequestMapping("/risk")
public class RiskController {

    @Autowired
    private RiskService service;

    /**
     * Calculates the risk of diabetes for a patient by their ID.
     *
     * @param patientId the ID of the patient for whom the risk assessment is to be calculated.
     * @return a ResponseEntity containing the risk assessment result as a {@link String}.
     */
    @GetMapping("/{patientId}")
    public ResponseEntity<String> get(@PathVariable("patientId") Integer patientId) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(service.calculateRiskDiabete(patientId));
    }

}
