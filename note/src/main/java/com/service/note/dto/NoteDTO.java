package com.service.note.dto;

import lombok.*;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Generated
public class NoteDTO {
    private String id;
    private String userName;
    private String noteObservation;
}
