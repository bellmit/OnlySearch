$(function () {
    let bVid = $("input#bVid").attr("title");
    let cid = $("input#cid").attr("title");
    let aid = $("input#aid").attr("title");
    let currentPage = 1;
    window.rooter = new Vue({
        el : "#rooter",
        data : function () {
            return {
                pageList : null,
                videos : null,
                playUrl : null,
                title : null,
                description : null,
                currentPage : currentPage
            };
        },
        created : function () {
            $.ajax({
                type: "GET",
                url: "/bilibili/getBiliBiliVideoPageCids/" + aid,
                dataType: "json",
                success: function (data) {
                    rooter.pageList = data.data;
                }
            });
        }
    });

    rooter.$watch("pageList",function () {
        let pageList = rooter.pageList;
        console.dir(pageList)
        rooter.playUrl = "/bilibili/getVideoStream?cid=" + rooter.pageList[0].cid + "&bVid=" + bVid;

        let lis = $("#rooter ul li.item");
        for (let i = 0; i < lis.length; i++) {
            (function (i) {
                lis.eq(i).unbind().click(function () {
                    rooter.playUrl = "/bilibili/getVideoStream?cid=" + rooter.pageList[i].cid + "&bVid=" + bVid;
                    rooter.currentPage = $(this).attr("page");
                    lis.removeClass("focus");
                    $(this).addClass("focus");
                });
            })(i);
        }

        $("p > a").attr("href","/bilibili/downloadVideos/" + bVid + "/" + aid);
    });

});