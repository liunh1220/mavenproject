<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>测试页面</title>
</head>
<body>
<h2>user list</h2>
<c:forEach items="${list}" var="va" varStatus="id" begin="0">
	${id.index+1}&nbsp;${va.name}<br>
 </c:forEach>


</body>
</html>
