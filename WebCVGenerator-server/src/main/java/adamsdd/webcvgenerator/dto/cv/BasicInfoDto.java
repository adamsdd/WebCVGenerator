package adamsdd.webcvgenerator.dto.cv;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

public class BasicInfoDto {

    public final Long id;
    public final String name;
    public final String surname;
    public final LocalDate birthDate;
    public final String city;
    public final String phone;
    public final String hobby;

    @JsonCreator
    public BasicInfoDto(@JsonProperty("id") Long id,
                        @JsonProperty("name") String name,
                        @JsonProperty("surname") String surname,
                        @JsonProperty("birthDate") LocalDate birthDate,
                        @JsonProperty("city") String city,
                        @JsonProperty("phone") String phone,
                        @JsonProperty("hobby") String hobby) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.birthDate = birthDate;
        this.city = city;
        this.phone = phone;
        this.hobby = hobby;
    }
}
