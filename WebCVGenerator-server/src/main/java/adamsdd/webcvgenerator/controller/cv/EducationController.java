package adamsdd.webcvgenerator.controller.cv;

import adamsdd.webcvgenerator.dto.cv.EducationDto;
import adamsdd.webcvgenerator.service.cv.EducationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/education/")
public class EducationController {

    private final EducationService educationService;

    @Autowired
    public EducationController(EducationService educationService) {
        this.educationService = educationService;
    }

    @PutMapping
    public EducationDto create(@RequestBody EducationDto educationDto) {
        return educationService.create(educationDto);
    }

    @DeleteMapping(value = "{id}")
    public void delete(@PathVariable("id") Long educationId) {
        educationService.delete(educationId);
    }

    @PostMapping
    public EducationDto update(@RequestBody EducationDto educationDto) {
        return educationService.update(educationDto);
    }
}
