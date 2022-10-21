<%@page import="com.ideas2it.employee.dto.TrainerDto"%>
<%@page import="java.util.List"%>
<html>
 <head>
  <title> Trainer List </title>
 </head>

 <body style="background-color:FFF8DC">
 <h2 style="text-align:center"> Trainer List </h2>
  <table border=1 cellpadding=5 cellspacing=0 style="border:5px solid blue;text-align:center;margin-left: 20%;" >
    <tr>
     <th style="font-style:italic;color:blue;background-color: #d2dfd2;"> Id </th>
     <th style="font-style:italic;color:blue;width: 686px;background-color: #d2dfd2;"> Name </th>
     <th style="background-color: #d2dfd2;color:blue;"> Option </th>
    </tr> 
 <% List<TrainerDto> trainers = (List)request.getAttribute("trainersDto");
    for (TrainerDto trainer : trainers) { %>
     <tr> 
     <td style="color:red"> <%= trainer.getId() %> </td>
     <td style="color:FF4500"> <%= trainer.getEmployeeName() %> </td>
     <td> <a href="viewField?id=<%= trainer.getId()%>"> <input class ="view btn" type="button" value="View"></a> </td>
     </tr>

      <% } %>
</table>
 <div style="color:red;padding-left: 253px;padding-top: 4px;">
 <a href="trainerForm"> <input class ="add btn" type="button" value="Add Trainer"></a>
 <a href="/" style="color:blue" > Back to Home </a>
  </div>
 </body>
</html>
