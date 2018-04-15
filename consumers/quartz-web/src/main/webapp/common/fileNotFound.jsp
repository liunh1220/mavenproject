<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isErrorPage="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>错误提示</title>
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
img.src = "spacer.gif";//替换透明PNG的图片

}
}
}
}

</script>
<![endif]-->
<style>
body{margin:0;padding:0}
.error-warp{width:706px;height:300px;margin:0 auto;padding:0;background:url(loginimages/error_bg01.png) no-repeat;}
.mmi-left{float:left;text-align:center;padding-top:50px;padding-left:120px;}
.mmi-right{float:left;padding-top:40px;}
.button{background:#53B7EA url(loginimages/btn-sprite.png) repeat-x 0 -43px;border:solid 1px #1768B3;padding-top:2px;}
.mmi-bottom{background:url(loginimages/error_bg02.gif) no-repeat center 30px;text-align:center;padding-top:40px;line-height:40px;}
.mmi-bottom img{vertical-align:middle}
.imgbgtu{background:url(loginimages/error-icon.png) no-repeat;width:44px;height:44px;}
.imgbgtu1{background:url(loginimages/error-icon02.png) no-repeat;width:122px;height:90px;}
.weizhi{width:226px;font-family:"微软雅黑";font-size:13px;}
.weizhi{background:url(loginimages/error_bg02.png) no-repeat;}
</style>
</head>

<body scroll="no">
<table class="error-warp" width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td align="left" valign="top"><table width="100%" height="298" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td><table width="50%" height="160" border="0" align="right" cellpadding="0" cellspacing="0">
          <tr>
            <td align="center"><img src="loginimages/error-icon.png" width="44" height="44" /></td>
          </tr>
          <tr>
            <td align="center"><img src="loginimages/error-icon02.png" alt="" width="122" height="90" /></td>
          </tr>
        </table></td>
        <td><table class="weizhi" width="226" height="137" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td valign="top"><table style="margin-top:30px;" width="90%" border="0" align="center" cellpadding="0" cellspacing="0">
              <tr>
                <td>对不起，您下载的附件不存在！</td>
              </tr>
            </table></td>
          </tr>
          
        </table></td>
      </tr>
      
      <tr>
        <td height="50" colspan="2" align="right"><table width="80%" border="0" align="center" cellpadding="0" cellspacing="0">
          <tr>
            <td align="right"><input type="submit" name="button" id="button" value="关 闭" class="button" onclick="window.close()" /></td>
          </tr>
        </table></td>
      </tr>
    </table>
    </td>
  </tr>
</table>
</body>
</html>