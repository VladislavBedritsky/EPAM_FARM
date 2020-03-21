import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private baseUrl = "http://localhost:8080/user"

  constructor(private httpClient: HttpClient) { }

  getPrincipal(): Observable<any> {
    return this.httpClient.get<any>(this.baseUrl);
  }
}
