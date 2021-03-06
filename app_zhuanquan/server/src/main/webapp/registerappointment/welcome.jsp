<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>异世谣</title>
  <meta name="apple-mobile-web-app-capable" content="yes"/>
  <meta name="apple-mobile-web-app-status-bar-style" content="black"/>
  <meta name="format-detection" content="telephone=no"/>
  <meta name="format-detection" content="email=no"/>
  <meta name="wap-font-scale" content="no"/>
  <meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1,minimum-scale=1,user-scalable=no">
  <link rel="stylesheet" href="/registerappointment/welcome.css"/>
  <!--[if IE 8]>
  <link rel="stylesheet" href="welcome-ie8.css"/>
  <![endif]-->
</head>
<body>
<div class="cloud1"></div>
<div class="cloud1c"></div>
<div class="cloud2"></div>
<div class="rainbow" id="rainbow"></div>
<div class="bird1" id="bird1"></div>
<div class="bird2 bird-hide" id="bird2"></div>
<div class="bird3 bird-hide" id="bird3"></div>
<div class="logo fn-hide">
  <div class="c">
    <img src="/registerappointment/logo.png"/>
    <a href="javascript:alert('预约活动已结束 异世大门即将开启')" id="yuyue">立即预约</a>
  </div>
</div>
<div class="favor fn-hide">
  <div class="c">
    <img src="/registerappointment/favor.png"/>
    <a href="#" id="favor">收好藏宝图</a>
  </div>
</div>
<div class="cp">
  <p>All Rights Reserved 结梦谷</p>
  <p>浙ICP备17029501号-1</p>
</div>
<audio autoplay="autoplay" loop="loop">
  <source src="http://rhymesland.oss-cn-shanghai.aliyuncs.com/bgm/BGM.mp3" type="audio/mpeg"/>
</audio>
<script>
var redirectUrl='<%=session.getAttribute("redirectUrl")%>';
</script>
<script src="/registerappointment/jquery.js"></script>
<script src="/registerappointment/welcome.js"></script>
</body>
</html>
