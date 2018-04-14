$(window).ready(function () {
    GetStaff();
    $("#StaffNames").change(function () {
        GetStaffOfficeHours();
    });

});
function GetStaff() {

    $.post("StaffServlet", {flag: "staffList"},
    function (data) {
        var staffList = $.parseJSON(data).mails;


        $("#StaffNames" ).find('option').remove().end();
        $("#StaffNames").append("<option value= 0 > None </option>");
        $.each(staffList, function (index, item) {
            $("#StaffNames").append("<option value=" + item.staff_id + ">" + item.Name + "</option>");

        });
    });

}

function GetStaffOfficeHours() {
    var staff_id = $("#StaffNames").val();
    if (staff_id === 0 || staff_id === "" )
        return;
    $.post("StaffServlet", {
        flag: "staffOfficeHours",
        staff_id : staff_id
    },
    function (data) {
        var officehours = $.parseJSON(data).officehours;

var newhtml = "<table><tr> <th> Day </th><th> Start </th> <th> End </th><th> Place </th> </tr>";

        $.each(officehours, function (index, item) {
            newhtml += "<tr>";
            newhtml += "<td>" + item.Day + "</td>";
            newhtml += "<td>" + item.StartTime + "</td>";
            newhtml += "<td>" + item.EndTime + "</td>";
            newhtml += "<td>" + item.Place + "</td>";
            newhtml += "</tr>";
        });

        newhtml += "</table>";
        $("#Office_Hours_items").html("");
        $("#Office_Hours_items").html(newhtml);
    });

}


