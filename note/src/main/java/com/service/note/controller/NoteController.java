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
    public ResponseEntity<NoteDTO> get(@PathVariable("id") ObjectId id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(service.findById(id));
    }

    @GetMapping("/user/{patientid}")
    public ResponseEntity<List<NoteDTO>> getByPatientId(@PathVariable("patientid") Integer patientId) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(service.findByPatientId(patientId));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") ObjectId id) {
        service.deleteById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<NoteDTO> update(@PathVariable("id") ObjectId id,
                       @RequestBody UpdateNoteDTO noteObservation ) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(service.update(id,noteObservation));
    }

    @PostMapping
    public ResponseEntity<NoteDTO> save(@RequestBody NoteDTO noteDTO ) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(service.save(noteDTO));
    }
}
