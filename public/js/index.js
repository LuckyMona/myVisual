import "./components/projSelector.js";
import { initNav } from "./nav.js";
// import {resFormatToJson, resFormatToString, setHost, getIDs, listenChange, getDateStr} from "./utils.js";
import * as utils from "./utils.js";
import Highcharts from 'highcharts';

var HOST = utils.setHost();

$(function(){

    initNav();  // 初始化左侧导航
    $("#commonHeaderW").load("commonHeader.html",function(){
        utils.hintsDynamic();               // 处理commonHeader的指标解释
        utils.commonHeaderRouter();         // 处理commonHeader点击的路由效果
        $("#projSelector").projSelector();  // 初始化项目、版本选择框
        sendReq();                          // 初始化数据请求
        utils.listenChange(sendReq);        // 监听参数改变，重发数据请求
    })

    function sendReq(){

        getTotalIndexs();                   //获得“累计用户”等四项数值的接口
        pointDynamics();                    //指标动态效果
        var todayStr = utils.getDateStr(0);
        var date = {
            start:todayStr,
            end:todayStr
        }
        getIndexs(date);                    //获得“启动次数”等关键指标（今日）
        getChartDetails(date);              //应用概况-关键指标
    }
    function getIndexs(date){
        console.log("getIndexs");
        utils.getIndexs(date, callback);
        function callback(jsonRes){
            setPointBtns("startUpCounts", jsonRes);
            setPointBtns("newUsers", jsonRes);
            setPointBtns("activeUsers", jsonRes);
            setPointBtns("usingTime", jsonRes);
            setPointBtns("customEventsCounts", jsonRes);
        }
        function setPointBtns(keyName,jsonRes){
            $("#pointBtnW li[data-chart="+keyName+"]").find("h4").html(jsonRes[keyName]);
        }
    }
    function getTotalIndexs(){
        var IDsObj = utils.getIDs();
        $.get(HOST + "appOverview/keyIndex/getTotalIndexs", IDsObj, function(res){
            var jsonRes = utils.resFormatToJson(res);
            if(jsonRes){
                setData("totalUsers");
                setData("weekActiveUsers");
                setData("weekRetention");
                setData("errorRatio");
            }

            function setData(idName){
                $("#"+idName).find("h4").html(jsonRes[idName]);
            }
        })
    }

    function pointDynamics() {
        $('#pointBtnW li').on('click', function(e) {
            $(this).siblings().removeClass('on');
            $(this).addClass('on');

            $('#' + $(this).data('chart')).siblings().hide();
            $('#' + $(this).data('chart')).show();
        })
    }
    function getChartDetails(date) {
        var IDsObj = utils.getIDs();
        var reqOption ={
            start:date.start,
            end: date.end,
            projID:IDsObj.projID,
            verID:IDsObj.verID,
            appID:IDsObj.appID
        };
        $.get(HOST + "historyTrends/getTableDetails", reqOption, function(res){
            var jsonRes = utils.resFormatToJson(res);
            var startupData = [], newuserData = [],
                activeData = [], usingData = [],
                customData = [], xData = [];
            $.each(jsonRes.tableDatas, function(idx, item) {
                xData.push(item.time);
                startupData.push(item.startUpCounts);
                newuserData.push(item.newUsers);
                activeData.push(item.activeUsers);
                usingData.push(item.usingTime);
                customData.push(item.customEventsCounts);
            });

            var setParams = function(yData, title) {
                return {
                    title: {
                        text: title
                    },
                    xAxis: {
                        categories: xData
                    },
                    yAxis: {
                        allowDecimals: false
                    },
                    exporting: {
                        enabled: false
                    },
                    series: [{
                        name: $('#projSelector .verSelect option:selected').val(),
                        data: yData
                    }]
                };
            }
            // 启动次数
            var startup = Highcharts.chart('startUpCounts', setParams(startupData, '启动次数'));
            // 新增用户
            var newuser = Highcharts.chart('newUsers', setParams(newuserData, '新增用户'));
            // 活跃用户
            var activechart = Highcharts.chart('activeUsers', setParams(activeData, '活跃用户'));
            // 自定义事件
            var customchart = Highcharts.chart('customEventsCounts', setParams(customData, '自定义事件发生次数'));
            utils.hideLoading();
        })
    }
})
