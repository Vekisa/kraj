import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Message} from "../model";
import {Observable, throwError} from "rxjs";
import {catchError} from "rxjs/operators";

@Injectable({
  providedIn: 'root'
})
export class MessageService {
  private unitURL = 'http://localhost:8762/agent/message';


  constructor(private http: HttpClient) { }

  newMessage(message: Message ): Observable<Message> {
    console.log("uslo");
    return this.http.post<Message>(this.unitURL + "/create_message", message).pipe(catchError(err => {
      return throwError(err);
    }));
  }

  allMessages(): Observable<any> {
    return this.http.get<any>(this.unitURL + "/getAll");
  }
  allUsers(): Observable<any> {
    return this.http.get<any>(this.unitURL + "/getAllUSers");
  }

  allMessagesFromUser(id: number): Observable<any> {
    return this.http.get<any>(this.unitURL + "/getFromUser/" + id);
  }

  messageSeen(message: Message): Observable<any> {
    return this.http.put<any>(this.unitURL + "/seen/" , message);
  }
}
