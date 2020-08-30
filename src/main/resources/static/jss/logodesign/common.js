$(function () {

    Vue.use(VueLazyload, {
        error: '/images/bg.gif',
        loading: '/images/bg.gif'
    });

    $("span.design").unbind().click(function () {
        let word = $("div.cover input").val();
        if (null !== word && "" !== word){
            window.location = "/logo/logoContent/" + word;
        }
    });

});