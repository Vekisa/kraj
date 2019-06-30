class Image{
  id: number;
  source: Blob;

  constructor(){}
}

export class Comment{
  id: number;
  text : string;
  dateOfPublication: Date;
  registeredUser: RegisteredUser;
  constructor(id:number,text:string,dateOfPublication: Date,registeredUser: RegisteredUser){
    this.id = id;
    this.dateOfPublication = dateOfPublication;
    this.text = text;
    this.registeredUser = registeredUser;
  }
}


export class ObjectForDropDown{
  item_id : number;
  item_text : string;

  constructor(id: number, name : string){
    this.item_id = id;
    this.item_text = name;
  }
}

export class Plan{
  id: number;
  fromDate: Date;
  toDate: Date;
  price: number;
  month: number;
  perPerson: boolean;

  constructor(id: number, fromDate: Date, toDate: Date, price: number,  perPerson: boolean){
    this.id=id;
    this.fromDate = fromDate;
    this.toDate = toDate;
    this.price=price;
    this.month=0;
    this.perPerson=perPerson;
  }
}

export class ExtraOption{
  id: number;
  name: string;
  description: string;
  constructor(id:number,name:string,description:string){
    this.id = id;
    this.name = name;
    this.description = description;
  }
}

export class Unit {
  id: number;
  person: number;
  beds: number;
  object: Object;
  image: Image[];
  priceSchedule: PriceSchedule[]=[];
  reservation: string[];
  accommodationType: AccommodationType;
  cancellation: number;
  rating : number;

  constructor(id: number, person: number, beds: number, object :Object, image: Image[], accommodationType : AccommodationType,cancellation: number, priceSchedule : PriceSchedule[], rating: number) {
    this.id = id;
    this.beds = beds;
    this.person = person;
    this.object = object;
    this.image = image;
    this.accommodationType = accommodationType;
    this.cancellation = cancellation;
    this.priceSchedule = priceSchedule;
    this.rating = rating;
  }
}
export class Includes{
  id: number;
  quantity: number;
  reservation: Reservation;
  extraOption: ExtraOption;
  constructor(id:number, quantity:number, reservation: Reservation, extraOption : ExtraOption){
    this.id = id;
    this.quantity = quantity;
    this.reservation = reservation;
    this.extraOption = extraOption;
  }
}

export class Reservation{
  id: number;
  start: Date;
  end:Date;
  confirmed: boolean;
  possibleCancellationDate: Date;
  price : number;
  unit : Unit;
  registeredUser : RegisteredUser;
  includes : Includes[];
  constructor(id: number, start: Date, end: Date, confirmed: boolean, possibleCancellationDate: Date, price : number, unit: Unit, registeredUser: RegisteredUser, includes: Includes[]){
    this.id = id;
    this.start = start;
    this.end = end;
    this.confirmed = confirmed;
    this.possibleCancellationDate = possibleCancellationDate;
    this.price = price;
    this.unit = unit;
    this.registeredUser = registeredUser;
    this.includes = includes;
  }

}

export class AccommodationType{
  id: number;
  name: string;
  description: string;

  constructor(id:number,name:string,description:string){
    this.id = id;
    this.name = name;
    this.description = description;
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
  rating : number;

  constructor(id: number, name: string, description: string, cancellation: number, category: number, rating: number){
    this.id = id;
    this.name = name;
    this.description = description;
    this.cancellation = cancellation;
    this.category = category;
    this.rating = rating;
  }
}

export class RegisteredUser{
  id: number;
  firstName: string;
  lastName: string;
  email:string;
  username:string;
  password:string;
  active: boolean;
  lastPasswordResetDate:Date;

  constructor(id: number, firstName: string, lastName: string, password:string,email: string,username:string, active: boolean,lastPasswordResetDate:Date){
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.password = password;
    this.email = email;
    this.username = username;
    this.active = active;
    this.lastPasswordResetDate = lastPasswordResetDate;
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
export class PriceSchedule{
  id: number;
  made: Date;
  plan: Plan[];

  constructor(){}
}

export class Type{
  id: number;
  name: string;
  description: string;

  constructor(name:string){
    this.name=name;
  }
}

export class NewPass {
  oldPass: string;
  newPass: string;

  constructor(oldPass: string, newPass: string) {
    this.oldPass = oldPass;
    this.newPass = newPass;
  }

}

 export class Rtng{
  id : number;
  mark : number;

  constructor(id: number, mark:number) {
       this.id = id;
       this.mark = mark;
  }

}


