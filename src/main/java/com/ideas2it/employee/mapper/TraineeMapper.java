package com.ideas2it.employee.mapper;

import com.ideas2it.employee.dto.TraineeDto;
import com.ideas2it.employee.model.Trainee;
import com.ideas2it.employee.model.Trainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TraineeMapper {

    QualificationMapper qualificationMapper;

    RoleMapper roleMapper;

    @Autowired
    public TraineeMapper (QualificationMapper qualificationMapper, RoleMapper roleMapper) {
        this.qualificationMapper = qualificationMapper;
        this.roleMapper = roleMapper;
    }

    public Trainee toTrainee (TraineeDto traineeDto) {
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
        trainee.setTrainersId(traineeDto.getTrainersId());
        trainee.setQualification(qualificationMapper.toQualification(traineeDto.getQualificationDto()));
        trainee.setRole(roleMapper.toRole(traineeDto.getRoleDto()));
        return trainee;
    }

    public TraineeDto toTraineeDto (Trainee trainee) {
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
        traineeDto.setQualificationDto(qualificationMapper.toQualificationDto(trainee.getQualification()));
        traineeDto.setRoleDto(roleMapper.toRoleDto(trainee.getRole()));
        List<Integer> trainersId = new ArrayList<>();
        for (Trainer trainer : trainee.getTrainers()) {
            trainersId.add(trainer.getId());
        }
        traineeDto.setTrainersId(trainersId);
        return traineeDto;
    }
}
