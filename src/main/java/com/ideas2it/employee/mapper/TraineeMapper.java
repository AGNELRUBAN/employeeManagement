package com.ideas2it.employee.mapper;

import com.ideas2it.employee.dto.TraineeDto;
import com.ideas2it.employee.model.Trainee;
import com.ideas2it.employee.model.Trainer;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TraineeMapper {

    public static Trainee toTrainee (TraineeDto traineeDto) {
        Trainee trainee = new Trainee();
        trainee.setId(traineeDto.getId());
        trainee.setEmployeeName(traineeDto.getEmployeeName());
        trainee.setGender(traineeDto.getGender());
        trainee.setEmailId(traineeDto.getEmailId());
        trainee.setDateOfBirth(traineeDto.getDateOfBirth());
        trainee.setDateOfJoining(traineeDto.getDateOfJoining());
        trainee.setAddress(traineeDto.getAddress());
        trainee.setPhoneNumber(traineeDto.getPhoneNumber());
        trainee.setAdhaarNumber(traineeDto.getAdhaarNumber());
        trainee.setDepartment(traineeDto.getDepartment());
        trainee.setSalary(traineeDto.getSalary());
        trainee.setQualification(QualificationMapper.toQualification(traineeDto.getQualificationDto()));
        trainee.setRole(RoleMapper.toRole(traineeDto.getRoleDto()));
        return trainee;
    }

  /*  public static TraineeDto convertObjectToTraineeDto(Object[] object) {
        TraineeDto traineeDto = new TraineeDto();
        traineeDto.setSalary((Integer) object[0]);
        traineeDto.setId((Integer) object[1]);
        traineeDto.setEmailId((String) object[2]);
        traineeDto.setPhoneNumber((String) object[3]);
        traineeDto.setEmployeeName((String) object[4]);
        return traineeDto;
    }*/

    public static TraineeDto toTraineeDto(Trainee trainee) {
        TraineeDto traineeDto = new TraineeDto();
        traineeDto.setId(trainee.getId());
        traineeDto.setEmployeeName(trainee.getEmployeeName());
        traineeDto.setGender(trainee.getGender());
        traineeDto.setEmailId(trainee.getEmailId());
        traineeDto.setDateOfBirth(trainee.getDateOfBirth());
        traineeDto.setDateOfJoining(trainee.getDateOfJoining());
        traineeDto.setAddress(trainee.getAddress());
        traineeDto.setPhoneNumber(trainee.getPhoneNumber());
        traineeDto.setAdhaarNumber(trainee.getAdhaarNumber());
        traineeDto.setDepartment(trainee.getDepartment());
        traineeDto.setSalary(trainee.getSalary());
        traineeDto.setQualificationDto(QualificationMapper.toQualificationDto(trainee.getQualification()));
        traineeDto.setRoleDto(RoleMapper.toRoleDto(trainee.getRole()));
        List<String> trainerNames = new ArrayList<>();
        List<Integer> trainersId = new ArrayList<>();
        for (Trainer trainer : trainee.getTrainers()) {
            trainersId.add(trainer.getId());
            trainerNames.add(trainer.getEmployeeName());
        }
        traineeDto.setTrainersId(trainersId);
        traineeDto.setTrainersName(trainerNames);
        return traineeDto;
    }
}
