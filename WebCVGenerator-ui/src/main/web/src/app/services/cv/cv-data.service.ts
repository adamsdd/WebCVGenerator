import { Injectable } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class CvDataService {

  private url = 'localhost:8080/'

  constructor() { }
}
