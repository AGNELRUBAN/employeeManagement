<%@page import="com.ideas2it.employee.dto.TraineeDto"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Trainees of Trainers</title>
    <link rel="stylesheet" href="stylee.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
</head>
<body>

<div class="header">
    <table class="table" >
        <tr >
            <td style="width:8%;padding-left:29px;">
              <i class="glyphicon glyphicon-user"  style="font-size:49px;color:#0a0a47;margin-left4px;"></i>
            </td>
            <td>
                <p style="font-weight: 800;color:white;font-style: revert;height: 8px;">${trainerDto.employeeName}</p>
                <p style="font-weight: 800;color:white;"> (Trainer) </p>
            </td>
            <td style="width:38%;color:white;font-style:revert;font-weight: 800;">
                <p> Number Of Trainees</p>
                <p style="padding-left:67px;"> <c:out value="${traineeDto.size()}"/> </p>
            </td>
        </tr>
    </table>
    <table class="table1">
        <tr class="first">
            <td>
                <p>Name</p>
            </td>
            <td>
                <p> Email Id</p>
            </td>
            <td>
                <p> Salary</p>
            </td>
            <td>
                <p>Mobile Number</p>
            </td>
        </tr>
         <c:forEach var="par" items="${traineeDto}">
         <tr class="second">
            <td>
                 <i class="glyphicon glyphicon-user"  style="font-size: 18px;margin-left: -38px;"></i>
                <h5 class="name"> <c:out value="${par.employeeName}"/> <br> </h5>
            </td>
            <td>
                <p > <c:out value="${par.emailId}"/></p>
            </td>
            <td>
                <p> <c:out value="${par.salary}"/></p>
            </td>
            <td>
                <p> <c:out value="${par.phoneNumber}"/></p>
            </td>
         </c:forEach>
        </tr>
    </table>
     <a href="/viewField?id=${trainerDto.id}" style="color: #00e8ff;position:absolute;margin-top:10px" > Back </a>

</div>
</body>
</html>