$(function () {
    let id = $("input#id").attr("title");
    let index = $("input#chapterId").attr("title");
    let pageIndex = 1;

    Vue.use(VueLazyload, {
        error: '/images/bg.gif',
        loading: '/images/bg.gif'
    });

    let rooter = new Vue({
        el : "#rooter",
        data : function () {
            return {
                id : id,
                classifyList: null,
                manHuaChapterInfo : null,
                chapterIndex : window.parseInt(index) - 1,
                chapterPictures: null,
                nextChapterId : null
            };
        },
        created : function () {

            $.ajax({
                type: "GET",
                url: "/htmls/tengxun_manhua/tengxun-manhua.json",
                dataType: "json",
                success: function (data) {
                    rooter.classifyList = data;
                }
            });

            $.ajax({
                type: "GET",
                url: "/tengxun_manhua/getTengXunManHuaInfoById/" + id,
                dataType: "json",
                success: function (data) {
                    for (let chapter of data["allChapter"]){
                        for (let key in chapter){
                            let splits = key.split("/");
                            chapter.id = splits[splits.length - 1];
                            chapter.chapterTitle = chapter[key];
                        }
                    }

                    rooter.nextChapterId = data["allChapter"][index]["id"];

                    rooter.manHuaChapterInfo = data;
                }
            });

            $.ajax({
                type: "GET",
                url: "/tengxun_manhua/computeNonceAndData/" + id + "/" + index,
                dataType: "json",
                success: function (data) {
                    rooter.chapterPictures = data.picture;
                }
            });
        }
    });
});