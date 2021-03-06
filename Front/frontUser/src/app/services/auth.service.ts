import { Injectable } from '@angular/core';
import {Router} from "@angular/router";
import {CookieService} from "ngx-cookie-service";
import {HttpClient, HttpHeaders, HttpParams} from "@angular/common/http";
import {Observable} from "rxjs";
import {NewPass, RegisteredUser} from "../model";

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private baseUrl = 'http://localhost:8762/';

  constructor(private _router: Router,private http: HttpClient,private cookie:CookieService) { }

  login(userLogin) {
    let params = new URLSearchParams();
    params.append('username',userLogin.username);
    params.append('password',userLogin.password);
    params.append('grant_type','password');

    const body = new HttpParams()
      .set('username', userLogin.username)
      .set('password', userLogin.password)
      .set('grant_type', 'password');

    let headers = new HttpHeaders({'Content-type': 'application/x-www-form-urlencoded; charset=utf-8', 'Authorization': 'Basic '+btoa("oauthorize:securitypass")});


    let options = {headers:headers}

    return this.http.post(this.baseUrl + 'uua/oauth/token', body, options).subscribe(data=>{
      console.log(data);
      this.saveToken(data);

    },err=>{
      console.log(err);
    });

  }

  saveToken(token){
    var expireDate = new Date().getTime() + (1000 * token.expires_in);
    console.log(token.expires_in);
    this.cookie.set("access_token", token.access_token, expireDate);
    console.log('Obtained Access token');
    window.location.href='';
  }

  getToken(){
    return this.cookie.get("access_token");
  }

  user():Observable<any> {
    return this.http.get(this.baseUrl+"uua/user/info");
  }

  roles():Observable<any> {
    return this.http.get(this.baseUrl+"uua/user/roles");
  }

  isValid():boolean{

    if(this.getToken()==null){
      return false;
    }else{
      this.roles().subscribe(data=>{
        return true;
      },err => {
        return false;
      });
    }

  }

  saveUser(regUser:RegisteredUser):Observable<any>{
    return this.http.post(this.baseUrl+"uua/user/auth/save",regUser);
  }

  changeEmail(regUser:RegisteredUser):Observable<any>{
    return this.http.put(this.baseUrl+"uua/user/changeEmail",regUser);
  }

  changePass(pass:NewPass):Observable<any>{
    return this.http.put(this.baseUrl+"uua/user/changePass",pass);
  }

  changeName(user:RegisteredUser):Observable<any>{
    return this.http.post(this.baseUrl+"uua/user/changeName",user);
  }

  changeUserName(user:RegisteredUser):Observable<any>{
    return this.http.put(this.baseUrl+"uua/user/changeUsername",user);
  }




  logout(){
    this.cookie.delete('access_token');
  }

}
