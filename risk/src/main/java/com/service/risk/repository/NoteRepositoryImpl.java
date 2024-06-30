package com.service.risk.repository;

import com.service.risk.client.NoteServiceClient;
import com.service.risk.dto.NoteDTO;
import feign.FeignException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
@AllArgsConstructor
public class NoteRepositoryImpl implements NoteRepository {

    private final NoteServiceClient noteServiceClient;

    @Override
    public List<NoteDTO> getByPatientId(Integer id) {
        try {
            List<NoteDTO> list = noteServiceClient.getByPatientId(id);
            return list;
        } catch (FeignException.FeignClientException e) {
            if (e.status() == 404)
                return null ;
            else throw e;
        }
    }

}