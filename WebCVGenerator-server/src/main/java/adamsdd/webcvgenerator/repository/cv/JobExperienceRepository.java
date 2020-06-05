package adamsdd.webcvgenerator.repository.cv;

import adamsdd.webcvgenerator.domain.cv.JobExperience;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobExperienceRepository extends JpaRepository<JobExperience, Long> {
}
