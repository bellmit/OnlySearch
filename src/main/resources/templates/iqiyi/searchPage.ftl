<!DOCTYPE HTML>
<html>
<head>
    <title>爱奇艺专栏</title>
    <meta charset="UTF-8"/>
    <meta http-equiv="pragma" content="no-cache"/>
    <meta http-equiv="cache-control" content="no-cache"/>
    <meta http-equiv="expires" content="0"/>
    <meta name="keywords" content="爱奇艺,千度,千度搜索,千度网,搜索引擎大全,高级搜索,搜索神器,搜索大全"/>
    <meta name="description" content="爱奇艺,千度,千度一下，你更知道,千度搜索,千度网,搜索引擎大全,高级搜索,搜索神器,搜索大全"/>
    <link rel="shortcut icon" href="/images/logo/index_logo.png" type="image/icon"/>
    <link rel="stylesheet" type="text/css" href="/css/iqiyi/searchPage.css"/>
    <!--
        <link rel="stylesheet" type="text/css" href="styles.css">
        -->
    <script type="text/javascript" src="/jss/jquery-3.3.1.min.js"></script>
    <script type="text/javascript" src="/jss/vue.js"></script>
    <script type="text/javascript" src="/jss/iqiyi/searchPage.js"></script>
</head>

<body>
    <div id="rooter">
        <div class="searchArea">
            <a target="_blank" class="logo" title="祥龙检索，千度一下"><img src="/images/index/homelogo.png"></a>
            <input type="text" placeholder="请入要查询的内容">
            <a target="_blank" class="searchButton">千度一下</a>
        </div>
        <ul class="content">
            <li v-for="(item,index) in videoList">
                <a target="_blank" :href="item.href">
                    <img :src="item.imgSrc">
                </a>
                <br/>
                <span class="jiNumber" v-html="item.jiNumber"></span>
                <p v-html="item.title"></p>
            </li>
        </ul>
    </div>
<input id="keyword" style="display: none;" title="${keyword}"/>
</body>
</html>
