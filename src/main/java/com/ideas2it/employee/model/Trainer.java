package com.ideas2it.employee.model;

import java.util.Set;

/**
 * <h1> Trainer </h1>
 * <p>
 * It is pojo class and it extends another class
 * </p>
 * version 1.0
 * @author ruban 11/08/22
 **/
public class Trainer extends Employee {
    private byte trainerExperience;
    private int trainerId;
    private Employee employee;
    private Set<Trainee> trainees;

    public Trainer() {
    }

    public Trainer(Employee employee, byte trainerExperience) {
        this.employee = employee;
        this.trainerExperience = trainerExperience;
    }

    public void setTrainerExperience(byte trainerExperience) {
        this.trainerExperience = trainerExperience;
    }

    public byte getTrainerExperience() {
        return trainerExperience;
    }

    public void setTrainerId(int trainerId) {
        this.trainerId = trainerId;
    }

    public int getTrainerId() {
        return trainerId;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Employee getEmployee() {
        return employee;
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
        return employee.getId() + " - " + employee.getEmployeeName();

    }
}