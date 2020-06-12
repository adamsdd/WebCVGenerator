package adamsdd.webcvgenerator.domain.cv;

import adamsdd.webcvgenerator.dto.cv.JobExperienceDto;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class JobExperience {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    public Long id;

    public LocalDate dateFrom;
    public LocalDate dateTo;
    public String firm;
    public String description;
    public boolean currently;
    @ManyToOne(fetch = FetchType.EAGER)
    public CVData cvData;

    public JobExperience() {
    }

    public JobExperience(Long id, LocalDate dateFrom, LocalDate dateTo, String firm, String description, boolean currently, CVData cvData) {
        this.id = id;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.firm = firm;
        this.description = description;
        this.currently = currently;
        this.cvData = cvData;
    }

    public JobExperience(JobExperienceDto dto, CVData cvData) {
        this.id = dto.id;
        this.dateFrom = dto.dateFrom;
        this.dateTo = dto.dateTo;
        this.firm = dto.firm;
        this.description = dto.description;
        this.currently = dto.currently;
        this.cvData = cvData;
    }

    public JobExperienceDto dto() {
        return new JobExperienceDto(id, dateFrom, dateTo, firm, description, currently, cvData.id);
    }
}
