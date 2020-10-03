$(function () {
    let pid = $("input#pid").attr("title");
    let currentPageIndex = 0;
    let mvPageIndex = 1;

    Vue.use(VueLazyload, {
        error: '/images/bg.gif',
        loading: '/images/bg.gif'
    });


    let dataArray = [
        ["首播","236682871"],
        ["华语","236682731"],
        ["日韩","236742444"],
        ["网络","236682773"],
        ["欧美","236682735"],
        ["现场","236742576"],
        ["热舞","236682777"],
        ["伤感","236742508"],
        ["剧情","236742578"]
    ];

    let rooter = new Vue({
        el : "#rooter",
        data : function () {
            return {
                mvs : null,
                currentPageIndex : currentPageIndex,
                dataArray : dataArray
            };
        },
        created : function () {
            $.ajax({
                type: "GET",
                url: "/kuwo/mvList/" + pid + "/1/30",
                dataType: "json",
                success: function (data) {
                    rooter.mvs = data.data.mvlist;
                    for (let i=0;i<dataArray.length;i++){
                        if (dataArray[i][1] === pid){
                            rooter.currentPageIndex = i;
                        }
                    }
                }
            });
        }
    });

    let tabs = $("ul.tab li");
    tabs.unbind().click(function () {
        tabs.removeClass("focus");
        $(this).addClass("focus");
        pid = $(this).attr("pid");
        $.ajax({
            type: "GET",
            url: "/kuwo/mvList/" + pid + "/1/30",
            dataType: "json",
            success: function (data) {
                rooter.mvs = data.data.mvlist;
            }
        });
    });

    $(window).scroll(function() {
        if ($(document).scrollTop() >= ($(document).height() - $(window).height()) * 0.9) {
            mvPageIndex++;
            $.ajax({
                type: "GET",
                url: "/kuwo/mvList/" + pid + "/" + mvPageIndex + "/30",
                dataType: "json",
                success: function (data) {
                    let mvs = data.data.mvlist;
                    for (let i = 0; i < mvs.length; i++) {
                        rooter.mvs.push(mvs[i]);
                    }
                }
            });
        }
    });
});