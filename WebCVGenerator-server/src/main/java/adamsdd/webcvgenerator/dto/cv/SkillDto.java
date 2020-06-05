package adamsdd.webcvgenerator.dto.cv;

import adamsdd.webcvgenerator.domain.cv.SkillLevel;

public class SkillDto {

    public final Long id;
    public final String name;
    public final SkillLevel level;
    public final Long cvDataId;

    public SkillDto(Long id, String name, SkillLevel level, Long cvDataId) {
        this.id = id;
        this.name = name;
        this.level = level;
        this.cvDataId = cvDataId;
    }
}
