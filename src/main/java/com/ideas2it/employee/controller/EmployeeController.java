package com.ideas2it.employee.controller;

import com.ideas2it.employee.exception.BadRequest;
import com.ideas2it.employee.model.Trainer;
import com.ideas2it.employee.model.Trainee;
import com.ideas2it.employee.exception.EmployeeNotFound;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
import javax.servlet.ServletException;
import java.io.IOException;
import java.io.PrintWriter;

import com.ideas2it.employee.service.TraineeService;
import com.ideas2it.employee.service.TrainerService;
import com.ideas2it.employee.service.impl.TraineeServiceImpl;
import com.ideas2it.employee.service.impl.TrainerServiceImpl;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


/**
 * <p>
 * It implements methods for add, view, delete and update opertions for Employees
 * </p>
 *
 * @author Ruban
 * @version 1.0 12/08/22
 *
 **/
@Controller
public class EmployeeController extends HttpServlet {
    private final TrainerService trainerService = new TrainerServiceImpl();
    private final TraineeService traineeService = new TraineeServiceImpl();
    private static final Logger logger = LogManager.getLogger(EmployeeController.class);



    @RequestMapping("/")
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

    @RequestMapping("/addOrUpdateTrainer")
    public String addOrUpdateTrainer(@ModelAttribute("trainer") Trainer trainer, RedirectAttributes redirectAttributes) {
        try {
            trainerService.addTrainer(trainer);
            if (trainer.getEmployee().getId() == 0) {
                redirectAttributes.addFlashAttribute("msg", trainer.getEmployee().getEmployeeName() + " Inserted Successfully");
            } else {
                redirectAttributes.addFlashAttribute("msg", trainer.getEmployee().getEmployeeName() + " Updated Successfully");
            }
        } catch (Exception exception) {
            redirectAttributes.addFlashAttribute("msg", exception.getMessage());
        }
        return "redirect:/viewTrainer";
    }

 // @RequestMapping("/add")
   /* public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String choice = req.getParameter("action");
        switch(choice) {
            case "addOrUpdateTrainer":
                addOrUpdateTrainer(req, res);
                break;

            case "deleteTrainer":
                deleteTrainer(req, res);
                break;

            case "viewTrainer":
                viewTrainer(req, res);
                break;

            case "updateTrainer":
                updateTrainer(req, res);
                break;

            case "addOrUpdateTrainee":
                addOrUpdateTrainee(req, res);
                break;

            case "deleteTrainee":
                deleteTrainee(req, res);
                break;

            case "viewTrainee":
                viewTrainee(req, res);
                break;

            case "updateTrainee":
                updateTrainee(req, res);
                break;
        }
    }
*/
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



    //@RequestMapping("/add")
    public void addOrUpdateTrainer(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("text/html");
        PrintWriter out = res.getWriter();
        HttpSession session = req.getSession();
        String name = req.getParameter("name");
        String gender = req.getParameter("gender");
        String emailId = req.getParameter("emailId");
        String dateOfBirth = req.getParameter("dateOfBirth");
        String dateOfJoining = req.getParameter("dateOfJoining");
        String address = req.getParameter("address");
        String phoneNumber = req.getParameter("phoneNumber");
        String adhaarNumber = req.getParameter("adhaarNumber");
        String department = req.getParameter("department");
        String trainerExperience = req.getParameter("trainerExperience");
        String qualification = req.getParameter("qualification");
        String choice = req.getParameter("choice");
        Trainer trainer = (Trainer)session.getAttribute("trainer");
        try {
            trainerService.addTrainer(trainer, name, gender, emailId,
                    dateOfBirth, dateOfJoining, address,
                    phoneNumber, adhaarNumber, department,
                    trainerExperience, qualification);
            out.println("<h1>");
            out.println((trainer != null ? " Updated " : " Inserted ") + "Successfully");
            out.println("<h1>");
            RequestDispatcher rd=req.getRequestDispatcher("/employee?action=viewTrainer");
            rd.include(req, res);
        } catch (BadRequest e) {
            logger.warn(e.getMessage());
            out.println(e.getMessage());
            req.setAttribute("trainer", trainer);
            RequestDispatcher rd=req.getRequestDispatcher("/index.html");
            rd.include(req, res);
        } finally {
            out.close();
        }

    }

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException,IOException {
        doPost(req, res);
    }

   /* public void viewTrainer(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        List<Trainer> trainers = trainerService.getTrainers();
        req.setAttribute("trainers", trainers);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/viewTrainer.jsp");
        dispatcher.forward(req, res);
    }*/

