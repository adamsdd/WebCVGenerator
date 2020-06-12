import { Injectable } from '@angular/core';
import {Observable} from 'rxjs';
import {HttpClient} from '@angular/common/http';
import {EndpointUtils} from '../../../../domain/utils/EndpointUtils';
import {Skill} from '../../../../domain/cv/Skill';

@Injectable({
  providedIn: 'root'
})
export class SkillsService {

  private skillUrl = EndpointUtils.BASE_URL + 'skill/';

  constructor(private http: HttpClient) { }

  public saveSkill(skill: Skill): Observable<Skill> {
    return this.http.post<Skill>(this.skillUrl, skill);
  }

  removeSkill(skill: Skill): Observable<any> {
    return this.http.delete(this.skillUrl + skill.id);
  }
}
