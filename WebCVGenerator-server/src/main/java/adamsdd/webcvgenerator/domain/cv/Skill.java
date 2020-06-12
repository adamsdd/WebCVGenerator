package adamsdd.webcvgenerator.domain.cv;

import adamsdd.webcvgenerator.dto.cv.SkillDto;

import javax.persistence.*;

@Entity
public class Skill {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    public Long id;

    public String name;
    @Enumerated(value = EnumType.STRING)
    public SkillLevel level;
    @ManyToOne(fetch = FetchType.EAGER)
    public CVData cvData;

    public Skill() {
    }

    public Skill(Long id, String name, SkillLevel level, CVData cvData) {
        this.id = id;
        this.name = name;
        this.level = level;
        this.cvData = cvData;
    }

    public Skill(SkillDto dto, CVData cvData) {
        this.id = dto.id;
        this.name = dto.name;
        this.level = dto.level;
        this.cvData = cvData;
    }

    public SkillDto dto() {
        return new SkillDto(id, name, level, cvData.id);
    }
}
