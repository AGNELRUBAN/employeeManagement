<%@ page import = "com.ideas2it.employee.model.Trainer" %>
<%@ page import = "com.ideas2it.employee.model.Trainee" %>
<html>
 <head>
  <title> Add/Update Trainee </title>
 </head>

 <body style="background-color:FFF8DC">
  <% String choice = request.getParameter("action");
      Trainee trainee =null;
      String name = "";
      String gender = "";
      String qualification ="";
      String emailId = "";
      String dateOfBirth = "";
      String dateOfJoining ="";
      String address = "";
      String phoneNumber = "";
      String adhaarNumber = "";
      String department = "";
      String salary = "";
      String trainersId ="";
      String heading = "Add Trainee";
      if(choice.equals("updateTrainee")) {
          trainee = (Trainee)request.getAttribute("trainee");
           name = trainee.getEmployee().getEmployeeName();
           gender = trainee.getEmployee().getGender();
           qualification = trainee.getEmployee().getQualification().getDescription();
           emailId = trainee.getEmployee().getEmailId();
           dateOfBirth = String.valueOf(trainee.getEmployee().getDateOfBirth());
           dateOfJoining = trainee.getEmployee().getDateOfJoining();
           address = trainee.getEmployee().getAddress();
           phoneNumber = String.valueOf(trainee.getEmployee().getPhoneNumber());
           adhaarNumber = trainee.getEmployee().getAdhaarNumber();
           department = trainee.getEmployee().getDepartment();
           salary = String.valueOf(trainee.getSalary());
           for (Trainer trainer : trainee.getTrainers()) {
               trainersId = String.join(",", String.valueOf(trainer.getEmployee().getId()), trainersId);
           }
           heading = "Update Trainee";
      }
      session.setAttribute("trainee", trainee);
   %>
    <h3 style="color:blue"> <%= heading %> </h3>
    <form action="employee?action=addOrUpdateTrainee&choice=<%=choice%>" method="post">
    Name : <br><input type="text" name="name" value="<%= name %>" required/></br><br>
    Gender : <br><input type="radio" id="male" name="gender" value="male" <%=(gender.equals("male") ? "checked = checked" : "")%>>
            <label for="male">male</label><br>
            <input type="radio" id="female" name="gender" value="female" <%=(gender.equals("female") ? "checked = checked" : "")%>>
            <label for="female"> female</label><br>
            <input type="radio" id="others" name="gender" value="others" <%=(gender.equals("others") ? "checked = checked" : "")%>>
            <label for="others"> others </label><br><br>
    Qualification :<br> <input type="text" name="qualification" value="<%= qualification %>" required/></br></br>
    Email :<br> <input type="emailId" name="emailId" value="<%= emailId %>" required/></br></br>
    Date of birth :<br> <input type="date" name="dateOfBirth" value="<%= dateOfBirth %>" required/></br></br>
    Date of Joining :<br> <input type="date" name="dateOfJoining" value="<%= dateOfJoining %>" required/></br></br>
   Address :<br> <input type="text" name="address" value="<%= address %>" required/></br></br>
   Phone Number : <br><input type="number" name="phoneNumber" value="<%= phoneNumber %>" required/></br></br>
   AdhaarNumber :<br> <input type="number" name="adhaarNumber" value="<%= adhaarNumber %>" required/></br></br>
   Department :<br> <input type="text" name="department" value="<%= department %>" required/></br></br>
   salary :<br> <input type="number" name="salary" value="<%= salary %>" required/></br></br>
   Trainers Id : </br> <input type="text" name="trainersId" value="<%= trainersId %>" required/> </br>
   </br><input type="submit" value="<%= heading %>"/>
  </form>
  </body>
 </html>
