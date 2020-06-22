import { Component, OnInit } from '@angular/core';

import { Department } from 'src/app/common/department';
import { DepartmentService } from 'src/app/service/department.service'

@Component({
  selector: 'app-department-list',
  templateUrl: './department-list.component.html',
  styleUrls: ['./department-list.component.css']
})
export class DepartmentListComponent implements OnInit {

  departments: Department[];

  constructor(private _departmentService: DepartmentService) { }

  ngOnInit(): void {
      this.listDepartments();
  }

  listDepartments() {
    this._departmentService.getDepartments().subscribe(
      data => this.departments = data
    )
  }

}
