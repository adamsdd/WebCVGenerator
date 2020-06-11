import { Component, OnInit } from '@angular/core';
import {UserService} from '../../services/user/user.service';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {UserFormData} from '../../../domain/user/UserFormData';
import {ActivatedRoute, Router} from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  private userService: UserService;
  public form: FormGroup;
  private formBuilder: FormBuilder;

  constructor(userService: UserService, formBuilder: FormBuilder, private router: Router) {
    this.userService = userService;
    this.formBuilder = formBuilder;
  }

  ngOnInit(): void {
    this.form = this.formBuilder.group({
      username: [null, [Validators.required]],
      password: [null, Validators.required],
    });
  }

  login(): void {
    const userData: UserFormData = new UserFormData();
    userData.username = this.form.controls.username.value;
    userData.password = this.form.controls.password.value;

    this.userService.login(userData).subscribe(data => {
      if (data != null) {
        this.userService.setLoggedUser(data);
        this.form.reset();
        this.router.navigate(['/home']);
      }
    }, error => {
      console.log(error);
    });
  }
}
