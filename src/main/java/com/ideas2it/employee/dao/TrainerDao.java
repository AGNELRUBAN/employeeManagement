package com.ideas2it.employee.dao;

import com.ideas2it.employee.model.Trainer;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * <h1> Trainer Dao Interface </h1>
 * <p>
 * It provides only method declarations.
 * </P>
 * version 1.0
 * @author ruban 11/08/22
 **/
public interface TrainerDao extends JpaRepository<Trainer, Integer> {


}