package adamsdd.webcvgenerator.controller.cv;

import adamsdd.webcvgenerator.dto.cv.JobExperienceDto;
import adamsdd.webcvgenerator.service.cv.JobExperienceService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/job-experience/")
public class JobExperienceController {

    private final JobExperienceService jobExperienceService;

    public JobExperienceController(JobExperienceService jobExperienceService) {
        this.jobExperienceService = jobExperienceService;
    }

    @DeleteMapping(value = "{id}")
    public boolean delete(@PathVariable("id") Long jobExperienceId) {
        return jobExperienceService.delete(jobExperienceId);
    }

    @PostMapping
    public JobExperienceDto update(@RequestBody JobExperienceDto jobExperienceDto) {
        return jobExperienceService.update(jobExperienceDto);
    }
}
