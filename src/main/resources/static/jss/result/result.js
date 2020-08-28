$(function () {
    var keyword = $("i#keyword").text();
    var pageIndex = $("i#pageIndex").text();
    $.ajax({
        type: "GET",
        url: "getBigData?keyword="+keyword+"&pageIndex="+pageIndex,
        dataType: "json",
        success: function(data){
            var singleUl = $("ul.singleUl");
            var singles = data["single"];
            var ssingleHtml = "";
            for (var i=0;i<singles.length;i++){
                ssingleHtml += "<li class=\"single\">\n" +
                    "            <a href='"+singles[i]["href"]+"' target='_blank'>\n" +
                    "                <img src=\""+singles[i]["imgSrc"]+"\"/>\n" +
                    "            </a>\n" +
                    "            <a href='"+singles[i]["href"]+"' target='_blank'>\n" +
                    "                <span class=\"title\">"+singles[i]["title"]+"</span><br/>\n" +
                    "                <span class=\"time\">"+singles[i]["time"]+"</span>\n" +
                    "            </a>\n" +
                    "        </li>";
            }
            singleUl.html(ssingleHtml);


            var multifyUl = $("ul.multifyUl");
            var multifys = data["multiply"];
            var smultifyHtml = "";
            for (var i=0;i<multifys.length;i++){
                smultifyHtml += "<li class=\"multify\">\n" +
                    "            <a href='"+multifys[i]["href"]+"' target='_blank'>\n" +
                    "                <img src=\""+multifys[i]["imgSrc"]+"\"/>\n" +
                    "            </a>\n" +
                    "            <a href='"+multifys[i]["href"]+"' target='_blank'>\n" +
                    "                <span class=\"title\">"+multifys[i]["title"]+"</span><br/>\n" +
                    "            </a>\n" +
                    "        </li>";
            }
            multifyUl.html(smultifyHtml);


            window.setTimeout(function () {
                var oLis = multifyUl.find("li");
                for (var i=0;i<oLis.length;i++){
                    var oTempUl = document.createElement("ul");
                    var oPlayList = data["multiply"][i]["playList"];
                    var sPlayListHtml = "";

                    var targetIndex = -1;
                    for (var index=0;index<oPlayList.length;index++){
                        if (oPlayList[index]["title"] === "更多"){
                            targetIndex = index;
                            break;
                        }
                        if (oPlayList[index]["title"] === "更多>"){
                            oPlayList[index]["title"] = "更多";
                        }
                        if ("展开更多 (余{asyncTotal - (asyncCurrent || 5)}集)" === oPlayList[index]["title"]){
                            oPlayList[index]["title"] = "";
                        }
                        if ("收起" === oPlayList[index]["title"]){
                            oPlayList[index]["title"] = "";
                        }
                    }
                    oPlayList.splice(0,targetIndex+1);
                    for (var j=0;j<oPlayList.length;j++){
                        sPlayListHtml += "<li class='playList'><a href='"+oPlayList[j]["href"]+"' target='_blank'>" +
                            oPlayList[j]["title"] +
                            "</a></li>";
                    }
                    oTempUl.innerHTML  = sPlayListHtml;
                    oLis.get(i).appendChild(oTempUl);
                }


                var lis = $("li.playList");

                for (var i=lis.length-1;i>=0;i--){
                    if (lis.eq(i).find("a").text() === ""){
                        lis.get(i).parentNode.removeChild(lis.get(i));
                    }
                }
            },1000);


            var pages = $("ul.list");
            var sPageHtml = "";
            for (var i=0;i<20;i++){
                if (i == pageIndex - 1){
                    sPageHtml += "<li class='current'><a href=\"result?keyword="+keyword+"&pageIndex="+(i+1)+"\">"+(i+1)+"</a></li>";
                }
                else{
                    sPageHtml += "<li><a href=\"result?keyword="+keyword+"&pageIndex="+(i+1)+"\">"+(i+1)+"</a></li>";
                }

            }
            pages.html(sPageHtml);

            $("div.loadingTop").css({
                display:"none"
            });
        }
        ,
        error : function (event) {
            if (keyword.indexOf("电视剧") >= 0 || keyword.indexOf("dsj") >= 0){
                window.location = "tvResult?pageIndex=1";
            }
            else if (keyword.indexOf("电影") >= 0 || keyword.indexOf("dy") >= 0){
                window.location = "movieResult?pageIndex=1";
            }
            else if (keyword.indexOf("app") >= 0){
                window.location = "appResult?categoryId=-10&pageSize=60&pageContext=1";
            }
            else if (keyword.indexOf("音乐") >= 0 || keyword.indexOf("yy") >= 0){
                window.location = "musicResult?keyword=音乐&pageIndex=1&pageSize=60";
            }
            else{
                window.location = "../../../templates/error/error.ftl";
            }

        }
    });

    //数据提交
    $("div.searchArea p input").on("keydown",function (event) {
        $("div.searchArea a").attr("href","result?keyword="+$(this).val()+"&pageIndex=1");
        if (event.keyCode === 13 && $(this).val() !== ""){
            window.location = "result?keyword="+$(this).val()+"&pageIndex=1";
        }
    });


    $("div.searchArea p input").val(keyword);
});