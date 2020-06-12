import { Component, OnInit } from '@angular/core';
import {UserService} from '../../services/user/user.service';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {UserFormData} from '../../../domain/user/UserFormData';
import {Router} from '@angular/router';
import {MatDialog, MatDialogConfig} from '@angular/material/dialog';
import {RegistrationModalComponent} from './registration-modal/registration-modal.component';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  private userService: UserService;
  public form: FormGroup;
  private formBuilder: FormBuilder;

  constructor(userService: UserService, formBuilder: FormBuilder, private router: Router, public matDialog: MatDialog) {
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

  openAddModal() {
    const dialogConfig = new MatDialogConfig();
    dialogConfig.disableClose = true;
    dialogConfig.id = 'modal-component';
    dialogConfig.height = '300px';
    dialogConfig.width = '400px';
    const modalDialog = this.matDialog.open(RegistrationModalComponent, dialogConfig);
  }
}
