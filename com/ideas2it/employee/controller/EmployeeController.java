package com.ideas2it.employee.controller;

import com.ideas2it.employee.model.Trainer;
import com.ideas2it.employee.model.Trainee;
import com.ideas2it.employee.model.Role;
import com.ideas2it.employee.model.Qualification;
import com.ideas2it.employee.service.impl.TrainerServiceImpl;
import com.ideas2it.employee.service.impl.TraineeServiceImpl;
import com.ideas2it.employee.service.TrainerService;
import com.ideas2it.employee.service.TraineeService;
import com.ideas2it.employee.utility.StringUtility;
import com.ideas2it.employee.utility.DateUtility;
import com.ideas2it.employee.exception.EmployeeNotFound;
import com.ideas2it.employee.exception.BadRequest;

import java.util.Scanner;
import java.util.InputMismatchException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

/**
 * <p> 
 * It implements methods for add, view, delete and update opertions for Employees
 * </p>
 *
 * @author Ruban
 * @version 1.0 12/08/22
 * 
 **/ 
public class EmployeeController {
    private Scanner userInput = new Scanner(System.in);
    private TrainerService trainerService = new TrainerServiceImpl();
    private TraineeService traineeService = new TraineeServiceImpl();
    private static Logger logger = LogManager.getLogger(EmployeeController.class);

    public static void main(String args[]) { 
        EmployeeController employeeController = new EmployeeController();
        employeeController.userMenu();
      
    }

    /** 
     * <p>
     * It show the user to select CRUD operations.
     * </p>
     **/
    private void userMenu() {
        String option;
        String userchoice;
        do {
            logger.info( "\n\n\t\t   EMPLOYEE MANAGEMENT PORTAL\n " +
                                "\n\t\t       TRAINER PORTAL\n" +
                                "\n" + "\t\tEnter 1 to Add     Trainer Details\n\t\tEnter 2 to View    Trainer Details" +
                                "\n" + "\t\tEnter 3 to Delete  Trainer Details\n\t\tEnter 4 to Update  Trainer Details\n"+
                                "\n\t\t       TRAINEE PORTAL\n" +
                                "\n" + "\t\tEnter 5 to Add    Trainee Details\n\t\tEnter 6 to View   Trainee Details" +
                                "\n" + "\t\tEnter 7 to Delete Trainee Details\n\t\tEnter 8 to Update Trainee Details" +
                                "\n" + "\n\t\t      Enter 9 to Exit");
            logger.info("\n" + "Enter your choice");
 
            userchoice = userInput.next();
            switch (userchoice) {
            case "1" :
                addTrainer(); 
                break;

            case "2" :
                viewTrainers();
                break;

            case "3" :
                deleteTrainerDetailById();
                break;

            case "4" :
                updateTrainer();
                break;

            case "5" :
                addTrainee();
                break;

            case "6" :
                viewTrainees();
                break;

            case "7" :
                deleteTraineeDetailById();
                break;

            case "8" :
                updateTrainee();
                break;
               
            case "9" :
                System.exit(0);

            default :
                logger.warn("Invalid Option");
            } 
            logger.info("\nFor Main Menu type Yes, To exit type No");
            option = userInput.next();
        } while (option.equalsIgnoreCase("yes")); 
    }

    /** <p>
     *  It gets input from user and sends it to Service. 
     *  </p>
     **/
    private void addTrainer() {
        logger.info("\n" + "Enter Employee Name :");
        String employeeName = userInput.next();
        logger.info("\n" + "For Gender:\nSelect the option: \n1.Male \n2.Female \n3.Others ");
        String gender = null;
        do {
            String choice = userInput.next();
            switch (choice) {
            case "1" :
                gender = "male";
                break;

            case "2" :
                gender = "female";
                break;

            case "3" :
                gender = "others";
                break;

            default :
                logger.warn("please select valid option");
                break;
             }
        } while (gender == null);

        System.out.println("\n" +"Enter your Qualification :");
        String qualifications = userInput.next(); 
        Qualification qualification = new Qualification(qualifications);

        logger.info("\n" +"Enter the EmailId :");
        String emailId = userInput.next();
        logger.info("\n" +"Enter the Date of Birth(DD/MM/YYYY) :");
        String dateOfBirth = userInput.next();
        logger.info("\n" +"Enter the Date of Joining :");
        String dateOfJoining = userInput.next();
        logger.info("\n" +"Enter the Address :");
        String address = userInput.next();
        userInput.nextLine();
        logger.info("\n" +"Enter the Mobile Number :");
        String phoneNumber = userInput.next();                            
        logger.info("\n" +"Enter the Adhaar Number :");
        String adhaarNumber = userInput.next();
        logger.info("\n" +"Enter the Department :");
        String department = userInput.next();
        logger.info("\n" +"Year of Experience :");
        byte trainerExperience = userInput.nextByte();
        Role role = new Role("Trainer");
        
        boolean isValid = false;
        do {
            List<Integer> validationErrorAsList = new ArrayList<Integer>();
            try { 
                validationErrorAsList = trainerService.addTrainer(employeeName, gender, emailId,
                                                                  dateOfBirth, dateOfJoining, address,
                                                                  phoneNumber, adhaarNumber, department, 
                                                                  trainerExperience, role, qualification);
            } catch(BadRequest e) {
                logger.warn(e.getMessage());
                validationErrorAsList = e.getValidationErrorAsList();
            }
            if (validationErrorAsList.size() > 0) {
                for (int i = 0 ; i < validationErrorAsList.size(); i++) {
                    switch (validationErrorAsList.get(i)) {
                    case 1 :
                        logger.info("\nEnter the Valid EmailId :");
                        emailId = userInput.next();
                        break;
                    case 2 :
                        logger.info("\nPlease Enter the Valid DateOfBirth(DD/MM/YYYY) :");
                        dateOfBirth = userInput.next();
                        break;
                    case 3 :
                        logger.info("\nEnter the valid mobile Number :");
                        phoneNumber = userInput.next();
                        break;
                    case 4 : 
                        logger.info("\nEnter the Valid AdhaarNumber :");
                        adhaarNumber = userInput.next();
                        break;
                    case 5 : 
                        logger.info("\nEnter the Valid Employee Name :");
                        employeeName = userInput.next();
                        break;
                    } 
                }
            } 
            if (validationErrorAsList.size() > 0 ) {
                isValid = true; 
            } else {
                isValid = false;
            }
        } while (isValid);
    }

