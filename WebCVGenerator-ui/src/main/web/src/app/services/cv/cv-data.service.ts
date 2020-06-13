import { Injectable } from '@angular/core';
import {HttpClient, HttpClientModule, HttpEvent, HttpRequest} from '@angular/common/http';
import {UserService} from '../user/user.service';
import {EndpointUtils} from '../../../domain/utils/EndpointUtils';
import {CVData} from '../../../domain/cv/CVData';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CvDataService {

  private cvDataByUserUrl = EndpointUtils.BASE_URL + 'cv-data/user/';
  private cvDataUrl = EndpointUtils.BASE_URL + 'cv-data/';
  private cvDataDownloadUrl = EndpointUtils.BASE_URL + 'cv-data/generate';
  private photoByCvDataIdUrl = this.cvDataUrl + 'photo/';

  constructor(private userService: UserService, private http: HttpClient) {
  }

  public getCvData(userId: number): Observable<CVData> {
    return this.http.get<CVData>(this.cvDataByUserUrl  + userId);
  }

  public getImage(cvDataId: number): any {
    return this.http.get(this.photoByCvDataIdUrl + cvDataId, {responseType: 'blob' as 'json'});
  }

  public uploadPhoto(file: File, cvDataId: number): Observable<HttpEvent<{}>> {
    const photoData: FormData = new FormData();

    photoData.append('file', file);

    const req = new HttpRequest('POST', this.photoByCvDataIdUrl + cvDataId, photoData, {
      reportProgress: true,
      responseType: 'text'
    });

    return this.http.request(req);
  }

  public deletePhoto(cvDataId: number): Observable<any> {
    return this.http.delete(this.photoByCvDataIdUrl + cvDataId);
  }

  public generate(cvDataId: number): Observable<any> {
    return this.http.get(this.cvDataDownloadUrl + '/' + cvDataId, {responseType: 'blob'});
  }
}
