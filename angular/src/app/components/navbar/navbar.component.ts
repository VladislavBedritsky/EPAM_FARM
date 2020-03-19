import { Component, OnInit } from '@angular/core';
import { AppService } from 'src/app/service/app.service';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  constructor(private _appService: AppService,
              private http: HttpClient) {
  }

  ngOnInit(): void {
  }

}
