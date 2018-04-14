$(window).ready(function () {
    Get_Groups_Departments();
    $("#GetTop50button").unbind().click(function (){
        GetTop50();
    });
});


function GetTop50() {
    var department_id = $("#department_50").val();
    var level = $("#top50_level").val();
    $.post("StaffServlet", {
        flag: "top50",
        department:department_id,
        level : level
    },
    function (data) {
        var staffList = $.parseJSON(data).top50list;
        var newhtml = "<table><tr> <th> Name </th><th> GPA </th>  </tr>";

        $.each(staffList, function (index, item) {
            newhtml += "<tr>";
            newhtml += "<td>" + item.Name + "</td>";
            newhtml += "<td>" + item.GPA + "</td>";
            newhtml += "</tr>";
        });

        newhtml += "</table>";
        $("#top50").html("");
        $("#top50").html(newhtml);
    });

}
function Get_Groups_Departments() {
    $.post("StaffServlet", {flag: "DGLists"},
    function (data) {
        var obj = $.parseJSON(data).DG;
        //console.log(obj);
        var groups = obj[0];
        var departments = obj[1];

        $("#department_50").find('option').remove().end();
        $.each(departments, function (index, item) {
            $("#department_50").append("<option value=" + item.id + ">" + item.Name + "</option>");

        });

    });

}
