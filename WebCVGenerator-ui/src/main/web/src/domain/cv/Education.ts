export class Education {

  public id: number;
  public name: string;
  public country: string;
  public dateFrom: Date;
  public dateTo: Date;
  public currently: boolean;
  public schoolType: string;
  public cvDataId: number;


  constructor(id, name, country, dateFrom, dateTo, currently, schoolType, cvDataId) {
    this.id = id;
    this.name = name;
    this.country = country;
    this.dateFrom = dateFrom;
    this.dateTo = dateTo;
    this.currently = currently;
    this.schoolType = schoolType;
    this.cvDataId = cvDataId;
  }

}
