package com.ideas2it.employee.dao;

import com.ideas2it.employee.model.Trainee;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * <h1> Trainee Dao Interface </h1>
 * <p>
 * It provides only method declarations.
 * </P>
 * version 1.0
 * @author ruban 11/08/22
 **/
public interface TraineeDao extends JpaRepository<Trainee, Integer> {

}
