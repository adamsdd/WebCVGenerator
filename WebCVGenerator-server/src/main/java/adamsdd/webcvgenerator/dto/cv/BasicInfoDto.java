package adamsdd.webcvgenerator.dto.cv;

import java.time.LocalDate;

public class BasicInfoDto {

    public final Long id;
    public final String name;
    public final String surname;
    public final LocalDate birthDate;
    public final String city;
    public final String hobby;
    public final Long cvDataId;

    public BasicInfoDto(Long id, String name, String surname, LocalDate birthDate, String city, String hobby, Long cvDataId) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.birthDate = birthDate;
        this.city = city;
        this.hobby = hobby;
        this.cvDataId = cvDataId;
    }
}
