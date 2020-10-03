$(function () {
    let pageIndex = 0;
    let currentIndex = -1;
    let id = $("input#id").attr("title");
    let rooter = new Vue({
        el : "#rooter",
        data : function () {
            return {
                songIds : null,
                data : null
            };
        },
        created : function () {
            $.ajax({
                type: "GET",
                url: "/htmls/cloudMusic/topN-config.json",
                dataType: "json",
                success: function(data) {
                    rooter.data = data;
                }
            });

            $.ajax({
                type: "GET",
                url: "/cloudMusic/topNIds/" + id,
                dataType: "json",
                success: function(data) {
                    let songIds = [];
                    for (let i=0;i<data.length;i++){
                        songIds.push([data[i].songId,data[i].name]);
                    }
                    rooter.songIds = songIds;
                }
            });
        }
    });

    rooter.$watch("data",function () {
        //增加排行榜切换事件
        let lis = $("div.content div.topN ul li");
        lis.unbind().click(function () {
            window.location = "/cloudMusic/topN/" + $(this).attr("data");
        });

        for (let i=0;i<rooter.data.length;i++){
            if (id === rooter.data[i][0]){
                $("div.expand").text(rooter.data[i][1]);
                break;
            }
        }
    });

    rooter.$watch("songIds",function () {
        let lis = $("div.content ul li.song");
        let index = 0;
        let interval = window.setInterval(function () {
            (function (index) {
                $.ajax({
                    type: "GET",
                    url: "/cloudMusic/getSongInfoBySongId/" + rooter.songIds[index][0],
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