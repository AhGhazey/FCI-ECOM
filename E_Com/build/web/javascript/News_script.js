$(window).ready(function () {
    Get_News();

});

function Get_News() {

    $.post("HomeServlet", {
        flag: "Get_News",
    },
    function (data) {
        var news_list = $.parseJSON(data).news;
        console.log(news_list);
            var counter = 0;
             $.each(news_list, function (index, item) { 
                 newhtml = "<div id='news_"+counter+"' >";
                 newhtml+="<h2> "+item.title+"</h2>"
                 newhtml+="<div class='news_item'>"
                 newhtml+= "<p> "+item.content+"</p>"
                 newhtml+="</div>"
                 newhtml+= "</div>"
                 newhtml+= "<div style='display: inline-block; bottom: 0;width: 100%;'><hr></div>";
                 counter+=1;
                  $("#Faculty_News_items").append(newhtml);

              });
        
    });
}
;


