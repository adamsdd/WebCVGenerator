import {User} from '../user/User';
import {BasicInfo} from './BasicInfo';
import {Education} from './Education';
import {JobExperience} from './JobExperience';
import {Skill} from './Skill';

export class CVData {

  public id: number;
  public name: string;
  public surname: string;
  public user: User;
  public basicInfo: BasicInfo;
  public education: Array<Education>;
  public jobExperiences: Array<JobExperience>;
  public skills: Array<Skill>;

  constructor(id, name, surname, user, basicInfo, jobExperiences, skills) {
    this.id = id;
    this.name = name;
    this.surname = surname;
    this.user = user;
    this.basicInfo = basicInfo;
    this.jobExperiences = jobExperiences;
    this.skills = skills;
  }

}
