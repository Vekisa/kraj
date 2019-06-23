export class Unit {
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

export class Adress {
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

export class Object {
  id: number;
  name: string;
  description: string;
  cancellation: number;
  category: number;
  adress: Adress;
  objectType: Type;
  unit: Unit[];

  constructor(name: string, description: string, cancellation: number, category: number){
    this.name=name;
    this.description=description;
    this.cancellation=cancellation;
    this.category=category;
  }
}


export class Type{
  id: number;
  name: string;
  description: string;

  constructor(name:string){
    this.name=name;
  }

}
export class Plan{
  id: number;
  from: Date;
  to: Date;
  price: number;
  month: number;
  perPerson: boolean;

  constructor(){
  }
}

export class PriceSchedule{
  id: number;
  made: Date;
  plan: Plan[];

  constructor(){}
}

export class Image{
  id: number;
  source: Blob;


  constructor(){}
}

export class Reservation{
  id: number;
  start: Date;
  end: Date;
  confirmed: true;
  possibleCancellationDate: Date;
  price: number;
  unit: Unit;

  constructor() {

  }

}

export class RegisteredUser{
  id: number;
  firstName: string;
  lastName: string;
  email:string;
  active: boolean;

  constructor(id: number, firstName: string, lastName: string, email: string, active: boolean){
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
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

