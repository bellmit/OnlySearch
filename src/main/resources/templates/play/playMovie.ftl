<!DOCTYPE HTML>
<html>
<head>
    <title text="祥龙检索，千度寻---${name}"></title>
    <meta charset="UTF-8"/>
    <meta http-equiv="pragma" content="no-cache"/>
    <meta http-equiv="cache-control" content="no-cache"/>
    <meta http-equiv="expires" content="0"/>
    <meta name="keywords" content="千度,千度搜索,千度网,搜索引擎大全,高级搜索,搜索神器,搜索大全"/>
    <meta name="description" content="千度,千度一下，你更知道,千度搜索,千度网,搜索引擎大全,高级搜索,搜索神器,搜索大全"/>
    <link rel="shortcut icon" href="/images/logo/index_logo.png" type="image/icon"/>
    <link rel="stylesheet" type="text/css" href="/css/play/play.css"/>
    <script type="text/javascript" src="/jss/jquery-3.3.1.min.js"></script>
    <style type="text/css">
        *{
            margin: 0;
            padding: 0;
        }

        html,body,iframe{
            width: 100%;
            height: 100%;
            overflow: hidden;
        }
    </style>
</head>

<body>
<input style="display: none;" id="input" url="${url}"/>
<input style="display: none;" id="name" url="${name}"/>
<iframe></iframe>
<script type="text/javascript">
    $(function () {
        $("iframe").attr("src","https://api.yueliangjx.com/?url=" + $("#input").attr("url"));
    });
</script>
</body>
</html>
