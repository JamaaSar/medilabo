import { Component, OnInit, ViewChild } from '@angular/core';
import { User } from 'src/model/user';
import { PatientService } from '../service/patient.service';
import { Patient } from 'src/model/patient';
import { CookieService } from '../service/cookie.service';
import { Note } from 'src/model/note';
import { ModalAddPatientComponent } from '../modal-add-patient/modal-add-patient.component';

@Component({
  selector: 'app-homepage',
  templateUrl: './homepage.component.html',
  styleUrls: ['./homepage.component.scss'],
})
export class HomepageComponent implements OnInit {
  @ViewChild('addPatientModal') addPatientModal:
    | ModalAddPatientComponent
    | undefined;
  showModal = false;
  addModal = true;
  isLoggedIn = false;
  allUser: User[] = [];
  patientList: any = new Array<Patient>();
  currentUser!: User;
  username: string;
  isValidFormSubmitted = false;
  failed = false;
  notFound = false;
  errorMessage = '';
  searchResult: User[] = [];
  selectedPateint!: Patient;
  notesObservation!: Note[];
  risk!: string;

  toggle: Boolean = false;

  constructor(
    private patientService: PatientService,
    private cookieService: CookieService
  ) {
    this.username = this.cookieService.getCookie('username')!;
  }

  ngOnInit() {
    this.getPatientList();
  }

  async getPatientList() {
    this.patientService.getAllPatients().subscribe({
      next: (data) => {
        this.patientList = data;
      },
      error: (err) => {},
    });
  }

  onClick(patient: Patient, inputElement?: any) {
    this.selectedPateint = patient;
    this.getPatientNote();
    this.getRisk();
    this.searchResult = [];
    this.notFound = false;
    if (inputElement) {
      inputElement.value = '';
    }
      
  }
  async getPatientNote() {
    this.patientService.getPatientNote(this.selectedPateint).subscribe({
      next: (data) => {
        this.notesObservation = data;
      },
      error: (err) => {},
    });
  }
  async getRisk() {
    this.patientService.getRisk(this.selectedPateint.id).subscribe(
      (data: string) => (this.risk = data),
      (error) => console.error('There was an error!', error)
    );
  }
  onInput(e: any) {
    if (!e.target.value) {
      this.searchResult = [];
    } else {
      this.searchResult = this.patientList.filter(
        (patient: Patient) =>
          patient.nom.toLowerCase().includes(e.target.value.toLowerCase()) ||
          patient.prenom.toLowerCase().includes(e.target.value.toLowerCase())
      );
      if (this.searchResult.length <= 0) {
        this.searchResult = [];
        this.notFound = true;
        this.errorMessage = 'not found';
      }
    }
    this.toggle = false;
  }
  showPatientAndClear(patient: Patient) {
    this.toggle = true;
    this.searchResult = [];
  }
  openModal(patient?: Patient | null) {
    if (patient != null) {
      this.addModal = false;
    } else {
      this.addModal = true;
    }
    this.showModal = true;
  }

  closeModal() {
    this.showModal = false;
  }
  deletePatient(id: number) {
    this.patientService.deletePatient(id).subscribe(
      (response) => {
        this.patientList = this.patientList.filter((x: Patient) => x.id !== id);
        this.getPatientList();
      },
      (error) => {
        console.error('Error deleting item', error);
      }
    );
  }
  onPatientAdded() {
    this.getPatientList();
  }
}
