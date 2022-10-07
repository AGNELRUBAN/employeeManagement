package com.ideas2it.employee.service;

import com.ideas2it.employee.model.Trainee;
import com.ideas2it.employee.model.Role;
import com.ideas2it.employee.model.Qualification;

import com.ideas2it.employee.exception.EmployeeNotFound;

import java.util.ArrayList;
import java.util.List;

/**
 * <h1> Trainee Service Interface </h1>
 * <p>
 * This class implements only method declarations.
 * </P>
 * version 1.0
 * @author ruban 11/08/22
 **/
public interface TraineeService {

    List<Integer> addTrainee(Trainee trainee, final String employeeName, final String gender,
                             final String emailId,
                             final String validDateOfBirth, final String dateOfJoining,
                             final String address, final String phoneNumber,
                             final String adhaarNumber, final String department,
                             final String salary, final String qualification, final List<String> trainersId);

    List<Trainee> getTrainees();

    boolean deleteByTraineeId(int empId) throws EmployeeNotFound;

    Trainee retrieveTraineeById(int empId) throws EmployeeNotFound;
}
