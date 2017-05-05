import "./components/projSelector.js";
import {resFormatToJson, resFormatToString, setHost, getIDs} from "./utils.js";

var HOST = setHost();

$(function(){

    $("#commonHeaderW").load("commonHeader.html",function(){
        hintsDynamic();
        myRouter();
        $("#projSelector").projSelector();
        getTotalIndexs();           //应用概况-运营指标-获得“累计用户”等四项数值的接口
        getMemoryDataElecIndexs();  //应用概况-设备使用-获取“内存”等三项指标的数据
        getAuthorityAndRate();      //应用概况-设备使用-获得权限占比
        pointDynamics();            //应用概况-运营指标-指标动态效果
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
    function getAuthorityAndRate(){
        var IDsObj = getIDs();
        $.get(HOST+"appOverview/appUsing/getAuthorityAndRate", IDsObj, function(res){
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
        $.get(HOST+"appOverview/keyIndex/getTotalIndexs", IDsObj, function(res){
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
        $.get(HOST+"appOverview/appUsing/getMemoryDataElecIndexs", IDsObj, function(res){
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
})
