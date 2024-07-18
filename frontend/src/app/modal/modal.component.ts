import { Component, EventEmitter, Input, Output } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { PatientService } from '../service/patient.service';
import { Note } from 'src/model/note';

@Component({
  selector: 'app-modal',
  templateUrl: './modal.component.html',
  styleUrls: ['./modal.component.scss'],
})
export class ModalComponent {
  @Output() noteAdded = new EventEmitter<void>();
  @Output() close = new EventEmitter<void>();
  @Input()
  patientId!: number;
  @Input()
  addModal!: boolean;
  @Input()
  noteToUpdate!: Note;
  noteObservationForm: any = new FormGroup({
    noteObservation: new FormControl('', [Validators.required]),
  });

  constructor(private patientService: PatientService) {}
  ngOnInit() {
    this.noteObservationForm.setValue({
      noteObservation: this.noteToUpdate
        ? this.noteToUpdate.noteObservation
        : '',
    });
  }
  closeModal() {
    this.close.emit();
  }
  addNewNote() {
    this.patientService
      .addObservationNote(
        this.patientId,
        this.noteObservationForm.get('noteObservation').value
      )
      .subscribe({
        next: (data) => {
          this.noteAdded.emit();
          this.close.emit();
        },
        error: (error) => {
          console.error('There was an error!', error);
        },
      });
  }
  editNote() {
    this.patientService
      .updateObservationNote(
        this.noteToUpdate.id,
        this.noteObservationForm.get('noteObservation').value
      )
      .subscribe({
        next: (data) => {
          this.ngOnInit();
          this.noteAdded.emit();
          this.close.emit();
        },
        error: (error) => {
          console.error('There was an error!', error);
        },
      });
  }
}
