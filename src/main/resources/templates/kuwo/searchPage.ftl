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
    <link rel="stylesheet" type="text/css" href="/css/kuwo/searchPage.css"/>
    <script type="text/javascript" src="/jss/jquery-3.3.1.min.js"></script>
    <script type="text/javascript" src="/jss/vue.js"></script>
    <script type="text/javascript" src="/jss/vue-lazyload.js"></script>
    <script type="text/javascript" src="/jss/kuwo/searchPage.js"></script>
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

<div id="rooter">
    <div class="indexTop">
        <h2>搜索结果</h2>
        <span class="focus" data-index="0">单曲</span>
        <span data-index="1">专辑</span>
        <span data-index="2">MV</span>
        <span data-index="3">歌单</span>
        <span data-index="4">歌手</span>
    </div>

    <div v-if="currentIndex === 0" class="music">
        <ul>
            <li class="playButton">播放全部</li>
            <li v-for="(item,index) in musicList" class="musicItem">
                <span>{{index + 1}}</span>
                <img v-lazy="item.pic"/>
                <span>{{item.name}}</span>
                <span>{{item.artist}}</span>
                <span>{{item.album}}</span>
                <span>{{item.songTimeMinutes}}</span>
                <span :rid="item.rid" class="play">播放</span>
            </li>
        </ul>
        <audio autoplay controls></audio>
    </div>

    <div v-if="currentIndex === 1" class="album">
        <ul>
            <li v-for="(item,index) in albumList" :albumid="item.albumid">
                <a class="back" :href="'/kuwo/albumInfoPage/' + item.albumid" target="_blank">
                    <img v-lazy="item.pic"/>
                </a>
                <p>{{item.album}}</p>
                <p>{{item.releaseDate}}</p>
                <a class="link" :href="'/kuwo/albumInfoPage/' + item.albumid" target="_blank">
                    <img src="/images/kuwo/play.png"/>
                </a>
            </li>
        </ul>
    </div>

    <div v-if="currentIndex === 2" class="mv">
        <ul>
            <li v-for="(item,index) in mvList">
                <a class="back" :href="'/kuwo/artistMvPage/' + item.id" target="_blank">
                    <img v-lazy="item.pic"/>
                </a>
                <p>{{item.name}}</p>
                <p>{{item.artist}}</p>
                <a class="link" :href="'/kuwo/artistMvPage/' + item.id" target="_blank">
                    <img src="/images/kuwo/play.png"/>
                </a>
            </li>
        </ul>
    </div>

    <div v-if="currentIndex === 3" class="playList">
        <ul>
            <li v-for="(item,index) in searchPlayList">
                <a class="back" :href="'/kuwo/playListInfo/' + item.id" target="_blank">
                    <img v-lazy="item.img"/>
                </a>
                <p>{{item.uname}}</p>
                <p>{{item.name.substring(0,10)}}</p>
                <a class="link" :href="'/kuwo/playListInfo/' + item.id" target="_blank">
                    <img src="/images/kuwo/play.png"/>
                </a>
            </li>
        </ul>
    </div>

    <div v-if="currentIndex === 4" class="artist">
        <ul>
            <li v-for="(item,index) in artistList">
                <a class="back" :href="'/kuwo/singerDetailInfo/' + item.id" target="_blank">
                    <img v-lazy="item.pic"/>
                </a>
                <p v-html="item.name"></p>
                <p>{{item.artistFans}}</p>
            </li>
        </ul>
    </div>
</div>
<input style="display: none;" id="keyword" title="${keyword}"/>
</body>
</html>
