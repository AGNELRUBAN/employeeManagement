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
     <td style="color:red"> <%= trainer.getId() %> </td>
     <td style="color:FF4500"> <%= trainer.getEmployeeName() %> </td>
     <td style="color:FF4500"> <%= trainer.getGender() %> </td>
     <td style="color:FF4500"> <%= trainer.getAddress() %> </td>
     <td style="color:FF4500"> <%= trainer.getDateOfBirth() %> </td>
     <td style="color:FF4500"> <%= trainer.getDateOfJoining() %> </td>
     <td style="color:FF4500"> <%= trainer.getEmailId() %> </td>
     <td style="color:FF4500"> <%= trainer.getPhoneNumber() %> </td>
     <td style="color:FF4500"> <%= trainer.getAdhaarNumber() %> </td>
     <td style="color:FF4500"> <%= trainer.getQualification() %> </td>
     <td style="color:FF4500"> <%= trainer.getDepartment() %> </td>
     <td style="color:FF4500"> <%= trainer.getTrainerExperience() %> </td>

     <td> <a href="deleteTrainer?id=<%= trainer.getId()%>"> <input class ="delete btn" type="button" value="Delete"></a> </td>
     <td> <a href="updateTrainer?id=<%= trainer.getId()%>"> <input class ="update btn" type="button" value="Update"></a> </td>
     </tr>


      <% } %>
</table>
 <a href="trainerForm"> <input class ="add btn" type="button" value="Add Trainer"></a>
<a href="/" style="color:blue" > Back to Home </a>
 </body>
</html>
