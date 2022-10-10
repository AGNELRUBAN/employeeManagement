<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page isELIgnored="false"%>
<%
    String action = (String) request.getAttribute("action");
	Trainer trainer = (Trainer) request.getAttribute("trainer");
	String heading = "Add Trainer";
	if (action.equals("updateTrainer")) {
	    heading = "Update Trainer";
	}
%>

<html>
 <head>
   <title> <%= heading %> </title>
  </head>
 <body style="background-color:FFF8DC">
   <%@ page import="com.ideas2it.employee.model.Trainer" %>
   <h3 style="color:Red"> <%= heading %> </h3>
   <form:form modelAttribute="trainer" action="addOrUpdateTrainer?action=${action}" method="post">
    <form:hidden path="employee.id" />
    <form:hidden path="trainerId" />
    <table>
        <tr>
            <td>
                Name :
            </td>
            <td>
                <form:input type="text" path="employee.employeeName" name="employeeName" required="required"/>
            </td>
        </tr>
         <tr>
            <td>
                Gender :
            </td>
            <td>
                <form:select path="employee.Gender">
                   <form:option value="Male" label="male"/>
                   <form:option value="Female" label="female"/>
                   <form:option value="Others" label="Others"/>
                </form:select>
            </td>
        </tr>
        <tr>
            <td>
                Qualification :
            </td>
            <td>
                <form:input type="text" path="employee.qualification.description" name="qualification" required="required"/>
            </td>
        </tr>
        <tr>
             <td>
                 Email Id :
             </td>
             <td>
                 <form:input type="email" path="employee.emailId" name="emailId" required="required" />
             </td>
         </tr>
         <tr>
            <td>
                Date of Birth :
            </td>
            <td>
                <form:input type="date" path="employee.dateOfBirth" name="dateOfBirth" required="required"/>
            </td>
        </tr>
        <tr>
            <td>
                Date of Joining :
            </td>
            <td>
                <form:input type="date" path="employee.dateOfJoining" name="dateOfJoining" required="required"/>
            </td>
        </tr>
        <tr>
            <td>
                Address :
            </td>
            <td>
                <form:input type="text" path="employee.address" name="address" required="required"/>
            </td>
        </tr>
        <tr>
            <td>
                Phone Number :
            </td>
            <td>
                <form:input type="number" path="employee.phoneNumber" name="phoneNumber" required="required"/>
            </td>
        </tr>
        <tr>
            <td>
                Adhaar Number :
            </td>
            <td>
                <form:input type="number" path="employee.adhaarNumber" name="adhaarNumber" required="required"/>
            </td>
        </tr>
       <tr>
            <td>
                Department :
            </td>
            <td>
                <form:input type="text" path="employee.department" name="department" required="required"/>
            </td>
        </tr>
        <tr>
            <td>
                Trainer Experience :
            </td>
            <td>
                <form:input type="number" path="trainerExperience" name="trainerExperience" required="required"/>
            </td>
        </tr>
        <tr>
            <td>
                <input class ="btn" type="submit" value="<%= heading %>"/>
            </td>
            <td>
                <a href="/"> <input class ="btn" type="button" value="Back"/></a>
            </td>
        </tr>
    </table>
  </form:form>
  </div>
 </body>
</html>