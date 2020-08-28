$(function () {
    new Vue({
        el: "#rooter",
        data: function () {
            return {
                textList: null,
                chapterList: null
            };
        },
        created: function () {
            var url = $("#url").attr("title");
            var _this = this;
            $.ajax({
                type: "GET",
                url: "cartoonContent",
                data: {
                    "url": url
                },
                dataType: "json",
                success: function (data) {
                    _this.textList = data.textList;
                    _this.chapterList = data.chapterList;

                    $("p.next").css("display", "block");

                    /**
                     * 处理按钮的点击事件，切换图片
                     */
                    window.index = 0;
                    var pNextClickFunc = function () {
                        $("div.chapter_index_image img").attr("src", "images/cartoon/loading.gif");
                        if (window.index >= data.chapterList.length) {
                            alert("已经到最后一章了。");
                        } else {
                            $.ajax({
                                type: "GET",
                                url: "getChapterToAImage",
                                data: {
                                    "chapterUrl": data.chapterList[window.index].url,
                                    "index": window.chapter_index
                                },
                                dataType: "json",
                                success: function (_data) {
                                    if (_data["imgSrc"] !== '404') {
                                        if (window.index < data.chapterList.length) {
                                            var image = new Image();
                                            image.onload = function () {
                                                $("div.chapter_index_image img").attr("src", image.src);
                                                window.chapter_index++;
                                                $("p.next").text("下一个漫画");
                                            };
                                            image.onerror = function(){
                                                $("p.next").text("重新加载");
                                            };
                                            image.src = _data["imgSrc"];
                                        }
                                    } else {
                                        window.chapter_index = 1;
                                        window.index++;
                                        $("div.chapters > p:nth-of-type(" + (window.index + 1) + ")").click();
                                    }
                                },
                                error: function () {
                                    $("p.next").unbind(pNextClickFunc()).click(pNextClickFunc);
                                }
                            });
                        }
                    };
                    window.chapter_index++;
                    $("p.next").click(pNextClickFunc);
                    //处理下一界面按钮的动画事件
                    $("p.next").mousedown(function () {
                        $(this).attr("id","mousedown");

                        $(this).mouseup(function () {
                            $(this).attr("id","");
                        });
                    });
                    $.ajax({
                        type: "GET",
                        url: "getChapterToAImage",
                        data: {
                            "chapterUrl": data.chapterList[0].url,
                            "index": window.chapter_index
                        },
                        dataType: "json",
                        success: function (data) {
                            if (data["imgSrc"] !== '404') {
                                var image = new Image();
                                image.onload = function () {
                                    $("div.chapter_index_image img").attr("src", image.src);
                                    window.chapter_index++;
                                    $("p.next").text("下一个漫画");
                                };
                                image.onerror = function(){
                                  $("p.next").text("重新加载");
                                };
                                image.src = data["imgSrc"];
                            }
                        }
                    });
                }
            });
        }
    });
});