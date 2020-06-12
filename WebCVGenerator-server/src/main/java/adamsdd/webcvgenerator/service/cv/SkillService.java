package adamsdd.webcvgenerator.service.cv;

import adamsdd.webcvgenerator.domain.cv.CVData;
import adamsdd.webcvgenerator.domain.cv.Skill;
import adamsdd.webcvgenerator.dto.cv.SkillDto;
import adamsdd.webcvgenerator.repository.cv.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class SkillService {

    private final SkillRepository skillRepository;
    private final CVDataService cvDataService;

    @Autowired
    public SkillService(SkillRepository skillRepository, CVDataService cvDataService) {
        this.skillRepository = skillRepository;
        this.cvDataService = cvDataService;
    }

    public Skill getSkill(Long id) {
        return skillRepository
                .findById(id).orElseThrow(() -> new EntityNotFoundException("Cannot find skill with id = " + id));
    }

    public SkillDto getJobExperienceDto(Long id) {
        return getSkill(id).dto();
    }

    public SkillDto update(SkillDto skillDto) {
        CVData cvData = cvDataService.getCVData(skillDto.cvDataId);
        Skill skill = new Skill(skillDto, cvData);

        return skillRepository.save(skill).dto();
    }

    public boolean delete(Long id) {
        Skill savedSkill = getSkill(id);
        skillRepository.delete(savedSkill);

        return true;
    }
}
