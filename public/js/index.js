import 'normalize.css';
import "./components/projSelector.js";
import {resFormatToJson, resFormatToString, setHost, getIDs, listenChange} from "./utils.js";
import Highcharts from 'highcharts';
import "./libs/jquery.pagination.js";

var HOST = setHost();
var APPID = 12;

$(function(){
    setItemsPerPageSelect(); // 根据localStorage的itemsPerPage，设置选中项
    $("#navW").load("nav.html", function(){
        navDynamic();   //处理左侧导航的动态效果
        myRouter();     //处理导航点击的路由效果
        initSelector(); //初始化应用选择器、proj选择器和vers选择器
        setDatePointer(); //设置日期选中动态效果
        pointDynamics();//处理指标按钮的动态效果
        setUserName();  //设置header右侧的用户名
        sendReqs();     //这两个接口发送请求: getIndexs 和 getTableDetails
    });
    listenChange(sendReqs);

    function sendReqs(){
        getIndexs();    //获取“启动次数”等五项指标
        getTableDetails(); //获取详细数据表格
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
    function setUserName(){
        var userName = localStorage.getItem("userName");
        if (userName){
            $("#userName").html(userName);
            return;
        }
        alert("请先登录!");
        window.location.href = 'login.html';
    }
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
    function pointDynamics() {
        $('.point-btn').on('click', function(e) {
            var $this = $(e.currentTarget);
            $this.siblings().removeClass('active');
            $this.addClass('active');

            $('#' + $this.data('chart')).siblings().hide();
            $('#' + $this.data('chart')).show();
        });
    }
    function getTableDetails(){
        var IDsObj = getIDs();
        var reqOption = {
            start:"2017-03-04",
            end: "2017-03-09",
            projID:IDsObj.projID,
            verID:IDsObj.verID,
            appID:IDsObj.appID
        };
        $.get(HOST+"historyTrends/getTableDetails", reqOption, function(res){
            var jsonRes = resFormatToJson(res);
            console.log(jsonRes);
            var tableDatas = jsonRes.tableDatas
            if(tableDatas){
                setTable(tableDatas,$("#itemsPerPage option:selected").val(),1);
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
                // TODO: 需要判断数据是按小时为单位还是按天为单位返回的，来确定横坐标显示格式
                // if (oneday) {
                //     var hour = time.getHours() < 10?
                //                ('0'+time.getHours()) : (''+time.getHours())
                //              + time.getMinutes() < 10?
                //                ('0'+time.getMinutes()) : (''+time.getMinutes());
                //     xData.push(hour);
                // } else {
                // }
                var time = new Date(item.time);
                var day = time.getFullYear() + '-' + time.getMonth() + '-' + time.getDate();
                xData.push(day);
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
                        name: '今天',
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
                var newContent = tableDatas.slice(pageIndex*itemsPerPage,(pageIndex+1)*itemsPerPage);
                newContent.forEach(function(item, index){
                    tdStr+="<tr class='tdTr'>"
                            +"<td><span>"+(index+1)+"</span>"+item.time+"</td>"
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
            console.log(arguments);
            setTable(tableDatas,itemsPerPage, pageIndex);
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
                items_per_page:itemsPerPage, // Show only 2 items per page
                num_display_entries:3,
            });
        }
    }
    function getIndexs(){
        var IDsObj = getIDs();
        var reqOption = {
            start:"2017-03-04",
            end: "2017-03-09",
            projID:IDsObj.projID,
            verID:IDsObj.verID,
            appID:IDsObj.appID
        };
        console.log(reqOption);
        $.get(HOST+"historyTrends/getIndexs", reqOption, function(res){
            var jsonRes = resFormatToJson(res);
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
            var jsonRes = resFormatToJson(res);
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
                localStorage.setItem("projsAndVersStr",resFormatToString(res));
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



