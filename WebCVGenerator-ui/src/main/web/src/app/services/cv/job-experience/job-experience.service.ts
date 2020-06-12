import { Injectable } from '@angular/core';
import {Observable} from 'rxjs';
import {HttpClient} from '@angular/common/http';
import {EndpointUtils} from '../../../../domain/utils/EndpointUtils';
import {JobExperience} from '../../../../domain/cv/JobExperience';

@Injectable({
  providedIn: 'root'
})
export class JobExperienceService {

  private jobExperienceUrl = EndpointUtils.BASE_URL + 'job-experience/';

  constructor(private http: HttpClient) { }

  public saveJobExperience(jobExperience: JobExperience): Observable<JobExperience> {
    return this.http.post<JobExperience>(this.jobExperienceUrl, jobExperience);
  }

  removeJobExperience(jobExperience: JobExperience): Observable<any> {
    return this.http.delete(this.jobExperienceUrl + jobExperience.id);
  }
}
