package com.ideas2it.employee.dao.impl;

import com.ideas2it.employee.model.Trainer;
import com.ideas2it.employee.model.Role;
import com.ideas2it.employee.model.Qualification;
import com.ideas2it.employee.dao.TrainerDao;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.stereotype.Component;


/**
 * <h1> Trainee Dao </h1>
 * <p>
 * It provides service for CRUD operations.
 * </P>
 * version 1.0
 * @author ruban 11/08/22
 **/
@Component
public class TrainerDaoImpl implements TrainerDao {
    private Session session;
    private SessionFactory factory = new Configuration().configure("hibernate/properties/hibernate.cfg.xml").buildSessionFactory();
    private static final Logger logger = LogManager.getLogger(TrainerDaoImpl.class);

    /**
     * <p>
     * This method will add trainer in database.
     * </p>
     * @return return nothing
     **/
    @Override
    public void insertTrainer(Trainer trainer) {
        logger.info("Trainer Insert to DB Method in Dao");
        try {
            session = factory.openSession();
            Transaction transaction = session.beginTransaction();
            Role roleResult = (Role) session.createQuery("from Role where description = :description")
                    .setParameter("description", trainer.getEmployee().getRole().getDescription()).uniqueResult();
            if (roleResult != null) {
                trainer.getEmployee().setRole(roleResult);
            }
            Qualification qualificationResult = (Qualification) session.createQuery("from Qualification where description = :description")
                                               .setParameter("description", trainer.getEmployee().getQualification().getDescription())
                                               .uniqueResult();
            if (qualificationResult != null) {
                trainer.getEmployee().setQualification(qualificationResult);
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
     * @return returns trainers from database.
     **/
    @Override
    public List<Trainer> retrieveTrainer() {
        logger.info("Trainer List Method in Dao");
        List<Trainer> trainerDetails = new ArrayList<Trainer>();
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
    @Override
    public boolean deleteTrainerById(int empId) {
        logger.info("Delete Trainer Method in Dao");
        boolean isDeleted = false;
        session = factory.openSession();
        Transaction transaction = session.beginTransaction();
        Trainer result = (Trainer) session.createQuery("from Trainer where employee.id = :id")
                         .setParameter("id", empId).uniqueResult();
        if (result != null) {
            session.remove(result);
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
    @Override
    public Trainer retrieveTrainerById(int empId) {
        logger.info("Retrieve trainer List By Id Method in Dao");
        Trainer trainer = null;
            session = factory.openSession();
            Transaction transaction = session.beginTransaction();
            Trainer result = (Trainer) session.createQuery("from Trainer where employee.id = :id")
                              .setParameter("id", empId)
                              .uniqueResult();
            if (result != null) {
                trainer = result;
            }
            transaction.commit();
        return trainer;
    }
}
