import { Component, OnInit } from '@angular/core';
import { Employee } from 'src/app/common/employee';
import { EmployeeService } from 'src/app/service/employee.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-department-id',
  templateUrl: './department-id.component.html',
  styleUrls: ['./department-id.component.css']
})
export class DepartmentIdComponent implements OnInit {

  employees: Employee[];
  currentCategoryId: number;

  constructor(private _employeeService: EmployeeService,
              private _activatedRoute: ActivatedRoute) { }

  ngOnInit(): void {
    this.listEmployees();
  }

  listEmployees() {

    this.currentCategoryId = +this._activatedRoute.snapshot.paramMap.get('id');

    this._employeeService.getEmployeesByDepartmentId(this.currentCategoryId).subscribe(
      data => this.employees = data
    )
  }

}
