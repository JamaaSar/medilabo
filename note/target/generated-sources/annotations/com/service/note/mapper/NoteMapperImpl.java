package com.service.note.mapper;

import com.service.note.dto.NoteDTO;
import com.service.note.entity.Note;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-06-14T22:37:45+0200",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 21.0.2 (Oracle Corporation)"
)
@Component
public class NoteMapperImpl implements NoteMapper {

    @Override
    public NoteDTO toNoteDto(Note patient) {
        if ( patient == null ) {
            return null;
        }

        NoteDTO.NoteDTOBuilder noteDTO = NoteDTO.builder();

        noteDTO.id( patient.getId() );
        noteDTO.userName( patient.getUserName() );
        noteDTO.noteObservation( patient.getNoteObservation() );

        return noteDTO.build();
    }

    @Override
    public Note toDtoNote(NoteDTO noteDTO) {
        if ( noteDTO == null ) {
            return null;
        }

        Note note = new Note();

        note.setId( noteDTO.getId() );
        note.setUserName( noteDTO.getUserName() );
        note.setNoteObservation( noteDTO.getNoteObservation() );

        return note;
    }

    @Override
    public List<NoteDTO> toNoteDtoList(List<Note> patientList) {
        if ( patientList == null ) {
            return null;
        }

        List<NoteDTO> list = new ArrayList<NoteDTO>( patientList.size() );
        for ( Note note : patientList ) {
            list.add( toNoteDto( note ) );
        }

        return list;
    }
}
