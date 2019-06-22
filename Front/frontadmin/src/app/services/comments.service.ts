import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class CommentsService {

  private baseUrl = 'http://localhost:8762/administrator/comment';

  constructor(private http: HttpClient) { }

  allComments(): Observable<any> {
    return this.http.get<any>(this.baseUrl);
  }

  approve(id: number): Observable<any> {
    return this.http.post<any>(this.baseUrl + "/approve/" + id, id);
  };

  forbid(id : number): Observable<any>{
    console.log("obr2");
    return this.http.post<any>(this.baseUrl+"/forbid/"+id,id);
  }
}
