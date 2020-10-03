$(function () {
    let input = $("input[placeholder]");
    input.unbind().keyup(function (event) {
        if (event.keyCode === 13 && $(this).val() !== ""){
            window.location = "/kuwo/searchPage/"+input.val();
        }
    });
});