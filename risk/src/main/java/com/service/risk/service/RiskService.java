package com.service.risk.service;

import com.service.risk.client.NoteServiceClient;
import com.service.risk.client.PatientServiceClient;
import com.service.risk.dto.NoteDTO;
import com.service.risk.dto.PatientDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.*;

@RequiredArgsConstructor
@Service
public class RiskService {

    private final NoteServiceClient noteServiceClient;
    private final PatientServiceClient patientServiceClient;
    private  final List<String> listOfTriggers =  List.of(
            "Hémoglobine A1C", "Microalbumine", "Taille", "Poids",
            "Fumeur", "Fumeuse", "Anormal", "Cholestérol",
            "Vertiges", "Rechute", "Réaction", "Anticorps"
    );
    public String calculateRiskDiabete(Integer patientId){


        PatientDTO patient = patientServiceClient.get(patientId);
        List<NoteDTO> noteList = noteServiceClient.getByPatientId(patient.getId());
        if(noteList.isEmpty()){
            return "None";
        }

        int counter = countTriggers(noteList);
        boolean ageIsOver30 = isOver30(patient.getDateDeNaissance());
        boolean isMale = patient.getGenre().equals("M") ? true : false;

        if ( counter >=2 && counter <= 5 && ageIsOver30)
        {
            return "Borderline";
        }
        else if ( isMale && !ageIsOver30 && counter >= 5
                || !isMale && !ageIsOver30 && counter >= 7
                || ageIsOver30 && counter >= 8 )
        {
            return "EarlyOnset";
        }
        else if ( isMale && !ageIsOver30 && counter == 3
                || !isMale && !ageIsOver30 && counter == 4
                || ageIsOver30 && counter == 6 && counter == 7 )
        {
            return "InDanger";
        }
        else
        {
            return "None";
        }
    }
    private int countTriggers(List<NoteDTO> noteList){
        int counter = 0;
        for (NoteDTO note : noteList) {
            String observation = note.getNoteObservation().toLowerCase();
            for (String trigger : listOfTriggers) {
                if (observation.contains(trigger.toLowerCase())) {
                    counter += 1;
                }
            }
        }
        return counter;
    }
    private boolean isOver30(Date birthDate) {
        LocalDate today = LocalDate.now();
        LocalDate date = birthDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return Period.between(date, today).getYears() < 30 ? false : true;
    }
}
