import {getAPPID} from "./../utils.js";
$.fn.projSelector = function(options){
    var me = this,
        $e = $(this),
        defaults = {"isShowAll":true},
        opts = $.extend({}, defaults, options);

    me.init = function(){

        /*$e.addClass("projSelector");*/
        if(window.localStorage){
            var storage = window.localStorage;

            var projStr = "",
                versStr = "";

            var projsAndVersStr = storage.getItem("projsAndVersStr");
            var projsAndVers = JSON.parse(projsAndVersStr);
            var projs = projsAndVers.projs;
            var vers = projsAndVers.vers;

            var projEle = $(".projSelect",$e),
                verEle = $(".verSelect",$e);

            getOptions(projs,"projID","projName", projEle);

            var storagedProjID = storage.getItem("selectedProjID");
            var storagedVerID = storage.getItem("selectedVerID");

            var APPID = getAPPID();

            if(storagedProjID && storagedVerID){
                var selectedAppVers = filterVers(vers, APPID);
                getOptions(selectedAppVers, "verID", "verName", verEle);
                var nProjID = Number(storagedProjID);
                var nVerID = Number(storagedVerID);
                $(".projSelect option[value='"+nProjID+"']").attr("selected", true);
                $(".verSelect option[value='"+nVerID+"']").attr("selected", true);
            }else{
                var firstAppVers = filterVers(vers, APPID);
                getOptions(firstAppVers, "verID","verName", verEle);
                storage.setItem("selectedProjID",projs[0].projID);
                storage.setItem("selectedVerID",firstAppVers[0].verID);
            }

            projEle.change(function(){
                var selectedProjID = $(".projSelect option:selected").val();
                storage.setItem("selectedProjID", selectedProjID);
                /*var projVers = filterVers(vers,Number(selectedProjID))
                getOptions(projVers, "verID","verName", verEle);
                storage.setItem("selectedVerID", projVers[0].verID);*/
            })
            verEle.change(function(){
                var selectedVerID = $(".verSelect option:selected").val();
                storage.setItem("selectedVerID", selectedVerID);
            })
        }

        function filterVers(vers,APPID){
            return vers.filter(function(item, index){
                return item.appID === APPID
            })
        }

        function getOptions(datas,optionID, optionName, eleWrap){
            var strArr = datas.map(function(item, index){
                return '<option value='+item[optionID]+'>'+item[optionName]+'</option>'
            })
            if(opts.isShowAll===true){
            	var allStr = optionID === "projID"?"@@@allProjs":"@@@allVers";
            	strArr.unshift('<option value='+allStr+'>全部</option>');
            }
            eleWrap.html(strArr.join(""));
        }
    }
    me.init();
    return this;
}

