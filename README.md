# EPAM_FARM PROJECT

_**App in progress...**_

## Instruction to run app locally:
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
 ## Or you can find all services remotely on links below :  

 ## Tomcat 
 _http://35.239.53.104:8087/_
 * username: `jenkins`
 * password: `jenkins`

You can find here 3 apps:
 * `./web-app`
 * `./rest`
           
             ADMIN(role): username: q; password: q
             USER(role): username: qw; password: qw
 * `./consumer-currency`

## Angular-app
_http://35.239.53.104:4200/_
             
             ADMIN(role): username: q; password: q
             USER(role): username: qw; password: qw
   
 Location: `./angular/`
 
## Jenkins
_http://35.239.53.104:8089/_
* username: `admin`
* password: `admin`

## Sonarqube
_http://35.239.53.104:9000/_
* username: `admin`
* password: `admin`

## Artifactory
_http://35.239.53.104:8081/_
* username: `admin`
* password: `password123`

## ActiveMQ
_http://35.226.148.166:8161/_
* username: `admin`
* password: `admin`

## Oauth2-server
_http://18.184.244.253:8981/auth/rest/user_
   
Location: `./oauth/`

## Config-server
_http://18.184.244.253:8980/rest-prod.properties/_
   
Location: `https://github.com/VladislavBedritsky/server-config`

 
 