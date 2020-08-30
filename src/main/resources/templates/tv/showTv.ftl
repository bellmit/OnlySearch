<!DOCTYPE HTML>
<html>
<head>
	<title text="电视剧：${tvName}"></title>
	<meta charset="UTF-8"/>
	<meta http-equiv="pragma" content="no-cache"/>
	<meta http-equiv="cache-control" content="no-cache"/>
	<meta http-equiv="expires" content="0"/>
	<meta http-equiv="Content-Security-Policy" content="upgrade-insecure-requests">
	<meta name="referrer" content="no-referrer"/>
	<meta name="keywords" content="${tvName}千度,千度搜索,千度网,搜索引擎大全,高级搜索,搜索神器,搜索大全"/>
	<meta name="description" content="${tvName}千度,千度一下,你更知道,千度搜索,千度网,搜索引擎大全,高级搜索,搜索神器,搜索大全"/>
	<link rel="shortcut icon" href="/images/logo/index_logo.png" type="image/icon"/>
	<link rel="stylesheet" type="text/css" href="/css/tv/tvShow.css"/>
	<script type="text/javascript" src="/jss/jquery-3.3.1.min.js"></script>
	<script type="text/javascript" src="/jss/vue.js"></script>
	<script type="text/javascript" src="/jss/tv/showTv.js"></script>
</head>

<body>

<div id="rooter">
	<div class="list-view" v-if="tvPlayList.length > 0">
		<h2>${tvName}</h2>
		<iframe></iframe>
		<ul class="tvUl">
			<li v-for="(item,index) in tvPlayList" :url="item.url" v-html="item.index" :title="item.name">
			</li>
		</ul>
	</div>
	<div v-else style="text-align: center;margin-top: 50px;">
		<img src="/images/multiMusic/loading.gif"/>
	</div>
</div>
<a id="url" style="display: none" title="${url}"></a>
<a id="aid" style="display: none" title="${aid}"></a>
<a id="platform" style="display: none" title="${platform}"></a>
<a id="tvName" style="display: none" title="${tvName}"></a>
</body>
</html>