package adamsdd.webcvgenerator.repository.cv;

import adamsdd.webcvgenerator.domain.cv.CVData;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CVDataRepository extends JpaRepository<CVData, Long> {

    CVData findByUserId(Long userId);
}
