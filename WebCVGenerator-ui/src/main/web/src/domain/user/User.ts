export class User {

  public id: number;
  public username: string;
  public role: string

  constructor(id, username, role) {
    this.id = id;
    this.username = username;
    this.role = role;
  }

}
