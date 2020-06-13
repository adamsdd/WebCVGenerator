package adamsdd.webcvgenerator.service

import adamsdd.webcvgenerator.domain.cv.CVData
import adamsdd.webcvgenerator.provider.cv.CVDataProvider
import adamsdd.webcvgenerator.service.cvcreator.CVCreator
import adamsdd.webcvgenerator.service.cvcreator.CVTemplate
import org.springframework.core.io.ByteArrayResource
import spock.lang.Specification

class CVCreatorTest extends Specification {

    void "Should generate CV file"() {
        given: "CV data"
        CVData cvData = CVDataProvider.cvData()
        and: "Basic CV template"
        String basicCvTemplatePath = CVTemplate.BASIC_TEMPLATE_PATH
        and: "CV creator"
        CVCreator cvCreator = new CVCreator(basicCvTemplatePath, cvData)

        when: "cv is generated"
        ByteArrayResource byteArrayResource = cvCreator.generateDocx()

        then: "no exception thrown"
        noExceptionThrown()
        and: "resource was generated"
        byteArrayResource.contentLength() > 0
    }
}
