$(window).ready(function () {
    $("#addofficehours_form_submit").click(function () {
        Add_Office_Hours();
    });

});

function Add_Office_Hours() {
    var day = $("#rohday").val();
    var place = $("#rohplace").val();
    var from = $("#rohfrom").val();
    var to = $("#rohto").val();
   
    var flag = "add_office_hours";
    $.post("StaffServlet", {
        flag: flag,
        day: day,
        place: place,
        from:from,
        to:to
        
    },
            function (data) {
                var obj = $.parseJSON(data);

            });
}

