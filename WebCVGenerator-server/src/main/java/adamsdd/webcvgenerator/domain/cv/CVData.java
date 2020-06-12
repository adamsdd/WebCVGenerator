package adamsdd.webcvgenerator.domain.cv;

import adamsdd.webcvgenerator.domain.user.User;
import adamsdd.webcvgenerator.dto.cv.CVDataDto;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Entity
public class CVData {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    public Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    public User user;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "PK_ID")
    public BasicInfo basicInfo;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "CV_DATA_ID")
    public List<Education> education = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "CV_DATA_ID")
    public List<JobExperience> jobExperiences = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "CV_DATA_ID")
    public List<Skill> skills = new ArrayList<>();

    @Lob
    @Column(name = "photo", columnDefinition="BLOB")
    public byte[] photo;

    public CVData() {
    }

    public CVData(User user) {
        this.user = user;
        this.basicInfo = new BasicInfo();
        this.basicInfo.cvData = this;
    }

    public CVData(Long id, User user, BasicInfo basicInfo, List<Education> education, List<JobExperience> jobExperiences,
                  List<Skill> skills, byte[] photo) {
        this.id = id;
        this.user = user;
        this.basicInfo = basicInfo;
        this.education.addAll(education);
        this.jobExperiences.addAll(jobExperiences);
        this.skills.addAll(skills);
        this.photo = photo;
    }

    @Override
    public String toString() {
        return "CVData{" +
                "id=" + id +
                ", basicInfo=" + basicInfo +
                ", education=" + education +
                ", jobExperiences=" + jobExperiences +
                ", skills=" + skills +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CVData cvData = (CVData) o;
        return Objects.equals(id, cvData.id) &&
                Objects.equals(basicInfo, cvData.basicInfo) &&
                Objects.equals(education, cvData.education) &&
                Objects.equals(jobExperiences, cvData.jobExperiences) &&
                Objects.equals(skills, cvData.skills);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, basicInfo, education, jobExperiences, skills);
    }

    public CVDataDto dto() {
        return new CVDataDto(id,
                basicInfo.dto(),
                education.stream().map(Education::dto).collect(Collectors.toList()),
                jobExperiences.stream().map(JobExperience::dto).collect(Collectors.toList()),
                skills.stream().map(Skill::dto).collect(Collectors.toList()));
    }
}
