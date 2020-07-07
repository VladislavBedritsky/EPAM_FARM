# EPAM_FARM PROJECT

_**App in progress...**_


 ## _You can find all services remotely on links below :_  

## Web-app
_https://tomcat.xfarm.xyz/web-app/_
             
             ADMIN(role): username: q; password: q
             USER(role): username: qw; password: qw
   
:+1: **location:** _https://github.com/VladislavBedritsky/EPAM_FARM/tree/master/web-app_

## REST-app
_https://tomcat.xfarm.xyz/rest/employees/_
             
             ADMIN(role): username: q; password: q
             USER(role): username: qw; password: qw
   
**Code location:**  _https://github.com/VladislavBedritsky/EPAM_FARM/tree/master/rest_

## Currency-app
_https://tomcat.xfarm.xyz/currency_
             
**Code location:**  _https://github.com/VladislavBedritsky/EPAM_FARM/tree/master/consumer-currency_

## Angular-app
_https://angular.xfarm.xyz_
             
             ADMIN(role): username: q; password: q
             USER(role): username: qw; password: qw
   
**Code location:**  _https://github.com/VladislavBedritsky/EPAM_FARM/tree/master/angular_

## Oauth2-server
_https://oauth.xfarm.xyz/auth/login_
   
**Code location:**  _https://github.com/VladislavBedritsky/EPAM_FARM/tree/master/oauth_

## Config-server
_https://properties.xfarm.xyz/rest-prod.properties/_
   
**Code location:** _https://github.com/VladislavBedritsky/server-config_

## Tic-Tac-Toe game backend
_https://tomcat.xfarm.xyz/tic-tac-toe/users_ 
* username: `q`
* password: `q`

**Code location:**  _https://github.com/VladislavBedritsky/WebGame-Angular-Spring/tree/master/web_
 
## Tic-Tac-Toe game frontend
_https://xo.xfarm.xyz_ 
* username: `q`
* password: `q`

**Code location:** _https://github.com/VladislavBedritsky/WebGame-Angular-Spring/tree/master/angular_
 
## Tomcat 
_https://tomcat.xfarm.xyz_
* username: `jenkins`
* password: `jenkins`

 
## Jenkins
_https://jenkins.xfarm.xyz_
* username: `admin`
* password: `admin`

## Sonar
_https://sonar.xfarm.xyz_
* username: `admin`
* password: `admin`

## Artifactory
_https://artifactory.xfarm.xyz_
* username: `admin`
* password: `password123`

## ActiveMQ
_https://activemq.xfarm.xyz_
* username: `admin`
* password: `admin`


#
## _Instruction to run app locally:_
   1) `git clone  https://github.com/VladislavBedritsky/EPAM_FARM.git`
   
   2) To start Web App:
      * `cd web-app`
      * `mvn jetty:run`
      * http://localhost:8080/web-app-1.01
   
   3) To start REST App:
      * `cd rest`
      * `mvn jetty:run`
      * http://localhost:8080/rest-1.01/departments   
   
   4) To start Currency App:
      * `cd consumer-currency`
      * `mvn jetty:run`
      * http://localhost:8080/consumer-currency-1.01
   
   5) To start Angular App:
      * `cd angular`
      * `npm install`
      * `ng serve` 
      *  http://localhost:4200/  
 