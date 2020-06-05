package adamsdd.webcvgenerator.dto.cv;

import adamsdd.webcvgenerator.domain.cv.SchoolType;
import com.fasterxml.jackson.annotation.JsonCreator;

import java.time.LocalDate;

public class EducationDto {

    public final Long id;
    public final String name;
    public final String country;
    public final LocalDate from;
    public final LocalDate to;
    public final boolean currently;
    public final SchoolType schoolType;
    public final Long cvDataId;

    @JsonCreator
    public EducationDto(Long id, String name, String country, LocalDate from, LocalDate to, boolean currently,
                        SchoolType schoolType, Long cvDataId) {
        this.id = id;
        this.name = name;
        this.country = country;
        this.from = from;
        this.to = to;
        this.currently = currently;
        this.schoolType = schoolType;
        this.cvDataId = cvDataId;
    }
}
