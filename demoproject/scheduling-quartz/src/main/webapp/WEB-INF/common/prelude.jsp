<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
<link rel="stylesheet" type="text/css" href="<%=basePath%>js/bootstrap-3.3.7/css/bootstrap.min.css"></link>
<link rel="stylesheet" type="text/css" href="<%=basePath%>js/bootstrap-3.3.7/css/bootstrap-theme.min.css"></link>
<script type="text/javascript" src="<%=basePath%>js/jquery-3.2.0.min.js"></script>
<script type="text/javascript" src="<%=basePath%>js/bootstrap-3.3.7/js/bootstrap.min.js"></script>
<script type="text/javascript" src="<%=basePath%>js/bootstrap-3.3.7/js/npm.js"></script>
