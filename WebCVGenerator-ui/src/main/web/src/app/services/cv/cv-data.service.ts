import { Injectable } from '@angular/core';
import {HttpClient, HttpClientModule} from '@angular/common/http';
import {UserService} from '../user/user.service';
import {EndpointUtils} from "../../../domain/utils/EndpointUtils";
import {User} from "../../../domain/user/User";

@Injectable({
  providedIn: 'root'
})
export class CvDataService {

  private cvDataUrl = EndpointUtils.BASE_URL + 'cv-data/';

  constructor(private userService: UserService, private http: HttpClient) {

  }

  getTestData() {
    return this.http.get(this.cvDataUrl + '1');
  }
}
