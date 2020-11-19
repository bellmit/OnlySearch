$(function () {
    let keyword = $("input#keyword").attr("title");
    let pageIndex = 1;

    Vue.use(VueLazyload, {
        error: '/images/bg.gif',
        loading: '/images/bg.gif'
    });

    let rooter = new Vue({
        el : "#rooter",
        data : function () {
            return {
                classifyList: null,
                manhuas : []
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
                url: "/tengxun_manhua/queryByKeyword/" + keyword + "/" + pageIndex + "/30",
                dataType: "json",
                success: function (data) {
                    for (let i=0;i<data.length;i++){
                        rooter.manhuas.push(data[i]);
                        data[i].tags = JSON.parse(data[i].tags);
                        let splits = data[i].manHuaMainUrl.split("/");
                        data[i].id = splits[splits.length -1];
                    }
                }
            });
        }
    });

    let flag = true;
    $(window).scroll(function() {
        if ($(document).scrollTop() >= ($(document).height() - $(window).height()) * 0.95) {
            if (flag){
                pageIndex ++;
                flag = false;
                $.ajax({
                    type: "GET",
                    url: "/tengxun_manhua/queryByKeyword/" + keyword + "/" + pageIndex + "/30",
                    dataType: "json",
                    success: function (data) {
                        for (let i=0;i<data.length;i++){
                            rooter.manhuas.push(data[i]);
                            data[i].tags = JSON.parse(data[i].tags);
                            let splits = data[i].manHuaMainUrl.split("/");
                            data[i].id = splits[splits.length -1];
                        }
                        flag = true;
                    }
                });
            }

        }
    });
});