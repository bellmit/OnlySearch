$(function () {

    var keyword = $("i#keyword").text();
    var typeid = $("i#typeid").text();
    var pageIndex = $("i#pageIndex").text();
    var typeid2 = GetUrlParam("typeid");

    function GetUrlParam(paraName) {
        var url = document.location.toString();
        var arrObj = url.split("?");

        if (arrObj.length > 1) {
            var arrPara = arrObj[1].split("&");
            var arr;

            for (var i = 0; i < arrPara.length; i++) {
                arr = arrPara[i].split("=");

                if (arr != null && arr[0] == paraName) {
                    return arr[1];
                }
            }
            return "";
        }
        else {
            return "";
        }
    }

    $.ajax({
        type: "GET",
        url: "getThunderSearchPagingResult?keyword=" + keyword + "&typeid=" + typeid + "&pageIndex=" + pageIndex,
        dataType: "json",
        success: function (data) {
            //显示页码
            var pages = $("ul.list");
            var sPageHtml = "";
            if (data.length > 0) {
                for (var i = 0; i < data[0]["pageCount"]; i++) {
                    if (i == pageIndex - 1) {
                        sPageHtml += "<li class='current'><a href=\"getThunderResult?keyword=" + keyword + "&typeid=" + typeid2 + "&pageIndex=" + (i + 1) + "\">" + (i + 1) + "</a></li>";
                    }
                    else {
                        sPageHtml += "<li><a href=\"getThunderResult?keyword=" + keyword + "&typeid=" + typeid2 + "&pageIndex=" + (i + 1) + "\">" + (i + 1) + "</a></li>";
                    }

                }
            }
            pages.html(sPageHtml);
            var thunderSearchUl = $("ul.thunderSearchUl");
            var thunderSearchs = data;
            var sthunderSearchHtml = "";
            for (var i = 0; i < thunderSearchs.length; i++) {
                sthunderSearchHtml += "<li>\n" +
                    "                <p class=\"title\">\n" +
                    "                    <a href=\""+("getThunderDownloadResult?url="+window.encodeURIComponent(thunderSearchs[i]["downloadPageUrl"]))+"\">"+thunderSearchs[i]["title"]+"</a>\n" +
                    "                </p>\n" +
                    "                <p class=\"description\">\n" + thunderSearchs[i]["description"] +
                    "                </p>\n" +
                    "            </li>";
            }

            thunderSearchUl.html(sthunderSearchHtml);


            $("div.loadingTop").css({
                display: "none"
            });
        }
    });


    //数据提交
    $("div.searchArea p input").on("keydown", function (event) {
        $("div.searchArea a").attr("href", "getThunderResult?keyword=" + $(this).val()) + "&typeid=" + $("select").val() + "&pageIndex=1";
        if (event.keyCode === 13 && $(this).val() !== "") {
            window.location = "getThunderResult?keyword=" + $(this).val() + "&typeid=" + $("select").val() + "&pageIndex=1";
        }
    });


    $("div.searchArea p input").val(keyword);

    $("div#fixed_top").css("display","none");
    $(document).scroll(function () {
        if ($(this).scrollTop() >= 316){
            $("div#fixed_top").css("display","block");
        }
        if ($(this).scrollTop() <= 244){
            $("div#fixed_top").css("display","none");
        }
    });
});