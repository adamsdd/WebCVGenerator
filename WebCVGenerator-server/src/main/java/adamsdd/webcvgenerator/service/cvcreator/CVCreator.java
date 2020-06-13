package adamsdd.webcvgenerator.service.cvcreator;

import adamsdd.webcvgenerator.domain.cv.CVData;
import adamsdd.webcvgenerator.domain.cv.SchoolType;
import adamsdd.webcvgenerator.domain.cv.Skill;
import org.apache.poi.xwpf.usermodel.*;
import org.apache.xmlbeans.XmlException;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.openpackaging.parts.Part;
import org.docx4j.openpackaging.parts.PartName;
import org.docx4j.openpackaging.parts.Parts;
import org.docx4j.openpackaging.parts.WordprocessingML.BinaryPart;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBody;
import org.springframework.core.io.ByteArrayResource;

import java.io.*;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class CVCreator {

    private String templatePath;
    private CVData cvData;

    public CVCreator(String templatePath, CVData cvData) {
        this.templatePath = templatePath;
        this.cvData = cvData;
    }

    public ByteArrayResource generateDocx() {
        try {
            File file = new File(templatePath);
            WordprocessingMLPackage wordMLPackage = WordprocessingMLPackage
                    .load(new File(file.getAbsolutePath()));

            replaceImg(wordMLPackage);
            ByteArrayOutputStream byteOutputStream = new ByteArrayOutputStream();
            wordMLPackage.save(byteOutputStream);
            InputStream inputStream = new ByteArrayInputStream(byteOutputStream.toByteArray());
            XWPFDocument document = new XWPFDocument(inputStream);

            setTableValues(document.getTables());

            CTBody resultBody = replaceStaticElementsFromBody(document.getDocument().getBody());
            document.getDocument().setBody(resultBody);

            ByteArrayOutputStream cvData = new ByteArrayOutputStream();
            document.write(cvData);
            return new ByteArrayResource(cvData.toByteArray());
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalStateException(e);
        }
    }

    private void setTableValues(List<XWPFTable> tables) {
        for (XWPFTable table : tables) {
            if (table.getText().contains(CVTemplateConstants.EDU_FROM)) {
                setEducation(table);
            }
            if (table.getText().contains(CVTemplateConstants.EXP_FROM)) {
                setJobExperience(table);
            }
            if (table.getText().contains(CVTemplateConstants.SKILL)) {
                setSkills(table);
            }
        }
    }

    private void replaceImg(WordprocessingMLPackage wordMLPackage) {
        Parts parts = wordMLPackage.getParts();

        HashMap partsMap = parts.getParts();
        PartName partName = null;
        Part part = null;

        Set set = partsMap.keySet();
        for (Iterator iterator = set.iterator(); iterator.hasNext(); ) {
            PartName name = (PartName) iterator.next();
            if (name.getName().equalsIgnoreCase("/word/media/image1.jpeg")) {
                part = (Part) partsMap.get(name);
                partName = name;
            }

        }
        if (part != null && partName != null && cvData.photo != null) {
            part = (Part) partsMap.get(partName);
            BinaryPart binaryPart = (BinaryPart) part;
            binaryPart.setBinaryData(cvData.photo);
        }
    }

    private void setEducation(XWPFTable table) {

        for (int rowIterator = 0; rowIterator < cvData.education.size(); rowIterator++) {
            XWPFTableRow row = table.insertNewTableRow(CVTemplateConstants.EDUCATION_START_ROW + rowIterator);
            for (int cellIterator = 0; cellIterator <= CVTemplateConstants.EDUCATION_COLUMNS; cellIterator++) {
                XWPFTableCell cell = row.addNewTableCell();
                XWPFRun run = cell.addParagraph().insertNewRun(0);
                switch (cellIterator) {
                    case 0:
                        run.setText(getStringFromDate(cvData.education.get(rowIterator).dateFrom));
                        break;
                    case 1:
                        run.setText(getStringFromDate(cvData.education.get(rowIterator).dateTo));
                        break;
                    case 2:
                        run.setText(emptyStringOrValue(cvData.education.get(rowIterator).name));
                        break;
                    case 3:
                        run.setText(emptyStringOrValue(cvData.education.get(rowIterator).country));
                        break;
                    case 4:
                        run.setText(getSchoolType(cvData.education.get(rowIterator).schoolType));
                        break;
                }
            }
        }

        table.removeRow(1);
    }

    private String getSchoolType(SchoolType schoolType) {
        if (schoolType != null) {
            return schoolType.name;
        } else {
            return "";
        }
    }

    private void setJobExperience(XWPFTable table) {

        for (int rowIterator = 0; rowIterator < cvData.jobExperiences.size(); rowIterator++) {
            XWPFTableRow row = table.insertNewTableRow(CVTemplateConstants.JOB_EXPERIENCE_START_ROW + rowIterator);
            for (int cellIterator = 0; cellIterator <= CVTemplateConstants.JOB_EXPERIENCE_COLUMNS; cellIterator++) {
                XWPFTableCell cell = row.addNewTableCell();
                XWPFRun run = cell.addParagraph().insertNewRun(0);
                switch (cellIterator) {
                    case 0:
                        run.setText(getStringFromDate(cvData.jobExperiences.get(rowIterator).dateFrom));
                        break;
                    case 1:
                        run.setText(getStringFromDate(cvData.jobExperiences.get(rowIterator).dateTo));
                        break;
                    case 2:
                        run.setText(emptyStringOrValue(cvData.jobExperiences.get(rowIterator).firm));
                        break;
                    case 3:
                        run.setText(emptyStringOrValue(cvData.jobExperiences.get(rowIterator).description));
                        break;
                }
            }
        }

        table.removeRow(1);
    }

    private String getStringFromDate(LocalDate localDate) {
        String stringDate = "";
        if (localDate != null) {
            stringDate = localDate.toString();
        }

        return stringDate;
    }

    private String emptyStringOrValue(String value) {
        String result = "";
        if (value != null) {
            return value;
        } else {
            return result;
        }
    }

    private void setSkills(XWPFTable table) {
        for (int rowIterator = 0; rowIterator < cvData.skills.size(); rowIterator++) {
            XWPFTableRow row = table.insertNewTableRow(CVTemplateConstants.SKILLS_START_ROW + rowIterator);
            for (int cellIterator = 0; cellIterator <= CVTemplateConstants.SKILLS_COLUMNS; cellIterator++) {
                XWPFTableCell cell = row.addNewTableCell();
                XWPFRun run = cell.addParagraph().insertNewRun(0);
                switch (cellIterator) {
                    case 0:
                        run.setText(getSkill(cvData.skills.get(rowIterator)));
                        break;
                    case 1:
                        run.setText(getSkillLevel(cvData.skills.get(rowIterator)));
                        break;
                }
            }
        }

        table.removeRow(1);
    }

    private String getSkill(Skill skill) {
        String skillName = "";
        if (skill.name != null) {
            skillName = skill.name;
        }

        return skillName;
    }

    private String getSkillLevel(Skill skill) {
        String skillLevel = "";
        if (skill.level != null) {
            skillLevel = skill.level.name();
        }

        return skillLevel;
    }

    private CTBody replaceStaticElementsFromBody(CTBody body) throws XmlException {
        String srcString = body.xmlText();
        String resultString = replaceName(srcString);
        resultString = replacePhone(resultString);
        resultString = replaceHobby(resultString);
        resultString = replaceBirthDate(resultString);
        resultString = replaceCity(resultString);

        return CTBody.Factory.parse(resultString);
    }

    private String replaceName(String srcString) {
        return srcString.replace(CVTemplateConstants.NAME, getFullName());
    }

    private String getFullName() {
        String name = " ";
        String surname = " ";
        if (cvData.basicInfo.name != null) {
            name = cvData.basicInfo.name;
        }
        if (cvData.basicInfo.surname != null) {
            surname = cvData.basicInfo.surname;
        }

        return name + " " + surname;
    }

    private String replacePhone(String srcString) {
        String phone = "";
        if (cvData.basicInfo.phone != null) {
            phone = cvData.basicInfo.phone;
        }

        return srcString.replace(CVTemplateConstants.PHONE, phone);
    }

    private String replaceCity(String srcString) {
        String city = "";
        if (cvData.basicInfo.city != null) {
            city = cvData.basicInfo.city;
        }

        return srcString.replace(CVTemplateConstants.CITY, city);
    }

    private String replaceHobby(String srcString) {
        String hobby = "";
        if (cvData.basicInfo.hobby != null) {
            hobby = cvData.basicInfo.hobby;
        }

        return srcString.replace(CVTemplateConstants.HOBBY, hobby);
    }

    private String replaceBirthDate(String srcString) {
        String birthDate = "";
        if (cvData.basicInfo.birthDate != null) {
            birthDate = cvData.basicInfo.birthDate.toString();
        }

        return srcString.replace(CVTemplateConstants.BIRTH, birthDate);
    }

//    private void exportDocx(XWPFDocument document) {
//        try {
//            FileOutputStream fileOutputStream = new FileOutputStream(resultPath);
//            document.write(fileOutputStream);
//
//
//            fileOutputStream.close();
//            document.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//            throw new IllegalStateException("Cannot export docx", e);
//        }
//    }
}
