$(function () {

    var keyword = $("i#keyword").text();
    var pageIndex = $("i#pageIndex").text();
    var pageSize = $("i#pageSize").text();

    Vue.use(VueLazyload, {
        error: '/images/bg.gif',
        loading: '/images/bg.gif'
    });

    var rooter = new Vue({
        el: "#rooter",
        data: function () {
            return {
                images: [],
                keyword: keyword
            };
        },
        created: function () {
            var rooter = this;
            $.ajax({
                type: "GET",
                url: "getAllMatchingImages?keyword=" + keyword + "&pageIndex=" + pageIndex + "&pageSize=" + pageSize,
                dataType: "json",
                success: function (data) {
                    var images = data;
                    for (var k = 0; k < images.length; k++) {
                        rooter.images.push(images[k]);
                    }
                }
            });
        }
    });

    rooter.$watch("images",function () {
        $("div.list-view ul.imageUl li").unbind().click(function (event) {
            $("div#bigImage img").attr("src",$(this).find("img").attr("src"));
            var image = new Image();
            image.onload = function(){
                var w = image.width + 100;
                var h = image.height + 100;
                $("div#bigImage").css({
                    width: w + "px",
                    height: h + "px",
                    margin: "-" +( h /2 ) + "px -" + ( w / 2 ) + "px",
                    display: "table"
                });

                $("div#bigImage img").css({
                    width: image.width + "px",
                    height: image.height + "px",
                    margin: "-" +( image.height /2 ) + "px -" + ( image.width / 2 ) + "px",
                    opacity: "1.0"
                });
            };
            image.src = $(this).find("img").attr("src");

            event.stopPropagation();
        });

        $(document).click(function () {
            $("div#bigImage").css("display","none");
        });
    });

    //数据提交
    $("div.searchArea p input").on("keydown", function (event) {
        $("div.searchArea a").attr("href", "imageResult?keyword=" + $(this).val() + "&pageIndex=1&pageSize=60");
        if (event.keyCode === 13 && $(this).val() !== "") {
            window.location = "imageResult?keyword=" + $(this).val() + "&pageIndex=1&pageSize=60";
        }
    });

    $("div.searchArea p input").val(keyword);

    $("div#fixed_top").css("display","none");
    $(document).scroll(function () {
        if ($(this).scrollTop() >= 316){
            $("div#fixed_top").css("display","block");
        }
        if ($(this).scrollTop() <= 244){
            $("div#fixed_top").css("display","none");
        }
    });


    $(window).scroll(function() {
        if ($(document).scrollTop() >= ($(document).height() - $(window).height()) * 0.95) {
            pageIndex ++;
            $.ajax({
                type: "GET",
                url: "getAllMatchingImages?keyword=" + keyword + "&pageIndex=" + pageIndex + "&pageSize=" + pageSize,
                dataType: "json",
                success: function (data) {
                    var images = data;
                    for (var k = 0; k < images.length; k++) {
                        rooter.images.push(images[k]);
                    }
                }
            });
        }
    });

});