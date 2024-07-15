package com.service.note.service;

import com.service.note.client.PatientServiceClient;
import com.service.note.dto.NoteDTO;
import com.service.note.dto.PatientDTO;
import com.service.note.dto.UpdateNoteDTO;
import com.service.note.entity.Note;
import com.service.note.exceptions.BadRequestException;
import com.service.note.exceptions.NotFoundException;
import com.service.note.mapper.NoteMapper;
import com.service.note.repository.NoteRepository;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class NoteService {
    private final NoteRepository repository;
    private final NoteMapper mapper;

    private final PatientServiceClient patientServiceClient;
    public Iterable<NoteDTO> getAll() {
        return mapper.toNoteDtoList(repository.findAll());
    }
    public NoteDTO findById(String id) {
        Note note = repository.findById(new ObjectId(id))
                .orElseThrow(() -> new NotFoundException("Note  introuvable"));
        NoteDTO noteDTO = mapper.toNoteDto(note);
        return noteDTO;
    }
    public List<NoteDTO> findByPatientId(Integer patientId) {
        List<Note> notes = null;
        try {
            notes = repository.findByPatientId(patientId);
        } catch (NotFoundException e) {
            throw new NotFoundException("Aucun note trouvé pour le patient");
        }
        return mapper.toNoteDtoList(notes);
    }
    public NoteDTO save(Integer id, UpdateNoteDTO updateNoteDTO) {
        PatientDTO patient = patientServiceClient.get(id);
        NoteDTO noteDTO = new NoteDTO();
        noteDTO.setPatientId(patient.getId());
        noteDTO.setPatientName(patient.getPrenom());
        noteDTO.setNoteObservation(updateNoteDTO.getNoteObservation());
        Note note = mapper.toDtoNote(noteDTO);
        try {
            repository.save(note);
        } catch (BadRequestException e) {
            throw new BadRequestException("Échec de l'enregistrement de la note");
        }
        return noteDTO;
    }
    public NoteDTO update(String id, UpdateNoteDTO noteObservation) {

        Note note = repository.findById(new ObjectId(id))
                .orElseThrow(() -> new NotFoundException("Note introuvable"));
        patientServiceClient.get(note.getPatientId());
        if (noteObservation.getNoteObservation() != null) {
            note.setNoteObservation(noteObservation.getNoteObservation());
            try {
                repository.save(note);
            } catch (BadRequestException e) {
                throw new BadRequestException("Échec de la mise à jour de la note");
            }
        }
        return mapper.toNoteDto(note);
    }
    public void deleteById(String id) {
        repository.deleteById(new ObjectId(id));
    }

}