  /*  public void viewTrainee(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        List<Trainee> trainees = traineeService.getTrainees();
        req.setAttribute("trainees", trainees);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/viewTrainee.jsp");
        dispatcher.forward(req, res);
    }*/



    @GetMapping("/deleteTrainer")
    public String deleteTrainer(@RequestParam int id, RedirectAttributes redirectAttributes)  {
        trainerService.deleteByTrainerId(id);
        redirectAttributes.addFlashAttribute("msg",id + " SuccessFully Deleted");
        return "redirect:/viewTrainer";
    }

    @GetMapping("/deleteTrainee")
    public String deleteTrainee(@RequestParam int id, RedirectAttributes redirectAttributes)  {
        traineeService.deleteByTraineeId(id);
        redirectAttributes.addFlashAttribute("msg",id + " SuccessFully Deleted");
        return "redirect:/viewTrainee";
    }


  /*  public void deleteTrainee(HttpServletRequest req, HttpServletResponse res) throws ServletException,IOException {
        res.setContentType("text/html");
        PrintWriter out = res.getWriter();
        int id = Integer.parseInt(req.getParameter("id"));
        try {
            traineeService.deleteByTraineeId(id);
            out.println("EmployeeId" +" " +id +" "+ "Deleted Successfully");
        } catch (EmployeeNotFound e) {
            logger.warn(e.getMessage());
            out.println(e.getMessage());
        }
    }*/

    public void updateTrainer(HttpServletRequest req, HttpServletResponse res) throws ServletException,IOException {
        res.setContentType("text/html");
        PrintWriter out = res.getWriter();
        int id = Integer.parseInt(req.getParameter("id"));
        Trainer trainer;
        try {
            trainer = trainerService.retrieveTrainerById(id);
            req.setAttribute("trainer", trainer);
            RequestDispatcher rd = req.getRequestDispatcher("/addOrUpdateTrainer.jsp");
            rd.forward(req, res);
        } catch(EmployeeNotFound e) {
            logger.warn(e.getMessage());
            out.println(e.getMessage());
            RequestDispatcher rd = req.getRequestDispatcher("/index.html");
            rd.include(req, res);
        }
    }

    public void addOrUpdateTrainee(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("text/html");
        PrintWriter out = res.getWriter();
        HttpSession session = req.getSession();
        String name = req.getParameter("name");
        String gender = req.getParameter("gender");
        String emailId = req.getParameter("emailId");
        String dateOfBirth = req.getParameter("dateOfBirth");
        String dateOfJoining = req.getParameter("dateOfJoining");
        String address = req.getParameter("address");
        String phoneNumber = req.getParameter("phoneNumber");
        String adhaarNumber = req.getParameter("adhaarNumber");
        String department = req.getParameter("department");
        String salary = req.getParameter("salary");
        String qualification = req.getParameter("qualification");
        String values = req.getParameter("trainersId");
        List<String> trainersId = new ArrayList<String>();
        Collections.addAll(trainersId, values.split(","));
        Trainee trainee = (Trainee) session.getAttribute("trainee");
        try {
            traineeService.addTrainee(trainee, name, gender, emailId,
                    dateOfBirth, dateOfJoining, address,
                    phoneNumber, adhaarNumber, department,
                    salary, qualification, trainersId);
            out.println("<h1>");
            out.println((trainee != null ? " Updated " : " Inserted ") + "Successfully");
            out.println("<h1>");
            RequestDispatcher rd=req.getRequestDispatcher("/employee?action=viewTrainee");
            rd.include(req, res);
        } catch (BadRequest e) {
            logger.warn(e.getMessage());
            out.println("<h6>");
            out.println(e.getMessage());
            out.println("</h6>");
            RequestDispatcher rd=req.getRequestDispatcher("/index.html");
            rd.include(req, res);
        } finally {
            out.close();
        }
    }

    public void updateTrainee(HttpServletRequest req, HttpServletResponse res) throws ServletException,IOException {
        res.setContentType("text/html");
        PrintWriter out = res.getWriter();
        int id = Integer.parseInt(req.getParameter("id"));
        Trainee trainee;
        try {
            trainee = traineeService.retrieveTraineeById(id);
            req.setAttribute("trainee", trainee);
            RequestDispatcher rd = req.getRequestDispatcher("/addOrUpdateTrainee.jsp");
            rd.forward(req, res);
        } catch(EmployeeNotFound e) {
            logger.warn(e.getMessage());
            out.println(e.getMessage());
            RequestDispatcher rd = req.getRequestDispatcher("/index.html");
            rd.include(req, res);
        }
    }
}