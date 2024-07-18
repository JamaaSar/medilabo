package com.service.patient.dto;

import jakarta.annotation.Nullable;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Generated
public class PatientDTO {
    private Integer id;
    private String prenom;
    private String nom;
    private Date dateDeNaissance ;
    private String genre ;
    private String adressePostale ;
    private String numeroDeTelephone;

}
