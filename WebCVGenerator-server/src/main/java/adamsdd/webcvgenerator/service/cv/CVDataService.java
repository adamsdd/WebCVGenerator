package adamsdd.webcvgenerator.service.cv;

import adamsdd.webcvgenerator.domain.cv.CVData;
import adamsdd.webcvgenerator.domain.user.User;
import adamsdd.webcvgenerator.dto.cv.CVDataDto;
import adamsdd.webcvgenerator.repository.cv.CVDataRepository;
import adamsdd.webcvgenerator.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.io.IOException;

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

    @Transactional
    public CVDataDto createCVData(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("Cannot find user with id = " + userId));

        CVData cvData = new CVData(user);
        return cvDataRepository.save(cvData).dto();
    }

    public byte[] getPhotoById(Long id) {
        CVData cvData = getCVData(id);
        return cvData.photo;
    }

    @Transactional
    public CVDataDto saveCvData(CVData cvData) {
        return cvDataRepository.save(cvData).dto();
    }

    public CVDataDto savePhoto(Long cvDataId, MultipartFile file) {
        CVData cvData = getCVData(cvDataId);
        try {
            cvData.photo = file.getBytes();
            return saveCvData(cvData);
        } catch (IOException e) {
            e.printStackTrace();
            throw new IllegalStateException("Cannot get photo");
        }
    }

    public boolean deletePhoto(Long cvDataId) {
        CVData cvData = getCVData(cvDataId);
        cvData.photo = null;
        saveCvData(cvData);
        return true;
    }
}
