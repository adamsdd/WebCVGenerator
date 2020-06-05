import {Component, ViewChild} from '@angular/core';
import {FormBuilder, FormControl, FormGroup} from '@angular/forms';
import {GenerateComponent} from './components/generate/generate.component';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'webCVGenerator-ui';
  cvForm: FormGroup;

  @ViewChild(GenerateComponent) generateComponent: GenerateComponent;

  constructor(
    private formBuilder: FormBuilder
  ) {this.cvForm = new FormGroup({}); }


  onSubmitted() {
    // TODO: Implement onSubmitted
    console.log('abc');
    this.cvForm.reset();
  }
}
