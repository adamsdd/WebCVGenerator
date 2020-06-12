export class NewUser {

  public id: number;
  public username: string;
  public password: string;
  public role: string;

  constructor(username, password) {
    this.id = null;
    this.username = username;
    this.password = password;
    this.role = 'USER';
  }
}
