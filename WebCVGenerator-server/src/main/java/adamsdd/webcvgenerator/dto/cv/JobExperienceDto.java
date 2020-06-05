package adamsdd.webcvgenerator.dto.cv;


import java.time.LocalDate;

public class JobExperienceDto {

    public final Long id;
    public final LocalDate from;
    public final LocalDate to;
    public final String firm;
    public final boolean currently;
    public final Long cvDataId;

    public JobExperienceDto(Long id, LocalDate from, LocalDate to, String firm, boolean currently, Long cvDataId) {
        this.id = id;
        this.from = from;
        this.to = to;
        this.firm = firm;
        this.currently = currently;
        this.cvDataId = cvDataId;
    }
}
