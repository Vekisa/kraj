import { Injectable } from '@angular/core';
import {Observable, throwError} from "rxjs";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {ObjectType} from "../model";
import {catchError} from "rxjs/operators";

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
    return this.http.put<any>(this.baseUrl, obj).pipe(catchError(err=>{
      return throwError(err);
    }));
  };

  delete(id : number): Observable<any>{
    console.log("obr2");
    return this.http.delete<any>(this.baseUrl+"/"+id);
  }

}
