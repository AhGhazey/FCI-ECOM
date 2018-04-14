$(window).ready(function () {
    $("#compose_submit").unbind().click(function () {
        Send_message();

    });
    function Send_message() {
         $("#compose_ajax_response").html("");
        var email = $("#cuser_email").val();
        var title = $("#cmessage_title").val();
        var message = $("#cmessage_content").val();
        $.post("MessageServlet", {
            flag: "message",
            email: email,
            title: title,
            message: message
        },
        function (data) {
            var res = $.parseJSON(data).success;
            if (res) {
                $("#cuser_email").val("");
                $("#cmessage_title").val("");
                $("#cmessage_content").val("");
                
                $("#compose_ajax_response").html("<p> Email Sent Successfuly Thanks, we will contact you asap </p>");
            }else {
                
                 $("#compose_ajax_response").html("<p>Something went wrong, please try again later </p>");
            }
        });
    }
    ;
});


