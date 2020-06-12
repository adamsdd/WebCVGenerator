package adamsdd.webcvgenerator.dto.cv;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

public class JobExperienceDto {

    public final Long id;
    public final LocalDate dateFrom;
    public final LocalDate dateTo;
    public final String firm;
    public final String description;
    public final boolean currently;
    public final Long cvDataId;

    @JsonCreator
    public JobExperienceDto(@JsonProperty("id") Long id,
                            @JsonProperty("dateFrom") LocalDate dateFrom,
                            @JsonProperty("dateTo") LocalDate dateTo,
                            @JsonProperty("firm") String firm,
                            @JsonProperty("description") String description,
                            @JsonProperty("currently") boolean currently,
                            @JsonProperty("cvDataId") Long cvDataId) {
        this.id = id;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.firm = firm;
        this.description = description;
        this.currently = currently;
        this.cvDataId = cvDataId;
    }
}
