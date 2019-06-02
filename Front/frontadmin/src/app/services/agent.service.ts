import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Agent, ExtraOption} from "../model";

@Injectable({
  providedIn: 'root'
})
export class AgentService {

  private baseUrl = 'http://localhost:8080/agent';

  constructor(private http: HttpClient) { }

  allAgents(): Observable<any> {
    return this.http.get<any>(this.baseUrl);
  }

  createAgent(obj : Agent): Observable<any> {
    return this.http.put<any>(this.baseUrl, obj);
  };
}
