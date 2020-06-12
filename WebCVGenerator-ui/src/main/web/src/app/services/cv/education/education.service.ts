import { Injectable } from '@angular/core';
import {EndpointUtils} from '../../../../domain/utils/EndpointUtils';
import {HttpClient} from '@angular/common/http';
import {Education} from '../../../../domain/cv/Education';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class EducationService {

  private educationSaveUrl = EndpointUtils.BASE_URL + 'education/';

  constructor(private http: HttpClient) { }

  public saveEducation(education: Education): Observable<Education> {
    return this.http.post<Education>(this.educationSaveUrl, education);
  }

  removeEducation(education: Education): Observable<any> {
    return this.http.delete(this.educationSaveUrl + education.id);
  }
}
