$(function(){
	
	var zIndex = 999999;

	$("li").mouseenter(function () {
		$(this).css("z-index:" , (zIndex ++));
	});

	new Vue({
		el:"#rooter",
		data:function () {
			return {
				cartoonPageContents : [],
				indexs : null,
				pageIndex : null,
				prefixUrl : null,
				classfyName : null
			};
		},
		created: function(){
			_this = this;
			var prefixUrl = $("#prefixUrl").attr("title");
			var pageIndex = $("#pageIndex").attr("title");
			var classfyName = $("#classfyName").attr("title");

			$.ajax({
				type: "GET",
				url: "cartoonPages",
				data:{
					"prefixUrl" : prefixUrl,
					"pageIndex" : pageIndex,
					"classfyName" : classfyName
				},
				dataType: "json",
				success: function(data){
					_this.cartoonPageContents = data.cartoonPageContents;
					_this.indexs = data.cartoonPageContents[0].indexs;
					_this.pageIndex = data.pageIndex;
					_this.prefixUrl = data.prefixUrl;
					_this.classfyName = data.classfyName;

					//移除loading组件
					$("div.loadingTop").remove();
				}
			});
		}
	});
	
});