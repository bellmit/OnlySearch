$(function () {
    let pageIndex = 1;
    let flag = true;
    let rooter = new Vue({
        el: "#rooter",
        data: function () {
            return {
                tv: null,
                tvChannelId: null,
                tvType: null,
                tvTime: null,
                tvArea: null,
                tvVideoType: null,
                tvPageIndex: null,
                tvChannelIdCurrentIndex: null,
                tvTypeCurrentIndex: null,
                tvTimeCurrentIndex: null,
                tvAreaCurrentIndex: null,
                tvVideoTypeCurrentIndex: null,
                tvPageIndexCurrentIndex: null,
                movie: null,
                movieChannelId: null,
                movieType: null,
                movieTime: null,
                movieArea: null,
                movieVideoType: null,
                moviePageIndex: null,
                movieChannelIdCurrentIndex: null,
                movieTypeCurrentIndex: null,
                movieTimeCurrentIndex: null,
                movieAreaCurrentIndex: null,
                movieVideoTypeCurrentIndex: null,
                moviePageIndexCurrentIndex: null,
                variety: null,
                varietyChannelId: null,
                varietyType: null,
                varietyTime: null,
                varietyArea: null,
                varietyVideoType: null,
                varietyPageIndex: null,
                varietyChannelIdCurrentIndex: null,
                varietyTypeCurrentIndex: null,
                varietyTimeCurrentIndex: null,
                varietyAreaCurrentIndex: null,
                varietyVideoTypeCurrentIndex: null,
                varietyPageIndexCurrentIndex: null,
                anime: null,
                animeChannelId: null,
                animeType: null,
                animeTime: null,
                animeArea: null,
                animeVideoType: null,
                animePageIndex: null,
                animeChannelIdCurrentIndex: null,
                animeTypeCurrentIndex: null,
                animeTimeCurrentIndex: null,
                animeAreaCurrentIndex: null,
                animeVideoTypeCurrentIndex: null,
                animePageIndexCurrentIndex: null,
                child: null,
                childChannelId: null,
                childType: null,
                childTime: null,
                childArea: null,
                childVideoType: null,
                childPageIndex: null,
                childChannelIdCurrentIndex: null,
                childTypeCurrentIndex: null,
                childTimeCurrentIndex: null,
                childAreaCurrentIndex: null,
                childVideoTypeCurrentIndex: null,
                childPageIndexCurrentIndex: null,
                music: null,
                musicChannelId: null,
                musicType: null,
                musicTime: null,
                musicArea: null,
                musicVideoType: null,
                musicPageIndex: null,
                musicStamp: null,
                musicLanguage: null,
                musicChannelIdCurrentIndex: null,
                musicTypeCurrentIndex: null,
                musicTimeCurrentIndex: null,
                musicAreaCurrentIndex: null,
                musicVideoTypeCurrentIndex: null,
                musicPageIndexCurrentIndex: null,
                musicStampCurrentIndex: null,
                musicLanguageCurrentIndex: null,
                edu: null,
                eduChannelId: null,
                eduType: null,
                eduTime: null,
                eduArea: null,
                eduVideoType: null,
                eduPageIndex: null,
                eduChannelIdCurrentIndex: null,
                eduTypeCurrentIndex: null,
                eduTimeCurrentIndex: null,
                eduAreaCurrentIndex: null,
                eduVideoTypeCurrentIndex: null,
                eduPageIndexCurrentIndex: null,
                documentary: null,
                documentaryChannelId: null,
                documentaryType: null,
                documentaryTime: null,
                documentaryArea: null,
                documentaryVideoType: null,
                documentaryPageIndex: null,
                documentaryChannelIdCurrentIndex: null,
                documentaryTypeCurrentIndex: null,
                documentaryTimeCurrentIndex: null,
                documentaryAreaCurrentIndex: null,
                documentaryVideoTypeCurrentIndex: null,
                documentaryPageIndexCurrentIndex: null,
                sport: null,
                sportChannelId: null,
                sportType: null,
                sportTime: null,
                sportArea: null,
                sportVideoType: null,
                sportPageIndex: null,
                sportChannelIdCurrentIndex: null,
                sportTypeCurrentIndex: null,
                sportTimeCurrentIndex: null,
                sportAreaCurrentIndex: null,
                sportVideoTypeCurrentIndex: null,
                sportPageIndexCurrentIndex: null,
                culture: null,
                cultureChannelId: null,
                cultureType: null,
                cultureTime: null,
                cultureArea: null,
                cultureVideoType: null,
                culturePageIndex: null,
                cultureChannelIdCurrentIndex: null,
                cultureTypeCurrentIndex: null,
                cultureTimeCurrentIndex: null,
                cultureAreaCurrentIndex: null,
                cultureVideoTypeCurrentIndex: null,
                culturePageIndexCurrentIndex: null,
                entertainment: null,
                entertainmentChannelId: null,
                entertainmentType: null,
                entertainmentTime: null,
                entertainmentArea: null,
                entertainmentVideoType: null,
                entertainmentPageIndex: null,
                entertainmentChannelIdCurrentIndex: null,
                entertainmentTypeCurrentIndex: null,
                entertainmentTimeCurrentIndex: null,
                entertainmentAreaCurrentIndex: null,
                entertainmentVideoTypeCurrentIndex: null,
                entertainmentPageIndexCurrentIndex: null,
                game: null,
                gameChannelId: null,
                gameType: null,
                gameTime: null,
                gameArea: null,
                gameVideoType: null,
                gamePageIndex: null,
                gameChannelIdCurrentIndex: null,
                gameTypeCurrentIndex: null,
                gameTimeCurrentIndex: null,
                gameAreaCurrentIndex: null,
                gameVideoTypeCurrentIndex: null,
                gamePageIndexCurrentIndex: null,
                information: null,
                informationChannelId: null,
                informationType: null,
                informationTime: null,
                informationArea: null,
                informationVideoType: null,
                informationPageIndex: null,
                informationChannelIdCurrentIndex: null,
                informationTypeCurrentIndex: null,
                informationTimeCurrentIndex: null,
                informationAreaCurrentIndex: null,
                informationVideoTypeCurrentIndex: null,
                informationPageIndexCurrentIndex: null,
                funny: null,
                funnyChannelId: null,
                funnyType: null,
                funnyTime: null,
                funnyArea: null,
                funnyVideoType: null,
                funnyPageIndex: null,
                funnyChannelIdCurrentIndex: null,
                funnyTypeCurrentIndex: null,
                funnyTimeCurrentIndex: null,
                funnyAreaCurrentIndex: null,
                funnyVideoTypeCurrentIndex: null,
                funnyPageIndexCurrentIndex: null,
                life: null,
                lifeChannelId: null,
                lifeType: null,
                lifeTime: null,
                lifeArea: null,
                lifeVideoType: null,
                lifePageIndex: null,
                lifeChannelIdCurrentIndex: null,
                lifeTypeCurrentIndex: null,
                lifeTimeCurrentIndex: null,
                lifeAreaCurrentIndex: null,
                lifeVideoTypeCurrentIndex: null,
                lifePageIndexCurrentIndex: null,
                videoList: [],
                currentChannel: "tv",
                channelIdSelected: "97",
                typeSelected: "",
                timeSelected: "",
                areaSelected: "",
                videoTypeSelected: "show",
                pageIndexSelected: "1",
                stampSelected: "",
                languageSelected: ""
            };
        },
        created: function () {
            readTvConfig();
        }
    });

    rooter.$watch("tv", function () {
        rooter.currentChannel = "tv";
        commonAjax();
        let tvTypeLis = $("ul.tv-type li");
        tvTypeLis.unbind().click(function () {
            rooter.typeSelected = $(this).find("span").attr("type");
            tvTypeLis.removeClass("focus");
            $(this).addClass("focus");
            rooter.pageIndexSelected = "1";
            pageIndex = 1;
            commonAjax();
        });

        let tvTimeLis = $("ul.tv-time li");
        tvTimeLis.unbind().click(function () {
            rooter.timeSelected = $(this).find("span").attr("time");
            tvTimeLis.removeClass("focus");
            $(this).addClass("focus");
            rooter.pageIndexSelected = "1";
            pageIndex = 1;
            commonAjax();
        });

        let tvAreaLis = $("ul.tv-area li");
        tvAreaLis.unbind().click(function () {
            rooter.areaSelected = $(this).find("span").attr("area");
            tvAreaLis.removeClass("focus");
            $(this).addClass("focus");
            rooter.pageIndexSelected = "1";
            pageIndex = 1;
            commonAjax();
        });
    });

    rooter.$watch("movie", function () {
        rooter.currentChannel = "movie";
        commonAjax();
        let movieTypeLis = $("ul.movie-type li");
        movieTypeLis.unbind().click(function () {
            rooter.typeSelected = $(this).find("span").attr("type");
            movieTypeLis.removeClass("focus");
            $(this).addClass("focus");
            rooter.pageIndexSelected = "1";
            pageIndex = 1;
            commonAjax();
        });

        let movieTimeLis = $("ul.movie-time li");
        movieTimeLis.unbind().click(function () {
            rooter.timeSelected = $(this).find("span").attr("time");
            movieTimeLis.removeClass("focus");
            $(this).addClass("focus");
            rooter.pageIndexSelected = "1";
            pageIndex = 1;
            commonAjax();
        });

        let movieAreaLis = $("ul.movie-area li");
        movieAreaLis.unbind().click(function () {
            rooter.areaSelected = $(this).find("span").attr("area");
            movieAreaLis.removeClass("focus");
            $(this).addClass("focus");
            rooter.pageIndexSelected = "1";
            pageIndex = 1;
            commonAjax();
        });
    });

    rooter.$watch("variety", function () {
        rooter.currentChannel = "variety";
        commonAjax();
        let varietyTypeLis = $("ul.variety-type li");
        varietyTypeLis.unbind().click(function () {
            rooter.typeSelected = $(this).find("span").attr("type");
            varietyTypeLis.removeClass("focus");
            $(this).addClass("focus");
            rooter.pageIndexSelected = "1";
            pageIndex = 1;
            commonAjax();
        });

        let varietyTimeLis = $("ul.variety-time li");
        varietyTimeLis.unbind().click(function () {
            rooter.timeSelected = $(this).find("span").attr("time");
            varietyTimeLis.removeClass("focus");
            $(this).addClass("focus");
            rooter.pageIndexSelected = "1";
            pageIndex = 1;
            commonAjax();
        });

        let varietyAreaLis = $("ul.variety-area li");
        varietyAreaLis.unbind().click(function () {
            rooter.areaSelected = $(this).find("span").attr("area");
            varietyAreaLis.removeClass("focus");
            $(this).addClass("focus");
            rooter.pageIndexSelected = "1";
            pageIndex = 1;
            commonAjax();
        });
    });

    rooter.$watch("anime", function () {
        rooter.currentChannel = "anime";
        commonAjax();
        let animeTypeLis = $("ul.anime-type li");
        animeTypeLis.unbind().click(function () {
            rooter.typeSelected = $(this).find("span").attr("type");
            animeTypeLis.removeClass("focus");
            $(this).addClass("focus");
            rooter.pageIndexSelected = "1";
            pageIndex = 1;
            commonAjax();
        });

        let animeTimeLis = $("ul.anime-time li");
        animeTimeLis.unbind().click(function () {
            rooter.timeSelected = $(this).find("span").attr("time");
            animeTimeLis.removeClass("focus");
            $(this).addClass("focus");
            rooter.pageIndexSelected = "1";
            pageIndex = 1;
            commonAjax();
        });

        let animeAreaLis = $("ul.anime-area li");
        animeAreaLis.unbind().click(function () {
            rooter.areaSelected = $(this).find("span").attr("area");
            animeAreaLis.removeClass("focus");
            $(this).addClass("focus");
            rooter.pageIndexSelected = "1";
            pageIndex = 1;
            commonAjax();
        });
    });

    rooter.$watch("child", function () {
        rooter.currentChannel = "child";
        commonAjax();
        let childTypeLis = $("ul.child-type li");
        childTypeLis.unbind().click(function () {
            rooter.typeSelected = $(this).find("span").attr("type");
            childTypeLis.removeClass("focus");
            $(this).addClass("focus");
            rooter.pageIndexSelected = "1";
            pageIndex = 1;
            commonAjax();
        });

        let childTimeLis = $("ul.child-time li");
        childTimeLis.unbind().click(function () {
            rooter.timeSelected = $(this).find("span").attr("time");
            childTimeLis.removeClass("focus");
            $(this).addClass("focus");
            rooter.pageIndexSelected = "1";
            pageIndex = 1;
            commonAjax();
        });

        let childAreaLis = $("ul.child-area li");
        childAreaLis.unbind().click(function () {
            rooter.areaSelected = $(this).find("span").attr("area");
            childAreaLis.removeClass("focus");
            $(this).addClass("focus");
            rooter.pageIndexSelected = "1";
            pageIndex = 1;
            commonAjax();
        });
    });

    rooter.$watch("music", function () {
        rooter.currentChannel = "music";
        commonAjax();
        let musicTypeLis = $("ul.music-type li");
        musicTypeLis.unbind().click(function () {
            rooter.typeSelected = $(this).find("span").attr("type");
            musicTypeLis.removeClass("focus");
            $(this).addClass("focus");
            rooter.pageIndexSelected = "1";
            pageIndex = 1;
            commonAjax();
        });

        let musicTimeLis = $("ul.music-time li");
        musicTimeLis.unbind().click(function () {
            rooter.timeSelected = $(this).find("span").attr("time");
            musicTimeLis.removeClass("focus");
            $(this).addClass("focus");
            rooter.pageIndexSelected = "1";
            pageIndex = 1;
            commonAjax();
        });

        let musicAreaLis = $("ul.music-area li");
        musicAreaLis.unbind().click(function () {
            rooter.areaSelected = $(this).find("span").attr("area");
            musicAreaLis.removeClass("focus");
            $(this).addClass("focus");
            rooter.pageIndexSelected = "1";
            pageIndex = 1;
            commonAjax();
        });

        let musicStampLis = $("ul.music-stamp li");
        musicStampLis.unbind().click(function () {
            rooter.stampSelected = $(this).find("span").attr("stamp");
            musicStampLis.removeClass("focus");
            $(this).addClass("focus");
            rooter.pageIndexSelected = "1";
            pageIndex = 1;
            commonAjax();
        });

        let musicLanguageLis = $("ul.music-language li");
        musicLanguageLis.unbind().click(function () {
            rooter.languageSelected = $(this).find("span").attr("language");
            musicLanguageLis.removeClass("focus");
            $(this).addClass("focus");
            rooter.pageIndexSelected = "1";
            pageIndex = 1;
            commonAjax();
        });
    });

    rooter.$watch("edu", function () {
        rooter.currentChannel = "edu";
        commonAjax();
        let eduTypeLis = $("ul.edu-type li");
        eduTypeLis.unbind().click(function () {
            rooter.typeSelected = $(this).find("span").attr("type");
            eduTypeLis.removeClass("focus");
            $(this).addClass("focus");
            rooter.pageIndexSelected = "1";
            pageIndex = 1;
            commonAjax();
        });

        let eduTimeLis = $("ul.edu-time li");
        eduTimeLis.unbind().click(function () {
            rooter.timeSelected = $(this).find("span").attr("time");
            eduTimeLis.removeClass("focus");
            $(this).addClass("focus");
            rooter.pageIndexSelected = "1";
            pageIndex = 1;
            commonAjax();
        });

        let eduAreaLis = $("ul.edu-area li");
        eduAreaLis.unbind().click(function () {
            rooter.areaSelected = $(this).find("span").attr("area");
            eduAreaLis.removeClass("focus");
            $(this).addClass("focus");
            rooter.pageIndexSelected = "1";
            pageIndex = 1;
            commonAjax();
        });
    });

    rooter.$watch("documentary", function () {
        rooter.currentChannel = "documentary";
        commonAjax();
        let documentaryTypeLis = $("ul.documentary-type li");
        documentaryTypeLis.unbind().click(function () {
            rooter.typeSelected = $(this).find("span").attr("type");
            documentaryTypeLis.removeClass("focus");
            $(this).addClass("focus");
            rooter.pageIndexSelected = "1";
            pageIndex = 1;
            commonAjax();
        });

        let documentaryTimeLis = $("ul.documentary-time li");
        documentaryTimeLis.unbind().click(function () {
            rooter.timeSelected = $(this).find("span").attr("time");
            documentaryTimeLis.removeClass("focus");
            $(this).addClass("focus");
            rooter.pageIndexSelected = "1";
            pageIndex = 1;
            commonAjax();
        });

        let documentaryAreaLis = $("ul.documentary-area li");
        documentaryAreaLis.unbind().click(function () {
            rooter.areaSelected = $(this).find("span").attr("area");
            documentaryAreaLis.removeClass("focus");
            $(this).addClass("focus");
            rooter.pageIndexSelected = "1";
            pageIndex = 1;
            commonAjax();
        });
    });

    rooter.$watch("sport", function () {
        rooter.currentChannel = "sport";
        commonAjax();
        let sportTypeLis = $("ul.sport-type li");
        sportTypeLis.unbind().click(function () {
            rooter.typeSelected = $(this).find("span").attr("type");
            sportTypeLis.removeClass("focus");
            $(this).addClass("focus");
            rooter.pageIndexSelected = "1";
            pageIndex = 1;
            commonAjax();
        });

        let sportTimeLis = $("ul.sport-time li");
        sportTimeLis.unbind().click(function () {
            rooter.timeSelected = $(this).find("span").attr("time");
            sportTimeLis.removeClass("focus");
            $(this).addClass("focus");
            rooter.pageIndexSelected = "1";
            pageIndex = 1;
            commonAjax();
        });

        let sportAreaLis = $("ul.sport-area li");
        sportAreaLis.unbind().click(function () {
            rooter.areaSelected = $(this).find("span").attr("area");
            sportAreaLis.removeClass("focus");
            $(this).addClass("focus");
            rooter.pageIndexSelected = "1";
            pageIndex = 1;
            commonAjax();
        });
    });

    rooter.$watch("culture", function () {
        rooter.currentChannel = "culture";
        commonAjax();
        let cultureTimeLis = $("ul.culture-time li");
        cultureTimeLis.unbind().click(function () {
            rooter.timeSelected = $(this).find("span").attr("time");
            cultureTimeLis.removeClass("focus");
            $(this).addClass("focus");
            rooter.pageIndexSelected = "1";
            pageIndex = 1;
            commonAjax();
        });
    });

    rooter.$watch("entertainment", function () {
        rooter.currentChannel = "entertainment";
        commonAjax();
        let entertainmentTypeLis = $("ul.entertainment-type li");
        entertainmentTypeLis.unbind().click(function () {
            rooter.typeSelected = $(this).find("span").attr("type");
            entertainmentTypeLis.removeClass("focus");
            $(this).addClass("focus");
            rooter.pageIndexSelected = "1";
            pageIndex = 1;
            commonAjax();
        });

        let entertainmentTimeLis = $("ul.entertainment-time li");
        entertainmentTimeLis.unbind().click(function () {
            rooter.timeSelected = $(this).find("span").attr("time");
            entertainmentTimeLis.removeClass("focus");
            $(this).addClass("focus");
            rooter.pageIndexSelected = "1";
            pageIndex = 1;
            commonAjax();
        });
    });

    rooter.$watch("game", function () {
        rooter.currentChannel = "game";
        commonAjax();

        let gameTimeLis = $("ul.game-time li");
        gameTimeLis.unbind().click(function () {
            rooter.timeSelected = $(this).find("span").attr("time");
            gameTimeLis.removeClass("focus");
            $(this).addClass("focus");
            rooter.pageIndexSelected = "1";
            pageIndex = 1;
            commonAjax();
        });
    });

    rooter.$watch("information", function () {
        rooter.currentChannel = "information";
        commonAjax();
        let informationTypeLis = $("ul.information-type li");
        informationTypeLis.unbind().click(function () {
            rooter.typeSelected = $(this).find("span").attr("type");
            informationTypeLis.removeClass("focus");
            $(this).addClass("focus");
            rooter.pageIndexSelected = "1";
            pageIndex = 1;
            commonAjax();
        });

        let informationTimeLis = $("ul.information-time li");
        informationTimeLis.unbind().click(function () {
            rooter.timeSelected = $(this).find("span").attr("time");
            informationTimeLis.removeClass("focus");
            $(this).addClass("focus");
            rooter.pageIndexSelected = "1";
            pageIndex = 1;
            commonAjax();
        });
    });

    rooter.$watch("funny", function () {
        rooter.currentChannel = "funny";
        commonAjax();
        let funnyTypeLis = $("ul.funny-type li");
        funnyTypeLis.unbind().click(function () {
            rooter.typeSelected = $(this).find("span").attr("type");
            funnyTypeLis.removeClass("focus");
            $(this).addClass("focus");
            rooter.pageIndexSelected = "1";
            pageIndex = 1;
            commonAjax();
        });
    });

    rooter.$watch("life", function () {
        rooter.currentChannel = "life";
        commonAjax();
        let lifeTypeLis = $("ul.life-type li");
        lifeTypeLis.unbind().click(function () {
            rooter.typeSelected = $(this).find("span").attr("type");
            lifeTypeLis.removeClass("focus");
            $(this).addClass("focus");
            rooter.pageIndexSelected = "1";
            pageIndex = 1;
            commonAjax();
        });
    });

    $(window).scroll(function () {
        if ($(document).scrollTop() >= ($(document).height() - $(window).height()) * 0.95) {
            if (flag){
                flag = false;
                pageIndex++;
                $.ajax({
                    type: "GET",
                    url: "/youku/categoryList?classify=" + rooter.channelIdSelected + "&type=" + rooter.typeSelected + "&time=" + rooter.timeSelected + "&area=" + rooter.areaSelected + "&videoType=" + rooter.videoTypeSelected + "&pageIndex=" + pageIndex,
                    dataType: "json",
                    success: function (data) {
                        let videoList = data.data;
                        for (let i=0;i<videoList.length;i++){
                            rooter.videoList.push(videoList[i]);
                        }
                        flag = true;
                    }
                });
            }
        }
    });

    /**
     * 处理channel的切换
     */
    let channelLis = $("#rooter ul.main li");
    channelLis.unbind().click(function () {
        let channelString = $(this).text();
        switch (channelString) {
            case "电视剧":
                rooter.currentChannel = "tv";
                rooter.channelIdSelected = "97";
                readTvConfig();
                break;
            case "电影":
                rooter.currentChannel = "movie";
                rooter.channelIdSelected = "96";
                readMovieConfig();
                break;
            case "综艺":
                rooter.currentChannel = "variety";
                rooter.channelIdSelected = "85";
                readVarietyConfig();
                break;
            case "动漫":
                rooter.currentChannel = "anime";
                rooter.channelIdSelected = "100";
                readAnimeConfig();
                break;
            case "少儿":
                rooter.currentChannel = "child";
                rooter.channelIdSelected = "177";
                readChildConfig();
                break;
            case "音乐":
                rooter.currentChannel = "music";
                rooter.channelIdSelected = "95";
                readMusicConfig();
                break;
            case "教育":
                rooter.currentChannel = "edu";
                rooter.channelIdSelected = "87";
                readEduConfig();
                break;
            case "纪录片":
                rooter.currentChannel = "documentary";
                rooter.channelIdSelected = "84";
                readDocumentaryConfig();
                break;
            case "体育":
                rooter.currentChannel = "sport";
                rooter.channelIdSelected = "98";
                readSportConfig();
                break;
            case "文化":
                rooter.currentChannel = "culture";
                rooter.channelIdSelected = "178";
                readCultureConfig();
                break;
            case "娱乐":
                rooter.currentChannel = "entertainment";
                rooter.channelIdSelected = "86";
                readEntertainmentConfig();
                break;
            case "游戏":
                rooter.currentChannel = "game";
                rooter.channelIdSelected = "99";
                readGameConfig();
                break;
            case "资讯":
                rooter.currentChannel = "information";
                rooter.channelIdSelected = "91";
                readInformationConfig();
                break;
            case "搞笑":
                rooter.currentChannel = "funny";
                rooter.channelIdSelected = "94";
                readFunnyConfig();
                break;
            case "生活":
                rooter.currentChannel = "life";
                rooter.channelIdSelected = "103";
                readLifeConfig();
                break;
            default:
                break;
        }
        rooter.videoList = [];
        pageIndex = 1;
        commonAjax();
        channelLis.removeClass("focus");
        $(this).addClass("focus");
    });


    function commonAjax() {
        $.ajax({
            type: "GET",
            url: "/youku/categoryList?classify=" + rooter.channelIdSelected + "&type=" + rooter.typeSelected + "&time=" + rooter.timeSelected + "&area=" + rooter.areaSelected + "&videoType=" + rooter.videoTypeSelected + "&pageIndex=" + rooter.pageIndexSelected + "&stamp=" + rooter.stampSelected + "&language=" + rooter.languageSelected,
            dataType: "json",
            success: function (data) {
                rooter.videoList = data.data;
            }
        });
    }

    function readTvConfig() {
        $.ajax({
            type: "GET",
            url: "/htmls/youku/tv-config.json",
            dataType: "json",
            success: function (data) {
                //处理电视剧
                rooter.tv = data["电视剧"];
                rooter.tvChannelId = "97";
                rooter.tvType = rooter.tv.type;
                rooter.tvTime = rooter.tv.time;
                rooter.tvArea = rooter.tv.area;
                rooter.tvVideoType = "show";
                rooter.tvPageIndex = 1;
                rooter.tvChannelIdCurrentIndex = 1;
                rooter.tvTypeCurrentIndex = 1;
                rooter.tvTimeCurrentIndex = 1;
                rooter.tvAreaCurrentIndex = 1;
                rooter.tvVideoTypeCurrentIndex = 1;
                rooter.tvPageIndexCurrentIndex = 1;
                //重置数据
                rooter.typeSelected = "";
                rooter.timeSelected = "";
                rooter.areaSelected = "";
                rooter.videoTypeSelected = "show";
                rooter.pageIndexSelected = "1";
            }
        });
    }

    function readMovieConfig() {
        $.ajax({
            type: "GET",
            url: "/htmls/youku/movie-config.json",
            dataType: "json",
            success: function (data) {
                //处理电视剧
                rooter.movie = data["电影"];
                rooter.movieChannelId = "96";
                rooter.movieType = rooter.movie.type;
                rooter.movieTime = rooter.movie.time;
                rooter.movieArea = rooter.movie.area;
                rooter.movieVideoType = "show";
                rooter.moviePageIndex = 1;
                rooter.movieChannelIdCurrentIndex = 1;
                rooter.movieTypeCurrentIndex = 1;
                rooter.movieTimeCurrentIndex = 1;
                rooter.movieAreaCurrentIndex = 1;
                rooter.movieVideoTypeCurrentIndex = 1;
                rooter.moviePageIndexCurrentIndex = 1;
                //重置数据
                rooter.typeSelected = "";
                rooter.timeSelected = "";
                rooter.areaSelected = "";
                rooter.videoTypeSelected = "show";
                rooter.pageIndexSelected = "1";
            }
        });
    }

    function readVarietyConfig() {
        $.ajax({
            type: "GET",
            url: "/htmls/youku/variety-config.json",
            dataType: "json",
            success: function (data) {
                //处理电视剧
                rooter.variety = data["综艺"];
                rooter.varietyChannelId = "85";
                rooter.varietyType = rooter.variety.type;
                rooter.varietyTime = rooter.variety.time;
                rooter.varietyArea = rooter.variety.area;
                rooter.varietyVideoType = "show";
                rooter.varietyPageIndex = 1;
                rooter.varietyChannelIdCurrentIndex = 1;
                rooter.varietyTypeCurrentIndex = 1;
                rooter.varietyTimeCurrentIndex = 1;
                rooter.varietyAreaCurrentIndex = 1;
                rooter.varietyVideoTypeCurrentIndex = 1;
                rooter.varietyPageIndexCurrentIndex = 1;
                //重置数据
                rooter.typeSelected = "";
                rooter.timeSelected = "";
                rooter.areaSelected = "";
                rooter.videoTypeSelected = "show";
                rooter.pageIndexSelected = "1";
            }
        });
    }

    function readAnimeConfig() {
        $.ajax({
            type: "GET",
            url: "/htmls/youku/anime-config.json",
            dataType: "json",
            success: function (data) {
                //处理电视剧
                rooter.anime = data["动漫"];
                rooter.animeChannelId = "100";
                rooter.animeType = rooter.anime.type;
                rooter.animeTime = rooter.anime.time;
                rooter.animeArea = rooter.anime.area;
                rooter.animeVideoType = "show";
                rooter.animePageIndex = 1;
                rooter.animeChannelIdCurrentIndex = 1;
                rooter.animeTypeCurrentIndex = 1;
                rooter.animeTimeCurrentIndex = 1;
                rooter.animeAreaCurrentIndex = 1;
                rooter.animeVideoTypeCurrentIndex = 1;
                rooter.animePageIndexCurrentIndex = 1;
                //重置数据
                rooter.typeSelected = "";
                rooter.timeSelected = "";
                rooter.areaSelected = "";
                rooter.videoTypeSelected = "show";
                rooter.pageIndexSelected = "1";
            }
        });
    }

    function readChildConfig() {
        $.ajax({
            type: "GET",
            url: "/htmls/youku/child-config.json",
            dataType: "json",
            success: function (data) {
                //处理电视剧
                rooter.child = data["少儿"];
                rooter.childChannelId = "177";
                rooter.childType = rooter.child.type;
                rooter.childTime = rooter.child.time;
                rooter.childArea = rooter.child.area;
                rooter.childVideoType = "show";
                rooter.childPageIndex = 1;
                rooter.childChannelIdCurrentIndex = 1;
                rooter.childTypeCurrentIndex = 1;
                rooter.childTimeCurrentIndex = 1;
                rooter.childAreaCurrentIndex = 1;
                rooter.childVideoTypeCurrentIndex = 1;
                rooter.childPageIndexCurrentIndex = 1;
                //重置数据
                rooter.typeSelected = "";
                rooter.timeSelected = "";
                rooter.areaSelected = "";
                rooter.videoTypeSelected = "show";
                rooter.pageIndexSelected = "1";
            }
        });
    }

    function readMusicConfig() {
        $.ajax({
            type: "GET",
            url: "/htmls/youku/music-config.json",
            dataType: "json",
            success: function (data) {
                //处理电视剧
                rooter.music = data["音乐"];
                rooter.musicChannelId = "95";
                rooter.musicType = rooter.music.type;
                rooter.musicTime = rooter.music.time;
                rooter.musicArea = rooter.music.area;
                rooter.musicStamp = rooter.music.stamp;
                rooter.musicLanguage = rooter.music.language;
                rooter.musicVideoType = "show";
                rooter.musicPageIndex = 1;
                rooter.musicChannelIdCurrentIndex = 1;
                rooter.musicTypeCurrentIndex = 1;
                rooter.musicTimeCurrentIndex = 1;
                rooter.musicAreaCurrentIndex = 1;
                rooter.musicVideoTypeCurrentIndex = 1;
                rooter.musicPageIndexCurrentIndex = 1;
                rooter.musicStampCurrentIndex = 1;
                rooter.musicLanguageCurrentIndex = 1;
                //重置数据
                rooter.typeSelected = "";
                rooter.timeSelected = "";
                rooter.areaSelected = "";
                rooter.videoTypeSelected = "show";
                rooter.pageIndexSelected = "1";
            }
        });
    }


    function readEduConfig() {
        $.ajax({
            type: "GET",
            url: "/htmls/youku/edu-config.json",
            dataType: "json",
            success: function (data) {
                //处理电视剧
                rooter.edu = data["教育"];
                rooter.eduChannelId = "87";
                rooter.eduType = rooter.edu.type;
                rooter.eduTime = rooter.edu.time;
                rooter.eduArea = rooter.edu.area;
                rooter.eduVideoType = "show";
                rooter.eduPageIndex = 1;
                rooter.eduChannelIdCurrentIndex = 1;
                rooter.eduTypeCurrentIndex = 1;
                rooter.eduTimeCurrentIndex = 1;
                rooter.eduAreaCurrentIndex = 1;
                rooter.eduVideoTypeCurrentIndex = 1;
                rooter.eduPageIndexCurrentIndex = 1;
                //重置数据
                rooter.typeSelected = "";
                rooter.timeSelected = "";
                rooter.areaSelected = "";
                rooter.videoTypeSelected = "show";
                rooter.pageIndexSelected = "1";
            }
        });
    }

    function readDocumentaryConfig() {
        $.ajax({
            type: "GET",
            url: "/htmls/youku/documentary-config.json",
            dataType: "json",
            success: function (data) {
                //处理电视剧
                rooter.documentary = data["纪录片"];
                rooter.documentaryChannelId = "84";
                rooter.documentaryType = rooter.documentary.type;
                rooter.documentaryTime = rooter.documentary.time;
                rooter.documentaryArea = rooter.documentary.area;
                rooter.documentaryVideoType = "show";
                rooter.documentaryPageIndex = 1;
                rooter.documentaryChannelIdCurrentIndex = 1;
                rooter.documentaryTypeCurrentIndex = 1;
                rooter.documentaryTimeCurrentIndex = 1;
                rooter.documentaryAreaCurrentIndex = 1;
                rooter.documentaryVideoTypeCurrentIndex = 1;
                rooter.documentaryPageIndexCurrentIndex = 1;
                //重置数据
                rooter.typeSelected = "";
                rooter.timeSelected = "";
                rooter.areaSelected = "";
                rooter.videoTypeSelected = "show";
                rooter.pageIndexSelected = "1";
            }
        });
    }

    function readSportConfig() {
        $.ajax({
            type: "GET",
            url: "/htmls/youku/sport-config.json",
            dataType: "json",
            success: function (data) {
                //处理电视剧
                rooter.sport = data["体育"];
                rooter.sportChannelId = "98";
                rooter.sportType = rooter.sport.type;
                rooter.sportTime = rooter.sport.time;
                rooter.sportArea = rooter.sport.area;
                rooter.sportVideoType = "show";
                rooter.sportPageIndex = 1;
                rooter.sportChannelIdCurrentIndex = 1;
                rooter.sportTypeCurrentIndex = 1;
                rooter.sportTimeCurrentIndex = 1;
                rooter.sportAreaCurrentIndex = 1;
                rooter.sportVideoTypeCurrentIndex = 1;
                rooter.sportPageIndexCurrentIndex = 1;
                //重置数据
                rooter.typeSelected = "";
                rooter.timeSelected = "";
                rooter.areaSelected = "";
                rooter.videoTypeSelected = "show";
                rooter.pageIndexSelected = "1";
            }
        });
    }


    function readCultureConfig() {
        $.ajax({
            type: "GET",
            url: "/htmls/youku/culture-config.json",
            dataType: "json",
            success: function (data) {
                //处理电视剧
                rooter.culture = data["文化"];
                rooter.cultureChannelId = "178";
                rooter.cultureType = rooter.culture.type;
                rooter.cultureTime = rooter.culture.time;
                rooter.cultureArea = rooter.culture.area;
                rooter.cultureVideoType = "show";
                rooter.culturePageIndex = 1;
                rooter.cultureChannelIdCurrentIndex = 1;
                rooter.cultureTypeCurrentIndex = 1;
                rooter.cultureTimeCurrentIndex = 1;
                rooter.cultureAreaCurrentIndex = 1;
                rooter.cultureVideoTypeCurrentIndex = 1;
                rooter.culturePageIndexCurrentIndex = 1;
                //重置数据
                rooter.typeSelected = "";
                rooter.timeSelected = "";
                rooter.areaSelected = "";
                rooter.videoTypeSelected = "show";
                rooter.pageIndexSelected = "1";
            }
        });
    }

    function readEntertainmentConfig() {
        $.ajax({
            type: "GET",
            url: "/htmls/youku/entertainment-config.json",
            dataType: "json",
            success: function (data) {
                //处理电视剧
                rooter.entertainment = data["娱乐"];
                rooter.entertainmentChannelId = "86";
                rooter.entertainmentType = rooter.entertainment.type;
                rooter.entertainmentTime = rooter.entertainment.time;
                rooter.entertainmentArea = rooter.entertainment.area;
                rooter.entertainmentVideoType = "show";
                rooter.entertainmentPageIndex = 1;
                rooter.entertainmentChannelIdCurrentIndex = 1;
                rooter.entertainmentTypeCurrentIndex = 1;
                rooter.entertainmentTimeCurrentIndex = 1;
                rooter.entertainmentAreaCurrentIndex = 1;
                rooter.entertainmentVideoTypeCurrentIndex = 1;
                rooter.entertainmentPageIndexCurrentIndex = 1;
                //重置数据
                rooter.typeSelected = "";
                rooter.timeSelected = "";
                rooter.areaSelected = "";
                rooter.videoTypeSelected = "show";
                rooter.pageIndexSelected = "1";
            }
        });
    }

    function readGameConfig() {
        $.ajax({
            type: "GET",
            url: "/htmls/youku/game-config.json",
            dataType: "json",
            success: function (data) {
                //处理电视剧
                rooter.game = data["游戏"];
                rooter.gameChannelId = "99";
                rooter.gameType = rooter.game.type;
                rooter.gameTime = rooter.game.time;
                rooter.gameArea = rooter.game.area;
                rooter.gameVideoType = "show";
                rooter.gamePageIndex = 1;
                rooter.gameChannelIdCurrentIndex = 1;
                rooter.gameTypeCurrentIndex = 1;
                rooter.gameTimeCurrentIndex = 1;
                rooter.gameAreaCurrentIndex = 1;
                rooter.gameVideoTypeCurrentIndex = 1;
                rooter.gamePageIndexCurrentIndex = 1;
                //重置数据
                rooter.typeSelected = "";
                rooter.timeSelected = "";
                rooter.areaSelected = "";
                rooter.videoTypeSelected = "show";
                rooter.pageIndexSelected = "1";
            }
        });
    }

    function readInformationConfig() {
        $.ajax({
            type: "GET",
            url: "/htmls/youku/information-config.json",
            dataType: "json",
            success: function (data) {
                //处理电视剧
                rooter.information = data["资讯"];
                rooter.informationChannelId = "91";
                rooter.informationType = rooter.information.type;
                rooter.informationTime = rooter.information.time;
                rooter.informationArea = rooter.information.area;
                rooter.informationVideoType = "video";
                rooter.informationPageIndex = 1;
                rooter.informationChannelIdCurrentIndex = 1;
                rooter.informationTypeCurrentIndex = 1;
                rooter.informationTimeCurrentIndex = 1;
                rooter.informationAreaCurrentIndex = 1;
                rooter.informationVideoTypeCurrentIndex = 1;
                rooter.informationPageIndexCurrentIndex = 1;
                //重置数据
                rooter.typeSelected = "";
                rooter.timeSelected = "";
                rooter.areaSelected = "";
                rooter.videoTypeSelected = "video";
                rooter.pageIndexSelected = "1";
            }
        });
    }

    function readFunnyConfig() {
        $.ajax({
            type: "GET",
            url: "/htmls/youku/funny-config.json",
            dataType: "json",
            success: function (data) {
                //处理电视剧
                rooter.funny = data["搞笑"];
                rooter.funnyChannelId = "94";
                rooter.funnyType = rooter.funny.type;
                rooter.funnyTime = rooter.funny.time;
                rooter.funnyArea = rooter.funny.area;
                rooter.funnyVideoType = "video";
                rooter.funnyPageIndex = 1;
                rooter.funnyChannelIdCurrentIndex = 1;
                rooter.funnyTypeCurrentIndex = 1;
                rooter.funnyTimeCurrentIndex = 1;
                rooter.funnyAreaCurrentIndex = 1;
                rooter.funnyVideoTypeCurrentIndex = 1;
                rooter.funnyPageIndexCurrentIndex = 1;
                //重置数据
                rooter.typeSelected = "";
                rooter.timeSelected = "";
                rooter.areaSelected = "";
                rooter.videoTypeSelected = "video";
                rooter.pageIndexSelected = "1";
            }
        });
    }

    function readLifeConfig() {
        $.ajax({
            type: "GET",
            url: "/htmls/youku/life-config.json",
            dataType: "json",
            success: function (data) {
                //处理电视剧
                rooter.life = data["生活"];
                rooter.lifeChannelId = "103";
                rooter.lifeType = rooter.life.type;
                rooter.lifeTime = rooter.life.time;
                rooter.lifeArea = rooter.life.area;
                rooter.lifeVideoType = "video";
                rooter.lifePageIndex = 1;
                rooter.lifeChannelIdCurrentIndex = 1;
                rooter.lifeTypeCurrentIndex = 1;
                rooter.lifeTimeCurrentIndex = 1;
                rooter.lifeAreaCurrentIndex = 1;
                rooter.lifeVideoTypeCurrentIndex = 1;
                rooter.lifePageIndexCurrentIndex = 1;
                //重置数据
                rooter.typeSelected = "";
                rooter.timeSelected = "";
                rooter.areaSelected = "";
                rooter.videoTypeSelected = "video";
                rooter.pageIndexSelected = "1";
            }
        });
    }
});