import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {ExtraOption, ObjectType} from "../model";

@Injectable({
  providedIn: 'root'
})
export class ExtraOptionsService {

  private baseUrl = 'http://localhost:8080/extra_option';

  constructor(private http: HttpClient) { }

  allExtraOptions(): Observable<any> {
    return this.http.get<any>(this.baseUrl);
  }

  createExtraOption(obj : ExtraOption): Observable<any> {
    return this.http.put<any>(this.baseUrl, obj);
  };

  delete(id : number): Observable<void>{
    return this.http.delete<void>(this.baseUrl+"/"+id);
  }
}
