# EPAM_FARM

:bangbang:  _**App in progress...**_


## :arrow_down: _You can find all services remotely on links below :_ :arrow_down:  

##  :white_check_mark: Web-app
 > ### _https://tomcat.xfarm.xyz/web-app/_
             
             ADMIN(role): username: q; password: q
             USER(role): username: qw; password: qw
   
:round_pushpin: **Code location:** 

_https://github.com/VladislavBedritsky/EPAM_FARM/tree/master/web-app_

## :white_check_mark: REST-app
 > ###  _https://tomcat.xfarm.xyz/rest/employees/_
             
             ADMIN(role): username: q; password: q
             USER(role): username: qw; password: qw
   
:round_pushpin: **Code location:** 

_https://github.com/VladislavBedritsky/EPAM_FARM/tree/master/rest_

## :white_check_mark: Currency-app
 >  ### _https://tomcat.xfarm.xyz/currency_
             
:round_pushpin: **Code location:** 

_https://github.com/VladislavBedritsky/EPAM_FARM/tree/master/consumer-currency_

## :white_check_mark: Angular-app
 > ### _https://angular.xfarm.xyz_
             
             ADMIN(role): username: q; password: q
             USER(role): username: qw; password: qw
   
:round_pushpin: **Code location:** 

_https://github.com/VladislavBedritsky/EPAM_FARM/tree/master/angular_

## :white_check_mark: Oauth2-server
 > ### _https://oauth.xfarm.xyz/auth/login_
   
:round_pushpin: **Code location:** 

_https://github.com/VladislavBedritsky/EPAM_FARM/tree/master/oauth_

## :white_check_mark: Config-server
 > ### _https://properties.xfarm.xyz/rest-prod.properties/_
   
:round_pushpin: **Code location:** 
 
 _https://github.com/VladislavBedritsky/server-config_

## :white_check_mark: Tic-Tac-Toe game backend
 > ### _https://tomcat.xfarm.xyz/tic-tac-toe/users_ 
* username: `q`
* password: `q`

:round_pushpin: **Code location:** 
 
 _https://github.com/VladislavBedritsky/WebGame-Angular-Spring/tree/master/web_
 
## :white_check_mark: Tic-Tac-Toe game frontend
 > ### _https://xo.xfarm.xyz_ 
* username: `q`
* password: `q`

:round_pushpin: **Code location:** 

_https://github.com/VladislavBedritsky/WebGame-Angular-Spring/tree/master/angular_
 
## :white_check_mark: Tomcat 
 > ### _https://tomcat.xfarm.xyz_
* username: `jenkins`
* password: `jenkins`

 
## :white_check_mark: Jenkins
 > ### _https://jenkins.xfarm.xyz_
* username: `admin`
* password: `admin`

## :white_check_mark: Sonar
 > ### _https://sonar.xfarm.xyz_
* username: `admin`
* password: `admin`

## :white_check_mark: Artifactory
 >  ### _https://artifactory.xfarm.xyz_
* username: `admin`
* password: `password123`

## :white_check_mark: ActiveMQ
 > ### _https://activemq.xfarm.xyz_
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
 