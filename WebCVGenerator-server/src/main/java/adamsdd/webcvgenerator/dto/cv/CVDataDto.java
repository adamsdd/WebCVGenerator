package adamsdd.webcvgenerator.dto.cv;

import adamsdd.webcvgenerator.domain.user.User;
import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.List;

public class CVDataDto {

    public final Long id;
    public final User user;
    public final BasicInfoDto basicInfo;
    public final List<EducationDto> education;
    public final List<JobExperienceDto> jobExperiences;
    public final List<SkillDto> skills;

    @JsonCreator
    public CVDataDto(Long id, User user, BasicInfoDto basicInfo, List<EducationDto> education, List<JobExperienceDto> jobExperiences, List<SkillDto> skills) {
        this.id = id;
        this.user = user;
        this.basicInfo = basicInfo;
        this.education = education;
        this.jobExperiences = jobExperiences;
        this.skills = skills;
    }
}
