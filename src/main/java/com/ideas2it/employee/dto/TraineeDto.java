package com.ideas2it.employee.dto;

import java.util.List;
import java.util.Set;

/**
 * <h1> Trainee </h1>
 * <p>
 * It is pojo class and it extends another class
 * </p>
 * version 1.0
 * @author ruban 11/08/22
 **/

public class TraineeDto extends EmployeeDto {

    private int salary;

    private Set<TrainerDto> trainers;

    private List<Integer> trainersId;

    private String trainersName;

    public TraineeDto() {
    }

    public TraineeDto(EmployeeDto employee, int salary, Set<TrainerDto> trainers) {
        // this.employee = employee;
        this.salary = salary;
        this.trainers = trainers;
    }

    public String getTrainersName() {
        return trainersName;
    }

    public void setTrainersName(String trainersName) {
        this.trainersName = trainersName;
    }


    public void setSalary(int salary) {
        this.salary = salary;
    }

    public int getSalary() {
        return salary;
    }

    public void setTrainersId(List<Integer> trainersId) {
        this.trainersId = trainersId;
    }

    public List<Integer> getTrainersId() {
        return trainersId;
    }

    public void setTrainers(Set<TrainerDto> trainers) {
        this.trainers = trainers;
    }

    public Set<TrainerDto> getTrainers() {
        return trainers;
    }

    /**
     * <p>
     * This method prints the value.
     * </p>
     *
     **/

    @Override
    public String toString() {
        return "Trainee{" + "salary=" + salary +
                ", trainersId=" + trainersId +
                ", trainers=" + trainers + '}';
    }
}