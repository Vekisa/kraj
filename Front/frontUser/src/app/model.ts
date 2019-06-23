class Image{
  id: number;
  source: Blob;

  constructor(){}
}

export class Plan{
  id: number;
  from: Date;
  to: Date;
  price: number;
  month: number;
  perPerson: boolean;

  constructor(id: number, fromDate: Date, toDate: Date, price: number,  perPerson: boolean){
    this.id=id;
    this.from=fromDate;
    this.to=toDate;
    this.price=price;
    this.month=0;
    this.perPerson=perPerson;
  }
}

class Unit {
  id: number;
  adults: number;
  children: number;
  beds: number;
  smoking: boolean;
  size: number;
  object: Object;
  image: Image[];
  priceSchedule: PriceSchedule[]=[];
  reservation: string[];
  accommodationType: string;

  constructor() {
  }

}

class Adress {
  id:number;
  state: string;
  city: string;
  street: string;
  number: number;
  zip: number;
  longitude: number;
  latitude: number;

  constructor(id: number, state: string, city: string, street: string, number: number, zip: number, longitude: number, latitude: number){
    this.id=id;
    this.state=state;
    this.city=city;
    this.street=street;
    this.number=number;
    this.zip=zip;
    this.longitude=longitude;
    this.latitude=latitude;
  }
}

class Object {
  id: number;
  name: string;
  description: string;
  cancellation: number;
  category: number;


  constructor(id: number, name: string, description: string, cancellation: number, category: number){
    this.id = id;
    this.name = name;
    this.description = description;
    this.cancellation = cancellation;
    this.category = category;
  }
}

export class RegisteredUser{
  id: number;
  firstName: string;
  lastName: string;
  email:string;
  password:string;
  active: boolean;

  constructor(id: number, firstName: string, lastName: string, password:string,email: string, active: boolean){
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.password = password;
    this.email = email;
    this.active = active;
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

export class Agent {
  id: number;
  firstName: string;
  lastName: string;
  email: string;
  bussinesRegistrationNumber: string;

  constructor(id: number, firstName: string, lastName: string, email: string, bussinesRegistrationNumber: string) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.bussinesRegistrationNumber = bussinesRegistrationNumber;
  }

}
class PriceSchedule{
  id: number;
  made: Date;
  plan: Plan[];

  constructor(){}
}

class Type{
  id: number;
  name: string;
  description: string;

  constructor(name:string){
    this.name=name;
  }


}
