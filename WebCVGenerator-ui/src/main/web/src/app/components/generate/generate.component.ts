import {Component, Input, OnInit} from '@angular/core';
import {FormGroup} from '@angular/forms';

@Component({
  selector: 'app-generate',
  templateUrl: './generate.component.html',
  styleUrls: ['./generate.component.scss']
})
export class GenerateComponent implements OnInit {

  @Input() cvForm: FormGroup;

  constructor() { }

  ngOnInit(): void {
  }


  public generate() {
    console.log('reset form');
    this.cvForm.reset();
  }
}
