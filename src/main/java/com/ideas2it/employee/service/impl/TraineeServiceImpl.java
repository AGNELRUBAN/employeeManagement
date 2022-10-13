package com.ideas2it.employee.service.impl;

import com.ideas2it.employee.dao.QualificationDao;
import com.ideas2it.employee.dao.RoleDao;
import com.ideas2it.employee.dao.TraineeDao;
import com.ideas2it.employee.exception.BadRequest;
import com.ideas2it.employee.model.Qualification;
import com.ideas2it.employee.model.Role;
import com.ideas2it.employee.model.Trainee;
import com.ideas2it.employee.model.Trainer;
import com.ideas2it.employee.service.TraineeService;
import com.ideas2it.employee.service.TrainerService;
import com.ideas2it.employee.utility.StringUtility;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

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
    public QualificationDao qualificationDao;

    @Autowired
    public RoleDao roleDao;
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
        String employeeName = trainee.getEmployeeName();
        if (!StringUtility.isValidName(employeeName)) {
            errorFoundMessage += "\nInvalid Name\n";
            errorFound.add(5);
        }
        String emailId = trainee.getEmailId();
        if (!StringUtility.isValidMail(emailId)) {
            errorFoundMessage += "\nInvalid Email Id\n";
            errorFound.add(1);
        }
        String phoneNumber = trainee.getPhoneNumber();
        if (!StringUtility.isValidNumber(phoneNumber)) {
            errorFoundMessage += "\nInvalid Mobile Number\n";
            errorFound.add(3);
        }
        String adhaarNumber = trainee.getAdhaarNumber();
        if (!StringUtility.isNumberValid(adhaarNumber)) {
            errorFoundMessage += "\nInvalid Adhaar Number\n";
            errorFound.add(4);
        }

        Optional<Qualification> qualification = qualificationDao.findByDescription(trainee.getQualification().getDescription());
        if (qualification.isPresent()) {
            trainee.setQualification(qualification.get());
        }

        Optional<Role> role = roleDao.findByDescription(trainee.getRole().getDescription());
        if (role.isPresent()) {
            trainee.setRole(role.get());
        }

        List<Trainer> trainers = trainerService.getTrainers();
        Set<Trainer> validTrainers = new HashSet<>();
        for (Integer trainerId : trainee.getTrainersId()) {
            for (Trainer trainer : trainers) {
                if (trainerId == trainer.getId()) {
                    validTrainers.add(trainer);
                }
            }
        }
        trainee.setTrainers(validTrainers);

        if (errorFound.size() == 0) {
            traineeDao.save(trainee);
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
        return traineeDao.findAll();
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
        traineeDao.deleteById(empId);
        return true;
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
        Trainee trainee = null;
        Optional<Trainee> trainees = traineeDao.findById(empId);
        if (trainees.isPresent()) {
            trainee = trainees.get();
        }
        List<Integer> trainersId = new ArrayList<>();
        for (Trainer trainer : trainee.getTrainers()) {
            trainersId.add(trainer.getId());
        }
        trainee.setTrainersId(trainersId);
        return trainee;

    }
}
