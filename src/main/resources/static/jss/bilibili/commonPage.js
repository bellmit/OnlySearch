$(function () {
    function timestampToTime(timestamp) {
        let date = new Date(timestamp * 1000); //时间戳为10位需*1000，时间戳为13位的话不需乘1000
        let Y = date.getFullYear() + '-';
        let M = (date.getMonth() + 1 < 10 ? '0' + (date.getMonth() + 1) : date.getMonth() + 1) + '-';
        let D = change(date.getDate()) + ' ';
        let h = change(date.getHours()) + ':';
        let m = change(date.getMinutes()) + ':';
        let s = change(date.getSeconds());
        return Y + M + D + h + m + s;
    }

    function change(t) {
        if (t < 10) {
            return "0" + t;
        } else {
            return t;
        }
    }

    let pageIndex = 1;
    let rooter = new Vue({
        el: "#rooter",
        data: function () {
            return {
                order: null,
                duration: null,
                tids_1: null,
                videoList: null,
                pageNums: null,
                date: null,
                nonePage : false
            };
        }
    });

    /**
     * 初始化数据
     * @type {*|jQuery}
     */
    rooter.order = "totalrank";
    rooter.duration = "0";
    rooter.tids_1 = "0";

    $("input.search").val($("input.keyword").attr("title"));

    dealSearchPage();

    //改变变量的值，添加click事件
    let orders = $(".types ul:nth-child(1) li");
    orders.eq(0).addClass("focus");
    orders.unbind().click(function () {
        rooter.order = $(this).attr("order");
        orders.removeClass("focus");
        $(this).addClass("focus");
        dealSearchPage();
    });

    //改变变量的值，添加click事件
    let durations = $(".types ul:nth-child(2) li");
    durations.eq(0).addClass("focus");
    durations.unbind().click(function () {
        rooter.duration = $(this).attr("duration");
        durations.removeClass("focus");
        $(this).addClass("focus");
        dealSearchPage();
    });

    //改变变量的值，添加click事件
    let tids_1s = $(".types ul:nth-child(3) li");
    tids_1s.eq(0).addClass("focus");
    tids_1s.unbind().click(function () {
        rooter.tids_1 = $(this).attr("tids_1");
        tids_1s.removeClass("focus");
        $(this).addClass("focus");
        dealSearchPage();
    });

    function dealSearchPage() {
        pageIndex = 1;
        $.ajax({
            type: "GET",
            url: "/bilibili/searchResult/?keyword=" + $("input").val() + "&order=" + rooter.order + "&duration=" + rooter.duration + "&tids_1=" + rooter.tids_1 + "&pageIndex=" + pageIndex,
            dataType: "json",
            success: function (data) {
                rooter.videoList = JSON.parse(data.videoList);
                for (let i = 0; i < data.times.length; i++) {
                    rooter.videoList[i].duration = data.times[i];
                    rooter.videoList[i].date = timestampToTime(rooter.videoList[i].pubdate);
                }
                rooter.pageNums = data.pageNumbers;
                rooter.nonePage = false;
            },
            error : function () {
                rooter.nonePage = true;
            }
        });
    }


    $(window).scroll(function() {
        if ($(document).scrollTop() >= ($(document).height() - $(window).height()) * 0.9) {
            pageIndex ++;
            $.ajax({
                type: "GET",
                url: "/bilibili/searchResult/?keyword=" + $("input").val() + "&order=" + rooter.order + "&duration=" + rooter.duration + "&tids_1=" + rooter.tids_1 + "&pageIndex=" + pageIndex,
                dataType: "json",
                success: function (data) {
                    let videoList = JSON.parse(data.videoList);
                    for (let i = 0; i < data.times.length; i++) {
                        videoList[i].duration = data.times[i];
                        videoList[i].date = timestampToTime(videoList[i].pubdate);
                    }
                    rooter.pageNums = data.pageNumbers;

                    for (let i=0;i<videoList.length;i++){
                        rooter.videoList.push(videoList[i]);
                    }
                    rooter.nonePage = false;
                }
            });
        }
    });

    $("input.search").unbind().keydown(function (event) {
        if (event.keyCode === 13 && $(this).val() !== ""){
            window.location = "/bilibili/searchPage?keyword="+$(this).val();
        }
    });

    $("i.searchButton").unbind().click(function () {
        window.location = "/bilibili/searchPage?keyword="+$("input.search").val();
    });
});