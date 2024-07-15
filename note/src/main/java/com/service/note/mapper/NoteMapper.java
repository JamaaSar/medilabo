package com.service.note.mapper;

import com.service.note.dto.NoteDTO;
import com.service.note.entity.Note;
import lombok.Generated;
import org.bson.types.ObjectId;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Generated
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,componentModel = "spring")
public interface NoteMapper {

    @Mapping(target = "id", source = "id", qualifiedByName = "toString")
    NoteDTO toNoteDto(Note patient);
    @Mapping(target="id", ignore=true)
    Note toDtoNote(NoteDTO noteDTO);

    @Mapping(target = "id", source = "id", qualifiedByName = "toString")
    List<NoteDTO> toNoteDtoList(List<Note> noteList);

    @Named("toString")
    default String toString(ObjectId id){
        return id.toString();
    }
    @Named("toObject")
    default ObjectId toObject(String id){
        return new ObjectId(id);
    }

}
