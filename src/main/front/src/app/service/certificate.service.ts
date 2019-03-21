import { Injectable } from '@angular/core';
import {CertInfo, User} from "../model";
import {Observable} from "rxjs";
import {JwtResponse} from "../authentication/response";
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class CertificateService {

  private certUrl = 'https://localhost:8443/certificates';

  constructor(private http: HttpClient) { }

  newCertificate(certInfo: CertInfo): Promise<JwtResponse> {
    return this.http.post<JwtResponse>(this.certUrl+"/createSS", certInfo).toPromise();
  }
}
