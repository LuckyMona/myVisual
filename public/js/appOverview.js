require("./components/projSelector.js");

$(function(){

    $("#commonHeaderW").load("commonHeader.html",function(){
        hintsDynamic();
        myRouter();
        $("#projSelector").projSelector();
    })

    function hintsDynamic(){
        $("#showHints").hover(function(){
            $("#hintsW").show();
        }, function(){
            $("#hintsW").hide();
        });
    }

    function myRouter(){
        var hash = window.location.hash.substr(1);
        if(hash){
            var index = hash.indexOf("-");
            if(index>-1){
                $(".btnGroup a").removeClass('active');
                var subNavHash = hash.substring(index+1);
                $("#"+subNavHash).addClass('active')
            }
        }
    }

})