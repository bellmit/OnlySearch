$(function () {
    let url = $("#url").attr("title");
    let platform = $("#platform").attr("title");
    let aid = $("#aid").attr("title");
    let tvName = $("#tvName").attr("title");

    let rooter = new Vue({
        el : "#rooter",
        data : function () {
            return {
                tvPlayList : []
            };
        },
        created : function () {
            let rooter = this;
            $.ajax({
                type: "GET",
                url: "/showTvMaps?url=" + url + "&platform=" + platform + "&aid=" + aid + "&name=" + tvName,
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

            iframe.attr("src","https://jx.618g.com/?url="+$(this).attr("url"));
        });

        //初始化
        iframe.attr("src","https://jx.618g.com/?url="+lis.eq(0).attr("url"));
        lis.eq(0).addClass("focus");
    });

});