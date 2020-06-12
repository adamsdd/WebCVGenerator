import {Component, Input, IterableDiffers, OnInit} from '@angular/core';
import {Education} from '../../../domain/cv/Education';
import {MatDialog, MatDialogConfig} from '@angular/material/dialog';
import {EducationModalComponent} from './education-modal/education-modal.component';
import {BehaviorSubject} from 'rxjs';
import {EducationService} from '../../services/cv/education/education.service';

@Component({
  selector: 'app-education',
  templateUrl: './education.component.html',
  styleUrls: ['./education.component.scss']
})
export class EducationComponent implements OnInit {

  @Input() education: Array<Education>;
  private cvDataIdBehavior = new BehaviorSubject<number>(null);

  constructor(public matDialog: MatDialog, private educationService: EducationService) {

  }

  ngOnInit(): void {
  }

  @Input() set setCvDataId(cvDataId: number) {
    this.cvDataIdBehavior.next(cvDataId);
  }

  get getCvDataId() {
    return this.cvDataIdBehavior.getValue();
  }

  public getSchoolByType(schoolType) {
    return this.education?.filter(education => education.schoolType === schoolType);
  }

  openAddModal(editedEducation: Education) {
    const dialogConfig = new MatDialogConfig();
    dialogConfig.disableClose = true;
    dialogConfig.id = 'modal-component';
    dialogConfig.height = '600px';
    dialogConfig.width = '400px';
    dialogConfig.data = editedEducation || new Education(this.getCvDataId);
    const modalDialog = this.matDialog.open(EducationModalComponent, dialogConfig);
    modalDialog.componentInstance.addEventEmitter.subscribe(newEducation => {
      this.addNewEducation(newEducation);
    });
  }

  private addNewEducation(education: Education) {
    this.educationService.saveEducation(education).subscribe(savedEducation => {
      if (savedEducation) {
        this.updateEducationInList(savedEducation);
      }
    }, error => new Error(error));
  }

  private updateEducationInList(newEducation) {
    const educationToUpdate = this.education.find(this.findIndexToUpdate, newEducation.id);
    if (educationToUpdate) {
      const index = this.education.indexOf(educationToUpdate);
      this.education[index] = newEducation;
      this.education = [...this.education];
    } else {
      this.education.push(newEducation);
      this.education = [...this.education];
    }
  }

  private removeEducationFromList(educationId: number) {
    if (educationId) {
      const educationToDelete = this.education.find(this.findIndexToUpdate, educationId);
      if (educationToDelete) {
        const index = this.education.indexOf(educationToDelete);
        if (index !== -1) {
          this.education.splice(index, 1);
          this.education = [...this.education];
        }
      }
    }
  }

  findIndexToUpdate(newItem) {
    return newItem.id === this;
  }

  remove(education: Education) {
    this.educationService.removeEducation(education).subscribe(data => {
      if (data) {
        this.removeEducationFromList(education.id);
      }
    });
  }
}
