$(window).ready(function () {
    $("#addCourse_form_submit").click(function () {
        Add_Course();
    });
});
function Add_Course() {
    var name = $("#CourseName").val();
    var code = $("#CourseCode").val();
    var flag = "AC";
    $.post("StaffServlet", {
        flag: flag,
        name: name,
        code: code,
    },
            function (data) {
                var obj = $.parseJSON(data);

            });

}