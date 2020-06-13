package adamsdd.webcvgenerator.provider.cv;

import adamsdd.webcvgenerator.TestDataPusher;
import adamsdd.webcvgenerator.domain.cv.CVData;
import adamsdd.webcvgenerator.domain.cv.JobExperience;
import adamsdd.webcvgenerator.domain.cv.Skill;
import adamsdd.webcvgenerator.domain.cv.SkillLevel;
import adamsdd.webcvgenerator.domain.user.User;
import adamsdd.webcvgenerator.domain.user.UserRole;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class CVDataProvider {

    public static CVData cvData() {
        CVData cvData = new CVData(new User(1L, "Jan", "pass", UserRole.ADMIN));
        cvData.basicInfo = BasicInfoProvider.basicInfo(cvData);
        try {
            cvData.photo = Files.readAllBytes(
                    new File(TestDataPusher.class.getClassLoader().getResource("simpsoncharacter.jpg").getPath()).toPath());
        } catch (IOException e) {
            e.printStackTrace();
            throw new IllegalStateException("Cannot get photo");
        }

        cvData.education.add(EducationProvider.education(cvData));
        cvData.education.add(EducationProvider.education(cvData));
        cvData.education.add(EducationProvider.education(cvData));
        cvData.jobExperiences.add(JobExperienceProvider.jobExperience(cvData));
        cvData.jobExperiences.add(JobExperienceProvider.jobExperience(cvData));
        cvData.jobExperiences.add(JobExperienceProvider.jobExperience(cvData));
        cvData.jobExperiences.add(JobExperienceProvider.jobExperience(cvData));

        cvData.skills.add(new Skill(1L, "Java", SkillLevel.EXPERT, cvData));
        cvData.skills.add(new Skill(1L, "SQL", SkillLevel.AVERAGE, cvData));
        cvData.skills.add(new Skill(1L, "C++", SkillLevel.BEGINNER, cvData));
        cvData.skills.add(new Skill(1L, "C#", SkillLevel.BEGINNER, cvData));

        return cvData;
    }
}
