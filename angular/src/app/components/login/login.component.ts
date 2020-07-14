import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';

import { AppService } from 'src/app/service/app.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  username = ''
  password = ''
  invalidLogin = false
  returnUrl: string

  constructor(private _appService: AppService,
              private router: Router,
              private route: ActivatedRoute,) { }

  ngOnInit(): void {
    this._appService.logOut();

    this.returnUrl = this.route.snapshot.queryParams['returnUrl'] || '/';
  }

  checkLogin() {
    this._appService.authenticate(this.username, this.password).subscribe(
      data => {
        this.router.navigateByUrl(this.returnUrl)
        this.invalidLogin = false
      },
      error => {
        this.invalidLogin = true

      }
    );

  }

}
