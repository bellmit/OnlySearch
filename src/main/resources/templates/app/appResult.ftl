<!DOCTYPE HTML>
<html>
<head>
    <title>祥龙检索，千度寻--app</title>
    <meta charset="UTF-8"/>
    <meta http-equiv="pragma" content="no-cache"/>
    <meta http-equiv="cache-control" content="no-cache"/>
    <meta http-equiv="expires" content="0"/>
	<meta name="keywords" content="app,千度,千度搜索,千度网,搜索引擎大全,高级搜索,搜索神器,搜索大全"/>
	<meta name="description" content="app,千度,千度一下，你更知道,千度搜索,千度网,搜索引擎大全,高级搜索,搜索神器,搜索大全"/>
    <link rel="shortcut icon" href="/images/logo/index_logo.png" type="image/icon"/>
    <link rel="stylesheet" type="text/css" href="/css/app/appResult.css"/>
    <script type="text/javascript" src="/jss/jquery-3.3.1.min.js"></script>
    <script type="text/javascript" src="/jss/vue.js"></script>
</head>

<body>

<div id="rooter">
	<div style="height: 366px;background-color: rgba(0,0,0,.6);z-index: 99999;">
	    <div class="oneLine">
	        <a title="祥龙检索，搜app"><img src="/images/index/homelogo.png"/></a>
	    </div>
	    <p style="margin: 10px auto;text-align: center;
	position: relative;left: 0px;top: 64px;color: #FFFFFF;">可以搜索app名称等</p>
	    <div class="searchArea">
	        <p><input type="text" title="可以搜索app名称等"/></p>
	        <a>千度app</a>
	    </div>

		<div id="fixed_top">
			<div class="searchArea">
				<p><input type="text" title="可以搜索app名称等"/></p>
				<a>千度app</a>
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
	
	    <div class="classifyUrl">
	        <ul>
				<li v-for="(item , index ) in index_a" :class="item">
					<a :href="'appResult?categoryId=' + categoryIds[index]+'&pageSize='+pageSize+'&pageContext=1'">{{keywords[index]}}</a>
				</li>
	        </ul>
	    </div>
	</div>
	<div class="list-view">
	    <div v-if="apps.length > 0">
			<ul class="appUl">
				<li v-for="(item,index) in apps">
					<p>
						<img :src="item.iconUrl" /><br/>
						<span class="title">{{item.appName}}</span><br/>
						<span class="version">版本：&nbsp;&nbsp;"{{item.versionName}}"</span><br/>
						<span class="size">{{(item.fileSize*1.0/1024/1024).toFixed(2)}}MB</span>
					</p>
					<p>
						<a :href="item.apkUrl">
							<img src="/images/page_result/timg.png"/>
						</a>
					</p>
				</li>
			</ul>
		</div>
		<div v-else style="text-align: center;">
			<img src="/images/multiMusic/loading.gif"/>
		</div>
	
	    <ul class="list">
			<li v-for="(item,index) in indexs" :class="item">
				<a :href="'appResult?categoryId=' + categoryId+'&pageSize='+pageSize+'&pageContext='+(index+1)">{{index + 1}}</a>
			</li>
	    </ul>
	</div>
	<i id="categoryId" style="display: none;">${categoryId}</i>
	<i id="pageSize" style="display: none;">${pageSize}</i>
	<i id="pageContext" style="display: none;">${pageContext1}</i>
</div>
<script type="text/javascript" src="/jss/app/appResult.js"></script>
</body>
</html>
