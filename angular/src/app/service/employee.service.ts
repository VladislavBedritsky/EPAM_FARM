import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { Employee } from 'src/app/common/employee'

@Injectable({
  providedIn: 'root'
})
export class EmployeeService {

  private baseUrl = "https://tomcat.xfarm.xyz/rest/employees"

  constructor(private httpClient: HttpClient) { }

  getEmployees(): Observable<Employee[]> {
    return this.httpClient.get<Employee[]>(this.baseUrl);
  }

  getEmployeesByDepartmentId(categoryId: number): Observable<Employee[]> {
    const searchUrl = `${this.baseUrl}/byDepartmentId/${categoryId}`;
    return this.httpClient.get<Employee[]>(searchUrl);
  }

  saveEmployee(employee) {
    return this.httpClient.post(this.baseUrl, employee);
  }

  deleteEmployee(employee) {
    const headers = new HttpHeaders({ Authorization: 'Basic ' + btoa('q' + ':' + 'q') });
    const searchUrl = `${this.baseUrl}/${employee.id}`;
    return this.httpClient.delete<Employee>(searchUrl, {headers});
  }
}

