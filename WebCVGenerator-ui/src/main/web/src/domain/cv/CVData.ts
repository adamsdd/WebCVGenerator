import {BasicInfo} from './BasicInfo';
import {Education} from './Education';
import {JobExperience} from './JobExperience';
import {Skill} from './Skill';

export class CVData {

  public id: number;
  public name: string;
  public surname: string;
  public basicInfo: BasicInfo;
  public education: Array<Education>;
  public jobExperiences: Array<JobExperience>;
  public skills: Array<Skill>;

  constructor(id, name, surname, basicInfo, jobExperiences, skills) {
    this.id = id;
    this.name = name;
    this.surname = surname;
    this.basicInfo = basicInfo;
    this.jobExperiences = jobExperiences;
    this.skills = skills;
  }

}
