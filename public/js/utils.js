module.exports = {
    "resFormatToJson":resFormatToJson,
    "resFormatToString":resFormatToString,
    "setHost":setHost,
    "getIDs":getIDs,
    "listenChange":listenChange
}

var APPID = 12;

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
            callBack();
        });
    }

    $("#projSelector select").change(function(){
        callBack();
    });
}

