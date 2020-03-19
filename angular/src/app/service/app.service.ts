import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { map } from 'rxjs/operators';


export class User{
  constructor(
    public status:string,
     ) {}

}


@Injectable({
  providedIn: 'root'
})
export class AppService {

  constructor(private http: HttpClient) { }

  authenticate(username: string, password: string) {
    const headers = new HttpHeaders({Authorization: 'Basic ' + btoa(username+":"+password)})
    return this.http.get<User>("http://localhost:8080/user", {headers}).pipe(
      map(
        data => {
          sessionStorage.setItem('username',username);
          let authString = 'Basic ' + btoa(username+":"+password);
          sessionStorage.setItem('basicauth',authString);
          return data;
        }
      )
    );
  }

}
