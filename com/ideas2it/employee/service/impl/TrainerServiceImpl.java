package com.ideas2it.employee.service.impl;

import com.ideas2it.employee.dao.TrainerDao;
import com.ideas2it.employee.dao.impl.TrainerDaoImpl;
import com.ideas2it.employee.utility.StringUtility;
import com.ideas2it.employee.utility.DateUtility;
import com.ideas2it.employee.utility.Constant;
import com.ideas2it.employee.utility.NumberUtility;
import com.ideas2it.employee.model.Trainer;
import com.ideas2it.employee.model.Employee;
import com.ideas2it.employee.model.Role;
import com.ideas2it.employee.model.Qualification;
import com.ideas2it.employee.service.TrainerService;
import com.ideas2it.employee.exception.EmployeeNotFound;
import com.ideas2it.employee.exception.BadRequest;

import java.util.ArrayList;
import java.util.List;
import java.time.Period;
import java.time.format.DateTimeParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

/**
 * <h1> Trainer Service </h1>
 * <p> 
 * It gets Trainer Input from controller and sends it to dao for CRUD operations.
 * </P>
 * version 1.0
 * @author ruban 11/08/22
 **/
public class TrainerServiceImpl implements TrainerService {
    public TrainerDao trainerDao = new TrainerDaoImpl();
    private static Logger logger = LogManager.getLogger(TrainerServiceImpl.class);

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
    public List<Integer> addTrainer(Trainer trainer, final String employeeName, final String gender,
                                    final String emailId, 
                                    final String dateOfBirth, final String dateOfJoining, 
                                    final String address, final String phoneNumber,
                                    final String adhaarNumber, final String department,
                                    final String trainerExperience, final String qualification) {
        logger.info("ADD Trainer Method");
        List<Integer> errorFound = new ArrayList<Integer>();
        String errorFoundMessage = "";
        errorFound.clear();
        if (!StringUtility.isValidName(employeeName)) {
            errorFoundMessage += "\nInvalid Name";
            errorFound.add(5);
        }
        if (!StringUtility.isValidMail(emailId)) {
            errorFoundMessage += "\nInvalid Email Id";
            errorFound.add(1);
        }
        if (!StringUtility.isValidNumber(phoneNumber)) {
            errorFoundMessage += "\nInvalid Mobile Number";
            errorFound.add(3);
        } 
        if (!StringUtility.isNumberValid(adhaarNumber)) {
            errorFoundMessage += "\nInvalid Adhaar Number";
            errorFound.add(4);
        } 
        Qualification validQualification;
        byte trainerExperiences = Byte.valueOf(trainerExperience); 
        LocalDate validDateOfBirth = LocalDate.parse(dateOfBirth);
        Role role;

        if (errorFound.size() == 0) {
            if (trainer == null) {
                validQualification = new Qualification(qualification);
                role = new Role("Trainer");
                Employee employee = new Employee(employeeName, gender, 
                                                 emailId, validDateOfBirth, dateOfJoining,
                                                 address, phoneNumber, adhaarNumber, department,
                                                 role, validQualification );
                trainer = new Trainer(employee, trainerExperiences);
            } else {
                trainer.getEmployee().setEmployeeName(employeeName);
                trainer.getEmployee().setGender(gender);
                trainer.getEmployee().setEmailId(emailId);
                trainer.getEmployee().setDateOfBirth(validDateOfBirth);
                trainer.getEmployee().setDateOfJoining(dateOfJoining);
                trainer.getEmployee().setAddress(address);
                trainer.getEmployee().setPhoneNumber(phoneNumber);
                trainer.getEmployee().setAdhaarNumber(adhaarNumber);
                trainer.getEmployee().setDepartment(department);
                trainer.getEmployee().getQualification().setDescription(qualification);
                trainer.setTrainerExperience(trainerExperiences);
            }
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
     * @param no parameters
     * @return it returns list of trainers
     **/
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
    public boolean deleteByTrainerId(int empId) throws EmployeeNotFound {
        logger.info("Delete Trainer Method");
        boolean isTrainerDeleted = trainerDao.deleteTrainerById(empId);
        if (!isTrainerDeleted) {
            throw new EmployeeNotFound("Id not found");
        }
        return isTrainerDeleted;
    }

    /**
     * <p>
     * It receives the employee Id from controller and sends it to Trainer Dao.
     * </p>
     * @param {@link int} empId
     * @return it returns nothing
     **/ 
    public Trainer retrieveTrainerById(int empId) throws EmployeeNotFound {
        logger.info("Trainer Retrieve By Id Method");
        Trainer validTrainer = trainerDao.retrieveTrainerById(empId);
        if (validTrainer == null) {
            throw new EmployeeNotFound("Id not found");
        } 
        return validTrainer;
    }  
}