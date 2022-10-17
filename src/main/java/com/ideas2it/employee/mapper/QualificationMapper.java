package com.ideas2it.employee.mapper;

import com.ideas2it.employee.dto.QualificationDto;
import com.ideas2it.employee.model.Qualification;
import org.springframework.stereotype.Component;

@Component
public class QualificationMapper {

    public Qualification toQualification(QualificationDto qualificationDto) {
        Qualification qualification = new Qualification();
        qualification.setDescription(qualificationDto.getDescription());
        return qualification;
    }

    public QualificationDto toQualificationDto(Qualification qualification) {
        QualificationDto qualificationDto = new QualificationDto();
        qualificationDto.setDescription(qualification.getDescription());
        return qualificationDto;
    }
}
