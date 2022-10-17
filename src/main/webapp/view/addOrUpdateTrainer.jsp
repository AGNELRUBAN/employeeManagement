<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page isELIgnored="false"%>
<%
String action = (String) request.getAttribute("action");
Trainer trainer = (Trainer) request.getAttribute("trainer");
String heading = "ADD TRAINER";
if (action.equals("updateTrainer")) {
heading = "Update Trainer";
}
%>

<html>
<head>
    <title style="text-align:center"> <%= heading %> </title>
</head>
<body style="background-color:FFF8DC;alinment:center">
<%@ page import="com.ideas2it.employee.model.Trainer" %>
<h3 style="padding-left:540px;color:violet;"> <%= heading %> </h3>
<form:form style="padding:2px;" modelAttribute="trainerDto" action="addOrUpdateTrainer?action=${action}" method="post">
    <form:hidden path="id" />
    <form:select hidden="hidden" path="roleDto.description">
        <form:option selected="selected" value="Trainer">Trainer</form:option>
    </form:select>
    <table >
        <tr>
            <td style="padding-left:400px;">
                Name         :
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
        <tr >
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
        <tr >
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
        <tr >
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
                Trainer Experience :
            </td>
            <td>
                <form:input type="number" path="trainerExperience" name="trainerExperience" required="required"/>
            </td>
        </tr>
        <tr >
            <td style="padding-left:450px;">
                <input class ="btn" type="submit" value="<%= heading %>"/>
            </td>
            <td style="padding-left:50px;">
                <a href="/"> <input class ="btn" type="button" value="Back"/></a>
            </td>
        </tr>
    </table>
</form:form>
</div>
</body>
</html>