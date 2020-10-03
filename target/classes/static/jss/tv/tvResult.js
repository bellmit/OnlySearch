$(function () {

	let pageIndex = $("i#pageIndex").text();

	Vue.use(VueLazyload, {
		error: '/images/bg.gif',
		loading: '/images/bg.gif'
	});

	var rooter = new Vue({
		el : "#rooter",
		data : function(){
			return {
				tvList : []
			};
		},
		created : function(){
			$.ajax({
				type: "GET",
				url: "/tvPlayResult?pageIndex=" + pageIndex,
				dataType: "json",
				success: function (data) {
					var tvList = data["tvList"];

					rooter.tvList = tvList;

					$("div.loadingTop").css({
						display:"none"
					});
				}
			});
		}
	});

	rooter.$watch("tvList",function () {
		var oAs = $("div.list-view ul li a");
		for (var i=0;i<oAs.length;i++){
			var url = oAs.eq(i).attr("_href");
			var platform = oAs.eq(i).attr("platform");
			var aid = oAs.eq(i).attr("albumId") != null ? oAs.eq(i).attr("albumId")  : "";
			var tvName =oAs.eq(i).find("span:nth-of-type(1)").text();
			oAs.eq(i).attr("href","/showTv?url=" + url + "&platform=" + platform + "&aid=" + aid + "&tvName=" + tvName);
		}
	});


	//数据提交
	$("input").unbind().keyup(function (event) {
		if (event.keyCode === 13 && $(this).val() !== ""){
			$("a.searchButton").get(0).click();
		}
	});

	$("a.searchButton").unbind().click(function () {
		$(this).attr("href","tvSearchResult?keyword="+$("input").val());
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
		if ($(document).scrollTop() >= ($(document).height() - $(window).height()) * 0.9) {
			pageIndex ++;
			$.ajax({
				type: "GET",
				url: "tvPlayResult?pageIndex=" + pageIndex,
				dataType: "json",
				success: function (data) {
					var tvList = data["tvList"];
					for (var i=0;i<tvList.length;i++){
						rooter.tvList.push(tvList[i]);
					}
				}
			});
		}
	});
});