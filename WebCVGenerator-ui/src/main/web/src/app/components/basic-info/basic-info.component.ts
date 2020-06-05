import {Component, Input, OnInit} from '@angular/core';
import {FormBuilder, FormControl, FormGroup} from '@angular/forms';

@Component({
  selector: 'app-basic-info',
  templateUrl: './basic-info.component.html',
  styleUrls: ['./basic-info.component.scss']
})
export class BasicInfoComponent implements OnInit {


  @Input() cvForm: FormGroup;
  constructor(private formBuilder: FormBuilder) { }

  ngOnInit() {
    this.cvForm.addControl('name' , new FormControl());
    this.cvForm.addControl('surname' , new FormControl());
    this.cvForm.addControl('birthDate' , new FormControl());
    this.cvForm.addControl('city' , new FormControl());
  }
}
