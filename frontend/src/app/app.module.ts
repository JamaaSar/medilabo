import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { SigninComponent } from './signin/signin.component';
import { HeaderComponent } from './header/header.component';
import { HomepageComponent } from './homepage/homepage.component';
import { AuthInterceptor } from './guard/auth. interceptor';
import { NoopAnimationsModule } from '@angular/platform-browser/animations';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { MatIconModule } from '@angular/material/icon';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { TableModule } from 'primeng/table';
import { PaginatorModule } from 'primeng/paginator';
import { ButtonModule } from 'primeng/button';
import { PatientInfoComponent } from './patient-info/patient-info.component';
import { NoteListComponent } from './note-list/note-list.component';
import { CookieService } from 'ngx-cookie-service';
import { ModalComponent } from './modal/modal.component';
import { ModalAddPatientComponent } from './modal-add-patient/modal-add-patient.component';

@NgModule({
  declarations: [
    AppComponent,
    SigninComponent,
    HeaderComponent,
    HomepageComponent,
    PatientInfoComponent,
    NoteListComponent,
    ModalComponent,
    ModalAddPatientComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    NoopAnimationsModule,
    MatCheckboxModule,
    MatIconModule,
    TableModule,
    PaginatorModule,
    ButtonModule,
    ReactiveFormsModule,
  ],
  providers: [
    AuthInterceptor,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: AuthInterceptor,
      multi: true,
    },
    CookieService,

    // provider used to create fake backend
  ],
  bootstrap: [AppComponent],
})
export class AppModule {}
