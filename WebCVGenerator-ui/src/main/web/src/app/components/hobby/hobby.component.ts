import {Component, Input, OnInit} from '@angular/core';
import {FormControl, FormGroup} from '@angular/forms';

@Component({
  selector: 'app-hobby',
  templateUrl: './hobby.component.html',
  styleUrls: ['./hobby.component.scss']
})
export class HobbyComponent implements OnInit {

  @Input() cvForm: FormGroup;
  constructor() { }

  ngOnInit(): void {
    this.cvForm.addControl('hobby' , new FormControl());
  }

}
