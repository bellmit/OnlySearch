$(function () {

    let category = window.parseInt($("input#category").attr("title"));
    let prefix = $("input#prefix").attr("title");
    prefix = prefix === undefined ? "" : prefix;
    let pageIndex = window.parseInt($("input#pageIndex").attr("title"));
    let pageSize = $("input#pageSize").attr("title");
    let otherSize = $("input#otherSize").attr("title");
    let currentPage = $("input#currentPage").attr("title");


    let rooter = new Vue({
        el: "#rooter",
        data: function () {
            return {
                top10 : [],
                otherList : [],
                indexList : [],
                category : category,
                prefix : prefix,
                pageIndex : pageIndex,
                pageSize : pageSize
            };
        },
        created: function () {
            $.ajax({
                type: "GET",
                url: "/kuwo/artistInfo/"+category+ "/"+pageIndex+"/"+otherSize + "?prefix=" + prefix,
                dataType: "json",
                success: function (data) {
                    let total = window.parseInt(data.data.total);

                    let pageNumber = total % pageSize === 0 ? Math.floor(total / pageSize) : Math.floor(total / pageSize) + 1;

                    if (currentPage !== pageNumber){
                        otherSize = pageSize;
                    }

                    $.ajax({
                        type: "GET",
                        url: "/kuwo/artistInfo/"+category+ "/"+pageIndex+"/"+otherSize + "?prefix=" + prefix,
                        dataType: "json",
                        success: function (data) {
                            let artistList = data.data.artistList;
                            let top10 = [];
                            for (let i=0;i<12;i++){
                                top10.push(artistList[i]);
                            }

                            let otherList = [];
                            for (let i=12;i<artistList.length;i++){
                                otherList.push(artistList[i]);
                            }

                            rooter.top10 = top10;
                            rooter.otherList = otherList;

                            let total = window.parseInt(data.data.total);

                            let pageNumber = total % pageSize === 0 ? Math.floor(total / pageSize) : Math.floor(total / pageSize) + 1;

                            console.dir(pageNumber)
                            let i = 0;
                            for (i=0;i<pageNumber - 1;i++){
                                rooter.indexList.push("/kuwo/singer/"+(i+1)+"/"+pageSize + "/" + otherSize + "/" + (i+1) + "?prefix=" + prefix);
                            }

                            if (total % pageNumber !== 0){
                                rooter.indexList.push("/kuwo/singer/"+category+"/"+(i+1)+"/"+pageSize + "/" + otherSize +  "/" + (i+1) + "?prefix=" + prefix);
                            }
                            else{
                                rooter.indexList.push("/kuwo/singer/"+category+"/"+(i+1)+"/"+ pageSize + "/" + (total % pageSize) + "/" + (i+1) + "?prefix=" + prefix);
                            }
                        }
                    });
                }
            });

        }
    });


    rooter.$watch("otherList",function () {
        let hotList = $("ul.hot li.item");
        hotList.unbind().click(function () {
            hotList.removeClass("focus");
            $(this).addClass("focus");
            let a = $(this).find("a");
            pageIndex = 1;
            prefix = a.text();
            a.attr("href","/kuwo/singer/"+category+"/"+pageIndex+"/"+pageSize+"/"+pageSize+"/" + pageSize + "?prefix=" + prefix);
            a.get(0).click();
        });

        let categoryList = $("ul.category li.item");
        categoryList.unbind().click(function () {
            categoryList.removeClass("focus");
            $(this).addClass("focus");
            let a = $(this).find("a");
            pageIndex = 1;
            category =  $(this).attr("index");
            a.attr("href","/kuwo/singer/"+category+"/"+pageIndex+"/"+pageSize+"/"+pageSize+"/" + pageSize + "?prefix=" + prefix);
            a.get(0).click();
        });
    });
});