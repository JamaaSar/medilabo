package com.service.note.mapper;

import com.service.note.dto.NoteDTO;
import com.service.note.entity.Note;
import lombok.Generated;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Generated
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,componentModel = "spring")
public interface NoteMapper {

    NoteDTO toNoteDto(Note patient);

    Note toDtoNote(NoteDTO noteDTO);

    List<NoteDTO> toNoteDtoList(List<Note> patientList);


}
