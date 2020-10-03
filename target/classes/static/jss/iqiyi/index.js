$(function () {

    let pageIndex = 1;

    Vue.use(VueLazyload, {
        error: '/images/bg.gif',
        loading: '/images/bg.gif'
    });

    window.rooter = new Vue({
        el: "#rooter",
        data: function () {
            return {
                data: null,
                tv: null,
                tvMode: null,
                tvArea: null,
                tvType: null,
                tvMarketReleaseDateLevel: null,
                tvModeCurrentIndex: null,
                tvAreaCurrentIndex: null,
                tvTypeCurrentIndex: null,
                tvMarketReleaseDateLevelCurrentIndex: null,
                movie: null,
                movieMode: null,
                movieArea: null,
                movieType: null,
                movieMarketReleaseDateLevel: null,
                movieOther: null,
                movieModeCurrentIndex: null,
                movieAreaCurrentIndex: null,
                movieTypeCurrentIndex: null,
                movieMarketReleaseDateLevelCurrentIndex: null,
                movieOtherIndex: null,
                anime: null,
                animeMode: null,
                animeArea: null,
                animeType: null,
                animeMarketReleaseDateLevel: null,
                animeVersion: null,
                animeOther: null,
                animeModeCurrentIndex: null,
                animeAreaCurrentIndex: null,
                animeTypeCurrentIndex: null,
                animeMarketReleaseDateLevelCurrentIndex: null,
                animeVersionCurrentIndex: null,
                animeOtherCurrentIndex: null,
                documentary: null,
                documentaryMode: null,
                documentaryArea: null,
                documentaryType: null,
                documentaryMarketReleaseDateLevel: null,
                documentaryVersion: null,
                documentaryOther: null,
                documentaryModeCurrentIndex: null,
                documentaryAreaCurrentIndex: null,
                documentaryTypeCurrentIndex: null,
                documentaryMarketReleaseDateLevelCurrentIndex: null,
                documentaryVersionCurrentIndex: null,
                documentaryOtherCurrentIndex: null,
                information: null,
                informationMode: null,
                informationArea: null,
                informationType: null,
                informationMarketReleaseDateLevel: null,
                informationVersion: null,
                informationOther: null,
                informationModeCurrentIndex: null,
                informationAreaCurrentIndex: null,
                informationTypeCurrentIndex: null,
                informationMarketReleaseDateLevelCurrentIndex: null,
                informationVersionCurrentIndex: null,
                informationOtherCurrentIndex: null,
                entertainment: null,
                entertainmentMode: null,
                entertainmentArea: null,
                entertainmentType: null,
                entertainmentMarketReleaseDateLevel: null,
                entertainmentVersion: null,
                entertainmentOther: null,
                entertainmentModeCurrentIndex: null,
                entertainmentAreaCurrentIndex: null,
                entertainmentTypeCurrentIndex: null,
                entertainmentMarketReleaseDateLevelCurrentIndex: null,
                entertainmentVersionCurrentIndex: null,
                entertainmentOtherCurrentIndex: null,
                webmovie: null,
                webmovieMode: null,
                webmovieArea: null,
                webmovieType: null,
                webmovieMarketReleaseDateLevel: null,
                webmovieVersion: null,
                webmovieOther: null,
                webmovieModeCurrentIndex: null,
                webmovieAreaCurrentIndex: null,
                webmovieTypeCurrentIndex: null,
                webmovieMarketReleaseDateLevelCurrentIndex: null,
                webmovieVersionCurrentIndex: null,
                webmovieOtherCurrentIndex: null,
                flower: null,
                flowerMode: null,
                flowerArea: null,
                flowerType: null,
                flowerMarketReleaseDateLevel: null,
                flowerVersion: null,
                flowerOther: null,
                flowerModeCurrentIndex: null,
                flowerAreaCurrentIndex: null,
                flowerTypeCurrentIndex: null,
                flowerMarketReleaseDateLevelCurrentIndex: null,
                flowerVersionCurrentIndex: null,
                flowerOtherCurrentIndex: null,
                music: null,
                musicMode: null,
                musicArea: null,
                musicType: null,
                musicMarketReleaseDateLevel: null,
                musicVersion: null,
                musicOther: null,
                musicModeCurrentIndex: null,
                musicAreaCurrentIndex: null,
                musicTypeCurrentIndex: null,
                musicMarketReleaseDateLevelCurrentIndex: null,
                musicVersionCurrentIndex: null,
                musicOtherCurrentIndex: null,
                military: null,
                militaryMode: null,
                militaryArea: null,
                militaryType: null,
                militaryMarketReleaseDateLevel: null,
                militaryVersion: null,
                militaryOther: null,
                militaryModeCurrentIndex: null,
                militaryAreaCurrentIndex: null,
                militaryTypeCurrentIndex: null,
                militaryMarketReleaseDateLevelCurrentIndex: null,
                militaryVersionCurrentIndex: null,
                militaryOtherCurrentIndex: null,
                education: null,
                educationMode: null,
                educationArea: null,
                educationType: null,
                educationMarketReleaseDateLevel: null,
                educationVersion: null,
                educationOther: null,
                educationModeCurrentIndex: null,
                educationAreaCurrentIndex: null,
                educationTypeCurrentIndex: null,
                educationMarketReleaseDateLevelCurrentIndex: null,
                educationVersionCurrentIndex: null,
                educationOtherCurrentIndex: null,
                physicaleducation: null,
                physicaleducationMode: null,
                physicaleducationArea: null,
                physicaleducationType: null,
                physicaleducationMarketReleaseDateLevel: null,
                physicaleducationVersion: null,
                physicaleducationOther: null,
                physicaleducationModeCurrentIndex: null,
                physicaleducationAreaCurrentIndex: null,
                physicaleducationTypeCurrentIndex: null,
                physicaleducationMarketReleaseDateLevelCurrentIndex: null,
                physicaleducationVersionCurrentIndex: null,
                physicaleducationOtherCurrentIndex: null,
                child: null,
                childMode: null,
                childArea: null,
                childType: null,
                childMarketReleaseDateLevel: null,
                childVersion: null,
                childOther: null,
                childOther2: null,
                childModeCurrentIndex: null,
                childAreaCurrentIndex: null,
                childTypeCurrentIndex: null,
                childMarketReleaseDateLevelCurrentIndex: null,
                childVersionCurrentIndex: null,
                childOtherCurrentIndex: null,
                childOther2CurrentIndex: null,
                fashion: null,
                fashionMode: null,
                fashionArea: null,
                fashionType: null,
                fashionMarketReleaseDateLevel: null,
                fashionVersion: null,
                fashionOther: null,
                fashionOther2: null,
                fashionModeCurrentIndex: null,
                fashionAreaCurrentIndex: null,
                fashionTypeCurrentIndex: null,
                fashionMarketReleaseDateLevelCurrentIndex: null,
                fashionVersionCurrentIndex: null,
                fashionOtherCurrentIndex: null,
                life: null,
                lifeMode: null,
                lifeArea: null,
                lifeType: null,
                lifeMarketReleaseDateLevel: null,
                lifeVersion: null,
                lifeOther: null,
                lifeOther2: null,
                lifeModeCurrentIndex: null,
                lifeAreaCurrentIndex: null,
                lifeTypeCurrentIndex: null,
                lifeMarketReleaseDateLevelCurrentIndex: null,
                lifeVersionCurrentIndex: null,
                lifeOtherCurrentIndex: null,
                car: null,
                carMode: null,
                carArea: null,
                carType: null,
                carMarketReleaseDateLevel: null,
                carVersion: null,
                carOther: null,
                carOther2: null,
                carModeCurrentIndex: null,
                carAreaCurrentIndex: null,
                carTypeCurrentIndex: null,
                carMarketReleaseDateLevelCurrentIndex: null,
                carVersionCurrentIndex: null,
                carOtherCurrentIndex: null,
                ad: null,
                adMode: null,
                adArea: null,
                adType: null,
                adMarketReleaseDateLevel: null,
                adVersion: null,
                adOther: null,
                adOther2: null,
                adModeCurrentIndex: null,
                adAreaCurrentIndex: null,
                adTypeCurrentIndex: null,
                adMarketReleaseDateLevelCurrentIndex: null,
                adVersionCurrentIndex: null,
                adOtherCurrentIndex: null,
                original: null,
                originalMode: null,
                originalArea: null,
                originalType: null,
                originalMarketReleaseDateLevel: null,
                originalVersion: null,
                originalOther: null,
                originalOther2: null,
                originalModeCurrentIndex: null,
                originalAreaCurrentIndex: null,
                originalTypeCurrentIndex: null,
                originalMarketReleaseDateLevelCurrentIndex: null,
                originalVersionCurrentIndex: null,
                originalOtherCurrentIndex: null,
                channelIdSelected: "2",
                dataTypeSelected: "1",
                modeSelected: "",
                areaSelected: "",
                typeSelected: "",
                marketReleaseDateLevelSelected: "",
                finishedSelected : "",
                versionSelected: "",
                otherSelected: "",
                other2Selected: "",
                other3Selected: "",
                videoList: null,
                currentChannel: "tv"
            };
        },
        created: function () {
            readTvConfig();
        }
    });

    rooter.$watch("tv", function () {
        rooter.currentChannel = "tv";
        commonAjax();
        let tvModeLis = $("ul.tv-mode li");
        tvModeLis.unbind().click(function () {
            rooter.modeSelected = $(this).find("span").attr("mode");
            tvModeLis.removeClass("focus");
            $(this).addClass("focus");
            commonAjax();
        });

        let tvAreaLis = $("ul.tv-area li");
        tvAreaLis.unbind().click(function () {
            rooter.areaSelected = $(this).find("span").attr("area");
            tvAreaLis.removeClass("focus");
            $(this).addClass("focus");
            commonAjax();
        });

        let tvTypeLis = $("ul.tv-type li");
        tvTypeLis.unbind().click(function () {
            if (rooter.areaSelected === null ||
                rooter.areaSelected === "" ||
                $(this).find("span").attr("type") === "") {
                rooter.typeSelected = $(this).find("span").attr("type");
            } else {
                rooter.typeSelected = "," + $(this).find("span").attr("type");
            }

            tvTypeLis.removeClass("focus");
            $(this).addClass("focus");
            commonAjax();
        });

        let tvMarketReleaseDateLevelLis = $("ul.tv-marketReleaseDateLevel li");
        tvMarketReleaseDateLevelLis.unbind().click(function () {
            rooter.marketReleaseDateLevelSelected = $(this).find("span").attr("marketReleaseDateLevel");
            tvMarketReleaseDateLevelLis.removeClass("focus");
            $(this).addClass("focus");
            commonAjax();
        });
    });

    rooter.$watch("movie", function () {
        let movieModeLis = $("ul.movie-mode li");
        movieModeLis.unbind().click(function () {
            rooter.modeSelected = $(this).find("span").attr("mode");
            movieModeLis.removeClass("focus");
            $(this).addClass("focus");
            commonAjax();
        });

        let movieAreaLis = $("ul.movie-area li");
        movieAreaLis.unbind().click(function () {
            rooter.areaSelected = $(this).find("span").attr("area");
            movieAreaLis.removeClass("focus");
            $(this).addClass("focus");
            commonAjax();
        });

        let movieTypeLis = $("ul.movie-type li");
        movieTypeLis.unbind().click(function () {
            if (rooter.areaSelected === null ||
                rooter.areaSelected === "" ||
                $(this).find("span").attr("type") === "") {
                rooter.typeSelected = $(this).find("span").attr("type");
            } else {
                rooter.typeSelected = "," + $(this).find("span").attr("type");
            }

            movieTypeLis.removeClass("focus");
            $(this).addClass("focus");
            commonAjax();
        });

        let movieOtherLis = $("ul.movie-other li");
        movieOtherLis.unbind().click(function () {
            if (rooter.typeSelected === null ||
                rooter.typeSelected === "" ||
                $(this).find("span").attr("other") === "") {
                rooter.otherSelected = $(this).find("span").attr("other");
            } else {
                rooter.otherSelected = "," + $(this).find("span").attr("other");
            }
            movieOtherLis.removeClass("focus");
            $(this).addClass("focus");
            commonAjax();
        });

        let movieMarketReleaseDateLevelLis = $("ul.movie-marketReleaseDateLevel li");
        movieMarketReleaseDateLevelLis.unbind().click(function () {
            rooter.marketReleaseDateLevelSelected = $(this).find("span").attr("marketReleaseDateLevel");
            movieMarketReleaseDateLevelLis.removeClass("focus");
            $(this).addClass("focus");
            commonAjax();
        });
    });

    rooter.$watch("anime", function () {
        let animeModeLis = $("ul.anime-mode li");
        animeModeLis.unbind().click(function () {
            rooter.modeSelected = $(this).find("span").attr("mode");
            animeModeLis.removeClass("focus");
            $(this).addClass("focus");
            commonAjax();
        });

        let animeAreaLis = $("ul.anime-area li");
        animeAreaLis.unbind().click(function () {
            rooter.areaSelected = $(this).find("span").attr("area");
            animeAreaLis.removeClass("focus");
            $(this).addClass("focus");
            commonAjax();
        });

        let animeTypeLis = $("ul.anime-type li");
        animeTypeLis.unbind().click(function () {
            if (rooter.areaSelected === null ||
                rooter.areaSelected === "" ||
                $(this).find("span").attr("type") === "") {
                rooter.typeSelected = $(this).find("span").attr("type");
            } else {
                rooter.typeSelected = "," + $(this).find("span").attr("type");
            }

            animeTypeLis.removeClass("focus");
            $(this).addClass("focus");
            commonAjax();
        });

        let animeVersionLis = $("ul.anime-version li");
        animeVersionLis.unbind().click(function () {
            if (rooter.typeSelected === null ||
                rooter.typeSelected === "" ||
                $(this).find("span").attr("version") === "") {
                rooter.versionSelected = $(this).find("span").attr("version");
            } else {
                rooter.versionSelected = "," + $(this).find("span").attr("version");
            }
            animeVersionLis.removeClass("focus");
            $(this).addClass("focus");
            commonAjax();
        });

        let animeOtherLis = $("ul.anime-other li");
        animeOtherLis.unbind().click(function () {
            if (rooter.versionSelected === null ||
                rooter.versionSelected === "" ||
                $(this).find("span").attr("other") === "") {
                rooter.otherSelected = $(this).find("span").attr("other");
            } else {
                rooter.otherSelected = "," + $(this).find("span").attr("other");
            }
            animeOtherLis.removeClass("focus");
            $(this).addClass("focus");
            commonAjax();
        });

        let animeMarketReleaseDateLevelLis = $("ul.anime-marketReleaseDateLevel li");
        animeMarketReleaseDateLevelLis.unbind().click(function () {
            rooter.marketReleaseDateLevelSelected = $(this).find("span").attr("marketReleaseDateLevel");
            animeMarketReleaseDateLevelLis.removeClass("focus");
            $(this).addClass("focus");
            commonAjax();
        });
    });

    rooter.$watch("documentary", function () {
        let documentaryModeLis = $("ul.documentary-mode li");
        documentaryModeLis.unbind().click(function () {
            rooter.modeSelected = $(this).find("span").attr("mode");
            documentaryModeLis.removeClass("focus");
            $(this).addClass("focus");
            commonAjax();
        });

        let documentaryAreaLis = $("ul.documentary-area li");
        documentaryAreaLis.unbind().click(function () {
            rooter.areaSelected = $(this).find("span").attr("area");
            documentaryAreaLis.removeClass("focus");
            $(this).addClass("focus");
            commonAjax();
        });

        let documentaryTypeLis = $("ul.documentary-type li");
        documentaryTypeLis.unbind().click(function () {
            if (rooter.areaSelected === null ||
                rooter.areaSelected === "" ||
                $(this).find("span").attr("type") === "") {
                rooter.typeSelected = $(this).find("span").attr("type");
            } else {
                rooter.typeSelected = "," + $(this).find("span").attr("type");
            }

            documentaryTypeLis.removeClass("focus");
            $(this).addClass("focus");
            commonAjax();
        });

        let documentaryVersionLis = $("ul.documentary-version li");
        documentaryVersionLis.unbind().click(function () {
            if (rooter.typeSelected === null ||
                rooter.typeSelected === "" ||
                $(this).find("span").attr("version") === "") {
                rooter.versionSelected = $(this).find("span").attr("version");
            } else {
                rooter.versionSelected = "," + $(this).find("span").attr("version");
            }
            documentaryVersionLis.removeClass("focus");
            $(this).addClass("focus");
            commonAjax();
        });

        let documentaryOtherLis = $("ul.documentary-other li");
        documentaryOtherLis.unbind().click(function () {
            if (rooter.versionSelected === null ||
                rooter.versionSelected === "" ||
                $(this).find("span").attr("other") === "") {
                rooter.otherSelected = $(this).find("span").attr("other");
            } else {
                rooter.otherSelected = "," + $(this).find("span").attr("other");
            }
            documentaryOtherLis.removeClass("focus");
            $(this).addClass("focus");
            commonAjax();
        });

        let documentaryMarketReleaseDateLevelLis = $("ul.documentary-marketReleaseDateLevel li");
        documentaryMarketReleaseDateLevelLis.unbind().click(function () {
            rooter.marketReleaseDateLevelSelected = $(this).find("span").attr("marketReleaseDateLevel");
            documentaryMarketReleaseDateLevelLis.removeClass("focus");
            $(this).addClass("focus");
            commonAjax();
        });
    });

    rooter.$watch("information", function () {
        let informationModeLis = $("ul.information-mode li");
        informationModeLis.unbind().click(function () {
            rooter.modeSelected = $(this).find("span").attr("mode");
            informationModeLis.removeClass("focus");
            $(this).addClass("focus");
            commonAjax();
        });

        let informationTypeLis = $("ul.information-type li");
        informationTypeLis.unbind().click(function () {
            if (rooter.areaSelected === null ||
                rooter.areaSelected === "" ||
                $(this).find("span").attr("type") === "") {
                rooter.typeSelected = $(this).find("span").attr("type");
            } else {
                rooter.typeSelected = "," + $(this).find("span").attr("type");
            }

            informationTypeLis.removeClass("focus");
            $(this).addClass("focus");
            commonAjax();
        });

        let informationMarketReleaseDateLevelLis = $("ul.information-marketReleaseDateLevel li");
        informationMarketReleaseDateLevelLis.unbind().click(function () {
            rooter.marketReleaseDateLevelSelected = $(this).find("span").attr("marketReleaseDateLevel");
            informationMarketReleaseDateLevelLis.removeClass("focus");
            $(this).addClass("focus");
            commonAjax();
        });
    });


    rooter.$watch("entertainment", function () {
        let entertainmentModeLis = $("ul.entertainment-mode li");
        entertainmentModeLis.unbind().click(function () {
            rooter.modeSelected = $(this).find("span").attr("mode");
            entertainmentModeLis.removeClass("focus");
            $(this).addClass("focus");
            commonAjax();
        });

        let entertainmentAreaLis = $("ul.entertainment-area li");
        entertainmentAreaLis.unbind().click(function () {
            rooter.areaSelected = $(this).find("span").attr("area");
            entertainmentAreaLis.removeClass("focus");
            $(this).addClass("focus");
            commonAjax();
        });

        let entertainmentTypeLis = $("ul.entertainment-type li");
        entertainmentTypeLis.unbind().click(function () {
            if (rooter.areaSelected === null ||
                rooter.areaSelected === "" ||
                $(this).find("span").attr("type") === "") {
                rooter.typeSelected = $(this).find("span").attr("type");
            } else {
                rooter.typeSelected = "," + $(this).find("span").attr("type");
            }

            entertainmentTypeLis.removeClass("focus");
            $(this).addClass("focus");
            commonAjax();
        });

        let entertainmentOtherLis = $("ul.entertainment-other li");
        entertainmentOtherLis.unbind().click(function () {
            if (rooter.typeSelected === null ||
                rooter.typeSelected === "" ||
                $(this).find("span").attr("other") === "") {
                rooter.otherSelected = $(this).find("span").attr("other");
            } else {
                rooter.otherSelected = "," + $(this).find("span").attr("other");
            }
            entertainmentOtherLis.removeClass("focus");
            $(this).addClass("focus");
            commonAjax();
        });

        let entertainmentMarketReleaseDateLevelLis = $("ul.entertainment-marketReleaseDateLevel li");
        entertainmentMarketReleaseDateLevelLis.unbind().click(function () {
            rooter.marketReleaseDateLevelSelected = $(this).find("span").attr("marketReleaseDateLevel");
            entertainmentMarketReleaseDateLevelLis.removeClass("focus");
            $(this).addClass("focus");
            commonAjax();
        });
    });

    rooter.$watch("webmovie", function () {
        let webmovieModeLis = $("ul.webmovie-mode li");
        webmovieModeLis.unbind().click(function () {
            rooter.modeSelected = $(this).find("span").attr("mode");
            webmovieModeLis.removeClass("focus");
            $(this).addClass("focus");
            commonAjax();
        });

        let webmovieAreaLis = $("ul.webmovie-area li");
        webmovieAreaLis.unbind().click(function () {
            rooter.areaSelected = $(this).find("span").attr("area");
            webmovieAreaLis.removeClass("focus");
            $(this).addClass("focus");
            commonAjax();
        });

        let webmovieMarketReleaseDateLevelLis = $("ul.webmovie-marketReleaseDateLevel li");
        webmovieMarketReleaseDateLevelLis.unbind().click(function () {
            rooter.marketReleaseDateLevelSelected = $(this).find("span").attr("marketReleaseDateLevel");
            webmovieMarketReleaseDateLevelLis.removeClass("focus");
            $(this).addClass("focus");
            commonAjax();
        });

        $("ul.content li").unbind().click(function (event) {
        });
    });


    rooter.$watch("flower", function () {
        let flowerModeLis = $("ul.flower-mode li");
        flowerModeLis.unbind().click(function () {
            rooter.modeSelected = $(this).find("span").attr("mode");
            flowerModeLis.removeClass("focus");
            $(this).addClass("focus");
            commonAjax();
        });

        let flowerAreaLis = $("ul.flower-area li");
        flowerAreaLis.unbind().click(function () {
            rooter.areaSelected = $(this).find("span").attr("area");
            flowerAreaLis.removeClass("focus");
            $(this).addClass("focus");
            commonAjax();
        });

        let flowerTypeLis = $("ul.flower-type li");
        flowerTypeLis.unbind().click(function () {
            if (rooter.areaSelected === null ||
                rooter.areaSelected === "" ||
                $(this).find("span").attr("type") === "") {
                rooter.typeSelected = $(this).find("span").attr("type");
            } else {
                rooter.typeSelected = "," + $(this).find("span").attr("type");
            }

            flowerTypeLis.removeClass("focus");
            $(this).addClass("focus");
            commonAjax();
        });

        let flowerOtherLis = $("ul.flower-other li");
        flowerOtherLis.unbind().click(function () {
            if (rooter.typeSelected === null ||
                rooter.typeSelected === "" ||
                $(this).find("span").attr("other") === "") {
                rooter.otherSelected = $(this).find("span").attr("other");
            } else {
                rooter.otherSelected = "," + $(this).find("span").attr("other");
            }
            flowerOtherLis.removeClass("focus");
            $(this).addClass("focus");
            commonAjax();
        });

        let flowerMarketReleaseDateLevelLis = $("ul.flower-marketReleaseDateLevel li");
        flowerMarketReleaseDateLevelLis.unbind().click(function () {
            rooter.marketReleaseDateLevelSelected = $(this).find("span").attr("marketReleaseDateLevel");
            flowerMarketReleaseDateLevelLis.removeClass("focus");
            $(this).addClass("focus");
            commonAjax();
        });
    });


    rooter.$watch("music", function () {
        let musicModeLis = $("ul.music-mode li");
        musicModeLis.unbind().click(function () {
            rooter.modeSelected = $(this).find("span").attr("mode");
            musicModeLis.removeClass("focus");
            $(this).addClass("focus");
            commonAjax();
        });

        let musicAreaLis = $("ul.music-area li");
        musicAreaLis.unbind().click(function () {
            rooter.areaSelected = $(this).find("span").attr("area");
            musicAreaLis.removeClass("focus");
            $(this).addClass("focus");
            commonAjax();
        });

        let musicTypeLis = $("ul.music-type li");
        musicTypeLis.unbind().click(function () {
            if (rooter.areaSelected === null ||
                rooter.areaSelected === "" ||
                $(this).find("span").attr("type") === "") {
                rooter.typeSelected = $(this).find("span").attr("type");
            } else {
                rooter.typeSelected = "," + $(this).find("span").attr("type");
            }

            musicTypeLis.removeClass("focus");
            $(this).addClass("focus");
            commonAjax();
        });

        let musicVersionLis = $("ul.music-version li");
        musicVersionLis.unbind().click(function () {
            if (rooter.typeSelected === null ||
                rooter.typeSelected === "" ||
                $(this).find("span").attr("version") === "") {
                rooter.versionSelected = $(this).find("span").attr("version");
            } else {
                rooter.versionSelected = "," + $(this).find("span").attr("version");
            }
            musicVersionLis.removeClass("focus");
            $(this).addClass("focus");
            commonAjax();
        });

        let musicOtherLis = $("ul.music-other li");
        musicOtherLis.unbind().click(function () {
            if (rooter.versionSelected === null ||
                rooter.versionSelected === "" ||
                $(this).find("span").attr("other") === "") {
                rooter.otherSelected = $(this).find("span").attr("other");
            } else {
                rooter.otherSelected = "," + $(this).find("span").attr("other");
            }
            musicOtherLis.removeClass("focus");
            $(this).addClass("focus");
            commonAjax();
        });

        let musicMarketReleaseDateLevelLis = $("ul.music-marketReleaseDateLevel li");
        musicMarketReleaseDateLevelLis.unbind().click(function () {
            rooter.marketReleaseDateLevelSelected = $(this).find("span").attr("marketReleaseDateLevel");
            musicMarketReleaseDateLevelLis.removeClass("focus");
            $(this).addClass("focus");
            commonAjax();
        });
    });

    rooter.$watch("military", function () {
        let militaryModeLis = $("ul.military-mode li");
        militaryModeLis.unbind().click(function () {
            rooter.modeSelected = $(this).find("span").attr("mode");
            militaryModeLis.removeClass("focus");
            $(this).addClass("focus");
            commonAjax();
        });

        let militaryAreaLis = $("ul.military-area li");
        militaryAreaLis.unbind().click(function () {
            rooter.areaSelected = $(this).find("span").attr("area");
            militaryAreaLis.removeClass("focus");
            $(this).addClass("focus");
            commonAjax();
        });

        let militaryMarketReleaseDateLevelLis = $("ul.military-marketReleaseDateLevel li");
        militaryMarketReleaseDateLevelLis.unbind().click(function () {
            rooter.marketReleaseDateLevelSelected = $(this).find("span").attr("marketReleaseDateLevel");
            militaryMarketReleaseDateLevelLis.removeClass("focus");
            $(this).addClass("focus");
            commonAjax();
        });

        $("ul.content li").unbind().click(function (event) {
        });
    });


    rooter.$watch("information", function () {
        let informationModeLis = $("ul.information-mode li");
        informationModeLis.unbind().click(function () {
            rooter.modeSelected = $(this).find("span").attr("mode");
            informationModeLis.removeClass("focus");
            $(this).addClass("focus");
            commonAjax();
        });

        let informationTypeLis = $("ul.information-type li");
        informationTypeLis.unbind().click(function () {
            if (rooter.areaSelected === null ||
                rooter.areaSelected === "" ||
                $(this).find("span").attr("type") === "") {
                rooter.typeSelected = $(this).find("span").attr("type");
            } else {
                rooter.typeSelected = "," + $(this).find("span").attr("type");
            }

            informationTypeLis.removeClass("focus");
            $(this).addClass("focus");
            commonAjax();
        });

        let informationMarketReleaseDateLevelLis = $("ul.information-marketReleaseDateLevel li");
        informationMarketReleaseDateLevelLis.unbind().click(function () {
            rooter.marketReleaseDateLevelSelected = $(this).find("span").attr("marketReleaseDateLevel");
            informationMarketReleaseDateLevelLis.removeClass("focus");
            $(this).addClass("focus");
            commonAjax();
        });
    });


    rooter.$watch("education", function () {
        let educationModeLis = $("ul.education-mode li");
        educationModeLis.unbind().click(function () {
            rooter.modeSelected = $(this).find("span").attr("mode");
            educationModeLis.removeClass("focus");
            $(this).addClass("focus");
            commonAjax();
        });

        let educationAreaLis = $("ul.education-area li");
        educationAreaLis.unbind().click(function () {
            rooter.areaSelected = $(this).find("span").attr("area");
            educationAreaLis.removeClass("focus");
            $(this).addClass("focus");
            commonAjax();
        });

        let educationMarketReleaseDateLevelLis = $("ul.education-marketReleaseDateLevel li");
        educationMarketReleaseDateLevelLis.unbind().click(function () {
            rooter.marketReleaseDateLevelSelected = $(this).find("span").attr("marketReleaseDateLevel");
            educationMarketReleaseDateLevelLis.removeClass("focus");
            $(this).addClass("focus");
            commonAjax();
        });

        $("ul.content li").unbind().click(function (event) {
        });
    });

    rooter.$watch("physicaleducation", function () {
        let physicaleducationModeLis = $("ul.physicaleducation-mode li");
        physicaleducationModeLis.unbind().click(function () {
            rooter.modeSelected = $(this).find("span").attr("mode");
            physicaleducationModeLis.removeClass("focus");
            $(this).addClass("focus");
            commonAjax();
        });

        let physicaleducationAreaLis = $("ul.physicaleducation-area li");
        physicaleducationAreaLis.unbind().click(function () {
            rooter.areaSelected = $(this).find("span").attr("area");
            physicaleducationAreaLis.removeClass("focus");
            $(this).addClass("focus");
            commonAjax();
        });

        let physicaleducationTypeLis = $("ul.physicaleducation-type li");
        physicaleducationTypeLis.unbind().click(function () {
            if (rooter.areaSelected === null ||
                rooter.areaSelected === "" ||
                $(this).find("span").attr("type") === "") {
                rooter.typeSelected = $(this).find("span").attr("type");
            } else {
                rooter.typeSelected = "," + $(this).find("span").attr("type");
            }

            physicaleducationTypeLis.removeClass("focus");
            $(this).addClass("focus");
            commonAjax();
        });

        let physicaleducationVersionLis = $("ul.physicaleducation-version li");
        physicaleducationVersionLis.unbind().click(function () {
            if (rooter.typeSelected === null ||
                rooter.typeSelected === "" ||
                $(this).find("span").attr("version") === "") {
                rooter.versionSelected = $(this).find("span").attr("version");
            } else {
                rooter.versionSelected = "," + $(this).find("span").attr("version");
            }
            physicaleducationVersionLis.removeClass("focus");
            $(this).addClass("focus");
            commonAjax();
        });

        let physicaleducationMarketReleaseDateLevelLis = $("ul.physicaleducation-marketReleaseDateLevel li");
        physicaleducationMarketReleaseDateLevelLis.unbind().click(function () {
            rooter.marketReleaseDateLevelSelected = $(this).find("span").attr("marketReleaseDateLevel");
            physicaleducationMarketReleaseDateLevelLis.removeClass("focus");
            $(this).addClass("focus");
            commonAjax();
        });
    });


    rooter.$watch("child", function () {
        let childModeLis = $("ul.child-mode li");
        childModeLis.unbind().click(function () {
            rooter.modeSelected = $(this).find("span").attr("mode");
            childModeLis.removeClass("focus");
            $(this).addClass("focus");
            commonAjax();
        });

        let childAreaLis = $("ul.child-area li");
        childAreaLis.unbind().click(function () {
            rooter.areaSelected = $(this).find("span").attr("area");
            childAreaLis.removeClass("focus");
            $(this).addClass("focus");
            commonAjax();
        });

        let childTypeLis = $("ul.child-type li");
        childTypeLis.unbind().click(function () {
            if (rooter.areaSelected === null ||
                rooter.areaSelected === "" ||
                $(this).find("span").attr("type") === "") {
                rooter.typeSelected = $(this).find("span").attr("type");
            } else {
                rooter.typeSelected = "," + $(this).find("span").attr("type");
            }

            childTypeLis.removeClass("focus");
            $(this).addClass("focus");
            commonAjax();
        });

        let childVersionLis = $("ul.child-version li");
        childVersionLis.unbind().click(function () {
            if (rooter.typeSelected === null ||
                rooter.typeSelected === "" ||
                $(this).find("span").attr("version") === "") {
                rooter.versionSelected = $(this).find("span").attr("version");
            } else {
                rooter.versionSelected = "," + $(this).find("span").attr("version");
            }
            childVersionLis.removeClass("focus");
            $(this).addClass("focus");
            commonAjax();
        });

        let childOtherLis = $("ul.child-other li");
        childOtherLis.unbind().click(function () {
            if (rooter.versionSelected === null ||
                rooter.versionSelected === "" ||
                $(this).find("span").attr("other") === "") {
                rooter.otherSelected = $(this).find("span").attr("other");
            } else {
                rooter.otherSelected = "," + $(this).find("span").attr("other");
            }
            childOtherLis.removeClass("focus");
            $(this).addClass("focus");
            commonAjax();
        });

        let childOther2Lis = $("ul.child-other2 li");
        childOther2Lis.unbind().click(function () {
            if (rooter.versionSelected === null ||
                rooter.versionSelected === "" ||
                $(this).find("span").attr("other2") === "") {
                rooter.other2Selected = $(this).find("span").attr("other2");
            } else {
                rooter.other2Selected = "," + $(this).find("span").attr("other2");
            }
            childOther2Lis.removeClass("focus");
            $(this).addClass("focus");
            commonAjax();
        });

        let childMarketReleaseDateLevelLis = $("ul.child-marketReleaseDateLevel li");
        childMarketReleaseDateLevelLis.unbind().click(function () {
            rooter.marketReleaseDateLevelSelected = $(this).find("span").attr("marketReleaseDateLevel");
            childMarketReleaseDateLevelLis.removeClass("focus");
            $(this).addClass("focus");
            commonAjax();
        });
    });

    rooter.$watch("fashion", function () {
        let fashionModeLis = $("ul.fashion-mode li");
        fashionModeLis.unbind().click(function () {
            rooter.modeSelected = $(this).find("span").attr("mode");
            fashionModeLis.removeClass("focus");
            $(this).addClass("focus");
            commonAjax();
        });

        let fashionAreaLis = $("ul.fashion-area li");
        fashionAreaLis.unbind().click(function () {
            rooter.areaSelected = $(this).find("span").attr("area");
            fashionAreaLis.removeClass("focus");
            $(this).addClass("focus");
            commonAjax();
        });

        let fashionTypeLis = $("ul.fashion-type li");
        fashionTypeLis.unbind().click(function () {
            if (rooter.areaSelected === null ||
                rooter.areaSelected === "" ||
                $(this).find("span").attr("type") === "") {
                rooter.typeSelected = $(this).find("span").attr("type");
            } else {
                rooter.typeSelected = "," + $(this).find("span").attr("type");
            }

            fashionTypeLis.removeClass("focus");
            $(this).addClass("focus");
            commonAjax();
        });

        let fashionVersionLis = $("ul.fashion-version li");
        fashionVersionLis.unbind().click(function () {
            if (rooter.typeSelected === null ||
                rooter.typeSelected === "" ||
                $(this).find("span").attr("version") === "") {
                rooter.versionSelected = $(this).find("span").attr("version");
            } else {
                rooter.versionSelected = "," + $(this).find("span").attr("version");
            }
            fashionVersionLis.removeClass("focus");
            $(this).addClass("focus");
            commonAjax();
        });

        let fashionMarketReleaseDateLevelLis = $("ul.fashion-marketReleaseDateLevel li");
        fashionMarketReleaseDateLevelLis.unbind().click(function () {
            rooter.marketReleaseDateLevelSelected = $(this).find("span").attr("marketReleaseDateLevel");
            fashionMarketReleaseDateLevelLis.removeClass("focus");
            $(this).addClass("focus");
            commonAjax();
        });
    });

    rooter.$watch("life", function () {
        let lifeModeLis = $("ul.life-mode li");
        lifeModeLis.unbind().click(function () {
            rooter.modeSelected = $(this).find("span").attr("mode");
            lifeModeLis.removeClass("focus");
            $(this).addClass("focus");
            commonAjax();
        });

        let lifeAreaLis = $("ul.life-area li");
        lifeAreaLis.unbind().click(function () {
            rooter.areaSelected = $(this).find("span").attr("area");
            lifeAreaLis.removeClass("focus");
            $(this).addClass("focus");
            commonAjax();
        });

        let lifeMarketReleaseDateLevelLis = $("ul.life-marketReleaseDateLevel li");
        lifeMarketReleaseDateLevelLis.unbind().click(function () {
            rooter.marketReleaseDateLevelSelected = $(this).find("span").attr("marketReleaseDateLevel");
            lifeMarketReleaseDateLevelLis.removeClass("focus");
            $(this).addClass("focus");
            commonAjax();
        });
    });

    rooter.$watch("car", function () {
        let carModeLis = $("ul.car-mode li");
        carModeLis.unbind().click(function () {
            rooter.modeSelected = $(this).find("span").attr("mode");
            carModeLis.removeClass("focus");
            $(this).addClass("focus");
            commonAjax();
        });

        let carAreaLis = $("ul.car-area li");
        carAreaLis.unbind().click(function () {
            rooter.areaSelected = $(this).find("span").attr("area");
            carAreaLis.removeClass("focus");
            $(this).addClass("focus");
            commonAjax();
        });

        let carTypeLis = $("ul.car-type li");
        carTypeLis.unbind().click(function () {
            if (rooter.areaSelected === null ||
                rooter.areaSelected === "" ||
                $(this).find("span").attr("type") === "") {
                rooter.typeSelected = $(this).find("span").attr("type");
            } else {
                rooter.typeSelected = "," + $(this).find("span").attr("type");
            }

            carTypeLis.removeClass("focus");
            $(this).addClass("focus");
            commonAjax();
        });

        let carVersionLis = $("ul.car-version li");
        carVersionLis.unbind().click(function () {
            if (rooter.typeSelected === null ||
                rooter.typeSelected === "" ||
                $(this).find("span").attr("version") === "") {
                rooter.versionSelected = $(this).find("span").attr("version");
            } else {
                rooter.versionSelected = "," + $(this).find("span").attr("version");
            }
            carVersionLis.removeClass("focus");
            $(this).addClass("focus");
            commonAjax();
        });

        let carMarketReleaseDateLevelLis = $("ul.car-marketReleaseDateLevel li");
        carMarketReleaseDateLevelLis.unbind().click(function () {
            rooter.marketReleaseDateLevelSelected = $(this).find("span").attr("marketReleaseDateLevel");
            carMarketReleaseDateLevelLis.removeClass("focus");
            $(this).addClass("focus");
            commonAjax();
        });
    });

    rooter.$watch("ad", function () {
        let adModeLis = $("ul.ad-mode li");
        adModeLis.unbind().click(function () {
            rooter.modeSelected = $(this).find("span").attr("mode");
            adModeLis.removeClass("focus");
            $(this).addClass("focus");
            commonAjax();
        });

        let adAreaLis = $("ul.ad-area li");
        adAreaLis.unbind().click(function () {
            rooter.areaSelected = $(this).find("span").attr("area");
            adAreaLis.removeClass("focus");
            $(this).addClass("focus");
            commonAjax();
        });

        let adTypeLis = $("ul.ad-type li");
        adTypeLis.unbind().click(function () {
            if (rooter.areaSelected === null ||
                rooter.areaSelected === "" ||
                $(this).find("span").attr("type") === "") {
                rooter.typeSelected = $(this).find("span").attr("type");
            } else {
                rooter.typeSelected = "," + $(this).find("span").attr("type");
            }

            adTypeLis.removeClass("focus");
            $(this).addClass("focus");
            commonAjax();
        });

        let adVersionLis = $("ul.ad-version li");
        adVersionLis.unbind().click(function () {
            if (rooter.typeSelected === null ||
                rooter.typeSelected === "" ||
                $(this).find("span").attr("version") === "") {
                rooter.versionSelected = $(this).find("span").attr("version");
            } else {
                rooter.versionSelected = "," + $(this).find("span").attr("version");
            }
            adVersionLis.removeClass("focus");
            $(this).addClass("focus");
            commonAjax();
        });

        let adMarketReleaseDateLevelLis = $("ul.ad-marketReleaseDateLevel li");
        adMarketReleaseDateLevelLis.unbind().click(function () {
            rooter.marketReleaseDateLevelSelected = $(this).find("span").attr("marketReleaseDateLevel");
            adMarketReleaseDateLevelLis.removeClass("focus");
            $(this).addClass("focus");
            commonAjax();
        });
    });

    rooter.$watch("original", function () {
        let originalModeLis = $("ul.original-mode li");
        originalModeLis.unbind().click(function () {
            rooter.modeSelected = $(this).find("span").attr("mode");
            originalModeLis.removeClass("focus");
            $(this).addClass("focus");
            commonAjax();
        });

        let originalAreaLis = $("ul.original-area li");
        originalAreaLis.unbind().click(function () {
            rooter.areaSelected = $(this).find("span").attr("area");
            originalAreaLis.removeClass("focus");
            $(this).addClass("focus");
            commonAjax();
        });

        let originalMarketReleaseDateLevelLis = $("ul.original-marketReleaseDateLevel li");
        originalMarketReleaseDateLevelLis.unbind().click(function () {
            rooter.marketReleaseDateLevelSelected = $(this).find("span").attr("marketReleaseDateLevel");
            originalMarketReleaseDateLevelLis.removeClass("focus");
            $(this).addClass("focus");
            commonAjax();
        });

        $("ul.content li").unbind().click(function (event) {
        });
    });

    function commonAjax() {
        $.ajax({
            type: "GET",
            url: "/iqiyi/searchTv?channelId=" + rooter.channelIdSelected + "&dataType=" + rooter.dataTypeSelected + "&finished=" +  rooter.finishedSelected + "&marketReleaseDateLevel=" + rooter.marketReleaseDateLevelSelected + "&mode=" + rooter.modeSelected + "&pageNumber=1&pageSize=48&area=" + rooter.areaSelected + "&type=" + rooter.typeSelected + "&version=" + rooter.versionSelected + "&other=" + rooter.otherSelected + "&other2=" + rooter.other2Selected + "&other3=" + rooter.other3Selected,
            dataType: "json",
            success: function (data) {
                rooter.videoList = data.data.list;
            }
        });
    }

    $(window).scroll(function () {
        if ($(document).scrollTop() >= ($(document).height() - $(window).height()) * 0.95) {
            pageIndex++;
            $.ajax({
                type: "GET",
                url: "/iqiyi/searchTv?channelId=" + rooter.channelIdSelected + "&dataType=" + rooter.dataTypeSelected + "&finished=" +  rooter.finishedSelected + "&marketReleaseDateLevel=" + rooter.marketReleaseDateLevelSelected + "&mode=" + rooter.modeSelected + "&pageNumber=" + pageIndex + "&pageSize=48&area=" + rooter.areaSelected + "&type=" + rooter.typeSelected + "&version=" + rooter.versionSelected + "&other=" + rooter.otherSelected + "&other2=" + rooter.other2Selected + "&other3=" + rooter.other3Selected,
                dataType: "json",
                success: function (data) {
                    let videoList = data.data.list;
                    for (let i = 0; i < videoList.length; i++) {
                        rooter.videoList.push(videoList[i]);
                    }
                }
            });
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
                rooter.channelIdSelected = "2";
                rooter.dataTypeSelected = "1";
                readTvConfig();
                break;
            case "电影":
                rooter.currentChannel = "movie";
                rooter.channelIdSelected = "1";
                rooter.dataTypeSelected = "1";
                readMovieConfig();
                break;
            case "动漫":
                rooter.currentChannel = "anime";
                rooter.channelIdSelected = "4";
                rooter.dataTypeSelected = "1";
                dealAnimeConfig();
                break;
            case "纪录片":
                rooter.currentChannel = "documentary";
                rooter.channelIdSelected = "3";
                rooter.dataTypeSelected = "1";
                dealDocumentaryConfig();
                break;
            case "资讯":
                rooter.currentChannel = "information";
                rooter.channelIdSelected = "25";
                rooter.dataTypeSelected = "1";
                dealInformationConfig();
                break;
            case "娱乐":
                rooter.currentChannel = "entertainment";
                rooter.channelIdSelected = "7";
                rooter.dataTypeSelected = "1";
                dealentertainmentConfig();
                break;
            case "网络电影":
                rooter.currentChannel = "webmovie";
                rooter.channelIdSelected = "16";
                rooter.dataTypeSelected = "2";
                dealwebmovieConfig();
                break;
            case "片花":
                rooter.currentChannel = "flower";
                rooter.channelIdSelected = "10";
                rooter.dataTypeSelected = "2";
                dealFlowerConfig();
                break;
            case "音乐":
                rooter.currentChannel = "music";
                rooter.channelIdSelected = "5";
                rooter.dataTypeSelected = "2";
                dealMusicConfig();
                break;
            case "军事":
                rooter.currentChannel = "military";
                rooter.channelIdSelected = "28";
                rooter.dataTypeSelected = "2";
                dealMilitaryConfig();
                break;
            case "教育":
                rooter.currentChannel = "education";
                rooter.channelIdSelected = "12";
                rooter.dataTypeSelected = "1";
                dealEducationConfig();
                break;
            case "体育":
                rooter.currentChannel = "physicaleducation";
                rooter.channelIdSelected = "17";
                rooter.dataTypeSelected = "2";
                dealPhysicalEducationConfig();
                break;
            case "儿童":
                rooter.currentChannel = "child";
                rooter.channelIdSelected = "15";
                rooter.dataTypeSelected = "1";
                dealChildConfig();
                break;
            case "时尚":
                rooter.currentChannel = "fashion";
                rooter.channelIdSelected = "13";
                rooter.dataTypeSelected = "2";
                dealFashionConfig();
                break;
            case "生活":
                rooter.currentChannel = "life";
                rooter.channelIdSelected = "21";
                rooter.dataTypeSelected = "2";
                dealLifeConfig();
                break;
            case "汽车":
                rooter.currentChannel = "car";
                rooter.channelIdSelected = "26";
                rooter.dataTypeSelected = "2";
                dealCarConfig();
                break;
            case "广告":
                rooter.currentChannel = "ad";
                rooter.channelIdSelected = "20";
                rooter.dataTypeSelected = "2";
                dealAdConfig();
                break;
            case "原创":
                rooter.currentChannel = "original";
                rooter.channelIdSelected = "27";
                rooter.dataTypeSelected = "1";
                dealOriginalConfig();
                break;
            default:
                break;
        }
        commonAjax();
        channelLis.removeClass("focus");
        $(this).addClass("focus");
    });

    function readTvConfig() {
        $.ajax({
            type: "GET",
            url: "/htmls/iqiyi/tv-config.json",
            dataType: "json",
            success: function (data) {
                //处理电视剧
                rooter.tv = data["电视剧"];
                rooter.tvMode = rooter.tv.mode;
                rooter.tvArea = rooter.tv.area;
                rooter.tvType = rooter.tv.type;
                rooter.tvMarketReleaseDateLevel = rooter.tv.marketReleaseDateLevel;
                rooter.tvModeCurrentIndex = 1;
                rooter.tvAreaCurrentIndex = 1;
                rooter.tvTypeCurrentIndex = 1;
                rooter.tvMarketReleaseDateLevelCurrentIndex = 1;
                //重置数据
                rooter.currentChannel = "tv";
                rooter.channelIdSelected = "2";
                rooter.modeSelected = "24";
                rooter.areaSelected = "";
                rooter.typeSelected = "";
                rooter.marketReleaseDateLevelSelected = "";
                rooter.versionSelected = "";
                rooter.otherSelected = "";
            }
        });
    }

    function readMovieConfig() {
        $.ajax({
            type: "GET",
            url: "/htmls/iqiyi/movie-config.json",
            dataType: "json",
            success: function (data) {
                //处理电影
                rooter.movie = data["电影"];
                rooter.movieMode = rooter.movie.mode;
                rooter.movieArea = rooter.movie.area;
                rooter.movieType = rooter.movie.type;
                rooter.movieMarketReleaseDateLevel = rooter.movie.marketReleaseDateLevel;
                rooter.movieOther = rooter.movie.other;
                rooter.movieModeCurrentIndex = 1;
                rooter.movieAreaCurrentIndex = 1;
                rooter.movieTypeCurrentIndex = 1;
                rooter.movieMarketReleaseDateLevelCurrentIndex = 1;
                rooter.movieOtherCurrentIndex = 1;

                //重置数据
                rooter.currentChannel = "movie";
                rooter.channelIdSelected = "1";
                rooter.dataTypeSelected = "1";
                rooter.modeSelected = "24";
                rooter.areaSelected = "";
                rooter.typeSelected = "";
                rooter.marketReleaseDateLevelSelected = "";
                rooter.versionSelected = "";
                rooter.otherSelected = "";
            }
        });
    }

    function dealAnimeConfig() {
        $.ajax({
            type: "GET",
            url: "/htmls/iqiyi/anime-config.json",
            dataType: "json",
            success: function (data) {
                //处理动漫
                rooter.anime = data["动漫"];
                rooter.animeMode = rooter.anime.mode;
                rooter.animeArea = rooter.anime.area;
                rooter.animeType = rooter.anime.type;
                rooter.animeMarketReleaseDateLevel = rooter.anime.marketReleaseDateLevel;
                rooter.animeVersion = rooter.anime.version;
                rooter.animeOther = rooter.anime.other;
                rooter.animeModeCurrentIndex = 1;
                rooter.animeAreaCurrentIndex = 1;
                rooter.animeTypeCurrentIndex = 1;
                rooter.animeMarketReleaseDateLevelCurrentIndex = 1;
                rooter.animeVersionCurrentIndex = 1;
                rooter.animeOtherCurrentIndex = 1;

                //重置数据
                rooter.currentChannel = "anime";
                rooter.channelIdSelected = "4";
                rooter.modeSelected = "24";
                rooter.areaSelected = "";
                rooter.typeSelected = "";
                rooter.marketReleaseDateLevelSelected = "";
                rooter.versionSelected = "";
                rooter.otherSelected = "";
                rooter.finishedSelected = "";
            }
        });
    }

    function dealDocumentaryConfig() {
        $.ajax({
            type: "GET",
            url: "/htmls/iqiyi/documentary-config.json",
            dataType: "json",
            success: function (data) {
                //处理纪录片
                rooter.documentary = data["纪录片"];
                rooter.documentaryMode = rooter.documentary.mode;
                rooter.documentaryArea = rooter.documentary.area;
                rooter.documentaryType = rooter.documentary.type;
                rooter.documentaryMarketReleaseDateLevel = rooter.documentary.marketReleaseDateLevel;
                rooter.documentaryVersion = rooter.documentary.version;
                rooter.documentaryOther = rooter.documentary.other;
                rooter.documentaryModeCurrentIndex = 1;
                rooter.documentaryAreaCurrentIndex = 1;
                rooter.documentaryTypeCurrentIndex = 1;
                rooter.documentaryMarketReleaseDateLevelCurrentIndex = 1;
                rooter.documentaryVersionCurrentIndex = 1;
                rooter.documentaryOtherCurrentIndex = 1;

                //重置数据
                rooter.currentChannel = "documentary";
                rooter.channelIdSelected = "3";
                rooter.modeSelected = "24";
                rooter.areaSelected = "";
                rooter.typeSelected = "";
                rooter.marketReleaseDateLevelSelected = "";
                rooter.versionSelected = "";
                rooter.otherSelected = "";
                rooter.finishedSelected = "";
            }
        });
    }

    function dealInformationConfig() {
        $.ajax({
            type: "GET",
            url: "/htmls/iqiyi/information-config.json",
            dataType: "json",
            success: function (data) {
                //处理资讯
                rooter.information = data["资讯"];
                rooter.informationMode = rooter.information.mode;
                rooter.informationArea = rooter.information.area;
                rooter.informationType = rooter.information.type;
                rooter.informationMarketReleaseDateLevel = rooter.information.marketReleaseDateLevel;
                rooter.informationVersion = rooter.information.version;
                rooter.informationOther = rooter.information.other;
                rooter.informationModeCurrentIndex = 1;
                rooter.informationAreaCurrentIndex = 1;
                rooter.informationTypeCurrentIndex = 1;
                rooter.informationMarketReleaseDateLevelCurrentIndex = 1;
                rooter.informationVersionCurrentIndex = 1;
                rooter.informationOtherCurrentIndex = 1;

                //重置数据
                rooter.currentChannel = "information";
                rooter.channelIdSelected = "25";
                rooter.modeSelected = "24";
                rooter.areaSelected = "";
                rooter.typeSelected = "";
                rooter.marketReleaseDateLevelSelected = "";
                rooter.versionSelected = "";
                rooter.otherSelected = "";
                rooter.finishedSelected = "";
            }
        });
    }

    function dealentertainmentConfig() {
        $.ajax({
            type: "GET",
            url: "/htmls/iqiyi/entertainment-config.json",
            dataType: "json",
            success: function (data) {
                //处理娱乐
                rooter.entertainment = data["娱乐"];
                rooter.entertainmentMode = rooter.entertainment.mode;
                rooter.entertainmentArea = rooter.entertainment.area;
                rooter.entertainmentType = rooter.entertainment.type;
                rooter.entertainmentMarketReleaseDateLevel = rooter.entertainment.marketReleaseDateLevel;
                rooter.entertainmentVersion = rooter.entertainment.version;
                rooter.entertainmentOther = rooter.entertainment.other;
                rooter.entertainmentModeCurrentIndex = 1;
                rooter.entertainmentAreaCurrentIndex = 1;
                rooter.entertainmentTypeCurrentIndex = 1;
                rooter.entertainmentMarketReleaseDateLevelCurrentIndex = 1;
                rooter.entertainmentVersionCurrentIndex = 1;
                rooter.entertainmentOtherCurrentIndex = 1;

                //重置数据
                rooter.currentChannel = "entertainment";
                rooter.channelIdSelected = "7";
                rooter.modeSelected = "24";
                rooter.areaSelected = "";
                rooter.typeSelected = "";
                rooter.marketReleaseDateLevelSelected = "";
                rooter.versionSelected = "";
                rooter.otherSelected = "";
                rooter.finishedSelected = "";
            }
        });
    }

    function dealwebmovieConfig() {
        $.ajax({
            type: "GET",
            url: "/htmls/iqiyi/webmovie-config.json",
            dataType: "json",
            success: function (data) {
                //处理网络电影
                rooter.webmovie = data["网络电影"];
                rooter.webmovieMode = rooter.webmovie.mode;
                rooter.webmovieArea = rooter.webmovie.area;
                rooter.webmovieType = rooter.webmovie.type;
                rooter.webmovieMarketReleaseDateLevel = rooter.webmovie.marketReleaseDateLevel;
                rooter.webmovieVersion = rooter.webmovie.version;
                rooter.webmovieOther = rooter.webmovie.other;
                rooter.webmovieModeCurrentIndex = 1;
                rooter.webmovieAreaCurrentIndex = 1;
                rooter.webmovieTypeCurrentIndex = 1;
                rooter.webmovieMarketReleaseDateLevelCurrentIndex = 1;
                rooter.webmovieVersionCurrentIndex = 1;
                rooter.webmovieOtherCurrentIndex = 1;

                //重置数据
                rooter.currentChannel = "webmovie";
                rooter.channelIdSelected = "16";
                rooter.modeSelected = "24";
                rooter.areaSelected = "";
                rooter.typeSelected = "";
                rooter.marketReleaseDateLevelSelected = "";
                rooter.versionSelected = "";
                rooter.otherSelected = "";
                rooter.finishedSelected = "";
            }
        });
    }

    function dealFlowerConfig() {
        $.ajax({
            type: "GET",
            url: "/htmls/iqiyi/flower-config.json",
            dataType: "json",
            success: function (data) {
                //处理片花
                rooter.flower = data["片花"];
                rooter.flowerMode = rooter.flower.mode;
                rooter.flowerArea = rooter.flower.area;
                rooter.flowerType = rooter.flower.type;
                rooter.flowerMarketReleaseDateLevel = rooter.flower.marketReleaseDateLevel;
                rooter.flowerVersion = rooter.flower.version;
                rooter.flowerOther = rooter.flower.other;
                rooter.flowerModeCurrentIndex = 1;
                rooter.flowerAreaCurrentIndex = 1;
                rooter.flowerTypeCurrentIndex = 1;
                rooter.flowerMarketReleaseDateLevelCurrentIndex = 1;
                rooter.flowerVersionCurrentIndex = 1;
                rooter.flowerOtherCurrentIndex = 1;

                //重置数据
                rooter.currentChannel = "flower";
                rooter.channelIdSelected = "10";
                rooter.modeSelected = "24";
                rooter.areaSelected = "";
                rooter.typeSelected = "";
                rooter.marketReleaseDateLevelSelected = "";
                rooter.versionSelected = "";
                rooter.otherSelected = "";
                rooter.finishedSelected = "";
            }
        });
    }

    function dealMusicConfig() {
        $.ajax({
            type: "GET",
            url: "/htmls/iqiyi/music-config.json",
            dataType: "json",
            success: function (data) {
                //处理音乐
                rooter.music = data["音乐"];
                rooter.musicMode = rooter.music.mode;
                rooter.musicArea = rooter.music.area;
                rooter.musicType = rooter.music.type;
                rooter.musicMarketReleaseDateLevel = rooter.music.marketReleaseDateLevel;
                rooter.musicVersion = rooter.music.version;
                rooter.musicOther = rooter.music.other;
                rooter.musicModeCurrentIndex = 1;
                rooter.musicAreaCurrentIndex = 1;
                rooter.musicTypeCurrentIndex = 1;
                rooter.musicMarketReleaseDateLevelCurrentIndex = 1;
                rooter.musicVersionCurrentIndex = 1;
                rooter.musicOtherCurrentIndex = 1;

                //重置数据
                rooter.currentChannel = "music";
                rooter.channelIdSelected = "5";
                rooter.modeSelected = "24";
                rooter.areaSelected = "";
                rooter.typeSelected = "";
                rooter.marketReleaseDateLevelSelected = "";
                rooter.versionSelected = "";
                rooter.otherSelected = "";
                rooter.finishedSelected = "";
            }
        });
    }

    function dealMilitaryConfig() {
        $.ajax({
            type: "GET",
            url: "/htmls/iqiyi/military-config.json",
            dataType: "json",
            success: function (data) {
                //处理军事
                rooter.military = data["军事"];
                rooter.militaryMode = rooter.military.mode;
                rooter.militaryArea = rooter.military.area;
                rooter.militaryType = rooter.military.type;
                rooter.militaryMarketReleaseDateLevel = rooter.military.marketReleaseDateLevel;
                rooter.militaryVersion = rooter.military.version;
                rooter.militaryOther = rooter.military.other;
                rooter.militaryModeCurrentIndex = 1;
                rooter.militaryAreaCurrentIndex = 1;
                rooter.militaryTypeCurrentIndex = 1;
                rooter.militaryMarketReleaseDateLevelCurrentIndex = 1;
                rooter.militaryVersionCurrentIndex = 1;
                rooter.militaryOtherCurrentIndex = 1;

                //重置数据
                rooter.currentChannel = "military";
                rooter.channelIdSelected = "28";
                rooter.modeSelected = "24";
                rooter.areaSelected = "";
                rooter.typeSelected = "";
                rooter.marketReleaseDateLevelSelected = "";
                rooter.versionSelected = "";
                rooter.otherSelected = "";
                rooter.finishedSelected = "";
            }
        });
    }

    function dealEducationConfig() {
        $.ajax({
            type: "GET",
            url: "/htmls/iqiyi/education-config.json",
            dataType: "json",
            success: function (data) {
                //处理教育
                rooter.education = data["教育"];
                rooter.educationMode = rooter.education.mode;
                rooter.educationArea = rooter.education.area;
                rooter.educationType = rooter.education.type;
                rooter.educationMarketReleaseDateLevel = rooter.education.marketReleaseDateLevel;
                rooter.educationVersion = rooter.education.version;
                rooter.educationOther = rooter.education.other;
                rooter.educationModeCurrentIndex = 1;
                rooter.educationAreaCurrentIndex = 1;
                rooter.educationTypeCurrentIndex = 1;
                rooter.educationMarketReleaseDateLevelCurrentIndex = 1;
                rooter.educationVersionCurrentIndex = 1;
                rooter.educationOtherCurrentIndex = 1;

                //重置数据
                rooter.currentChannel = "education";
                rooter.channelIdSelected = "12";
                rooter.modeSelected = "24";
                rooter.areaSelected = "";
                rooter.typeSelected = "";
                rooter.marketReleaseDateLevelSelected = "";
                rooter.versionSelected = "";
                rooter.otherSelected = "";
                rooter.finishedSelected = "";
            }
        });
    }

    function dealPhysicalEducationConfig() {
        $.ajax({
            type: "GET",
            url: "/htmls/iqiyi/physicaleducation-config.json",
            dataType: "json",
            success: function (data) {
                //处理体育
                rooter.physicaleducation = data["体育"];
                rooter.physicaleducationMode = rooter.physicaleducation.mode;
                rooter.physicaleducationArea = rooter.physicaleducation.area;
                rooter.physicaleducationType = rooter.physicaleducation.type;
                rooter.physicaleducationMarketReleaseDateLevel = rooter.physicaleducation.marketReleaseDateLevel;
                rooter.physicaleducationVersion = rooter.physicaleducation.version;
                rooter.physicaleducationOther = rooter.physicaleducation.other;
                rooter.physicaleducationModeCurrentIndex = 1;
                rooter.physicaleducationAreaCurrentIndex = 1;
                rooter.physicaleducationTypeCurrentIndex = 1;
                rooter.physicaleducationMarketReleaseDateLevelCurrentIndex = 1;
                rooter.physicaleducationVersionCurrentIndex = 1;
                rooter.physicaleducationOtherCurrentIndex = 1;

                //重置数据
                rooter.currentChannel = "physicaleducation";
                rooter.channelIdSelected = "17";
                rooter.modeSelected = "24";
                rooter.areaSelected = "";
                rooter.typeSelected = "";
                rooter.marketReleaseDateLevelSelected = "";
                rooter.versionSelected = "";
                rooter.otherSelected = "";
                rooter.finishedSelected = "";
            }
        });
    }

    function dealChildConfig() {
        $.ajax({
            type: "GET",
            url: "/htmls/iqiyi/music-config.json",
            dataType: "json",
            success: function (data) {
                //处理儿童
                rooter.child = data["儿童"];
                rooter.childMode = rooter.child.mode;
                rooter.childArea = rooter.child.area;
                rooter.childType = rooter.child.type;
                rooter.childMarketReleaseDateLevel = rooter.child.marketReleaseDateLevel;
                rooter.childVersion = rooter.child.version;
                rooter.childOther = rooter.child.other;
                rooter.childOther2 = rooter.child.other2;
                rooter.childModeCurrentIndex = 1;
                rooter.childAreaCurrentIndex = 1;
                rooter.childTypeCurrentIndex = 1;
                rooter.childMarketReleaseDateLevelCurrentIndex = 1;
                rooter.childVersionCurrentIndex = 1;
                rooter.childOtherCurrentIndex = 1;
                rooter.childOther2CurrentIndex = 1;

                //重置数据
                rooter.currentChannel = "child";
                rooter.channelIdSelected = "15";
                rooter.modeSelected = "24";
                rooter.areaSelected = "";
                rooter.typeSelected = "";
                rooter.marketReleaseDateLevelSelected = "";
                rooter.versionSelected = "";
                rooter.otherSelected = "";
                rooter.other2Selected = "";
                rooter.finishedSelected = "";
            }
        });
    }

    function dealFashionConfig() {
        $.ajax({
            type: "GET",
            url: "/htmls/iqiyi/fashion-config.json",
            dataType: "json",
            success: function (data) {
                //处理时尚
                rooter.fashion = data["时尚"];
                rooter.fashionMode = rooter.fashion.mode;
                rooter.fashionArea = rooter.fashion.area;
                rooter.fashionType = rooter.fashion.type;
                rooter.fashionMarketReleaseDateLevel = rooter.fashion.marketReleaseDateLevel;
                rooter.fashionVersion = rooter.fashion.version;
                rooter.fashionOther = rooter.fashion.other;
                rooter.fashionModeCurrentIndex = 1;
                rooter.fashionAreaCurrentIndex = 1;
                rooter.fashionTypeCurrentIndex = 1;
                rooter.fashionMarketReleaseDateLevelCurrentIndex = 1;
                rooter.fashionVersionCurrentIndex = 1;
                rooter.fashionOtherCurrentIndex = 1;
                rooter.fashionOther2CurrentIndex = 1;

                //重置数据
                rooter.currentChannel = "fashion";
                rooter.channelIdSelected = "13";
                rooter.modeSelected = "24";
                rooter.areaSelected = "";
                rooter.typeSelected = "";
                rooter.marketReleaseDateLevelSelected = "";
                rooter.versionSelected = "";
                rooter.otherSelected = "";
                rooter.finishedSelected = "";
            }
        });
    }

    function dealLifeConfig() {
        $.ajax({
            type: "GET",
            url: "/htmls/iqiyi/life-config.json",
            dataType: "json",
            success: function (data) {
                //处理生活
                rooter.life = data["生活"];
                rooter.lifeMode = rooter.life.mode;
                rooter.lifeArea = rooter.life.area;
                rooter.lifeType = rooter.life.type;
                rooter.lifeMarketReleaseDateLevel = rooter.life.marketReleaseDateLevel;
                rooter.lifeVersion = rooter.life.version;
                rooter.lifeOther = rooter.life.other;
                rooter.lifeModeCurrentIndex = 1;
                rooter.lifeAreaCurrentIndex = 1;
                rooter.lifeTypeCurrentIndex = 1;
                rooter.lifeMarketReleaseDateLevelCurrentIndex = 1;
                rooter.lifeVersionCurrentIndex = 1;
                rooter.lifeOtherCurrentIndex = 1;
                rooter.lifeOther2CurrentIndex = 1;

                //重置数据
                rooter.currentChannel = "life";
                rooter.channelIdSelected = "21";
                rooter.modeSelected = "24";
                rooter.areaSelected = "";
                rooter.typeSelected = "";
                rooter.marketReleaseDateLevelSelected = "";
                rooter.versionSelected = "";
                rooter.otherSelected = "";
                rooter.finishedSelected = "";
            }
        });
    }

    function dealCarConfig() {
        $.ajax({
            type: "GET",
            url: "/htmls/iqiyi/car-config.json",
            dataType: "json",
            success: function (data) {
                //处理汽车
                rooter.car = data["汽车"];
                rooter.carMode = rooter.car.mode;
                rooter.carArea = rooter.car.area;
                rooter.carType = rooter.car.type;
                rooter.carMarketReleaseDateLevel = rooter.car.marketReleaseDateLevel;
                rooter.carVersion = rooter.car.version;
                rooter.carOther = rooter.car.other;
                rooter.carModeCurrentIndex = 1;
                rooter.carAreaCurrentIndex = 1;
                rooter.carTypeCurrentIndex = 1;
                rooter.carMarketReleaseDateLevelCurrentIndex = 1;
                rooter.carVersionCurrentIndex = 1;
                rooter.carOtherCurrentIndex = 1;
                rooter.carOther2CurrentIndex = 1;

                //重置数据
                rooter.currentChannel = "car";
                rooter.channelIdSelected = "26";
                rooter.modeSelected = "24";
                rooter.areaSelected = "";
                rooter.typeSelected = "";
                rooter.marketReleaseDateLevelSelected = "";
                rooter.versionSelected = "";
                rooter.otherSelected = "";
                rooter.finishedSelected = "";
            }
        });
    }

    function dealAdConfig() {
        $.ajax({
            type: "GET",
            url: "/htmls/iqiyi/ad-config.json",
            dataType: "json",
            success: function (data) {
                //处理广告
                rooter.ad = data["广告"];
                rooter.adMode = rooter.ad.mode;
                rooter.adArea = rooter.ad.area;
                rooter.adType = rooter.ad.type;
                rooter.adMarketReleaseDateLevel = rooter.ad.marketReleaseDateLevel;
                rooter.adVersion = rooter.ad.version;
                rooter.adOther = rooter.ad.other;
                rooter.adModeCurrentIndex = 1;
                rooter.adAreaCurrentIndex = 1;
                rooter.adTypeCurrentIndex = 1;
                rooter.adMarketReleaseDateLevelCurrentIndex = 1;
                rooter.adVersionCurrentIndex = 1;
                rooter.adOtherCurrentIndex = 1;
                rooter.adOther2CurrentIndex = 1;

                //重置数据
                rooter.currentChannel = "ad";
                rooter.channelIdSelected = "20";
                rooter.modeSelected = "24";
                rooter.areaSelected = "";
                rooter.typeSelected = "";
                rooter.marketReleaseDateLevelSelected = "";
                rooter.versionSelected = "";
                rooter.otherSelected = "";
                rooter.finishedSelected = "";
            }
        });
    }

    function dealOriginalConfig() {
        $.ajax({
            type: "GET",
            url: "/htmls/iqiyi/original-config.json",
            dataType: "json",
            success: function (data) {
                //处理原创
                rooter.original = data["原创"];
                rooter.originalMode = rooter.original.mode;
                rooter.originalArea = rooter.original.area;
                rooter.originalType = rooter.original.type;
                rooter.originalMarketReleaseDateLevel = rooter.original.marketReleaseDateLevel;
                rooter.originalVersion = rooter.original.version;
                rooter.originalOther = rooter.original.other;
                rooter.originalModeCurrentIndex = 1;
                rooter.originalAreaCurrentIndex = 1;
                rooter.originalTypeCurrentIndex = 1;
                rooter.originalMarketReleaseDateLevelCurrentIndex = 1;
                rooter.originalVersionCurrentIndex = 1;
                rooter.originalOtherCurrentIndex = 1;
                rooter.originalOther2CurrentIndex = 1;

                //重置数据
                rooter.currentChannel = "original";
                rooter.channelIdSelected = "27";
                rooter.modeSelected = "24";
                rooter.areaSelected = "";
                rooter.typeSelected = "";
                rooter.marketReleaseDateLevelSelected = "";
                rooter.versionSelected = "";
                rooter.otherSelected = "";
                rooter.finishedSelected = "";
            }
        });
    }


    $("input").unbind().keyup(function (event) {
        if (event.keyCode === 13 && $(this).val() !== ""){
            $("a.searchButton").get(0).click();
        }
    });

    $("a.searchButton").unbind().click(function () {
        $(this).attr("href","/iqiyi/searchPage/"+$("input").val());
    });

});