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
  private userUrl = 'https://localhost:8443/users';
  private commUrl = 'https://localhost:8443/communication';

  constructor(private http: HttpClient) { }

  newCertificate(certInfo: CertInfo): Promise<JwtResponse> {
    return this.http.post<JwtResponse>(this.certUrl+"/create_new_certificate", certInfo).toPromise();
  }

  allCertificates(): Observable<any>{
    return this.http.get<any>(this.certUrl+"/all");
  }

  allCertificatesL(): Observable<any>{
    return this.http.get<any>(this.certUrl+"/allL");
  }

  showCertificate(alias): Promise<JwtResponse> {
    return this.http.get<JwtResponse>(this.certUrl+"/show/"+ alias).toPromise();
  }

  revokeCertificate(alias): Promise<JwtResponse> {
    return this.http.post<JwtResponse>(this.certUrl+"/revoke",alias).toPromise();
  }

  allUsers(): Observable<any> {
    return this.http.get<JwtResponse>(this.userUrl);
  }

  enable(id: number): Promise<JwtResponse>{
    return this.http.post<JwtResponse>(this.userUrl + "/" + id + "/enable",id).toPromise();
  }

  disable(id: number): Promise<JwtResponse>{
    return this.http.post<JwtResponse>(this.userUrl + "/" + id + "/disable",id).toPromise();
  }

  enableCommunication(first: string, second: string):Promise<JwtResponse>{
    console.log("url: " + this.commUrl + "/" + first + "/" + second);
    return this.http.post<JwtResponse>(this.commUrl + "/" + first +"/" + second +"/create",first).toPromise();
  }

  disableCommunication(first: string, second: string):Promise<JwtResponse>{
    console.log("url: " + this.commUrl + "/" + first + "/" + second);
    return this.http.post<JwtResponse>(this.commUrl + "/" + first +"/" + second +"/disable",first).toPromise();
  }

  communications(alias: string):Observable<any>{
    return this.http.get<JwtResponse>(this.commUrl+"/"+alias);
  }

  search(alias: string):Observable<any>{
    return this.http.get<JwtResponse>(this.certUrl+"/" + alias + "/search")
  }

  searchUsers(alias: string):Observable<any>{
    return this.http.get<JwtResponse>(this.userUrl+"/" + alias + "/search")
  }
}
