package com.ideas2it.employee.service.impl;

import com.ideas2it.employee.dao.QualificationDao;
import com.ideas2it.employee.dao.RoleDao;
import com.ideas2it.employee.dao.TrainerDao;
import com.ideas2it.employee.dto.TrainerDto;
import com.ideas2it.employee.exception.BadRequest;
import com.ideas2it.employee.mapper.TrainerMapper;
import com.ideas2it.employee.model.Qualification;
import com.ideas2it.employee.model.Role;
import com.ideas2it.employee.model.Trainer;
import com.ideas2it.employee.service.TrainerService;
import com.ideas2it.employee.utility.StringUtility;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    private  Logger logger = LogManager.getLogger(TrainerServiceImpl.class);
    public TrainerDao trainerDao;
    public RoleDao roleDao;
    public QualificationDao qualificationDao;
    private TrainerMapper trainerMapper;

    @Autowired
    public TrainerServiceImpl(TrainerDao trainerDao, RoleDao roleDao,
                              QualificationDao qualificationDao,
                              TrainerMapper trainerMapper) {
        this.trainerDao = trainerDao;
        this.roleDao = roleDao;
        this.qualificationDao = qualificationDao;
        this.trainerMapper = trainerMapper;
    }




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
    public List<Integer> addTrainer(TrainerDto trainerDto) {
        List<Integer> errorFound = new ArrayList<Integer>();
        String errorFoundMessage = "";
        errorFound.clear();
       String employeeName = trainerDto.getEmployeeName();
        if (!StringUtility.isValidName(employeeName)) {
            errorFoundMessage += "\nInvalid Name";
            errorFound.add(5);
        }
        String emailId = trainerDto.getEmailId();
        if (!StringUtility.isValidMail(emailId)) {
            errorFoundMessage += "\nInvalid Email Id";
            errorFound.add(1);
        }
        String phoneNumber = trainerDto.getPhoneNumber();
        if (!StringUtility.isValidNumber(phoneNumber)) {
            errorFoundMessage += "\nInvalid Mobile Number";
            errorFound.add(3);
        }
        String adhaarNumber = trainerDto.getAdhaarNumber();
        if (!StringUtility.isNumberValid(adhaarNumber)) {
            errorFoundMessage += "\nInvalid Adhaar Number";
            errorFound.add(4);
        }




        if (errorFound.size() == 0) {
            Trainer trainer = trainerMapper.toTrainer(trainerDto);
            Optional<Qualification> qualification = qualificationDao.findByDescription(trainer.getQualification().getDescription());
            if (qualification.isPresent()) {
                trainer.setQualification(qualification.get());
            }
            Optional<Role> role = roleDao.findByDescription(trainer.getRole().getDescription());
            if (role.isPresent()) {
                trainer.setRole(role.get());
            }
            trainerDao.save(trainer);
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
    public List<TrainerDto> getTrainers() {
        logger.info("Trainer Retrieve Method");
        List<TrainerDto> trainers = new ArrayList<>();
        for (Trainer trainer : trainerDao.findAll()) {
            trainers.add(trainerMapper.toTrainerDto(trainer));
        }
        return trainers;
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
        trainerDao.deleteById(empId);
        return true;
    }

    /**
     * <p>
     * It receives the employee Id from controller and sends it to Trainer Dao.
     * </p>
     * @param {@link int} empId
     * @return it returns nothing
     **/
    @Override
    public List<Trainer> retrieveTrainersById(List<Integer> trainerIds) {
        logger.info("Trainer Retrieve By Id Method");
        List<Trainer> trainers = trainerDao.findAllById(trainerIds);
        return trainers;
    }

    @Override
    public TrainerDto getTrainerById(final int trainerId) {
        logger.info("Entered getTrainerById() method");
        TrainerDto trainerDto = null;
        Optional<Trainer> trainer = trainerDao.findById(trainerId);
        if (trainer.isPresent()) {
            trainerDto = trainerMapper.toTrainerDto(trainer.get());
        }
        return trainerDto;
    }
}
