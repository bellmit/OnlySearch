<!DOCTYPE HTML>
<html>
<head>
    <title>免费logo在线制作-字体logo-logo设计</title>
    <meta charset="UTF-8"/>
    <meta http-equiv="pragma" content="no-cache"/>
    <meta http-equiv="cache-control" content="no-cache"/>
    <meta http-equiv="expires" content="0"/>
    <meta name="keywords" content="LOGO,LOGO在线,LOGO在线设计,千度,千度搜索,千度网,搜索引擎大全,高级搜索,搜索神器,搜索大全"/>
    <meta name="description" content="LOGO,LOGO在线,LOGO在线设计,千度,千度一下，你更知道,千度搜索,千度网,搜索引擎大全,高级搜索,搜索神器,搜索大全"/>
    <link rel="shortcut icon" href="/images/logo/index_logo.png" type="image/icon"/>
    <link rel="stylesheet" type="text/css" href="/css/logodesign/logoContent.css"/>
    <script type="text/javascript" src="/jss/jquery-3.3.1.min.js"></script>
    <script type="text/javascript" src="/jss/vue.js"></script>
    <script type="text/javascript" src="/jss/vue-lazyload.js"></script>
    <script type="text/javascript" src="/jss/logodesign/logoContent.js"></script>
    <script type="text/javascript" src="/jss/logodesign/gbk.js"></script>
    <script type="text/javascript" src="/jss/logodesign/common.js"></script>
</head>

<body>
<div id="rooter">
    <div id="top">
        <div class="cover">
            <p>
                <span>完全免费的LOGO在线设计制作工具</span>
                <br/>
                <input type="text" placeholder="LOGO名称"/>
                <span class="design">开始设计</span><br/>
                <span>请输入logo名称</span>
                <span id="logoType">图标类型：{{logoType}}</span>
                <span id="ziti">字体选型：{{ziti}}</span>
            </p>
        </div>
    </div>

    <div class="content">
        <ul class="selection">
            <li class="iconSelection">
                图标类型选择
            </li>
            <li class="zitiSelection">
                字体选择
            </li>
            <li class="colorSelection">
                颜色选择
            </li>
        </ul>

        <div class="icon">
            <h1>请选择免费LOGO设计案例</h1>
            <ul>
                <li v-for="(item,index) of logos" :class="(index + 1) === currentTab ? 'focus' : ''" v-html="item"></li>
            </ul>
        </div>

        <div class="ziti">
            <ul>
                <li v-for="(item,index) in zitiList" :textfont="item" :class="index === 0 ? 'focus' : ''">
                    <img v-lazy="'http://www.uugai.com/logoa/ziphp/ziti_a.php?txt='+word+'&txtfont='+item+'&minitxt='"/>
                </li>
            </ul>
        </div>

        <div class="color">
            <table class="reference">
                <tbody><tr>
                    <th>Red</th>
                    <th>Green</th>
                    <th>Blue</th>
                </tr>
                <tr>
                    <td id="valRed">{{red}}</td>
                    <td id="valGreen">{{green}}</td>
                    <td id="valBlue">{{blue}}</td>
                </tr>
                <tr>
                    <td id="red" style="height:50px;background-color:red"></td>
                    <td id="green" style="background-color:green"></td>
                    <td id="blue" style="background-color:blue"></td>
                </tr>
                <tr>
                    <td>
                        <input type="range" id="slideRed" name="slideRed" min="0" max="255" v-html="slideRed"/>
                    </td>
                    <td>
                        <input type="range" id="slideGreen" name="slideGreen" min="0" max="255" v-html="slideGreen"/>
                    </td>
                    <td>
                        <input type="range" id="slideBlue" name="slideBlue" min="0" max="255" v-html="slideBlue"/>
                    </td>
                </tr>
                </tbody></table>
        </div>
    </div>

    <div class="images">
        <ul>
            <li v-for="(item,index) in imageList">
                <img v-lazy="item"/>
            </li>
        </ul>
    </div>

</div>
<script type="text/javascript">

</script>
<a id="word" style="display: none" title="${word}"></a>
</body>
</html>
