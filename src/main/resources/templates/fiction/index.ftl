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
    <link rel="stylesheet" type="text/css" href="/css/fiction/index.css"/>
    <script type="text/javascript" src="/jss/jquery-3.3.1.min.js"></script>
    <script type="text/javascript" src="/jss/vue.js"></script>
	<script type="text/javascript" src="/jss/fiction/index.js"></script>
</head>

<body>

<div id="rooter">
	<div style="height: 247px;background-color: rgba(0,0,0,.6);z-index: 99999;" class="firstInput">
	    <div class="oneLine">
	        <a title="祥龙检索，搜小说"><img src="/images/index/homelogo.png"/></a>
	    </div>
	    <p style="margin: 10px auto;text-align: center;
	position: relative;left: 0px;top: 64px;color: #FFFFFF;">可以搜索小说名称或者作者名称等</p>
	    <div class="searchArea">
	        <p><input type="text" title="可以搜索小说名称等" placeholder="搜索小说"/></p>
	        <a>千度小说</a>
	    </div>
	</div>

	<div id="fixed_top">
		<div class="searchArea">
			<p><input type="text" title="可以搜索小说名称或作者名称等"/></p>
			<a>千度小说</a>
		</div>
	</div>


	<div class="content" v-if="fictionList !== null">
		<ul>
			<li v-for="(item,index) in fictionList">
				<a :href="'/fiction/chapter/' + item.id">
					<img :src="item.imgSrc"/>
				</a>
				<span class="title" v-html="item.title"></span>
				<p class="center">
					<span class="author" v-html="item.author"></span> |
					<span class="updateTime" v-html="item.updateTime"></span> |
					<span class="status" v-html="item.status"></span>
				</p>
				<p class="content" v-html="item.introduction"></p>
			</li>
		</ul>
	</div>
    <div v-else style="text-align: center;padding-top: 100px;">
        <img src="/images/multiMusic/loading.gif"/>
    </div>
</div>

</body>
</html>
