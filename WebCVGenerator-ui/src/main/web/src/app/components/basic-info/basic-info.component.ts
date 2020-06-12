import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {FormBuilder, FormControl, FormGroup} from '@angular/forms';
import {BasicInfo} from '../../../domain/cv/BasicInfo';
import {CvDataService} from '../../services/cv/cv-data.service';

@Component({
  selector: 'app-basic-info',
  templateUrl: './basic-info.component.html',
  styleUrls: ['./basic-info.component.scss']
})
export class BasicInfoComponent implements OnInit {


  @Input() photo;
  @Input() cvForm: FormGroup;
  @Input() basicInfo: BasicInfo;
  @Output() uploadPhotoEvent = new EventEmitter<string>();
  @Output() deletePhotoEvent = new EventEmitter<string>();
  @Output() basicInfoChange: EventEmitter<BasicInfo> = new EventEmitter();

  constructor(private formBuilder: FormBuilder, private cvDataService: CvDataService) { }

  onNameChange(newName) {
    if (this.basicInfo) {
      this.basicInfo.name = newName;
      this.basicInfoChange.emit(this.basicInfo);
    }
  }

  ngOnInit() {
    this.cvForm.addControl('name' , new FormControl());
    this.cvForm.addControl('surname' , new FormControl());
    this.cvForm.addControl('birthDate' , new FormControl());
    this.cvForm.addControl('city' , new FormControl());
    this.cvForm.addControl('phone' , new FormControl());
  }

  onSurnameChange(newSurname) {
    if (this.basicInfo) {
      this.basicInfo.surname = newSurname;
      this.basicInfoChange.emit(this.basicInfo);
    }
  }

  onBirthDateChange(newBirthDate) {
    if (this.basicInfo) {
      this.basicInfo.birthDate = newBirthDate;
      this.basicInfoChange.emit(this.basicInfo);
    }
  }

  onCityChange(newCity) {
    if (this.basicInfo) {
      this.basicInfo.city = newCity;
      this.basicInfoChange.emit(this.basicInfo);
    }
  }

  onPhoneChange(newPhone) {
    if (this.basicInfo) {
      this.basicInfo.phone = newPhone;
      this.basicInfoChange.emit(this.basicInfo);
    }
  }

  public callSelectFile($event) {
    this.uploadPhotoEvent.next($event);
  }

  public callDeletePhoto() {
    this.deletePhotoEvent.emit();
  }
}
