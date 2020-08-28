$(function () {

    var keyword = $("i#keyword").text();

	var rooter = new Vue({
		el : "#rooter",
		data : function(){
			return {
				baidus : [],
				indexs : null,
				keyword : keyword
			};
		},
		created : function(){
			$.ajax({
			    type: "GET",
			    url: "/getSearchPagingBaiduYunSources?keyword=" + keyword+"&pageIndex=1",
			    dataType: "json",
			    success: function (data) {
			        let baidus = data;
					
					rooter.baidus = baidus;
			
			        $("div.loadingTop").css({
			            display:"none"
			        });
			    }
			});
		}
	});


    //数据提交
    $("div.searchArea p input").on("keydown",function (event) {
        $("div.searchArea a").attr("href","baiduyunwangpanSearchResult?keyword=" + $(this).val()+"&pageIndex=1");
        if (event.keyCode === 13 && $(this).val() !== ""){
            window.location = "baiduyunwangpanSearchResult?keyword=" + $(this).val()+"&pageIndex=1";
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

	let flag = true;
	let pageIndex = 1;

	$(window).scroll(function () {
		if ($(document).scrollTop() >= ($(document).height() - $(window).height()) * 0.95) {
			if (flag){
				pageIndex++;
				flag = false;
				$.ajax({
					type: "GET",
					url: "/getSearchPagingBaiduYunSources?keyword=" + keyword+"&pageIndex="+pageIndex,
					dataType: "json",
					success: function (data) {
						let baidus = data;
						for (let i = 0; i < baidus.length; i++) {
							rooter.baidus.push(baidus[i]);
						}
						flag = true;
					}
				});
			}
		}
	});
});