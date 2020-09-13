$(function () {
    let rooter = new Vue({
        el : "#rooter",
        data : function () {
            return {
                tvPlayList : []
            };
        },
        created : function () {
            let rooter = this;
            let url = $("#url").attr("title");
            $.ajax({
                type: "GET",
                url: "/tengxun/analysisPageToList?url=" + window.encodeURIComponent(url),
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

            iframe.attr("src","https://www.2ajx.com/vip.php?url="+$(this).attr("url"));
        });

        //初始化
        iframe.attr("src","https://www.2ajx.com/vip.php?url="+lis.eq(0).attr("url"));
        lis.eq(0).addClass("focus");
    });

});