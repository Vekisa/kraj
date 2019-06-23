export class ObjectType {
  id: number;
  name: string;
  description: string;

  constructor(id: number, name: string, description: string) {
    this.id = id;
    this.name = name;
    this.description = description;
  }

}

export class AccommodationType {
  id: number;
  name: string;
  description: string;

  constructor(id: number, name: string, description: string){
    this.id = id;
    this.name = name;
    this.description = description;
  }
}

export class ExtraOption{
  id: number;
  name: string;
  price: number;
  description: string;

  constructor(id: number, name: string, price: number, description: string){
    this.id = id;
    this.name = name;
    this.price = price;
    this.description = this.description;
  }
}

export class Commenta{
  id: number;
  text: string;
  dateOfPublication: any;
  object: Object;
  registeredUser: RegisteredUser;

  constructor(id: number, text: string, dateOfPublication: any, object: Object, registeredUser: RegisteredUser){
    this.id = id;
    this.text = text;
    this.dateOfPublication = dateOfPublication;
    this.object = object;
    this.registeredUser = registeredUser;
  }

}

export class Object{
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

export class Agent{
  id: number;
  firstName: string;
  lastName: string;
  email: string;
  bussinesRegistrationNumber: string;

  constructor(id: number, firstName: string, lastName: string, email: string, bussinesRegistrationNumber: string){
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.bussinesRegistrationNumber = bussinesRegistrationNumber;
  }
}
