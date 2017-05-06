
$.fn.projSelector = function(options){
    var me = this,
        $e = $(this);

    me.init = function(){

        /*$e.addClass("projSelector");*/
        if(window.localStorage){
            var storage = window.localStorage;

            //TODO: projs and vers need to change when backend run
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

            if(storagedProjID && storagedVerID){
                var selectedProjVers = filterVers(vers, Number(storagedProjID));
                getOptions(selectedProjVers, "verID", "verName", verEle);
                var nProjID = Number(storagedProjID);
                var nVerID = Number(storagedVerID);
                $(".projSelect option[value='"+nProjID+"']").attr("selected", true);
                $(".verSelect option[value='"+nVerID+"']").attr("selected", true);
            }else{
                var firstProjVers = filterVers(vers, projs[0].projID);
                getOptions(firstProjVers, "verID","verName", verEle);
                storage.setItem("selectedProjID",projs[0].projID);
                storage.setItem("selectedVerID",firstProjVers[0].verID);
            }

            projEle.change(function(){
                var selectedProjID = $(".projSelect option:selected").val();
                storage.setItem("selectedProjID", selectedProjID);
                var projVers = filterVers(vers,Number(selectedProjID))
                getOptions(projVers, "verID","verName", verEle);
                storage.setItem("selectedVerID", projVers[0].verID);
            })
            verEle.change(function(){
                var selectedVerID = $(".verSelect option:selected").val();
                storage.setItem("selectedVerID", selectedVerID);
            })
        }

        function filterVers(vers,projID){
            return vers.filter(function(item, index){
                return item.projID === projID
            })
        }

        function getOptions(datas,optionID, optionName, eleWrap){
            var strArr = datas.map(function(item, index){
                return '<option value='+item[optionID]+'>'+item[optionName]+'</option>'
            })
            eleWrap.html(strArr.join(""));
        }
    }
    me.init();
    return this;
}

