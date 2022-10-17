package com.ideas2it.employee.controller;

import com.ideas2it.employee.dto.TraineeDto;
import com.ideas2it.employee.dto.TrainerDto;
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

    private Logger logger = LogManager.getLogger(EmployeeController.class);
    @Autowired
    private  TrainerService trainerService;
    @Autowired
    private TraineeService traineeService;


    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/trainerForm")
    public ModelAndView showTrainerForm() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("trainerDto", new TrainerDto());
        modelAndView.addObject("action", "addTrainer");
        modelAndView.setViewName("addOrUpdateTrainer");
        return modelAndView;
    }

    @GetMapping("/traineeForm")
    public ModelAndView showTraineeForm() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("trainersDto", trainerService.getTrainers());
        modelAndView.addObject("traineeDto", new TraineeDto());
        modelAndView.addObject("action", "addTrainee");
        modelAndView.setViewName("addOrUpdateTrainee");
        return modelAndView;
    }

    @RequestMapping("/addOrUpdateTrainee")
    public String addOrUpdateTrainee(@ModelAttribute("traineeDto") TraineeDto traineeDto, @RequestParam("action") String action, RedirectAttributes redirectAttributes) {
        try {
            traineeService.addTrainee(traineeDto);
            if ("addTrainee".equals(action)) {
                redirectAttributes.addFlashAttribute("msg", traineeDto.getEmployeeName() + " Inserted Successfully");
            } else {
                redirectAttributes.addFlashAttribute("msg", traineeDto.getEmployeeName() + " Updated Successfully");
            }
        } catch (Exception exception) {
            redirectAttributes.addFlashAttribute("msg", exception.getMessage());
        }
        return "redirect:/viewTrainee";
    }

    @RequestMapping("/addOrUpdateTrainer")
    public String addOrUpdateTrainer(@ModelAttribute TrainerDto trainerDto, @RequestParam("action") String action, RedirectAttributes redirectAttributes) {
        try {
            trainerService.addTrainer(trainerDto);
            if ("addTrainer".equals("action")) {
                redirectAttributes.addFlashAttribute("msg", trainerDto.getEmployeeName() + " Inserted Successfully");
            } else {
                redirectAttributes.addFlashAttribute("msg", trainerDto.getEmployeeName() + " Updated Successfully");
            }
        } catch (Exception exception) {
            redirectAttributes.addFlashAttribute("msg", exception.getMessage());
        }
        return "redirect:/viewTrainer";
    }

    @RequestMapping (value = "/viewTrainer")
    public ModelAndView viewTrainer() {
        List<TrainerDto> trainersDto = trainerService.getTrainers();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("trainersDto", trainersDto);
        return modelAndView;
    }

    @RequestMapping(value ="/viewTrainee")
    public ModelAndView viewTrainee() {
        List<TraineeDto> traineeDto = traineeService.getTrainees();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("traineeDto", traineeDto);
        return modelAndView;
    }

    @GetMapping("/deleteTrainer")
    public String deleteTrainer(@RequestParam("id") int trainerId, RedirectAttributes redirectAttributes)  {
        trainerService.deleteByTrainerId(trainerId);
         redirectAttributes.addFlashAttribute("msg",trainerId + " SuccessFully Deleted");
        return "redirect:/viewTrainer";
    }

    @GetMapping("/deleteTrainee")
    public String deleteTrainee(@RequestParam("id") int traineeId, RedirectAttributes redirectAttributes)  {
        traineeService.deleteByTraineeId(traineeId);
        // redirectAttributes.addFlashAttribute("msg",id + " SuccessFully Deleted");
        return "redirect:/viewTrainee";
    }

    @GetMapping("/updateTrainer")
    public String getTrainerById(@RequestParam("id") int trainerId, Model model) {
        TrainerDto trainerDto = trainerService.retrieveTrainerById(trainerId);
        model.addAttribute("trainerDto", trainerDto);
        model.addAttribute("action", "updateTrainer");
        return "addOrUpdateTrainer";
    }

    @GetMapping("/updateTrainee")
    public String getTraineeById(@RequestParam("id") int traineeId, Model model) {
        TraineeDto traineeDto = traineeService.retrieveTraineeById(traineeId);
        List<TrainerDto> trainersDto = trainerService.getTrainers();
        model.addAttribute("trainersDto", trainersDto);
        model.addAttribute("traineeDto", traineeDto);
        model.addAttribute("action", "updateTrainee");
        return "addOrUpdateTrainee";
    }
}