import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private baseUrl = 'http://localhost:8762/';

  constructor(private http: HttpClient) { }

  login(userLogin) {
    let params = new URLSearchParams();
    params.append('username',userLogin.username);
    params.append('password',userLogin.password);
    params.append('grant_type','password');

    let headers = new HttpHeaders({'Content-type': 'application/x-www-form-urlencoded; charset=utf-8', 'Authorization': 'Basic '+btoa("oauthorize:securitypass")});

    let options = {headers:headers}

    return this.http.post(this.baseUrl + 'uua/oauth/token', params, options).subscribe(data=>{
      console.log(data);
    },err=>{
      console.log(err);
    });

  }

}
