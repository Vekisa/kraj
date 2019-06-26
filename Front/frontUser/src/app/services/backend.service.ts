import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable, throwError} from "rxjs";
import {catchError} from "rxjs/operators";

@Injectable({
  providedIn: 'root'
})
export class BackendService {
  private baseExtraOptionsURL = 'http://localhost:8762/backend/extra_option';
  private baseAccommodationTypesURL = 'http://localhost:8762/backend/accommodation_type';
  private baseUnitURL = 'http://localhost:8762/backend/unit';
  private baseObjectURL = 'http://localhost:8762/backend/object';

  constructor(private http: HttpClient) { }

  getAllExtraOptions():Observable<any>{
    return this.http.get<any>(this.baseExtraOptionsURL).pipe(catchError(err => {
      return throwError(err);
    }));
  };

  getAllAccommodationTypes():Observable<any>{
    return this.http.get<any>(this.baseAccommodationTypesURL).pipe(catchError(err => {
      return throwError(err);
    }));
  };

  getUnit(id:number):Observable<any>{
    return this.http.get<any>(this.baseUnitURL+"/"+id).pipe(catchError(err => {
      return throwError(err);
    }));
  }

  extraOptionsOfObject(id:number):Observable<any>{
    return this.http.get<any>(this.baseObjectURL+"/"+id + "/extra_options").pipe(catchError(err => {
      return throwError(err);
    }));
  }

  getPlanOfUnit(id: number):Observable<any> {
    return this.http.get<any>(this.baseUnitURL+"/"+id + "/price_schedule").pipe(catchError(err => {
      return throwError(err);
    }));
  }
}
