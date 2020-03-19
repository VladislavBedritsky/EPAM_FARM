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

  username = ''
  password = ''
  invalidLogin = false

  constructor(private _appService: AppService,
              private router: Router,
              private http: HttpClient) { }

  ngOnInit(): void {
  }

  login() {
    this._appService.authenticate(this.username, this.password).subscribe(
      data => {
        this.router.navigate(['']);
        this.invalidLogin = false
      },
      error => {
        this.invalidLogin = true
      }
    );
  }

}
