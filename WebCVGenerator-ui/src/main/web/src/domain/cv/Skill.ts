export class Skill {

  public id: number;
  public name: string;
  public skillLevel: string;
  public cvDataId: number;


  constructor(id, name, skillLevel, cvDataId) {
    this.id = id;
    this.name = name;
    this.skillLevel = skillLevel;
    this.cvDataId = cvDataId;
  }
}
