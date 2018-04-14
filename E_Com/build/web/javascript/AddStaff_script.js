$(window).ready(function () {
     Get_Groups_Departments(2);
    
 
    $("#addStaff_form_submit").unbind().click(function () {
        Add_Staff();
    });
});
function Get_Groups_Departments(type) {
    $.post("StaffServlet", {flag: "DGLists"},
    function (data) {
        var obj = $.parseJSON(data).DG;
        //console.log(obj);
        var groups = obj[0];
        var departments = obj[1];
        if (type === 1) {
            update_select("Student_Group", groups);
            update_select("Student_minor", departments);
            update_select("Student_major", departments);
        } else if (type === 2) {
            update_select("Staff_department", departments);
        }
    });

}
function update_select(select_element, data) {
    $("#" + select_element).find('option').remove().end();
    $.each(data, function (index, item) {
        $("#" + select_element).append("<option value=" + item.id + ">" + item.Name + "</option>");

    });
}

function Add_Staff() {
    $('#addStaff_form_submit').attr("disabled", true);
    var name = $("#staffName").val();
    var password = $("#staff_password").val();
    var email = $("#staff_email").val();
    var department = $("#Staff_department").val();
    var role = $("#Staff_Role").val();
    var position = $("#Staff_possition").val();
    var flag = "ASt";
    $.post("StaffServlet", {
        flag: flag,
        name: name,
        password: password,
        email: email,
        department: department,
        role: role,
        position: position
    },
    function (data) {
        $('#addStaff_form_submit').attr("disabled", false);
        var obj = $.parseJSON(data);

    });
}