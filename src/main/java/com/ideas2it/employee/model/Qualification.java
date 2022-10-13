package com.ideas2it.employee.model;

import javax.persistence.*;

/**
 * <h1> Qualification </h1>
 * <p>
 * It has getter, setter methods
 * </p>
 * version 1.0
 * @author ruban 11/08/22
 **/
@Entity
@Table(name = "qualification")
public class Qualification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="qualification_id")
    private int qualificationId;
    @Column(name = "description")
    private String description;

    public Qualification() {
    }

    public Qualification(String description) {
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

