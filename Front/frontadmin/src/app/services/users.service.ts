import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class UsersService {

  private baseUrl = 'http://localhost:8080/registered_user';

  constructor(private http: HttpClient) { }

  allUsers(): Observable<any> {
    return this.http.get<any>(this.baseUrl);
  }

  activate(id: number): Observable<any>{
    return this.http.post(this.baseUrl + "/activate/" + id, id);
  }

  deactivate(id: number): Observable<any>{
    return this.http.post(this.baseUrl + "/deactivate/" + id, id);
  }

  delete(id : number): Observable<any>{
    return this.http.delete<any>(this.baseUrl+"/"+id);
  }
}
