package com.ideas2it.employee.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

/**
 * <h1> Trainer </h1>
 * <p>
 * It is pojo class and it extends another class
 * </p>
 * version 1.0
 * @author ruban 11/08/22
 **/
@Entity
@Table(name = "trainer")
public class Trainer extends Employee {
    @Column(name = "trainer_experience")
    private byte trainerExperience;
    @ManyToMany(mappedBy = "trainers")
    private Set<Trainee> trainees;

    public Trainer() {
    }

    public Trainer(int id, String employeeName, String gender, String emailId, LocalDate dateOfBirth,
                   LocalDate dateOfJoining, String address, String phoneNumber, String adhaarNumber,
                   String department, Qualification qualification, Role role, byte trainerExperience) {
        super(id, employeeName, gender, emailId, dateOfBirth, String.valueOf(dateOfJoining), address, phoneNumber,
                adhaarNumber, department, qualification, role);
        this.trainerExperience = trainerExperience;
    }

    public void setTrainerExperience(byte trainerExperience) {
        this.trainerExperience = trainerExperience;
    }

    public byte getTrainerExperience() {
        return trainerExperience;
    }

    public void setTrainees(Set<Trainee> trainees) {
        this.trainees = trainees;
    }

    public Set<Trainee> getTrainees() {
        return trainees;
    }



    /**
     * <p>
     * This method prints the value.
     * </p>
     *
     **/
    @Override
    public String toString() {
        return "Trainer{" +  "trainingExperience=" + trainerExperience +
                ", trainees=" + trainees + '}';
    }
}