# EPAM_FARM PROJECT

_**App in progress...**_


 ## _You can find all services remotely on links below :_  

## Web-app
_https://tomcat.xfarm.xyz/web-app/_
             
             ADMIN(role): username: q; password: q
             USER(role): username: qw; password: qw
   
 Location: `./web-app/`

## REST-app
_https://tomcat.xfarm.xyz/rest/employees/_
             
             ADMIN(role): username: q; password: q
             USER(role): username: qw; password: qw
   
 Location: `./rest/`

## Currency-app
_https://tomcat.xfarm.xyz/currency_
             
 Location: `./consumer-currency/`

## Angular-app
_https://angular.xfarm.xyz_
             
             ADMIN(role): username: q; password: q
             USER(role): username: qw; password: qw
   
 Location: `./angular/`
 
 
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

## Oauth2-server
_https://oauth.xfarm.xyz/auth/login_
   
Location: `./oauth/`

## Config-server
_https://properties.xfarm.xyz/rest-prod.properties/_
   
Location: `https://github.com/VladislavBedritsky/server-config`


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
 