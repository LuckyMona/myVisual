import "./components/projSelector.js";
import { initNav } from "./nav.js";
// import {resFormatToJson, resFormatToString, setHost, getIDs, listenChange, getDateStr} from "./utils.js";
import * as utils from "./utils.js";

var HOST = utils.setHost();

$(function(){

    initNav();  // 初始化左侧导航
    $("#commonHeaderW").load("commonHeader.html",function(){
        utils.hintsDynamic();               // 处理commonHeader的指标解释
        utils.commonHeaderRouter();         // 处理commonHeader点击的路由效果
        $("#projSelector").projSelector({"isShowAll":false});  // 初始化项目、版本选择框
        sendReq();                          // 初始化数据请求
        utils.listenChange(sendReq);        // 监听参数改变，重发数据请求
    })

    function sendReq(objDate){
        getMemoryDataElecIndexs();  //应用概况-设备使用-获取“内存”等三项指标的数据
        getAuthorityAndRate();      //应用概况-设备使用-获得权限占比
    }

    function getAuthorityAndRate(){
        var IDsObj = utils.getIDs();
        $.get(HOST + "appOverview/appUsing/getAuthorityAndRate", IDsObj, function(res){
            var jsonRes = utils.resFormatToJson(res);
            console.log(jsonRes);
            if(jsonRes){
                $("#authApply").html(jsonRes.authorities);

                var tableStr = "";
                var tableThStr = "<tr>";
                var tableTdStr = "<tr>";

                jsonRes.authoritiesAndRate.forEach(function(item, index){
                    tableThStr+="<th>"+item.authority+"</th>";
                    tableTdStr+="<td>"+item.rate+"</td>";
                });
                tableThStr+="</tr>";
                tableTdStr+="</tr>";

                tableStr = tableThStr+tableTdStr;

                $("#innerTable").html(tableStr);
                var tableWidth = $("#innerTable").width();
                var tableWrapWidth = $(".innerTableW").width();
                if(tableWidth<tableWrapWidth){
                    $("#innerTable").find("th").width(tableWrapWidth/jsonRes.authoritiesAndRate.length)
                }
            }
        });
    }
    function getMemoryDataElecIndexs(){
        var IDsObj = utils.getIDs();
        $.get(HOST + "appOverview/appUsing/getMemoryDataElecIndexs", IDsObj, function(res){
            var jsonRes = utils.resFormatToJson(res);
            console.log(jsonRes);
            if(jsonRes){
                setData("memory");
                setData("dataflow");
                setData("electricity");
            }

            function setData(idName){
                $("#"+ idName + " div:first").find("h5").html(jsonRes[idName]["averageUsage"]);

                if($("#"+ idName + " div").length>1){
                   $("#"+ idName + " div:last").find("h5").html(jsonRes[idName]["maxUsage"]);
                }
            }
        })
    }
})
