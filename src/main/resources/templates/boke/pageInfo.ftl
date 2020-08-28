<!DOCTYPE HTML>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <title>祥龙检索，千度寻--博客</title>
    <meta charset="UTF-8"/>
    <meta http-equiv="pragma" content="no-cache"/>
    <meta http-equiv="cache-control" content="no-cache"/>
    <meta http-equiv="expires" content="0"/>
    <meta name="keywords" th:content="${boke.getTitle()} + ',博客,千度,千度搜索,千度网,搜索引擎大全,高级搜索,搜索神器,搜索大全'"/>
    <meta name="description" th:content="${boke.getTitle()} + ',博客,千度,千度一下，你更知道,千度搜索,千度网,搜索引擎大全,高级搜索,搜索神器,搜索大全'"/>
    <link rel="shortcut icon" href="/images/logo/index_logo.png" type="image/icon"/>
    <link rel="stylesheet" type="text/css" href="/css/boke/pageInfo.css"/>
    <link rel="stylesheet" href="/htmls/ueditor/third-party/SyntaxHighlighter/shCoreDefault.css"/>
    <script type="text/javascript" src="/jss/jquery-3.3.1.min.js"></script>
    <script type="text/javascript" src="/jss/vue.js"></script>
    <script type="text/javascript" charset="utf-8" src="/htmls/ueditor/ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="/htmls/ueditor/ueditor.all.js"></script>
    <script type="text/javascript" src="/htmls/ueditor/third-party/SyntaxHighlighter/shCore.js"></script>
    <script type="text/javascript" src="/jss/boke/pageInfo.js"></script>
    <script type="text/javascript">
        SyntaxHighlighter.all();
    </script>

</head>

<body>
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
    <div id="leftArea">
        <p class="logo">
            <img src="/images/cartoon/header.jpg"/>
            <br/>
            <span class="text">天龙战神</span>
            <br/>
            <span class="text">1050100468@qq.com</span>
        </p>

        <p class="text">
            <a href="https://blog.csdn.net/wtl1992" target="_blank">我的csdn博客</a>
        </p>

        <p class="text">
            Powered by Wang Tianlong!
        </p>

        <p class="text">
            工欲善其事，必先利其器
        </p>
    </div>

    <div id="rooter">
        <h1 th:text="${boke.getTitle()}"></h1>
        <p th:text="${boke.getUpdateDate()}"></p>
        <hr/>
        <div id="html" th:utext="${boke.getHtml()}"></div>
    </div>
</div>
</body>
</html>
