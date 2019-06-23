import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable, throwError} from "rxjs";
import {catchError} from "rxjs/operators";

@Injectable({
  providedIn: 'root'
})
export class BackendService {
  private baseExtraOptionsURL = 'http://localhost:8762/exta_options';
  private baseAccommodationTypesURL = 'http://localhost:8762/accommodation_types';

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
}
