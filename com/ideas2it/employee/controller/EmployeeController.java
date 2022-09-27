package com.ideas2it.employee.controller;

import com.ideas2it.employee.model.Trainer;
import com.ideas2it.employee.model.Trainee;
import com.ideas2it.employee.model.Role;
import com.ideas2it.employee.model.Qualification;
import com.ideas2it.employee.service.impl.TrainerServiceImpl;
import com.ideas2it.employee.service.impl.TraineeServiceImpl;
import com.ideas2it.employee.service.TrainerService;
import com.ideas2it.employee.service.TraineeService;
import com.ideas2it.employee.utility.StringUtility;
import com.ideas2it.employee.utility.DateUtility;
import com.ideas2it.employee.exception.EmployeeNotFound;
import com.ideas2it.employee.exception.BadRequest;

import java.util.InputMismatchException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;  
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
import javax.servlet.ServletException;
import java.io.IOException;
import java.io.PrintWriter;  

/**
 * <p> 
 * It implements methods for add, view, delete and update opertions for Employees
 * </p>
 *
 * @author Ruban
 * @version 1.0 12/08/22
 * 
 **/ 
public class EmployeeController extends HttpServlet {
    private TrainerService trainerService = new TrainerServiceImpl();
    private TraineeService traineeService = new TraineeServiceImpl();

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException { 
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

    public void viewTrainer(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        List<Trainer> trainers = trainerService.getTrainers();
        req.setAttribute("trainers", trainers);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/viewTrainer.jsp");
        dispatcher.forward(req, res);
    } 

    public void viewTrainee(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        List<Trainee> trainees = traineeService.getTrainees();
        req.setAttribute("trainees", trainees);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/viewTrainee.jsp");
        dispatcher.forward(req, res);
    }      

    public void deleteTrainer(HttpServletRequest req, HttpServletResponse res) throws ServletException,IOException {
        res.setContentType("text/html"); 
        PrintWriter out = res.getWriter();
        int id = Integer.parseInt(req.getParameter("id"));
        try {
            trainerService.deleteByTrainerId(id);
            out.println("EmployeeId" +" " +id +" "+ "Deleted Successfully");
        } catch (EmployeeNotFound e) {
            out.println(e.getMessage());
        }
    }

    public void deleteTrainee(HttpServletRequest req, HttpServletResponse res) throws ServletException,IOException {
        res.setContentType("text/html"); 
        PrintWriter out = res.getWriter();
        int id = Integer.parseInt(req.getParameter("id"));
        try {
            traineeService.deleteByTraineeId(id);
            out.println("EmployeeId" +" " +id +" "+ "Deleted Successfully");
        } catch (EmployeeNotFound e) {
            out.println(e.getMessage());
        }
    }

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
        List<String> trainersId = new ArrayList<>();
        for (String id : values.split(",")) {
            trainersId.add(id);
        }
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
            out.println(e.getMessage());
            RequestDispatcher rd = req.getRequestDispatcher("/index.html");
            rd.include(req, res);
        }
    }    
}