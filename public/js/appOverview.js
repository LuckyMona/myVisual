import "./components/projSelector.js";
import {resFormatToJson, resFormatToString, setHost, getIDs, listenChange, getDateStr} from "./utils.js";
import Highcharts from 'highcharts';

var HOST = setHost();

$(function(){

    $("#commonHeaderW").load("commonHeader.html",function(){
        hintsDynamic();
        myRouter();
        $("#projSelector").projSelector();
        sendReq();
        listenChange(sendReq);
    })

    function sendReq(objDate){
        getTotalIndexs();           //应用概况-运营指标-获得“累计用户”等四项数值的接口
        getMemoryDataElecIndexs();  //应用概况-设备使用-获取“内存”等三项指标的数据
        getAuthorityAndRate();      //应用概况-设备使用-获得权限占比
        pointDynamics();            //应用概况-运营指标-指标动态效果
        var date = null;
        if(objDate){
            date = objDate;
        }else{
            var todayStr = getDateStr(0);
            date = {
                start:todayStr,
                end:todayStr
            }
        }
        getChartDetails(date);          //应用概况-关键指标
    }
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
    function getAuthorityAndRate(){
        var IDsObj = getIDs();
        $.get("appOverview/appUsing/getAuthorityAndRate", IDsObj, function(res){
            var jsonRes = resFormatToJson(res);
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
    function getTotalIndexs(){
        var IDsObj = getIDs();
        $.get("appOverview/keyIndex/getTotalIndexs", IDsObj, function(res){
            var jsonRes = resFormatToJson(res);
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

    function getMemoryDataElecIndexs(){
        var IDsObj = getIDs();
        $.get("appOverview/appUsing/getMemoryDataElecIndexs", IDsObj, function(res){
            var jsonRes = resFormatToJson(res);
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
    function pointDynamics() {
        $('#keyIndexW ul li.point-btn').on('click', function(e) {
            var $this = $(e.currentTarget);
            $this.siblings().removeClass('on');
            $this.addClass('on');

            $('#' + $this.data('chart')).siblings().hide();
            $('#' + $this.data('chart')).show();
        })
    }
    function getChartDetails(date) {
        var IDsObj = getIDs();
        var reqOption ={
            start:date.start,
            end: date.end,
            projID:IDsObj.projID,
            verID:IDsObj.verID,
            appID:IDsObj.appID
        };
        $.get("historyTrends/getTableDetails", reqOption, function(res){
            var jsonRes = resFormatToJson(res);
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
            var customchart = Highcharts.chart('cusEventsCounts', setParams(customData, '自定义事件发生次数'));
        })
    }
})
