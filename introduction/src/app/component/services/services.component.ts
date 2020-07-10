import { Component, OnInit, ViewChild, AfterViewInit } from '@angular/core';
import { MatButton } from '@angular/material/button';
import { trigger, transition, style, animate, query, stagger } from '@angular/animations';

import { Service } from 'src/app/interface/service';

const enterListAnimation = trigger('enterListAnimation', [
  transition('* <=> *', [
    query(':enter',
      [style({ opacity: 0 }), stagger('200ms', animate('1000ms ease-out', style({ opacity: 1 })))],
      { optional: true }
    ),
  ])
]);

@Component({
  selector: 'app-services',
  templateUrl: './services.component.html',
  styleUrls: ['./services.component.css'],
  animations: [enterListAnimation]
})
export class ServicesComponent implements OnInit, AfterViewInit {

  @ViewChild('btnAllRef') btnAllRef: MatButton;

  github: Service = { id: 1, label: 'GitHub', class: 'github', rest: false };
  angular: Service = { id: 2, label: 'Angular', class: 'angular', rest: false  };
  webapp: Service = { id: 3, label: 'WebApp', class: 'webapp', rest: false  };
  rest: Service = { id: 4, label: 'WebApp', class: 'rest', rest: true };
  tomcat: Service = { id: 5, label: 'Tomcat', class: 'tomcat', rest: false  };
  currency: Service = { id: 6, label: 'Currency', class: 'currency', rest: false  };
  ticTacFront: Service = { id: 7, label: 'Tic-tac-toe', class: 'tic-tac-front', rest: false  };
  ticTacBack: Service = { id: 8, label: 'Tic-tac-toe', class: 'tic-tac-back', rest: true  };
  jenkins: Service = { id: 9, label: 'Jenkins', class: 'jenkins', rest: false  };
  oauth: Service = { id: 10, label: 'Oauth2', class: 'oauth', rest: false  };
  properties: Service = { id: 11, label: 'Properties', class: 'properties', rest: false  };
  artifactory: Service = { id: 12, label: 'Artifactory', class: 'artifactory', rest: false  };
  sonar: Service = { id: 13, label: 'SonarQube', class: 'sonar', rest: false  };
  activemq: Service = { id: 14, label: 'ActiveMQ', class: 'activemq', rest: false  };

  services: Service[] = [];

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
        this.jenkins,
        this.oauth,
        this.properties,
        this.artifactory,
        this.sonar,
        this.activemq
    ]
  }

  ngAfterViewInit() {
     this.btnAllRef.focus();
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
        this.jenkins,
        this.oauth,
        this.properties,
        this.artifactory,
        this.sonar,
        this.activemq
    ]
  }

  showSpringApps() {
    this.services = [
        this.webapp,
        this.rest,
        this.currency,
        this.ticTacBack,
        this.oauth,
        this.properties
    ]
  }

  showAngApps() {
    this.services = [
        this.angular,
        this.ticTacFront
    ]
  }

  showCICDApps() {
    this.services = [
        this.tomcat,
        this.jenkins,
        this.artifactory,
        this.sonar
    ]
  }

  showGitApp() {
    this.services = [
       this.github
    ]
  }

}
