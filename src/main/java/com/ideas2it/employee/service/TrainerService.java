package com.ideas2it.employee.service;

import com.ideas2it.employee.dto.TrainerDto;
import com.ideas2it.employee.model.Trainer;

import java.util.List;


/**
 * <h1> Trainer Service Interface </h1>
 * <p>
 * It implements only method declarations.
 * </P>
 * version 1.0
 * @author ruban 11/08/22
 **/
public interface TrainerService {

    List<Integer> addTrainer(TrainerDto trainerDto);

    List<TrainerDto> getTrainers();

    boolean deleteByTrainerId(int empId);

    List<Trainer> retrieveTrainersById(List<Integer> trainerIds);

    TrainerDto getTrainerById(final int trainerId);
}
