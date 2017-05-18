import 'normalize.css';
import "./components/projSelector.js";
import { initNav } from "./nav.js";
// import {resFormatToJson, resFormatToString, setHost, getIDs, listenChange, getDateStr} from "./utils.js";
import * as utils from "./utils.js";
import Highcharts from 'highcharts';
import "./libs/jquery.pagination.js";

var HOST = utils.setHost();
var APPID = 12;

$(function(){
    initNav();  // 初始化左侧导航
    initPage();
    utils.listenChange(sendReqs);

    function initPage(){
        setItemsPerPageSelect(); // 根据localStorage的itemsPerPage，设置选中项
        initSelector();     //初始化应用选择器、proj选择器和vers选择器
        setDatePointer();   //设置日期选中动态效果
        pointDynamics();    //处理指标按钮的动态效果
        sendReqs();         //这两个接口发送请求: getIndexs 和 getTableDetails
    }
    function sendReqs(objDate){
        var date = null;
        if(objDate){
            date = objDate;
        }else{
            var todayStr = utils.getDateStr(0);
            date = {
                start:todayStr,
                end:todayStr
            }
        }
        getIndexs(date);    //获取“启动次数”等五项指标
        getTableDetails(date); //获取详细数据表格
    }

    function setDatePointer(){
        $("#timeSelector").on("click","span", function(evt){
            $(this).siblings().removeClass("active");
            $(this).addClass("active");
        });
    }
    function setItemsPerPageSelect(){
        var localVal = localStorage.getItem("itemsPerPage");
        if(localVal){
            $("#itemsPerPage option[value="+ Number(localVal) +"]").attr("selected", true);
        }
    }

    function pointDynamics() {
        $('.point-btn').on('click', function(e) {
            var $this = $(e.currentTarget);
            $this.siblings().removeClass('active');
            $this.addClass('active');

            $('#' + $this.data('chart')).siblings().hide();
            $('#' + $this.data('chart')).show();
        });
    }
    function getTableDetails(date){

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
            var tableDatas = jsonRes.tableDatas
            if(tableDatas){
               // setTable(tableDatas,$("#itemsPerPage option:selected").val(),1);
                initPagination(tableDatas);
                $("#itemsPerPage").change(function(){
                    var itemsPerPage = Number($("#itemsPerPage option:selected").val());
                    localStorage.setItem("itemsPerPage", itemsPerPage);
                    setTable(tableDatas,itemsPerPage,1);
                    initPagination(tableDatas);
                });
            }
            // 晓莉代码start

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
            // 晓莉代码end
            utils.hideLoading();
        });

        function setTable(tableDatas,itemsPerPage, pageIndex){
                var tdStr = "<tr class='thTr'>"
                            +"<th width='20%'>时间</th>"
                            +"<th width='15%'>启动次数</th>"
                            +"<th width='15%'>新增用户</th>"
                            +"<th width='15%'>活跃用户</th>"
                            +"<th width='15%'>每次使用时长</th>"
                            +"<th width='20%'>自定义事件发生次数</th>"
                        +"</tr>";
                var iItemsPerPage = Number(itemsPerPage);
                var newContent = tableDatas.slice((pageIndex-1)*iItemsPerPage, pageIndex*iItemsPerPage);
                newContent.forEach(function(item, index){
                    tdStr+="<tr class='tdTr'>"
                            +"<td><span>"+((pageIndex-1)*iItemsPerPage+index+1)+"</span>"+item.time+"</td>"
                            +"<td>"+item.startUpCounts+"</td>"
                            +"<td>"+item.newUsers+"</td>"
                            +"<td>"+item.activeUsers+"</td>"
                            +"<td>"+item.usingTime+"</td>"
                            +"<td>"+item.customEventsCounts+"</td>"
                        +"</tr>"
                });
                $("#detailDataW tbody").empty().append(tdStr);
        }
        function pageselectCallback(tableDatas, itemsPerPage, pageIndex, objContainer){
            setTable(tableDatas,itemsPerPage, pageIndex+1);
            return false;
        }
        function getItemsPerPage(){
            var itemsPerPage = Number(localStorage.getItem("itemsPerPage")) || Number($("#itemsPerPage option:selected").val());
            return itemsPerPage;
        }
        function initPagination(tableDatas) {
            var num_entries = tableDatas.length;
            var itemsPerPage = getItemsPerPage();
            $("#Pagination").pagination(num_entries, {
                callback: pageselectCallback.bind(this,tableDatas,itemsPerPage),
                items_per_page:itemsPerPage, // Show n items per page
                num_display_entries:3,
            });
        }
    }
    function getIndexs(date){
        utils.getIndexs(date,callback);
        function callback(jsonRes){
            var wrapObj = $("#countsW");
            fillIndexs("startUpCounts",wrapObj,jsonRes);
            fillIndexs("newUsers",wrapObj,jsonRes);
            fillIndexs("activeUsers",wrapObj,jsonRes);
            fillIndexs("usingTime",wrapObj,jsonRes);
            fillIndexs("customEventsCounts",wrapObj,jsonRes);
            // utils.hideLoading();
        }
        function fillIndexs(className, wrapObj,jsonRes){
            /*wrapObj.children("."+className).find("h4").html(jsonRes[className].baseCounts)
            wrapObj.children("."+className).find("h5").html(jsonRes[className].contrastCounts);*/
            wrapObj.children("."+className).find("h4").html(jsonRes[className])
        }
    }
    function initSelector(){

        $.get(HOST + "systemInfo/getProjsAndVers", function(res){
            var jsonRes = utils.resFormatToJson(res);
            console.log(jsonRes)
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
                localStorage.setItem("projsAndVersStr",utils.resFormatToString(res));
                $("#projSelector").projSelector();
            }
        })
    }

})
