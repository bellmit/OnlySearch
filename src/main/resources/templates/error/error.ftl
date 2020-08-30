<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="pragma" content="no-cache"/>
    <meta http-equiv="cache-control" content="no-cache"/>
    <meta http-equiv="expires" content="0"/>
    <meta name="keywords" content="千度,千度搜索,千度网,搜索引擎大全,高级搜索,搜索神器,搜索大全"/>
    <meta name="description" content="千度,千度一下，你更知道,千度搜索,千度网,搜索引擎大全,高级搜索,搜索神器,搜索大全"/>
    <link rel="shortcut icon" href="/images/logo/index_logo.png" type="image/icon"/>
    <title>祥龙检索-链接不存在</title>
    <style type="text/css">
        *{
            margin: 0;
            padding: 0;
        }

        ul,li{
            list-style: none;
        }

        img {
            vertical-align: top;
        }

        div.oneLine{
            padding-top: 20px;
            text-align: center;
        }

        div.content{
            width: 800px;
            height: 400px;
            margin: 140px auto;
            background-color: #CCCCCC;
            text-align: center;
            padding-top: 30px;
            position: relative;
            left: 0px;
            top: -103px;
        }

        div.content p{
            font-size: 20px;
            text-align: center;
            padding-top: 100px;
            color: #FFF;
        }

        div.oneLine a {
            display: block;
        }


        div.searchArea{
            width: 600px;
            height: 55px;
            line-height: 40px;
            margin: 0 auto;
            margin-top: 28px;
            position: relative;
        }

        div.searchArea p{
            width: 80%;
            height: 100%;
        }

        div.searchArea p input{
            width: 100%;
            height: 100%;
            outline: none;
            font-size: 25px;
            line-height: 55px;
            padding-left: 6px;
            border: 1px solid #FFF;
            transition: all 1s;
        }

        div.searchArea p input:focus{
            border: 1px solid #3385ff;
        }

        div.searchArea a {
            display: inline-block;
            position: relative;
            background-color: #3385ff;
            color: #FFF;
            width: 20%;
            height: 100%;
            right: -6px;
            top: -55px;
            float: right;
            text-align: center;
            line-height: 55px;
            cursor: pointer;
            transition: all 0.5s;
            border: 1px solid #3385ff;
        }

        div.searchArea a:hover{
            font-size: 18px;
            background-color: #1f36ff;
            font-weight: bold;
        }
        div.background-pic{
            position: fixed;
            z-index: -9999999;
            background: url("/images/index/index_background.jpg") no-repeat;
            background-size: cover;
            left: 0;
            right: 0;
        }


        div.items{
            width: 1100px;
            margin: 30px auto;
            position: relative;
            left: 0px;
            top: -41px;
        }

        div.items ul{
            width: 100%;
            overflow: hidden;
            background-color: rgba(0,0,0,.6);
        }

        div.items ul li{
            float: left;
            width: 100px;
            height: 30px;
            line-height: 30px;
            padding: 6px;
            text-align: center;
        }

        div.items ul li a {
            text-decoration: none;
            color: #FFFFFF;
            transition: all 0.5s;
        }

        div.items ul li a:hover {
            color: #ff5c38;
            font-size: 23px;
            text-decoration: underline;
        }
    </style>

    <script type="text/javascript" src="/jss/jquery-3.3.1.min.js"></script>
</head>
<body>
<div class="background-pic">
</div>
<div class="oneLine">
    <a title="祥龙检索，千度一下"><img src="/images/index/homelogo.png"/></a>
</div>

<div class="searchArea">
    <p><input type="text"/></p>
    <a target="_blank">千度一下</a>
</div>

<div class="items">
    <ul>
        <li><a href="/real">回首页</a></li>
        <li><a href="/showTvList?offset=0&pageSize=60">电视剧</a></li>
        <li><a href="/movieResult?pageIndex=1">电影</a></li>
        <li><a href="/appResult?categoryId=-10&pageSize=60&pageContext=1">app</a></li>
        <li><a href="/imageResult?keyword=美女&pageIndex=1&pageSize=60">图片</a></li>
        <li><a href="/musicResult?keyword=音乐&pageIndex=1&pageSize=20">音乐</a></li>
        <li><a href="/baiduyunwangpanSearchResult?keyword=数据库&pageIndex=1">百度网盘</a></li>
    </ul>
</div>

<div class="content">
    <img src="/images/error_html/errorImg_b3a6155.png"/>
    <p>
        页面找不到了......
    </p>
</div>

<script type="text/javascript">
    $(function () {
        $("div.background-pic").css({
            width : window.innerWidth + "px",
            height : window.innerHeight + "px"
        });

        $(window).on("resize",function () {
            $("div.background-pic").css({
                width : window.innerWidth + "px",
                height : window.innerHeight + "px"
            });
        });


        //数据提交
        $("div.searchArea p input").on("keydown",function (event) {
            $("div.searchArea a").attr("href","../result?keyword="+$(this).val()+"&pageIndex=1");
            if (event.keyCode === 13 && $(this).val() !== ""){
                window.location = "/result?keyword="+$(this).val()+"&pageIndex=1";
            }
        });
    });
</script>
</body>
</html>