$(function () {
    let keyword = $("input#keyword").attr("title");
    let pageIndex = 0;
    let channelIds = [
        2, 1, 4, 3, 25, 7, 16, 10, 5, 28, 12, 17, 15, 13, 21, 26, 20, 27
    ];
    let rooter = new Vue({
        el: "#rooter",
        data: function () {
            return {
                videoList: null
            };
        },
        created: function () {
            $.ajax({
                type: "GET",
                url: "/iqiyi/searchResult/" + keyword + "/0/100",
                dataType: "json",
                success: function (data) {
                    for (let i=0;i<data.length;i++){
                        let dat = data[i];
                        switch (dat.channelId) {
                            case 2:
                            case 4:
                            case 3:
                            case 13:
                            case 15:
                                dat.href = "/showTv?url=" + dat.href + '&platform=iqiyi&aid=' + dat.aid + '&tvName=' + dat.title;
                                break;
                            case 1:
                            case 25:
                            case 7:
                            case 16:
                            case 10:
                            case 5:
                            case 28:
                            case 12:
                            case 17:
                            case 21:
                            case 26:
                            case 20:
                            case 27:
                                dat.href = "/playMovieWithThreePart?playUrl=" + dat.href + '&name=' + dat.title;
                                break;
                            default:
                                break;
                        }
                    }
                    rooter.videoList = data;
                }
            });
        }
    });

    $(window).scroll(function() {
        if ($(document).scrollTop() >= ($(document).height() - $(window).height()) * 0.95) {
            pageIndex ++;
            $.ajax({
                type: "GET",
                url: "/iqiyi/searchResult/" + keyword + "/" + pageIndex * 100 + "/100",
                dataType: "json",
                success: function (data) {
                    for (let i=0;i<data.length;i++) {
                        let dat = data[i];
                        rooter.videoList.push(dat);
                        switch (dat.channelId) {
                            case 2:
                            case 4:
                            case 3:
                            case 13:
                            case 15:
                                dat.href = "/showTv?url=" + dat.href + '&platform=iqiyi&aid=' + dat.aid + '&tvName=' + dat.title;
                                break;
                            case 1:
                            case 25:
                            case 7:
                            case 16:
                            case 10:
                            case 5:
                            case 28:
                            case 12:
                            case 17:
                            case 21:
                            case 26:
                            case 20:
                            case 27:
                                dat.href = "/playMovieWithThreePart?playUrl=" + dat.href + '&name=' + dat.title;
                                break;
                            default:
                                break;
                        }
                    }
                }
            });
        }
    });

    $("input").unbind().keyup(function (event) {
        if (event.keyCode === 13 && $(this).val() !== ""){
            $("a.searchButton").get(0).click();
        }
    });

    $("a.searchButton").unbind().click(function () {
        $(this).attr("href","/iqiyi/searchPage/"+$("input").val());
    });
});