package com.service.risk.repository;

import com.service.risk.dto.NoteDTO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoteRepository {

    List<NoteDTO> getByPatientId(Integer id);
}
