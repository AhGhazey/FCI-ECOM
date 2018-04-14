$(window).ready(function () {
    Get_announcement();

});

function Get_announcement() {

    $.post("HomeServlet", {
        flag: "Get_announcement",
    },
    function (data) {
        var announcement_list = $.parseJSON(data).announcements;
        console.log(announcement_list);
            var counter = 0;
             $.each(announcement_list, function (index, item) { 
                 newhtml = "<div id='announcement"+counter+"' class='announcement_item'>";
                 newhtml+= "<h2> "+item.title+"</h2>"
                 newhtml+= "<p> "+item.content+"</p>"
                 newhtml+= "</div>"
                 newhtml+="<hr>";
                 counter+=1;
                  $("#Announcements_items").append(newhtml);

              });
        
    });
}
;
