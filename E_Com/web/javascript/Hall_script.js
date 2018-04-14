$(window).ready(function () {
$("#addHall_form_submit").click(function () {
        Add_Hall();
});

});
function Add_Hall() {
    var name = $("#HallName").val();
    var capacity = $("#HallCapcity").val();
    var flag = "AH";
    $.post("StaffServlet", {
        flag: flag,
        name: name,
        capacity: capacity,
    },
    function (data) {
        var obj = $.parseJSON(data);

    });
}