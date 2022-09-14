package com.ideas2it.employee.dao;

import com.ideas2it.employee.model.Trainee;

import java.util.ArrayList;
import java.util.List;

/**
 * <h1> Trainee Dao Interface </h1>
 * <p> 
 * It provides only method declarations.
 * </P>
 * version 1.0
 * @author ruban 11/08/22
 **/
public interface TraineeDao {

    public void insertTrainee(Trainee trainee);
  
    public List<Trainee> retrieveTrainee(); 

    public boolean deleteTraineeById(int empId);

    public Trainee retrieveTraineeById(int empId);

    public void updateTrainee(Trainee trainee);

}