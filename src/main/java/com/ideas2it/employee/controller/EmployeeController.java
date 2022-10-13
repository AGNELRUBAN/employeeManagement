package com.ideas2it.employee.controller;

import com.ideas2it.employee.model.Trainee;
import com.ideas2it.employee.model.Trainer;
import com.ideas2it.employee.service.TraineeService;
import com.ideas2it.employee.service.TrainerService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;


/**
 * <p>
 * It implements methods for add, view, delete and update operations for Employees
 * </p>
 *
 * @ author Ruban
 * @ version 1.0 12/08/22
 *
 **/
@Controller
public class EmployeeController {
    @Autowired
    private  TrainerService trainerService;
    @Autowired
    private TraineeService traineeService;
    private static final Logger logger = LogManager.getLogger(EmployeeController.class);

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/trainerForm")
    public ModelAndView showTrainerForm() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("trainer", new Trainer());
        modelAndView.addObject("action", "addTrainer");
        modelAndView.setViewName("addOrUpdateTrainer");
        return modelAndView;
    }

    @GetMapping("/traineeForm")
    public ModelAndView showTraineeForm() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("trainers", trainerService.getTrainers());
        modelAndView.addObject("trainee", new Trainee());
        modelAndView.addObject("action", "addTrainee");
        modelAndView.setViewName("addOrUpdateTrainee");
        return modelAndView;
    }

    @RequestMapping("/addOrUpdateTrainee")
    public String addOrUpdateTrainee(@ModelAttribute("trainee") Trainee trainee, @RequestParam("action") String action, RedirectAttributes redirectAttributes) {
        try {
            traineeService.addTrainee(trainee);
            if ("addTrainee".equals(action)) {
                redirectAttributes.addFlashAttribute("msg", trainee.getEmployeeName() + " Inserted Successfully");
            } else {
                redirectAttributes.addFlashAttribute("msg", trainee.getEmployeeName() + " Updated Successfully");
            }
        } catch (Exception exception) {
            redirectAttributes.addFlashAttribute("msg", exception.getMessage());
        }
        return "redirect:/viewTrainee";
    }

    @RequestMapping("/addOrUpdateTrainer")
    public String addOrUpdateTrainer(@ModelAttribute("trainer") Trainer trainer, RedirectAttributes redirectAttributes) {
        try {
            trainerService.addTrainer(trainer);
            if ("addTrainer".equals("action")) {
                redirectAttributes.addFlashAttribute("msg", trainer.getEmployeeName() + " Inserted Successfully");
            } else {
                redirectAttributes.addFlashAttribute("msg", trainer.getEmployeeName() + " Updated Successfully");
            }
        } catch (Exception exception) {
            redirectAttributes.addFlashAttribute("msg", exception.getMessage());
        }
        return "redirect:/viewTrainer";
    }

    @RequestMapping (value = "/viewTrainer")
    public ModelAndView viewTrainer() {
        List<Trainer> trainers = trainerService.getTrainers();
        ModelAndView modelAndView = new ModelAndView("viewTrainer");
        modelAndView.addObject("trainers", trainers);
        return modelAndView;
    }

    @RequestMapping(value ="/viewTrainee")
    public ModelAndView viewTrainee() {
        List<Trainee> trainees = traineeService.getTrainees();
        ModelAndView modelAndView = new ModelAndView("viewTrainee");
        modelAndView.addObject("trainees", trainees);
        return modelAndView;
    }

    @GetMapping("/deleteTrainer")
    public String deleteTrainer(@RequestParam int id, RedirectAttributes redirectAttributes)  {
        trainerService.deleteByTrainerId(id);
        //  redirectAttributes.addFlashAttribute("msg",id + " SuccessFully Deleted");
        return "redirect:/viewTrainer";
    }

    @GetMapping("/deleteTrainee")
    public String deleteTrainee(@RequestParam int id, RedirectAttributes redirectAttributes)  {
        traineeService.deleteByTraineeId(id);
        // redirectAttributes.addFlashAttribute("msg",id + " SuccessFully Deleted");
        return "redirect:/viewTrainee";
    }

    @GetMapping("/updateTrainer")
    public String getTrainerById(@RequestParam("id") int trainerId, Model model) {
        Trainer trainer = trainerService.retrieveTrainerById(trainerId);
        model.addAttribute("trainer", trainer);
        model.addAttribute("action", "updateTrainer");
        return "addOrUpdateTrainer";
    }

    @GetMapping("/updateTrainee")
    public String getTraineeById(@RequestParam("id") int traineeId, Model model) {
        Trainee trainee = traineeService.retrieveTraineeById(traineeId);
        model.addAttribute("trainers", trainerService.getTrainers());
        model.addAttribute("trainee", trainee);
        model.addAttribute("action", "updateTrainee");
        return "addOrUpdateTrainee";
    }
}