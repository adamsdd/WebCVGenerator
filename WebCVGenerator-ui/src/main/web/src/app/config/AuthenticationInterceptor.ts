import {Injectable} from '@angular/core';
import {HttpEvent, HttpHandler, HttpHeaders, HttpInterceptor, HttpRequest, HttpResponse} from '@angular/common/http';
import {Observable, throwError} from 'rxjs';
import {catchError, map} from 'rxjs/operators';
import {UserService} from '../services/user/user.service';
import {Router} from '@angular/router';

@Injectable({providedIn: 'root'})
export class AuthenticationInterceptor implements HttpInterceptor {

  constructor(private router: Router, private userService: UserService) {
  }

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    const clonedRequest =
      request.clone(
        {withCredentials: true}
      );
    return next.handle(clonedRequest)
      .pipe(
        map((event: HttpEvent<any>) => {
          if (event instanceof HttpResponse) {
          }
          return event;
        }),
        catchError(error => {
          if (error.status === 401) {
            this.userService.setLoggedUser(null);
          }
          return throwError(error);
        }));
  }
}
