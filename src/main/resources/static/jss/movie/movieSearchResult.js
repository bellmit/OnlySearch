$(function () {

    var keyword = $("i#keyword").text();

	Vue.use(VueLazyload, {
		error: '/images/bg.gif',
		loading: '/images/bg.gif'
	});
	
	var rooter = new Vue({
		el:"#rooter",
		data : function(){
			return {
				movieList : []
			};
		},
		created : function(){
			$.ajax({
			    type: "GET",
			    url: "selectByMovieLikeTitle?keyword=" + keyword,
			    dataType: "json",
			    success: function (data) {
			        var movieList = data;

					rooter.movieList = movieList;
					for (let i = 0; i < data.length; i++) {
						let dat = data[i];
						dat.href = "/playMovieWithThreePart?playUrl=" + dat.href + '&name=' + dat.title;
					}
			        if (movieList.length === 0){
			            window.location = "movieResult?pageIndex=1";
			        }
			
			
			        $("div.loadingTop").css({
			            display:"none"
			        });
			    }
			});
		}
	});

    


    //数据提交
	$("input").unbind().keyup(function (event) {
		if (event.keyCode === 13 && $(this).val() !== ""){
			$("a.searchButton").get(0).click();
		}
	});

	$("a.searchButton").unbind().click(function () {
		$(this).attr("href","movieSearchResult?keyword="+$("input").val());
	});

    $("div.searchArea p input").val(keyword);
});