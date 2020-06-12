import { Injectable } from '@angular/core';
import {BehaviorSubject, Observable} from 'rxjs';
import {User} from '../../../domain/user/User';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {EndpointUtils} from '../../../domain/utils/EndpointUtils';
import {UserFormData} from '../../../domain/user/UserFormData';
import {NewUser} from '../../../domain/user/NewUser';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private static loginEndpoint = EndpointUtils.BASE_URL + 'login';
  private static logoutEndpoint = EndpointUtils.BASE_URL + 'logout';
  private static createEndpoint = EndpointUtils.BASE_URL + 'create';

  private loggedInUserSubject: BehaviorSubject<User>;
  public loggedInUser: Observable<User>;

  constructor(private http: HttpClient) {
    this.loggedInUserSubject = new BehaviorSubject<User>(null);
    this.loggedInUser = this.loggedInUserSubject.asObservable();
    this.loggedInUserSubject = new BehaviorSubject<User>(JSON.parse(localStorage.getItem('currentUser')));
  }

  public setLoggedUser(loggedUser: User): void {
    this.loggedInUserSubject.next(loggedUser);
    localStorage.setItem('currentUser',  JSON.stringify(loggedUser));
  }

  public get currentUserValue(): User {
    return this.loggedInUserSubject.value;
  }

  public getLoggedUser() {
    return this.loggedInUser;
  }

  public login(userData: UserFormData): Observable<User> {

    return this.http.post<User>(UserService.loginEndpoint, userData);
  }

  public logout() {
    this.http.get(UserService.logoutEndpoint).subscribe(data => {
      localStorage.clear();
      this.setLoggedUser(null);
    });
  }

  public createNewUser(newUser: NewUser) {
    return this.http.put<User>(UserService.createEndpoint, newUser);
  }
}
