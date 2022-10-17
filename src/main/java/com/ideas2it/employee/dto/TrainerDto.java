package com.ideas2it.employee.dto;

import java.time.LocalDate;
import java.util.Set;

/**
 * <h1> Trainer </h1>
 * <p>
 * It is pojo class and it extends another class
 * </p>
 * version 1.0
 * @author ruban 11/08/22
 *
 **/
public class TrainerDto extends EmployeeDto {

    private byte trainerExperience;
    private Set<TraineeDto> trainees;

    public TrainerDto() {
    }

    public TrainerDto(int id, String employeeName, String gender, String emailId, LocalDate dateOfBirth,
                   LocalDate dateOfJoining, String address, String phoneNumber, String adhaarNumber,
                   String department, QualificationDto qualification, RoleDto role, byte trainerExperience) {
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

    public void setTrainees(Set<TraineeDto> trainees) {
        this.trainees = trainees;
    }

    public Set<TraineeDto> getTrainees() {
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