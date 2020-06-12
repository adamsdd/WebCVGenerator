import {Component, Input, OnInit} from '@angular/core';
import {FormGroup} from '@angular/forms';
import {CVData} from '../../../domain/cv/CVData';
import {BasicInfoService} from '../../services/cv/basic-info/basic-info.service';

@Component({
  selector: 'app-generate',
  templateUrl: './generate.component.html',
  styleUrls: ['./generate.component.scss']
})
export class GenerateComponent implements OnInit {

  @Input() cvForm: FormGroup;
  @Input() cvData: CVData;

  constructor(private basicInfoService: BasicInfoService) { }

  ngOnInit(): void {
  }


  public generate() {
    console.log('reset form');
    this.cvForm.reset();
  }

  public save() {
    this.basicInfoService.saveBasicInfo(this.cvData.basicInfo, this.cvData.id).subscribe(data => {
    });
  }
}
