package com.service.patient.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Date;

@Data
@Entity
@Table(name = "patient")
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull(message = "Prénom est obligatoire")
    private String prenom;
    @NotNull(message = "Nom est obligatoire")
    private String nom;
    @NotNull(message = "Date de naissance est obligatoire")
    private Date dateDeNaissance ;
    @NotNull(message = "Genre est obligatoire")
    private String genre ;
    private String adressePostale ;
    private String numeroDeTelephone;

}
