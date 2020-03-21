import { Component, OnInit } from '@angular/core';
import { Employee } from 'src/app/common/employee';
import { Department } from 'src/app/common/department';
import { EmployeeService } from 'src/app/service/employee.service';
import { DepartmentService } from 'src/app/service/department.service';
import { UserService } from 'src/app/service/user.service';


export class UserAuthorities {
  authority: string;
}

@Component({
  selector: 'app-employee-list',
  templateUrl: './employee-list.component.html',
  styleUrls: ['./employee-list.component.css']
})
export class EmployeeListComponent implements OnInit {

    employees: Employee[];
    departments: Department[];
    userAuthorities: any;
    isAdmin = false;
    q1 = ""; q2 = ""; q3 = ""; q4 = "";

  constructor(private _employeeService: EmployeeService,
              private _departmentService: DepartmentService,
              private _userService: UserService) { }

  ngOnInit(): void {
    this.listEmployees();
    this.listDepartments();
    this.principalIsAdmin();
  }

  listEmployees() {
    this._employeeService.getEmployees().subscribe(
      data => {
        this.employees = data ;
      }

    );
  }

  principalIsAdmin() {
    this._userService.getPrincipal().subscribe(
      data => {
          for (let i = 0; i < data['authorities'].length; i++) {
            if(data['authorities'][i].authority === 'ADMIN') {
              this.isAdmin = true;
            }
          }
      }
    )
  }

  deleteEmployee(employee: Employee): void {
    this._employeeService.deleteEmployee(employee).subscribe(
      data => {
        this.employees = this.employees.filter(
          u => u !== employee
        );
      }
    )
  }

  saveEmployee(): void {
    const body = {id: 1,name:this.q1, birthday:this.q2, salary:this.q3, department: {name:this.q4}};
    this._employeeService.saveEmployee(body).subscribe(
      data => {
        this.employees.push(body);
      }
    )
  }

  listDepartments() {
      this._departmentService.getDepartments().subscribe(
        data => this.departments = data
      )
  }

}

