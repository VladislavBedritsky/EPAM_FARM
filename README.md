### EPAM_FARM_PROJECT

`App in progress`

## Jenkins
_localhost:8089_,  _jenkins.epam-farm.net_
* username: `admin`
* password: `admin`

## Sonarqube
_localhost:9000_,  _sonarqube.epam-farm.net_
* username: `admin`
* password: `admin`

## Tomcat 
_localhost:8087_,  _tomcat.epam-farm.net_
* username: `jenkins`
* password: `jenkins`

## Artifactory
_localhost:8081_,  _artifactory.epam-farm.net_

**Steps for sign in:**
1. Username: `admin`; Password: `password`;
2. Change password to: `paasword123`
3. Skip step "Configure a Proxy Server"
4. Create Maven repository in step "Create Repositories"
5. Finish

## Nginx 
_localhost:80_,  _nginx.epam-farm.net_

**To configure subdomains you have to update folder `hosts`**
1. In Windows10 - `C:\Windows\System32\drivers\etc\hosts`
2. Linux - `/etc/hosts`
 
**E.g.** `config/nginx/hosts`

## Oauth2-server
_localhost:8981_
   
Location: `./oauth`

## Config-server
_localhost:8980_
   
Location: `https://github.com/VladislavBedritsky/server-config`
 
## Angular-app
_localhost:4200_
   
 Location: `./angular`
 
 Command to start: `ng serve`
 
 