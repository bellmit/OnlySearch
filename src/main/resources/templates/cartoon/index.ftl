<!DOCTYPE HTML>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <title>祥龙检索，千度寻--漫画</title>
    <meta charset="UTF-8"/>
    <meta http-equiv="pragma" content="no-cache"/>
    <meta http-equiv="cache-control" content="no-cache"/>
    <meta http-equiv="expires" content="0"/>
    <meta name="keywords" content="动漫,动漫专区,千度,千度搜索,千度网,搜索引擎大全,高级搜索,搜索神器,搜索大全"/>
    <meta name="description" content="动漫,动漫专区,千度,千度一下，你更知道,千度搜索,千度网,搜索引擎大全,高级搜索,搜索神器,搜索大全"/>
    <link rel="shortcut icon" href="images/logo/index_logo.png" type="image/icon"/>
    <link rel="stylesheet" type="text/css" href="css/cartoon/index.css"/>
    <!--
        <link rel="stylesheet" type="text/css" href="styles.css">
        -->
    <script type="text/javascript" src="jss/jquery-3.3.1.min.js"></script>
</head>

<body>

<div id="rooter">
    <p class="top">
        爬虫动漫专区，资源来自于https://m.gufengmh8.com
    </p>
	<ul>
        <p class="p_class">按类型</p>
        <li th:each="data : ${data_url_s.get(0)}" class="common_li">
            <p>
                <a th:href="@{'cartoonPageViews?prefixUrl=' + ${data.getUrl()} + '&pageIndex=1' + '&classfyName=' + ${data.getName()}}" th:text="${data.getName()}" target="_blank">
                </a>
            </p>
        </li>
    </ul>
    <br/>
    <ul>
        <p class="p_class">按地区</p>
        <li th:each="data : ${data_url_s.get(1)}" class="common_li">
            <p>
                <a th:href="@{'cartoonPageViews?prefixUrl=' + ${data.getUrl()} + '&pageIndex=1' + '&classfyName=' + ${data.getName()}}" th:text="${data.getName()}" target="_blank">
                </a>
            </p>
        </li>
    </ul>
    <br/>
    <ul>
        <p class="p_class">按剧情</p>
        <li th:each="data : ${data_url_s.get(2)}" class="common_li">
            <p>
                <a th:href="@{'cartoonPageViews?prefixUrl=' + ${data.getUrl()} + '&pageIndex=1' + '&classfyName=' + ${data.getName()}}" th:text="${data.getName()}" target="_blank">
                </a>
            </p>
        </li>
    </ul>
    <br/>
    <ul>
        <p class="p_class">按字母</p>
        <li th:each="data : ${data_url_s.get(3)}" class="common_li">
            <p>
                <a th:href="@{'cartoonPageViews?prefixUrl=' + ${data.getUrl()} + '&pageIndex=1' + '&classfyName=' + ${data.getName()}}" th:text="${data.getName()}" target="_blank">
                </a>
            </p>
        </li>
    </ul>
    <br/>
    <ul>
        <p class="p_class">按进度</p>
        <li th:each="data : ${data_url_s.get(4)}" class="common_li">
            <p>
                <a th:href="@{'cartoonPageViews?prefixUrl=' + ${data.getUrl()} + '&pageIndex=1' + '&classfyName=' + ${data.getName()}}" th:text="${data.getName()}" target="_blank">
                </a>
            </p>
        </li>
    </ul>
</div>
</body>
</html>
