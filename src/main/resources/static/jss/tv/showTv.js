$(function () {
    var rooter = new Vue({
        el : "#rooter",
        data : function () {
            return {
                tvPlayList : []
            };
        },
        created : function () {
            var rooter = this;
            var url = $("#url").attr("title");
            var platform = $("#platform").attr("title");
            var aid = $("#aid").attr("title");
            $.ajax({
                type: "GET",
                url: "/showTvMaps?url=" + url + "&platform=" + platform + "&aid=" + aid,
                dataType: "json",
                success: function (data) {
                    rooter.tvPlayList = data;
                }
            });
        }
    });

    /**
     * 处理电视剧的播放
     */
    rooter.$watch("tvPlayList",function () {
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