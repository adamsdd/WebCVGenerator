package adamsdd.webcvgenerator.service.cv;

import adamsdd.webcvgenerator.domain.cv.CVData;
import adamsdd.webcvgenerator.domain.user.User;
import adamsdd.webcvgenerator.dto.cv.CVDataDto;
import adamsdd.webcvgenerator.repository.cv.CVDataRepository;
import adamsdd.webcvgenerator.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class CVDataService {

    private final CVDataRepository cvDataRepository;
    private final UserRepository userRepository;

    @Autowired
    public CVDataService(CVDataRepository cvDataRepository, UserRepository userRepository) {
        this.cvDataRepository = cvDataRepository;
        this.userRepository = userRepository;
    }

    public CVDataDto getCVDataDto(Long id) {
        return getCVData(id).dto();
    }

    public CVData getCVData(Long id) {
        return cvDataRepository.findById(id)
                 .orElseThrow(() -> new EntityNotFoundException("Cannot find cv data with id = " + id));
    }

    public CVDataDto getCvDataByUserId(Long userId) {
        CVData cvData = cvDataRepository.findByUserId(userId);

        return cvData != null ? cvData.dto() : null;
    }

    public CVDataDto createCVData(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("Cannot find user with id = " + userId));

        CVData cvData = new CVData(user);
        return cvDataRepository.save(cvData).dto();
    }
}
