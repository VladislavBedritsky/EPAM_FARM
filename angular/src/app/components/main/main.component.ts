import { Component, OnInit } from '@angular/core';
import { formatDate } from '@angular/common';

import { UserService } from 'src/app/service/user.service';

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.css']
})
export class MainComponent implements OnInit {

  timeNow = new Date();
  formatTimeNow = ''

  constructor(private _userService: UserService) { }

  ngOnInit(): void {
    this.formatTimeNow = formatDate(this.timeNow, 'yyyy-MM-dd hh:mm:ss', 'en-US');
    this.saveSession()
  }

  saveSession() {
    const session = {id: 1, time: this.formatTimeNow, page: 'angular'};
    this._userService.saveSession(session).subscribe()
  }

}
