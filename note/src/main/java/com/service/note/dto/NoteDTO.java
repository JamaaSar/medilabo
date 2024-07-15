package com.service.note.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Generated
public class NoteDTO {
    private String id;
    private Integer patientId;
    private String patientName;
    private String noteObservation;
}
