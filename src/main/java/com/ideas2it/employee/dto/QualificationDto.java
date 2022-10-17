package com.ideas2it.employee.dto;

import javax.persistence.*;

/**
 * <h1> Qualification </h1>
 * <p>
 * It has getter, setter methods
 * </p>
 * version 1.0
 * @author ruban 11/08/22
 **/

public class QualificationDto {
    private int qualificationId;

    private String description;

    public QualificationDto() {
    }

    public QualificationDto(String description) {
        this.description = description;
    }

    public void setQualificationId(int qualificationId) {
        this.qualificationId = qualificationId;
    }

    public int getQualificationId() {
        return qualificationId;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public String toString() {
        return description;
    }
}

