package adamsdd.webcvgenerator.provider.cv;

import adamsdd.webcvgenerator.domain.cv.CVData;
import adamsdd.webcvgenerator.domain.cv.JobExperience;

import java.time.LocalDate;

public class JobExperienceProvider {

    public static JobExperience jobExperience(CVData cvData) {
        return new JobExperience(1L, LocalDate.now(), LocalDate.now(), "Micro$oft", "Goooglowanie, memowanie, youtubowanie", false, cvData);
    }
}
