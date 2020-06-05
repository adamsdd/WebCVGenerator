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
    public boolean currently;
    @ManyToOne(fetch = FetchType.LAZY)
    public CVData cvData;

    public JobExperience() {
    }

    public JobExperience(Long id, LocalDate dateFrom, LocalDate dateTo, String firm, boolean currently, CVData cvData) {
        this.id = id;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.firm = firm;
        this.currently = currently;
        this.cvData = cvData;
    }

    public JobExperience(JobExperienceDto dto, CVData cvData) {
        this.id = dto.id;
        this.dateFrom = dto.from;
        this.dateTo = dto.to;
        this.firm = dto.firm;
        this.currently = dto.currently;
        this.cvData = cvData;
    }

    public JobExperienceDto dto() {
        return new JobExperienceDto(id, dateFrom, dateTo, firm, currently, cvData.id);
    }
}
