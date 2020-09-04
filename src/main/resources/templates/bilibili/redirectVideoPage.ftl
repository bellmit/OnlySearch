<!DOCTYPE HTML>
<html>
<head>
    <title>祥龙检索--哔哩哔哩</title>
    <meta charset="UTF-8"/>
    <meta http-equiv="pragma" content="no-cache"/>
    <meta http-equiv="cache-control" content="no-cache"/>
    <meta http-equiv="expires" content="0"/>
    <meta name="keywords" content="哔哩哔哩,千度,千度搜索,千度网,搜索引擎大全,高级搜索,搜索神器,搜索大全"/>
    <meta name="description" content="哔哩哔哩,千度,千度一下，你更知道,千度搜索,千度网,搜索引擎大全,高级搜索,搜索神器,搜索大全"/>
    <link rel="shortcut icon" href="/images/logo/index_logo.png" type="image/icon"/>
    <link rel="stylesheet" type="text/css" href="/css/bilibili/redirectVideoPage.css"/>
    <script type="text/javascript" src="/jss/jquery-3.3.1.min.js"></script>
    <script type="text/javascript" src="/jss/vue.js"></script>
    <script type="text/javascript" src="/jss/flv.js"></script>
    <script type="text/javascript" src="/jss/bilibili/redirectVideoPage.js"></script>
</head>

<body>
<div id="rooter">
    <h3 v-html="title"></h3>
    <h5 v-html="description"></h5>
    <video autoplay controls></video>
    <div class="rightArea">
        <ul>
            <li class="item" v-for="(item,index) in pageList" :cid="item.cid" :page="item.page" :part="item.part" v-html="item.part"
                :class="currentPage === item.page ? 'focus' : ''"></li>
        </ul>

        <p>
            <a target="_blank">进入下载页面</a>
        </p>
    </div>

</div>
<input style="display: none;" title="${bVid}" id="bVid">
<input style="display: none;" title="${cid}" id="cid">
<input style="display: none;" title="${aid}" id="aid">
</body>
</html>
