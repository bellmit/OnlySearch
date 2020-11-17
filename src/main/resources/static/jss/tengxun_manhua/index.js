$(function () {
    let classify = $("input#classify").attr("title");
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
                url: "/tengxun_manhua/getTengXunManHuaList/" + classify + "/" + pageIndex,
                dataType: "json",
                success: function (data) {
                    for (let i=0;i<data.length;i++){
                        rooter.manhuas.push(data[i]);
                        let splits = data[i].manHuaMainUrl.split("/");
                        data[i].id = splits[splits.length -1];
                    }
                }
            });
        }
    });

    rooter.$watch("classifyList",function () {
       let lis = $("div.tags div.classify ul li");
       for (let li of lis){
           if (classify === $(li).attr("type")){
               $(li).addClass("focus");
               break;
           }
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
                    url: "/tengxun_manhua/getTengXunManHuaList/" + classify + "/" + pageIndex,
                    dataType: "json",
                    success: function (data) {
                        for (let i=0;i<data.length;i++){
                            rooter.manhuas.push(data[i]);
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