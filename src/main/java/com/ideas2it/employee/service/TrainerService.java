package com.ideas2it.employee.service;

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

    List<Integer> addTrainer(Trainer trainer);

    List<Trainer> getTrainers();

    boolean deleteByTrainerId(int empId);

    Trainer retrieveTrainerById(int empId);
}
