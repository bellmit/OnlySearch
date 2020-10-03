$(function () {

    var pageIndex = $("i#pageIndex").text();

	Vue.use(VueLazyload, {
		error: '/images/bg.gif',
		loading: '/images/bg.gif'
	});
	
	var rooter = new Vue({
		el : "#rooter",
		data : function(){
			return {
				movieList : [],
				sStarsList : []
			};
		},
		created : function(){
			$.ajax({
			    type: "GET",
			    url: "moviePlayResult?pageIndex=" + pageIndex,
			    dataType: "json",
			    success: function (data) {
			        var movieList = data["movieList"];
					var sStarsList = [];

					for (var i=0;i<movieList.length;i++){
						sStarsList.push(movieList[i]["subTitle"]);
					}
					
					rooter.movieList = movieList;
					rooter.sStarsList = sStarsList;
			
			
			        $("div.loadingTop").css({
			            display:"none"
			        });
			    }
			});
		}
	});
	
	
    //数据提交
    $("div.searchArea p input").on("keydown",function (event) {
        $("div.searchArea a").attr("href","movieSearchResult?keyword="+$(this).val());
        if (event.keyCode === 13 && $(this).val() !== ""){
            window.location = "movieSearchResult?keyword="+$(this).val();
        }
    });


	$("div#fixed_top").css("display","none");
	$(document).scroll(function () {
		if ($(this).scrollTop() >= 316){
			$("div#fixed_top").css("display","block");
		}
		if ($(this).scrollTop() <= 244){
			$("div#fixed_top").css("display","none");
		}
	});

	$(window).scroll(function() {
		if ($(document).scrollTop() >= ($(document).height() - $(window).height()) * 0.95) {
			pageIndex ++;
			$.ajax({
				type: "GET",
				url: "moviePlayResult?pageIndex=" + pageIndex,
				dataType: "json",
				success: function (data) {
					var movieUl = $("ul.movieUl");
					var movieList = data["movieList"];
					var sStarsList = [];

					for (var i=0;i<movieList.length;i++){
						sStarsList.push(movieList[i]["subTitle"]);
					}

					for (var i=0;i<movieList.length;i++){
						rooter.movieList.push(movieList[i]);
					}
					for (var i=0;i<sStarsList.length;i++){
						rooter.sStarsList.push(sStarsList[i]);
					}
				}
			});
		}
	});
});