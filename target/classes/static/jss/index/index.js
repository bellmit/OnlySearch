$(function () {
    var pageIndex = 2;
    var rooter = new Vue({
        el : "#rooter",
        data : function () {
            return {
                calendar : null,
                city : null,
                twoDayWeathers : null,
                showTopDatas : null,
                topDatas : null,
                recommendList : null
            };
        },
        created : function () {
            var rooter = this;
            $.ajax({
                type: "GET",
                url: "/index/getHao123TopWeatherAndCalendar",
                dataType: "json",
                success: function(data){
                    rooter.calendar = data.calendar;
                    rooter.city = data.city;
                    rooter.twoDayWeathers = data.twoDayWeathers;
                }
            });

            //获取百度top值
            $.ajax({
                type: "GET",
                url: "/index/getBaiduTopBuzzList",
                dataType: "json",
                success: function(data){
                    rooter.topDatas = data;
                    var showTopData = [];
                    for (var i=0;i<10;i++){
                        showTopData.push(data[i]);
                    }
                    rooter.showTopDatas = showTopData;
                    console.dir(showTopData)
                }
            });

            //获取推荐列表
            $.ajax({
                type: "GET",
                url: "/index/recommend?pageNumber=2&pageSize=10",
                dataType: "json",
                success: function(data){
                    for (var i=0;i<data.length;i++){
                        data[i].img = data[i].img !== undefined ? data[i].img : "/images/index/noPic.jpg";
                    }
                    rooter.recommendList = data;
                }
            });
        }
    });

    $(window).scroll(function() {
        if ($(document).scrollTop() >= ($(document).height() - $(window).height()) * 0.95) {
            pageIndex ++;
            if (rooter.recommendList === undefined){
                rooter.recommendList = [];
            }
            //获取推荐列表
            $.ajax({
                type: "GET",
                url: "/index/recommend?pageNumber="+pageIndex+"&pageSize=10",
                dataType: "json",
                success: function(data){
                    for (var i=0;i<data.length;i++){
                        data[i].img = data[i].img !== undefined ? data[i].img : "/images/index/noPic.jpg";
                        rooter.recommendList.push(data[i]);
                    }
                }
            });
        }
    });

    $("div#bigArea div.top span.exchange").unbind().click(function () {
        var showTopData = [];
        pageIndex = (++pageIndex) % 5;
        for (var i= 10 * pageIndex;i<10 + 10 * pageIndex;i++){
            if (rooter.topDatas[i] !== undefined){
                showTopData.push(rooter.topDatas[i]);
            }
        }
        rooter.showTopDatas = showTopData;
    });

    //数据提交
    $("div.searchArea input").on("keydown",function (event) {
       $("div.searchArea a").attr("href","result?keyword="+$(this).val()+"&pageIndex=1");
       if (event.keyCode === 13 && $(this).val() !== ""){
           window.location = "result?keyword="+$(this).val()+"&pageIndex=1";
       }
    });
});