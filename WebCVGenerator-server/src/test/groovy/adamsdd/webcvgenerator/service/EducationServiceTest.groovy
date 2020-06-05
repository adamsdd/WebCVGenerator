package adamsdd.webcvgenerator.service

import adamsdd.webcvgenerator.domain.cv.CVData
import adamsdd.webcvgenerator.domain.cv.Education
import adamsdd.webcvgenerator.domain.user.User
import adamsdd.webcvgenerator.dto.cv.CVDataDto
import adamsdd.webcvgenerator.dto.cv.EducationDto
import adamsdd.webcvgenerator.provider.cv.EducationProvider
import adamsdd.webcvgenerator.provider.user.UserProvider
import adamsdd.webcvgenerator.repository.user.UserRepository
import adamsdd.webcvgenerator.service.cv.CVDataService
import adamsdd.webcvgenerator.service.cv.EducationService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

@SpringBootTest
class EducationServiceTest extends Specification{

    @Autowired
    private final EducationService educationService
    @Autowired
    private final CVDataService cvDataService
    @Autowired
    private final UserRepository userRepository

    private final User user = UserProvider.user()
    private final String EXPECTED_CHANGED_NAME = "Polsko Japonska"
    private CVData savedCVData


    void "Should create new education"() {
        given: "saved cv data"
        savedCVData

        when: "new education is created"
        EducationDto educationDto = EducationProvider.educationDto(savedCVData)
        EducationDto createdEducationDto = educationService.create(educationDto)

        then: "no exception thrown"
        noExceptionThrown()
        and: "education is existing"
        createdEducationDto != null
        and: "education has ID number"
        createdEducationDto.id != null
        and: "education has correct saved data's ID"
        createdEducationDto.cvDataId == savedCVData.id
    }

    void "Should update education"() {
        given: "saved cv data with education"
        EducationDto createdEducationDto = educationService.create(EducationProvider.educationDto(savedCVData))
        Education newEducation = educationService.getEducation(createdEducationDto.id)
        savedCVData = cvDataService.getCVData(savedCVData.id)

        when: "education is changed"
        newEducation.name = "Polsko Japonska"
        and: "education is updated"
        educationService.update(newEducation.dto())

        then: "no exception thrown"
        noExceptionThrown()
        and: "education is updated"
        EducationDto updatedEducation = educationService.getEducationDto(createdEducationDto.id)
        and: "education has expected name after update"
        updatedEducation.name == EXPECTED_CHANGED_NAME
    }

    def setup() {
        userRepository.save(user)
        CVDataDto cvDataDto = cvDataService.createCVData(user.id)
        savedCVData = cvDataService.getCVData(cvDataDto.id)
    }
}
