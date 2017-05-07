module.exports = {
    "resFormatToJson":resFormatToJson,
    "resFormatToString":resFormatToString,
    "setHost":setHost,
    "getIDs":getIDs,
    "listenChange":listenChange,
    "getDateStr":getDateStr,
    "getAPPID":getAPPID
}

/*var APPID = "com.cyanogenmod.trebuchet2";*/

function getAPPID(){
    return "com.cyanogenmod.trebuchet2";
}

function resFormatToJson(res){
    var resJson = null;
    if(typeof res === "object"){
        resJson = res;
    }else if(typeof res === "string"){
        resJson = JSON.parse(res);
    }else{
        console.log("resFormatToJson: Wrong type response!");
    }
    return resJson;
}

function resFormatToString(res){
    var resString = "";
    if(typeof res === "object"){
        resString = JSON.stringify(res);
    }else if(typeof res === "string"){
        resString = res;
    }else{
        console.log("resFormatToString: Wrong type response!");
    }
    return resString;
}

function setHost(){
    var host = localStorage.getItem('HOST');
    if(host){
        return host;
    }
    host = "http://127.0.0.1:8080/ue/";
    localStorage.setItem("HOST", host);
    return host;
}

function getIDs(){
    var APPID = getAPPID();
    return{
        "projID":localStorage.getItem("selectedProjID"),
        "verID":localStorage.getItem("selectedVerID"),
        "appID":APPID
    }
}

function listenChange(callBack){

    console.log("change");
    var objTimeSelector = $("#timeSelector");
    if (objTimeSelector){
        objTimeSelector.on("click","span",function(){
            var date = getDate($(this).html());
            callBack(date);
        });
    }

    $("#projSelector select").change(function(){
        callBack();
    });
}
function getDate(dateStr){
    switch (dateStr){
        case "今天":
            var todayStr = getDateStr(0);
            return {
                "start":todayStr,
                "end":todayStr
            }
        case "昨天":
            var yestodayStr = getDateStr(-1);
            return {
                "start":yestodayStr,
                "end":yestodayStr
            }
        case "最近7天":
            var sevenDaysBefore = getDateStr(-7);
            var todayStr = getDateStr(0);
            return {
                "start":sevenDaysBefore,
                "end":todayStr
            }
        case "最近30天":
            var oneMonthBefore = getDateStr(-30);
            var todayStr = getDateStr(0);
            return {
                "start":oneMonthBefore,
                "end":todayStr
            }
    }
}
function getDateStr(AddDayCount) {
    var dd = new Date();
    dd.setDate(dd.getDate()+AddDayCount);//获取AddDayCount天后的日期
    var y = dd.getFullYear();
    var m = dd.getMonth()+1;//获取当前月份的日期
    var d = dd.getDate();
    return y+"-"+m+"-"+d;

    /*
    document.write("前天："+GetDateStr(-2));
    document.write("<br />昨天："+GetDateStr(-1));
    document.write("<br />今天："+GetDateStr(0));
    document.write("<br />明天："+GetDateStr(1));
    */
}

