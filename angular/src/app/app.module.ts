import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { RouterModule, Routes } from '@angular/router';
import { FormsModule } from '@angular/forms';

import { AppComponent } from './app.component';
import { EmployeeListComponent } from './components/employee-list/employee-list.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { FooterComponent } from './components/footer/footer.component';
import { PageNotFoundComponent } from './components/page-not-found/page-not-found.component'
import { MainComponent } from './components/main/main.component';
import { DepartmentListComponent } from './components/department-list/department-list.component';
import { DepartmentIdComponent } from './components/department-id/department-id.component';
import { LoginComponent } from './components/login/login.component';
import { LogoutComponent } from './components/logout/logout.component';
import { AuthGuardService } from 'src/app/service/auth-guard.service';
import { BasicAuthHttpInterceptorService } from 'src/app/service/basic-auth-http-interceptor.service';
import { ValidatorDirective } from './directive/validator.directive';

const routes: Routes = [
  {path: 'employees', component: EmployeeListComponent, canActivate:[AuthGuardService] },
  {path: 'departments', component: DepartmentListComponent, canActivate:[AuthGuardService] },
  {path: 'departments/:id', component: DepartmentIdComponent,canActivate:[AuthGuardService] },
  {path: 'login', component: LoginComponent },
  {path: 'logout', component: LogoutComponent, canActivate:[AuthGuardService]},
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
    DepartmentIdComponent,
    LoginComponent,
    LogoutComponent,
    ValidatorDirective
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    RouterModule.forRoot(routes),
    FormsModule
  ],
  providers: [
    {
      provide: HTTP_INTERCEPTORS, useClass: BasicAuthHttpInterceptorService, multi:true
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
