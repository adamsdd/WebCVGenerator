package adamsdd.webcvgenerator.repository.cv;

import adamsdd.webcvgenerator.domain.cv.Education;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EducationRepository extends JpaRepository<Education, Long> {
}
