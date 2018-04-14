
var admin_active_tab = "";
function changeActiveTab(newActive) {
    
    $('#' + admin_active_tab).removeClass('active');
    $('#' + admin_active_tab).addClass('not-active');
    
    $('#' + newActive).removeClass('not-active');
    $('#' + newActive).addClass('active');
    admin_active_tab = newActive;
}
$(window).ready(function () {
    //$("#left-menu-tree-content_student").load("Student_menu_partial.html");
    $("#logout_admin").click(function () {
        alert("hhhhhh");
    });
    $("#a_addstudent").click(function () {
        changeActiveTab("a_addstudent");
        $("#main_content_admin").load("Add_Student.html");
        

    });
    $("#a_addstaff").click(function () {
        changeActiveTab("a_addstaff");
        $("#main_content_admin").load("Add_Staff.html");
        Get_Groups_Departments(2);
    });
    $("#a_addhall").click(function () {
        changeActiveTab("a_addhall");
        $("#main_content_admin").load("Add_Hall.html");
    });
    $("#a_add_department").click(function () {
        changeActiveTab("a_add_department");
        $("#main_content_admin").load("Add_Department.html");
    });
    $("#a_add_course").click(function () {
        changeActiveTab("a_add_course");
        $("#main_content_admin").load("Add_Course.html");
    });
    $("#a_addslot").click(function () {
        changeActiveTab("a_addslot");
        $("#main_content_admin").load("Add_Slot.html");
    });
    $("#a_assignslot").click(function () {
        changeActiveTab("a_assignslot");
    });
    $("#a_stfmail").click(function () {
        changeActiveTab("a_stfmail");
        $("#main_content_admin").load("Staff_Mails.html");
    });
    $("#a_top50").click(function () {
        changeActiveTab("a_top50");
         $("#main_content_admin").load("Top50.html");
    });

    
     $("#ad_inbox").click(function () {
        $("#main_content_admin").load("Inbox.html");
    });
    $("#ad_outbox").click(function () {
        $("#main_content_admin").load("Outbox.html");
    });
    $("#ad_compose_message").click(function () {
        $("#main_content_admin").load("Compose_Message.html");
    });
    
   
});











