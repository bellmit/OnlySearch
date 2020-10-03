$(function () {
    let playListId = $("input#playListId").attr("title");
    let currentIndex = -1;
    let rooter = new Vue({
        el : "#rooter",
        data : function () {
            return {
                songIds : null
            };
        },
        created : function () {
            $.ajax({
                type: "GET",
                url: "/cloudMusic/getPlaylistByPlayListId?playListId=" + playListId,
                dataType: "json",
                success: function(data){
                    rooter.songIds = data;
                }
            });
        }
    });

    rooter.$watch("songIds",function () {
        let lis = $("div.content ul li.song");
        let index = 0;
        let interval = window.setInterval(function () {
            (function (index) {
                $.ajax({
                    type: "GET",
                    url: "/cloudMusic/getSongInfoBySongId/" + rooter.songIds[index],
                    dataType: "json",
                    success: function(dat) {
                        lis.get(index).innerHTML = lis.get(index).
                        innerHTML.
                        replace("NAME",dat.name).
                        replace("NAME",dat.name).
                        replace("/images/cloudMusic/loading.gif",dat.picUrl).
                        replace("DATA_URL",dat.songUrl);
                    }
                });
            })(index);
            index ++;
            if (index === rooter.songIds.length){
                window.clearInterval(interval);
            }
        },300);

        lis.unbind().click(function () {
            lis.find("span.play").text("播放");
            $("audio").attr("src",$(this).find("span.play").attr("data-url"));
            $(this).find("span.play").text("播放中......");
            $("title").text("祥龙检索，千度寻--" + $(this).find("span.play").attr("data"));
            currentIndex = window.parseInt($(this).find("span.play").attr("index"));
        });

        //处理audio的事件
        let audio = $("audio");
        audio.get(0).addEventListener("ended",function () {
            currentIndex ++;
            lis.get(currentIndex % lis.length).click();
        });
        audio.get(0).addEventListener("error",function () {
            currentIndex ++;
            lis.get(currentIndex % lis.length).click();
        });
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