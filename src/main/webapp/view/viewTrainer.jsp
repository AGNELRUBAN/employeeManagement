<%@page import="com.ideas2it.employee.dto.TrainerDto"%>
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

    </tr> 
 <% List<TrainerDto> trainers = (List)request.getAttribute("trainersDto");
    for (TrainerDto trainer : trainers) { %>
     <tr> 
     <td style="color:red"> <%= trainer.getId() %> </td>
     <td style="color:FF4500"> <%= trainer.getEmployeeName() %> </td>


     <td> <a href="deleteTrainer?id=<%= trainer.getId()%>"> <input class ="delete btn" type="button" value="Delete"></a> </td>
     <td> <a href="updateTrainer?id=<%= trainer.getId()%>"> <input class ="update btn" type="button" value="Update"></a> </td>
     <td> <a href="viewField?id=<%= trainer.getId()%>"> <input class ="view btn" type="button" value="View"></a> </td>

     </tr>


      <% } %>
</table>
 <a href="trainerForm"> <input class ="add btn" type="button" value="Add Trainer"></a>
<a href="/" style="color:blue" > Back to Home </a>
 </body>
</html>
