<%-- 
    Document   : Student_home
    Created on : Apr 25, 2016, 7:19:43 PM
    Author     : aghazey
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="css/main_style.css">
        <script  src="javascript/jquery-2.1.4.js"></script>
        <script  src="javascript/Student_script.js"></script>
        <title>Student Dashboard</title>
    </head>
    <body>
        <div id="StudentWidgetContent"  class = "WidgetContent" style="height: 100%;">
            <div id="header">
                <div id="header-top">
                    <a class="fci-logo" href="#">FCI-CU<span class="dotcom">.edu</span></a>
                    <div class="w3-right toptext w3-wide">THE OFFICIAL FACULTY WEB SITE</div>
                </div>

                <div id="headerItems_student" class ="header">
                    <ul id="hzNavBar_student" >
                        <li style="float:right">
                            <a href="#" class="active" id="logout"> Logout </a>
                        </li>
                    </ul>
                </div>
            </div>
            <div id="left_side_bar_student" class="left_side_bar"> 
                <div id="user_information" class="userinfo">
                    <span>Welcome: ${name}</span> 
                    <span>Email: ${email}</span> 
                    <span>Level: ${level}</span> 
                    <span>Group: ${group}</span> 
                </div>
                <div class="dropdown">
                    <button class="dropbtn">Email</button>
                    <div class="dropdown-content">
                        <a id="stu_inbox" href="#">Inbox</a>
                        <a id="stu_outbox" href="#">Outbox</a>
                        <a id="stu_compose_message" href="#">Compose Email</a>
                    </div>
                </div>
                <div id="left-menu-tree-content_student" class ="left_menu" >

                    <div id ="student_left_menu" >

                        <ul>

                            <li ><a href="#" id="s_mysubj" class="not-active">MY subjects</a></li>
                            <li ><a href="#" id="s_rslot" class="not-active">Reserve Slot</a></li>
                            <li ><a href="#" id="s_scd" class="not-active">My schedule</a></li>
                            <li><a href="#"  id="s_ofchr" class="not-active">Office Hours</a></li>
                            <li><a href="#"  id="s_stfmail" class="not-active">Staff Mails</a></li>
                            <li><a href="#"  id="s_gpteam" class="not-active">MY GP team</a></li>     
                            <li><a href="#"  id="s_top50" class="not-active">Top 50</a></li>
                        </ul>
                    </div>
                </div>
            </div>

            <div id="main_content_student" class="main_content"> 
                <p id="initial_step"> Please select option from the left menu. </p>
            </div>


        </div>
        <div id="footer_student" class="footer">
            <small>copyright 2016</small>
        </div>
    </div>

</body>
</html>
