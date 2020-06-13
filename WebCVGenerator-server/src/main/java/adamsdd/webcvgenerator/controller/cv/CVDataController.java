package adamsdd.webcvgenerator.controller.cv;

import adamsdd.webcvgenerator.dto.cv.CVDataDto;
import adamsdd.webcvgenerator.service.cv.CVDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

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

    @GetMapping("photo/{cvDataId}")
    public ResponseEntity<byte[]> getImage(@PathVariable("cvDataId") Long cvDataId) throws IOException {

        byte[] imageFile = cvDataService.getPhotoById(cvDataId);

        return ResponseEntity
                .ok()
                .contentType(MediaType.IMAGE_PNG)
                .body(imageFile);
    }

    @PostMapping("/photo/{cvDataId}")
    public CVDataDto uploadPhoto(@PathVariable("cvDataId") Long cvDataId, @RequestParam("file") MultipartFile file) {
        return cvDataService.savePhoto(cvDataId, file);
    }

    @DeleteMapping("/photo/{cvDataId}")
    public boolean deletePhoto(@PathVariable("cvDataId") Long cvDataId) {
        return cvDataService.deletePhoto(cvDataId);
    }

    @GetMapping(path = "/generate/{cvDataId}", produces = "application/vnd.openxmlformats-officedocument.wordprocessingml.document")
    public ResponseEntity<Resource> download(@PathVariable("cvDataId") Long cvDataId) throws IOException {
        ByteArrayResource resource = cvDataService.generateCV(cvDataId);
        return ResponseEntity.ok()
                .contentLength(resource.contentLength())
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + "CV.docx")
                .body(resource);
    }
}
