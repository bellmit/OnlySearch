<!DOCTYPE HTML>
<html>
<head>
    <title>祥龙检索，千度寻--博客编辑</title>
    <meta charset="UTF-8"/>
    <meta http-equiv="pragma" content="no-cache"/>
    <meta http-equiv="cache-control" content="no-cache"/>
    <meta http-equiv="expires" content="0"/>
    <meta name="keywords" content="富文本,千度,千度搜索,千度网,搜索引擎大全,高级搜索,搜索神器,搜索大全"/>
    <meta name="description" content="富文本,千度,千度一下，你更知道,千度搜索,千度网,搜索引擎大全,高级搜索,搜索神器,搜索大全"/>
    <link rel="shortcut icon" href="/images/logo/index_logo.png" type="image/icon"/>
    <link rel="stylesheet" type="text/css" href="/css/boke/editor.css"/>
    <link rel="stylesheet" href="/htmls/ueditor/third-party/SyntaxHighlighter/shCoreDefault.css"/>
    <script type="text/javascript" src="/jss/jquery-3.3.1.min.js"></script>
    <script type="text/javascript" charset="utf-8" src="/htmls/ueditor/ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="/htmls/ueditor/ueditor.all.js"></script>
    <!--建议手动加在语言，避免在ie下有时因为加载语言失败导致编辑器加载失败-->
    <!--这里加载的语言文件会覆盖你在配置项目里添加的语言类型，比如你在配置项目里配置的是英文，这里加载的中文，那最后就是中文-->
    <script type="text/javascript" charset="utf-8" src="/htmls/ueditor/lang/zh-cn/zh-cn.js"></script>
    <script type="text/javascript" src="/jss/boke/editor.js"></script>
    <script type="text/javascript" src="/htmls/ueditor/third-party/SyntaxHighlighter/shCore.js"></script>
    <script type="text/javascript">
        SyntaxHighlighter.all();
    </script>
</head>

<body>
    <div class="topArea">
        <div class="background">
            <p>
                <a href="/boke/getPages/0/30" class="boke">
                    <span>王天龙的博客</span>
                </a>
                <a class="writeBoKe" href="/ueditor/ueditorView/edit/-1" target="_blank">写博客</a>
                <span class="searchButton">搜索</span>
                <input class="searchInput" type="text" placeholder="请输入要查找文章的关键字"/>
            </p>
        </div>
    </div>
    <div class="input">
        <input id="inputTitle" type="text" placeholder="输入标题"/>
        <script id="editor" type="text/plain" style="width:1024px;height:500px;"></script>
        <div class="fabu">
            <button>发布博客</button>
        </div>
    </div>
<input id="editorType" style="display: none;" title="${editorType}"/>
<input id="id" style="display: none;" title="${id}"/>
</body>
</html>
