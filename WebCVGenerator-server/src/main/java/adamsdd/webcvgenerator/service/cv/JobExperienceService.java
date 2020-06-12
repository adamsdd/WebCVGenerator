package adamsdd.webcvgenerator.service.cv;

import adamsdd.webcvgenerator.domain.cv.CVData;
import adamsdd.webcvgenerator.domain.cv.JobExperience;
import adamsdd.webcvgenerator.dto.cv.JobExperienceDto;
import adamsdd.webcvgenerator.repository.cv.JobExperienceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class JobExperienceService {

    private final JobExperienceRepository jobExperienceRepository;
    private final CVDataService cvDataService;

    @Autowired
    public JobExperienceService(JobExperienceRepository jobExperienceRepository, CVDataService cvDataService) {
        this.jobExperienceRepository = jobExperienceRepository;
        this.cvDataService = cvDataService;
    }

    public JobExperience getJobExperience(Long id) {
        return jobExperienceRepository
                .findById(id).orElseThrow(() -> new EntityNotFoundException("Cannot find job experience with id = " + id));
    }

    public JobExperienceDto getJobExperienceDto(Long id) {
        return getJobExperience(id).dto();
    }

    public JobExperienceDto update(JobExperienceDto jobExperienceDto) {
        CVData cvData = cvDataService.getCVData(jobExperienceDto.cvDataId);
        JobExperience jobExperience = new JobExperience(jobExperienceDto, cvData);

        return jobExperienceRepository.save(jobExperience).dto();
    }

    public boolean delete(Long id) {
        JobExperience savedJobExperience = getJobExperience(id);
        jobExperienceRepository.delete(savedJobExperience);

        return true;
    }
}
