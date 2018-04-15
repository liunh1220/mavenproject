<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isErrorPage="false"%> 
<%@include file="/common/include.jsp"%>       
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>error</title>
<!--[if IE 6]>
<script language="JavaScript" type="text/javascript">
if (navigator.platform == "Win32" && navigator.appName == "Microsoft Internet Explorer" && window.attachEvent) {
window.attachEvent("onload", enableAlphaImages);
}

function enableAlphaImages(){
var rslt = navigator.appVersion.match(/MSIE (\d+\.\d+)/, '');
var itsAllGood = (rslt != null && Number(rslt[1]) >= 5.5);
if (itsAllGood) {
for (var i=0; i<document.all.length; i++){
var obj = document.all[i];
var bg = obj.currentStyle.backgroundImage;
var img = document.images[i];
if (bg && bg.match(/\.png/i) != null) {
var img = bg.substring(5,bg.length-2);
var offset = obj.style["background-position"];
obj.style.filter =
"progid:DXImageTransform.Microsoft.AlphaImageLoader(src='"+img+"', sizingMethod='crop')";
obj.style.backgroundImage = "url('loginimages/spacer.gif')";//替换透明PNG的图片

obj.style["background-position"] = offset; // reapply
} else if (img && img.src.match(/\.png$/i) != null) {
var src = img.src;
img.style.width = img.width + "px";
img.style.height = img.height + "px";
img.style.filter =
"progid:DXImageTransform.Microsoft.AlphaImageLoader(src='"+src+"', sizingMethod='crop')"
img.src = "/image/loginimages/spacer.gif";//替换透明PNG的图片

}
}
}
}

</script>
<![endif]-->


<style>
.imgbgtu{background:url(/image/loginimages/error-icon.png) no-repeat;width:44px;height:44px;}
.imgbgtu1{background:url(/image/loginimages/error-icon02.png) no-repeat;width:122px;height:90px;}
.weizhi{display:block;width:226px;height:97px;*height:137px;padding:40px 0 0 40px;font-family:"微软雅黑";font-size:13px;}
.weizhi{background:url(/image/loginimages/error_bg02.png) no-repeat;}
</style>
</head>

<body scroll="no">
<div class="dfasfw">
<div class="error-warp">
<div class="mmi-left">
    <div class="imgbgtu"></div>
    <div class="imgbgtu1" style="margin-top:5px;"></div>
</div>
<div class="mmi-right">
    <span class="weizhi">您所请求的页面未找到！</span>
</div>
<div style="clear:both;"></div>
</div>
</div>
<div class="mmi-bottom"><div style="text-align:right;width:506px;margin:0 auto;"><div style="text-align:right;width:506px;margin:0 auto;"><a href="#" class="easyui-linkbutton" onclick="window.history.back()" iconCls="icon-back">返回</a></div></div>
</body>
</html>