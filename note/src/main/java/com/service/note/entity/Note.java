package com.service.note.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(value = "note")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Note {
    @Id
    private ObjectId id;
    private Integer patientId;
    private String userName;
    private String noteObservation;

    public Note(int patientId, String userName, String noteObservation) {
        this.patientId = patientId;
        this.userName = userName;
        this.noteObservation = noteObservation;
    }
}
