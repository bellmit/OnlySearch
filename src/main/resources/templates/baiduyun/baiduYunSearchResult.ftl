<!DOCTYPE HTML>
<html lang="html5">
<head>

    <title>祥龙检索，千度寻--百度网盘</title>
    <meta charset="UTF-8"/>
    <meta http-equiv="pragma" content="no-cache"/>
    <meta name="referrer" content="no-referrer"/>
    <meta http-equiv="cache-control" content="no-cache"/>
    <meta http-equiv="expires" content="0"/>
	<meta name="keywords" content="${keyword},百度云,千度,千度搜索,千度网,搜索引擎大全,高级搜索,搜索神器,搜索大全"/>
	<meta name="description" content="${keyword},百度云,千度,千度一下，你更知道,千度搜索,千度网,搜索引擎大全,高级搜索,搜索神器,搜索大全"/>
	<link rel="shortcut icon" href="images/logo/index_logo.png" type="image/icon"/>
    <link rel="stylesheet" type="text/css" href="/css/baidu/baiduResult.css"/>
    <script type="text/javascript" src="/jss/jquery-3.3.1.min.js"></script>
	 <script type="text/javascript" src="/jss/vue.js"></script>
</head>

<body>

<div id="rooter">
	<div style="height: 310px;background-color: rgba(0,0,0,.6);
	z-index: 99999;">
	    <div class="oneLine">
	        <a title="祥龙检索，搜迅雷下载资源"><img src="/images/index/homelogo.png"/></a>
	    </div>
	    <p style="margin: 10px auto;text-align: center;
	position: relative;left: 0px;top: 64px;color: #FFFFFF;">可以搜索百度网盘资源名字等，<a href="https://pan.baidu.com/" target="_blank" style="color: red;text-decoration: underline;">请先下载安装百度网盘软件</a></p>
	    <div class="searchArea">
	        <p><input type="text" title="可以搜索百度网盘资源名字等"/></p>
	        <a>千度百度网盘</a>
	    </div>

		<div id="fixed_top">
			<div class="searchArea">
				<p><input type="text" title="可以搜索百度网盘资源名字等"/></p>
				<a>千度百度网盘</a>
			</div>
		</div>
	
	    <div class="items">
	        <ul>
	            <li><a href="/real">回首页</a></li>
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
	    <div v-if="baidus.length > 0">
			<ul class="baiduUl">
				<li v-for="(item , index ) in baidus" :class="index % 2 === 0 ? 'odd' : ''">
					<p class="title">
						<a :href="item.resourceUrl" target='_blank' v-html="item.resourceName"></a>
					</p>
					<p class="description">{{item.resourceTime}} ; {{item.resourceSize}}</p>
				</li>
			</ul>
		</div>
		<div v-else style="text-align: center;">
			<img src="/images/multiMusic/loading.gif"/>
		</div>
	</div>
	<i id="keyword" style="display: none;">${keyword}</i>
</div>
<script type="text/javascript" src="/jss/baidu/baiduResult.js"></script>
</body>
</html>
