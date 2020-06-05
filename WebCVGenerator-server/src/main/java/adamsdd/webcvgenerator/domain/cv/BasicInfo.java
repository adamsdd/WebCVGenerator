package adamsdd.webcvgenerator.domain.cv;

import adamsdd.webcvgenerator.dto.cv.BasicInfoDto;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class BasicInfo {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    public Long id;

    public String name;
    public String surname;
    public LocalDate birthDate;
    public String city;
    public String hobby;
    @ManyToOne(fetch = FetchType.LAZY)
    public CVData cvData;

    public BasicInfo() {
    }

    public BasicInfo(Long id, String name, String surname, LocalDate birthDate, String city, String hobby, CVData cvData) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.birthDate = birthDate;
        this.city = city;
        this.hobby = hobby;
        this.cvData = cvData;
    }

    public BasicInfo(BasicInfoDto dto, CVData cvData) {
        this.id = dto.id;
        this.name = dto.name;
        this.surname = dto.surname;
        this.birthDate = dto.birthDate;
        this.city = dto.city;
        this.hobby = dto.hobby;
        this.cvData = cvData;
    }

    public BasicInfoDto dto() {
        return new BasicInfoDto(id, name, surname, birthDate, city, hobby, cvData.id);
    }
}
