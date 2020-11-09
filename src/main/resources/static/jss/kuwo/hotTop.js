$(function () {

    let pageIndex = 1;

    Vue.use(VueLazyload, {
        error: '/images/bg.gif',
        loading: '/images/bg.gif'
    });

    let hotTopList = [
        ["酷我飙升榜", "酷我新歌榜", "酷我热歌榜", "抖音热歌榜"],
        ["ACG新歌榜", "极品电音榜", "国风音乐榜", "网红新歌榜", "酷我热评榜", "DJ嗨歌榜", "爆笑相声榜", "最强翻唱榜", "经典怀旧榜", "影视金曲榜", "流行趋势榜"],
        ["夏日节拍榜", "Vlog背景乐榜", "巴士随身听榜", "枕边轻音乐榜", "熬夜修仙榜", "KTV点唱榜"]
    ];

    let hotTopIdList = [
        [279, 93, 17, 158],
        [290, 242, 278, 153, 284, 176, 291, 185, 26, 64, 187],
        [279, 264, 281, 283, 282, 255]
    ];

    let index = 1;
    let currentIndex = 1;
    let audioCurrentIndex = -1;
    let itemCurrentIndex = 0;

    let globalFlag = false;

    let rooter = new Vue({
        el: "#rooter",
        data: function () {
            return {
                hotTop: hotTopList[0],
                hotTopId: hotTopIdList[0],
                currentIndex: currentIndex,
                songs: null,
                itemCurrentIndex: itemCurrentIndex
            };
        },
        created: function () {
            $.ajax({
                type: "GET",
                url: "/kuwo/leaderBoard/" + hotTopIdList[0][currentIndex] + "/1/30",
                dataType: "json",
                success: function (data) {
                    rooter.songs = data.data.musicList;
                }
            });
        }
    });

    rooter.$watch("songs", function () {
        let headers = $("ul.header li");
        let hotTops = $("ul.hotTop li");
        let songPlayButtons = $("span.playButton");
        headers.unbind().click(function () {
            headers.removeClass("focus");
            $(this).addClass("focus");
            index = $(this).attr("index");
            currentIndex = 0;
            hotTops.removeClass("focus");
            hotTops.eq(currentIndex).addClass("focus");
            audioCurrentIndex = 0;
            globalFlag = false;
            songPlayButtons = $("span.playButton");
            $.ajax({
                type: "GET",
                url: "/kuwo/leaderBoard/" + hotTopIdList[index - 1][currentIndex] + "/1/30",
                dataType: "json",
                success: function (data) {
                    rooter.songs = data.data.musicList;
                }
            });

            rooter.hotTop = hotTopList[index - 1];
        });

        hotTops.unbind().click(function () {
            hotTops.removeClass("focus");
            $(this).addClass("focus");
            currentIndex = $(this).attr("_id");
            audioCurrentIndex = 0;
            globalFlag = false;
            songPlayButtons = $("span.playButton");
            $.ajax({
                type: "GET",
                url: "/kuwo/leaderBoard/" + hotTopIdList[index - 1][currentIndex] + "/1/30",
                dataType: "json",
                success: function (data) {
                    rooter.songs = data.data.musicList;
                }
            });

            rooter.hotTop = hotTopList[index - 1];
        });

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

        let playAllButton = $("li.playAll");
        let rid = songPlayButtons.eq(0).attr("rid");
        playAllButton.unbind().click(function () {
            audioCurrentIndex = 0;
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

        let $audio = $("audio");
        let audio = $audio.get(0);

        audio.addEventListener("playing", function () {
            songPlayButtons = $("span.playButton");
            songPlayButtons.eq(audioCurrentIndex).parent().addClass("playing");
            songPlayButtons.eq(audioCurrentIndex).text("播放中......");
        });

        audio.addEventListener("ended", function () {
            songPlayButtons = $("span.playButton");
            audioCurrentIndex++;
            let rid = songPlayButtons.eq(audioCurrentIndex).attr("rid");
            $.ajax({
                type: "GET",
                url: "/kuwo/songInfo/" + rid,
                dataType: "json",
                success: function (data) {
                    $audio.attr("src", data.url);
                    let title = $("title");
                    title.text("祥龙检索-酷我音乐--" + songPlayButtons.eq(audioCurrentIndex).attr("name"));
                }
            });
        });

        audio.addEventListener("error", function () {
            songPlayButtons = $("span.playButton");
            audioCurrentIndex++;
            let rid = songPlayButtons.eq(audioCurrentIndex).attr("rid");
            $.ajax({
                type: "GET",
                url: "/kuwo/songInfo/" + rid,
                dataType: "json",
                success: function (data) {
                    $audio.attr("src", data.url);
                    let title = $("title");
                    title.text("祥龙检索-酷我音乐--" + songPlayButtons.eq(audioCurrentIndex).attr("name"));
                }
            });
        });

        let flag = true;

        $(window).scroll(function () {
            if ($(document).scrollTop() >= ($(document).height() - $(window).height()) * 0.95) {
                if (flag === true){
                    pageIndex++;
                    flag = false;
                    $.ajax({
                        type: "GET",
                        url: "/kuwo/leaderBoard/" + hotTopIdList[index - 1][currentIndex] + "/" + pageIndex + "/30",
                        dataType: "json",
                        success: function (data) {
                            let songs = data.data.musicList;
                            if (songs !== undefined){
                                for (let i = 0; i < songs.length; i++) {
                                    rooter.songs.push(songs[i]);
                                }
                            }
                            flag = true;
                        }
                    });
                }
            }
        });
    });
});