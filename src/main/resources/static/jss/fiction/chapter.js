$(function () {
    let id = $("input#id").attr("title");
    let pageIndex = 0;
    let pageNumber = 0;
    let flag = true;
    let rooter = new Vue({
        el : "#rooter",
        data : function () {
            return {
                chapterList : null,
                currentChapters : []
            };
        },
        created : function () {
            $.ajax({
                type: "GET",
                url: "/fiction/showChapter/" + id,
                dataType: "json",
                success: function (data) {
                    rooter.chapterList = data;
                }
            });
        }
    });

    rooter.$watch("chapterList",function () {
        $.ajax({
            type: "GET",
            url: "/fiction/getChapterCatalog/" + id + "/" + rooter.chapterList[0].chapters[0].chapterUrl.split("/")[5].split("\.")[0],
            dataType: "json",
            success: function (data) {
                rooter.currentChapters = [data];
            }
        });
    });

    $(window).scroll(function() {
        if ($(document).scrollTop() >= ($(document).height() - $(window).height()) * 0.95) {
            if (flag){
                flag = false;
                pageIndex ++;
                $.ajax({
                    type: "GET",
                    url: "/fiction/getChapterCatalog/" + id + "/" + rooter.chapterList[pageNumber].chapters[pageIndex].chapterUrl.split("/")[5].split("\.")[0],
                    dataType: "json",
                    success: function (data) {
                        rooter.currentChapters.push(data);
                        if (pageIndex > rooter.chapterList[pageNumber].length){
                            pageNumber ++;
                            pageIndex = 0;
                        }
                        flag = true;
                    }
                });
            }
        }
    });
});