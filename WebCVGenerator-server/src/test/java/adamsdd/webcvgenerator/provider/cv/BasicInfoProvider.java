package adamsdd.webcvgenerator.provider.cv;

import adamsdd.webcvgenerator.domain.cv.BasicInfo;
import adamsdd.webcvgenerator.domain.cv.CVData;

import java.time.LocalDate;

public class BasicInfoProvider {

    public static BasicInfo basicInfo(CVData cvData) {
        return new BasicInfo(1L, "Jan", "Kowalski", LocalDate.now(), "Warszawa", "666999666" , "TV, Filmy, Seriale", cvData);
    }
}
