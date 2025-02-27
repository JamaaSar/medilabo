package com.service.risk.service;

import com.service.risk.client.NoteServiceClient;
import com.service.risk.client.PatientServiceClient;
import com.service.risk.dto.NoteDTO;
import com.service.risk.dto.PatientDTO;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RiskServiceTest {

    @Mock
    private PatientServiceClient patientServiceClient;
    @Mock
    private NoteServiceClient noteServiceClient;

    @InjectMocks
    private RiskService service;
    private NoteDTO noteDTO;
    private NoteDTO note;
    private List<NoteDTO> noteDTOList;
    private List<NoteDTO> noteList1 = new ArrayList<>();
    private List<NoteDTO> noteList2 = new ArrayList<>();
    private List<NoteDTO> noteList3 = new ArrayList<>();
    private List<NoteDTO> noteList4 = new ArrayList<>();
    private List<NoteDTO> noteList5 = new ArrayList<>();
    private List<NoteDTO> noteList6 = new ArrayList<>();
    private List<NoteDTO> noteList7 = new ArrayList<>();
    private List<NoteDTO> noteList8 = new ArrayList<>();

    private PatientDTO patient1 = new PatientDTO();
    private PatientDTO patient2 = new PatientDTO();
    private PatientDTO patient3 = new PatientDTO();
    private PatientDTO patient4 = new PatientDTO();
    private PatientDTO patient5 = new PatientDTO();
    private PatientDTO patient6 = new PatientDTO();
    private PatientDTO patient7 = new PatientDTO();
    private PatientDTO patient8 = new PatientDTO();


    private static final String id = "60c72b2f9b1d8b3b4c8e69d1";

    @BeforeEach
    public void setUp() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        patient1.setPrenom("TestNone");
        patient1.setNom("Test");
        patient1.setDateDeNaissance(sdf.parse("1966-12-31"));
        patient1.setGenre("F");
        patient1.setAdressePostale("1 Brookside St");
        patient1.setNumeroDeTelephone("100-222-3333");

        patient2.setPrenom("TestBorderline");
        patient2.setNom("Test");
        patient2.setDateDeNaissance(sdf.parse("1945-06-24"));
        patient2.setGenre("M");
        patient2.setAdressePostale("2 High St");
        patient2.setNumeroDeTelephone("200-333-4444");

        patient3.setPrenom("TestInDanger");
        patient3.setNom("Test");
        patient3.setDateDeNaissance(sdf.parse("2004-06-18"));
        patient3.setGenre("M");
        patient3.setAdressePostale("3 Club Road");
        patient3.setNumeroDeTelephone("300-444-5555");

        patient4.setPrenom("TestEarlyOnset");
        patient4.setNom("Test");
        patient4.setDateDeNaissance(sdf.parse("2002-06-28"));
        patient4.setGenre("F");
        patient4.setAdressePostale("4 Valley Dr");
        patient4.setNumeroDeTelephone("400-555-6666");

        patient5.setPrenom("TestEarlyOnset2");
        patient5.setNom("Test");
        patient5.setDateDeNaissance(sdf.parse("2002-06-28"));
        patient5.setGenre("M");
        patient5.setAdressePostale("4 Valley Dr");
        patient5.setNumeroDeTelephone("400-555-6666");

        patient6.setPrenom("TestEarlyOnset3");
        patient6.setNom("Test");
        patient6.setDateDeNaissance(sdf.parse("1992-06-28"));
        patient6.setGenre("M");
        patient6.setAdressePostale("4 Valley Dr");
        patient6.setNumeroDeTelephone("400-555-6666");

        patient7.setPrenom("InDanger1");
        patient7.setNom("Test");
        patient7.setDateDeNaissance(sdf.parse("2012-06-28"));
        patient7.setGenre("M");
        patient7.setAdressePostale("4 Valley Dr");
        patient7.setNumeroDeTelephone("400-555-6666");

        patient8.setPrenom("InDanger1");
        patient8.setNom("Test");
        patient8.setDateDeNaissance(sdf.parse("1992-06-28"));
        patient8.setGenre("F");
        patient8.setAdressePostale("4 Valley Dr");
        patient8.setNumeroDeTelephone("400-555-6666");

        NoteDTO noteDTO1 = new NoteDTO();
        noteDTO1.setPatientId(1);
        noteDTO1.setPatientName("TestNone");
        noteDTO1.setNoteObservation("Le patient déclare qu'il 'se sent très bien' Poids égal ou inférieur au poids recommandé");
        noteList1.add(noteDTO1);
        NoteDTO noteDTO2 =  new NoteDTO();
        noteDTO2.setPatientId(2);
        noteDTO2.setPatientName("TestBorderline");
        noteDTO2.setNoteObservation("Le patient déclare qu'il ressent beaucoup de " +
                "stress au travail Il se plaint également que son audition est anormale dernièrement");
        NoteDTO noteDTO3 = new NoteDTO();
        noteDTO3.setPatientId(2);
        noteDTO3.setPatientName("TestBorderline");
        noteDTO3.setNoteObservation("Le patient " +
                "déclare avoir fait une réaction aux médicaments au cours des 3 " +
                "derniers mois Il remarque également que son audition continue d'être anormale");
        noteList2.add(noteDTO3);
        noteList2.add(noteDTO2);
        NoteDTO noteDT04 = new NoteDTO();
        noteDT04.setPatientId(3);
        noteDT04.setPatientName("TestInDanger");
        noteDT04.setNoteObservation("Le patient déclare" +
                " qu'il fume depuis peu");
        NoteDTO noteDTO5 = new NoteDTO();
        noteDTO5.setPatientId(3);
        noteDTO5.setPatientName("TestInDanger");
        noteDTO5.setNoteObservation("Le patient déclare qu'il est fumeur et qu'il a " +
                "cessé de fumer l'année dernière Il se plaint également de crises d’apnée respiratoire anormales Tests de laboratoire indiquant un taux de cholestérol LDL élevé");
        noteList3.add(noteDT04);
        noteList3.add(noteDTO5);
        NoteDTO noteDTO6 = new NoteDTO();
        noteDTO6.setPatientId(4);
        noteDTO6.setPatientName("TestEarlyOnset");
        noteDTO6.setNoteObservation("Le patient déclare qu'il lui est devenu difficile de monter les escaliers Il se " +
                "plaint également d’être essoufflé Tests de laboratoire indiquant que les anticorps sont élevés Réaction aux médicaments");
        NoteDTO noteDTO7 = new NoteDTO();
        noteDTO7.setPatientId(4);
        noteDTO7.setPatientName("TestEarlyOnset");
        noteDTO7.setNoteObservation("Le patient déclare qu'il a mal au dos lorsqu'il reste assis pendant longtemps");
        NoteDTO noteDTO8 = new NoteDTO();
        noteDTO8.setPatientId(4);
        noteDTO8.setPatientName("TestEarlyOnset");
        noteDTO8.setNoteObservation("Le patient déclare avoir commencé à fumer depuis peu Hémoglobine A1C supérieure au niveau recommandé");
        NoteDTO noteDTO9 = new NoteDTO();
        noteDTO9.setPatientId(4);
        noteDTO9.setPatientName("TestEarlyOnset");
        noteDTO9.setNoteObservation("Taille, Poids, Cholestérol, Vertige et Réaction");
        noteList4.add(noteDTO6);
        noteList4.add(noteDTO7);
        noteList4.add(noteDTO8);
        noteList4.add(noteDTO9);
        noteList4.add(noteDTO9);

        NoteDTO noteDTO10 = new NoteDTO();
        noteDTO10.setPatientId(5);
        noteDTO10.setPatientName("TestEarlyOnset2");
        noteDTO10.setNoteObservation("Taille, Poids, Cholestérol, Vertiges et Réaction ");
        noteList5.add(noteDTO10);

        NoteDTO noteDTO11 = new NoteDTO();
        noteDTO11.setPatientId(6);
        noteDTO11.setPatientName("TestEarlyOnset3");
        noteDTO11.setNoteObservation("Taille, Poids, Cholestérol, Vertiges, Fumeur, " +
                "Microalbumine, Rechute, Cholestérol et Réaction ");
        noteList6.add(noteDTO11);

        NoteDTO noteDTO12 = new NoteDTO();
        noteDTO12.setPatientId(7);
        noteDTO12.setPatientName("InDanger1");
        noteDTO12.setNoteObservation("Taille, Poids et Réaction ");
        noteList7.add(noteDTO12);

        NoteDTO noteDTO13 = new NoteDTO();
        noteDTO13.setPatientId(8);
        noteDTO13.setPatientName("InDanger2");
        noteDTO13.setNoteObservation("Taille, Poids, Vertiges, Cholestérol, Fumeur et " +
                "Réaction ");
        noteList8.add(noteDTO13);

    }

    @ParameterizedTest
    @CsvSource({
            "1, None",
            "2, Borderline",
            "3, InDanger",
            "4, EarlyOnset",
            "5, EarlyOnset",
            "6, EarlyOnset",
            "7, InDanger",
            "8, InDanger",
    })
    void calculateRiskid1Test(int patientId,String expected) {
        List<NoteDTO> noteList;
        PatientDTO patientDTO;
        if(patientId == 1){
            patientDTO = patient1;
            noteList = noteList1;
        }else if(patientId == 2){
            patientDTO = patient2;
            noteList = noteList2;
        }else if(patientId == 3){
            patientDTO = patient3;
            noteList = noteList3;
        }else if(patientId == 4){
            patientDTO = patient4;
            noteList = noteList4;
        }else if(patientId == 5){
            patientDTO = patient5;
            noteList = noteList5;
        }else if(patientId == 6){
            patientDTO = patient6;
            noteList = noteList6;
        }else if(patientId == 7){
            patientDTO = patient7;
            noteList = noteList7;
        }else if(patientId == 8){
            patientDTO = patient8;
            noteList = noteList8;
        }else{
            patientDTO = patient4;
            noteList = new ArrayList<>();
        }

        when(patientServiceClient.get(any())).thenReturn(patientDTO);

        when(noteServiceClient.getByPatientId(any())).thenReturn(noteList);

        String result = service.calculateRiskDiabete(1);
        assertEquals(expected, result);
    }

}
