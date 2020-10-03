$(function () {
    let artistId = $("input#artistId").attr("title");
    let songPageIndex = 1;
    let albumPageIndex = 1;
    let mvPageIndex = 1;
    let audio = $("audio");
    let rooter = new Vue({
        el : "#rooter",
        data : function () {
            return {
                singerInfo : {},
                currentPage : 3,
                songs : null,
                albums : null,
                mvs : null
            };
        },
        created : function () {
            $.ajax({
                type: "GET",
                url: "/kuwo/singerDetail/" + artistId,
                dataType: "json",
                success: function (data) {
                    rooter.singerInfo = data.data;
                }
            });

            $.ajax({
                type: "GET",
                url: "/kuwo/artistMusic/" + artistId + "/1/30",
                dataType: "json",
                success: function (data) {
                    rooter.songs = data.data.list;
                }
            });

            $.ajax({
                type: "GET",
                url: "/kuwo/artistAlbum/" + artistId + "/1/30",
                dataType: "json",
                success: function (data) {
                    rooter.albums = data.data.albumList;
                }
            });

            $.ajax({
                type: "GET",
                url: "/kuwo/artistMv/" + artistId + "/1/30",
                dataType: "json",
                success: function (data) {
                    rooter.mvs = data.data.mvlist;
                }
            });
        }
    });

    window.setInterval(function () {
        let playButtons = $("span.playButton");
        if (playButtons !== null){
            playButtons.unbind().click(function () {
                let _this = this;
                playButtons.parent().removeClass("focus");
                $(this).parent().addClass("focus");
                let rid = $(this).attr("rid");
                $.ajax({
                    type: "GET",
                    url: "/kuwo/songInfo/" + rid,
                    dataType: "json",
                    success: function (data) {
                        audio.attr("src", data.url);
                        let title = $("title");
                        title.text( "祥龙检索-酷我音乐--" + $(_this).attr("name"));
                    }
                });
            });
        }
    },2000);

    let tabs = $("ul.tab li");
    tabs.unbind().click(function () {
        tabs.removeClass("focus");
        $(this).addClass("focus");
        rooter.currentPage = window.parseInt($(this).attr("index"));
    });

    $(window).scroll(function() {
        if ($(document).scrollTop() >= ($(document).height() - $(window).height()) * 0.9) {
            if (rooter.currentPage === 0){
                songPageIndex ++;
                $.ajax({
                    type: "GET",
                    url: "/kuwo/artistMusic/" + artistId + "/"+songPageIndex+"/30",
                    dataType: "json",
                    success: function (data) {
                        let songs = data.data.list;
                        for (let i=0;i<songs.length;i++){
                            rooter.songs.push(songs[i]);
                        }
                    }
                });
            }
            else if (rooter.currentPage === 1){
                albumPageIndex ++;
                $.ajax({
                    type: "GET",
                    url: "/kuwo/artistAlbum/" + artistId + "/"+albumPageIndex+"/30",
                    dataType: "json",
                    success: function (data) {
                        let albums = data.data.albumList;
                        for (let i=0;i<albums.length;i++){
                            rooter.albums.push(albums[i]);
                        }
                    }
                });
            }
            else if (rooter.currentPage === 2){
                mvPageIndex ++;
                $.ajax({
                    type: "GET",
                    url: "/kuwo/artistMv/" + artistId + "/"+mvPageIndex+"/30",
                    dataType: "json",
                    success: function (data) {
                        let mvs = data.data.mvlist;
                        for (let i=0;i<mvs.length;i++){
                            rooter.mvs.push(mvs[i]);
                        }
                    }
                });
            }
        }
    });
});