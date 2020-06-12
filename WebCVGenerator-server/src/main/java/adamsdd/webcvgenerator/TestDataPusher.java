package adamsdd.webcvgenerator;

import adamsdd.webcvgenerator.domain.cv.*;
import adamsdd.webcvgenerator.domain.user.User;
import adamsdd.webcvgenerator.domain.user.UserRole;
import adamsdd.webcvgenerator.repository.cv.CVDataRepository;
import adamsdd.webcvgenerator.repository.cv.EducationRepository;
import adamsdd.webcvgenerator.repository.cv.JobExperienceRepository;
import adamsdd.webcvgenerator.repository.cv.SkillRepository;
import adamsdd.webcvgenerator.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.io.*;
import java.nio.file.Files;
import java.time.LocalDate;
import java.util.Collections;

@Component
public class TestDataPusher {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CVDataRepository cvDataRepository;
    @Autowired
    private EducationRepository educationRepository;
    @Autowired
    private JobExperienceRepository jobExperienceRepository;
    @Autowired
    private SkillRepository skillRepository;

    @EventListener
    public void appReady(ApplicationReadyEvent event) {

        User adminUser = new User(null, "admin", "$2a$10$H9MHFX2Cqh1VWeGYhWcxVeJYcco6AmDGwg6S9RqSej7ECHQ6.O/na", UserRole.ADMIN);
        userRepository.save(adminUser);
        userRepository.save(new User(null, "user", "user", UserRole.USER));
        CVData adminCVData = new CVData(adminUser);
        adminCVData.basicInfo.name = "Łukasz";
        adminCVData.basicInfo.surname = "Bauza";
        CVData savedCVData = cvDataRepository.save(adminCVData);

        Education educationFirst = educationRepository.save(new Education(null, "Hogward", "Ukraina", LocalDate.now(),
                LocalDate.now(), true, SchoolType.HIGH_SCHOOL, savedCVData));

        Education educationSecond = educationRepository.save(new Education(null, "Politechnika", "Polska", LocalDate.now(),
                LocalDate.now(), true, SchoolType.STUDIES, savedCVData));

        savedCVData.education.add(educationFirst);
        savedCVData.education.add(educationSecond);

        JobExperience jobExperience = jobExperienceRepository.save(new JobExperience(null, LocalDate.now(), LocalDate.now(),
                "Microsoft", "Duuuuuzo pracy, gooooooglowania, itp.", true, savedCVData));

        savedCVData.jobExperiences.add(jobExperience);

        Skill skillFirst = skillRepository.save(new Skill(null, "C++", SkillLevel.EXPERT, savedCVData));
        Skill skillSecond = skillRepository.save(new Skill(null, "Java", SkillLevel.EXPERT, savedCVData));
        Skill skillThird = skillRepository.save(new Skill(null, "SQL", SkillLevel.SKILLED, savedCVData));


        savedCVData.skills.add(skillFirst);
        savedCVData.skills.add(skillSecond);
        savedCVData.skills.add(skillThird);

        try {
            savedCVData.photo = Files.readAllBytes(
                    new File(TestDataPusher.class.getClassLoader().getResource("simpsoncharacter.jpg").getPath()).toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }

        cvDataRepository.save(savedCVData);

//        String encoded = new BCryptPasswordEncoder().encode("admin");
//        System.out.println(encoded);
    }
}
