$(window).ready(function () {
    GetTimeTable();
    Get_Groups_Departments();
      $('#GetTable_submit').unbind().click(function () {
        GetGroupTimeTable ();
    });

});
function GetTimeTable() {

    $.post("TimeTableServlet", {flag: "get_time_table"},
    function (data) {
        var cells = $.parseJSON(data).cells;
        console.log(cells);
        var MyRows = $('table#time_table').find('tbody').find('tr');
        $.each(cells, function (index, item) {
             var newhtml = "<p>" + item.HallName + "</p>"   
             newhtml+="<p>" + item.StaffName + "</p>";
             newhtml+="<p>" + item.CourseName + "</p>";
             newhtml+="<p>Group " + item.GroupName + "</p>";
             newhtml+="<p>" + item.DepartmentName + "</p>";
             $(MyRows[item.day_index]).find('td:eq('+(item.Index-1)+')').html(newhtml);
        });



    });

};

function Get_Groups_Departments() {
    $.post("StaffServlet", {flag: "DGLists"},
    function (data) {
        var obj = $.parseJSON(data).DG;
        //console.log(obj);
        var groups = obj[0];
        var departments = obj[1];
       
        update_select("t_Student_Group", groups);
        update_select("t_department", departments);
        
    });
}
function update_select(select_element, data) {
    $("#" + select_element).find('option').remove().end();
     $("#" + select_element).append("<option value=0>None</option>");
    $.each(data, function (index, item) {
        $("#" + select_element).append("<option value=" + item.id + ">" + item.Name + "</option>");

    });
}

function GetGroupTimeTable (){
    
    var department_id = $("#t_department").val();
    var group_id = $("#t_Student_Group").val();
    var t_level = $("#t_level").val();
    
    $.post("TimeTableServlet", {
        flag: "get_gtime_table",
        department : department_id,
        group: group_id,
        level: t_level      
    },
    function (data) {
        var cells = $.parseJSON(data).cells;
        console.log(cells);
        $("#time_table td").html("");
        var MyRows = $('table#time_table').find('tbody').find('tr');
        $.each(cells, function (index, item) {
             var newhtml = "<p>" + item.HallName + "</p>"   
             newhtml+="<p>" + item.StaffName + "</p>";
             newhtml+="<p>" + item.CourseName + "</p>";
             newhtml+="<p>Group " + item.GroupName + "</p>";
             newhtml+="<p>" + item.DepartmentName + "</p>";
             $(MyRows[item.day_index]).find('td:eq('+(item.Index-1)+')').html(newhtml);
        });



    });
    
}
