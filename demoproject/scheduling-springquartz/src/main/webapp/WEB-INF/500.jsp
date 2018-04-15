<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isErrorPage="true" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>500</title>
<style>
a{ color:#666; text-decoration:none;}
a:hover{color:#f76900; }
</style>
</head>

<body style="background:url(/images/404_bg.jpg);">
	<div style="width:460px; height:155px; border:1px solid #b9b8bc; margin:250px auto 0 auto;
		font-family:Microsoft yahei; padding:75px 0 0 180px; line-height:30px;">
		<p style="font-size:24px; color:#f13320; margin:0px;">系统繁忙，请您稍候再试</p>
		<p style="margin:0px; color:#666;">给您带来的不便，敬请谅解!</p>
	</div>
</body>
</html>
<%
if (exception != null) {
	out.println("<div style='display:none'>"); out.flush();
	exception.printStackTrace(response.getWriter());
	out.println("</div>");
}
%>