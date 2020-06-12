export class BasicInfo {

  public id: number;
  public name: string;
  public surname: string;
  public birthDate: Date;
  public city: string;
  public phone: string;
  public hobby: string;

  constructor(id, name, surname, birthDate, city, phone, hobby) {
    this.id = id;
    this.name = name;
    this.surname = surname;
    this.birthDate = birthDate;
    this.city = city;
    this.phone = phone;
    this.hobby = hobby;
  }
}
