<!DOCTYPE HTML>
<html>
<head>
    <title>祥龙检索，千度寻</title>
    <meta charset="UTF-8"/>
    <meta http-equiv="pragma" content="no-cache"/>
    <meta http-equiv="cache-control" content="no-cache"/>
    <meta http-equiv="expires" content="0"/>
    <meta name="referrer" content="no-referrer"/>
    <meta name="keywords" content="${keyword},千度,千度搜索,千度网,搜索引擎大全,高级搜索,搜索神器,搜索大全"/>
    <meta name="description" content="${keyword},千度,千度一下，你更知道,千度搜索,千度网,搜索引擎大全,高级搜索,搜索神器,搜索大全"/>
    <link rel="shortcut icon" href="/images/logo/index_logo.png" type="image/icon"/>
    <link rel="stylesheet" type="text/css" href="/css/result/result.css"/>
    <script type="text/javascript" src="/jss/jquery-3.3.1.min.js"></script>
    <script type="text/javascript" src="/jss/result/result.js"></script>
</head>

<body>

<div style="position: fixed;left: 0;top: 0;width: 100%;height: 316px;background-color: rgba(0,0,0,.6);
z-index: 99999;">
    <div class="oneLine">
        <a title="祥龙检索，千度一下"><img src="/images/index/homelogo.png"/></a>
    </div>

    <div class="searchArea">
        <p><input type="text"/></p>
        <a target="_blank">千度一下</a>
    </div>

    <div class="items">
        <ul>
            <li><a href="/">回首页</a></li>
            <li><a href="/tvResult?pageIndex=1">电视剧</a></li>
            <li><a href="/movieResult?pageIndex=1">电影</a></li>
            <li><a href="/appResult?categoryId=-10&pageSize=60&pageContext=1">app</a></li>
            <li><a href="/imageResult?keyword=美女&pageIndex=1&pageSize=60">图片</a></li>
            <li><a href="/musicResult?keyword=音乐&pageIndex=1&pageSize=60">音乐</a></li>
            <li><a href="/getThunderPagingResult?classify=最新影片&pageIndex=1">迅雷下载</a></li>
            <li><a href="/baiduyunwangpanSearchResult?keyword=数据库&pageIndex=1">百度网盘</a></li>
        </ul>
    </div>
</div>
<div class="list-view">
    <ul class="singleUl">
    </ul>

    <ul class="multifyUl">
    </ul>

    <ul class="list">
    </ul>
</div>
<i id="keyword" style="display: none;">${keyword}</i>
<i id="pageIndex" style="display: none;">${pageIndex}</i>

<div class="loadingTop">
    <div class="loading_pic">
        <img src="/images/page_result/loading.gif">
    </div>
</div>
</body>
</html>
