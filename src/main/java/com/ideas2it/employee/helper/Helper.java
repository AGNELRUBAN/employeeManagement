package com.ideas2it.employee.helper;

import com.ideas2it.employee.dao.TraineeDao;
import com.ideas2it.employee.dao.TrainerDao;
import com.ideas2it.employee.dto.TrainerDto;
import com.ideas2it.employee.model.Trainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Helper {
       private TrainerDao trainerDao;

       @Autowired
       public Helper(TrainerDao trainerDao) {
              this.trainerDao = trainerDao;
       }

       public List<Trainer> getTrainersFromDao (List<Integer> trainerIds) {
              List<Trainer> trainers = trainerDao.findAllById(trainerIds);
              return trainers;

       }

}
