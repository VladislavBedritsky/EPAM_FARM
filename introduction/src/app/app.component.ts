import { Component, OnInit } from '@angular/core';
import { HostListener } from '@angular/core';

import { MenuItem } from 'src/app/interface/menu-item';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  title = 'xfarm';

  menuItems: MenuItem[] = [
    {
      label: 'About',
    },
    {
      label: 'Recent work',
    },
    {
      label: 'Get in touch',
    }
  ];

  constructor() { }

  ngOnInit(): void {
  }


  @HostListener('window:scroll', ['$event'])
  onWindowScroll(e) {
        let element = document.querySelector('mat-toolbar');
        if (window.pageYOffset > element.clientHeight) {
          element.classList.add('navbar-inverse');
        } else {
          element.classList.remove('navbar-inverse');
        }
  }

  scroll(el: any) {
        document.getElementById(el).scrollIntoView({ behavior: 'smooth' });
  }
}
