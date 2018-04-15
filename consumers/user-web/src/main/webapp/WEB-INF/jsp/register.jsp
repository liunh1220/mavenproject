<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ include file="/common/include.jsp"%> 
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>index page</title>

<body>
  <div name="rge">  
    <form action="/Day01/servlet/doRegister" method="post">  
        <input type="hidden" name="token" value="${token}">   <!-- 传递一个令牌值 防止重复提交--> 
        <table border="1" bordercolor="#000099" width="523" cellpadding="10" cellspacing="0" height="343">  
            <tr><td colspan="2" align="center">注册页面</td></tr>  
            <tr>  
                <td class="one">用户名:</td>  
                <td class="two"><input type="text" name="name"></td>  
            </tr>  
            <tr>  
                <td class="one">密码：</td>  
                <td class="two"><input type="password" name="password" /></td>  
            </tr>  
            <tr>  
                <td class="one">确认密码：</td>  
                <td class="two"><input type="password" name="password2" /></td>  
            </tr>  
            <tr>  
                <td class="one">性别：</td>  
                <td class="two">  
                    <input type="radio" name="sex" value="nan" />男  
                    <input type="radio" name="sex" value="nv" />女  
                </td>  
            </tr>  
            <tr bgcolor="#CCFFFF">  
                <th colspan="2">  
                <input type="submit" value="提交数据" />  
                <input type="reset" value="置空" />  
                </th>  
            </tr>  
        </table>  
    </form>  
  </div>  


</body>
</html>
