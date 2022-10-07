package com.ideas2it.employee.dao.impl;

import com.ideas2it.employee.model.Trainee;
import com.ideas2it.employee.model.Role;
import com.ideas2it.employee.model.Qualification;
import com.ideas2it.employee.dao.TraineeDao;


import com.ideas2it.employee.service.impl.TraineeServiceImpl;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import java.util.ArrayList;
import java.util.List;

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
public class TraineeDaoImpl implements TraineeDao {
    private Session session;
    private SessionFactory factory = new Configuration().configure("hibernate/properties/hibernate.cfg.xml").buildSessionFactory();
    private static final Logger logger = LogManager.getLogger(TraineeServiceImpl.class);

    /**
     * <p>
     * This method will add trainee details in database.
     * </p>
     * @param trainee object
     * @return return nothing
     **/
    public void insertTrainee(Trainee trainee) {
        logger.info("Trainee Insert method in Dao");
        try {
           // SessionFactory factory;
            session = factory.openSession();
            Transaction transaction = session.beginTransaction();
            Role roleResult = (Role) session.createQuery("from Role where description = :description")
                    .setParameter("description", trainee.getEmployee().getRole().getDescription())
                    .uniqueResult();
            if (roleResult == null) {
                trainee.getEmployee().setRole(roleResult);
            }
            Qualification result = (Qualification) session.createQuery("from Qualification where description = :description")
                    .setParameter("description", trainee.getEmployee().getQualification().getDescription())
                    .uniqueResult();
            if (result == null) {
                trainee.getEmployee().setQualification(result);
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
     * @return returns trainees from the database
     **/
    public List<Trainee> retrieveTrainee() {
        logger.info("Retrieve Trainee method in Dao");
        List<Trainee> traineeDetails = new ArrayList<Trainee>();
       // factory = new Configuration().configure().buildSessionFactory();
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
        logger.info("Delete Trainee method in Dao");
        boolean isDeleted = false;
       // factory = new Configuration().configure().buildSessionFactory();
        session = factory.openSession();
        Transaction transaction = session.beginTransaction();
        Trainee result = (Trainee) session.createQuery("from Trainee where employee.id = :id")
                .setParameter("id", empId).uniqueResult();
        if (result == null) {
            session.remove(result);
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
        logger.info("Retrieve Trainee By Id method in Dao");
        Trainee trainee = null;
        List<Trainee> available = new ArrayList<Trainee>();
       // factory = new Configuration().configure().buildSessionFactory();
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
