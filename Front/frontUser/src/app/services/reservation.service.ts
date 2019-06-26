import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable, throwError} from "rxjs";
import {catchError} from "rxjs/operators";

@Injectable({
  providedIn: 'root'
})
export class ReservationService {

  private baseReservationURL = 'http://localhost:8762/reservation/reservation';

  constructor(private http: HttpClient) { }

  getAllForUser():Observable<any>{
    return this.http.get<any>(this.baseReservationURL + "/get_all_for_user").pipe(catchError(err => {
      return throwError(err);
    }));
  };
}
