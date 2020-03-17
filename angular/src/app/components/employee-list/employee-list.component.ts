import { Component, OnInit } from '@angular/core';
import { Employee } from 'src/app/common/employee';
import { EmployeeService } from 'src/app/service/employee.service'

@Component({
  selector: 'app-employee-list',
  templateUrl: './employee-list.component.html',
  styleUrls: ['./employee-list.component.css']
})
export class EmployeeListComponent implements OnInit {

    employees: Employee[];

  constructor(private _employeeService: EmployeeService) { }

  ngOnInit(): void {
    this.listEmployees();
  }

  listEmployees() {
    this._employeeService.getEmployees().subscribe(
      data => this.employees = data
    )
  }
}
