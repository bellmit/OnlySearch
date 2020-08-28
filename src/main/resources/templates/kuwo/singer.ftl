<!DOCTYPE HTML>
<html>
<head>
    <title>祥龙检索-酷我音乐</title>
    <meta charset="UTF-8"/>
    <meta http-equiv="pragma" content="no-cache"/>
    <meta http-equiv="cache-control" content="no-cache"/>
    <meta http-equiv="expires" content="0"/>
    <meta name="keywords" content="酷我音乐,千度,千度搜索,千度网,搜索引擎大全,高级搜索,搜索神器,搜索大全"/>
    <meta name="description" content="酷我音乐,千度,千度一下，你更知道,千度搜索,千度网,搜索引擎大全,高级搜索,搜索神器,搜索大全"/>
    <link rel="shortcut icon" href="/images/logo/index_logo.png" type="image/icon"/>
    <link rel="stylesheet" type="text/css" href="/css/kuwo/singer.css"/>
    <script type="text/javascript" src="/jss/jquery-3.3.1.min.js"></script>
    <script type="text/javascript" src="/jss/vue.js"></script>
    <script type="text/javascript" src="/jss/kuwo/singer.js"></script>
    <script type="text/javascript" src="/jss/kuwo/common.js"></script>
</head>

<body>
<div class="top">
    <div class="innerArea">
        <a href="/kuwo/index" target="_blank">
            <img src="/images/kuwo/index.png"/>
        </a>
        <a href="/kuwo/hotTop"><span>排行榜</span></a>
        <a href="/kuwo/singer/0/1/100/100/100?prefix="><span>歌手</span></a>
        <a href="/kuwo/tagPlayListPage/37"><span>歌单</span></a>
        <a href="/kuwo/mvListPage/236682871"><span>MV</span></a>

        <input type="text" placeholder="搜索音乐/MV/歌单/歌手" maxlength="128"/>
    </div>
</div>
<ul class="hot">
    <li class="title">热门</li>
    <#list hotList as item>
        <li class="${prefix} == ${item} ? 'focus item' : 'item'" index="${item_index}">
            <a target="_blank">${item}</a>
        </li>
    </#list>
</ul>

<ul class="category">
    <li class="title">分类</li>
    <#list categoryList as item>
        <li class="${category} == ${index.index + 1} ? 'focus item' : 'item'" index="${item_index}">
            <a target="_blank">${item}</a>
        </li>
    </#list>
</ul>

<div id="rooter">
    <div v-if="indexList.length > 0">
        <ul>
            <li v-for="item,index in top10">
                <a :href="'/kuwo/singerDetailInfo/' + item.id" target="_blank">
                    <img :src="item.pic"/>
                </a>
                <p v-html="item.aartist === '' ? item.name : item.aartist"></p>
                <p v-html="item.musicNum + '首歌曲'"></p>
            </li>
        </ul>

        <ul class="other">
            <li v-for="item,index in otherList">
                <a :href="'/kuwo/singerDetailInfo/' + item.id" target="_blank">
                    <img :src="item.pic"/>
                </a>
                <p v-html="item.aartist === '' ? item.name : item.aartist"></p>
            </li>
        </ul>

        <ul class="index">
            <li v-for="item,index in indexList" :class="index + 1 === pageIndex ? 'focus' : ''">
                <a :href="item" v-html="index + 1"></a>
            </li>
        </ul>
    </div>
    <div v-else style="text-align: center;">
        <img src="/images/cloudMusic/loading.gif"/>
    </div>
</div>

<input style="display: none;" title="${category}" id="category"/>
<input style="display: none;" title="${prefix}" id="prefix"/>
<input style="display: none;" title="${pageIndex}" id="pageIndex"/>
<input style="display: none;" title="${pageSize}" id="pageSize"/>
<input style="display: none;" title="${otherSize}" id="otherSize"/>
<input style="display: none;" title="${currentPage}" id="currentPage"/>
</body>
</html>
