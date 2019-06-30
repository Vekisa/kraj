import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable, throwError} from "rxjs";
import {catchError} from "rxjs/operators";

@Injectable({
  providedIn: 'root'
})
export class RatingService {

  private baseURL = 'http://localhost:8762/rating/rating';

  constructor(private http: HttpClient) { }

  getRatings(list : number[]):Observable<any>{
    return this.http.post<any>(this.baseURL+"/list_of_ratings", list).pipe(catchError(err => {
      return throwError(err);
    }));
  }

  getRating(id: number):Observable<any>{
    return this.http.get<any>(this.baseURL+"/" + id + "/mark").pipe(catchError(err => {
      return throwError(err);
    }));
  }

  test():Observable<any>{
    return this.http.get<any>(this.baseURL).pipe(catchError(err => {
      return throwError(err);
    }));
  }
}
