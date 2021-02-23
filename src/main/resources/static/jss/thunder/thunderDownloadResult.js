$(function () {

    var url = $("i#url").text();

    $.ajax({
        type: "GET",
        url: "getDownloads?downloadPageUrl=" + url,
        dataType: "json",
        success: function (data) {
            var thunderUl = $("ul.thunderDownloadUl");
            var thunders = data;
            var sthunderHtml = "";
            for (var i=0;i<thunders.length;i++){
                sthunderHtml += "<li>\n" +
                    "            <div class=\"description\">"+thunders[i]["description"]+"</div>\n" +
                    "        </li>";
            }

            thunderUl.html(sthunderHtml);


            $("div.loadingTop").css({
                display:"none"
            });
        }
    });


    //数据提交
    $("div.searchArea p input").on("keydown",function (event) {
        $("div.searchArea a").attr("href","getThunderResult?keyword="+$(this).val())+"&typeid="+$("select").val()+"&pageIndex=1";
        if (event.keyCode === 13 && $(this).val() !== ""){
            window.location = "getThunderResult?keyword="+$(this).val()+"&typeid="+$("select").val()+"&pageIndex=1";
        }
    });

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