package com.ideas2it.employee.model;

import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;

/**
 * <h1> Trainee </h1>
 * <p>
 * It is pojo class and it extends another class
 * </p>
 * version 1.0
 * @author ruban 11/08/22
 **/
public class Trainee extends Employee {
    private int salary;
    private int traineeId;
    private Employee employee;
    private Set<Trainer> trainers;
    private List<Integer> trainersId;

    public Trainee() {
    }

    public Trainee(Employee employee, int salary, List<Integer> trainersId) {
        this.employee = employee;
        //this.trainerNames = trainerNames;
        this.salary = salary;
        this.trainersId = trainersId;
    }

    /**
     * <p>
     * This method sets the variables to current class varaibles.
     * </p>
     *
     * @param {@link String} employeeName
     * @param {@link int} id
     * @param {@link String} gender
     * @param {@link String} qualification
     * @param {@link String} emailId
     * @param {@link LocalDate} dateOfBirth
     * @param {@link String} dateOfJoining
     * @param {@link String} address
     * @param {@link String} phoneNumber
     * @param {@link String} adhaarNumber
     * @param {@link String} department
     * @param {@link int} salary
     * @param {@link List<String>} trainerNames
     * @return It return trainee object with parameters
     **/
   /* public Trainee (String employeeName,int id, String gender, String qualification, String emailId,
                    LocalDate dateOfBirth, String dateOfJoining, String address,
                    String phoneNumber, String adhaarNumber, String department, int salary, List<String> trainerNames) {
        super (employeeName, id, gender, qualification, emailId, dateOfBirth, dateOfJoining,
               address, phoneNumber, adhaarNumber, department);
        this.salary = salary;
        this.trainerNames = trainerNames;
    }*/

    public void setTraineeId(int traineeId) {
       this.traineeId = traineeId;
    }

    public int getTraineeId() {
	return traineeId;
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

    public void setEmployee(Employee employee) {
       this.employee = employee;
    }

    public Employee getEmployee() {
	return employee;
    }

  /*  public void setTrainerNames(List<String> trainerNames) {
        this.trainerNames = trainerNames;
    }

    public List<String> getTrainerNames() {
        return trainerNames;
    }*/

   /* public void setTrainers(List<Trainer> trainers) {
        this.trainers = trainers;
    }

    public List<Trainer> getTrainers() {
        return trainers;
    }*/
 
    /**
     * <p>
     * This method prints the value.
     * </p>
     * 
     **/
   @Override
    public String toString() {
	return "\n" +"\nTrainee Details" +"\n"+"\n"+"Employee Name   :" + employee.getEmployeeName() + "\n" +"Emp Id          :" + 
                       employee.getId() +"\n"+ "Gender          :" + employee.getGender()+"\n"+"Qualification   :"+
                      employee.getQualification() +"\n"+  "Email Id        :" + employee.getEmailId() +"\n"+
                      "Date of Joining :" + 
                      employee.getDateOfJoining()+ "\n"  + "Address         :" +employee.getAddress()+"\n" + "Phone Number    :"+ 
                      employee.getPhoneNumber()+"\n" + "Adhaar Number   :" + employee.getAdhaarNumber()+"\n" +"Department      :"+ 
                      employee.getDepartment() + "\n" + "Salary          :" + getSalary();

   }
}