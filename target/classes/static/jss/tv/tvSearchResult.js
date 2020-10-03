$(function () {

	let keyword = $("a#keyword").attr("title");

	Vue.use(VueLazyload, {
		error: '/images/bg.gif',
		loading: '/images/bg.gif'
	});

	let rooter = new Vue({
		el : "#rooter",
		data : function(){
			return {
				tvList : []
			};
		},
		created : function(){
			$.ajax({
				type: "GET",
				url: "selectByTvLikeTitle?keyword=" + keyword,
				dataType: "json",
				success: function (data) {
					let tvList = data;

					for (let i = 0; i < data.length; i++) {
						let dat = data[i];
						dat.href = "/showTv?url=" + dat.href + '&platform=iqiyi&aid=' + dat.aid + '&tvName=' + dat.title;
					}
					rooter.tvList = data;

					if (tvList.length === 0){
						window.location = "tvResult?pageIndex=1";
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
		$(this).attr("href","tvSearchResult?keyword="+$("input").val());
	});


	$("div.searchArea p input").val(keyword);

	$("div#fixed_top").css("display","none");
	$(document).scroll(function () {
		if ($(this).scrollTop() >= 316){
			$("div#fixed_top").css("display","block");
		}
		if ($(this).scrollTop() <= 244){
			$("div#fixed_top").css("display","none");
		}
	});
});