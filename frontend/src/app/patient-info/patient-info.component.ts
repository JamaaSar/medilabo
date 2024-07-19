import { Component, Input, OnInit, ViewChild } from '@angular/core';
import { Patient } from 'src/model/patient';
import { PatientService } from '../service/patient.service';
import { Note } from 'src/model/note';
import { ModalComponent } from '../modal/modal.component';

@Component({
  selector: 'app-patient-info',
  templateUrl: './patient-info.component.html',
  styleUrls: ['./patient-info.component.scss'],
})
export class PatientInfoComponent {
  @ViewChild('addNoteModal') addNoteModal: ModalComponent | undefined;
  @Input()
  patient!: Patient;
  @Input()
  notes!: Note[];
  showModal = false;
  addModal = true;
  noteToUpdate!: Note | null;
  @Input()
  risk!: string;

  constructor(private patientService: PatientService) {}
  onNoteAdded() {
    this.getPatientNote();
    this.getRisk();
  }
  async getPatientNote() {
    this.patientService.getPatientNote(this.patient).subscribe({
      next: (data) => {
        this.notes = data;
      },
      error: (err) => {},
    });
  }
  async getRisk() {
    this.patientService.getRisk(this.patient.id).subscribe(
      (data: string) => (this.risk = data),
      (error) => console.error('There was an error!', error)
    );
  }
  openModal(note?: Note) {
    if (note != null) {
      this.addModal = false;
      this.noteToUpdate = note;
    } else {
      this.addModal = true;
      this.noteToUpdate = null;
    }
    this.showModal = true;
  }

  closeModal() {
    this.showModal = false;
  }
  deleteNote(noteId: number) {
    this.patientService.deleteObservationNote(noteId).subscribe(
      (response) => {
        this.notes = this.notes.filter((note) => note.id !== noteId);
        this.getRisk();
      },
      (error) => {
        console.error('Error deleting item', error);
      }
    );
  }
  getRiskColor(risk: string) {
    if (risk === 'Borderline') {
      return 'borderline';
    } else if (risk === 'InDanger') {
      return 'indanger';
    } else if (risk === 'EarlyOnset') {
      return 'earlyonset';
    } else {
      return 'none';
    }
  }
}
