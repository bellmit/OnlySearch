$(function () {
    /**
     * 处理搜索框的检索功能
     */
    $("input.local").unbind().keyup(function (event) {
        if (event.keyCode === 13 && $(this).val() !== ""){
            $(this).parent().parent().find("a.searchButtonLocal").get(0).click();
        }
    });

    $("input.up").unbind().keyup(function (event) {
        if (event.keyCode === 13 && $(this).val() !== ""){
            $(this).parent().parent().find("a.searchButtonUp").get(0).click();
        }
    });

    $("a.searchButtonLocal").unbind().click(function () {
        if ($("input").val() !== ""){
            $(this).attr("href", "/cloudMusic/searchResult/" + $("input").val());
        }
        else{
            alert("请输入搜索内容");
        }
    });
    $("a.searchButtonUp").unbind().click(function () {
        if ($("input").val() !== ""){
            $(this).attr("href", "/cloudMusic/searchResult/" + $("input").val());
        }
        else{
            alert("请输入搜索内容");
        }
    });
});