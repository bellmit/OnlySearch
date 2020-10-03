$(function () {
    var oLis = $("ul#tagChange > li");

    var oButtons = $("div#multify ul > li");

    for (var i=0;i<oButtons.length;i++){
        (function (i) {
            oButtons.eq(i).on("mouseup",function (event) {
                if (i==0){
                    oLis.eq(0).removeClass("hidden");
                    oLis.eq(1).addClass("hidden");
                    oLis.eq(2).addClass("hidden");
                    oButtons.eq(0).addClass("focus");
                    oButtons.eq(1).removeClass("focus");
                    oButtons.eq(2).removeClass("focus");
                }
                else if (i==1){
                    oLis.eq(1).removeClass("hidden");
                    oLis.eq(0).addClass("hidden");
                    oLis.eq(2).addClass("hidden");
                    oButtons.eq(1).addClass("focus");
                    oButtons.eq(0).removeClass("focus");
                    oButtons.eq(2).removeClass("focus");
                }
                else{
                    oLis.eq(2).removeClass("hidden");
                    oLis.eq(0).addClass("hidden");
                    oLis.eq(1).addClass("hidden");
                    oButtons.eq(2).addClass("focus");
                    oButtons.eq(0).removeClass("focus");
                    oButtons.eq(1).removeClass("focus");
                }
            });
        })(i);
    }


    //将位置放在第一个上，即腾讯视频
    oLis.eq(0).removeClass("hidden");
    oLis.eq(1).addClass("hidden");
    oLis.eq(2).addClass("hidden");
    oButtons.eq(0).addClass("focus");
    oButtons.eq(1).removeClass("focus");
    oButtons.eq(2).removeClass("focus");


    //撤回加载图片
    window.onresize = null;
    $("div.loading_pic").css({
        display : "none"
    });
    $("body").css({
        height : "auto",
        overflow : "scroll"
    });


    window.setTimeout(function () {
        //给v.qq.com里面的大屏幕topBar添加切换效果
        var vqqcomTopBarChangeUl = $("#tagChange div.vqqcom_top ul.topBar");
        var vqqcomtopBarChangeLis = $("#tagChange div.vqqcom_top ul.topBar > li");

        var svqqcomDotHtml = "<div class='circleDotDiv'><ul class='circleDot'>";
        for (var i=0;i<vqqcomtopBarChangeLis.length;i++){
            svqqcomDotHtml +=
                "                            <li>\n" +
                "                                <span class=\"circle\"></span>\n" +
                "                            </li>";
        }
        svqqcomDotHtml += "</ul></div>";
        vqqcomTopBarChangeUl.html(vqqcomTopBarChangeUl.html() + svqqcomDotHtml);
        window.setTimeout(function () {
            var tmpLis = $("#tagChange div.vqqcom_top ul.circleDot li");
            for (var i=0;i<tmpLis.length;i++){
                tmpLis.eq(i).css({
                    width : ($("#tagChange div.vqqcom_top ul.circleDot").width() / vqqcomtopBarChangeLis.length ) + "px"
                });
            }
        },300);
        var tmpLis = $("#tagChange div.vqqcom_top ul.circleDot li");
        var picLis = $("#tagChange div.vqqcom_top ul.topBar > li");
        var index = 0;

        window.setInterval(function () {
            for (var i=0;i<tmpLis.length;i++){
                picLis.eq(i).css({
                    opacity : "0"
                });
                tmpLis.eq(i).find("> span").removeClass("focus");
            }
            picLis.eq(index % tmpLis.length).css({
                opacity : "1"
            });
            tmpLis.eq(index % tmpLis.length).find("> span").addClass("focus");

            index ++;
        },3000);



        //给aiqiyi.com里面的大屏幕topBar添加切换效果
        var aiqiyicomTopBarChangeUl = $("#tagChange div.iqiyicom_top ul.topBar");
        var aiqiyicomtopBarChangeLis = $("#tagChange div.iqiyicom_top ul.topBar > li");

        var saiqiyicomDotHtml = "<div class='circleDotDiv'><ul class='circleDot'>";
        for (var i=0;i<aiqiyicomtopBarChangeLis.length;i++){
            saiqiyicomDotHtml +=
                "                            <li>\n" +
                "                                <span class=\"circle\"></span>\n" +
                "                            </li>";
        }
        saiqiyicomDotHtml += "</ul></div>";
        aiqiyicomTopBarChangeUl.html(aiqiyicomTopBarChangeUl.html() + saiqiyicomDotHtml);
        window.setTimeout(function () {
            var tmpLis = $("#tagChange div.iqiyicom_top ul.circleDot li");
            for (var i=0;i<tmpLis.length;i++){
                tmpLis.eq(i).css({
                    width : ($("#tagChange div.iqiyicom_top ul.circleDot").width() / aiqiyicomtopBarChangeLis.length ) + "px"
                });
            }
        },300);
        var tmpLis1 = $("#tagChange div.iqiyicom_top ul.circleDot li");
        var picLis1 = $("#tagChange div.iqiyicom_top ul.topBar > li");
        var index1 = 0;

        window.setInterval(function () {
            for (var i=0;i<tmpLis1.length;i++){
                picLis1.eq(i).css({
                    opacity : "0"
                });
                tmpLis1.eq(i).find("> span").removeClass("focus");
            }
            picLis1.eq(index % tmpLis1.length).css({
                opacity : "1"
            });
            tmpLis1.eq(index % tmpLis1.length).find("> span").addClass("focus");

            index1 ++;
        },3000);


        //给youku.com里面的大屏幕topBar添加切换效果
        var youkucomTopBarChangeUl = $("#tagChange div.youkucom_top ul.topBar");
        var youkucomtopBarChangeLis = $("#tagChange div.youkucom_top ul.topBar > li");

        var syoukucomDotHtml = "<div class='circleDotDiv'><ul class='circleDot'>";
        for (var i=0;i<youkucomtopBarChangeLis.length;i++){
            syoukucomDotHtml +=
                "                            <li>\n" +
                "                                <span class=\"circle\"></span>\n" +
                "                            </li>";
        }
        syoukucomDotHtml += "</ul></div>";

        youkucomTopBarChangeUl.html(youkucomTopBarChangeUl.html() + syoukucomDotHtml);
        window.setTimeout(function () {
            var tmpLis = $("#tagChange div.youkucom_top ul.circleDot li");
            for (var i=0;i<tmpLis.length;i++){
                tmpLis.eq(i).css({
                    width : ($("#tagChange div.youkucom_top ul.circleDot").width() / youkucomtopBarChangeLis.length ) + "px"
                });
            }
        },300);
        var tmpLis2 = $("#tagChange div.youkucom_top ul.circleDot li");
        var picLis2 = $("#tagChange div.youkucom_top ul.topBar > li");
        var index2 = 0;

        window.setInterval(function () {
            for (var i=0;i<tmpLis2.length;i++){
                picLis2.eq(i).css({
                    opacity : "0"
                });
                tmpLis2.eq(i).find("> span").removeClass("focus");
            }
            picLis2.eq(index % tmpLis2.length).css({
                opacity : "1"
            });
            tmpLis2.eq(index % tmpLis2.length).find("> span").addClass("focus");

            index2 ++;
        },3000);
    },3000);


    //处理数据提交
    $("div#multify input").on("keydown",function () {
       $("div#multify a").attr("href","result?keyword="+$(this).val()+"&pageIndex=1");
        if (event.keyCode === 13 && $(this).val() !== ""){
            window.location = "result?keyword="+$(this).val()+"&pageIndex=1";
        }
    });
});