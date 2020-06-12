package adamsdd.webcvgenerator.domain.cv;

import adamsdd.webcvgenerator.dto.cv.EducationDto;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "education")
public class Education {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    public Long id;
    public String name;
    public String country;
    public LocalDate dateFrom;
    public LocalDate dateTo;
    public boolean currently;
    @Enumerated(value = EnumType.STRING)
    public SchoolType schoolType;
    @ManyToOne(fetch = FetchType.EAGER)
    public CVData cvData;

    public Education() {
    }

    public Education(Long id, String name, String country, LocalDate dateFrom, LocalDate dateTo, boolean currently,
                     SchoolType schoolType,CVData cvData) {
        this.id = id;
        this.name = name;
        this.country = country;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.currently = currently;
        this.schoolType = schoolType;
        this.cvData = cvData;
    }

    public Education(EducationDto dto, CVData cvData) {
        this.id = dto.id;
        this.name = dto.name;
        this.country = dto.country;
        this.dateFrom = dto.dateFrom;
        this.dateTo = dto.dateTo;
        this.currently = dto.currently;
        this.schoolType = dto.schoolType;
        this.cvData = cvData;
    }



    public EducationDto dto() {
        return new EducationDto(id, name, country, dateFrom, dateTo, currently, schoolType, cvData.id);
    }
}
