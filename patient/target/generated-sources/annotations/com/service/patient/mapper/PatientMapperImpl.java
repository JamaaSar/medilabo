package com.service.patient.mapper;

import com.service.patient.dto.PatientDTO;
import com.service.patient.entity.Patient;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-06-14T20:10:10+0200",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 21.0.2 (Oracle Corporation)"
)
@Component
public class PatientMapperImpl implements PatientMapper {

    @Override
    public PatientDTO toPatientDto(Patient patient) {
        if ( patient == null ) {
            return null;
        }

        PatientDTO.PatientDTOBuilder patientDTO = PatientDTO.builder();

        patientDTO.id( patient.getId() );
        patientDTO.prenom( patient.getPrenom() );
        patientDTO.nom( patient.getNom() );
        patientDTO.dateDeNaissance( patient.getDateDeNaissance() );
        patientDTO.genre( patient.getGenre() );
        patientDTO.adressePostale( patient.getAdressePostale() );
        patientDTO.numeroDeTelephone( patient.getNumeroDeTelephone() );

        return patientDTO.build();
    }

    @Override
    public Patient toDtoPatient(PatientDTO patientDTO) {
        if ( patientDTO == null ) {
            return null;
        }

        Patient patient = new Patient();

        patient.setId( patientDTO.getId() );
        patient.setPrenom( patientDTO.getPrenom() );
        patient.setNom( patientDTO.getNom() );
        patient.setDateDeNaissance( patientDTO.getDateDeNaissance() );
        patient.setGenre( patientDTO.getGenre() );
        patient.setAdressePostale( patientDTO.getAdressePostale() );
        patient.setNumeroDeTelephone( patientDTO.getNumeroDeTelephone() );

        return patient;
    }

    @Override
    public List<PatientDTO> toPatientDtoList(List<Patient> patientList) {
        if ( patientList == null ) {
            return null;
        }

        List<PatientDTO> list = new ArrayList<PatientDTO>( patientList.size() );
        for ( Patient patient : patientList ) {
            list.add( toPatientDto( patient ) );
        }

        return list;
    }
}
