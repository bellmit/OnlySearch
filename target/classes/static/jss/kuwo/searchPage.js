$(function () {

    let keyword = $("input#keyword").attr("title");

    Vue.use(VueLazyload, {
        error: '/images/bg.gif',
        loading: '/images/bg.gif'
    });

    let rooter = new Vue({
        el: "#rooter",
        data: function () {
            return {
                musicList: null,
                currentIndex: 0,
                albumList: null,
                mvList: null,
                searchPlayList : null,
                artistList : null
            };
        },
        created() {
            $.ajax({
                type: "GET",
                url: "/kuwo/searchMusic/" + window.encodeURIComponent(keyword) + "/1/30",
                dataType: "json",
                success: function (data) {
                    rooter.musicList = data.data.list;
                }
            });

            $.ajax({
                type: "GET",
                url: "/kuwo/searchAlbum/" + window.encodeURIComponent(keyword) + "/1/30",
                dataType: "json",
                success: function (data) {
                    rooter.albumList = data.data.albumList;
                }
            });

            $.ajax({
                type: "GET",
                url: "/kuwo/searchMv/" + window.encodeURIComponent(keyword) + "/1/30",
                dataType: "json",
                success: function (data) {
                    rooter.mvList = data.data.mvlist;
                }
            });


            $.ajax({
                type: "GET",
                url: "/kuwo/searchPlayList/" + window.encodeURIComponent(keyword) + "/1/30",
                dataType: "json",
                success: function (data) {
                    rooter.searchPlayList = data.data.list;
                }
            });

            $.ajax({
                type: "GET",
                url: "/kuwo/searchArtist/" + window.encodeURIComponent(keyword) + "/1/30",
                dataType: "json",
                success: function (data) {
                    rooter.artistList = data.data.artistList;
                }
            });
        }
    });


    let spans = $("div.indexTop span");
    spans.unbind().click(function () {
        spans.removeClass("focus");
        $(this).addClass("focus");
        rooter.currentIndex = window.parseInt($(this).attr("data-index"));
    });


    //处理单曲的播放
    rooter.$watch("musicList", function () {
        let playButtons = $("div.music li.musicItem span.play");
        playButtons.unbind().click(function () {
            playButtons.parent().removeClass("focus");
            $(this).parent().addClass("focus");
            playButtons.text("播放");
            let _this = this;
            $.ajax({
                type: "GET",
                url: "/kuwo/songInfo/" + $(this).attr("rid"),
                dataType: "json",
                success: function (data) {
                    $(_this).text("播放中...");
                    $("audio").attr("src", data.url);
                }
            });
        });


        //播放全部
        let playAllBtn = $("div.music li.playButton");
        let currentPlayIndex = 0;
        playAllBtn.unbind().click(function () {
            playButtons.parent().removeClass("focus");
            let firstPlayButton = playButtons.eq(currentPlayIndex);
            firstPlayButton.parent().addClass("focus");
            $.ajax({
                type: "GET",
                url: "/kuwo/songInfo/" + firstPlayButton.attr("rid"),
                dataType: "json",
                success: function (data) {
                    firstPlayButton.text("播放中...");
                    $("audio").attr("src", data.url);
                }
            });
        });

        let audio = $("audio");

        audio.get(0).onended = function () {
            currentPlayIndex++;
            let currentPlayButton = playButtons.eq(currentPlayIndex);
            playButtons.removeClass("focus");
            currentPlayButton.parent().addClass("focus");
            $.ajax({
                type: "GET",
                url: "/kuwo/songInfo/" + currentPlayButton.attr("rid"),
                dataType: "json",
                success: function (data) {
                    currentPlayButton.text("播放中...");
                    $("audio").attr("src", data.url);
                }
            });
        };

        audio.get(0).onerror = function () {
            currentPlayIndex++;
            let currentPlayButton = playButtons.eq(currentPlayIndex);
            playButtons.removeClass("focus");
            currentPlayButton.parent().addClass("focus");
            $.ajax({
                type: "GET",
                url: "/kuwo/songInfo/" + currentPlayButton.attr("rid"),
                dataType: "json",
                success: function (data) {
                    currentPlayButton.text("播放中...");
                    $("audio").attr("src", data.url);
                }
            });
        };
    });


    rooter.$watch("albumList", function () {

    });

    let musicPageIndex = 1;
    let albumPageIndex = 1;
    let mvPageIndex = 1;
    let searchPlayListIndex = 1;
    let artistPageIndex = 1;
    let musicFlag = true;
    let albumFlag = true;
    let mvFlag = true;
    let searchPlayListFlag = true;
    let artistFlag = true;

    $(window).scroll(function () {
        if ($(document).scrollTop() >= ($(document).height() - $(window).height()) * 0.95) {
            if (rooter.currentIndex === 0) {
                if (musicFlag) {
                    musicPageIndex++;
                    musicFlag = false;
                    $.ajax({
                        type: "GET",
                        url: "/kuwo/searchMusic/" + window.encodeURIComponent(keyword) + "/" + musicPageIndex + "/30",
                        dataType: "json",
                        success: function (data) {
                            let musicList = data.data.list;
                            for (let i = 0; i < musicList.length; i++) {
                                rooter.musicList.push(musicList[i]);
                            }
                            musicFlag = true;
                        }
                    });
                }
            } else if (rooter.currentIndex === 1) {
                if (albumFlag) {
                    albumPageIndex++;
                    albumFlag = false;
                    $.ajax({
                        type: "GET",
                        url: "/kuwo/searchAlbum/" + window.encodeURIComponent(keyword) + "/" + albumPageIndex + "/30",
                        dataType: "json",
                        success: function (data) {
                            let albumList = data.data.albumList;
                            for (let i = 0; i < albumList.length; i++) {
                                rooter.albumList.push(albumList[i]);
                            }
                            albumFlag = true;
                        }
                    });
                }
            }
            else if (rooter.currentIndex === 2) {
                if (mvFlag) {
                    mvPageIndex++;
                    mvFlag = false;
                    $.ajax({
                        type: "GET",
                        url: "/kuwo/searchMv/" + window.encodeURIComponent(keyword) + "/" + mvPageIndex + "/30",
                        dataType: "json",
                        success: function (data) {
                            let searchPlayList = data.data.list;
                            for (let i = 0; i < searchPlayList.length; i++) {
                                rooter.searchPlayList.push(searchPlayList[i]);
                            }
                            mvFlag = true;
                        }
                    });
                }
            }

            else if (rooter.currentIndex === 3) {
                if (searchPlayListFlag) {
                    searchPlayListIndex++;
                    searchPlayListFlag = false;
                    $.ajax({
                        type: "GET",
                        url: "/kuwo/searchPlayList/" + window.encodeURIComponent(keyword) + "/" + searchPlayListIndex + "/30",
                        dataType: "json",
                        success: function (data) {
                            let searchPlayList = data.data.list;
                            for (let i = 0; i < searchPlayList.length; i++) {
                                rooter.searchPlayList.push(searchPlayList[i]);
                            }
                            searchPlayListFlag = true;
                        }
                    });
                }
            }

            else if (rooter.currentIndex === 4) {
                if (artistFlag) {
                    artistPageIndex++;
                    artistFlag = false;
                    $.ajax({
                        type: "GET",
                        url: "/kuwo/searchArtist/" + window.encodeURIComponent(keyword) + "/" + artistPageIndex + "/30",
                        dataType: "json",
                        success: function (data) {
                            let artistList = data.data.artistList;
                            for (let i = 0; i < artistList.length; i++) {
                                rooter.artistList.push(artistList[i]);
                            }
                            artistFlag = true;
                        }
                    });
                }
            }
        }
    });

});