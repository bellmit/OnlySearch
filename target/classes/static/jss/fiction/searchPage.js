$(function () {
    let keyword = $("input#keyword").attr("title");

    let pageIndex = 1;

    let size = 50;

    let rooter = new Vue({
        el : "#rooter",
        data : function () {
            return {
                fictionList : null
            };
        },
        created : function () {
            $.ajax({
                type: "GET",
                url: "/fiction/queryByKeyword/?keyword=" + keyword + "&offset=" + ((pageIndex-1) * size) + "&size=" + size,
                dataType: "json",
                success: function(data){
                    for (let i=0;i<data.length;i++){
                        data[i].introduction = data[i].introduction.substring(0,150) + "...";
                        data[i].id = data[i].href.split("/")[4].split("\.")[0];
                    }
                    rooter.fictionList = data;
                }
            });
        }
    });

    $(window).scroll(function() {
        if ($(document).scrollTop() >= ($(document).height() - $(window).height()) * 0.95) {
            pageIndex ++;
            //获取推荐列表
            $.ajax({
                type: "GET",
                url: "/fiction/queryByKeyword/?keyword=" + keyword + "&offset=" + ((pageIndex-1) * size) + "&size=" + size,
                dataType: "json",
                success: function(data){
                    for (let i=0;i<data.length;i++){
                        data[i].introduction = data[i].introduction.substring(0,150) + "...";
                        data[i].id = data[i].href.split("/")[4].split("\.")[0];
                        rooter.fictionList.push(data[i]);
                    }
                }
            });
        }
    });

    $("div#fixed_top").css("display","none");
    $(document).scroll(function () {
        if ($(this).scrollTop() >= 247){
            $("div#fixed_top").css("display","block");
        }
        if ($(this).scrollTop() <= 244){
            $("div#fixed_top").css("display","none");
        }
    });

    $("div.firstInput p input").on("keydown",function (event) {
        if (event.keyCode === 13 && $(this).val() !== ""){
            let oA = $("div.div.firstInput div.searchArea a");
            oA.attr("href","/fiction/fictionSearchResult?keyword="+$(this).val());
            oA.get(0).click();
        }
    });

    $("div.firstInput div.searchArea a").unbind().click(function () {
        $(this).attr("href","/fiction/fictionSearchResult?keyword="+$("div.firstInput div.searchArea p input").val());
    });

    $("div#fixed_top div.firstInput p input").on("keydown",function (event) {
        if (event.keyCode === 13 && $(this).val() !== ""){
            let oA = $("div#fixed_top div.searchArea a");
            oA.attr("href","/fiction/fictionSearchResult?keyword="+$(this).val());
            oA.get(0).click();
        }
    });

    $("div#fixed_top div.searchArea a").unbind().click(function () {
        $(this).attr("href","/fiction/fictionSearchResult?keyword="+$("div#fixed_top div.searchArea p input").val());
    });
});