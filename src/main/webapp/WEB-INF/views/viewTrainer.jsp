<%@page import="com.ideas2it.employee.model.Trainer"%>
<%@page import="java.util.List"%>

<html>
 <head>
  <title> Trainer List </title>
 </head>

 <body style="background-color:FFF8DC">
 <h2 style="text-align:center"> Trainer List </h2>
  <table border=1 cellpadding=5 cellspacing=0 style="border:5px solid blue" >
    <tr>
     <th style="font-style:italic;color:blue"> Id </th>
     <th style="font-style:italic;color:blue"> Name </th>
     <th style="font-style:italic;color:blue"> Gender </th>
     <th style="color:blue;font-style:italic"> Address </th>
     <th style="color:blue;font-style:italic"> Date Of Birth </th>
     <th style="color:blue;font-style:italic"> Date Of Joining </th>
     <th style="color:blue;font-style:italic"> Email Id </th>
     <th style="color:blue;font-style:italic"> Phone Number </th>
     <th style="color:blue;font-style:italic"> Adhaar Number </th>
     <th style="color:blue;font-style:italic"> Qualification </th>
     <th style="color:blue;font-style:italic"> Department </th>
     <th style="color:blue;font-style:italic"> Trainer Experience </th>
    </tr> 
 <% List<Trainer> trainers = (List<Trainer>)request.getAttribute("trainers");
    for (Trainer trainer : trainers) { %>
     <tr> 
     <td style="color:red"> <%= trainer.getEmployee().getId() %> </td>
     <td style="color:FF4500"> <%= trainer.getEmployee().getEmployeeName() %> </td>
     <td style="color:FF4500"> <%= trainer.getEmployee().getGender() %> </td>
     <td style="color:FF4500"> <%= trainer.getEmployee().getAddress() %> </td>
     <td style="color:FF4500"> <%= trainer.getEmployee().getDateOfBirth() %> </td>
     <td style="color:FF4500"> <%= trainer.getEmployee().getDateOfJoining() %> </td>
     <td style="color:FF4500"> <%= trainer.getEmployee().getEmailId() %> </td>
     <td style="color:FF4500"> <%= trainer.getEmployee().getPhoneNumber() %> </td>
     <td style="color:FF4500"> <%= trainer.getEmployee().getAdhaarNumber() %> </td>
     <td style="color:FF4500"> <%= trainer.getEmployee().getQualification() %> </td>
     <td style="color:FF4500"> <%= trainer.getEmployee().getDepartment() %> </td>
     <td style="color:FF4500"> <%= trainer.getTrainerExperience() %> </td>

     <td> <a href="deleteTrainer?id=<%= trainer.getEmployee().getId()%>"> <input class ="delete btn" type="button" value="Delete"></a> </td>
     <td> <a href="updateTrainer?id=<%= trainer.getEmployee().getId()%>"> <input class ="update btn" type="button" value="Update"></a> </td>
     </tr>


      <% } %>
</table>
 <a href="trainerForm"> <input class ="add btn" type="button" value="Add Trainer"></a>
<a href="/" style="color:blue" > Back to Home </a>
 </body>
</html>
