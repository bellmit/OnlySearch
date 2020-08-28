$(function () {
    //处理查询框
    let input = $("input.searchInput");
    input.focus(function () {
        $(this).css("width",350);
    });
    input.blur(function () {
        $(this).css("width",230);
    });
});