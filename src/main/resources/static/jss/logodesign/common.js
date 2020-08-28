$(function () {

    $($("#top > div > p > span:nth-child(4)")).click(function () {
        var ziword = $("div.cover input").val();
        console.dir(ziword)
        if (null !== ziword && "" !== ziword){
            window.location = "/logo/zitis/" + ziword;
        }
    });

});