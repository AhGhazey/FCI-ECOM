window.messagesarray = [];
table_selected_row = -1;
selectedMessage = null;
comefrom = 1;
$(window).ready(function () {
    Get_outbox();

});

function Get_outbox() {
    $.post("MessageServlet", {
        flag: "outbox"
    },
    function (data) {
        var messages = $.parseJSON(data).messages;
        messagesarray = messages;
        console.log(messages)
        var newhtml = "";
        $.each(messages, function (index, item) {
            if (item.State === 1)
            newhtml = "<tr>";
            newhtml += "<td>" + item.To + "</td>";
            newhtml += "<td>" + item.Title + "</td>";
            newhtml += "<td style ='display:none'>" + item.ID + "</td>";
            newhtml += "</tr>";
            $("#outbox_content").append(newhtml);
        });

        $('#outbox_content td').unbind().dblclick(function () {
            var col = $(this).parent().children().index($(this));
            var row = $(this).parent().parent().children().index($(this).parent());
            //alert('Row: ' + row + ', Column: ' + col);
            table_selected_row = row - 1;
            selectedMessage = messagesarray[table_selected_row];
            $("#main_content_student").load("Message.html");
            $("#main_content_admin").load("Message.html");
            $("#main_content_staff").load("Message.html");
        });
    });
}