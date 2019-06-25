import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Agent, ExtraOption} from "../model";

@Injectable({
  providedIn: 'root'
})
export class AgentService {

  private baseUrl = 'http://localhost:8762/administrator/agent';

  constructor(private http: HttpClient) { }

  allAgents(): Observable<any> {
    return this.http.get<any>(this.baseUrl+"/all");
  }

  createAgent(obj : Agent): Observable<any> {
    return this.http.post<any>(this.baseUrl, obj);
  };
}
