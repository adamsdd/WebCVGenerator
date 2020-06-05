package adamsdd.webcvgenerator.service.cv;

import adamsdd.webcvgenerator.domain.cv.CVData;
import adamsdd.webcvgenerator.domain.cv.Education;
import adamsdd.webcvgenerator.dto.cv.EducationDto;
import adamsdd.webcvgenerator.repository.cv.EducationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class EducationService {

    private final EducationRepository educationRepository;
    private final CVDataService cvDataService;

    @Autowired
    public EducationService(EducationRepository educationRepository, CVDataService cvDataService) {
        this.educationRepository = educationRepository;
        this.cvDataService = cvDataService;
    }

    public Education getEducation(Long id) {
        return educationRepository
                .findById(id).orElseThrow(() -> new EntityNotFoundException("Cannot find education with id = " + id));
    }

    public EducationDto create(EducationDto educationDto) {
        CVData cvData = cvDataService.getCVData(educationDto.cvDataId);
        Education education = new Education(educationDto, cvData);

        return educationRepository.save(education).dto();
    }

    public EducationDto getEducationDto(Long id) {
        return getEducation(id).dto();
    }

    public EducationDto update(EducationDto educationDto) {
        Education savedEducation = getEducation(educationDto.id);
        Education updatedEducation = new Education(educationDto, savedEducation.cvData);

        return educationRepository.save(updatedEducation).dto();
    }

    public void delete(Long id) {
        Education savedEducation = getEducation(id);

        educationRepository.delete(savedEducation);
    }
}
