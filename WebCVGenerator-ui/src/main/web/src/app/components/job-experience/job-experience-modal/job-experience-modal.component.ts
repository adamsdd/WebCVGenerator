import {Component, EventEmitter, Inject, Input, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogConfig, MatDialogRef} from '@angular/material/dialog';
import {JobExperience} from '../../../../domain/cv/JobExperience';

@Component({
  selector: 'app-job-experience-modal',
  templateUrl: './job-experience-modal.component.html',
  styleUrls: ['./job-experience-modal.component.scss']
})
export class JobExperienceModalComponent implements OnInit {

  public jobExperienceData: JobExperience;
  public addEventEmitter = new EventEmitter<JobExperience>();

  constructor(public dialogRef: MatDialogRef<JobExperienceModalComponent>, @Inject(MAT_DIALOG_DATA) data) {
    this.jobExperienceData = data;
  }

  ngOnInit(): void {
  }

  actionFunction() {
    this.addEventEmitter.emit(this.jobExperienceData);
    this.closeModal();
  }

  closeModal() {
    this.dialogRef.close();
  }
}
