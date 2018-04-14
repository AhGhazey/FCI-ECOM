$(window).ready(function () {
   $("#login_form_submit").click(function(){
    var form = $('#login_form');
        $.ajax({
                type: "Post",
                url: "AccountServlet",
                data: form.serialize(),
                dataType: "json",
               
                //if received a response from the server
                success: function( data, textStatus, jqXHR) {
                     if(data.success){
                        $("#ajaxResponse").html("");
                        console.log(data);
                        
                        if (data.User.role === 1){
                            Redirect("StudentServlet","Post","main")
                        }else if (data.User.role === 2){
                            Redirect("StaffServlet","Post","main")
                        }
                        else if (data.User.role === 3){
                            Redirect("StaffServlet","Post","staff")
                        }
                        else if (data.User.role === 4){
                            Redirect("StaffServlet","Post","dean")
                        }
                     } 
                     //display error message
                     else {
                        $("#ajaxResponse").html("<div><b>Something goes wrong check your email or password</b></div>");

                     }
                },
               
                //If there was no response from the server
                error: function(jqXHR, textStatus, errorThrown){
                     $("#ajaxResponse").html("<div><b>Something goes wrong check your email or password</b></div>");
                     $("#ajaxResponse").html(jqXHR.responseText);
                },
               
                //capture the request before it was sent to server
                beforeSend: function(jqXHR, settings){
                    //disable the button until we get the response
                    $('#login_form_submit').attr("disabled", true);
                },
               
                //this is called after the response or error functions are finsihed
                //so that we can take some action
                complete: function(jqXHR, textStatus){
                    //enable the button 
                    $('#login_form_submit').attr("disabled", false);
                }
     
            });        
    
    });
    
});
function Redirect (servlet , method_type,flag){
    var form = $(document.createElement('form'));
    $(form).attr("action", servlet);
    $(form).attr("method", method_type);
    var input = $("<input>").attr("type", "hidden").attr("name", "flag").val(flag);
    $(form).append($(input));
    $(form).css("display", "none");
    form.appendTo( document.body );
    $(form).submit();
};