$(function () {
    let rid = $("input#rid").attr("title");

    Vue.use(VueLazyload, {
        error: '/images/bg.gif',
        loading: '/images/bg.gif'
    });

    let rooter = new Vue({
        el : "#rooter",
        data : function () {
            return {

            };
        },
        created : function () {
            $.ajax({
                type: "GET",
                url: "/kuwo/url/" + rid,
                success: function (data) {
                    console.dir(data)
                    $("video").attr("src","/kuwo/getVideoStream?rid=" + rid);
                }
            });
        }
    });
});