    /** 
     * <p>
     * It list out all the trainers in trainer object if it exist
     * </p>
     *
     **/
    private void viewTrainers() {
     
        List<Trainer> listOfTrainers = trainerService.getTrainers();
        if (trainerService.getTrainers().size() < 1) {
            logger.warn("\nYou have not added any Record");
        } else {
            for (Trainer trainer : trainerService.getTrainers()) {  
                logger.info(trainer);           
            }
        }
    }

    /** 
     * <p>
     * Gets Trainee input from user and sends value to Service
     * </p>
     **/
    private void addTrainee() {
        logger.info("\n" + "Enter Employee Name :");
        String employeeName = userInput.next();
        logger.info("\n" + "For Gender:\nSelect the option: \n1.Male \n2.Female \n3.Others ");
        String gender = null;
        do {
            String choice = userInput.next();
            switch (choice) {
            case "1" :
                gender = "male";
                break;

            case "2" :
                gender = "female";
                break;

            case "3" :
                gender = "others";
                break;

            default :
                logger.warn("please select valid option");
                break;
             }
        } while (gender == null);
        logger.info("\n" + "Enter your Qualification :");
        String qualifications = userInput.next(); 
        Qualification qualification = new Qualification(qualifications);
        logger.info("\n" + "Enter the Email Id :");
        String emailId = userInput.next();
        logger.info("\n" + "Enter the Date of Birth(DD/MM/YYYY) :");
        String dateOfBirth = userInput.next();
        logger.info("\n" + "Enter the Date of Joining :");
        String dateOfJoining = userInput.next();
        logger.info("\n" + "Enter the Address :");
        String address = userInput.next();
        userInput.nextLine();
        logger.info("\n" + "Enter the Mobile Number :");
        String phoneNumber = userInput.next();
        logger.info("\n" + "Enter the Adhaar Number :");
        String adhaarNumber = userInput.next();
        logger.info("\n" + "Enter the Department :");
        String department = userInput.next();	
        logger.info("\n" + "Enter the salary :");
        int salary = userInput.nextInt();
        Role role = new Role("Trainee");
        List<Integer> trainersId = new ArrayList<>();
        String trainerNames;
        int number =0;
        logger.info("\n" + "Enter Number of Trainers to be Added");
        boolean isNameValid = false;
        while(!isNameValid) {
            try {
                number = userInput.nextInt();
                isNameValid = true;
                userInput.nextLine();
             } catch(InputMismatchException e) {
               logger.warn("Enter valid");
               userInput = new Scanner(System.in);
             }
         trainersId = addTrainersId(number);
         }
        
        boolean isValid = false;
        do {
            List<Integer> validationErrorAsList = new ArrayList<Integer>(); 
            try {
                validationErrorAsList = traineeService.addTrainee(employeeName, gender,
                                                                  emailId, dateOfBirth,dateOfJoining,
                                                                  address, phoneNumber, adhaarNumber,
                                                                  department, trainersId, salary, role, qualification);
            } catch(BadRequest e) {
                logger.warn(e.getMessage());
                validationErrorAsList = e.getValidationErrorAsList();
            }
            if (validationErrorAsList.size() > 0) {
                for (int i = 0 ; i < validationErrorAsList.size(); i++) {
                    switch (validationErrorAsList.get(i)) {
                    case 1 :
                        logger.info("\nEnter the Valid EmailId :");
                        emailId = userInput.next();
                        break;
                    case 2 :
                        logger.info("\nPlease enter the Valid DateOfBirth(DD/MM/YYYY) :");
                        dateOfBirth = userInput.next();
                        break;
                    case 3 :
                        logger.info("\nEnter the valid mobile Number :");
                        phoneNumber = userInput.next();
                        break;
                    case 4 : 
                        logger.info("\nEnter the Valid AdhaarNumber :");
                        adhaarNumber = userInput.next();
                        break;
                    case 5 : 
                        logger.info("\nEnter the Valid Employee Name :");
                        employeeName = userInput.next();
                        break;  
                    } 
                }   
            } 
            if (validationErrorAsList.size() > 0 ) {
                isValid = true; 
            } else {
                isValid = false;
            }
        } while (isValid);    
    }

