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
                                    final List<Integer> trainersId, final int salary, final Qualification qualification) {
   
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
       // LocalDate dateOfBirth = LocalDate.now();
        //try {
           // DateTimeFormatter formatter = DateTimeFormatter.ofPattern(Constant.DATE_FORMAT);
           // dateOfBirth = LocalDate.parse(validDateOfBirth, formatter); 
            //if (DateUtility.isFutureDate(dateOfBirth)) {
              //  errorFoundMessage += "\nIt is a Future Date\n";
                //errorFound.add(2);
            //}
        //} catch (DateTimeParseException e) {
          //  errorFoundMessage += "\nInvalid DateOfBirth\n";
           // errorFound.add(2);
        //}
        //Period period = Period.between(dateOfBirth, LocalDate.now());  
        //int age = period.getYears();
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
         
        if (errorFound.size() == 0) {
            if (trainee == null) {
                validQualification = new Qualification(qualification);
                role = new Role("Trainee");   
           
            Employee employee = new Employee(employeeName, gender, 
                                             emailId, dateOfBirth, dateOfJoining,
                                             address, phoneNumber, adhaarNumber, department, role, validQualification);
            Trainee trainee = new Trainee(employee, salary, validTrainers);
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
                 trainee.setSalary(salary);
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
        Trainee validTrainee = traineeDao.retrieveTraineeById(empId);
        if (validTrainee == null) {
            throw new EmployeeNotFound("Id not found");
        } 
        return validTrainee;
    }

    /** 
     * <p>
     * This method for email updation.
     * </p>
     * @param no parameters
     * @return It returns Nothing
     **/
    public boolean updateEmail(Trainee trainee, String mail) {
        if (StringUtility.isValidMail(mail)) {
            trainee.getEmployee().setEmailId(mail);
            traineeDao.updateTrainee(trainee);
        } 
        return (StringUtility.isValidMail(mail));
    }

    /** 
     * <p>
     * This method for Number updation.
     * </p>
     * @param no parameters
     * @return It returns Nothing
     **/
    public boolean updateNumber(Trainee trainee, String number) {
        if (StringUtility.isValidNumber(number)) {
            trainee.getEmployee().setPhoneNumber(number);
            traineeDao.updateTrainee(trainee);
        } 
        return (StringUtility.isValidNumber(number));
    }

    /** 
     * <p>
     * This method for Address updation.
     * </p>
     * @param {@link Trainee} trainee
     * @param {@link String} address
     * @return It returns nothing
     **/
    public void updateAddress(Trainee trainee, String address) {
        trainee.getEmployee().setAddress(address);
        traineeDao.updateTrainee(trainee);
    }    
}