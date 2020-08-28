$(function () {
    let rooter = new Vue({
        el : "#rooter",
        data : function () {
            return {
                bokes : null,
                totalSize : null,
                indexs: null
            };
        },
        created : function () {
            commonAjax();
        }
    });

    function commonAjax(){
        let pageIndex = $("#offset").attr("value");
        let size = $("#size").attr("value");
        $.ajax({
            type: "GET",
            url: "/boke/getgetBokesByLimits/"+pageIndex+"/" + size,
            dataType: "json",
            success: function (data) {
                if (data["bokesByLimits"] !== null){
                    rooter.bokes = data["bokesByLimits"];
                    rooter.totalSize = data["totalSize"];
                    rooter.indexs = [];
                    for (let i=0;i<Math.ceil(window.parseInt(rooter.totalSize) / size );i++){
                        rooter.indexs.push((i+1));
                    }
                }
                else{
                    commonAjax();
                }
            }
        });
    }

    rooter.$watch("bokes",function () {
        $("div.line a").click(function () {
            $("form input").val($(this).attr("id_"));
            $("form").submit();
        });

        $("span.delete").unbind().click(function () {
            let a = $(this).parent().find("a");
            let id = a.attr("id_");
            if (window.confirm("是否真要删除该博客文章吗？" + a.text())){
                $.ajax({
                    type: "GET",
                    url: "/boke/deleteBoKeArticle?id=" + id,
                    dataType: "json",
                    success: function (data) {
                        alert(a.text() + "：删除成功！！！");
                    }
                });
            }
        });

        $("span.edit").unbind().click(function () {
            let a = $(this).parent().find("a");
            let id = a.attr("id_");
            window.location = "/ueditor/ueditorView/update/" + id;
        });
    });

    rooter.$watch("indexs",function () {
        let index = window.parseInt($("a#offset").attr("value"))/30 + 1;
        $("ul.indexs li:nth-of-type("+index+")").addClass("focus");
       $("ul.indexs li").unbind().click(function () {
           window.location = "/boke/getPages/"+((window.parseInt($(this).find("span").text()) - 1)* 30)+"/30";
       });
    });

    //处理查询框
    let input = $("input.searchInput");
    input.focus(function () {
        $(this).css("width",350);
    });
    input.blur(function () {
        $(this).css("width",230);
    });
});