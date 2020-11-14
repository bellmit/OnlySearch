$(function () {
    let lis = $("ul.scrollBar li");
    let i = 0;
    window.setInterval(function () {
        lis.css({
            opacity: 0
        });
        lis.eq(i % lis.length).css({
            opacity: 1
        });
        i++;
    },2000);

    let rooter = new Vue({
        el : "#rooter",
        data : function () {
            return {
                recommends : [] ,
                leaderBoards : [],
                recommendsIndex : 0,
                top12 : []
            };
        },
        created : function () {
            $.ajax({
                type: "GET",
                url: "/kuwo/recommend/1/5",
                dataType: "json",
                success: function (data) {
                    rooter.recommends.push([ "每日推荐", data.data.data]);
                }
            });

            $.ajax({
                type: "GET",
                url: "/kuwo/playlist/1/5/1848",
                dataType: "json",
                success: function (data) {
                    rooter.recommends.push([ "翻唱" , data.data.data]);
                }
            });

            $.ajax({
                type: "GET",
                url: "/kuwo/playlist/1/5/621",
                dataType: "json",
                success: function (data) {
                    rooter.recommends.push([ "网络" , data.data.data]);
                }
            });

            $.ajax({
                type: "GET",
                url: "/kuwo/playlist/1/5/146",
                dataType: "json",
                success: function (data) {
                    rooter.recommends.push([ "伤感" , data.data.data]);
                }
            });

            $.ajax({
                type: "GET",
                url: "/kuwo/playlist/1/5/35",
                dataType: "json",
                success: function (data) {
                    rooter.recommends.push(["欧美" ,data.data.data]);
                }
            });

            /**
             * 排行榜的ajax请求
             */
            $.ajax({
                type: "GET",
                url: "/kuwo/leaderBoard/158/1/5",
                dataType: "json",
                success: function (data) {
                    rooter.leaderBoards.push(["抖音热歌榜" ,data.data.musicList]);
                }
            });

            $.ajax({
                type: "GET",
                url: "/kuwo/leaderBoard/93/1/5",
                dataType: "json",
                success: function (data) {
                    rooter.leaderBoards.push(["酷我新歌榜" ,data.data.musicList]);
                }
            });

            $.ajax({
                type: "GET",
                url: "/kuwo/leaderBoard/279/1/5",
                dataType: "json",
                success: function (data) {
                    rooter.leaderBoards.push(["酷我飙升榜" ,data.data.musicList]);
                }
            });

            $.ajax({
                type: "GET",
                url: "/kuwo/leaderBoard/290/1/5",
                dataType: "json",
                success: function (data) {
                    rooter.leaderBoards.push(["ACG新歌榜" ,data.data.musicList]);
                }
            });

            $.ajax({
                type: "GET",
                url: "/kuwo/leaderBoard/17/1/5",
                dataType: "json",
                success: function (data) {
                    rooter.leaderBoards.push(["酷我热歌榜" ,data.data.musicList]);
                }
            });


            $.ajax({
                type: "GET",
                url: "/kuwo/artistInfo/0/6/6?prefix=",
                dataType: "json",
                success: function (data) {
                    rooter.top12 = data.data.artistList;
                }
            });
        }
    });
    
    rooter.$watch("recommends",function () {
        let recommendAs = $("div.recommend a.recommendItem");
        let recommendLis = $("div.recommend ul li.recommendItem");

        recommendAs.unbind().click(function () {
            let data = rooter.recommends[$(this).attr("index")][1];

            for (let i=0;i<data.length;i++){
                recommendLis.eq(i).attr("_id",data[i].id);
                recommendLis.eq(i).find("a > img").attr("src",data[i].img);
                recommendLis.eq(i).find("p").text(data[i].name);
            }
        });
    });
});