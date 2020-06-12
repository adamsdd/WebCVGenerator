export class Education {

  public id: number;
  public name: string;
  public country: string;
  public dateFrom: Date;
  public dateTo: Date;
  public currently: boolean;
  public schoolType: string;
  public cvDataId: number;

  constructor(cvDataId: number)
  constructor(cvDataId?: number, id?: number, name?: string, country?: string, dateFrom?: Date, dateTo?: Date, currently?: boolean,
              schoolType?: string) {
    this.cvDataId = cvDataId;
    this.id = id;
    this.name = name;
    this.country = country;
    this.dateFrom = dateFrom;
    this.dateTo = dateTo;
    this.currently = currently || false;
    this.schoolType = schoolType;
  }
}
