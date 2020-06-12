export class JobExperience {

  public id: number;
  public dateFrom: Date;
  public dateTo: Date;
  public firm: string;
  public description: string;
  public currently: boolean;
  public cvDataId: number;

  constructor(cvDataId)
  constructor(cvDataId?: number, id?: number, dateFrom?: Date, dateTo?: Date, firm?: string, description?: string, currently?: boolean) {
    this.cvDataId = cvDataId;
    this.id = id;
    this.dateFrom = dateFrom;
    this.dateTo = dateTo;
    this.firm = firm;
    this.description = description;
    this.currently = currently || false;
  }
}
