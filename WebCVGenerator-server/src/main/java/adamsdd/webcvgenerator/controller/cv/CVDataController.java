package adamsdd.webcvgenerator.controller.cv;

import adamsdd.webcvgenerator.dto.cv.CVDataDto;
import adamsdd.webcvgenerator.service.cv.CVDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cv-data/")
public class CVDataController {

    private final CVDataService cvDataService;

    @Autowired
    public CVDataController(CVDataService cvDataService) {
        this.cvDataService = cvDataService;
    }

    @GetMapping("{id}")
    public CVDataDto getCVData(@PathVariable("id") Long id) {
        return cvDataService.getCVDataDto(id);
    }

    @GetMapping("user/{id}")
    public CVDataDto getByUserId(@PathVariable("id") Long userId) {
        return cvDataService.getCvDataByUserId(userId);
    }
}
