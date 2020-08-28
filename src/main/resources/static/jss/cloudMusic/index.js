$(function () {
    let pageIndex = 0;
    window.rooter = new Vue({
        el : "#rooter",
        data : function () {
            return {
                language : null,
                style : null,
                scene : null,
                emotion : null,
                theme : null,
                musicList : null,
                cat : "华语"
            };
        },
        created : function () {
            $.ajax({
                type: "GET",
                url: "/htmls/cloudMusic/cloudMusic-config.json",
                dataType: "json",
                success: function(data){
                    rooter.language = data["语种"];
                    rooter.style = data["风格"];
                    rooter.scene = data["场景"];
                    rooter.emotion = data["情感"];
                    rooter.theme = data["主题"];
                }
            });

            /*
             * 请求数据
             */
            rooter.cat = "华语";
            $.ajax({
                type: "GET",
                url: "/cloudMusic/playlist/"+rooter.cat+"/0/35",
                dataType: "json",
                success: function(data){
                    rooter.musicList = data;
                    for (let i=0;i<data.length;i++){
                        data[i].title = data[i].title.substring(0,9) + "...";
                    }
                }
            });
        }
    });

    rooter.$watch("language",function () {
        let items = $("div.content div.cat span.item");
        items.unbind().click(function () {
            items.removeClass("focus");
            $(this).addClass("focus");
            rooter.cat = $(this).text();
        });
    });

    rooter.$watch("musicList",function () {
        let oLis = $("div.playlist ul li");

    });

    rooter.$watch("cat",function () {
        pageIndex = 0;
        $.ajax({
            type: "GET",
            url: "/cloudMusic/playlist/"+rooter.cat+"/0/35",
            dataType: "json",
            success: function(data){
                rooter.musicList = data;
                for (let i=0;i<data.length;i++){
                    data[i].title = data[i].title.substring(0,9) + "...";
                }
            }
        });
    });
    
    $(window).scroll(function() {
        if ($(document).scrollTop() >= ($(document).height() - $(window).height()) * 0.95) {
            pageIndex ++;
            $.ajax({
                type: "GET",
                url: "/cloudMusic/playlist/"+rooter.cat+"/"+pageIndex+"/35",
                dataType: "json",
                success: function(data){
                    for (let i=0;i<data.length;i++){
                        data[i].title = data[i].title.substring(0,9) + "...";
                        rooter.musicList.push(data[i]);
                    }
                }
            });
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