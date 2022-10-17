package com.ideas2it.employee.mapper;

import com.ideas2it.employee.dto.TrainerDto;
import com.ideas2it.employee.model.Trainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TrainerMapper {

    QualificationMapper qualificationMapper;
    RoleMapper roleMapper;

    @Autowired
    public TrainerMapper (QualificationMapper qualificationMapper, RoleMapper roleMapper) {
        this.qualificationMapper = qualificationMapper;
        this.roleMapper = roleMapper;
    }

    public Trainer toTrainer (TrainerDto trainerDto) {
        Trainer trainer = new Trainer();
        trainer.setId(trainerDto.getId());
        trainer.setEmployeeName(trainerDto.getEmployeeName());
        trainer.setGender(trainerDto.getGender());
        trainer.setEmailId(trainerDto.getEmailId());
        trainer.setDateOfBirth(trainerDto.getDateOfBirth());
        trainer.setDateOfJoining(trainerDto.getDateOfJoining());
        trainer.setAddress(trainerDto.getAddress());
        trainer.setPhoneNumber(trainerDto.getPhoneNumber());
        trainer.setAdhaarNumber(trainerDto.getAdhaarNumber());
        trainer.setDepartment(trainerDto.getDepartment());
        trainer.setQualification(qualificationMapper.toQualification(trainerDto.getQualificationDto()));
        trainer.setRole(roleMapper.toRole(trainerDto.getRoleDto()));
        return trainer;
    }

    public TrainerDto toTrainerDto (Trainer trainer) {
        TrainerDto trainerDto = new TrainerDto();
        trainerDto.setId(trainer.getId());
        trainerDto.setEmployeeName(trainer.getEmployeeName());
        trainerDto.setGender(trainer.getGender());
        trainerDto.setEmailId(trainer.getEmailId());
        trainerDto.setDateOfBirth(trainer.getDateOfBirth());
        trainerDto.setDateOfJoining(trainer.getDateOfJoining());
        trainerDto.setAddress(trainer.getAddress());
        trainerDto.setPhoneNumber(trainer.getPhoneNumber());
        trainerDto.setAdhaarNumber(trainer.getAdhaarNumber());
        trainerDto.setDepartment(trainer.getDepartment());
        trainerDto.setQualificationDto(qualificationMapper.toQualificationDto(trainer.getQualification()));
        trainerDto.setRoleDto(roleMapper.toRoleDto(trainer.getRole()));
        return trainerDto;
    }
}
