$(window).ready(function () {
    Get_Halls ();
    
     $("#addtimetableslot_form_submit").unbind().click(function () {
        Add_Slot();
    });
    
});

function Get_Halls (){
    $.post("TimeTableServlet", {flag: "get_halls"},
    function (data) {
        var obj = $.parseJSON(data);
        console.log(obj);
        $('#hall_name').find('option').remove().end();
        $.each(obj.Halls, function (index, item) {
        $("#hall_name" ).append("<option value=" + item.id + ">" + item.Hall_name + "</option>");

    });
    });
};
function Add_Slot (){
    $('#addtimetableslot_form_submit').attr("disabled", true);
    var day =$("#slot_day").find('option:selected').text();
    var time_slot = $("#slot_time").find('option:selected').text();
    var slot_number = $("#slot_time").val();
    var hall_id = $("#hall_name").val();
     $.post("TimeTableServlet", {
         flag: "add_slot",
         day : day,
         time_slot : time_slot,
         hall_id : hall_id,
         slot_number : slot_number
     },
    function (data) {
        $('#addtimetableslot_form_submit').attr("disabled", false);
        $.notify("Slot Added successfuly", { className: 'success', globalPosition: 'bottom left' });
        var obj = $.parseJSON(data);
        console.log(obj);

    });
    
};