package com.ideas2it.employee.service.impl;

import com.ideas2it.employee.exception.BadRequest;
import com.ideas2it.employee.model.Role;
import com.ideas2it.employee.model.Trainer;
import com.ideas2it.employee.dao.TraineeDao;
import com.ideas2it.employee.utility.StringUtility;
import com.ideas2it.employee.model.Trainee;
import com.ideas2it.employee.service.TraineeService;
import com.ideas2it.employee.service.TrainerService;

import java.util.ArrayList;
import java.util.List;
import java.util.HashSet;
import java.util.Set;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <h1> Trainee Service </h1>
 * <p>
 * It gets Input and sends it for CRUD operations.
 * </P>
 * version 1.0
 * @author ruban 11/08/22
 **/
@Component
public class TraineeServiceImpl implements TraineeService {
    @Autowired
    public TraineeDao traineeDao;

    @Autowired
    public TrainerService trainerService;
    private static final Logger logger = LogManager.getLogger(TraineeServiceImpl.class);

    /**
     * <p>
     * It gets Trainee Input from controller through parameter and sends it to utility for validation, if validation done
     * it will set in trainee object otherwise resend it to controller for particluar method.
     * </p>
     * @param {@link String} employeeName
     * @param {@link String} gender
     * @param {@link String} qualification
     * @param {@link String} emailId
     * @param {@link String} dateOfBirth
     * @param {@link String} dateOfJoining
     * @param {@link String} address
     * @param {@link String} phoneNumber
     * @param {@link String} adhaarNumber
     * @param {@link String} department
     * @param {@link List<String>} trainerNames
     * @param {@link int} salary
     * @return it returns the invalid parameter list
     *
     **/
    @Override
    public List<Integer> addTrainee(Trainee trainee) {
        List<Integer> errorFound = new ArrayList<Integer>();
        String errorFoundMessage = "";
        errorFound.clear();
        String employeeName = trainee.getEmployee().getEmployeeName();
        if (!StringUtility.isValidName(employeeName)) {
            errorFoundMessage += "\nInvalid Name\n";
            errorFound.add(5);
        }
        String emailId = trainee.getEmployee().getEmailId();
        if (!StringUtility.isValidMail(emailId)) {
            errorFoundMessage += "\nInvalid Email Id\n";
            errorFound.add(1);
        }
        String phoneNumber = trainee.getEmployee().getPhoneNumber();
        if (!StringUtility.isValidNumber(phoneNumber)) {
            errorFoundMessage += "\nInvalid Mobile Number\n";
            errorFound.add(3);
        }
        String adhaarNumber = trainee.getEmployee().getAdhaarNumber();
        if (!StringUtility.isNumberValid(adhaarNumber)) {
            errorFoundMessage += "\nInvalid Adhaar Number\n";
            errorFound.add(4);
        }

        List<Trainer> trainers = trainerService.getTrainers();
        Set<Trainer> validTrainers = new HashSet<>();
        for (Integer trainerId : trainee.getTrainersId()) {
            for (Trainer trainer : trainers) {
                if (trainerId == trainer.getEmployee().getId()) {
                    validTrainers.add(trainer);
                }
            }
        }
        trainee.setTrainers(validTrainers);
        Role role = new Role("Trainee");
        trainee.getEmployee().setRole(role);
        if (errorFound.size() == 0) {
            traineeDao.insertTrainee(trainee);
        } else {
            throw new BadRequest(errorFoundMessage, errorFound);
        }
        return errorFound;
    }

    /**
     * <p>
     * It Retrieve Trainee data from dao and sends it to controller.
     * </p>
     * @return It returns trainee List
     **/
    @Override
    public List<Trainee> getTrainees() {
        logger.info("Retrieve Trainee Method");
        return traineeDao.retrieveTrainee();
    }

    /**
     * <p>
     * It receives the employee Id from controller and sends it to Trainee Dao.
     * </p>
     * @param empId parameters
     * @return returns boolean
     **/
    @Override
    public boolean deleteByTraineeId(int empId) {
        logger.info("Delete Trainee Method");
        return traineeDao.deleteTraineeById(empId);
    }

    /**
     * <p>
     * This method receive Employee Id from controller and sends it to dao.
     * </p>
     * @param empId parameters
     * @return It returns trainee Id
     **/
    @Override
    public Trainee retrieveTraineeById(int empId)  {
        logger.info("Retrieve Trainee By Id Method");
        return traineeDao.retrieveTraineeById(empId);

    }
}
