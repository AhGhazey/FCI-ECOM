var staff_active_tab = "";
function changeActiveTab(newActive) {
    if (staff_active_tab !== "") {
        $('#' + staff_active_tab).removeClass('active');
        $('#' + staff_active_tab).addClass('not-active');
    }
    $('#' + newActive).removeClass('not-active');
    $('#' + newActive).addClass('active');
    staff_active_tab = newActive;
}
$(window).ready(function () {
    //$("#left-menu-tree-content_student").load("Student_menu_partial.html");
    $("#logout_staff").click(function () {
        alert("hhhhhh");
    });
    $("#resreq").click(function () {
        changeActiveTab("resreq");
        $("#main_content_staff").load("ReservationRequests.html");
    });
 
    $("#stu_stfmail").click(function () {
        changeActiveTab("stu_stfmail");
        $("#main_content_staff").load("Staff_Mails.html");
    });

     $("#st_top50").click(function () {
        changeActiveTab("st_top50");
        $("#main_content_staff").load("Top50.html");
    });
    
    $("#st_inbox").click(function () {
        $("#main_content_staff").load("Inbox.html");
    });
    $("#st_outbox").click(function () {
        $("#main_content_staff").load("Outbox.html");
    });
    $("#st_compose_message").click(function () {
       $("#main_content_staff").load("Compose_Message.html");
    });

});
