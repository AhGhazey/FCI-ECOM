$(window).ready(function (){
    console.log(selectedMessage);
    if (comefrom === 1)
        $("#MessageEmailAddress").text ("To: "+selectedMessage.To);
    else 
        $("#MessageEmailAddress").text ("From: "+selectedMessage.From);
    $("#MessageTitle").text ("Title: "+selectedMessage.Title);
    $("#smessage_content").val(selectedMessage.Body);
});