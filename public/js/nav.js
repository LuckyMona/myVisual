
$("#navW").load("nav.html", function(){
    navDynamic();   //处理左侧导航的动态效果
    myRouter();     //处理导航点击的路由效果
    initSelector(); //初始化应用选择器、proj选择器和vers选择器
});