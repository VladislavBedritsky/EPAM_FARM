import { Component, OnInit } from '@angular/core';
import { Employee } from 'src/app/common/employee';
import { EmployeeService } from 'src/app/service/employee.service';
import { Department } from 'src/app/common/department';
import { DepartmentService } from 'src/app/service/department.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-department-id',
  templateUrl: './department-id.component.html',
  styleUrls: ['./department-id.component.css']
})
export class DepartmentIdComponent implements OnInit {

  employees: Employee[];
  department: Department;
  currentDepartmentId: number;

  constructor(private _employeeService: EmployeeService,
              private _departmentService: DepartmentService,
              private _activatedRoute: ActivatedRoute) {

  this.currentDepartmentId = +this._activatedRoute.snapshot.paramMap.get('id');

  }

  ngOnInit(): void {
    this.listEmployees();
    this.getDepartment();
  }

  listEmployees() {

    this._employeeService.getEmployeesByDepartmentId(this.currentDepartmentId).subscribe(
      data => this.employees = data
    )
  }

  getDepartment() {

    this._departmentService.getDepartmentById(this.currentDepartmentId).subscribe(
      data => this.department = data
    )
  }

}
