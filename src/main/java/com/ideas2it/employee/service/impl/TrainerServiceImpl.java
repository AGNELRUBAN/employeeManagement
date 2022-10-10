package com.ideas2it.employee.service.impl;

import com.ideas2it.employee.dao.TrainerDao;
import com.ideas2it.employee.exception.BadRequest;
import com.ideas2it.employee.utility.StringUtility;
import com.ideas2it.employee.model.Trainer;
import com.ideas2it.employee.model.Role;
import com.ideas2it.employee.service.TrainerService;

import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <h1> Trainer Service </h1>
 * <p>
 * It gets Trainer Input from controller and sends it to dao for CRUD operations.
 * </P>
 * version 1.0
 * @author ruban 11/08/22
 **/
@Component
public class TrainerServiceImpl implements TrainerService {
    @Autowired
    public TrainerDao trainerDao;
    private static final Logger logger = LogManager.getLogger(TrainerServiceImpl.class);

    /**
     * <p>
     * It gets Trainer Input from controller through parameter and sends it to utility for validation, if validation done
     * it will set in trainer object otherwise resend it to contriller for particluar method.
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
     * @param {@link byte} trainerExperience
     * @return It returns list with invalid parameters
     **/
    @Override
    public List<Integer> addTrainer(Trainer trainer) {
        List<Integer> errorFound = new ArrayList<Integer>();
        String errorFoundMessage = "";
        errorFound.clear();
       String employeeName = trainer.getEmployee().getEmployeeName();
        if (!StringUtility.isValidName(employeeName)) {
            errorFoundMessage += "\nInvalid Name";
            errorFound.add(5);
        }
        String emailId = trainer.getEmployee().getEmailId();
        if (!StringUtility.isValidMail(emailId)) {
            errorFoundMessage += "\nInvalid Email Id";
            errorFound.add(1);
        }
        String phoneNumber = trainer.getEmployee().getPhoneNumber();
        if (!StringUtility.isValidNumber(phoneNumber)) {
            errorFoundMessage += "\nInvalid Mobile Number";
            errorFound.add(3);
        }
        String adhaarNumber = trainer.getEmployee().getAdhaarNumber();
        if (!StringUtility.isNumberValid(adhaarNumber)) {
            errorFoundMessage += "\nInvalid Adhaar Number";
            errorFound.add(4);
        }
        Role role = new Role("Trainer");
        trainer.getEmployee().setRole(role);
        if (errorFound.size() == 0) {
            trainerDao.insertTrainer(trainer);
        } else {
            throw new BadRequest(errorFoundMessage, errorFound);
        }
        return errorFound;
    }



    /**
     * <p>
     * It Retrieve Trainer data from dao and sends it to controller.
     * </p>
     * @return it returns list of trainers
     **/
    @Override
    public List<Trainer> getTrainers() {
        logger.info("Trainer Retrieve Method");
        return trainerDao.retrieveTrainer();
    }

    /**
     * <p>
     * It receives the employee Id from controller and sends it to Trainer Dao.
     * </p>
     * @param {@link int} empId
     * @return it returns nothing
     **/
    @Override
    public boolean deleteByTrainerId(int empId) {
        logger.info("Delete Trainer Method");
        return trainerDao.deleteTrainerById(empId);
    }

    /**
     * <p>
     * It receives the employee Id from controller and sends it to Trainer Dao.
     * </p>
     * @param {@link int} empId
     * @return it returns nothing
     **/
    @Override
    public Trainer retrieveTrainerById(int empId) {
        logger.info("Trainer Retrieve By Id Method");
        return trainerDao.retrieveTrainerById(empId);
    }
}
