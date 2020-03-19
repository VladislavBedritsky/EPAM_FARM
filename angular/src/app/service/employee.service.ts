import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { Employee } from 'src/app/common/employee'

@Injectable({
  providedIn: 'root'
})
export class EmployeeService {

  private baseUrl = "http://localhost:8080/employees"
  private username = "q";
  private password = "q";

  constructor(private httpClient: HttpClient) { }

  getEmployees(): Observable<Employee[]> {
    const headers = new HttpHeaders({Authorization: 'Basic '+ btoa(this.username+":"+this.password)});
    return this.httpClient.get<Employee[]>(this.baseUrl,{headers});
  }

  getEmployeesByDepartmentId(categoryId: number): Observable<Employee[]> {
    const headers = new HttpHeaders({Authorization: 'Basic '+ btoa(this.username+":"+this.password)});
    const searchUrl = `${this.baseUrl}/byDepartmentId/${categoryId}`;
    return this.httpClient.get<Employee[]>(searchUrl,{headers});
  }
}

