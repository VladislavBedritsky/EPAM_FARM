import { Component, OnInit } from '@angular/core';
import { AppService } from 'src/app/service/app.service';
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
              private router: Router) { }

  ngOnInit(): void {
  }

  checkLogin() {
    this._appService.authenticate(this.username, this.password).subscribe(
      data => {
        this.router.navigate([''])
        this.invalidLogin = false
      },
      error => {
        this.invalidLogin = true

      }
    );

  }

}
