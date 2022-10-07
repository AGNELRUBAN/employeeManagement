
package com.ideas2it.employee.service;

import com.ideas2it.employee.model.Trainer;
import com.ideas2it.employee.model.Role;
import com.ideas2it.employee.model.Qualification;

import com.ideas2it.employee.exception.EmployeeNotFound;

import java.util.ArrayList;
import java.util.List;


/**
 * <h1> Trainer Service Interface </h1>
 * <p>
 * It implements only method declarations.
 * </P>
 * version 1.0
 * @author ruban 11/08/22
 **/
public interface TrainerService {

    List<Integer> addTrainer(final Trainer trainer, final String employeeName, final String gender,
                                    final String emailId,
                                    final String validDateOfBirth, final String dateOfJoining,
                                    final String address, final String phoneNumber,
                                    final String adhaarNumber, final String department,
                                    final String trainerExperience, final String qualification);

    List<Trainer> getTrainers();

    boolean deleteByTrainerId(int empId);

    Trainer retrieveTrainerById(int empId) throws EmployeeNotFound;
}
