import { Component, OnInit } from '@angular/core';
import { AppService } from 'src/app/service/app.service';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  username: string;
  password: string;
  message: any

  constructor(private _appService: AppService,
              private router: Router) { }

  ngOnInit(): void {
  }

  doLogin() {
    let response = this._appService.login(this.username, this.password);
    response.subscribe(
      data => {
        this.message = data;
        this.router.navigate(["/main"])
      }
    )
  }

  getUsername() {
    return this.username;
  }

}
