package com.service.note.controller;


import com.service.note.dto.NoteDTO;
import com.service.note.service.NoteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/user/{username}")
    public ResponseEntity<NoteDTO> getByUserName(@PathVariable("username") String username) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(service.findByIUserName(username));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") String id) {
        service.deleteById(id);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable("id") String id,
                       @RequestBody @Valid String noteObservation ) {
        service.update(id,noteObservation);
    }

    @PostMapping("/")
    public ResponseEntity<NoteDTO> save(@RequestBody @Valid NoteDTO noteDTO ) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(service.save(noteDTO));
    }
}
