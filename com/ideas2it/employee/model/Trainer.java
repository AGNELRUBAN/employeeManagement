package com.ideas2it.employee.model;

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
     * @param {@link byte} trainerExperience
     * @return It return trainer object with parameters
     **/
   /* public Trainer (String employeeName, int id, String gender, String qualification,
                    String emailId, LocalDate dateOfBirth, String dateOfJoining,
                    String address, String phoneNumber, String adhaarNumber, String department, byte trainerExperience) {
        super (employeeName, id, gender, qualification, emailId, dateOfBirth, dateOfJoining, address, phoneNumber,
               adhaarNumber, department);
        this.trainerExperience = trainerExperience;
    }*/

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
        return "\n" + "\nTrainer Details"+ "\n"+"\n" +"Employee Name      :" + employee.getEmployeeName()+ "\n" + "Emp Id             :" +
                       employee.getId()+ "\n"+"Gender             :" + employee.getGender()+"\n" +"Qualification      :" + employee.getQualification()+"\n"+
                       "Email Id           :" +employee.getEmailId() +"\n" +"Date of Joining    :" + employee.getDateOfJoining() +
                       "\n"+"Address            :" +employee.getAddress()+"\n" + "Phone Number       :" + employee.getPhoneNumber()+
                       "\n"+"Adhaar Number      :" + employee.getAdhaarNumber()+"\n" +"Department         :" + employee.getDepartment() +
                       "\n"+"Trainer Experience :" + getTrainerExperience();

    }
}