<!DOCTYPE HTML>
<html>
<head>
    <title>祥龙检索，千度寻--小说</title>
    <meta charset="UTF-8"/>
    <meta http-equiv="pragma" content="no-cache"/>
    <meta http-equiv="cache-control" content="no-cache"/>
    <meta http-equiv="expires" content="0"/>
	<meta name="keywords" content="小说,千度,千度搜索,千度网,搜索引擎大全,高级搜索,搜索神器,搜索大全"/>
	<meta name="description" content="小说,千度,千度一下，你更知道,千度搜索,千度网,搜索引擎大全,高级搜索,搜索神器,搜索大全"/>
    <link rel="shortcut icon" href="/images/logo/index_logo.png" type="image/icon"/>
    <link rel="stylesheet" type="text/css" href="/css/fiction/chapter.css"/>
    <script type="text/javascript" src="/jss/jquery-3.3.1.min.js"></script>
    <script type="text/javascript" src="/jss/vue.js"></script>
	<script type="text/javascript" src="/jss/fiction/chapter.js"></script>
</head>

<body>

<div id="rooter">
	<div v-if="chapterList !== null">
		<div class="content" v-for="(item,index) in currentChapters">
			<h2 v-html="item.title"></h2>
			<p class="author">
				<span v-html="'作者：' + item.author + '| 字数：' + item.wordCount + '| 更新时间：' + item.updateTime"></span>
			</p>
			<div class="innerHtml" v-html="item.content"></div>
		</div>
	</div>
    <div v-else style="text-align: center;padding-top: 100px;">
        <img src="/images/multiMusic/loading.gif"/>
    </div>
</div>
<input id="id" style="display: none;" title="${id}"/>
</body>
</html>
