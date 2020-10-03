$(function () {

    $.ajax({
        url: "weiXinGetMedia/getAllMediaByLimit",
        data: {
            pageIndex: 0,
            count: 1000
        },
        dataType: "json",
        success: function (data) {
            var oUl = $("div.container ul");
            var sHtml = "";

            for (var i = 0; i < data.length; i++) {
                sHtml += "<li>\n" +
                    "                <input type=\"text\" value=\""+data[i].id+"\">\n" +
                    "                <input type=\"text\" value=\""+data[i].url+"\">\n" +
                    "                <input type=\"text\" value=\""+data[i].mediaId+"\">\n" +
                    "            </li>";
            }

            oUl.html(oUl.html() + sHtml);
        }
    });

});