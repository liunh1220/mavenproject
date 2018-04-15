<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>login page</title>
<%@ include file="/common/include.jsp"%> 
<LINK href="css/User_Login.css" type=text/css rel=stylesheet>
<META http-equiv=Content-Type content="text/html; charset=utf-8">

<script language="javascript">
// 页面加载时调用
$(function()
{
	$('#loginName').focus();
	
	$.ajax({
		type : "post",
		url : "/indxe.do",
		async:false
	});
	
	freshVCode();
});

// 回车提交表单
function KeyDown()
{
    if (event.keyCode == 13)
    {
        event.returnValue=false;
        event.cancel = true;
        $('#form1').submit();
    }
}

function submitForm()
{
	$('#form1').submit();
}

//刷新验证码
function freshVCode()
{
	document.getElementById("validateCodeImg").src="validateCode.jpg?timestamp="+new Date().getTime();
}
</script>

</HEAD>
<BODY id=userlogin_body>
	<FORM id="form1" action="login.do" method="post">
	<input type="hidden" name="token" value="${token}">   <!-- 传递一个令牌值 防止重复提交--> 
	<DIV></DIV>

	<DIV id=user_login>
		<DL>
			<DD id=user_top>
				<UL>
					<LI class=user_top_l></LI>
					<LI class=user_top_c></LI>
					<LI class=user_top_r></LI>
				</UL>
			<DD id=user_main>
				<UL>
					<LI class=user_main_l></LI>
					<LI class=user_main_c>
						<DIV class=user_main_box>
								<UL>
									<LI class=user_main_text>用户名：</LI>
									<LI class=user_main_input>
									<INPUT class="TxtUserNameCssClass"
										id="loginName" maxLength=20 name="name" value="${obj.name }"/>
									</LI>
								</UL>
								<UL>
									<LI class=user_main_text>密码：</LI>
									<LI class=user_main_input><INPUT class="TxtPasswordCssClass"
										id="password" type=password name="password" value="${obj.password }"/>
									</LI>
								</UL>
								<UL>
									<LI class=user_main_text>验证码：</LI>
									<LI class=user_main_input>
									<INPUT class="TxtValidateCodeCssClass" id="validateCode" maxLength=20 name="validateCode" />
									<img id="validateCodeImg" alt="点击刷新" align="top" height="22" width="60"/>
                            		<a href="#" onclick="freshVCode()"><span class="login_txt">刷新</span></a>
									</LI>
								</UL>
						</DIV>
					</LI>
					<LI class=user_main_r><INPUT
						style="BORDER-TOP-WIDTH: 0px; BORDER-LEFT-WIDTH: 0px; BORDER-BOTTOM-WIDTH: 0px; BORDER-RIGHT-WIDTH: 0px"
						onclick='javascript:submitForm()'
						type=image src="image/login/user_botton.gif">
					</LI>
				</UL>
			<DD id=user_bottom>
				<UL>
					<LI class=user_bottom_l></LI>
					<LI class=user_bottom_c></LI>
					<LI class=user_bottom_r></LI>
				</UL>
			</DD>
		</DL>
	</DIV>
	<SPAN id=ValrUserName style="DISPLAY: none; COLOR: red"></SPAN>
	<SPAN id=ValrPassword style="DISPLAY: none; COLOR: red"></SPAN>
	<SPAN id=ValrValidateCode style="DISPLAY: none; COLOR: red"></SPAN>
	<DIV id=ValidationSummary1 style="DISPLAY: none; COLOR: red"></DIV>

	<DIV></DIV>

	</FORM>
</BODY>
</html>