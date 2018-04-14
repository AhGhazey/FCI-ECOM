$(window).ready(function () {
    GetStaffMails() ;
});


function GetStaffMails() {

    $.post("StaffServlet", {flag: "staffList"},
    function (data) {
        var staffList = $.parseJSON(data).mails;
        var newhtml = "<table><tr> <th> Name </th><th> Email </th>  </tr>";

        $.each(staffList, function (index, item) {
            newhtml += "<tr>";
            newhtml += "<td>" + item.Name + "</td>";
            newhtml += "<td>" + item.Email + "</td>";
            newhtml += "</tr>";
        });

        newhtml += "</table>";
        $("#staff_mails").html("");
        $("#staff_mails").html(newhtml);
    });

}
