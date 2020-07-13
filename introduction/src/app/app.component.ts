import { Component, OnInit } from '@angular/core';
import { HostListener } from '@angular/core';

import * as $ from "jquery";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
  }


  @HostListener('window:scroll', ['$event'])
  onWindowScroll(e) {
        let elToolbar = document.querySelector('mat-toolbar');

        if (window.pageYOffset > elToolbar.clientHeight) {
          elToolbar.classList.add('navbar-inverse');
        } else {
          elToolbar.classList.remove('navbar-inverse');
        }

        let elAbout = document.getElementById('about');
        let elServices = document.getElementById('services');
        let elContacts = document.getElementById('contacts');

        if (window.pageYOffset < (elAbout.clientHeight*0.52)) {
          elToolbar.classList.add('elAbout-inverse');
          elAbout.classList.remove('elAbout-fadein');
          elAbout.classList.add('elAbout-fadeout');
        } else {
          elToolbar.classList.remove('elAbout-inverse');
          elAbout.classList.add('elAbout-fadein');
          elAbout.classList.remove('elAbout-fadeout');
        }

        if (window.pageYOffset >= 0.52*elAbout.clientHeight &&
        window.pageYOffset < (0.85*elServices.clientHeight + elAbout.clientHeight)) {
          elToolbar.classList.add('elServices-inverse');
        } else {
          elToolbar.classList.remove('elServices-inverse');
        }

        if (window.pageYOffset >= (0.85*elServices.clientHeight + elAbout.clientHeight) &&
        window.pageYOffset < (elServices.clientHeight + elAbout.clientHeight + 0.85*elContacts.clientHeight)) {
          elToolbar.classList.add('elContacts-inverse');
        } else {
          elToolbar.classList.remove('elContacts-inverse');
        }

  }


  scroll(el: any) {
     document.getElementById(el).scrollIntoView({ behavior: 'smooth' });
  }

  scrollNotDestop(el: any) {
    $('html, body').animate({
        scrollTop: $("#"+el).offset().top
    }, 500);
  }

}
