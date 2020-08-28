$(function () {
    var ziti = $URL.encode($("a#ziti").attr("title"));
    var rooter = new Vue({
        el: "#rooter",
        data: function () {
            return {
                zitis: null
            };
        },
        created: function () {
            var rooter = this;
            $.ajax({
                type: "GET",
                url: "/logo/designZitiAndPic",
                dataType: "json",
                success: function (data) {
                    for (var i=0;i<data.length;i++){
                        data[i] = data[i].replace("请输入内容",ziti).replace("LOGO副字体",ziti);
                    }
                    rooter.zitis = data;
                }
            });
        }
    });
});