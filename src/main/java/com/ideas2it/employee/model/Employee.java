package com.ideas2it.employee.model;


//import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * <h1> Employee </h1>
 * <p>
 * It has getter, setter methods
 * </p>
 * version 1.0
 * @author ruban 11/08/22
 **/

@Entity
@Table(name = "employee")
@Inheritance(strategy=InheritanceType.JOINED)
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "emp_id")
    private int id;
    @Column (name = "employee_name")
    private String employeeName;

    @Column(name = "gender")
    private String gender;
    @Column(name = "email_id")
    private String emailId;
    @Column(name = "date_of_birth")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfBirth;
    @Column(name ="date_of_joining")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String dateOfJoining;
    @Column(name = "address")
    private String address;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column(name = "adhaar_number")
    private String adhaarNumber;
    @Column(name = "department")
    private String department;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "role_id", referencedColumnName = "role_id")
    private Role role;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "qualification_id", referencedColumnName = "qualification_id")
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
    public Employee(int id, String employeeName, String gender, String emailId,
                    LocalDate dateOfBirth, String dateOfJoining, String address,
                    String phoneNumber, String adhaarNumber, String department,
                    Qualification qualification, Role role) {
        this.id = id;
        this.employeeName = employeeName;
        this.gender = gender;
        this.emailId = emailId;
        this.dateOfBirth = dateOfBirth;
        this.dateOfJoining = dateOfJoining;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.adhaarNumber = adhaarNumber;
        this.department = department;
        this.qualification = qualification;
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


    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}