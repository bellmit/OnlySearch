$(function () {
    let word = $("a#word").attr("title");
    let pageIndex = 1;

    let rooter = new Vue({
        el: "#rooter",
        data: function () {
            return {
                logoType: null,
                zitiList: null,
                word: word,
                ziti: null,
                reflectJson: null,
                imageList: null,
                logos: null,
                currentTab : 1,
                colors : null,
                color : null,
                red : "00",
                green : "00",
                blue : "00",
                slideRed : 0,
                slideGreen : 0,
                slideBlue : 0
            };
        },
        created: function () {
            let rooter = this;

            rooter.logos = ["通用图标", "字母图标", "动物图标", "人物图标", "房屋建设",
                "植物图标", "龙凤神兽", "厨师美食", "欧式花纹"];

            $.ajax({
                type: "GET",
                url: "/logo/getAllZitiList",
                dataType: "json",
                success: function (data) {
                    rooter.zitiList = data;
                    rooter.ziti = data[0];

                    rooter.logoType = rooter.logos[0];
                }
            });
        }
    });

    rooter.$watch("logoType",function () {
        let icons = $("div.icon ul li");
        icons.unbind().click(function (event) {
            icons.removeClass("focus");
            $(this).addClass("focus");
            rooter.logoType = $(this).text();
        });

        commonMethod();
    });

    rooter.$watch("ziti", function () {
        let ziTis = $("div.ziti ul li");
        ziTis.unbind().click(function (event) {
            ziTis.removeClass("focus");
            $(this).addClass("focus");
            rooter.ziti = $(this).attr("textfont");
        });

        commonMethod();
    });

    $("input").val(word);

    let slideRed = $("#slideRed");
    let slideGreen = $("#slideGreen");
    let slideBlue = $("#slideBlue");

    slideRed.get(0).onchange = function () {
        changeColor('red',slideRed.val())
    };
    slideGreen.get(0).onchange = function () {
        changeColor('green',slideGreen.val())
    };
    slideBlue.get(0).onchange = function () {
        changeColor('blue',slideBlue.val())
    };


    $("li.iconSelection").unbind().click(function () {
        $("div.icon").css({
            opacity: 1,
            width : "100%",
            height: "200px",
            left: 0,
            top: "200px"
        });
    });

    $("div.icon").unbind().click(function () {
        $(this).css({
            opacity: 0,
            width: 0,
            height: 0,
            left: "50%",
            top: 0
        });
    });

    $("li.zitiSelection").unbind().click(function () {
        $("div.ziti").css({
            opacity: 1,
            width : "100%",
            height: "700px",
            left: 0,
            top: "120px"
        });
    });

    $("div.ziti").unbind().click(function () {
        $(this).css({
            opacity: 0,
            width: 0,
            height: 0,
            left: "50%",
            top: 0
        });
    });

    $("li.colorSelection").unbind().click(function () {
        $("div.color").css({
            opacity: 1,
            width : "100%",
            height: "200px",
            left: 0,
            top: "320px"
        });
    });

    $("div.color").unbind().click(function () {
        $(this).css({
            opacity: 0,
            width: 0,
            height: 0,
            left: "50%",
            top: 0
        });
    });

    function commonMethod() {
        //请求获取设计图案列表
        $.ajax({
            type: "GET",
            url: "/jss/logodesign/reflerct.json",
            dataType: "json",
            success: function (data) {
                rooter.reflectJson = data;
                $.ajax({
                    type: "GET",
                    url: "/logo/getImagesByLogoStyle/" + rooter.logoType + "/" + rooter.reflectJson[rooter.logoType] + "/" + pageIndex,
                    dataType: "json",
                    success: function (data) {
                        let imageList = [];
                        for (let i = 0; i < data.length; i++) {
                            imageList.push("/logo/getLogImageSrc/" + rooter.ziti + "/" + data[i].split("=")[1] + "/" + rooter.color + "/" + rooter.word)
                        }

                        rooter.imageList = imageList;
                    }
                });
            }
        });
    }

    function changeColor(type,value) {
        switch (type) {
            case "red":
                rooter.red = window.parseInt(value).toString(16);
                break;
            case "green":
                rooter.green = window.parseInt(value).toString(16);
                break;
            case "blue":
                rooter.blue = window.parseInt(value).toString(16);
                break;
        }

        rooter.color = rooter.red + rooter.green + rooter.blue;

        commonMethod();
    }

    let flag = true;

    $(window).scroll(function() {
        if ($(document).scrollTop() >= ($(document).height() - $(window).height()) * 0.9) {
            if (flag){
                pageIndex ++;
                flag = false;
                $.ajax({
                    type: "GET",
                    url: "/logo/getImagesByLogoStyle/" + rooter.logoType + "/" + rooter.reflectJson[rooter.logoType] + "/" + pageIndex,
                    dataType: "json",
                    success: function (data) {
                        for (let i = 0; i < data.length; i++) {
                            rooter.imageList.push("/logo/getLogImageSrc/" + rooter.ziti + "/" + data[i].split("=")[1] + "/" + rooter.color + "/" + rooter.word)
                        }
                        flag = true;
                    }
                });
            }

        }
    });
});