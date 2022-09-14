package com.ideas2it.employee.service;

import com.ideas2it.employee.model.Trainer;
import com.ideas2it.employee.model.Role;
import com.ideas2it.employee.model.Qualification;
import com.ideas2it.employee.service.impl.TrainerServiceImpl;
import com.ideas2it.employee.exception.EmployeeNotFound;

import java.util.ArrayList;
import java.util.List;
import java.time.Period;
import java.util.InputMismatchException;
import java.time.format.DateTimeParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * <h1> Trainer Service Interface </h1>
 * <p> 
 * It implements only method declarations.
 * </P>
 * version 1.0
 * @author ruban 11/08/22
 **/
public interface TrainerService {
    
    List<Integer> addTrainer(final String employeeName, final String gender,
                             final String emailId,
                             final String dateOfBirth, final String dateOfJoining,
                             final String address, final String phoneNumber,
                             final String adhaarNumber, final String department, 
                             final byte trainerExperience, final Role role, final Qualification qualification);

    List<Trainer> getTrainers();

    boolean deleteByTrainerId(int empId) throws EmployeeNotFound;
  
    public Trainer retrieveTrainerById(int empId) throws EmployeeNotFound;

    public void updateEmail(Trainer trainer, String mail);

    public void updateNumber(Trainer trainer, String number);

    public void updateAddress(Trainer trainer, String address);
}
    