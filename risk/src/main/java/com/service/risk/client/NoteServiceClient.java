package com.service.risk.client;

import com.service.risk.dto.NoteDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * Feign client for accessing notes services.
 * This client provides methods to interact with the note service
 * for retrieving note information. The URL for the note service
 * is configured through application properties.
 */
@FeignClient(name = "note", url = "${note.url}")
public interface NoteServiceClient {

    @GetMapping("/{id}")
    NoteDTO get(@PathVariable Integer id);

    @GetMapping("/patient/{patientid}")
    List<NoteDTO> getByPatientId(@PathVariable("patientid") Integer patientId);

}
