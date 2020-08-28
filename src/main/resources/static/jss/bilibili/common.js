$(function () {
    rooter.$watch("dynamicList",function () {
       let aids = $("a.aid");
       for (let i=0;i<aids.length;i++){
            aids.eq(i).attr("href","/bilibili/redirectVideoPage/" + aids.eq(i).attr("bvid") + "/" + aids.eq(i).attr("cid") + "/" + aids.eq(i).attr("aid"));
       }
    });

    /**
     * 处理搜索框的检索功能
     */
    $("input").unbind().keyup(function (event) {
        if (event.keyCode === 13 && $(this).val() !== ""){
            window.location = "/bilibili/searchPage?keyword="+$(this).val();
        }
    });

    $("i.searchButton").unbind().click(function () {
        window.location = "/bilibili/searchPage?keyword="+$("input").val();
    });
});