import 'normalize.css'
import $ from 'jquery'

$(function(){

    $(".g-pr").on("click",function(){
        $(this).siblings('ul').slideToggle();
        $(this).find(".down").toggleClass("up");
    })
    $("#dplus-site-list-text").on("click",function(){
        $("#dplus-site-list-pop").toggleClass("popup");
        $(".iconfont.icon-xiala").toggleClass("up");
    });
})



