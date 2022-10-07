<%@ page import = "com.ideas2it.employee.model.Trainer" %>
<html>
 <head>
  <title > Add/Update Trainer </title>
 </head>

 <body style="background-color:FFF8DC">
  <% String choice = request.getParameter("action");
      Trainer trainer =null;
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
      String trainerExperience = "";
      String heading = "Add Trainer";
      if(choice.equals("updateTrainer")) {
          trainer = (Trainer)request.getAttribute("trainer");
           name = trainer.getEmployee().getEmployeeName();
           gender = trainer.getEmployee().getGender();
           qualification = trainer.getEmployee().getQualification().getDescription();
           emailId = trainer.getEmployee().getEmailId();
           dateOfBirth = String.valueOf(trainer.getEmployee().getDateOfBirth());
           dateOfJoining = trainer.getEmployee().getDateOfJoining();
           address = trainer.getEmployee().getAddress();
           phoneNumber = String.valueOf(trainer.getEmployee().getPhoneNumber());
           adhaarNumber = trainer.getEmployee().getAdhaarNumber();
           department = trainer.getEmployee().getDepartment();
           trainerExperience = String.valueOf(trainer.getTrainerExperience());
           heading = "Update Trainer";
      }
      session.setAttribute("trainer", trainer);
   %>
    <h3 style="color:blue"> <%= heading %> </h3>
    <form action="employee?action=addOrUpdateTrainer&choice=<%=choice%>" method="post">
    Name :<br> <input type="text" name="name" value="<%= name %>" required/></br></br>
    Gender :<br> <input type="radio" id="male" name="gender" value="male" <%=(gender.equals("male") ? "checked = checked" : "")%>>
            <label for="male">male</label><br>
            <input type="radio" id="female" name="gender" value="female" <%=(gender.equals("female") ? "checked = checked" : "")%>>
            <label for="female"> female</label><br>
            <input type="radio" id="others" name="gender" value="others" <%=(gender.equals("others") ? "checked = checked" : "")%>>
            <label for="others"> others </label><br><br>
    Qualification :<br> <input type="text" name="qualification" value="<%= qualification %>" required/></br></br>
    Email :<br> <input type="emailId" name="emailId" value="<%= emailId %>" required/></br></br>
    Date of birth : <br><input type="date" name="dateOfBirth" value="<%= dateOfBirth %>" required/></br></br>
    Date of Joining :<br> <input type="date" name="dateOfJoining" value="<%= dateOfJoining %>" required/></br></br>
   Address : <br><input type="text" name="address" value="<%= address %>" required/></br></br>
   Phone Number :<br> <input type="number" name="phoneNumber" value="<%= phoneNumber %>" required/></br></br>
   AdhaarNumber :<br> <input type="number" name="adhaarNumber" value="<%= adhaarNumber %>" required/></br></br>
   Department :<br> <input type="text" name="department" value="<%= department %>" required/></br></br>
   trainerExperience :<br> <input type="number" name="trainerExperience" value="<%= trainerExperience %>" required/></br>
   </br><input type="submit" value="<%= heading %>"/>
  </form>
  </body>
 </html>
