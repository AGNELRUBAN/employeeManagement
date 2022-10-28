<%@ page import = "java.util.List"%>
<%@ page import = "java.util.ArrayList" %>
<%@ page import = "com.ideas2it.employee.dto.TraineeDto"%>
<%@ page import = "com.ideas2it.employee.dto.TrainerDto"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8" isELIgnored="false" %>
<html>
<head>
 <title>View Trainee</title>
</head>
<body style="background-color:FFF8DC">
<h2 style="color:Red;padding-left:486px;margin:0"> Trainee Details </h2>
<div style="width:1252px;height:463px;overflow:hidden;overflow-y:auto;">
  <div>
 <table border = 1 cellpadding = 5 cellspacing = 0 style="border:5px solid blue">
    <tr>
	<th style="font-style:italic;color:blue">Id</th>
	<th style="font-style:italic;color:blue">Name</th>
    <th style="font-style:italic;color:blue"> Gender </th>
	<th style="font-style:italic;color:blue">Address</th>
	<th style="font-style:italic;color:blue">Date of Birth</th>
	<th style="font-style:italic;color:blue">Date of Joining</th>
	<th style="font-style:italic;color:blue">Email Id</th>
	<th style="font-style:italic;color:blue">Phone Number</th>
	<th style="font-style:italic;color:blue">Adhaar Number</th>
	<th style="font-style:italic;color:blue">Qualification</th>
	<th style="font-style:italic;color:blue">Department</th>
	<th style="font-style:italic;color:blue">Salary</th>
    <th style="font-style:italic;color:blue">Trainer Name</th>

    </tr>
    <%
       List<TraineeDto> trainees = (List) request.getAttribute("traineeDto");
       for (TraineeDto trainee : trainees) {
    %>
    <tr>
	<td style="color:FF4500"> <%= trainee.getId()%> </td>
	<td style="color:FF4500"> <%= trainee.getEmployeeName()%> </td>
    <td style="color:FF4500"> <%= trainee.getGender()%> </td>
	<td style="color:FF4500"> <%= trainee.getAddress()%> </td>
	<td style="color:FF4500"> <%= trainee.getDateOfBirth()%> </td>
	<td style="color:FF4500"> <%= trainee.getDateOfJoining()%> </td>
	<td style="color:FF4500"> <%= trainee.getEmailId()%> </td>
	<td style="color:FF4500"> <%= trainee.getPhoneNumber()%> </td>
    <td style="color:FF4500"> <%= trainee.getAdhaarNumber()%> </td>
	<td style="color:FF4500"> <%= trainee.getQualificationDto().getDescription()%> </td>
    <td style="color:FF4500"> <%= trainee.getDepartment()%> </td>
	<td style="color:FF4500"> <%= trainee.getSalary()%> </td>
    <td style="color:FF4500" > <%= trainee.getTrainersName().toString() %></td>
    <c:if test="${authority == 'ROLE_ADMIN'}">
	<td> <a href="deleteTrainee?id=<%= trainee.getId()%>"> <input class ="delete btn" type="button" value="Delete"></a> </td>
	<td> <a href="updateTrainee?id=<%= trainee.getId()%>"> <input class ="update btn" type="button" value="Update"></a> </td>
	</c:if>
    </tr>
    <%
       }
    %>

  </table>
   </div>
      </div>
      <c:if test="${authority == 'ROLE_ADMIN'}">
  <a href="\traineeForm"> <input style="margin-top:17px;"class ="add btn" type="button" value="Add Trainee"></a>
     </c:if>
  <a href="/" style="color:blue" > Back to Home </a>
</body>
</html>