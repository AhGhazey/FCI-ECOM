$(window).ready(function () {
    $("#contact_us_submit").unbind().click(function () {
        Send_message();

    });
    function Send_message() {
         $("#contact_us_ajax_response").html("");
        var email = $("#user_email").val();
        var title = $("#message_title").val();
        var message = $("#message_content").val();
        $.post("MessageServlet", {
            flag: "anonMessage",
            email: email,
            title: title,
            message: message
        },
        function (data) {
            var res = $.parseJSON(data).success;
            if (res) {
                $("#user_email").val("");
                $("#message_title").val("");
                $("#message_content").val("");
                
                $("#contact_us_ajax_response").html("<p> Email Sent Successfuly Thanks, we will contact you asap </p>");
            }else {
                
                 $("#contact_us_ajax_response").html("<p>Something went wrong, please try again later </p>");
            }
        });
    }
    ;
});


