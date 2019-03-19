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
