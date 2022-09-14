package com.ideas2it.employee.dao;

import com.ideas2it.employee.model.Trainer;

import java.util.ArrayList;
import java.util.List;

/**
 * <h1> Trainer Dao Interface </h1>
 * <p> 
 * It provides only method declarations.
 * </P>
 * version 1.0
 * @author ruban 11/08/22
 **/
public interface TrainerDao {

    public void insertTrainer(Trainer trainer);
  
    public List<Trainer> retrieveTrainer();
    
    public boolean deleteTrainerById(int empId);

    public Trainer retrieveTrainerById(int empId);

    public void updateTrainer(Trainer trainer);
}