<!DOCTYPE HTML>
<html>
<head>
    <title>祥龙检索，千度寻--漫画</title>
    <meta charset="UTF-8"/>
    <meta http-equiv="pragma" content="no-cache"/>
    <meta http-equiv="cache-control" content="no-cache"/>
    <meta http-equiv="expires" content="0"/>
    <meta name="keywords" content="动漫,动漫专区,千度,千度搜索,千度网,搜索引擎大全,高级搜索,搜索神器,搜索大全"/>
    <meta name="description" content="动漫,动漫专区,千度,千度一下，你更知道,千度搜索,千度网,搜索引擎大全,高级搜索,搜索神器,搜索大全"/>
    <link rel="shortcut icon" href="images/logo/index_logo.png" type="image/icon"/>
    <link rel="stylesheet" type="text/css" href="css/cartoon/pageViews.css"/>
    <script type="text/javascript" src="jss/jquery-3.3.1.min.js"></script>
    <script type="text/javascript" src="jss/vue.js"></script>
    <script type="text/javascript" src="jss/cartoon/pageViews.js"></script>
</head>

<body>

<div id="rooter">
    <p class="top">
        爬虫动漫专区，资源来自于https://m.gufengmh8.com<br/>
        <span>${classfyName}</span>
    </p>

    <div v-if="cartoonPageContents.length > 0">
        <ul class="list">
            <li v-for="(pageContents,index) in cartoonPageContents">
                <a :href="'cartoonContentView?url=' + pageContents.url" target="_blank">
                    <img :src="pageContents.imgUrl" onerror="$(this).parent().parent().remove()"/>
                    <br/>
                    <span v-html="pageContents.title"></span>
                    <br/>
                    <span v-html="pageContents.jiNumber"></span>
                </a>
            </li>
        </ul>
    </div>
    <div v-else style="text-align: center;padding-top: 50px;">
        <img src="/images/multiMusic/loading.gif"/>
    </div>
	

    <ul class="index">
        <li v-for="(item,index) in indexs" :class="item == pageIndex ? 'active' : ''">
            <a :href="'cartoonPageViews?prefixUrl=' + prefixUrl + '&pageIndex='+ item +'&classfyName=' + classfyName"
               target="_blank" v-html="item"></a>
        </li>
    </ul>

    <p style="display: none" title="${prefixUrl}" id="prefixUrl"></p>
    <p style="display: none" title="${pageIndex}" id="pageIndex"></p>
    <p style="display: none" title="${classfyName}" id="classfyName"></p>
</div>
</body>
</html>
