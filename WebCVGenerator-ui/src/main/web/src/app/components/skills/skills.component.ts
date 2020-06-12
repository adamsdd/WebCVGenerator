import {Component, Input, OnInit} from '@angular/core';
import {BehaviorSubject} from 'rxjs';
import {Skill} from '../../../domain/cv/Skill';
import {MatDialog, MatDialogConfig} from '@angular/material/dialog';
import {SkillsService} from '../../services/cv/skills/skills.service';
import {SkillsModalComponent} from './skills-modal/skills-modal.component';

@Component({
  selector: 'app-skills',
  templateUrl: './skills.component.html',
  styleUrls: ['./skills.component.scss']
})
export class SkillsComponent implements OnInit {

  @Input() skills: Array<Skill>;
  private cvDataIdBehavior = new BehaviorSubject<number>(null);

  constructor(public matDialog: MatDialog, private skillsService: SkillsService) { }

  ngOnInit(): void {
  }

  @Input() set setCvDataId(cvDataId: number) {
    this.cvDataIdBehavior.next(cvDataId);
  }

  get getCvDataId() {
    return this.cvDataIdBehavior.getValue();
  }

  openAddModal(skill: Skill) {
    const dialogConfig = new MatDialogConfig();
    dialogConfig.disableClose = true;
    dialogConfig.id = 'modal-component';
    dialogConfig.height = '600px';
    dialogConfig.width = '400px';
    dialogConfig.data = skill || new Skill(this.getCvDataId);
    const modalDialog = this.matDialog.open(SkillsModalComponent, dialogConfig);
    modalDialog.componentInstance.addEventEmitter.subscribe(newSkill => {
      this.addNewSkill(newSkill);
    });
  }

  private addNewSkill(skill: Skill) {
    this.skillsService.saveSkill(skill).subscribe(savedSkill => {
      if (savedSkill) {
        this.updateSkillInList(savedSkill);
      }
    }, error => new Error(error));
  }

  private updateSkillInList(newSkill) {
    const skillToUpdate = this.skills.find(this.findIndexToUpdate, newSkill.id);
    if (skillToUpdate) {
      const index = this.skills.indexOf(skillToUpdate);
      this.skills[index] = newSkill;
      this.skills = [...this.skills];
    } else {
      this.skills.push(newSkill);
      this.skills = [...this.skills];
    }
  }

  private removeSkillFromList(skillId: number) {
    if (skillId) {
      const skillToDelete = this.skills.find(this.findIndexToUpdate, skillId);
      if (skillToDelete) {
        const index = this.skills.indexOf(skillToDelete);
        if (index !== -1) {
          this.skills.splice(index, 1);
          this.skills = [...this.skills];
        }
      }
    }
  }

  findIndexToUpdate(newItem) {
    return newItem.id === this;
  }

  remove(skill: Skill) {
    this.skillsService.removeSkill(skill).subscribe(data => {
      if (data) {
        this.removeSkillFromList(skill.id);
      }
    });
  }
}
