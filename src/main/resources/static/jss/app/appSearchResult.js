$(function () {

    var keyword = $("i#keyword").text();
	
	var rooter = new Vue({
		el : "#rooter",
		data:function(){
			return {
				apps : []
			};
		},
		created : function(){
			$.ajax({
			    type: "GET",
			    url: "/appSearchResultJson?keyword=" + keyword,
			    dataType: "json",
			    success: function (data) {
			        var apps = data;
					
					rooter.apps = apps;
			
			        $("div.loadingTop").css({
			            display:"none"
			        });
			    }
			});
		}
	});

    //数据提交
    $("div.searchArea p input").on("keydown",function (event) {
        $("div.searchArea a").attr("href","/appSearchResult?keyword="+$(this).val());
        if (event.keyCode === 13 && $(this).val() !== ""){
            window.location = "/appSearchResult?keyword="+$(this).val();
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
});