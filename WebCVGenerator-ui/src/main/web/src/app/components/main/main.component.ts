import {Component, OnInit, ViewChild} from '@angular/core';
import {FormBuilder, FormGroup} from '@angular/forms';
import {GenerateComponent} from '../generate/generate.component';
import {CvDataService} from '../../services/cv/cv-data.service';
import {UserService} from '../../services/user/user.service';
import {User} from '../../../domain/user/User';
import {CVData} from '../../../domain/cv/CVData';
import {Observable} from 'rxjs';
import {DomSanitizer} from '@angular/platform-browser';
import {HttpEventType, HttpResponse} from '@angular/common/http';

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.scss']
})
export class MainComponent implements OnInit {

  constructor(
    private formBuilder: FormBuilder,
    private cvDataService: CvDataService,
    private userService: UserService,
    private sanitizer: DomSanitizer
  ) {this.cvForm = new FormGroup({});
     this.userService.loggedInUser.subscribe(currentUser => {
       this.currentUser = currentUser;
     });
  }

  title = 'webCVGenerator-ui';
  cvForm: FormGroup;
  currentUser: User;
  public cvData: CVData;

  selectedFiles: FileList;
  currentFileUpload: File;
  progress: { percentage: number } = { percentage: 0 };

  @ViewChild(GenerateComponent) generateComponent: GenerateComponent;

  public photo: any;

  ngOnInit() {
    const loggedUser = this.userService.currentUserValue;
    this.getCVData(loggedUser.id).subscribe(cvData => {
      this.cvData = cvData;

      this.cvDataService.getImage(cvData.id).subscribe(photoData => {
        if (photoData && photoData.size > 0) {
          this.createImageFromBlob(photoData);
        }
      });
    });
  }

  createImageFromBlob(image: Blob) {
    const reader = new FileReader();
    reader.addEventListener('load', () => {
      this.photo = reader.result;
    }, false);
    if (image) {
      reader.readAsDataURL(image);
    }
  }

  private getCVData(userId: number): Observable<CVData> {
    return this.cvDataService.getCvData(userId);
  }

  public selectFile(event) {
    const file = event.target.files.item(0);
    if (file.type.match('image.*')) {
      this.selectedFiles = event.target.files;
      if (this.selectedFiles) {
        this.uploadPhoto();
      }
    } else {
      alert('invalid format!');
    }
  }

  uploadPhoto() {
    this.progress.percentage = 0;

    this.currentFileUpload = this.selectedFiles.item(0);
    this.cvDataService.uploadPhoto(this.currentFileUpload, this.cvData.id).subscribe(event => {
      if (event.type === HttpEventType.UploadProgress) {
        this.progress.percentage = Math.round(100 * event.loaded / event.total);
      } else if (event instanceof HttpResponse) {
        this.cvDataService.getImage(this.cvData.id).subscribe(photoData => {
          if (photoData && photoData.size > 0) {
            this.createImageFromBlob(photoData);
          }
        });
      }
    });

    this.selectedFiles = undefined;
  }

  deletePhoto() {
    this.cvDataService.deletePhoto(this.cvData.id).subscribe(data => {
      if (data) {
        this.photo = null;
      }
    });
  }

  onSubmitted() {
    // TODO: Implement onSubmitted
    console.log('abc');
    this.cvForm.reset();
  }
}
