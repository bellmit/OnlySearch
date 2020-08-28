$(function () {

    /**
     * 处理点击事件，进行选择多媒体
     */
    // $("#selectMediaDiv").click(function () {
    //     $("input[type=file]").click();
    // });

    $("button").on('click', function (event) {
        if ($("textarea").val() !== ""){
            // $.ajax({
            //     url: "weiXinUploadMedia/getVoice",
            //     data:{
            //         content : $(this).val()
            //     },
            //     processData:false,  //tell jQuery not to process the data
            //     contentType: false,  //tell jQuery not to set contentType
            //     dataType: "json",
            //     success: function (data) {
            //         if (data != undefined && data.status === "success"){
            //             alert("上传"+type+"成功！！！");
            //         }
            //         else{
            //             alert(type+"上传失败！！！");
            //         }
            //     }
            // });

            $("audio").attr("src","weiXinUploadMedia/getVoice?content="+$("textarea").val());
        }
    });


    $("input[type=button]").click(function () {
        var formData = new FormData();
        formData.append("type","voice");
        formData.append("content",$("textarea").val());
        var type = $("#type").val();
        $.ajax({
            type: "POST",
            url: "weiXinUploadMedia/uploadMedia",
            data:formData,
            processData:false,  //tell jQuery not to process the data
            contentType: false,  //tell jQuery not to set contentType
            dataType: "json",
            success: function (data) {
                if (data != undefined && data.status === "success"){
                    alert("上传"+type+"成功！！！");
                }
                else{
                    alert(type+"上传失败！！！");
                }
            }
        });
    });

});