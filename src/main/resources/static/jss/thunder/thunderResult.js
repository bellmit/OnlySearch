$(function () {

    var classify = $("i#classify").text();
    var pageIndex = window.parseInt($("i#pageIndex").text());

    var url = null;
    if (classify === "最新影片") {
        url = "getPagingNewMovies";
    } else if (classify === "国内电影") {
        url = "getPagingChinaMovies";
    } else if (classify === "欧美电影") {
        url = "getPagingEuropeansMovies";
    } else if (classify === "华语电视") {
        url = "getPaginghuaYuTvs";
    } else if (classify === "日韩电视") {
        url = "getPagingRiHanTvs";
    } else if (classify === "欧美电视") {
        url = "getPagingOuMeiTvs";
    } else if (classify === "最新综艺") {
        url = "getPagingNewZongYis";
    } else if (classify === "旧版综艺") {
        url = "getPagingOldZongYis";
    } else if (classify === "动漫") {
        url = "getPagingDongMans";
    } else if (classify === "游戏") {
        url = "getPagingGames";
    }

    var rooter = new Vue({
        el: "#rooter",
        data: function () {
            return {
                thunders: [],
                classify: classify,
                pageIndex: pageIndex,
                indexs: []
            };
        },
        created: function () {
            $.ajax({
                type: "GET",
                url: url + "?pageIndex=" + pageIndex,
                dataType: "json",
                success: function (data) {
                    rooter.thunders = data;
                    console.dir(rooter.thunders)
                    for (let i = 0; i < data[0].pageCount; i++) {
                        rooter.indexs.push(i);
                    }

                    for (let i=0;i<rooter.thunders.length;i++){
                        rooter.thunders[i].downloadPageUrl = window.encodeURIComponent(rooter.thunders[i].downloadPageUrl);
                    }
                }
            });
        }
    });


    //设置链接列表
    var classifyUl = $("div.thunderPagingUl ul");
    var classifys = ["最新影片", "国内电影", "欧美电影", "华语电视", "日韩电视", "欧美电视"
        , "最新综艺", "旧版综艺", "动漫", "游戏"];
    // console.dir(categoryIds.length)
    // console.dir(keywords.length)
    var sclassifyHtml = "";
    for (var i = 0; i < classifys.length; i++) {
        if (classify == classifys[i]) {
            sclassifyHtml += "<li class='current'>\n" +
                "                <a href=\"getThunderPagingResult?classify=" + classifys[i] + "&pageIndex=1\">" + classifys[i] + "</a>\n" +
                "            </li>";
        } else {
            sclassifyHtml += "<li>\n" +
                "                <a href=\"getThunderPagingResult?classify=" + classifys[i] + "&pageIndex=1\">" + classifys[i] + "</a>\n" +
                "            </li>";
        }

    }
    classifyUl.html(sclassifyHtml);


    //数据提交
    $("div.searchArea p input").on("keydown", function (event) {
        $("div.searchArea a").attr("href", "getThunderResult?keyword=" + $(this).val() + "&typeid=" + $("select").val() + "&pageIndex=1");
        if (event.keyCode === 13 && $(this).val() !== "") {
            window.location = "getThunderResult?keyword=" + $(this).val() + "&typeid=" + $("select").val() + "&pageIndex=1";
        }
    });

    $("div#fixed_top").css("display", "none");
    $(document).scroll(function () {
        if ($(this).scrollTop() >= 316) {
            $("div#fixed_top").css("display", "block");
        }
        if ($(this).scrollTop() <= 244) {
            $("div#fixed_top").css("display", "none");
        }
    });

});