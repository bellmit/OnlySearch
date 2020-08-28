$(function () {
    let rooter = new Vue({
        el : "#rooter",
        data : function () {
            return {
                videoList : []
            };
        },
        created : function () {
            let rooter = this;
            let videoId = $("a#videoId").attr("title");
            let videoName = $("a#videoName").attr("title");
            $.ajax({
                type: "GET",
                url: "/youku/videoList/" + videoId,
                dataType: "json",
                success: function (data) {
                    rooter.videoList = data;
                }
            });
        }
    });

    /**
     * 处理电视剧的播放
     */
    rooter.$watch("videoList",function () {
        let lis = $("#rooter div.list-view ul li");
        let iframe = $("iframe");
        lis.unbind().click(function () {
            lis.removeClass("focus");

            $(this).addClass("focus");

            iframe.attr("src","https://jiexi.bm6ig.cn/?url="+$(this).attr("url"));
        });

        //初始化
        iframe.attr("src","https://jiexi.bm6ig.cn/?url="+lis.eq(0).attr("url"));
        lis.eq(0).addClass("focus");
    });

});