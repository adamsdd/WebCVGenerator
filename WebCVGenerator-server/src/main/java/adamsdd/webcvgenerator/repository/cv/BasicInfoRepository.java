package adamsdd.webcvgenerator.repository.cv;

import adamsdd.webcvgenerator.domain.cv.BasicInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BasicInfoRepository extends JpaRepository<BasicInfo, Long> {
}
