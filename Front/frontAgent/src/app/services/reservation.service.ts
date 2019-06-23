import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Reservation} from "../model";
import {Observable, throwError} from "rxjs";
import {JwtResponse} from "../response";
import {catchError} from "rxjs/operators";

@Injectable({
  providedIn: 'root'
})
export class ReservationService {
  private reservationURL = 'http://localhost:8333/reservation';
  constructor(private http: HttpClient) { }

  checkReservation(reservation: Reservation): Observable<boolean>{
    return this.http.put<boolean>(this.reservationURL + "/checkReservation", reservation);
  }

  createReservation(reservation: Reservation): Observable<JwtResponse>{
    return this.http.post<JwtResponse>(this.reservationURL+"/create", reservation).pipe(catchError(err => {
      return throwError(err);
    }));
  }
}
