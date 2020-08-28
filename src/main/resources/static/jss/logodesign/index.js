$(function () {
    var rooter = new Vue({
        el: "#rooter",
        data: function () {
            return {
                logoImages: null,
                logos: null,
                sucais: null,
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
                url: "/logo/getImageMap",
                dataType: "json",
                success: function (data) {
                    rooter.sucais = data["sucais"];
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

    rooter.$watch("sucais",function () {
        $("ul.flex li").click(function () {
            window.location = "/logo/logoContent/" + $(this).attr("data");
        });

    });
});