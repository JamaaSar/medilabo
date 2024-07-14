package com.service.note.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import jakarta.persistence.Id;
import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "note")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Note {
    @Id
    private ObjectId id;
    private Integer patientId;
    private String patientName;
    private String noteObservation;

}
