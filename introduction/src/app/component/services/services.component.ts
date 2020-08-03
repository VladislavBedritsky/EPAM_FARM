import { Component, OnInit, ViewChild, AfterViewInit } from '@angular/core';
import { MatButton } from '@angular/material/button';
import { trigger, transition, style, animate, query, stagger } from '@angular/animations';

import { Service } from 'src/app/interface/service';

const servicesAnimation = trigger('servicesAnimation', [
  transition('* <=> *', [
      style({
        opacity: 0
      }),
      animate(
        '1000ms ease-out',
        style({
          opacity: 1
        })
      )
  ])
]);

@Component({
  selector: 'app-services',
  templateUrl: './services.component.html',
  styleUrls: ['./services.component.css'],
  animations: [servicesAnimation]
})
export class ServicesComponent implements OnInit, AfterViewInit {

  @ViewChild('btnAllRef') btnAllRef: MatButton;

  github: Service = { id: 1, label: 'GitHub', class: 'github', rest: false, link: 'https://github.com/VladislavBedritsky/EPAM_FARM' };
  angular: Service = { id: 2, label: 'Angular', class: 'angular', rest: false, link: 'https://angular.xfarm.xyz/main'  };
  webapp: Service = { id: 3, label: 'WebApp', class: 'webapp', rest: false, link: 'https://tomcat.xfarm.xyz/web-app/'  };
  rest: Service = { id: 4, label: 'WebApp', class: 'rest', rest: true, link: 'https://tomcat.xfarm.xyz/rest/departments' };
  tomcat: Service = { id: 5, label: 'Tomcat', class: 'tomcat', rest: false, link: 'https://tomcat.xfarm.xyz/'  };
  currency: Service = { id: 6, label: 'Currency', class: 'currency', rest: false, link: 'https://tomcat.xfarm.xyz/currency'  };
  ticTacFront: Service = { id: 7, label: 'Tic-tac-toe', class: 'tic-tac-front', rest: false, link: 'https://xo.xfarm.xyz/main'  };
  ticTacBack: Service = { id: 8, label: 'Tic-tac-toe', class: 'tic-tac-back', rest: true, link: 'https://tomcat.xfarm.xyz/tic-tac-toe/users'  };
  jenkins: Service = { id: 9, label: 'Jenkins', class: 'jenkins', rest: false, link: 'https://jenkins.xfarm.xyz'  };
  oauth: Service = { id: 10, label: 'Oauth2', class: 'oauth', rest: false, link: 'https://oauth.xfarm.xyz/auth/login'  };
  properties: Service = { id: 11, label: 'Properties', class: 'properties', rest: false, link: 'https://properties.xfarm.xyz/rest-prod.properties/'  };
  artifactory: Service = { id: 12, label: 'Artifactory', class: 'artifactory', rest: false, link: 'https://artifactory.xfarm.xyz'  };
  sonar: Service = { id: 13, label: 'SonarQube', class: 'sonar', rest: false, link: 'https://sonar.xfarm.xyz'  };
  activemq: Service = { id: 14, label: 'ActiveMQ', class: 'activemq', rest: false, link: 'https://activemq.xfarm.xyz'  };
  aviaFront: Service = { id: 14, label: 'Travel', class: 'avia-front', rest: false, link: 'https://travel.xfarm.xyz'  };
  aviaBack: Service = { id: 14, label: 'Gateway', class: 'avia-back', rest: true, link: 'https://gateway.xfarm.xyz/api/cities'  };


  services: Service[] = [];

  buttonAll: any;

  constructor() { }

  ngOnInit(): void {
    this.services = [
       this.github,
        this.angular,
        this.webapp,
        this.rest,
        this.tomcat,
        this.currency,
        this.ticTacFront,
        this.ticTacBack,
        this.aviaBack,
        this.aviaFront,
        this.oauth,
        this.properties,
        this.jenkins,
        this.artifactory,
        this.sonar,
        this.activemq,
    ];

  }

  ngAfterViewInit() {
    this.buttonAll = document.getElementById('buttonAll');
  console.log(this.buttonAll)
        this.buttonAll.classList.add('buttonAll-focus');
  }

  showAllApps() {
    this.services = [
       this.github,
        this.angular,
        this.webapp,
        this.rest,
        this.tomcat,
        this.currency,
        this.ticTacFront,
        this.ticTacBack,
        this.aviaBack,
        this.aviaFront,
        this.oauth,
        this.properties,
        this.jenkins,
        this.artifactory,
        this.sonar,
        this.activemq
    ];
    this.buttonAll.classList.remove('buttonAll-focus');
  }

  showSpringApps() {
    this.services = [
        this.webapp,
        this.rest,
        this.currency,
        this.ticTacBack,
        this.oauth,
        this.properties,
        this.aviaBack
    ];
    this.buttonAll.classList.remove('buttonAll-focus');
  }

  showAngApps() {
    this.services = [
        this.angular,
        this.ticTacFront,
        this.aviaFront
    ];
    this.buttonAll.classList.remove('buttonAll-focus');
  }

  showCICDApps() {
    this.services = [
        this.tomcat,
        this.jenkins,
        this.artifactory,
        this.sonar
    ];
    this.buttonAll.classList.remove('buttonAll-focus');
  }

  showGitApp() {
    this.services = [
       this.github
    ]
  }

}
