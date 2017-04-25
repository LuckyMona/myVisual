import 'normalize.css'
import $ from 'jquery'

$(function(){

    $("#navW").load("nav.html", function(){

        $(".g-pr").on("click",function(){
            $(this).siblings('ul').slideToggle();
            $(this).find(".down").toggleClass("up");
        })

        $("#dplus-site-list-text").on("click",function(){
            $("#dplus-site-list-pop").toggleClass("popup");
            $(".iconfont.icon-xiala").toggleClass("up");
        });

        $("#dplus-site-list-pop").on("click", "li", function(){
            var selectedProj = $(this).html()
            $("#leftDplusSiteName").html(selectedProj)
            $("#dplus-site-list-pop").toggleClass("popup");
            $(".iconfont.icon-xiala").toggleClass("up");
        })

        var hash = window.location.hash.substr(1);
        if(hash){
            $(".childUl a").removeClass('active');
            var index = hash.indexOf("-");
            if(index>-1){
                var navHash = hash.substring(0,index);
                console.log(navHash)
                $("#"+navHash).addClass("active");
                return
            }
            $("#"+hash).addClass("active");
        }

        $("#showHints").hover(function(){
            $("#hintsW").show();
        }, function(){
            $("#hintsW").hide();
        });
    });

})



