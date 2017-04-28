import 'normalize.css';
require("./components/projSelector.js");

var HOST = "http://127.0.0.1:9876";
var APPID = 12;

$(function(){


    /*$("#projSelector").projSelector();*/

    $("#navW").load("nav.html", function(){
        navDynamic();   //处理左侧导航的动态效果
        myRouter();     //处理导航点击的路由效果
        initSelector(); //初始化应用选择器、proj选择器和vers选择器
        getIndexs();    //获取“启动次数”等五项指标
    });

    function getTimes(){
        var objSelectedTime = $("#timeSelector").find(".active");
        if(objSelectedTime.length===1){
            var timeStr = objSelectedTime.html();

        }
    }

    function getDate(timeStr){

        /*var oDate = new Date();
        switch(timeStr){
            case "今天":
                oDate.setDate(oDate.getDate());
                break;
            case "昨天":
                oDate.setDate(oDate.getDate()-1);
                break;
            case "最近7天":
            case "最近30天":
        }
        var dateStr = oDate.getFullYear() + "-"+ (oDate.getMonth()+1) +"-"+oDate.getDate();
        console.log(dateStr);
        return dateStr;*/
        return "2017-08-09";
    }
    function getIDs(){
        return{
            "projID":localStorage.getItem("selectedProjID"),
            "verID":localStorage.getItem("selectedVerID"),
            "appID":APPID
        }
    }
    function getIndexs(){
        var IDsObj = getIDs();
        var reqOption = {
            baseTime:getDate(),
            projID:IDsObj.projID,
            verID:IDsObj.verID,
            appID:IDsObj.appID
        };
        console.log(reqOption);
        $.get(HOST+"/historyTrends/getIndexs", reqOption, function(res){
            var jsonRes = JSON.parse(res);
            if(jsonRes && jsonRes.type==="success"){
                var wrapObj = $("#countsW");

                fillIndexs("startUpCounts",wrapObj,jsonRes);
                fillIndexs("newUsers",wrapObj,jsonRes);
                fillIndexs("activeUsers",wrapObj,jsonRes);
                fillIndexs("usingTime",wrapObj,jsonRes);
                fillIndexs("customEventsCounts",wrapObj,jsonRes);
            }
        });

        function fillIndexs(className, wrapObj,jsonRes){
            wrapObj.children("."+className).find("h4").html(jsonRes[className].baseCounts)
            wrapObj.children("."+className).find("h5").html(jsonRes[className].contrastCounts);
        }
    };
    function initSelector(){

        $.get(HOST+"/systemInfo/getProjsAndVers", function(res){
            var jsonRes = JSON.parse(res);
            if(jsonRes && jsonRes.type==="success"){
                /*  TODO: fullfill apps options
                *
                *
                if(res.apps && Array.isArray(res.apps)){
                    res.apps.forEach(function(item, index){
                        optionStr +=
                    })
                }
                $("#dplus-site-list-pop").find("ul").html(optionStr)
                */
                localStorage.setItem("projsAndVersStr",res);
                $("#projSelector").projSelector();
            }
        })
    }


    function navDynamic(){
        $(".g-pr").on("click",function(){
            $(this).siblings('ul').slideToggle();
            $(this).find(".down").toggleClass("up");
        });


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


        $("#showHints").hover(function(){
            $("#hintsW").show();
        }, function(){
            $("#hintsW").hide();
        });

    }

    function myRouter(){

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

    }

})



