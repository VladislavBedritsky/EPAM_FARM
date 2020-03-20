import { Component, OnInit } from '@angular/core';
import { AppService } from 'src/app/service/app.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-logout',
  templateUrl: './logout.component.html',
  styleUrls: ['./logout.component.css']
})
export class LogoutComponent implements OnInit {

  constructor(private _appService: AppService,
              private router: Router) { }

  ngOnInit(): void {
    this._appService.logOut();
    this.router.navigate(['login']);
  }

}
