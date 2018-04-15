﻿<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<head id="Head1">
<title></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<%@ include file="/common/include.jsp"%> 
<script type="text/javascript" src="js/sysmgt/main.js"></script>
<script type="text/javascript">
	//设置登录窗口
	/*function openPwd() {
	    $('#w').window({
	        title: '修改密码',
	        width: 280,
	        modal: true,
	        shadow: true,
	        closed: true,
	        height: 180,
	        resizable:false
	    });
	}*/

	//关闭登录窗口
	function closePwd() {
		$('#w').window('close');
	}

	//修改密码
	function changePassword() {
		var oldpass = $('#txtOldPass').val();
		var newpass = $('#txtNewPass').val();
		var rePass = $('#txtRePass').val();

		if (oldpass == '') {
			$.messager.alert('系统提示', '请输入原密码！', 'warning');
			return;
		}

		if (newpass == '') {
			$.messager.alert('系统提示', '请输入密码！', 'warning');
			return;
		}

		if (rePass == '') {
			$.messager.alert('系统提示', '请在一次输入密码！', 'warning');
			return;
		}

		if (newpass != rePass) {
			$.messager.alert('系统提示', '两次密码不一至！请重新输入', 'warning');
			return;
		}

		$.ajax({
			type : "post",
			url : "changPassword.do",
			data : {
				'oldPassword' : oldpass,
				'newPassword' : newpass
			},
			dataType : 'text',
			success : function(msg) {
				$.messager.alert('提示', msg, 'info');
				if (msg.indexOf('成功') != -1) {
					closePwd();
					$('#txtOldPass').val('');
					$('#txtNewPass').val('');
					$('#txtRePass').val('');
				}
			}
		});
	}

	$(function() {

		//openPwd();

		$('#editpass').click(function() {
			$('#w').window('open');
		});

		$('#btnEp').click(function() {
			changePassword();
		});

		$('#btnCancel').click(function() {
			closePwd();
		})

		$('#loginOut').click(function() {
			$.messager.confirm('系统提示', '您确定要退出本次登录吗?', function(result) {
				if (result) {
					location.href = 'logout.do';
				}
			});
		});
	});
	
	
	function addTab(title, url){
        if ($('#tabs').tabs('exists', title)){
            $('#tabs').tabs('select', title);
        } else {
            var content = '<iframe scrolling="auto" frameborder="0" src="'+ url +'" style="width:100%;height:100%;"></iframe>';
            $('#tabs').tabs('add',{
                title:title,
                content:content,
                closable:true
            });
        }
    }
	
</script>

</head>
<body class="easyui-layout" style="overflow-y: hidden" scroll="no">
	<noscript>
		<div style="position: absolute; z-index: 100000; height: 2046px; top: 0px; left: 0px; width: 100%; background: white; text-align: center;">
			<img src="/jquery/images/noscript.gif" alt='抱歉，请开启脚本支持！' />
		</div>
	</noscript>
	<div region="north" split="true" border="false"
		style="overflow: hidden; height: 28px; background: #D2E0F2 repeat-x center 50%; line-height: 20px; color: blue; font-family: Verdana, 微软雅黑">
		<span style="float: right; padding-right: 20px;">
		             欢迎 <c:out value="${obj.name }"></c:out> 
		    <a href="javascript:void(0);" plain="true" class="easyui-linkbutton" iconCls="icon-save" id="editpass">修改密码</a>
			<a href="javascript:void(0);" plain="true" class="easyui-linkbutton" iconCls="icon-redo" id="loginOut">安全退出</a>
		</span>
	</div>
	<div region="south" split="true"
		style="height: 30px; background: #D2E0F2;">
		<div class="footer">
			<a href="http://www.c.com/" target="_blank">Copyright © 2017
				xxx Technology Co., Ltd. </a>
		</div>
	</div>
	<div region="west" hide="true" split="true" icon="icon-go" title="导航菜单"
		style="width: 180px;" id="west">
		<div id="nav" class="easyui-accordion" fit="true" border="false">
			<!--  导航内容 -->
			<ul>
                <li><a href="javascript:void(0)" onclick="addTab('job列表','job/getList.do')">job列表</a></li>
            </ul>
		</div>

	</div>
	<div id="mainPanle" region="center" style="background: #eee; overflow-y: hidden">
		<div id="tabs" class="easyui-tabs" fit="true" border="false">
			<div title="首  页" style="padding: 20px; overflow: hidden; color: black;">
				<h1 style="font-size: 24px;">欢迎 XXX</h1>
			</div>
		</div>
	</div>


	<!--修改密码窗口-->
	<div id="w" class="easyui-window" title="修改密码" modal="true"
		iconCls="icon-save" closed="true" collapsible="false"
		minimizable="false" maximizable="false"
		style="width: 280px; height: 200px; padding: 5px; background: #fafafa;">
		<div class="easyui-layout" fit="true">
			<div region="center" border="false"
				style="padding: 10px; background: #fff; border: 1px solid #ccc;">
				<table cellpadding=3>
					<tr>
						<td>原密码：</td>
						<td><input id="txtOldPass" type="Password" class="txt01" /></td>
					</tr>
					<tr>
						<td>新密码：</td>
						<td><input id="txtNewPass" type="Password" class="txt01" /></td>
					</tr>
					<tr>
						<td>确认密码：</td>
						<td><input id="txtRePass" type="Password" class="txt01" /></td>
					</tr>
				</table>
			</div>
			<div region="south" border="false"
				style="text-align: right; height: 30px; line-height: 30px;">
				<a id="btnEp" class="easyui-linkbutton" icon="icon-ok"
					href="javascript:void()"> 确定</a> <a id="btnCancel"
					class="easyui-linkbutton" icon="icon-cancel"
					href="javascript:void()">取消</a>
			</div>
		</div>
	</div>

	<div id="mm" class="easyui-menu" style="width: 150px;">
		<div id="mm-tabupdate">刷新</div>
		<div class="menu-sep"></div>
		<div id="mm-tabclose">关闭</div>
		<div id="mm-tabcloseall">全部关闭</div>
		<div id="mm-tabcloseother">除此之外全部关闭</div>
		<div class="menu-sep"></div>
		<div id="mm-tabcloseright">当前页右侧全部关闭</div>
		<div id="mm-tabcloseleft">当前页左侧全部关闭</div>
		<div class="menu-sep"></div>
		<div id="mm-exit">退出</div>
	</div>

</body>
</html>