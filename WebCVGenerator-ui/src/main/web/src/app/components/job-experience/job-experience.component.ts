import {Component, Input, OnInit} from '@angular/core';
import {JobExperience} from '../../../domain/cv/JobExperience';
import {MatDialog, MatDialogConfig} from '@angular/material/dialog';
import {JobExperienceService} from '../../services/cv/job-experience/job-experience.service';
import {BehaviorSubject} from 'rxjs';
import {JobExperienceModalComponent} from './job-experience-modal/job-experience-modal.component';

@Component({
  selector: 'app-job-experience',
  templateUrl: './job-experience.component.html',
  styleUrls: ['./job-experience.component.scss']
})
export class JobExperienceComponent implements OnInit {

  @Input() jobExperiences: Array<JobExperience>;
  private cvDataIdBehavior = new BehaviorSubject<number>(null);

  constructor(public matDialog: MatDialog, private jobExperienceService: JobExperienceService) { }

  ngOnInit(): void {
  }

  @Input() set setCvDataId(cvDataId: number) {
    this.cvDataIdBehavior.next(cvDataId);
  }

  get getCvDataId() {
    return this.cvDataIdBehavior.getValue();
  }

  openAddModal(jobExperience: JobExperience) {
    const dialogConfig = new MatDialogConfig();
    dialogConfig.disableClose = true;
    dialogConfig.id = 'modal-component';
    dialogConfig.height = '600px';
    dialogConfig.width = '400px';
    dialogConfig.data = jobExperience || new JobExperience(this.getCvDataId);
    const modalDialog = this.matDialog.open(JobExperienceModalComponent, dialogConfig);
    modalDialog.componentInstance.addEventEmitter.subscribe(newJobExperience => {
      this.addNewJobExperience(newJobExperience);
    });
  }

  private addNewJobExperience(jobExperience: JobExperience) {
    this.jobExperienceService.saveJobExperience(jobExperience).subscribe(savedJobExperience => {
      if (savedJobExperience) {
        this.updateJobExperienceInList(savedJobExperience);
      }
    }, error => new Error(error));
  }

  private updateJobExperienceInList(newJobExperience) {
    const jobExperienceToUpdate = this.jobExperiences.find(this.findIndexToUpdate, newJobExperience.id);
    if (jobExperienceToUpdate) {
      const index = this.jobExperiences.indexOf(jobExperienceToUpdate);
      this.jobExperiences[index] = newJobExperience;
      this.jobExperiences = [...this.jobExperiences];
    } else {
      this.jobExperiences.push(newJobExperience);
      this.jobExperiences = [...this.jobExperiences];
    }
  }

  private removeJobExperienceFromList(jobExperienceId: number) {
    if (jobExperienceId) {
      const jobExperienceToDelete = this.jobExperiences.find(this.findIndexToUpdate, jobExperienceId);
      if (jobExperienceToDelete) {
        const index = this.jobExperiences.indexOf(jobExperienceToDelete);
        if (index !== -1) {
          this.jobExperiences.splice(index, 1);
          this.jobExperiences = [...this.jobExperiences];
        }
      }
    }
  }

  findIndexToUpdate(newItem) {
    return newItem.id === this;
  }

  remove(jobExperience: JobExperience) {
    this.jobExperienceService.removeJobExperience(jobExperience).subscribe(data => {
      if (data) {
        this.removeJobExperienceFromList(jobExperience.id);
      }
    });
  }
}
