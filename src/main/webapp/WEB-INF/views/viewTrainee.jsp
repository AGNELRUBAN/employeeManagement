<%@ page import = "java.util.List"%>
<%@ page import = "java.util.ArrayList" %>
<%@ page import = "com.ideas2it.employee.model.Trainee"%>
<%@ page import = "com.ideas2it.employee.model.Trainer"%>

<html>
<head>
 <title>View Trainee</title>
</head>
<body style="background-color:FFF8DC">
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
	<th style="font-style:italic;color:blue">Trainers Id</th>
    </tr>
    <%
       List<Trainee> trainees = (List) request.getAttribute("trainees");
       for (Trainee trainee : trainees) {
    %>
    <tr>
	<td style="color:FF4500"> <%= trainee.getEmployee().getId()%> </td>
	<td style="color:FF4500"> <%= trainee.getEmployee().getEmployeeName()%> </td>
    <td style="color:FF4500"> <%= trainee.getEmployee().getGender()%> </td>
	<td style="color:FF4500"> <%= trainee.getEmployee().getAddress()%> </td>
	<td style="color:FF4500"> <%= trainee.getEmployee().getDateOfBirth()%> </td>
	<td style="color:FF4500"> <%= trainee.getEmployee().getDateOfJoining()%> </td>
	<td style="color:FF4500"> <%= trainee.getEmployee().getEmailId()%> </td>
	<td style="color:FF4500"> <%= trainee.getEmployee().getPhoneNumber()%> </td>
    <td style="color:FF4500"> <%= trainee.getEmployee().getAdhaarNumber()%> </td>
	<td style="color:FF4500"> <%= trainee.getEmployee().getQualification().getDescription()%> </td>
    <td style="color:FF4500"> <%= trainee.getEmployee().getDepartment()%> </td>
	<td style="color:FF4500"> <%= trainee.getSalary()%> </td>
	<%
	    List<Integer> trainersId = new ArrayList<>();
	    for (Trainer trainer : trainee.getTrainers()) {
		trainersId.add(trainer.getEmployee().getId());
	    }
	%>
	<td style="color:FF4500"> <%= trainersId %> </td>
    </tr>
    <%
       }
    %>
  </table>
  <a href="/" style="color:blue" > Back to Home </a>
</body>
</html>