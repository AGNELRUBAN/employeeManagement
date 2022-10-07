<html>
 <head>
  <title>Get Id</title>
 </head>
 <body style="background-color:FFF8DC">
  <%
    String choice = request.getParameter("action");
    String heading = null;
    String button = null;
    choice = "deleteTrainer";
    switch (choice) {
        case "deleteTrainer":
            heading = "Enter Id to Delete Trainer";
            button = "Delete Trainer";
            break;
        case "updateTrainer":
            heading = "Enter Id to Update Trainer";
            button = "Update Trainer";
            break;
        case "deleteTrainee":
            heading = "Enter Id to Delete Trainee";
            button = "Delete Trainee";
            break;
        case "updateTrainee":
            heading = "Enter Id to Update Trainee";
            button = "Update Trainee";
            break;
        }
  %>
  <form action="/deleteTrainer" method ="post">
   <h2 style="color:blue"> <%= heading %> </h2>
   Employee Id:<input type="number" name="id"/>
   <input type="submit" value="<%= button %>"/>
  </form>
 </body>
</html>