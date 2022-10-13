package com.ideas2it.employee.model;

import javax.persistence.*;
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
@Entity
@Table(name = "trainee")
public class Trainee extends Employee {
    @Column(name = "salary")
    private int salary;
    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "employee_relation",
            joinColumns = @JoinColumn(name = "trainee_id", referencedColumnName = "emp_id"),
            inverseJoinColumns = @JoinColumn(name = "trainer_id", referencedColumnName = "emp_id"))
    private Set<Trainer> trainers;
    @Transient
    private List<Integer> trainersId;

    public Trainee() {
    }

    public Trainee(Employee employee, int salary, Set<Trainer> trainers) {
       // this.employee = employee;
        this.salary = salary;
        this.trainers = trainers;
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

    public void setTrainers(Set<Trainer> trainers) {
        this.trainers = trainers;
    }

    public Set<Trainer> getTrainers() {
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