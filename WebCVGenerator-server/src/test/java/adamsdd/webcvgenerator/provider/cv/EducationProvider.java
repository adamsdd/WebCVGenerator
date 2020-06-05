package adamsdd.webcvgenerator.provider.cv;

import adamsdd.webcvgenerator.domain.cv.CVData;
import adamsdd.webcvgenerator.domain.cv.Education;
import adamsdd.webcvgenerator.domain.cv.SchoolType;
import adamsdd.webcvgenerator.dto.cv.EducationDto;

import java.time.LocalDate;

public class EducationProvider {

    public static Education education(CVData cvData) {
        return new Education(1L, "Hogward", "Wielka Brytania", LocalDate.now(), LocalDate.now(), true, SchoolType.STUDIES, cvData);
    }

    public static EducationDto educationDto(CVData cvData) {
        return new EducationDto(null, "Hogward", "Wielka Brytania", LocalDate.now(), LocalDate.now(), true, SchoolType.STUDIES, cvData.id);
    }
}
