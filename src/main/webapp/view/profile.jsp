<%@page import="com.ideas2it.employee.dto.TrainerDto"%>
<%@page import="java.util.List"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Front End</title>
    <link rel="stylesheet" href="style.css">


</head>
<body>
<div class="wrapper">
    <img src="User-Profile-PNG-Image.png">
    <div class="name">
         <h2 style="margin-left:-5px;"> ${trainerDto.employeeName}</h2>
    </div>
    <div>
    <a class="delete" href="TraineeView?id=${trainerDto.id}"> <input class ="delete btn" type="button" value="Trainees"></a>
    </div>
    <div class="list">
         <p id="first">${trainerDto.qualificationDto.description}</p>
         <p id="first">${trainerDto.dateOfBirth}</p>
         <p id="first">${trainerDto.phoneNumber}</p>
         <p id="first">${trainerDto.emailId}</p>
         <p id="first">${trainerDto.address}</p>
    </div>

        <img class="imagee" src="smartphone.png">
        <img class="imagee1" src="location.png">
        <img class="imagee2" src="email.png">
        <img class="imagee3" src="open-book.png">
        <img class="imagee4" src="calendar.png">

        <div class="delete">
           <a class="delete" href=deleteTrainer?id=${trainerDto.id}> <input class ="delete btn" type="button" value="Delete"></a>
           </div>
        <div class="update">
           <a href=updateTrainer?id=${trainerDto.id}> <input class ="update btn" type="button" value="Update"></a>
           </div>
           <div class="home">
          <a href="/" style="color:blue" > Back to Home </a>
           </div>
           <div class="back">
          <a href="viewTrainer" style="color:blue" > Back </a>
          </div>
</div>

<div class="second">
<p class="third"> Professional Summary </p>
    <p class="four">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Dicta earum eius et, iste iure perspiciatis voluptates? Alias eaque earum eligendi error, eum ex explicabo ipsum laudantium minima perspiciatis voluptates voluptatum.
                     and the recursion effect is falling the night sky of the twenty term particle of the various system in the planet and the variable of the system may will not function the author of the kind person in the variable association</p>
    <p class="five"> Work Experience</p>
    <h3 class="six">Senior Front End Developer</h3>
    <img class="imagee5" src="location.png">
    <img class="imagee6" src="location.png">
    <img class="imagee7" src="location.png">
    <img class="imagee8" src="icons8-building-50.png">
    <img class="imagee9" src="icons8-building-50.png">
    <img class="imagee10" src="icons8-building-50.png">
       <h5 class="seven"> AppleInc </h5>               <h5 class="eight"> Los Angles </h5>
       <p class="nine"></p>
    <h4 class="ten">Full Time</h4>
    <h5 class="eleven">April 12,2020-</h5>
    <h3 class="twelve">junior Front End Developer</h3>
    <h5 class="thirteen"> Figma </h5>               <h5 class="fourteen"> San Francisco </h5>
    <p class="sixteen"></p>
    <h4 class="seventeen">Full Time</h4>
    <h5 class="eighteen">April 12,2019 - April 16, 2020</h5>
    <h3 class="nineteen">Intern Software Developer</h3>
    <h5 class="twenty"> Microsoft </h5>               <h5 class="twentyone"> New York City </h5>
    <p class="twentytwo"></p>
    <h4 class="twentythree">Full Time</h4>
    <h5 class="twentyfour">April 12,2017 - April 16, 2019</h5>
    <p class="twentyfive"> Education</p>
    <h3 class="twentysix">Masters In Software Engineering</h3>
    <h5 class="twentyseven"> Manchester institute Of Technology </h5>
    <img class="imagee11" src="icons8-graduate-48.png">
    <h5 class="twentyeight">April 12,2019 - April 16, 2020</h5>
    <p class="twentynine"></p>
    <h3 class="thirty">Bachelor In Software Engineering</h3>
    <h5 class="thirtyone"> Manchester institute Of Technology </h5>
    <img class="imagee12" src="icons8-graduate-48.png">
    <h5 class="thirtytwo">April 12,2019 - April 16, 2020</h5>
     <img class="imagee13" src="calendar.png">
     <img class="imagee14" src="calendar.png">
     <img class="imagee15" src="calendar.png">
     <img class="imagee16" src="calendar.png">
     <img class="imagee17" src="calendar.png">

</div>
</body>
</html>