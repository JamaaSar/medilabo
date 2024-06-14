package com.service.patient.mapper;

import com.service.patient.dto.PatientDTO;
import com.service.patient.entity.Patient;
import lombok.Generated;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Generated
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,componentModel = "spring")
public interface PatientMapper {

    PatientDTO toPatientDto(Patient patient);
    Patient toDtoPatient(PatientDTO patientDTO);
    List<PatientDTO> toPatientDtoList(List<Patient> patientList);


}
