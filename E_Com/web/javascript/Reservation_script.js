var table_selected_row = -1;
$(window).ready(function () {
    Get_Groups_Departments();
    $(function () {
        $("#reservation_date").datepicker();
        $("#reservation_date").datepicker("option", "dateFormat", "DD, d, mm, yy");
    });

    $("#reservation_date").change(function () {
        var date = $("#reservation_date").val().split(", ");
        console.log(date);
        Get_Avaliable_Slots(date[0]);
    });
    
    $('#reservation_submit').unbind().click(function () {
        Make_Reservation();
    });

});

function Get_Avaliable_Slots(day) {
    //$('#reservation_submit').attr("disabled", true);
    $.post("ReservationServlet", {
        flag: "get_free_slots",
        day: day,
    },
            function (data) {
                //$('#reservation_submit').attr("disabled", false);

                var slots = $.parseJSON(data).slots;
                console.log(slots);
                var newhtml = "<table id='avaSlots'><tr><th> Slot Number</th> <th> Hall Name</th><th>Hall Capacity </th> <th> Start hour </th> <th> End hour</th> <th style='display:none'> id </th> <th style='display:none'> hall_id </th>  </tr>";
                var counter = 1;
                $.each(slots, function (index, item) {
                    newhtml += "<tr>";
                    newhtml += "<td>" + counter + "</td>";
                    newhtml += "<td>" + item.hall_name + "</td>";
                    newhtml += "<td>" + item.hall_capacity + "</td>";
                    newhtml += "<td>" + item.start_hr + "</td>";
                    newhtml += "<td>" + item.end_hr + "</td>";
                    newhtml += "<td style ='display:none'>" + item.slot_id + "</td>";
                    newhtml += "<td style ='display:none'>" + item.hall_id + "</td>";
                    newhtml += "</tr>";
                    counter+=1;
                });

                newhtml += "</table>";
                $("#available_slots").html("");
                $("#available_slots").html(newhtml);

                $('#avaSlots td').unbind().click(function () {
                    //var col = $(this).parent().children().index($(this));
                    var row = $(this).parent().parent().children().index($(this).parent());
                    //alert('Row: ' + row + ', Column: ' + col);
                    table_selected_row = row -1;
                    $("#Selected_slot").text ("Slot number "+row+" is selected");
                });
            });

};

function Make_Reservation(){
    var purpose = $("#reservation_purpose").val();
    var date =  $("#reservation_date").val();
    var slot_id = $('tr td:nth-child(6)', $("#avaSlots")).eq(table_selected_row).text();
    var hall_id = $('tr td:nth-child(7)', $("#avaSlots")).eq(table_selected_row).text();
    var department_id = $("#res_department").val();
    var group_id = $("#res_Student_Group").val();
    var level = $("#res_level").val();
    console.log(slot_id);
    console.log(hall_id);
     $.post("ReservationServlet", {
        flag: "make_reservation",
        purpose: purpose,
        date : date,
        slot_id : slot_id,
        hall_id : hall_id,
        department_id: department_id,
        group_id: group_id,
        level : level
    },
    function (data) {
        var obj = $.parseJSON(data);
        console.log(obj);
    
    });
}

function Get_Groups_Departments() {
    $.post("StaffServlet", {flag: "DGLists"},
    function (data) {
        var obj = $.parseJSON(data).DG;
        //console.log(obj);
        var groups = obj[0];
        var departments = obj[1];
       
        update_select("res_Student_Group", groups);
        update_select("res_department", departments);
        
    });
}
function update_select(select_element, data) {
    $("#" + select_element).find('option').remove().end();
    $.each(data, function (index, item) {
        $("#" + select_element).append("<option value=" + item.id + ">" + item.Name + "</option>");

    });
}