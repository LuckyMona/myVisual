module.exports = {
	"initNav":initNav
}

function initNav(){
	$("#navW").load("nav.html", function(){
	    navDynamic();   //处理左侧导航的动态效果
	    myNavRouter();     //处理导航点击的路由效果
	    setUserName();  //设置header右侧的用户名
	});

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

	function myNavRouter(){
	    var hash = window.location.hash.substr(1);
	    if(hash){
	        $(".childUl a").removeClass('active');
	        var index = hash.indexOf("-");
	        if(index>-1){
	            var navHash = hash.substring(0,index);
	            // console.log(navHash)
	            $("#"+navHash).addClass("active");
	            return
	        }
	        $("#"+hash).addClass("active");
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
}