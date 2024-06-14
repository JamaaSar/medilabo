package com.service.note.repository;

import com.service.note.entity.Note;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface NoteRepository extends MongoRepository<Note, String> {
    Note findByUserName(String username);

}
