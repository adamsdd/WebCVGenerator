import {Component, EventEmitter, Inject, OnInit, Output} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material/dialog';
import {Education} from '../../../../domain/cv/Education';

@Component({
  selector: 'app-education-modal',
  templateUrl: './education-modal.component.html',
  styleUrls: ['./education-modal.component.scss']
})
export class EducationModalComponent implements OnInit {

  public educationData: Education;
  public addEventEmitter = new EventEmitter<Education>();

  public schoolTypes = [{label: 'label.education.highSchool', value: 'HIGH_SCHOOL'},
                       {label: 'label.education.studies', value: 'STUDIES'}];

  constructor(public dialogRef: MatDialogRef<EducationModalComponent>, @Inject(MAT_DIALOG_DATA) data) {
    this.educationData = data;
    this.educationData.schoolType = !this.educationData.schoolType ? 'HIGH_SCHOOL' : this.educationData.schoolType;
  }

  ngOnInit() {
  }

  actionFunction() {
    this.addEventEmitter.emit(this.educationData);
    this.closeModal();
  }

  closeModal() {
    this.dialogRef.close();
  }

  compareFn(type1: string, type2: string): boolean {
    return type1 && type2 ? type1 === type2 : type1 === type2;
  }
}
