import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ModalAddPatientComponent } from './modal-add-patient.component';

describe('ModalAddPatientComponent', () => {
  let component: ModalAddPatientComponent;
  let fixture: ComponentFixture<ModalAddPatientComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ModalAddPatientComponent]
    });
    fixture = TestBed.createComponent(ModalAddPatientComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
