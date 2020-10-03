$(function () {
    let currentIndex = 0;
    window.rooter = new Vue({
        el: "#rooter",
        data: function () {
            return {
                types: null,
                ranks: null,
                titles: null,
                subTitles: null,
                rids: null,
                hotTags: null,
                currentHotTags: null,
                hotTagKeywords: null,
                currentRids: null,
                videos: null,
                dynamicList: null,
                newList: null,
                rankingList: null,
                indexList: null,
                currentIndex: null,
                videoCount: null,
                pageCount: null
            };
        },
        created: function () {
            $.ajax({
                type: "GET",
                url: "/htmls/bilibili/config.json",
                dataType: "json",
                success: function (data) {
                    rooter.types = data.list;
                }
            });
            //获取所有子列表
            $.ajax({
                type: "GET",
                url: "/htmls/bilibili/list.json",
                dataType: "json",
                success: function (data) {
                    rooter.titles = data;
                }
            });

            //获取rid
            $.ajax({
                type: "GET",
                url: "/htmls/bilibili/rid.json",
                dataType: "json",
                success: function (data) {
                    rooter.rids = data;
                }
            });

            //获取hotTag
            $.ajax({
                type: "GET",
                url: "/htmls/bilibili/hotTag.json",
                dataType: "json",
                success: function (data) {
                    rooter.hotTags = data;
                }
            });
        }
    });

    rooter.$watch("hotTags", function () {
        let lis = $("ul.types li");
        //初始化li状态
        lis.eq(0).addClass("focus");
        lis.unbind().click(function () {
            lis.removeClass("focus");
            $(this).addClass("focus");
            rooter.subTitles = rooter.titles[$(this).text()];
            rooter.currentHotTags = rooter.hotTags[$(this).text()];
            rooter.currentRids = rooter.rids[$(this).text()];
            $("div.hotTagKeyWords ul li").removeClass("focus");
        });

        lis.eq(0).click();
    });

    rooter.$watch("subTitles", function () {
        let subTitleLis = $("div.subTitle ul li");
        subTitleLis.unbind().click(function () {
            subTitleLis.removeClass("focus");
            $(this).addClass("focus");
            $.ajax({
                type: "GET",
                url: "/bilibili/getHotTag?url=" + $(this).attr("href"),
                dataType: "json",
                success: function (data) {
                    rooter.hotTagKeywords = data;
                }
            });
        });

        subTitleLis.eq(0).click();
    });

    rooter.$watch("hotTagKeywords", function () {
        //处理hotTagKeyWords的点击事件
        let hotTagKeyWordsLis = $("div.hotTagKeyWords ul li");
        let currentLi = $("div.subTitle li.focus");
        let rid = currentLi.attr("rid");
        hotTagKeyWordsLis.eq(0).addClass("focus");
        $.ajax({
            type: "GET",
            url: "/bilibili/getRidVideo?rid=" + rid + "&pageIndex=1",
            dataType: "json",
            success: function (data) {
                rooter.videos = data;
                rooter.dynamicList = rooter.videos.dynamicList;
                rooter.newList = rooter.videos.newList.data.archives;
                rooter.rankingList = rooter.videos.rankingList;
                rooter.videoCount = rooter.videos.newList.data.page.count;
                rooter.pageCount = Math.floor(rooter.videoCount / 20);
            }
        });

        currentIndex = 0;

        //切换热门视频
        let hotTagKeyWordsLisIndex = 0;
        $("div.centerArea p.changeDynamicList").unbind().click(function () {
            $.ajax({
                type: "GET",
                url: "/bilibili/getDynamicList?rid=" + rid + "&pageIndex=" + ++hotTagKeyWordsLisIndex,
                dataType: "json",
                success: function (data) {
                    rooter.dynamicList = data;
                }
            });
        });
        //初始化index链接
        rooter.indexList = [];
        let i = 0;
        for (i = 0; i < 10; i++) {
            rooter.indexList.push([i + 1, "/bilibili/getNewList?rid=" + rid + "&pageIndex=" + (i + 1)]);
        }
        rooter.indexList.push(["下一页", "/bilibili/getNewList?rid=" + rid + "&pageIndex=" + (i + 1)]);

        hotTagKeyWordsLis.unbind().click(function () {
            hotTagKeyWordsLis.removeClass("focus");
            $(this).addClass("focus");
            let tagId = $(this).attr("tag_id");
            rooter.indexList = [];
            let i = 0;
            for (i = 0; i < 10; i++) {
                rooter.indexList.push([i + 1, "/bilibili/getNewListByTagId?rid=" + rid + "&pageIndex=" + (i + 1) + "&tagId=" + tagId]);
            }
            rooter.indexList.push(["下一页", "/bilibili/getNewListByTagId?rid=" + rid + "&pageIndex=" + (i + 1) + "&tagId=" + tagId]);
            if (tagId !== "all") {
                currentIndex = 0;
                rooter.currentIndex = currentIndex + 1;
                let currentLi = $("div.subTitle li.focus");
                let rid = currentLi.attr("rid");
                let lis = $("div.centerArea ul.index li");
                lis.removeClass("focus");
                $(this).addClass("focus");
                lis.unbind().click(function () {
                    if ($(this).find("a").text() === "下一页") {
                        rooter.indexList = [];
                        currentIndex++;
                        let i = currentIndex;
                        rooter.indexList.push(["上一页", "/bilibili/getNewListByTagId?rid=" + rid + "&pageIndex=" + (currentIndex - 1)]) + "&tagId=" + tagId;
                        for (i = currentIndex; i < 10 + currentIndex; i++) {
                            rooter.indexList.push([i + 1, "/bilibili/getNewListByTagId?rid=" + rid + "&pageIndex=" + (i + 1) + "&tagId=" + tagId]);
                        }
                        rooter.indexList.push(["下一页", "/bilibili/getNewListByTagId?rid=" + rid + "&pageIndex=" + (i + 1) + "&tagId=" + tagId]);
                    } else if ($(this).text() === "上一页") {
                        if (currentIndex > 0) {
                            rooter.indexList = [];
                            let i = --currentIndex;
                            rooter.indexList.push(["上一页", "/bilibili/getNewListByTagId?rid=" + rid + "&pageIndex=" + (currentIndex - 1) + "&tagId=" + tagId]);
                            for (i = currentIndex; i < 10 + currentIndex; i++) {
                                rooter.indexList.push([i + 1, "/bilibili/getNewListByTagId?rid=" + rid + "&pageIndex=" + (i + 1) + "&tagId=" + tagId]);
                            }
                            rooter.indexList.push(["下一页", "/bilibili/getNewListByTagId?rid=" + rid + "&pageIndex=" + (i + 1) + "&tagId=" + tagId]);
                        }
                    } else {
                        currentIndex = window.parseInt($(this).find("a").text()) - 1;
                        lis.removeClass("focus");
                        $(this).addClass("focus");
                    }
                });
                $.ajax({
                    type: "GET",
                    url: "/bilibili/getRidVideoByTagId?rid=" + rid + "&pageIndex=1&tagId=" + tagId,
                    dataType: "json",
                    success: function(data){
                        rooter.videos = data;
                        rooter.dynamicList = rooter.videos.dynamicList;
                        rooter.newList = rooter.videos.newList.data.archives;
                        rooter.rankingList = rooter.videos.rankingList;
                        rooter.videoCount = rooter.videos.newList.data.page.count;
                        rooter.pageCount = Math.floor(rooter.videoCount / 20);
                    }
                });
            }
        });
    });

    rooter.$watch("indexList", function () {
        rooter.currentIndex = currentIndex + 1;
        let currentLi = $("div.subTitle li.focus");
        let rid = currentLi.attr("rid");
        let tagId = $("div.hotTagKeyWords ul li.focus").attr("tag_id");
        let lis = $("div.centerArea ul.index li");
        lis.removeClass("focus");
        lis.unbind().click(function () {
            if (tagId === "all"){
                if ($(this).find("a").text() === "下一页") {
                    rooter.indexList = [];
                    currentIndex++;
                    let i = currentIndex;
                    rooter.indexList.push(["上一页", "/bilibili/getNewList?rid=" + rid + "&pageIndex=" + (currentIndex - 1)]);
                    for (i = currentIndex; i < 10 + currentIndex; i++) {
                        rooter.indexList.push([i + 1, "/bilibili/getNewList?rid=" + rid + "&pageIndex=" + (i + 1)]);
                    }
                    rooter.indexList.push(["下一页", "/bilibili/getNewList?rid=" + rid + "&pageIndex=" + (i + 1)]);
                } else if ($(this).text() === "上一页") {
                    if (currentIndex > 0) {
                        rooter.indexList = [];
                        let i = --currentIndex;
                        rooter.indexList.push(["上一页", "/bilibili/getNewList?rid=" + rid + "&pageIndex=" + (currentIndex - 1)]);
                        for (i = currentIndex; i < 10 + currentIndex; i++) {
                            rooter.indexList.push([i + 1, "/bilibili/getNewList?rid=" + rid + "&pageIndex=" + (i + 1)]);
                        }
                        rooter.indexList.push(["下一页", "/bilibili/getNewList?rid=" + rid + "&pageIndex=" + (i + 1)]);
                    }
                } else {
                    currentIndex = window.parseInt($(this).find("a").text()) - 1;
                }
                lis.removeClass("focus");
                $(this).addClass("focus");

                $.ajax({
                    type: "GET",
                    url: "/bilibili/getNewList?rid=" + rid + "&pageIndex=" + (currentIndex + 1),
                    dataType: "json",
                    success: function (data) {
                        rooter.newList = data.data.archives;
                        rooter.videoCount = data.data.page.count;
                        rooter.pageCount = Math.floor(rooter.videoCount / 20);
                    }
                });
            }
            else{
                if ($(this).find("a").text() === "下一页") {
                    rooter.indexList = [];
                    currentIndex++;
                    let i = currentIndex;
                    rooter.indexList.push(["上一页", "/bilibili/getNewListByTagId?rid=" + rid + "&pageIndex=" + (currentIndex - 1) + "&tagId=" + tagId]);
                    for (i = currentIndex; i < 10 + currentIndex; i++) {
                        rooter.indexList.push([i + 1, "/bilibili/getNewListByTagId?rid=" + rid + "&pageIndex=" + (i + 1) + "&tagId=" + tagId]);
                    }
                    rooter.indexList.push(["下一页", "/bilibili/getNewListByTagId?rid=" + rid + "&pageIndex=" + (i + 1) + "&tagId=" + tagId]);
                } else if ($(this).text() === "上一页") {
                    if (currentIndex > 0) {
                        rooter.indexList = [];
                        let i = --currentIndex;
                        rooter.indexList.push(["上一页", "/bilibili/getNewListByTagId?rid=" + rid + "&pageIndex=" + (currentIndex - 1) + "&tagId=" + tagId]);
                        for (i = currentIndex; i < 10 + currentIndex; i++) {
                            rooter.indexList.push([i + 1, "/bilibili/getNewListByTagId?rid=" + rid + "&pageIndex=" + (i + 1) + "&tagId=" + tagId]);
                        }
                        rooter.indexList.push(["下一页", "/bilibili/getNewListByTagId?rid=" + rid + "&pageIndex=" + (i + 1) + "&tagId=" + tagId]);
                    }
                } else {
                    currentIndex = window.parseInt($(this).find("a").text()) - 1;
                }
                lis.removeClass("focus");
                $(this).addClass("focus");

                $.ajax({
                    type: "GET",
                    url: "/bilibili/getNewListByTagId?rid=" + rid + "&pageIndex=" + (currentIndex + 1) + "&tagId=" + tagId,
                    dataType: "json",
                    success: function (data) {
                        rooter.newList = data.data.archives;
                        rooter.videoCount = data.data.page.count;
                        rooter.pageCount = Math.floor(rooter.videoCount / 20);
                    }
                });
            }
        });
    });

    $("div.centerArea p.count input").unbind().keydown(function (event) {
        let currentLi = $("div.subTitle li.focus");
        let rid = currentLi.attr("rid");
        if (event.keyCode === 13 && $(this).val() !== "") {
            let tagId = $("div.hotTagKeyWords ul li.focus").attr("tag_id");
            if (tagId === "all") {
                $.ajax({
                    type: "GET",
                    url: "/bilibili/getNewList?rid=" + rid + "&pageIndex=" + $(this).val(),
                    dataType: "json",
                    success: function (data) {
                        rooter.newList = data.data.archives;
                        rooter.videoCount = data.data.page.count;
                        rooter.pageCount = Math.floor(rooter.videoCount / 20);
                    }
                });
                let i = $(this).val() - 10;
                rooter.indexList = [];
                rooter.indexList.push(["上一页", "/bilibili/getNewList?rid=" + rid + "&pageIndex=" + i]);
                for (; i < $(this).val(); i++) {
                    rooter.indexList.push([i + 1, "/bilibili/getNewList?rid=" + rid + "&pageIndex=" + (i + 1)]);
                }
                rooter.indexList.push(["下一页", "/bilibili/getNewList?rid=" + rid + "&pageIndex=" + ($(this).val() + 1)]);
            } else {
                $.ajax({
                    type: "GET",
                    url: "/bilibili/getNewListByTagId?rid=" + rid + "&pageIndex=" + $(this).val() + "&tagId=" + tagId,
                    dataType: "json",
                    success: function (data) {
                        rooter.newList = data.data.archives;
                        rooter.videoCount = data.data.page.count;
                        rooter.pageCount = Math.floor(rooter.videoCount / 20);
                    }
                });
                let i = $(this).val() - 10;
                rooter.indexList = [];
                rooter.indexList.push(["上一页", "/bilibili/getNewListByTagId?rid=" + rid + "&pageIndex=" + i + "&tagId=" + tagId]);
                for (; i < $(this).val(); i++) {
                    rooter.indexList.push([i + 1, "/bilibili/getNewListByTagId?rid=" + rid + "&pageIndex=" + (i + 1) + "&tagId=" + tagId]);
                }
                rooter.indexList.push(["下一页", "/bilibili/getNewListByTagId?rid=" + rid + "&pageIndex=" + ($(this).val() + 1) + "&tagId=" + tagId]);
            }
        }
    });
});