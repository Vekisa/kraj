import { Injectable } from '@angular/core';
import {Observable} from "rxjs";
import {HttpClient} from "@angular/common/http";
import {AccommodationType, ObjectType} from "../model";

@Injectable({
  providedIn: 'root'
})
export class AccommodationTypesService {

  private baseUrl = 'http://localhost:8080/accommodation_type';

  constructor(private http: HttpClient) { }

  allObjectTypes(): Observable<any> {
    return this.http.get<any>(this.baseUrl);
  }

  createAccommodationType(obj : AccommodationType): Observable<any> {
    return this.http.put<any>(this.baseUrl, obj);
  };

  remove(id : number): Observable<void>{
    return this.http.delete<void>(this.baseUrl + "/" + id);
  }
}
