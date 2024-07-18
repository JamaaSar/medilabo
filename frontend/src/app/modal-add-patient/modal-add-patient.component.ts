import { Component, EventEmitter, Output } from '@angular/core';
import {
  FormBuilder,
  FormControl,
  FormGroup,
  Validators,
} from '@angular/forms';
import { PatientService } from '../service/patient.service';
import { Patient } from 'src/model/patient';
import * as moment from 'moment'; // Import moment.js

@Component({
  selector: 'app-modal-add-patient',
  templateUrl: './modal-add-patient.component.html',
  styleUrls: ['./modal-add-patient.component.scss'],
})
export class ModalAddPatientComponent {
  @Output() patientAdded = new EventEmitter<void>();
  @Output() close = new EventEmitter<void>();
  patientForm: FormGroup;

  constructor(private fb: FormBuilder, private patientService: PatientService) {
    this.patientForm = this.fb.group({
      nom: ['', Validators.required],
      prenom: ['', Validators.required],
      dateDeNaissance: ['', Validators.required],
      genre: ['', Validators.required],
      adressePostale: [null], // Allow null value
      numeroDeTelephone: [null],
    });
  }
  ngOnInit() {}
  addNewPatient() {
    console.log(this.patientForm);
    if (this.patientForm.valid) {
      const formValues = this.patientForm.value;
      const patient: Patient = {
        ...formValues,
        dateDeNaissance: moment(
          formValues.dateDeNaissance,
          'MM/DD/YYYY'
        ).format('YYYY-MM-DD'),
      };
      this.patientService.addPatient(patient).subscribe({
        next: (data) => {
          this.patientAdded.emit();
          this.close.emit();
        },
        error: (error) => {
          console.error('There was an error!', error);
        },
      });
    }
  }
  closeModal() {
    this.close.emit();
  }

  getFormControl(controlName: string) {
    return this.patientForm.get(controlName);
  }
}
