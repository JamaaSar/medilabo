package com.service.note.service;

import com.service.note.client.PatientServiceClient;
import com.service.note.dto.NoteDTO;
import com.service.note.dto.UpdateNoteDTO;
import com.service.note.entity.Note;
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
    public NoteDTO findById(ObjectId id) {
        Note note = repository.findById(id)
                .orElseThrow();
        NoteDTO noteDTO = mapper.toNoteDto(note);
        return noteDTO;
    }
    public List<NoteDTO> findByPatientId(Integer patientId) {

        List<Note> notes = repository.findByPatientId(patientId);
        return mapper.toNoteDtoList(notes);
    }
    public NoteDTO save(NoteDTO noteDTO) {
        try{
            patientServiceClient.get(noteDTO.getPatientId());
        }catch (Exception e){
            System.out.println("nt found");
        }
        Note note = mapper.toDtoNote(noteDTO);
        repository.save(note);
        return noteDTO;


    }
    public NoteDTO update(ObjectId id, UpdateNoteDTO noteObservation) {

        Note note = repository.findById(id)
                .orElseThrow();
        patientServiceClient.get(note.getPatientId());
        if (noteObservation.getNoteObservation() != null) {
            note.setNoteObservation(noteObservation.getNoteObservation());
        }
        repository.save(note);
        return mapper.toNoteDto(note);
    }
    public void deleteById(ObjectId id) {
        repository.deleteById(id);
    }

}
