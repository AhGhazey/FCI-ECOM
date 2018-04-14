$(window).ready(function () {
    $("#addDepartment_form_submit").click(function () {
        Add_Department();
    });
});
function Add_Department() {
    var name = $("#CourseName").val();
    var flag = "AD";
    $.post("StaffServlet", {
        flag: flag,
        name: name,
    },
    function (data) {
        var obj = $.parseJSON(data);
    });

}