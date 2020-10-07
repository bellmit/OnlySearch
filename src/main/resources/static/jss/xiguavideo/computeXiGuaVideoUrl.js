$(function () {

    let inputDom = $("#rooter input");

    inputDom.unbind().keyup(function (event){
        if (event.keyCode === 13 && $(this).val() !== ""){
            $("a").click();
        }
    });

    $("#rooter a").unbind().click(function () {
        let val = inputDom.val();
        if (val !== ""){
            $("a").attr("href","/xiGua/showVideoUrl?xiGuaUrl=" + val);
        }
    });
});