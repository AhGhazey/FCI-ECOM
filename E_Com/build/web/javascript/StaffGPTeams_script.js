$(window).ready(function () {
    Get_GP_Team();

});

function Get_GP_Team() {

    $.post("HomeServlet", {
        flag: "Get_GP_Team_Staff",
    },
            function (data) {
                var parsedData = $.parseJSON(data);
                console.log(parsedData);
                if (parsedData.success === true) {
                    var teamsdic = parsedData.teams;
                    $.each(teamsdic, function (key, listvalue) {
                        console.log(listvalue);
                        $.each(listvalue, function (key, list) {
                            var counter = 1;
                            var newhtml = "";
                            newhtml += "<table><tr> <th> Number</th><th>Name</th> <th>Description</th> <th>Project Name</th> <th>Team Name</th>  </tr>";
                            $.each(list, function (index, item) {
                                newhtml += "<tr>";
                                newhtml += "<td>" + counter + "</td>";
                                newhtml += "<td>" + item.StudentName + "</td>";
                                newhtml += "<td>" + item.Description + "</td>";
                                newhtml += "<td>" + item.ProjectName + "</td>";
                                newhtml += "<td>" + item.TeamName + "</td>";
                                newhtml += "</tr>";
                                counter += 1;
                            });
                            $("#StaGPTeam").append(newhtml);
                        });

                    });
                }




            });
}
;