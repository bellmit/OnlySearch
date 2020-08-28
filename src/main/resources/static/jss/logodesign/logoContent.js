$(function () {
    var type = $("a#type").attr("title");
    var rooter = new Vue({
        el: "#rooter",
        data: function () {
            return {
                logoImages: null,
                logos: null,
                types: null,
                map: null,
                filterSucais: null
            };
        },
        created: function () {
            var rooter = this;
            $.ajax({
                type: "GET",
                url: "/logo/analysisIndex",
                dataType: "json",
                success: function (data) {
                    rooter.logoImages = data;
                }
            });

            var logos = ["通用图标", "字母图标", "动物图标", "人物图标", "房屋建设",
                "植物图标", "龙凤神兽", "厨师美食"];

            rooter.logos = logos;

            $.ajax({
                type: "GET",
                url: "/logo/getClassfySucaisByLimit/"+type+"/1/60",
                dataType: "json",
                success: function (data) {
                    for (var i=0;i<data.length;i++){
                        data[i].sucaiUrl = "/logo/getSucaiImage?path=" +  data[i].sucaiUrl;
                    }
                    rooter.filterSucais = data;
                    rooter.types = [
                        "logo",
                        "logozm",
                        "logodw",
                        "logorw",
                        "logojz",
                        "logozw",
                        "logoyu",
                        "logokc"
                    ];
                    var map = {};
                    for (var i=0;i<rooter.types.length;i++){
                        map[rooter.types[i]] = rooter.logos[i];
                    }

                    rooter.map = map;
                }
            });
        }
    });

    rooter.$watch("filterSucais",function () {
        $("ul.flex li").click(function () {
            window.location = "/logo/logoContent/" + $(this).attr("data");
        });
    });

    var pageIndex = 0;

    $(window).scroll(function() {
        if ($(document).scrollTop() >= ($(document).height() - $(window).height()) * 0.95) {
            pageIndex ++;
            $.ajax({
                type: "GET",
                url: "/logo/getClassfySucaisByLimit/"+type+"/"+ ( pageIndex * 60 )+"/60",
                dataType: "json",
                success: function (data) {
                    for (var i=0;i<data.length;i++){
                        data[i].sucaiUrl = "/logo/getSucaiImage?path=" +  data[i].sucaiUrl;
                        rooter.filterSucais.push(data[i]);
                    }
                }
            });
        }
    });
});