$(function () {
    let xiGuaUrl = $("a#xiGuaUrl").attr("title");
    let rooter = new Vue({
        el : "#rooter",
        data : function (){
            return {
                xiGuaVideoUrls : null,
                title : null,
                description : null
            };
        },
        created(){
            $.ajax({
                type: "GET",
                url: "/xiGua/getXiGuaVideoUrl?xiGuaUrl=" + xiGuaUrl,
                dataType: "json",
                success: function(data){
                    rooter.xiGuaVideoUrls = [];
                    for(let i=0;i<data.length - 3 ;i++){
                        rooter.xiGuaVideoUrls.push(data[i]);
                    }
                    rooter.title = data[data.length - 2]["title"];
                    rooter.description = data[data.length - 1]["description"];
                }
            });
        }
    });
});