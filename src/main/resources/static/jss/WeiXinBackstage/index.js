$(function () {


    $("input[type=button]").click(function () {
        if ($("input[type=text]").val() === ""){
            $("input[type=text]").focus();
        }
        else if ($("input[type=password]").val() === ""){
            $("input[type=password]").focus();
        }
    });


});