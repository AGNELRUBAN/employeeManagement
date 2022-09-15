package com.ideas2it.employee.service;

import com.ideas2it.employee.model.Trainee;
import com.ideas2it.employee.exception.EmployeeNotFound; 
import com.ideas2it.employee.model.Role;
import com.ideas2it.employee.model.Qualification;

import java.util.ArrayList;
import java.util.List;
import java.time.Period;
import java.util.InputMismatchException;
import java.time.format.DateTimeParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * <h1> Trainee Service Interface </h1>
 * <p> 
 * This class implements only method declarations.
 * </P>
 * version 1.0
 * @author ruban 11/08/22
 **/
public interface TraineeService {
    
    List<Integer> addTrainee(final String employeeName, final String gender,
                             final String emailId, final String dateOfBirth, final String dateOfJoining, 
                             final String address, final String phoneNumber, final String adhaarNumber,
                             final String department, final List<Integer> trainersId, final int salary, final Role role, final Qualification qualification);

    List<Trainee> getTrainees();

    deleteByTraineeId(int empId) throws EmployeeNotFound;

    Trainee retrieveTraineeById(int empId) throws EmployeeNotFound;

    boolean updateEmail(Trainee trainee, String mail);

    boolean updateNumber(Trainee trainee, String number);

    void updateAddress(Trainee trainee, String address);    
}
    