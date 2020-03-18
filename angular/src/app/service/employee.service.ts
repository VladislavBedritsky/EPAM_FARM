import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { Employee } from 'src/app/common/employee'

@Injectable({
  providedIn: 'root'
})
export class EmployeeService {

  private baseUrl = "http://localhost:8080/employees"

  constructor(private httpClient: HttpClient) { }

  getEmployees(): Observable<Employee[]> {
    return this.httpClient.get<Employee[]>(this.baseUrl);
  }

  getEmployeesByDepartmentId(categoryId: number): Observable<Employee[]> {

    const searchUrl = `${this.baseUrl}/byDepartmentId/${categoryId}`

    return this.httpClient.get<Employee[]>(searchUrl);
  }
}

