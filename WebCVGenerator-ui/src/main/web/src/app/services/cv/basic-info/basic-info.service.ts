import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {EndpointUtils} from '../../../../domain/utils/EndpointUtils';
import {Observable} from 'rxjs';
import {BasicInfo} from '../../../../domain/cv/BasicInfo';

@Injectable({
  providedIn: 'root'
})
export class BasicInfoService {

  private basicInfoSaveUrl = EndpointUtils.BASE_URL + 'basic-info/';

  constructor(private http: HttpClient) { }

  public saveBasicInfo(basicInfo: BasicInfo, cvDataId: number): Observable<any> {
    return this.http.post(this.basicInfoSaveUrl + cvDataId, basicInfo);
  }
}
