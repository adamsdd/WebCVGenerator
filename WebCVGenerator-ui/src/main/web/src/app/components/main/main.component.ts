import {Component, OnInit, ViewChild} from '@angular/core';
import {FormBuilder, FormGroup} from '@angular/forms';
import {GenerateComponent} from '../generate/generate.component';
import {CvDataService} from '../../services/cv/cv-data.service';
import {UserService} from "../../services/user/user.service";
import {User} from "../../../domain/user/User";

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.scss']
})
export class MainComponent implements OnInit {

  title = 'webCVGenerator-ui';
  cvForm: FormGroup;
  currentUser: User;

  @ViewChild(GenerateComponent) generateComponent: GenerateComponent;

  constructor(
    private formBuilder: FormBuilder,
    private cvDataService: CvDataService,
    private userService: UserService
  ) {this.cvForm = new FormGroup({});
     this.userService.loggedInUser.subscribe(x => this.currentUser = x);
  }

  ngOnInit() {
    this.cvDataService.getTestData().subscribe(data => {
      console.log(data);
    });
  }

  onSubmitted() {
    // TODO: Implement onSubmitted
    console.log('abc');
    this.cvForm.reset();
  }
}
