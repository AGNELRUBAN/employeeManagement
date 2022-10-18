<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page isELIgnored="false"%>
<html>
<head>
    <title>Trainee</title>
</head>
<body style="background-color:FFF8DC">
<%@ page import="com.ideas2it.employee.model.Trainee"
         import="com.ideas2it.employee.model.Trainer"
%>
<%
String action = (String) request.getAttribute("action");
String heading = "ADD TRAINEE";
if (action.equals("updateTrainee")) {
heading = "Update Trainee";
}
%>
<h3 style="padding-left:540px;color:violet;"> <%= heading %> </h3>
<form:form modelAttribute="traineeDto" action="addOrUpdateTrainee?action=${action}" method="post">
    <form:hidden path="id"/>
    <form:select hidden="hidden" path="roleDto.description">
        <form:option selected="selected" value="Trainee">Trainee</form:option>
    </form:select>
    <table>
        <tr>
            <td style="padding-left:400px;">
                Name:
            </td>
            <td>
                <form:input type="text" path="employeeName" name="employeeName" required="required"/>
            </td>
        </tr>
        <tr>
            <td style="padding-left:400px;">
                Gender :
            </td>
            <td>
                <form:select path="Gender">
                    <form:option value="Male" label="male"/>
                    <form:option value="Female" label="female"/>
                    <form:option value="Others" label="Others"/>
                </form:select>
            </td>
        </tr>
        <tr>
            <td style="padding-left:400px;">
                Qualification :
            </td>
            <td>
                <form:input type="text" path="qualificationDto.description" name="qualification" required="required"/>
            </td>
        </tr>
        <tr>
            <td style="padding-left:400px;">
                Email Id :
            </td>
            <td>
                <form:input type="email" path="emailId" name="emailId" required="required" />
            </td>
        </tr>
        <tr>
            <td style="padding-left:400px;">
                Date of Birth :
            </td>
            <td>
                <form:input type="date" path="dateOfBirth" name="dateOfBirth" required="required"/>
            </td>
        </tr>
        <tr>
            <td style="padding-left:400px;">
                Date of Joining :
            </td>
            <td>
                <form:input type="date" path="dateOfJoining" name="dateOfJoining" required="required"/>
            </td>
        </tr>
        <tr>
            <td style="padding-left:400px;">
                Address :
            </td>
            <td>
                <form:input type="text" path="address" name="address" required="required"/>
            </td>
        </tr>
        <tr>
            <td style="padding-left:400px;">
                Phone Number :
            </td>
            <td>
                <form:input type="number" path="phoneNumber" name="phoneNumber" required="required"/>
            </td>
        </tr>
        <tr>
            <td style="padding-left:400px;">
                Adhaar Number :
            </td>
            <td>
                <form:input type="number" path="adhaarNumber" name="adhaarNumber" required="required"/>
            </td>
        </tr>
        <tr>
            <td style="padding-left:400px;">
                Department :
            </td>
            <td>
                <form:input type="text" path="department" name="department" required="required"/>
            </td>
        </tr>
        <tr>
            <td style="padding-left:400px;">
                Salary :
            </td>
            <td>
                <form:input type="number" path="salary" name="salary" required="required"/>
            </td>
        </tr>
        <tr>
            <td style="padding-left:400px;">
                Trainer Names :
            </td>
            <td>
                <form:select path="trainersId">
                    <c:forEach var="trainer" items="${trainersDto}" >
                        <form:option value="${trainer.id}" label="${trainer.id} - ${trainer.employeeName}" />
                    </c:forEach>
                </form:select>
            </td>
        </tr>
        <tr>
            <td style="padding-left:450px;">
                <input class ="btn" type="submit" value="<%= heading %>"/>
            </td>
            <td style="padding-left:50px;">
                <a href="/"> <input class ="btn" type="button" value="Back"/></a>
            </td>
        </tr>
    </table>
</form:form>
</body>
</html>
