package adamsdd.webcvgenerator.service

import adamsdd.webcvgenerator.domain.user.User
import adamsdd.webcvgenerator.dto.cv.CVDataDto
import adamsdd.webcvgenerator.provider.user.UserProvider
import adamsdd.webcvgenerator.repository.user.UserRepository
import adamsdd.webcvgenerator.service.cv.CVDataService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

@SpringBootTest
class CVDataServiceTest extends Specification {

    private User user = UserProvider.user()

    @Autowired
    private CVDataService cvDataService
    @Autowired
    private UserRepository userRepository

    void "Should create new CV Data for user"() {
        given: "user was saved in database"
        user
        user = userRepository.save(user)

        when: "new cv data is created"
        CVDataDto cvData = cvDataService.createCVData(user.id)

        then: "no exception thrown"
        noExceptionThrown()
        and: "cv data is existing"
        cvData != null
    }
}
