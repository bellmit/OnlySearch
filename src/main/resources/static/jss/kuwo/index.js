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
                recommendTypes : ["每日推荐","翻唱","网络","伤感","欧美"],
                //每日推荐
                recommend : null,
                //翻唱
                playlistCover : null,
                //网络
                playlistNet : null,
                //伤感
                playlistSad : null,
                //欧美
                playlistEurope : null,
                top12 : []
            };
        },
        created : function () {
            $.ajax({
                type: "GET",
                url: "/kuwo/recommend/1/5",
                dataType: "json",
                success: function (data) {
                    rooter.recommend = data.data.data;
                }
            });

            $.ajax({
                type: "GET",
                url: "/kuwo/playlist/1/5/1848",
                dataType: "json",
                success: function (data) {
                    rooter.playlistCover = data.data.data;
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
    
    
    let recommendAs = $("div.recommend a.recommendItem");
    let recommendLis = $("div.recommend ul li.recommendItem");
    recommendAs.unbind().click(function () {
        let data = JSON.parse($(this).attr("data"));

        for (let i=0;i<data.length;i++){
            recommendLis.eq(i).attr("_id",data[i].id);
            recommendLis.eq(i).find("a > img").attr("src",data[i].imgSrc);
            recommendLis.eq(i).find("p").text(data[i].name);
        }

    });
});