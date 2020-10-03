$(function () {
    let id = window.parseInt($("input#id").attr("title"));
    let pageIndex = 1;

    let types = [
        [["翻唱",1848],["网络",621],["经典",1265],["轻音乐",173],["怀旧",155],
            ["古风",127],["网红",1879],["佛乐",220],["影视",180],["器乐",578],
            ["游戏",1877],["国漫游戏",181],["KTV",361],["喊麦",216],["抖音",2189],
            ["3D",1366],["店铺专用",263],["纯音乐",577]],

        [["伤感",146],["放松",62],["励志",58],["开心",143],["甜蜜",137],
            ["兴奋",139],["安静",67],["治愈",66],["寂寞",147],["思念",160]],

        [["开车",376],["运动",366],["睡前",354],["跳舞",378],["学习",1876],
            ["清晨",353],["夜店",359],["校园",382],["亲热",380],["咖啡店",363],
            ["旅游",375],["散步",371],["工作",386],["婚礼",336]],

        [["70后",637],["80后",638],["90后",639],["00后",640],["10后",268]],

        [["流行",393],["电子",391],["摇滚",389],["民歌",1921],["民谣",392],
            ["古典",390],["嘻哈",387],["乡村",399],["爵士",397],["R&B",394],
            ["世界",396]],

        [["华语",37],["欧美",35],["韩语",1093],["粤语",13],["日语",1091],
            ["小语种",12]]
    ];


    let rooter = new Vue({
        el : "#rooter",
        data : function () {
            return {
                types : types,
                id : id,
                songList : null
            };
        },
        created : function () {
            $.ajax({
                type: "GET",
                url: "/kuwo/tagPlayList/" + id + "/1/30",
                dataType: "json",
                success: function (data) {
                    rooter.songList = data.data.data;
                }
            });
        }
    });

    rooter.$watch("songList",function () {
        let typeSpans = $("ul.types li p span");
        typeSpans.unbind().click(function () {
            typeSpans.removeClass("focus");
            $(this).addClass("focus");
            id = $(this).attr("id");
            let a = $(this).parent();
            a.attr("href","/kuwo/tagPlayListPage/" + id);
            a[0].click();
        });
    });

    $(window).scroll(function() {
        if ($(document).scrollTop() >= ($(document).height() - $(window).height()) * 0.95) {
            pageIndex ++;
            $.ajax({
                type: "GET",
                url: "/kuwo/tagPlayList/" + id + "/"+pageIndex+"/30",
                dataType: "json",
                success: function (data) {
                    let songList = data.data.data;
                    for (let i = 0; i < songList.length; i++) {
                        rooter.songList.push(songList[i]);
                    }
                }
            });
        }
    });
});