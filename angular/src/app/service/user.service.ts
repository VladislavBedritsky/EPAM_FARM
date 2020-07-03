import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private baseUrl = "https://tomcat.xfarm.xyz/rest-1.01/user"
  private saveSessionUrl = "https://tomcat.xfarm.xyz/rest-1.01/saveSession"

  constructor(private httpClient: HttpClient) { }

  getPrincipal(): Observable<any> {
    return this.httpClient.get<any>(this.baseUrl);
  }

  saveSession(session) {
    return this.httpClient.post(this.saveSessionUrl, session);
  }
}
