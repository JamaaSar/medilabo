import { Component, Input } from '@angular/core';
import { Patient } from 'src/model/patient';

@Component({
  selector: 'app-note-list',
  templateUrl: './note-list.component.html',
  styleUrls: ['./note-list.component.scss'],
})
export class NoteListComponent {
  @Input()
  notes!: Patient;
}
