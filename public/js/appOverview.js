import "./components/projSelector.js";
import {resFormatToJson, resFormatToString, setHost, getIDs} from "./utils.js";

var HOST = setHost();

$(function(){

    $("#commonHeaderW").load("commonHeader.html",function(){
        hintsDynamic();
        myRouter();
        $("#projSelector").projSelector();
        getTotalIndexs();
        getMemoryDataElecIndexs();
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
    function getTotalIndexs(){
        var IDsObj = getIDs();
        $.get(HOST+"appOverview/keyIndex/getTotalIndexs", IDsObj, function(res){
            var jsonRes = resFormatToJson(res);
            console.log(jsonRes);
            setData("totalUsers");
            setData("weekActiveUsers");
            setData("weekRetention");
            setData("errorRatio");

            function setData(idName){
                $("#"+idName).find("h4").html(jsonRes[idName]);
            }
        })
    }

    function getMemoryDataElecIndexs(){
        var IDsObj = getIDs();
        $.get(HOST+"appOverview/appUsing/getMemoryDataElecIndexs", IDsObj, function(res){
            var jsonRes = resFormatToJson(res);
            console.log(jsonRes);
            setData("memory");
            setData("dataflow");
            setData("electricity");

            function setData(idName){
                $("#"+ idName + " div:first").find("h5").html(jsonRes[idName]["averageUsage"]);

                if($("#"+ idName + " div").length>1){
                   $("#"+ idName + " div:last").find("h5").html(jsonRes[idName]["maxUsage"]);
                }
            }
        })
    }

})