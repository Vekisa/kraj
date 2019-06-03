import { Injectable } from '@angular/core';
import {Observable, throwError} from "rxjs";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {ObjectType} from "../model";

@Injectable({
  providedIn: 'root'
})
export class ObjectTypesService {

  private baseUrl = 'http://localhost:8080/object_type';

  constructor(private http: HttpClient) { }

  allObjectTypes(): Observable<any> {
    return this.http.get<any>(this.baseUrl);
  }

  createObjectType(obj : ObjectType): Observable<any> {
    return this.http.put(this.baseUrl, obj);
  };

  delete(id : number): Observable<void>{
    return this.http.delete<void>(this.baseUrl+"/"+id);
  }

}
