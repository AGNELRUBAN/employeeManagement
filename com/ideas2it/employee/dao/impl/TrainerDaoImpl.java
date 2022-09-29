package com.ideas2it.employee.dao.impl;

import com.ideas2it.employee.model.Trainer;
import com.ideas2it.employee.model.Role;
import com.ideas2it.employee.model.Qualification;
import com.ideas2it.employee.model.Employee;
import com.ideas2it.employee.dao.TrainerDao;

import java.util.ArrayList;
import java.util.List;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Date;
import java.time.LocalDate;
import org.hibernate.Session;    
import org.hibernate.SessionFactory;    
import org.hibernate.Transaction;    
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.cfg.Configuration;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;


/**
 * <h1> Trainee Dao </h1>
 * <p> 
 * It provides service for CRUD operations.
 * </P>
 * version 1.0
 * @author ruban 11/08/22
 **/
public class TrainerDaoImpl implements TrainerDao {
    private Session session;
    private SessionFactory factory;
    private static Logger logger = LogManager.getLogger(TrainerDaoImpl.class);

    /**
     * <p>
     * This method will add trainer in database.
     * </p>
     * @param trainee object
     * @return return nothing
     **/
    public void insertTrainer(Trainer trainer) {
        logger.info("Trainer Insert to DB Method in Dao");
        try {
            factory = new Configuration().configure().buildSessionFactory();  
            session = factory.openSession();  
            Transaction transaction = session.beginTransaction(); 
            Criteria criteria = session.createCriteria(Role.class);
            criteria.add(Restrictions.eq("description", trainer.getEmployee().getRole().getDescription()));
            List<Role> roleResult = criteria.list();
            if (roleResult.size() > 0) {
                trainer.getEmployee().setRole(roleResult.get(0));
            }
            Criteria criteriaa = session.createCriteria(Qualification.class);
            criteriaa.add(Restrictions.eq("description", trainer.getEmployee().getQualification().getDescription()));
            List<Qualification> roleResults = criteriaa.list();
            if (roleResults.size() > 0) {
	        trainer.getEmployee().setQualification(roleResults.get(0));
            }
            session.saveOrUpdate(trainer);
            transaction.commit();
        } catch(Throwable ex) {
            ex.printStackTrace();
        }
    }

    /**
     * <p>
     * This method will return trainer object.
     * </p>
     * @param no parameters
     * @return returns trainers from database.
     **/
    public List<Trainer> retrieveTrainer() {
        logger.info("Trainer List Method in Dao");
        List<Trainer> trainerDetails = new ArrayList<Trainer>();
        factory = new Configuration().configure().buildSessionFactory();   
        session = factory.openSession();  
        Transaction transaction = session.beginTransaction(); 
        String query = "from Trainer";
        trainerDetails = session.createQuery(query).list();
        transaction.commit(); 
        session.close();
        return trainerDetails;   
    }

    /**
     * <p>
     * This method delete tariner object by using employee id
     * </p>
     * @param {@link int} empId
     * @return returns boolean
     **/
    public boolean deleteTrainerById(int empId) {
        logger.info("Delete Trainer Method in Dao");
        boolean isDeleted = false;
        factory = new Configuration().configure().buildSessionFactory();
        session = factory.openSession();
        Transaction transaction = session.beginTransaction();
        Criteria criteria = session.createCriteria(Trainer.class);
        criteria.add(Restrictions.eq("employee.id", empId));
        List<Trainer> results = criteria.list();
        if (results.size() > 0) {
            session.remove(results.get(0));
            isDeleted = true;
        }
        transaction.commit();
        session.close();
        return isDeleted;
    }  

    /**
     * <p>
     * This method check the trainer Id and retrieve trainer object.
     * </p>
     * @param {@link int} empId
     * @return returns Trainer object
     **/
    public Trainer retrieveTrainerById(int empId) {
        logger.info("Retrieve trainer List By Id Method in Dao");
        Trainer trainer = null;
        List<Trainer> available = new ArrayList<Trainer>();
        factory = new Configuration().configure().buildSessionFactory();   
        session = factory.openSession();  
        Transaction transaction = session.beginTransaction(); 
        String query = "from Trainer where employee.id =" + empId;
        available = session.createQuery(query).list();
        transaction.commit();
        if (available.size() > 0) {
            trainer = available.get(0);
        } 
        session.close();
        return trainer;
    }                      
}
        