package adamsdd.webcvgenerator.dto.cv;

import adamsdd.webcvgenerator.domain.cv.SchoolType;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

public class EducationDto {

    public final Long id;
    public final String name;
    public final String country;
    public final LocalDate dateFrom;
    public final LocalDate dateTo;
    public final boolean currently;
    public final SchoolType schoolType;
    public final Long cvDataId;

    @JsonCreator
    public EducationDto(@JsonProperty("id") Long id,
                        @JsonProperty("name") String name,
                        @JsonProperty("country") String country,
                        @JsonProperty("dateFrom") LocalDate dateFrom,
                        @JsonProperty("dateTo") LocalDate dateTo,
                        @JsonProperty("currently") boolean currently,
                        @JsonProperty("schoolType") SchoolType schoolType,
                        @JsonProperty("cvDataId") Long cvDataId) {
        this.id = id;
        this.name = name;
        this.country = country;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.currently = currently;
        this.schoolType = schoolType;
        this.cvDataId = cvDataId;
    }
}
