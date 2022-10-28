package com.ideas2it.employee.dao;

import com.ideas2it.employee.model.Trainee;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * <h1> Trainee Dao Interface </h1>
 * <p>
 * It provides only method declarations.
 * </P>
 * version 1.0
 * @author ruban 11/08/22
 **/
@Repository
public interface TraineeDao extends JpaRepository<Trainee, Integer> {
    @Query("Select te from Trainee te join te.trainers er where er.id = ?1")
    List<Trainee> retrieveTraineesByTrainerId(int id);
}
