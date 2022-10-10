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
	String heading = "Add Trainee";
	if (action.equals("updateTrainee")) {
	    heading = "Update Trainee";
	}
  %>
  <h3 style="color:red"> <%= heading %> </h3>
  <form:form modelAttribute="trainee" action="addOrUpdateTrainee?action=${action}" method="post">
      <form:hidden path="employee.id"/>
      <form:hidden path="traineeId" />
       <table>
               <tr>
                   <td>
                       Name:
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
                           Salary :
                        </td>
                        <td>
                           <form:input type="number" path="salary" name="salary" required="required"/>
                        </td>
                      </tr>
                       <tr>
                          <td>
                            Trainer Ids :
                          </td>
                       <td>
                           <form:select path="trainersId">
                            <c:forEach var="trainer" items="${trainers}" >
                            <form:option value="${trainer.employee.id}" />
                              </c:forEach>
                           </form:select>
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
                       </body>
            </html>
