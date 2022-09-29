package com.ideas2it.employee.service.impl;

import com.ideas2it.employee.model.Employee;
import com.ideas2it.employee.model.Qualification;
import com.ideas2it.employee.model.Role;
import com.ideas2it.employee.model.Trainer;
import com.ideas2it.employee.dao.impl.TraineeDaoImpl;
import com.ideas2it.employee.dao.TraineeDao;
import com.ideas2it.employee.utility.StringUtility;
import com.ideas2it.employee.utility.DateUtility;
import com.ideas2it.employee.utility.Constant;
import com.ideas2it.employee.utility.NumberUtility;
import com.ideas2it.employee.model.Trainee;
import com.ideas2it.employee.service.TraineeService;
import com.ideas2it.employee.service.impl.TrainerServiceImpl;
import com.ideas2it.employee.service.TrainerService;
import com.ideas2it.employee.exception.EmployeeNotFound;
import com.ideas2it.employee.exception.BadRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.InputMismatchException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.time.format.DateTimeFormatter;
import java.time.Period;
import java.util.HashSet;
import java.util.Set;
import com.ideas2it.employee.exception.EmployeeNotFound;
import com.ideas2it.employee.exception.BadRequest;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

/**
 * <h1> Trainee Service </h1>
 * <p> 
 * It gets Input and sends it for CRUD operations.
 * </P>
 * version 1.0
 * @author ruban 11/08/22
 **/
public class TraineeServiceImpl implements TraineeService {
    public TraineeDao traineeDao = new TraineeDaoImpl();
    private TrainerService trainerService = new TrainerServiceImpl();
    private static Logger logger = LogManager.getLogger(TraineeServiceImpl.class);

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
    public List<Integer> addTrainee(Trainee trainee, final String employeeName, final String gender,
                                    final String emailId, 
                                    final String validDateOfBirth, final String dateOfJoining,
                                    final String address, final String phoneNumber,
                                    final String adhaarNumber, final String department,
                                    final String salary, final String qualification, final List<String> trainersId) {
        logger.info("Add Trainee Method");
        List<Integer> errorFound = new ArrayList<Integer>();
        String errorFoundMessage = "";
        errorFound.clear();
        if (!StringUtility.isValidName(employeeName)) {
            errorFoundMessage += "\nInvalid Name\n";
            errorFound.add(5);
        }
        if (!StringUtility.isValidMail(emailId)) { 
            errorFoundMessage += "\nInvalid Email Id\n";
            errorFound.add(1);
        }
        if (!StringUtility.isValidNumber(phoneNumber)) {
            errorFoundMessage += "\nInvalid Mobile Number\n";
            errorFound.add(3);
        } 
        if (!StringUtility.isNumberValid(adhaarNumber)) {
            errorFoundMessage += "\nInvalid Adhaar Number\n";
            errorFound.add(4);
        } 
    
        List<Integer> validTrainersId = new ArrayList<>();
        List<Trainer> trainers = trainerService.getTrainers();
        Set<Trainer> validTrainers = new HashSet<>();
        for (String trainerId : trainersId) {
            for (Trainer trainer : trainers) {
                if (Integer.valueOf(trainerId) == trainer.getEmployee().getId()) {
                    validTrainersId.add(Integer.valueOf(trainerId));
                    validTrainers.add(trainer);
                }
            }
        }
        Qualification validQualification;
        LocalDate dateOfBirth = LocalDate.parse(validDateOfBirth);
        Role role;
        int salaryy = Integer.parseInt(salary);
         
        if (errorFound.size() == 0) {
            if (trainee == null) {
                validQualification = new Qualification(qualification);
                role = new Role("Trainee");   
                Employee employee = new Employee(employeeName, gender, 
                                                 emailId, dateOfBirth, dateOfJoining,
                                                 address, phoneNumber, adhaarNumber, department, 
                                                 role, validQualification);
                trainee = new Trainee(employee, salaryy, validTrainers);
            } else {
                trainee.getEmployee().getQualification().setDescription(qualification);
                trainee.getEmployee().setEmployeeName(employeeName);
                trainee.getEmployee().setGender(gender);
                trainee.getEmployee().setEmailId(emailId);
                trainee.getEmployee().setDateOfBirth(dateOfBirth);
                trainee.getEmployee().setDateOfJoining(dateOfJoining);
                trainee.getEmployee().setAddress(address);
                trainee.getEmployee().setPhoneNumber(phoneNumber);
                trainee.getEmployee().setAdhaarNumber(adhaarNumber);
                trainee.getEmployee().setDepartment(department);
                trainee.setSalary(salaryy);
                trainee.setTrainersId(validTrainersId);
            }
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
     * @param no parameters
     * @return It returns trainee List
     **/
    public List<Trainee> getTrainees() {
        logger.info("Retrieve Trainee Method");
        return traineeDao.retrieveTrainee();
    }
   
    /**
     * <p>
     * It receives the employee Id from controller and sends it to Trainee Dao.
     * </p>
     * @param {@link int} empId
     * @return returns boolean
     **/
    public boolean deleteByTraineeId(int empId) throws EmployeeNotFound {
        logger.info("Delete Trainee Method");
        boolean isTraineeDeleted = traineeDao.deleteTraineeById(empId);
        if (!isTraineeDeleted) {
            throw new EmployeeNotFound("Id not found");
        }
        return isTraineeDeleted;
    }  

    /** 
     * <p>
     * This method receive Employee Id from controller and sends it to dao.
     * </p>
     * @param no parameters
     * @return It returns trainee Id
     **/
    public Trainee retrieveTraineeById(int empId) throws EmployeeNotFound {
        logger.info("Retrieve Trainee By Id Method");
        Trainee validTrainee = traineeDao.retrieveTraineeById(empId);
        if (validTrainee == null) {
            throw new EmployeeNotFound("Id not found");
        } 
        return validTrainee;
    }   
}