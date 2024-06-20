package com.service.risk.controller;

import com.service.risk.service.RiskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/risk")
public class RiskController {

    @Autowired
    private RiskService service;
    @GetMapping("/{patientId}")
    public ResponseEntity<String> get(@PathVariable("patientId") Integer id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(service.calculateRiskDiabete(id));
    }

}
