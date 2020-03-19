import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { Department } from 'src/app/common/department'

@Injectable({
  providedIn: 'root'
})
export class DepartmentService {

  private baseUrl = "http://localhost:8080/departments";
  private username = "q";
  private password = "q";

  constructor(private httpClient: HttpClient) { }

  getDepartments(): Observable<Department[]> {
    const headers = new HttpHeaders({Authorization: 'Basic '+ btoa(this.username+":"+this.password)});
    return this.httpClient.get<Department[]>(this.baseUrl, {headers});
  }

  getDepartmentById(id: number) {

    const url = `${this.baseUrl}/${id}`;
    const headers = new HttpHeaders({Authorization: 'Basic '+ btoa(this.username+":"+this.password)});
    return this.httpClient.get(url,{headers});
  }
}
