import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { RouterModule, Routes } from '@angular/router';

import { AppComponent } from './app.component';
import { EmployeeListComponent } from './components/employee-list/employee-list.component';
import { EmployeeService } from './service/employee.service';
import { NavbarComponent } from './components/navbar/navbar.component';
import { FooterComponent } from './components/footer/footer.component';
import { PageNotFoundComponent } from './components/page-not-found/page-not-found.component'
import { MainComponent } from './components/main/main.component';
import { DepartmentListComponent } from './components/department-list/department-list.component';
import { DepartmentIdComponent } from './components/department-id/department-id.component'

const routes: Routes = [
  {path: 'employees', component: EmployeeListComponent },
  {path: 'departments', component: DepartmentListComponent },
  {path: 'departments/:id', component: DepartmentIdComponent },
  {path: 'main', component: MainComponent },
  {path: '', redirectTo: '/main', pathMatch: 'full'},
  {path: '**', component: PageNotFoundComponent }
]

@NgModule({
  declarations: [
    AppComponent,
    EmployeeListComponent,
    NavbarComponent,
    FooterComponent,
    PageNotFoundComponent,
    MainComponent,
    DepartmentListComponent,
    DepartmentIdComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    RouterModule.forRoot(routes)
  ],
  providers: [
    EmployeeService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
