$(function () {

    var rooter = new Vue({
        el : "#rooter",
        data : function () {
            return {
                ranks : null,
                currentRank : null,
                allRank : null,
                orignRank : null,
                rookieRank : null,
                bangumiRank : null
            };
        },
        created : function () {
            /**
             * 获取全站榜的排名
             */
            $.ajax({
                type: "GET",
                url: "/bilibili/rank/all/0/7/true",
                dataType: "json",
                success: function(data){
                    rooter.currentRank = data.rank.list;
                    rooter.allRank = data.rank.list;
                }
            });
            /**
             * 获取原创榜的排名
             */
            $.ajax({
                type: "GET",
                url: "/bilibili/rank/orign/global/7/true",
                dataType: "json",
                success: function(data){
                    rooter.orignRank = data.result.list;
                    for (let i=0;i<rooter.orignRank.length;i++){
                        rooter.orignRank[i].author = "暂无";
                        rooter.orignRank[i].aid = rooter.orignRank[i].fav;
                        rooter.orignRank[i].pic = rooter.orignRank[i].cover;
                        rooter.orignRank[i].play = rooter.orignRank[i].play_count;
                    }
                }
            });

            /**
             * 获取新人榜的排名
             */
            $.ajax({
                type: "GET",
                url: "/bilibili/rank/rookie/0/7/true",
                dataType: "json",
                success: function(data){
                    rooter.rookieRank = data.rank.list;
                }
            });

            /**
             * 获取新番榜的排名
             */
            $.ajax({
                type: "GET",
                url: "/bilibili/rank/bangumi/global/7/true",
                dataType: "json",
                success: function(data){
                    rooter.bangumiRank = data.result.list;

                    for (let i=0;i<rooter.bangumiRank.length;i++){
                        rooter.bangumiRank[i].author = "暂无";
                        rooter.bangumiRank[i].aid = rooter.bangumiRank[i].fav;
                        rooter.bangumiRank[i].pic = rooter.bangumiRank[i].cover;
                        rooter.bangumiRank[i].play = rooter.bangumiRank[i].play_count;
                    }
                }
            });

        }
    });

    rooter.$watch("currentRank",function () {
        let lis = $("ul.rank-menu li");

        lis.unbind().click(function () {
            let type = $(this).find("span.text").text();
            switch (type) {
                case "全站榜":
                    rooter.currentRank = rooter.allRank;
                    break;
                case "原创榜":
                    rooter.currentRank = rooter.orignRank;
                    break;
                case "新番榜":
                    rooter.currentRank = rooter.bangumiRank;
                    break;
                case "新人榜":
                    rooter.currentRank = rooter.rookieRank;
                    break;
                default:
                    break;
            }

            lis.removeClass("focus");
            $(this).addClass("focus");
        });
    });

});