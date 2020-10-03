$(function () {
	
	var rooter = new Vue({
		el : "#rooter",
		data:function(){
			return {
				apps : [],
				indexs : null,
				categoryId : null,
				pageSize : null,
				pageContext : null,
				keywords : null,
				categoryIds : null,
				index_a : null
			};
		},
		created : function(){
			var categoryId = $("i#categoryId").text();
			var pageSize = $("i#pageSize").text();
			var pageContext = $("i#pageContext").text();
			
			this.categoryId = categoryId;
			this.pageSize = pageSize;
			this.pageContext = pageContext;
			
			$.ajax({
			    type: "GET",
			    url: "getClassifyApps?categoryId=" + categoryId+"&pageSize="+pageSize+"&pageContext="+pageContext,
			    dataType: "json",
			    success: function (data) {
					rooter.apps = data["apps"];
			    }
			});
			
			
			//显示页码
			var indexs = [];
			for (var i=0;i<100;i++){
			    if (i == pageContext - 1){
					indexs.push("current");
			    }
			    else{
					indexs.push("");
			    }
			}
			
			this.indexs = indexs;
			
			//设置链接列表
			var keywords = ["腾讯软件","购物","阅读","新闻","视频","旅游"
			,"工具","社交","音乐","美化","摄影","理财","系统","生活","出行",
			    "安全","教育","健康","娱乐","儿童","办公","通讯"];
			var categoryIds = [-10,122,102,110,103,108,115,106,101,119,104,114,117,107,
			112,118,111,109,105,100,113,116];
			// console.dir(categoryIds.length)
			// console.dir(keywords.length)
			
			this.keywords = keywords;
			this.categoryIds = categoryIds;
			var index_a = [];
			for (var i=0;i<keywords.length;i++){
			    if (categoryId == categoryIds[i]){
					index_a.push("current");
			    }
			    else{
					index_a.push("");
			    }
			}
			
			this.index_a = index_a;
		}
	});


    //数据提交
    $("div.searchArea p input").on("keydown",function (event) {
        $("div.searchArea a").attr("href","/appSearchResult?keyword="+$(this).val());
        if (event.keyCode === 13 && $(this).val() !== ""){
            window.location = "appSearchResult?keyword="+$(this).val();
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
});