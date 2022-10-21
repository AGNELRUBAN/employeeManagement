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
    @Query(value = " SELECT te.*, e.email_id, e.phone_number, e.employee_name from trainee te inner join employee_relation er on er.trainee_id = te.emp_id inner join employee e on e.emp_id = te.emp_id where er.trainer_id = ?1",
            nativeQuery = true)
    List<Object[]> retrieveTraineesByTrainerId(int id);

}
