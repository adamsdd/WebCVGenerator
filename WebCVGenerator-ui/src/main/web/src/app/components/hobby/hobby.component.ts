import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {FormBuilder, FormControl, FormGroup} from '@angular/forms';
import {BasicInfo} from '../../../domain/cv/BasicInfo';

@Component({
  selector: 'app-hobby',
  templateUrl: './hobby.component.html',
  styleUrls: ['./hobby.component.scss']
})
export class HobbyComponent implements OnInit {

  @Input() cvForm: FormGroup;
  @Input() basicInfo: BasicInfo;
  @Output() basicInfoChange: EventEmitter<BasicInfo> = new EventEmitter();
  constructor() { }

  ngOnInit(): void {
    this.cvForm.addControl('hobby' , new FormControl());
  }

  onHobbyChange(newHobby) {
    if (this.basicInfo) {
      this.basicInfo.hobby = newHobby;
      this.basicInfoChange.emit(this.basicInfo);
    }
  }
}
