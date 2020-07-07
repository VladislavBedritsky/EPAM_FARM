import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { Department } from 'src/app/common/department'

@Injectable({
  providedIn: 'root'
})
export class DepartmentService {

  private baseUrl = "https://tomcat.xfarm.xyz/rest/departments";

  constructor(private httpClient: HttpClient) { }

  getDepartments(): Observable<Department[]> {
    return this.httpClient.get<Department[]>(this.baseUrl);
  }

  getDepartmentById(id: number) {

    const url = `${this.baseUrl}/${id}`;
    return this.httpClient.get(url);
  }
}
