$(function () {

    var keyword = $("i#keyword").text();
    var pageIndex = $("i#pageIndex").text();
    var pageSize = $("i#pageSize").text();

	var rooter = new Vue({
		el : "#rooter",
		data : function(){
			return {
				musics : []
			};
		},
		created : function () {
			$.ajax({
				type: "GET",
				url: "getAllMatchingMusics?keyword=" + keyword+"&pageIndex="+pageIndex+"&pageSize="+pageSize,
				dataType: "json",
				success: function (data) {
					var musics = data;

					rooter.musics = musics;
				}
			});
		}
	});

	rooter.$watch("musics",function () {
		//处理视频播放
		$("ul.musicUl span.audio").unbind().click(function () {
			window.clearInterval(window.interval);
			$("audio").attr("src",$(this).attr("_src"));
			document.title = "祥龙检索，搜音乐--" + $(this).attr("_title");
			var _this = this;
			var flag = true;
			window.interval = window.setInterval(function () {
				if (flag){
					$(_this).parent().find("img").css({
						"transform" : "scale(1.2)"
					});
					flag = !flag;
				}
				else{
					$(_this).parent().find("img").css({
						"transform" : "scale(1.0)"
					});
					flag = !flag;
				}
			},1000);
		});
	});

    //数据提交
    $("div.searchArea p input").on("keydown",function (event) {
        $("div.searchArea a").attr("href","musicResult?keyword=" + $(this).val()+"&pageIndex=1&pageSize=20");
        if (event.keyCode === 13 && $(this).val() !== ""){
            window.location = "musicResult?keyword=" + $(this).val()+"&pageIndex=1&pageSize=60";
        }
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

	$(window).scroll(function() {
		if ($(document).scrollTop() >= ($(document).height() - $(window).height()) * 0.9) {
			pageIndex ++;
			$.ajax({
				type: "GET",
				url: "getAllMatchingMusics?keyword=" + keyword+"&pageIndex="+pageIndex+"&pageSize="+pageSize,
				dataType: "json",
				success: function (data) {
					var musics = data;

					for (var i=0;i<musics.length;i++){
						rooter.musics.push(musics[i]);
					}
				}
			});
		}
	});
});