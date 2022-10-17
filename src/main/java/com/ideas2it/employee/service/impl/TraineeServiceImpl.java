package com.ideas2it.employee.service.impl;

import com.ideas2it.employee.dao.QualificationDao;
import com.ideas2it.employee.dao.RoleDao;
import com.ideas2it.employee.dao.TraineeDao;
import com.ideas2it.employee.dto.TraineeDto;
import com.ideas2it.employee.exception.BadRequest;
import com.ideas2it.employee.mapper.TraineeMapper;
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
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * <h1> Trainee Service </h1>
 * <p>
 * It gets Input and sends it for CRUD operations.
 * </P>
 * version 1.0
 * @author ruban 11/08/22
 **/
@Service
public class TraineeServiceImpl implements TraineeService {

    private Logger logger = LogManager.getLogger(TraineeServiceImpl.class);
    private TrainerService trainerService;
    private TraineeDao traineeDao;
    private QualificationDao qualificationDao;
    private RoleDao roleDao;

    private TraineeMapper traineeMapper;

    @Autowired
    public TraineeServiceImpl(TrainerService trainerService, TraineeDao traineeDao,
                              QualificationDao qualificationDao, RoleDao roleDao,
                              TraineeMapper traineeMapper) {
        this.trainerService = trainerService;
        this.traineeDao = traineeDao;
        this.qualificationDao = qualificationDao;
        this.roleDao = roleDao;
        this.traineeMapper = traineeMapper;
    }


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
    public List<Integer> addTrainee(TraineeDto traineeDto) {
        List<Integer> errorFound = new ArrayList<Integer>();
        String errorFoundMessage = "";
        errorFound.clear();
        String employeeName = traineeDto.getEmployeeName();
        if (!StringUtility.isValidName(employeeName)) {
            errorFoundMessage += "\nInvalid Name\n";
            errorFound.add(5);
        }
        String emailId = traineeDto.getEmailId();
        if (!StringUtility.isValidMail(emailId)) {
            errorFoundMessage += "\nInvalid Email Id\n";
            errorFound.add(1);
        }
        String phoneNumber = traineeDto.getPhoneNumber();
        if (!StringUtility.isValidNumber(phoneNumber)) {
            errorFoundMessage += "\nInvalid Mobile Number\n";
            errorFound.add(3);
        }
        String adhaarNumber = traineeDto.getAdhaarNumber();
        if (!StringUtility.isNumberValid(adhaarNumber)) {
            errorFoundMessage += "\nInvalid Adhaar Number\n";
            errorFound.add(4);
        }


        if (errorFound.size() == 0) {
            Set<Trainer> trainers = Set.copyOf(trainerService.retrieveTrainerById(traineeDto.getTrainersId()));
            Trainee trainee = traineeMapper.toTrainee(traineeDto);
            trainee.setTrainers(trainers);
            Optional<Qualification> qualification = qualificationDao.findByDescription(trainee.getQualification().getDescription());
            if (qualification.isPresent()) {
                trainee.setQualification(qualification.get());
            }

            Optional<Role> role = roleDao.findByDescription(trainee.getRole().getDescription());
            if (role.isPresent()) {
                trainee.setRole(role.get());
            }
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
    public List<TraineeDto> getTrainees() {
        logger.info("Retrieve Trainee Method");
        List<TraineeDto> trainees = new ArrayList<>();
        for (Trainee trainee: traineeDao.findAll()) {
            trainees.add(traineeMapper.toTraineeDto(trainee));
        }
        return trainees;
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
    public TraineeDto retrieveTraineeById(int empId)  {
        logger.info("Retrieve Trainee By Id Method");
        TraineeDto traineeDto = null;
        Optional<Trainee> trainees = traineeDao.findById(empId);
        if (trainees.isPresent()) {
            traineeDto = traineeMapper.toTraineeDto(trainees.get());
        }
        /*List<Integer> trainersId = new ArrayList<>();
        for (Trainer trainer : trainee.getTrainers()) {
            trainersId.add(trainer.getId());
        }
        trainee.setTrainersId(trainersId);*/
        return traineeDto;

    }
}
