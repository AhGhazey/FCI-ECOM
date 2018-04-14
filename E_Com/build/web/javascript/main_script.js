/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/* global main */
var isFirsttime = false;
var active_tab ="home";
function changeActiveTab(newActive){
    $('#'+active_tab).removeClass('active');
    $('#'+active_tab).addClass('not-active');   
    $('#'+newActive).removeClass('not-active');
    $('#'+newActive).addClass('active');
    active_tab = newActive;
}
$(window).ready(function(){
  
    $("#home").click(function(){
       changeActiveTab("home") ;
       $("#main_content").html("");
       $("#Faculty_News_items").html("");
        $("#main_content").load("News.html");
    });
   $("#announcements").click(function(){   
         changeActiveTab("announcements") ;
         $("#main_content").html("");
         $("#main_content").load("Announcement.html");
    });
    $("#aboutus").click(function(){   
        $("#main_content").html("");
         changeActiveTab("aboutus") ;
        $("#main_content").load("About_us.html");
    });
    $("#contactus").click(function(){   
        $("#main_content").html("");
         changeActiveTab("contactus") ;
         $("#main_content").load("Contact_us.html");
    });
    $("#login").click(function(){  
        $("#main_content").html("");
         changeActiveTab("login") ;
         $("#main_content").load("LoginForm.html");
       
    });
if (!isFirsttime){
    isFirsttime = true;
    $("#home").click();
}

    
});


