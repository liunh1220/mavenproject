<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="js/jquery-ui-1.12.1/jquery-ui.structure.css" />
<link rel="stylesheet" href="js/jquery-ui-1.12.1/jquery-ui.theme.css" />
<script src="js/jquery-ui-1.12.1/external/jquery/jquery.js"></script>
<script src="js/jquery-ui-1.12.1/jquery-ui.min.js"></script>
<title>Insert title here</title>
</head>
<body>
<a href="/ServletDemo">servlet goGet</a>
<form action="/ServletDemo" method="get"><input id="doPost" type="submit" value="servlet doPost"></form>
<input id="doPut" type="button" value="servlet doPut">
<input id="doDelete" type="button" value="servlet doDelete">

<script>
	$(function(){
		$("#doPut").click(function(){
			$.ajax({
			  type: "put",
			  url: "/ServletDemo",
			});
		});
		$("#doDelete").click(function(){
			$.ajax({
			  type: "delete",
			  url: "/ServletDemo",
			});
		});
		
	});
	
</script>

</body>
</html>