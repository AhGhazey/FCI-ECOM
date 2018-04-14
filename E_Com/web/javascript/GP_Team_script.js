$(window).ready(function () {
    Get_GP_Team();

});

function Get_GP_Team() {

    $.post("HomeServlet", {
        flag: "Get_GP_Team",
    },
    function (data) {
        var parsedData = $.parseJSON(data);
        var team_list = parsedData.team;
            var counter = 1;
            var newhtml = "<h2 style='text-align:center'>"+parsedData.TeamName+"</h2>";
            newhtml+="<h3 style='text-align:center'> Prof : "+parsedData.StaffName+"</h3>";
            newhtml += "<table><tr> <th> Number</th><th>Name </tr>";
             $.each(team_list, function (index, item) { 
                 newhtml += "<tr>";
                    newhtml += "<td>" + counter + "</td>";
                    newhtml += "<td>" + item.StudentName+ "</td>";
                    newhtml += "</tr>";
                    counter +=1;
              });
            $("#SGPTeam").append(newhtml);
        
    });
}
;