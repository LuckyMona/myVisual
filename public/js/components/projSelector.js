
$.fn.projSelector = function(options){
    var me = this,
        $e = $(this);

    me.init = function(){
        console.log("init start");

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

            getOptions(projs, "projName", projEle);

            var storagedProjID = storage.getItem("selectedProjID");
            var storagedVerID = storage.getItem("selectedVerID");

            if(storagedProjID && storagedVerID){
                var selectedProjVers = filterVers(vers, Number(storagedProjID));
                getOptions(selectedProjVers, "verName", verEle);
            }else{
                var firstProjVers = filterVers(vers, projs[0].projID);
                getOptions(firstProjVers, "verName", verEle);
                storage.setItem("selectedProjID",projs[0].projID);
                storage.setItem("selectedVerID",firstProjVers[0].verID);
            }

            projEle.change(function(){
                var selectedProjName = $(this).children('option:selected').val();
                var selectedProj = (projs.filter(function(item, index){
                    return item.projName === selectedProjName
                }))[0]

                storage.setItem("selectedProjID", selectedProj.projID);
                var projVers = filterVers(vers,selectedProj.projID)
                getOptions(projVers, "verName", verEle)
            })
            verEle.change(function(){
                var selectedVerName = $(this).children('option:selected').val();
                var selectedVer = (vers.filter(function(item, index){
                    return item.verName === selectedVerName
                }))[0];
                storage.setItem("selectedVerID", selectedVer.verID);
            })
        }

        function filterVers(vers,projID){
            return vers.filter(function(item, index){
                return item.projID === projID
            })
        }

        function getOptions(datas,optionName, eleWrap){
            var strArr = datas.map(function(item, index){
                return '<option>'+item[optionName]+'</option>'
            })
            eleWrap.html(strArr.join(""));
        }
    }
    me.init();
    return this;
}

