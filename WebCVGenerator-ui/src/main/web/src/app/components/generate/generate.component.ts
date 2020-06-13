import {Component, Input, OnInit} from '@angular/core';
import {FormGroup} from '@angular/forms';
import {CVData} from '../../../domain/cv/CVData';
import {BasicInfoService} from '../../services/cv/basic-info/basic-info.service';
import {CvDataService} from '../../services/cv/cv-data.service';
import { saveAs } from 'file-saver/dist/FileSaver';

@Component({
  selector: 'app-generate',
  templateUrl: './generate.component.html',
  styleUrls: ['./generate.component.scss']
})
export class GenerateComponent implements OnInit {

  @Input() cvForm: FormGroup;
  @Input() cvData: CVData;

  constructor(private basicInfoService: BasicInfoService, private cvDataService: CvDataService, ) { }

  ngOnInit(): void {
  }

  public save() {
    this.basicInfoService.saveBasicInfo(this.cvData.basicInfo, this.cvData.id).subscribe(data => {
    });
  }

  public generate() {
    this.cvDataService.generate(this.cvData.id).subscribe(data => {
      saveAs(data, 'CV.docx');
    });
  }
}