    /** 
     * <p>
     * It shows the Trainee object from the list if it exist.
     * </p>
     **/
    private void viewTrainees() {
        if (traineeService.getTrainees().size() < 1) {
            logger.warn("\nYou have not added any Record");
        } else {
            for (Trainee trainee : traineeService.getTrainees()) {
                logger.info(trainee);
            }
        }
    }
 
    /**
     * <p>
     * It gets employeed id and sends it to Service.
     * </P>
     **/
    private void deleteTrainerDetailById() {
        if (trainerService.getTrainers().size() < 1) {
            logger.warn("\nYou have not added any Record");
        } else {
            System.out.println("Enter the Employee Id");
            int employeeId = userInput.nextInt();
            try {
                trainerService.deleteByTrainerId(employeeId);
                logger.info("Trainer Details deleted Successfully");
            } catch (EmployeeNotFound e) {
                logger.warn(e.getMessage());
            }
        }
    }
      
    /**
     * <p> 
     * It gets employeed id and sends it to Service.
     * </p>
     **/  
    private void deleteTraineeDetailById() {
        if (traineeService.getTrainees().size() < 1) {
            logger.warn("\nYou have not added any Record");
        } else {
            logger.info("Enter the Employee Id");
            int employeeId = userInput.nextInt();
            try {
                traineeService.deleteByTraineeId(employeeId);
                logger.info("Trainee Details deleted Successfully");
            } catch (EmployeeNotFound e) {
                logger.warn(e.getMessage());
            }
        }
    }

    /**
     * <p>
     * This method used to update Trainer details.
     * </p>
     **/
    private void updateTrainer() {
       
         Trainer trainer = null;
         logger.info("Enter the employee Id");
         int empId = userInput.nextInt();
         try {
             trainer = trainerService.retrieveTrainerById(empId);
             if (trainer != null ) {
                 System.out.println(trainer);
             } 
         } catch (EmployeeNotFound e) {
                System.out.println(e.getMessage());
                userInput = new Scanner(System.in);
         } 
       if (trainer != null) {
         logger.info("\nEnter 1 to update EmailId");
         logger.info("Enter 2 to update Address");
         logger.info("Enter 3 to update Phone Number"); 
         String userChoice = userInput.next();
         switch(userChoice) {
         case "1" :
             logger.info("Enter Email Id");
             String mail = userInput.next();
             trainerService.updateEmail(trainer, mail);
             break;
         case "2":
             logger.info("Enter the Address to be Update");
             String address = userInput.next();
             trainerService.updateAddress(trainer, address);
             break;
         case "3":
             logger.info("Enter the phone Number to be Update");
             String number = userInput.next();
             trainerService.updateNumber(trainer, number);
             break;
         default :
             logger.warn("Invalid option");
             break;
         }
       }
                                  
    }

    /**
     * <p>
     * This method used to update Trainer details.
     * </p>
     **/
    private void updateTrainee() {
         Trainee trainee = null;
         logger.info("Enter the employee Id");
         int empId = userInput.nextInt();
         try {
             trainee = traineeService.retrieveTraineeById(empId);
             if (trainee != null ) {
                 logger.warn(trainee);
             } 
         } catch (EmployeeNotFound e) {
                logger.warn(e.getMessage());
                userInput = new Scanner(System.in);
         } 
         if (trainee != null) {
         logger.info("\nEnter 1 to update EmailId");
         logger.info("Enter 2 to update Address");
         logger.info("Enter 3 to update Phone Number"); 
         String userChoice = userInput.next();
         switch(userChoice) {
         case "1" :
             logger.info("Enter Email Id to be Update");
             String mail = userInput.next();
             traineeService.updateEmail(trainee, mail);
             break;
         case "2":
             logger.info("Enter the Address to be Update");
             String address = userInput.next();
             traineeService.updateAddress(trainee, address);
             break;
         case "3":
             logger.info("Enter the phone Number to be Update");
             String number = userInput.next();
             traineeService.updateNumber(trainee, number);
             break;
          default :
             logger.warn("Invalid option");
             break;
         }
          }
              
    }

    /**
     * <p>
     * This method to add trainer names in list.
     * </p>
     * @param {@link int} number
     **/
    private List<Integer> addTrainersId(int number) {
        List<Integer> trainersId = new ArrayList<>();
        for (int i = 0; i < number; i++) {
            logger.info("Enter the trainer Id:");
            trainersId.add(userInput.nextInt());
        }
    return trainersId;
    }
}
	
		





		
    
     