package com.ideas2it.employee.service;

import com.ideas2it.employee.dto.TraineeDto;
import com.ideas2it.employee.model.Trainee;

import java.util.List;

/**
 * <h1> Trainee Service Interface </h1>
 * <p>
 * This class implements only method declarations.
 * </P>
 * version 1.0
 * @author ruban 11/08/22
 **/
public interface TraineeService {

    List<Integer> addTrainee(TraineeDto traineeDto);

    List<TraineeDto> getTrainees();

    boolean deleteByTraineeId(int empId);

    TraineeDto retrieveTraineeById(int empId);
}
