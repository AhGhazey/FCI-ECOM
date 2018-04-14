$(window).ready(function () {
    Get_Groups_Departments(1);
    
    $("#add_form_submit").unbind().click(function () {
        Add_Student();
    });
    
});

function Add_Student() {
    $('#add_form_submit').attr("disabled", true);
    var name = $("#studentName").val();
    var password = $("#student_password").val();
    var email = $("#studentEmail").val();
    var gpa = $("#student_gpa").val();
    var major = $("#Student_major").val();
    var minor = $("#Student_minor").val();
    var group = $("#Student_Group").val();
    var level = $("#student_level").val();
    var flag = "AS";
    $.post("StaffServlet", {flag: flag,
        name: name,
        password: password,
        email: email,
        gpa: gpa,
        major: major,
        minor: minor,
        group: group,
        level: level
    },
    function (data) {
        $('#add_form_submit').attr("disabled", false);

        var obj = $.parseJSON(data);

    });
}

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