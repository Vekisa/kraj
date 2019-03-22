
export class User {
  firstName: string;
  lastName: string;
  username: string;
  email: string;
  password: string;
  

  constructor(firstName: string, lastName: string, username: string, email: string, password: string) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.username = username;
    this.email = email;
    this.password = password;
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
  loc: string;
  org: string;
  orgUnit: string;
  commName: string;
  startDate: Date;
  endDate: Date;
  alias: string;
  password: string;

  constructor(parent: string, country: string, state:string, loc: string, org: string, orgUnit: string, commName: string, startDate: Date, endDate: Date, alias: string, password: string) {
    this.country = country;
    this.state = state;
    this.loc = loc;
    this.org = org;
    this.orgUnit = orgUnit;
    this.commName = commName;
    this.startDate = startDate;
    this.endDate = endDate;
    this.alias = alias;
    this.password = password;
    this.parent = parent;
  }

}
