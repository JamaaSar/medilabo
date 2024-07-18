import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { Observable } from 'rxjs';
import { CookieService } from './cookie.service';
import { Patient } from 'src/model/patient';
import { Note } from 'src/model/note';

@Injectable({
  providedIn: 'root',
})
export class PatientService {
  username: string;
  headers = new HttpHeaders().set('Content-Type', 'application/json');

  constructor(private http: HttpClient, private cookieService: CookieService) {
    this.username = cookieService.getCookie('username')!;
  }
  get currentUser() {
    return this.username;
  }
  addObservationNote(patientId: number, noteObservation: string) {
    return this.http.post<Note>(
      `${environment.apiUrl}/note/patient/${patientId}`,
      { noteObservation },
      { headers: this.headers }
    );
  }
  updateObservationNote(noteId: number, noteObservation: string) {
    return this.http.put<Note>(
      `${environment.apiUrl}/note/${noteId}`,
      { noteObservation },
      { headers: this.headers }
    );
  }
  deleteObservationNote(noteId: number) {
    return this.http.delete(`${environment.apiUrl}/note/${noteId}`, {
      headers: this.headers,
    });
  }
  getAllPatients() {
    return this.http.get<Patient[]>(`${environment.apiUrl}/patient`, {
      headers: this.headers,
    });
  }
  getPatientNote(patient: Patient) {
    return this.http.get<Note[]>(
      `${environment.apiUrl}/note/patient/${patient.id}`,
      {
        headers: this.headers,
      }
    );
  }
  addPatient(patient: Patient) {
    console.log(patient);
    return this.http.post<Patient>(`${environment.apiUrl}/patient`, patient, {
      headers: this.headers,
    });
  }
  deletePatient(id: number) {
    return this.http.delete(`${environment.apiUrl}/patient/${id}`, {
      headers: this.headers,
    });
  }
  getRisk(patientId: number) {
    return this.http.get<string>(`${environment.apiUrl}/risk/${patientId}`, {
      headers: this.headers,
      responseType: 'text' as 'json',
    });
  }
}
