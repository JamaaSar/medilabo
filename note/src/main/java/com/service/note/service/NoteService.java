package com.service.note.service;

import com.service.note.dto.NoteDTO;
import com.service.note.entity.Note;
import com.service.note.mapper.NoteMapper;
import com.service.note.repository.NoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class NoteService {
    private final NoteRepository repository;
    private final NoteMapper mapper;

    public Iterable<NoteDTO> getAll() {
        return mapper.toNoteDtoList(repository.findAll());
    }
    public NoteDTO findById(String id) {
        Note note = repository.findById(id)
                .orElseThrow();
        NoteDTO noteDTO = mapper.toNoteDto(note);
        return noteDTO;
    }
    public NoteDTO findByIUserName(String userName) {
        Note note = repository.findByUserName(userName);
        return mapper.toNoteDto(note);
    }
    public NoteDTO save(NoteDTO noteDTO) {
        Note note = mapper.toDtoNote(noteDTO);
        repository.save(note);
        return noteDTO;
    }
    public void update(String id, String noteObservation) {
        Note note = repository.findById(id)
                .orElseThrow();

        if (noteObservation != null) {
            note.setNoteObservation(noteObservation);
        }
        repository.save(note);
    }
    public void deleteById(String id) {
        repository.deleteById(id);
    }

}
