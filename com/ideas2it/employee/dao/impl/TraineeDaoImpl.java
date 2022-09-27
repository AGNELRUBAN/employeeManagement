package com.ideas2it.employee.dao.impl;

import com.ideas2it.employee.model.Trainee;
import com.ideas2it.employee.model.Trainer;
import com.ideas2it.employee.model.Role;
import com.ideas2it.employee.model.Qualification;
import com.ideas2it.employee.model.Employee;
import com.ideas2it.employee.dao.TraineeDao;
import com.ideas2it.employee.exception.EmployeeNotFound;


import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * <h1> Trainee Dao </h1>
 * <p> 
 * It provides service for CRUD operations.
 * </P>
 * version 1.0
 * @author ruban 11/08/22
 **/
public class TraineeDaoImpl implements TraineeDao {
    private Session session;
    private SessionFactory factory;
    
    /**
     * <p>
     * This method will add trainee details in database.
     * </p>
     * @param trainee object
     * @return return nothing
     **/
    public void insertTrainee(Trainee trainee) {
        try {
            factory = new Configuration().configure().buildSessionFactory(); 
            session = factory.openSession();  
            Transaction transaction = session.beginTransaction(); 
            Criteria criteria = session.createCriteria(Role.class);
            criteria.add(Restrictions.eq("description", trainee.getEmployee().getRole().getDescription()));
            List<Role> roleResult = criteria.list();
	    if (roleResult.size() > 0) {
	        trainee.getEmployee().setRole(roleResult.get(0));
            }
            Criteria criteriaa = session.createCriteria(Qualification.class);
            criteriaa.add(Restrictions.eq("description", trainee.getEmployee().getQualification().getDescription()));
            List<Qualification> roleResults = criteriaa.list();
	    if (roleResults.size() > 0) {
	        trainee.getEmployee().setQualification(roleResults.get(0));
            } 
            session.saveOrUpdate(trainee);
            transaction.commit();   
        } catch(Throwable ex) {
            ex.printStackTrace();
        } finally {
            session.close();
            factory.close();
        }
    }

    /**
     * <p>
     * This method will return trainee object from database.
     * </p>
     * @param no parameters
     * @return returns trainees from the database
     **/
    public List<Trainee> retrieveTrainee() {
        List<Trainee> traineeDetails = new ArrayList<Trainee>();
        factory = new Configuration().configure().buildSessionFactory();  
        session = factory.openSession();  
        Transaction transaction = session.beginTransaction(); 
        String query = "from Trainee";
        traineeDetails = session.createQuery(query).list();
        transaction.commit(); 
        session.close();
        return traineeDetails;
    }

    /**
     * <p>
     * This method delete trainee object by using employee id
     * </p>
     * @param {@link int} empId
     * @return returns boolean
     **/
    public boolean deleteTraineeById(int empId) {
        boolean isDeleted = false;
        factory = new Configuration().configure().buildSessionFactory();
	session = factory.openSession();
        Transaction transaction = session.beginTransaction();
        Criteria criteria = session.createCriteria(Trainee.class);
        criteria.add(Restrictions.eq("employee.id", empId));
        List<Trainee> results = criteria.list();
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
     * This method for retrieve Trainee object by Id.
     * </p>
     * @param {@link int} empId
     * @return returns trainee object
     **/
    public Trainee retrieveTraineeById(int empId) {
        Trainee trainee = null;
        List<Trainee> available = new ArrayList<Trainee>();
        factory = new Configuration().configure().buildSessionFactory();   
        session = factory.openSession();  
        Transaction transaction = session.beginTransaction(); 
        String query = "from Trainee where employee.id =" + empId;
        available = session.createQuery(query).list();
        transaction.commit();
        if (available.size() > 0) {
            trainee = available.get(0);
        } 
        session.close();
        return trainee;
    }   

}
        