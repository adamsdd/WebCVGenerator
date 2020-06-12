package adamsdd.webcvgenerator.dto.cv;

import adamsdd.webcvgenerator.domain.cv.SkillLevel;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class SkillDto {

    public final Long id;
    public final String name;
    public final SkillLevel level;
    public final Long cvDataId;

    @JsonCreator
    public SkillDto(@JsonProperty("id") Long id,
                    @JsonProperty("name") String name,
                    @JsonProperty("level") SkillLevel level,
                    @JsonProperty("cvDataId") Long cvDataId) {
        this.id = id;
        this.name = name;
        this.level = level;
        this.cvDataId = cvDataId;
    }
}
