/*头部搜索框*/
$(function(){

    $(window).scroll(function(){
    	var T_search = $(".shop-header .search");
    	if ($(document).scrollTop() > 55) {
    		T_search.addClass("search-fixed");
    	}else {
    		T_search.removeClass("search-fixed");
    	}
    });

});