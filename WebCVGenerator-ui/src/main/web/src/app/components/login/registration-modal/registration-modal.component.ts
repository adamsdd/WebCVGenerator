import { Component, OnInit } from '@angular/core';
import {NewUser} from '../../../../domain/user/NewUser';
import {MatDialogRef} from '@angular/material/dialog';
import {UserService} from '../../../services/user/user.service';

@Component({
  selector: 'app-registration-modal',
  templateUrl: './registration-modal.component.html',
  styleUrls: ['./registration-modal.component.scss']
})
export class RegistrationModalComponent implements OnInit {

  public newUser: NewUser;

  constructor(public dialogRef: MatDialogRef<RegistrationModalComponent>, private userService: UserService) {
    this.newUser = new NewUser(null, null);
}

  ngOnInit(): void {
  }

  actionFunction() {
    this.userService.createNewUser(this.newUser).subscribe(createdUser => {
      console.log(createdUser);
      this.closeModal();
    });

  }

  closeModal() {
    this.dialogRef.close();
  }
}
