export class Skill {

  public id: number;
  public name: string;
  public level: string;
  public cvDataId: number;

  constructor(cvDataId: number)
  constructor(cvDataId?: number, id?: number, name?: string, level?: string) {
    this.cvDataId = cvDataId;
    this.id = id;
    this.name = name;
    this.level = level;
  }
}
