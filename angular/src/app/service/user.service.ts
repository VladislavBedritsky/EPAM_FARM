import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private baseUrl = "http://35.239.53.104:8087/rest-1.01/user"

  constructor(private httpClient: HttpClient) { }

  getPrincipal(): Observable<any> {
    return this.httpClient.get<any>(this.baseUrl);
  }
}
