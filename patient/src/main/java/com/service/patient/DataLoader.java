package com.service.patient;

import com.service.patient.dto.PatientDTO;
import com.service.patient.entity.Patient;
import com.service.patient.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

@Component
public class DataLoader implements CommandLineRunner {
    @Autowired
    PatientRepository patientRepository;

    @Override
    public void run(String... args) throws Exception{
        if(patientRepository.count() == 0){
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Patient patient1 = new Patient();
            patient1.setPrenom("TestNone");
            patient1.setNom("Test");
            patient1.setDateDeNaissance(sdf.parse("1966-12-31"));
            patient1.setGenre("F");
            patient1.setAdressePostale("1 Brookside St");
            patient1.setNumeroDeTelephone("100-222-3333");

            Patient patient2 = new Patient();
            patient2.setPrenom("TestBorderline");
            patient2.setNom("Test");
            patient2.setDateDeNaissance(sdf.parse("1945-06-24"));
            patient2.setGenre("M");
            patient2.setAdressePostale("2 High St");
            patient2.setNumeroDeTelephone("200-333-4444");

            Patient patient3 = new Patient();
            patient3.setPrenom("TestInDanger");
            patient3.setNom("Test");
            patient3.setDateDeNaissance(sdf.parse("2004-06-18"));
            patient3.setGenre("M");
            patient3.setAdressePostale("3 Club Road");
            patient3.setNumeroDeTelephone("300-444-5555");

            Patient patient4 = new Patient();
            patient4.setPrenom("TestEarlyOnset");
            patient4.setNom("Test");
            patient4.setDateDeNaissance(sdf.parse("2002-06-28"));
            patient4.setGenre("F");
            patient4.setAdressePostale("4 Valley Dr");
            patient4.setNumeroDeTelephone("400-555-6666");

            patientRepository.saveAll(Arrays.asList(patient1, patient2, patient3, patient4));

        }
    }
}
