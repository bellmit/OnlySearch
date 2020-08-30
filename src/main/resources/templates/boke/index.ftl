<!DOCTYPE HTML>
<html>
<head>
    <title>千度一下</title>
    <meta charset="UTF-8"/>
    <meta http-equiv="pragma" content="no-cache"/>
    <meta http-equiv="cache-control" content="no-cache"/>
    <meta http-equiv="expires" content="0"/>
    <meta name="keywords" content="至尊博客,千度,千度搜索,千度网,搜索引擎大全,高级搜索,搜索神器,搜索大全"/>
    <meta name="description" content="至尊博客,千度一下,千度一下，你就知道,千度搜索,千度网,搜索引擎大全,高级搜索,搜索神器,搜索大全"/>
    <link rel="shortcut icon" href="/images/logo/index_logo.png" type="image/icon"/>
    <link rel="stylesheet" type="text/css" href="/css/boke/index.css"/>
    <script type="text/javascript" src="/jss/jquery-3.3.1.min.js"></script>
    <script type="text/javascript" src="/jss/vue.js"></script>
    <script type="text/javascript" src="/jss/boke/index.js"></script>

</head>

<body>
<h1 style="display: none" alt="至尊博客,千度一下,千度一下，你就知道,千度搜索,千度网"></h1>
<div class="topArea">
    <div class="background">
        <p>
            <a href="/boke/getPages/0/30" class="boke">
                <span>王天龙的博客</span>
            </a>
            <a class="writeBoKe" href="/ueditor/ueditorView/edit/-1" target="_blank">写博客</a>
            <span class="searchButton">搜索</span>
            <input class="searchInput" type="text" placeholder="请输入要查找文章的关键字"/>
        </p>
    </div>
</div>
<div class="content_info">
    <div id="rooter">
        <div class="leftArea">
            <div class="header">
                <img src="/images/logo/index_logo.png"/>
                <span>天龙至尊</span>
                <span>原创：{{totalSize}}</span>
            </div>

            <div class="newBoke">
                <ul>
                    <li class="header">最新文章</li>
                    <#list bokes as item>
                        <#if bokes_index < 5>
                            <li>
                                <a>${item.title}</a>
                            </li>
                        </#if>
                    </#list>
                </ul>
            </div>
        </div>
        <ul>
            <#list bokes as item>
                <li class="item">
                    <div class="line">
                        <a id_="${item.id}">${item.title}</a>
                        <p class="html_small">${item.htmlSmall?html}</p>
                        <p class="time">${item.updateDate}</p>
                        <span class="delete">删除</span>
                        <span class="edit">编辑</span>
                    </div>
                </li>
            </#list>
        </ul>

        <ul class="indexs">
            <li v-for="(item,index) in indexs">
                <span v-html="item"></span>
            </li>
        </ul>
    </div>
</div>
<form action="/boke/showPageInfo" method="post" style="display: none">
    <input type="hidden" name="id"/>
    <input type="submit"/>
</form>
<a id="offset" style="display: none" value="${offset}"></a>
<a id="size" style="display: none" value="${size}"></a>
<div class="footer">
    <a href="/real">千度一下，你更知道</a>
    <p>© 2020 ljxwtl.cn 版权所有 增值电信业务经营许可证：皖ICP备18021122号</p>
</div>
</body>
</html>
