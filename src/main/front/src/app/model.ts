
export class User {
  id: number;
  username: string;
  firstName: string;
  lastName: string;
  email: string;
  password: string;
  enabled: boolean;
  lastPasswordResetDate: Date;
  verified: boolean;

  constructor(id: number, username: string, firstName: string, lastName: string,  email: string, password: string, enabled: boolean, lastPasswordResetDate: Date, verified: boolean) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.username = username;
    this.email = email;
    this.password = password;
    this.enabled = enabled;
    this.lastPasswordResetDate = lastPasswordResetDate;
    this.verified = verified;
  }

}

export class UserLogin {
  username: string;
  password: string;

  constructor(username: string, password: string) {
    this.username = username;
    this.password = password;
  }

}

export class CertInfo {

  parent: string;
  country: string;
  state:string;
  locality: string;
  organization: string;
  organizationUnit: string;
  commonName: string;
  startDate: Date;
  endDate: Date;
  alias: string;
  password: string;
  leaf: boolean;

  constructor(parent: string, country: string, state:string, locality: string, organization: string, organizationUnit: string, commonName: string, startDate: Date, endDate: Date, alias: string, password: string) {
    this.country = country;
    this.state = state;
    this.locality = locality;
    this.organization = organization;
    this.organizationUnit = organizationUnit;
    this.commonName = commonName;
    this.startDate = startDate;
    this.endDate = endDate;
    this.alias = alias;
    this.password = password;
    this.parent = parent;
  }

}

export class CertificateDB {

  alias: String;
  sn: String;
  startDate: Date;
  endDate: Date;
  signedByAlias: String;

  constructor(alias: string,sn: string,startDate: Date,endDate: Date,signedByAlias: string){
    this.alias = alias;
    this.sn = sn;
    this.startDate = startDate;
    this.endDate = endDate;
    this.signedByAlias = signedByAlias;
  }




}
