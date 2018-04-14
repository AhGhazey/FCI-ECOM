var selected_reservation = null;
        var all_reservations = [];
        $(window).ready(function () {
GetAllReservationRequests();
        });
        function GetAllReservationRequests() {

        $.post("ReservationServlet", {
        flag: "GetAllRequests",
        },
                function (data) {
                var Reservations = $.parseJSON(data).Reservations;
                        all_reservations = Reservations;
                        console.log(Reservations);
                        var newhtml = "<table id='reservationRequests'><tr><th> Reservation #</th><th> Reservation purpose</th> <th> Reservation Owner</th><th> Hall </th><th> Department</th><th> Group</th> <th> Level</th><th> Date</th> <th> Day</th> <th> From</th> <th> To </th></tr>";
                        var counter = 1;
                        $.each(Reservations, function (index, item) {
                        console.log(item.group.Name + "  " + item.level + " test")
                                newhtml += "<tr>";
                                newhtml += "<td>" + counter + "</td>";
                                newhtml += "<td>" + item.purpose + "</td>";
                                newhtml += "<td>" + item.name + "  " + item.email + "</td>";
                                newhtml += "<td>" + item.slot.hall_name + "</td>";
                                newhtml += "<td>" + item.department.Name + "</td>";
                                newhtml += "<td >" + item.group.Name + "</td>";
                                newhtml += "<td >" + item.level + "</td>";
                                newhtml += "<td >" + item.date + "</td>";
                                newhtml += "<td>" + item.slot.day + "</td>";
                                newhtml += "<td>" + item.slot.start_hr + "</td>";
                                newhtml += "<td>" + item.slot.end_hr + "</td>";
                                newhtml += "</tr>";
                                newhtml += "<tr><td ><a class='accept' style='color:white;' onclick=''>Accept</a></td><td><a class='refuse' style='color:white;' onclick=''>Refuse</a></td></tr>";
                                counter += 1;
                        });
                        newhtml += "</table>";
                        $("#requests").html("");
                        $("#requests").html(newhtml);
                        $('#reservationRequests td').unbind().click(function () {
                var col = $(this).parent().children().index($(this));
                        var row = $(this).parent().parent().children().index($(this).parent());
                        //alert('Row: ' + row + ', Column: ' + col);

                        var selected_row = (row / 2) - 1;
                        selected_reservation = all_reservations[selected_row];
                        alert(selected_row);
                        console.log(selected_reservation);
                        if (row % 2 === 0 && col === 0) {
                Accept_Reservation();
                } else if (row % 2 === 0 && col === 1) {
                Refuse_Reservation();
                }
                });
                });
                }

function Accept_Reservation() {
var reservation_id = selected_reservation.reservation_id;
var email = selected_reservation.email;
var group = selected_reservation.group.id;
var department = selected_reservation.department.id;
var level = selected_reservation.level;
var hall_name = selected_reservation.slot.hall_name;
var start_hr = selected_reservation.slot.start_hr;
var end_hr = selected_reservation.slot.end_hr;
$.post("ReservationServlet", {
flag: "accept_reservation",
        reservation_id:reservation_id,
        email:email,
        group  :group,
        department : department,
        level: level,
        hall : hall_name,
        start_hr : start_hr,
        end_hr: end_hr,
        date : selected_reservation.date, 
        day : selected_reservation.slot.day,
        gName: selected_reservation.group.Name,
        dName: selected_reservation.department.Name,
},
        function (data) {


        });
}
;
        function Refuse_Reservation() {
        var reservation_id = selected_reservation.reservation_id;
                var email = selected_reservation.email;
                $.post("ReservationServlet", {
                flag: "refuse_reservation",
                        reservation_id:reservation_id,
                        email:email
                },
                        function (data) {


                        });
                }