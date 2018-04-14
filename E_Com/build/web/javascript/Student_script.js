var s_active_tab = "";
function changeActiveTab(newActive) {
    if (s_active_tab !== "") {
        $('#' + s_active_tab).removeClass('active');
        $('#' + s_active_tab).addClass('not-active');
    }
    $('#' + newActive).removeClass('not-active');
    $('#' + newActive).addClass('active');
    s_active_tab = newActive;
}
$(window).ready(function () {
    //$("#left-menu-tree-content_student").load("Student_menu_partial.html");
    $("#logout").click(function () {
        alert("hhhhhh");
    });
    $("#s_email").click(function () {
        changeActiveTab("s_email");
    });
    $("#s_mysubj").click(function () {
        changeActiveTab("s_mysubj");
        get_student_courses();
    });
    $("#s_rslot").click(function () {
        changeActiveTab("s_rslot");
        $("#main_content_student").load("Reservation.html");
    });
    $("#s_scd").click(function () {
        changeActiveTab("s_scd");
        $("#main_content_student").load("TimeTable.html");
    });
    $("#s_gscd").click(function () {
        changeActiveTab("s_gscd");
    });
    $("#s_ofchr").click(function () {
        changeActiveTab("s_ofchr");
        $("#main_content_student").load("Staff_Office_hours.html");

    });
    $("#s_stfmail").click(function () {
        changeActiveTab("s_stfmail");
        $("#main_content_student").load("Staff_Mails.html");
    });
    $("#s_gpteam").click(function () {
        changeActiveTab("s_gpteam");
        $("#main_content_student").load("GPTeam.html");
    });
    $("#s_top50").click(function () {
        changeActiveTab("s_top50");
        $("#main_content_student").load("Top50.html");
    });
    $("#stu_inbox").click(function () {
        $("#main_content_student").load("Inbox.html");
    });
    $("#stu_outbox").click(function () {
        $("#main_content_student").load("Outbox.html");
    });
    $("#stu_compose_message").click(function () {
        $("#main_content_student").load("Compose_Message.html");
    });
});

function get_student_courses() {
    $.ajax({
        type: "Get",
        url: "StudentServlet",
        dataType: "json",
        //if received a response from the server
        success: function (data, textStatus, jqXHR) {
            console.log(data);
            if (data.success) {
                var courses = data.scourses;
                var newhtml = "<table><tr> <th> Course Name</th><th>Course Code </th> <th> Practical Grade </th> <th> Final Grade</th> <th> Total Grade </th>  </tr>";

                $.each(data.scourses, function (index, item) {
                    newhtml += "<tr>";
                    newhtml += "<td>" + item.course_name + "</td>";
                    newhtml += "<td>" + item.course_code + "</td>";
                    newhtml += "<td>" + item.p_grade + "</td>";
                    newhtml += "<td>" + item.f_grade + "</td>";
                    newhtml += "<td>" + item.full_grade + "</td>";
                    newhtml += "</tr>";
                });

                newhtml += "</table>";
                $("#main_content_student").html("");
                $("#main_content_student").html(newhtml);
            }
            //display error message
            else {
                //$("#ajaxResponse").html("<div><b>Something goes wrong check your email or password</b></div>");

            }
        },
        //If there was no response from the server
        error: function (jqXHR, textStatus, errorThrown) {
            //$("#ajaxResponse").html("<div><b>Something goes wrong check your email or password</b></div>");
            //$("#ajaxResponse").html(jqXHR.responseText);
        },
        //capture the request before it was sent to server
        beforeSend: function (jqXHR, settings) {
            //disable the button until we get the response
            $('#s_mysubj').attr("disabled", true);
        },
        //this is called after the response or error functions are finsihed
        //so that we can take some action
        complete: function (jqXHR, textStatus) {
            //enable the button 
            $('#s_mysubj').attr("disabled", false);
        }

    });
}

