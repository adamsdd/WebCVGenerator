package adamsdd.webcvgenerator.controller.cv;

import adamsdd.webcvgenerator.dto.cv.BasicInfoDto;
import adamsdd.webcvgenerator.service.cv.BasicInfoService;
import adamsdd.webcvgenerator.service.cv.CVDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/basic-info")
public class BasicInfoController {

    private final BasicInfoService basicInfoService;

    @Autowired
    public BasicInfoController(BasicInfoService basicInfoService, CVDataService cvDataService) {
        this.basicInfoService = basicInfoService;
    }

    @PostMapping("/{cvDataId}")
    public BasicInfoDto save(@PathVariable(name = "cvDataId") Long cvDataId, @RequestBody BasicInfoDto basicInfoDto) {
        return basicInfoService.save(cvDataId, basicInfoDto);
    }
}
