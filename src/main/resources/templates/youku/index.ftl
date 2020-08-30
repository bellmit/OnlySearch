<!DOCTYPE HTML>
<html>
<head>
    <title>祥龙搜索---优酷专栏</title>
    <meta charset="UTF-8"/>
    <meta http-equiv="pragma" content="no-cache"/>
    <meta http-equiv="cache-control" content="no-cache"/>
    <meta http-equiv="expires" content="0"/>
    <meta name="keywords" content="优酷,千度,千度搜索,千度网,搜索引擎大全,高级搜索,搜索神器,搜索大全"/>
    <meta name="description" content="优酷,千度,千度一下，你更知道,千度搜索,千度网,搜索引擎大全,高级搜索,搜索神器,搜索大全"/>
    <link rel="shortcut icon" href="/images/logo/index_logo.png" type="image/icon"/>
    <link rel="stylesheet" type="text/css" href="/css/youku/index.css"/>
    <!--
        <link rel="stylesheet" type="text/css" href="styles.css">
        -->
    <script type="text/javascript" src="/jss/jquery-3.3.1.min.js"></script>
    <script type="text/javascript" src="/jss/vue.js"></script>
    <script type="text/javascript" src="/jss/youku/index.js"></script>
</head>

<body>
    <div id="rooter">
        <div class="searchArea">
            <a target="_blank" class="logo" title="祥龙检索，千度一下"><img src="/images/index/homelogo.png"></a>
            <input type="text" placeholder="请入要查询的内容">
            <a target="_blank" class="searchButton">千度一下</a>
        </div>
        <ul class="main">
            <li class="focus">电视剧</li>
            <li>电影</li>
            <li>综艺</li>
            <li>动漫</li>
            <li>少儿</li>
            <li>音乐</li>
            <li>教育</li>
            <li>纪录片</li>
            <li>体育</li>
            <li>文化</li>
            <li>娱乐</li>
            <li>游戏</li>
            <li>资讯</li>
            <li>搞笑</li>
            <li>生活</li>
        </ul>

        <div v-if="currentChannel === 'tv'">
            <ul class="tv-type">
                <li v-for="(item,index) in tvType" :class="index+1 === tvTypeCurrentIndex ? 'focus' : ''" :index="index + 1">
                    <span v-html="item[0]" :type="item[1]"></span>
                </li>
            </ul>
            <ul class="tv-time">
                <li v-for="(item,index) in tvTime" :class="index+1 === tvTimeCurrentIndex ? 'focus' : ''" :index="index + 1">
                    <span v-html="item[0]" :time="item[1]"></span>
                </li>
            </ul>
            <ul class="tv-area">
                <li v-for="(item,index) in tvArea" :class="index+1 === tvAreaCurrentIndex ? 'focus' : ''" :index="index + 1">
                    <span v-html="item[0]" :area="item[1]"></span>
                </li>
            </ul>

            <ul class="content">
                <li v-for="(item,index) in videoList" :videoId="item.videoId">
                    <a target="_blank" :href="'/youku/showVideo/' + item.videoId + '/' + item.title">
                        <img :src="item.img">
                    </a>
                    <span class="jiNumber" v-html="item.summary"></span>
                    <p v-html="item.title"></p>
                    <p v-html="item.subTitle"></p>
                </li>
            </ul>
        </div>

        <div v-if="currentChannel === 'movie'">
            <ul class="movie-type">
                <li v-for="(item,index) in movieType" :class="index+1 === movieTypeCurrentIndex ? 'focus' : ''" :index="index + 1">
                    <span v-html="item[0]" :type="item[1]"></span>
                </li>
            </ul>
            <ul class="movie-time">
                <li v-for="(item,index) in movieTime" :class="index+1 === movieTimeCurrentIndex ? 'focus' : ''" :index="index + 1">
                    <span v-html="item[0]" :time="item[1]"></span>
                </li>
            </ul>
            <ul class="movie-area">
                <li v-for="(item,index) in movieArea" :class="index+1 === movieAreaCurrentIndex ? 'focus' : ''" :index="index + 1">
                    <span v-html="item[0]" :area="item[1]"></span>
                </li>
            </ul>

            <ul class="content">
                <li v-for="(item,index) in videoList" :videoId="item.videoId">
                    <a target="_blank" :href="'/playMovieWithThreePart?playUrl=https:' + item.videoLink + '&name=' + item.title">
                        <img :src="item.img">
                    </a>
                    <span class="jiNumber" v-html="item.summary"></span>
                    <p v-html="item.title"></p>
                    <p v-html="item.subTitle"></p>
                </li>
            </ul>
        </div>

        <div v-if="currentChannel === 'variety'">
            <ul class="variety-type">
                <li v-for="(item,index) in varietyType" :class="index+1 === varietyTypeCurrentIndex ? 'focus' : ''" :index="index + 1">
                    <span v-html="item[0]" :type="item[1]"></span>
                </li>
            </ul>
            <ul class="variety-time">
                <li v-for="(item,index) in varietyTime" :class="index+1 === varietyTimeCurrentIndex ? 'focus' : ''" :index="index + 1">
                    <span v-html="item[0]" :time="item[1]"></span>
                </li>
            </ul>
            <ul class="variety-area">
                <li v-for="(item,index) in varietyArea" :class="index+1 === varietyAreaCurrentIndex ? 'focus' : ''" :index="index + 1">
                    <span v-html="item[0]" :area="item[1]"></span>
                </li>
            </ul>

            <ul class="content">
                <li v-for="(item,index) in videoList" :videoId="item.videoId">
                    <a target="_blank" :href="'/youku/showVideo/' + item.videoId + '/' + item.title">
                        <img :src="item.img">
                    </a>
                    <span class="jiNumber" v-html="item.summary"></span>
                    <p v-html="item.title"></p>
                    <p v-html="item.subTitle"></p>
                </li>
            </ul>
        </div>

        <div v-if="currentChannel === 'anime'">
            <ul class="anime-type">
                <li v-for="(item,index) in animeType" :class="index+1 === animeTypeCurrentIndex ? 'focus' : ''" :index="index + 1">
                    <span v-html="item[0]" :type="item[1]"></span>
                </li>
            </ul>
            <ul class="anime-time">
                <li v-for="(item,index) in animeTime" :class="index+1 === animeTimeCurrentIndex ? 'focus' : ''" :index="index + 1">
                    <span v-html="item[0]" :time="item[1]"></span>
                </li>
            </ul>
            <ul class="anime-area">
                <li v-for="(item,index) in animeArea" :class="index+1 === animeAreaCurrentIndex ? 'focus' : ''" :index="index + 1">
                    <span v-html="item[0]" :area="item[1]"></span>
                </li>
            </ul>

            <ul class="content">
                <li v-for="(item,index) in videoList" :videoId="item.videoId">
                    <a target="_blank" :href="'/youku/showVideo/' + item.videoId + '/' + item.title">
                        <img :src="item.img">
                    </a>
                    <span class="jiNumber" v-html="item.summary"></span>
                    <p v-html="item.title"></p>
                    <p v-html="item.subTitle"></p>
                </li>
            </ul>
        </div>

        <div v-if="currentChannel === 'child'">
            <ul class="child-type">
                <li v-for="(item,index) in childType" :class="index+1 === childTypeCurrentIndex ? 'focus' : ''" :index="index + 1">
                    <span v-html="item[0]" :type="item[1]"></span>
                </li>
            </ul>
            <ul class="child-time">
                <li v-for="(item,index) in childTime" :class="index+1 === childTimeCurrentIndex ? 'focus' : ''" :index="index + 1">
                    <span v-html="item[0]" :time="item[1]"></span>
                </li>
            </ul>
            <ul class="child-area">
                <li v-for="(item,index) in childArea" :class="index+1 === childAreaCurrentIndex ? 'focus' : ''" :index="index + 1">
                    <span v-html="item[0]" :area="item[1]"></span>
                </li>
            </ul>

            <ul class="content">
                <li v-for="(item,index) in videoList" :videoId="item.videoId">
                    <a target="_blank" :href="'/youku/showVideo/' + item.videoId + '/' + item.title">
                        <img :src="item.img">
                    </a>
                    <span class="jiNumber" v-html="item.summary"></span>
                    <p v-html="item.title"></p>
                    <p v-html="item.subTitle"></p>
                </li>
            </ul>
        </div>

        <div v-if="currentChannel === 'music'" class="music">
            <ul class="music-type">
                <li v-for="(item,index) in musicType" :class="index+1 === musicTypeCurrentIndex ? 'focus' : ''" :index="index + 1">
                    <span v-html="item[0]" :type="item[1]"></span>
                </li>
            </ul>
            <ul class="music-time">
                <li v-for="(item,index) in musicTime" :class="index+1 === musicTimeCurrentIndex ? 'focus' : ''" :index="index + 1">
                    <span v-html="item[0]" :time="item[1]"></span>
                </li>
            </ul>
            <ul class="music-area">
                <li v-for="(item,index) in musicArea" :class="index+1 === musicAreaCurrentIndex ? 'focus' : ''" :index="index + 1">
                    <span v-html="item[0]" :area="item[1]"></span>
                </li>
            </ul>
            <ul class="music-stamp">
                <li v-for="(item,index) in musicStamp" :class="index+1 === musicStampCurrentIndex ? 'focus' : ''" :index="index + 1">
                    <span v-html="item[0]" :stamp="item[1]"></span>
                </li>
            </ul>
            <ul class="music-language">
                <li v-for="(item,index) in musicLanguage" :class="index+1 === musicLanguageCurrentIndex ? 'focus' : ''" :index="index + 1">
                    <span v-html="item[0]" :language="item[1]"></span>
                </li>
            </ul>

            <ul class="content music">
                <li v-for="(item,index) in videoList" :videoId="item.videoId">
                    <a target="_blank" :href="'/playMovieWithThreePart?playUrl=https:' + item.videoLink + '&name=' + item.title">
                        <img :src="item.img">
                    </a>
                    <span class="jiNumber" v-html="item.summary"></span>
                    <p v-html="item.title"></p>
                    <p v-html="item.subTitle"></p>
                </li>
            </ul>
        </div>

        <div v-if="currentChannel === 'edu'">
            <ul class="edu-type">
                <li v-for="(item,index) in eduType" :class="index+1 === eduTypeCurrentIndex ? 'focus' : ''" :index="index + 1">
                    <span v-html="item[0]" :type="item[1]"></span>
                </li>
            </ul>
            <ul class="edu-time">
                <li v-for="(item,index) in eduTime" :class="index+1 === eduTimeCurrentIndex ? 'focus' : ''" :index="index + 1">
                    <span v-html="item[0]" :time="item[1]"></span>
                </li>
            </ul>
            <ul class="edu-area">
                <li v-for="(item,index) in eduArea" :class="index+1 === eduAreaCurrentIndex ? 'focus' : ''" :index="index + 1">
                    <span v-html="item[0]" :area="item[1]"></span>
                </li>
            </ul>

            <ul class="content">
                <li v-for="(item,index) in videoList" :videoId="item.videoId">
                    <a target="_blank" :href="'/youku/showVideo/' + item.videoId + '/' + item.title">
                        <img :src="item.img">
                    </a>
                    <span class="jiNumber" v-html="item.summary"></span>
                    <p v-html="item.title"></p>
                    <p v-html="item.subTitle"></p>
                </li>
            </ul>
        </div>

        <div v-if="currentChannel === 'documentary'">
            <ul class="documentary-type">
                <li v-for="(item,index) in documentaryType" :class="index+1 === documentaryTypeCurrentIndex ? 'focus' : ''" :index="index + 1">
                    <span v-html="item[0]" :type="item[1]"></span>
                </li>
            </ul>
            <ul class="documentary-time">
                <li v-for="(item,index) in documentaryTime" :class="index+1 === documentaryTimeCurrentIndex ? 'focus' : ''" :index="index + 1">
                    <span v-html="item[0]" :time="item[1]"></span>
                </li>
            </ul>
            <ul class="documentary-area">
                <li v-for="(item,index) in documentaryArea" :class="index+1 === documentaryAreaCurrentIndex ? 'focus' : ''" :index="index + 1">
                    <span v-html="item[0]" :area="item[1]"></span>
                </li>
            </ul>

            <ul class="content">
                <li v-for="(item,index) in videoList" :videoId="item.videoId">
                    <a target="_blank" :href="'/youku/showVideo/' + item.videoId + '/' + item.title">
                        <img :src="item.img">
                    </a>
                    <span class="jiNumber" v-html="item.summary"></span>
                    <p v-html="item.title"></p>
                    <p v-html="item.subTitle"></p>
                </li>
            </ul>
        </div>

        <div v-if="currentChannel === 'sport'">
            <ul class="sport-type">
                <li v-for="(item,index) in sportType" :class="index+1 === sportTypeCurrentIndex ? 'focus' : ''" :index="index + 1">
                    <span v-html="item[0]" :type="item[1]"></span>
                </li>
            </ul>
            <ul class="sport-time">
                <li v-for="(item,index) in sportTime" :class="index+1 === sportTimeCurrentIndex ? 'focus' : ''" :index="index + 1">
                    <span v-html="item[0]" :time="item[1]"></span>
                </li>
            </ul>
            <ul class="sport-area">
                <li v-for="(item,index) in sportArea" :class="index+1 === sportAreaCurrentIndex ? 'focus' : ''" :index="index + 1">
                    <span v-html="item[0]" :area="item[1]"></span>
                </li>
            </ul>

            <ul class="content">
                <li v-for="(item,index) in videoList" :videoId="item.videoId">
                    <a target="_blank" :href="'/youku/showVideo/' + item.videoId + '/' + item.title">
                        <img :src="item.img">
                    </a>
                    <span class="jiNumber" v-html="item.summary"></span>
                    <p v-html="item.title"></p>
                    <p v-html="item.subTitle"></p>
                </li>
            </ul>
        </div>

        <div v-if="currentChannel === 'culture'">
            <ul class="culture-time">
                <li v-for="(item,index) in cultureTime" :class="index+1 === cultureTimeCurrentIndex ? 'focus' : ''" :index="index + 1">
                    <span v-html="item[0]" :time="item[1]"></span>
                </li>
            </ul>

            <ul class="content">
                <li v-for="(item,index) in videoList" :videoId="item.videoId">
                    <a target="_blank" :href="'/youku/showVideo/' + item.videoId + '/' + item.title">
                        <img :src="item.img">
                    </a>
                    <span class="jiNumber" v-html="item.summary"></span>
                    <p v-html="item.title"></p>
                    <p v-html="item.subTitle"></p>
                </li>
            </ul>
        </div>

        <div v-if="currentChannel === 'entertainment'">
            <ul class="entertainment-type">
                <li v-for="(item,index) in entertainmentType" :class="index+1 === entertainmentTypeCurrentIndex ? 'focus' : ''" :index="index + 1">
                    <span v-html="item[0]" :type="item[1]"></span>
                </li>
            </ul>
            <ul class="entertainment-time">
                <li v-for="(item,index) in entertainmentTime" :class="index+1 === entertainmentTimeCurrentIndex ? 'focus' : ''" :index="index + 1">
                    <span v-html="item[0]" :time="item[1]"></span>
                </li>
            </ul>
            <ul class="content">
                <li v-for="(item,index) in videoList" :videoId="item.videoId">
                    <a target="_blank" :href="'/youku/showVideo/' + item.videoId + '/' + item.title">
                        <img :src="item.img">
                    </a>
                    <span class="jiNumber" v-html="item.summary"></span>
                    <p v-html="item.title"></p>
                    <p v-html="item.subTitle"></p>
                </li>
            </ul>
        </div>

        <div v-if="currentChannel === 'game'">
            <ul class="game-time">
                <li v-for="(item,index) in gameTime" :class="index+1 === gameTimeCurrentIndex ? 'focus' : ''" :index="index + 1">
                    <span v-html="item[0]" :time="item[1]"></span>
                </li>
            </ul>
            <ul class="content">
                <li v-for="(item,index) in videoList" :videoId="item.videoId">
                    <a target="_blank" :href="'/youku/showVideo/' + item.videoId + '/' + item.title">
                        <img :src="item.img">
                    </a>
                    <span class="jiNumber" v-html="item.summary"></span>
                    <p v-html="item.title"></p>
                    <p v-html="item.subTitle"></p>
                </li>
            </ul>
        </div>

        <div v-if="currentChannel === 'information'" class="information">
            <ul class="information-time">
                <li v-for="(item,index) in informationTime" :class="index+1 === informationTimeCurrentIndex ? 'focus' : ''" :index="index + 1">
                    <span v-html="item[0]" :time="item[1]"></span>
                </li>
            </ul>
            <ul class="content information">
                <li v-for="(item,index) in videoList" :videoId="item.videoId">
                    <a target="_blank" :href="'/playMovieWithThreePart?playUrl=https:' + item.videoLink + '&name=' + item.title">
                        <img :src="item.img">
                    </a>
                    <span class="jiNumber" v-html="item.summary"></span>
                    <p v-html="item.title"></p>
                    <p v-html="item.subTitle"></p>
                </li>
            </ul>
        </div>

        <div v-if="currentChannel === 'funny'" class="funny">
            <ul class="funny-type">
                <li v-for="(item,index) in funnyType" :class="index+1 === funnyTypeCurrentIndex ? 'focus' : ''" :index="index + 1">
                    <span v-html="item[0]" :type="item[1]"></span>
                </li>
            </ul>
            <ul class="funny-time">
                <li v-for="(item,index) in funnyTime" :class="index+1 === funnyTimeCurrentIndex ? 'focus' : ''" :index="index + 1">
                    <span v-html="item[0]" :time="item[1]"></span>
                </li>
            </ul>
            <ul class="content funny">
                <li v-for="(item,index) in videoList" :videoId="item.videoId">
                    <a target="_blank" :href="'/playMovieWithThreePart?playUrl=https:' + item.videoLink + '&name=' + item.title">
                        <img :src="item.img">
                    </a>
                    <span class="jiNumber" v-html="item.summary"></span>
                    <p v-html="item.title"></p>
                    <p v-html="item.subTitle"></p>
                </li>
            </ul>
        </div>

        <div v-if="currentChannel === 'life'" class="life">
            <ul class="life-type">
                <li v-for="(item,index) in lifeType" :class="index+1 === lifeTypeCurrentIndex ? 'focus' : ''" :index="index + 1">
                    <span v-html="item[0]" :type="item[1]"></span>
                </li>
            </ul>
            <ul class="life-time">
                <li v-for="(item,index) in lifeTime" :class="index+1 === lifeTimeCurrentIndex ? 'focus' : ''" :index="index + 1">
                    <span v-html="item[0]" :time="item[1]"></span>
                </li>
            </ul>
            <ul class="content life">
                <li v-for="(item,index) in videoList" :videoId="item.videoId">
                    <a target="_blank" :href="'/playMovieWithThreePart?playUrl=https:' + item.videoLink + '&name=' + item.title">
                        <img :src="item.img">
                    </a>
                    <span class="jiNumber" v-html="item.summary"></span>
                    <p v-html="item.title"></p>
                    <p v-html="item.subTitle"></p>
                </li>
            </ul>
        </div>

    </div>
</body>
</html>
