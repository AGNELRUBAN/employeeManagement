package com.ideas2it.employee.model;

import java.time.LocalDate;

/**
 * <h1> Employee </h1>
 * <p>
 * It has getter, setter methods
 * </p>
 * version 1.0
 * @author ruban 11/08/22
 **/
public class Employee {
    private String employeeName;
    private int id;
    private String gender;
    private String emailId;
    private LocalDate dateOfBirth;
    private String dateOfJoining;
    private String address;
    private String phoneNumber;
    private String adhaarNumber;
    private String department;
    private Trainer trainer;
    private Role role;
    private Qualification qualification;

    public Employee() {
    }

    /**
     * <p>
     * Receives variable values and set it to current class variables.
     * </p>
     *
     * @param {@link String} employeeName
     * @param {@link int} id
     * @param {@link String} age
     * @param {@link String} gender
     * @param {@link String} qualification
     * @param {@link String} emailId
     * @param {@link LocalDate} dateOfBirth
     * @param {@link String} dateOfJoining
     * @param {@link String} address
     * @param {@link String} phoneNumber
     * @param {@link String} adhaarNumber
     * @param {@link String} department
     * @return it returns all employee variables 
     **/
    public Employee(String employeeName, String gender, String emailId,
                    LocalDate dateOfBirth, String dateOfJoining, String address,
                    String phoneNumber, String adhaarNumber, String department, 
                    Role role, Qualification qualification) {
        this.employeeName = employeeName;
        this.id = id;
        this.gender= gender;
	this.qualification = qualification;
        this.emailId = emailId;
	this.dateOfBirth = dateOfBirth;
	this.dateOfJoining = dateOfJoining;
	this.address = address;
	this.phoneNumber = phoneNumber;
	this.adhaarNumber = adhaarNumber;
	this.department = department;
        this.role = role;
    }

    public String getEmployeeName() {
	return employeeName;
    }

    public void setEmployeeName(String employeeName) {
	this.employeeName = employeeName;
    }

    public int getId() {
         return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

     public Qualification getQualification() {
	return qualification;
    }

    public void setQualification(Qualification qualification) {
	this.qualification = qualification;
    }

     public String getEmailId() {
	return emailId;
    }

    public void setEmailId(String emailId) {
	this.emailId = emailId;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
	this.dateOfBirth = dateOfBirth;
    }

    public LocalDate getDateOfBirth() {
	return dateOfBirth;
    }

    public String getDateOfJoining() {
	return dateOfJoining;
    }

    public void setDateOfJoining(String dateOfJoining) {
	this.dateOfJoining = dateOfJoining;
    }

    public String getAddress() {
	return address;
    }

    public void setAddress(String address) {
	this.address = address;
    }

    public String getPhoneNumber() {
	return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
	this.phoneNumber = phoneNumber;
    }

    public String getAdhaarNumber() {
	return adhaarNumber; 
    }

    public void setAdhaarNumber(String adhaarNumber) {
	this.adhaarNumber = adhaarNumber;
    }

    public String getDepartment() {
	return department;
    }

    public void setDepartment(String department) {
	this.department = department;
    }

    public Trainer getTrainer() {
	return trainer;
    }

    public void setTrainer(Trainer trainer) {
	this.trainer = trainer;
    }

    public Role getRole() {
	return role;
    }

    public void setRole(Role role) {
	this.role = role;
    }
}