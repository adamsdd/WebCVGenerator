package adamsdd.webcvgenerator.service.cv;

import adamsdd.webcvgenerator.domain.cv.BasicInfo;
import adamsdd.webcvgenerator.domain.cv.CVData;
import adamsdd.webcvgenerator.dto.cv.BasicInfoDto;
import adamsdd.webcvgenerator.repository.cv.BasicInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BasicInfoService {

    private final CVDataService cvDataService;
    private final BasicInfoRepository basicInfoRepository;

    @Autowired
    public BasicInfoService(BasicInfoRepository basicInfoRepository, CVDataService cvDataService) {
        this.basicInfoRepository = basicInfoRepository;
        this.cvDataService = cvDataService;
    }

    public BasicInfoDto save(Long cvDataId, BasicInfoDto basicInfoDto) {
        CVData cvData = cvDataService.getCVData(cvDataId);
        return basicInfoRepository.save(new BasicInfo(basicInfoDto, cvData)).dto();
    }
}
