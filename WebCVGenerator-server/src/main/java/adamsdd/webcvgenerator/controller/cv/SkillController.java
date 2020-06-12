package adamsdd.webcvgenerator.controller.cv;

import adamsdd.webcvgenerator.dto.cv.SkillDto;
import adamsdd.webcvgenerator.service.cv.SkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/skill/")
public class SkillController {

    private final SkillService skillService;

    @Autowired
    public SkillController(SkillService skillService) {
        this.skillService = skillService;
    }

    @DeleteMapping(value = "{id}")
    public boolean delete(@PathVariable("id") Long skillId) {
        return skillService.delete(skillId);
    }

    @PostMapping
    public SkillDto update(@RequestBody SkillDto skillDto) {
        return skillService.update(skillDto);
    }
}
