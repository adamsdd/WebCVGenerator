export class JobExperience {

  public id: number;
  public dateFrom: Date;
  public dateTo: Date;
  public firm: string;
  public currently: boolean;
  public cvDataId: number;

  constructor(id, dateFrom, dateTo, firm, currently, cvDataId) {
    this.id = id;
    this.dateFrom = dateFrom;
    this.dateTo = dateTo;
    this.firm = firm;
    this.currently = currently;
    this.cvDataId = cvDataId;
  }
}
