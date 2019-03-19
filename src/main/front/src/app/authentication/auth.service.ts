import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {UserLogin} from "../model";
import {Observable} from "rxjs";
import {JwtResponse} from "./response";



const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private loginUrl = 'https://localhost:8443/api/auth/signin';

  constructor(private http: HttpClient) { }

  attemptAuth(credentials: UserLogin): Observable<JwtResponse> {
    return this.http.post<JwtResponse>(this.loginUrl, credentials, httpOptions);
  }



}
