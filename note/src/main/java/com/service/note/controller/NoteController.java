package com.service.note.controller;


import com.service.note.dto.NoteDTO;
import com.service.note.dto.UpdateNoteDTO;
import com.service.note.service.NoteService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/note")
public class NoteController {
    @Autowired
    private NoteService service;

    @GetMapping("/{id}")
    public ResponseEntity<NoteDTO> get(@PathVariable("id") String id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(service.findById(id));
    }

    @GetMapping("/patient/{patientid}")
    public ResponseEntity<List<NoteDTO>> getByPatientId(@PathVariable("patientid") Integer patientId) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(service.findByPatientId(patientId));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") String id) {
        service.deleteById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<NoteDTO> update(@PathVariable("id") String id,
                       @RequestBody UpdateNoteDTO noteObservation ) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(service.update(id,noteObservation));
    }

    @PostMapping("/patient/{patientid}")
    public ResponseEntity<NoteDTO> save(@PathVariable("patientid") Integer id,
                                        @RequestBody UpdateNoteDTO noteDTO ) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(service.save(id,noteDTO));
    }
}
