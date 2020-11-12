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

        let $audio = $("audio");
        let audio = $audio.get(0);
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
                    audio.play();
                    let title = $("title");
                    title.text(title.text() + "--" + songPlayButtons.eq(0).attr("name"));
                }
            });
        });


        audio.addEventListener("playing", function () {
            songPlayButtons = $("span.playButton");
            songPlayButtons.parent().removeClass("playing");
            songPlayButtons.text("播放");
            songPlayButtons.eq(audioCurrentIndex).parent().addClass("playing");
            songPlayButtons.eq(audioCurrentIndex).text("播放中......");
        });

        audio.addEventListener("loadstart", function () {     //当浏览器开始寻找指定的音频/视频时，会发生 loadstart 事件。即当加载过程开始时
            console.log("event loadstart: " + (new Date()).getTime());
        });
        audio.addEventListener("durationchange", function () {   //当指定音频/视频的时长数据发生变化时，发生 durationchange 事件。
            console.log("event durationchange: " + (new Date()).getTime());
        });
        audio.addEventListener("loadedmetadata", function () {   //当指定的音频/视频的元数据已加载时，会发生 loadedmetadata 事件。
            console.log("event loadedmetadata: " + (new Date()).getTime());
        });
        audio.addEventListener("progress", function () {   //当浏览器正在下载指定的音频/视频时，会发生 progress 事件。
            console.log("event progress: " + (new Date()).getTime());
        });
        audio.addEventListener("suspend", function () {    //该事件在媒体数据被阻止加载时触发。 可以是完成加载后触发，或者因为被暂停的原因。
            console.log("event suspend: " + (new Date()).getTime());
        });
        audio.addEventListener("loadeddata", function () {   //当当前帧的数据已加载，但没有足够的数据来播放指定音频/视频的下一帧时，会发生 loadeddata 事件
            console.log("event loadeddata: " + (new Date()).getTime());
        });
        audio.addEventListener("canplay", function () {   //当浏览器能够开始播放指定的音频/视频时，发生 canplay 事件。
            console.log("event canplay: " + (new Date()).getTime());
        });
        audio.addEventListener("canplaythrough", function () {   //当浏览器预计能够在不停下来进行缓冲的情况下持续播放指定的音频/视频时，会发生 canplaythrough 事件。
            console.log("event canplaythrough: " + (new Date()).getTime());
        });
        audio.addEventListener("play", function () {   //开始播放时触发
            console.log("event play: " + (new Date()).getTime());
        });
        audio.addEventListener("playing", function () {   // 开始回放
            console.log("event playing: " + (new Date()).getTime());
        });
        audio.addEventListener("timeupdate", function () { //播放时间改变   这个会一直打印
            // console.log("event timeupdate: " + (new Date()).getTime());
        });
        audio.addEventListener("pause", function () {   // 暂停时会触发，当播放完一首歌曲时也会触发
            console.log("event pause: " + (new Date()).getTime());
        });
        audio.addEventListener("ended", function () {   //当播放完一首歌曲时也会触发
            console.log("event ended: " + (new Date()).getTime());
        });



        audio.addEventListener("abort", function () {     //客户端主动终止下载（不是因为错误引起）
            console.log("event abort: " + (new Date()).getTime()) ;
        });
        audio.addEventListener("error", function () {   //请求数据时遇到错误
            console.log("event error: " + (new Date()).getTime());
        });
        audio.addEventListener("stalled", function () {   //网速失速
            console.log("event stalled: " + (new Date()).getTime());
        });
        audio.addEventListener("waiting", function () {   //等待数据，并非错误
            console.log("event waiting: " + (new Date()).getTime());
        });
        audio.addEventListener("seeking", function () {  //寻找中
            console.log("event seeking: " + (new Date()).getTime());
        });
        audio.addEventListener("seeked", function () { //寻找完毕
            console.log("event seeked: " + (new Date()).getTime());
        });
        audio.addEventListener("ratechange", function () {  //播放速率改变
            console.log("event ratechange: " + (new Date()).getTime());
        });
        audio.addEventListener("volumechange", function () {    //音量改变
            console.log("event volumechange: " + (new Date()).getTime());
        });

        songPlayButtons = $("span.playButton");
        for (let i=0;i<songPlayButtons.length;i++){
            console.dir(songPlayButtons.eq(i).attr("name"))
        }

        let playFlag = true;
        audio.addEventListener("ended", function () {
            songPlayButtons = $("span.playButton");

            if (playFlag){
                audioCurrentIndex++;
                playFlag = false;
                let rid = songPlayButtons.eq(audioCurrentIndex).attr("rid");
                $.ajax({
                    type: "GET",
                    url: "/kuwo/songInfo/" + rid,
                    dataType: "json",
                    success: function (data) {
                        $audio.attr("src", data.url);
                        audio.play();
                        let title = $("title");
                        title.text("祥龙检索-酷我音乐--" + songPlayButtons.eq(audioCurrentIndex).attr("name"));
                        playFlag = true;
                    }
                });
            }

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