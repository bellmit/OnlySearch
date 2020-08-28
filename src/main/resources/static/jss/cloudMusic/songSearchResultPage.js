$(function () {
    let pageIndex = 0;
    let currentIndex = -1;
    let keyword = $("input#keyword").attr("title");
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
                url: "/cloudMusic/search/1/" + keyword + "/0/50",
                dataType: "json",
                success: function(data) {
                    data = data.result.songs;
                    let songIds = [];
                    for (let i=0;i<data.length;i++){
                        songIds.push([data[i].id,data[i].album.name]);
                    }

                    rooter.songIds = songIds;
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

    $(window).scroll(function() {
        if ($(document).scrollTop() >= ($(document).height() - $(window).height()) * 0.95) {
            pageIndex ++;
            $.ajax({
                type: "GET",
                url: "/cloudMusic/search/1/" + keyword + "/" + pageIndex * 50 + "/50",
                dataType: "json",
                success: function (data) {
                    console.dir(data)
                    data = data.result.songs;
                    for (let i=0;i<data.length;i++){
                        rooter.songIds.push([data[i].id,data[i].album.name]);
                    }
                }
            });
        }
    });
});