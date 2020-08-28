$(function () {
    let bVid = $("input#bVid").attr("title");
    let rooter = new Vue({
        el : "#rooter",
        data : function () {
            return {
                videoList : null
            };
        },
        created : function () {
            $.ajax({
                type: "GET",
                url: "/bilibili/getBiliBiliVideoPageCids/" + $("input#aid").attr("title"),
                dataType: "json",
                success: function (data) {
                    let videoList = data.data;
                    rooter.videoList = [];
                    for (let i=0;i<videoList.length;i++){
                        rooter.videoList.push(["/bilibili/getVideoStream?cid=" + videoList[i].cid + "&bVid=" + bVid,videoList[i].part + ".mp4"]);
                    }
                }
            });
        }
    });

});