$(function () {
    window.article = {
        picUrls : []
    };
    var ue = UE.getEditor('editor');
    let editorType = $("#editorType").attr("title");
    let id = $("#id").attr("title");
    if (editorType === "update"){
        ue.addListener( 'ready', function() {
            $.ajax({
                url : "/boke/getBoKeHtmlByUuid?id=" + id,
                contentType: 'application/json; charset=UTF-8',
                dataType : "json",
                success : function (data) {
                    ue.execCommand('insertHtml', data["boKeHtml"]);
                    $("#inputTitle").val(data["title"]);
                }
            });
        } );
    }

    /**
     * 处理文章发布的事件
     */
    $("body > div.input > div.fabu > button").click(function () {
        var html = ue.getContent();
        window.article.html = html;
        window.article.title = $("div.input input").val();
        if (!window.article.title || !window.article.html){
            alert("没有写标题或内容");
            return;
        }
        if (editorType === "edit"){
            $.ajax({
                url : "/ueditor/uploadArticle",
                type : "POST",
                data : JSON.stringify(window.article),
                contentType: 'application/json; charset=UTF-8',
                dataType : "json",
                success : function () {
                    window.location = "/boke/getPages/0/30";
                }
            });
        }
        else{
            window.article.id = id;

            console.dir(window.article)
            $.ajax({
                url : "/ueditor/updateArticle",
                type : "POST",
                data : JSON.stringify(window.article),
                contentType: 'application/json; charset=UTF-8',
                dataType : "json",
                success : function () {
                    window.location = "/boke/getPages/0/30";
                }
            });
        }
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