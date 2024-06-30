package com.service.risk.client;

import com.service.risk.dto.NoteDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "note", url = "${note.url}")
public interface NoteServiceClient {

    @GetMapping("/{id}")
    NoteDTO get(@PathVariable Integer id);

    @GetMapping("/user/{patientid}")
    List<NoteDTO> getByPatientId(@PathVariable("patientid") Integer patientId);

}
