$(function () {
    let albumId = $("input#albumId").attr("title");
    let audioCurrentIndex = -1;
    let pageIndex = 1;
    let globalFlag = false;
    let audio = $("audio");

    Vue.use(VueLazyload, {
        error: '/images/bg.gif',
        loading: '/images/bg.gif'
    });
    
    let rooter = new Vue({
        el : "#rooter",
        data : function () {
            return {
                albumInfos : {},
                musicList : {}
            };
        },
        created : function () {
            $.ajax({
                type: "GET",
                url: "/kuwo/albumInfo/" + albumId + "/1/30",
                dataType: "json",
                success: function (data) {
                    rooter.albumInfos = data.data;
                    rooter.musicList = data.data.musicList;
                }
            });
        }
    });

    rooter.$watch("musicList", function () {
        let songPlayButtons = $("span.playButton");
        songPlayButtons.unbind().click(function () {
            let _this = this;
            songPlayButtons.removeClass("focus");
            $(this).addClass("focus");
            audioCurrentIndex = $(this).attr("index");
            globalFlag = true;
            let rid = $(this).attr("rid");
            $.ajax({
                type: "GET",
                url: "/kuwo/songInfo/" + rid,
                dataType: "json",
                success: function (data) {
                    $("audio").attr("src", data.url);
                    let title = $("title");
                    title.text(title.text() + "--" + $(_this).attr("name"));
                }
            });
        });

        let playAllButton = $("p.playAllBtn");
        let rid = songPlayButtons.eq(0).attr("rid");
        playAllButton.unbind().click(function () {
            audioCurrentIndex = 0;
            globalFlag = true;
            $.ajax({
                type: "GET",
                url: "/kuwo/songInfo/" + rid,
                dataType: "json",
                success: function (data) {
                    $("audio").attr("src", data.url);
                    let title = $("title");
                    title.text(title.text() + "--" + songPlayButtons.eq(0).attr("name"));
                }
            });
        });

        audio.unbind().on("ended", function () {
            songPlayButtons = $("span.playButton");
            audioCurrentIndex++;
            let rid = songPlayButtons.eq(audioCurrentIndex).attr("rid");
            $.ajax({
                type: "GET",
                url: "/kuwo/songInfo/" + rid,
                dataType: "json",
                success: function (data) {
                    audio.attr("src", data.url);
                    let title = $("title");
                    title.text("祥龙检索-酷我音乐--" + songPlayButtons.eq(audioCurrentIndex).attr("name"));
                }
            });

            audio.unbind().on("error", function () {
                songPlayButtons = $("span.playButton");
                audioCurrentIndex++;
                let rid = songPlayButtons.eq(audioCurrentIndex).attr("rid");
                $.ajax({
                    type: "GET",
                    url: "/kuwo/songInfo/" + rid,
                    dataType: "json",
                    success: function (data) {
                        audio.attr("src", data.url);
                        let title = $("title");
                        title.text("祥龙检索-酷我音乐--" + songPlayButtons.eq(audioCurrentIndex).attr("name"));
                    }
                });
            });
        });

        let flag = false;
        window.setInterval(function () {
            if (-1 === audioCurrentIndex){
                return;
            }
            songPlayButtons.parents().removeClass("change");
            if (globalFlag){
                if (!flag){
                    window.setTimeout(function () {
                        songPlayButtons.eq(audioCurrentIndex).parent().addClass("change");
                        flag = !flag;
                    },1000);
                }
                else{
                    window.setTimeout(function () {
                        songPlayButtons.eq(audioCurrentIndex).parent().removeClass("change");
                        flag = !flag;
                    },1000);
                }
            }
        },1000);
    });

    $(window).scroll(function () {
        if ($(document).scrollTop() >= ($(document).height() - $(window).height()) * 0.95) {
            pageIndex++;
            $.ajax({
                type: "GET",
                url: "/kuwo/albumInfo/" + albumId + "/"+pageIndex+"/30",
                dataType: "json",
                success: function (data) {
                    let musicList = data.data.musicList;
                    for (let i = 0; i < musicList.length; i++) {
                        rooter.musicList.push(musicList[i]);
                    }
                }
            });
        }
    });
});