package com.service.note.controller;


import com.service.note.dto.NoteDTO;
import com.service.note.dto.UpdateNoteDTO;
import com.service.note.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for managing notes.
 */
@RestController
@RequestMapping("/note")
public class NoteController {
    @Autowired
    private NoteService service;

    /**
     * Retrieves a note by its ID.
     *
     * @param id the ID of the note to retrieve.
     * @return a ResponseEntity containing the {@link NoteDTO} instance.
     */
    @GetMapping("/{id}")
    public ResponseEntity<NoteDTO> get(@PathVariable("id") String id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(service.findById(id));
    }

    /**
     * Retrieves notes by a patient's ID.
     *
     * @param patientId the ID of the patient whose notes are to be retrieved.
     * @return a ResponseEntity containing a list of {@link NoteDTO} instances.
     */
    @GetMapping("/patient/{patientid}")
    public ResponseEntity<List<NoteDTO>> getByPatientId(@PathVariable("patientid") Integer patientId) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(service.findByPatientId(patientId));
    }

    /**
     * Deletes a note by its ID.
     *
     * @param id the ID of the note to delete.
     */
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") String id) {
        service.deleteById(id);
    }

    /**
     * Updates a note by its ID.
     *
     * @param id the ID of the note to update.
     * @param noteObservation the new note details.
     * @return a ResponseEntity containing the updated {@link NoteDTO} instance.
     */
    @PutMapping("/{id}")
    public ResponseEntity<NoteDTO> update(@PathVariable("id") String id,
                       @RequestBody UpdateNoteDTO noteObservation ) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(service.update(id,noteObservation));
    }

    /**
     * Add a new note for a patient.
     *
     * @param id the ID of the patient to whom the note belongs.
     * @param noteDTO the details of the note to save.
     * @return a ResponseEntity containing the saved {@link NoteDTO} instance.
     */
    @PostMapping("/patient/{patientid}")
    public ResponseEntity<NoteDTO> add(@PathVariable("patientid") Integer id,
                                        @RequestBody UpdateNoteDTO noteDTO ) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(service.save(id,noteDTO));
    }
}
