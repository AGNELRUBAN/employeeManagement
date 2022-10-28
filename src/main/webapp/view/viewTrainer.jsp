<%@page import="com.ideas2it.employee.dto.TrainerDto"%>
<%@page import="java.util.List"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8" isELIgnored="false" %>
<html>
 <head>

  <title> Trainer List </title>
 </head>

 <body style="background-color:FFF8DC">
 <h2 style="text-align:center;color:#6f3251;"> Trainer List </h2>
 <div style="width:1252px;height:463px;overflow:hidden;overflow-y:auto;">
 <div>
  <table border=1 cellpadding=5 cellspacing=0 style="border:5px solid #972a0d91;text-align:center;margin-left: 20%;" >
    <tr>
     <th style="color:#ff8900;"> Id </th>
     <th style="color:#ff8900;width: 686px;"> Name </th>
     <th style="color:#ff8900;"> Option </th>
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
</div>
</div>
 <div style="color:red;padding-left: 253px;padding-top: 4px;">
 <c:if test="${authority == 'ROLE_ADMIN'}">
 <a href="trainerForm"> <input style="margin-top:17px;" class ="add btn" type="button" value="Add Trainer"></a>
 </c:if>
 <a href="/" style="color:blue;" > Back to Home </a>
  </div>
 </body>
</html>
