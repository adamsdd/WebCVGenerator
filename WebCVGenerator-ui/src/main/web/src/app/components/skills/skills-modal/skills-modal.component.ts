import {Component, EventEmitter, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material/dialog';
import {Skill} from '../../../../domain/cv/Skill';

@Component({
  selector: 'app-skills-modal',
  templateUrl: './skills-modal.component.html',
  styleUrls: ['./skills-modal.component.scss']
})
export class SkillsModalComponent implements OnInit {

  public skillData: Skill;
  public addEventEmitter = new EventEmitter<Skill>();

  public skillsLevel = [{label: 'label.skill.level.BEGINNER', value: 'BEGINNER'},
    {label: 'label.skill.level.AVERAGE', value: 'AVERAGE'},
    {label: 'label.skill.level.SKILLED', value: 'SKILLED'},
    {label: 'label.skill.level.SPECIALIST', value: 'SPECIALIST'},
    {label: 'label.skill.level.EXPERT', value: 'EXPERT'}];

  constructor(public dialogRef: MatDialogRef<SkillsModalComponent>, @Inject(MAT_DIALOG_DATA) data) {
    this.skillData = data;
    this.skillData.level = !this.skillData.level ? 'BEGINNER' : this.skillData.level;
  }

  ngOnInit(): void {
  }

  actionFunction() {
    this.addEventEmitter.emit(this.skillData);
    this.closeModal();
  }

  closeModal() {
    this.dialogRef.close();
  }
}
