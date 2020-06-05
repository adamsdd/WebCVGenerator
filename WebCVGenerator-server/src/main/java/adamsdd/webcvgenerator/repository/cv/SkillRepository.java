package adamsdd.webcvgenerator.repository.cv;

import adamsdd.webcvgenerator.domain.cv.Skill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SkillRepository extends JpaRepository<Skill, Long> {
}
