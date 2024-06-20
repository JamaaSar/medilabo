package com.service.risk.Client;
import com.service.note.dto.NoteDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "note", url = "http://localhost:8082/note")
public interface NoteServiceClient {

    @GetMapping("/{id}")
    NoteDTO get(@PathVariable Integer id);

    @GetMapping("/user/{patientid}")
    List<NoteDTO> getByPatientId(@PathVariable("patientid") Integer patientId);

}
