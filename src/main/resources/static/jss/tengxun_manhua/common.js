$(function (){
    $("div#fixed_top").css("display","none");
    $(document).scroll(function () {
        if ($(this).scrollTop() >= 247){
            $("div#fixed_top").css("display","block");
        }
        if ($(this).scrollTop() <= 244){
            $("div#fixed_top").css("display","none");
        }
    });

    $("div.firstInput p input").on("keydown",function (event) {
        if (event.keyCode === 13 && $(this).val() !== ""){
            let oA = $("div.div.firstInput div.searchArea a");
            oA.attr("href","/fiction/fictionSearchResult?keyword="+$(this).val());
            oA.get(0).click();
        }
    });

    $("div.firstInput div.searchArea a").unbind().click(function () {
        $(this).attr("href","/tengxun_manhua/searchPage/" + $("div.firstInput div.searchArea p input").val());
    });

    $("div#fixed_top div.firstInput p input").on("keydown",function (event) {
        if (event.keyCode === 13 && $(this).val() !== ""){
            let oA = $("div#fixed_top div.searchArea a");
            oA.attr("href","/tengxun_manhua/searchPage/" + $("div.firstInput div.searchArea p input").val());
            oA.get(0).click();
        }
    });

    $("div#fixed_top div.searchArea a").unbind().click(function () {
        $(this).attr("href","/fiction/fictionSearchResult?keyword="+$("div#fixed_top div.searchArea p input").val());
    });
});