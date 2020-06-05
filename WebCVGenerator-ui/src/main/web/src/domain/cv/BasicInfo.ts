export class BasicInfo {

  public id: number;
  public name: string;
  public surname: string;
  public birthDate: Date;
  public city: string;
  public hobby: string;
  public cvDataId: number;

  constructor(id, name, surname, birthDate, city, hobby, cvDataId) {
    this.id = id;
    this.name = name;
    this.surname = surname;
    this.birthDate = birthDate;
    this.city = city;
    this.hobby = hobby;
    this.cvDataId = cvDataId;
  }
}
