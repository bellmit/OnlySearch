<!DOCTYPE HTML>
<html>
<head>
	<title th:text="'千度一下，搜索结果---' + ${keyword}"></title>
	<meta charset="UTF-8"/>
	<meta http-equiv="pragma" content="no-cache"/>
	<meta http-equiv="cache-control" content="no-cache"/>
	<meta http-equiv="expires" content="0"/>
	<meta http-equiv="Content-Security-Policy" content="upgrade-insecure-requests">
	<meta name="referrer" content="no-referrer"/>
	<meta name="keywords" content="${keyword}千度,千度搜索,千度网,搜索引擎大全,高级搜索,搜索神器,搜索大全"/>
	<meta name="description" content="${keyword}千度,千度一下,你更知道,千度搜索,千度网,搜索引擎大全,高级搜索,搜索神器,搜索大全"/>
	<link rel="shortcut icon" href="/images/logo/index_logo.png" type="image/icon"/>
	<link rel="stylesheet" type="text/css" href="/css/tv/tvSearchResult.css"/>
	<script type="text/javascript" src="/jss/jquery-3.3.1.min.js"></script>
	<script type="text/javascript" src="/jss/vue.js"></script>
	<script type="text/javascript" src="/jss/vue-lazyload.js"></script>
	<script type="text/javascript" src="/jss/tv/tvSearchResult.js"></script>
</head>

<body>

<div id="rooter">
	<div style="height: 316px;background-color: rgba(0,0,0,.6);">
		<div class="oneLine">
			<a title="祥龙检索，搜电视剧"><img src="/images/index/homelogo.png"/></a>
		</div>
		<p style="margin: 10px auto;text-align: center;
	position: relative;left: 0px;top: 64px;color: #FFFFFF;">可以搜索电视剧名称或者演员名字等</p>
		<div class="searchArea">
			<p><input type="text" title="可以搜索电视剧名称或者演员名字等"/></p>
			<a class="searchButton" target="_blank">千度电视剧</a>
		</div>

		<div id="fixed_top">
			<div class="searchArea">
				<p><input type="text" title="可以搜索电视剧名称或者演员名字等"/></p>
				<a>千度电视剧</a>
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
				<li><a href="/baiduyunwangpanSearchResult?keyword=数据库&pageIndex=1">百度网盘</a></li>
			</ul>
		</div>

		<ul class="content">
			<li v-for="(item,index) in tvList">
				<a target="_blank" :href="item.href">
					<img v-lazy="item.imgSrc">
				</a>
				<br/>
				<span class="jiNumber" v-html="item.jiNumber"></span>
				<p v-html="item.title"></p>
			</li>
		</ul>
	</div>
</div>
<a id="keyword" style="display: none" title="${keyword}"></a>
</body>
</html>
