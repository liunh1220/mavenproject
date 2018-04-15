<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/include.jsp" %>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
	<script>
		// 保存菜单
		function submitForm()
		{
			$('#save_form').submit();
		}
		
		// 关闭窗口
		function closeWin()
		{
			window.close();
		}
	</script>
</head>
<body>
	<form name="save_form" action="job/save.do" id="save_form">
	<input type="hidden" name="id" value="${obj.id }"/>
	<div id="p" class="easyui-panel" title="当前位置: 系统管理 >> 任务调度"  fit="true">
		<table>
			<tr align="left">
				<td class="table-td_title">
				  调度名称:
				</td>
				<td>
				<input id="name" name="name"  value="${obj.name }"/>
				</td>
			</tr>
			<tr align="left">
				<td class="table-td_title">
				  调度组:
				</td>
				<td>
				<input id="groupName" name="groupName"  value="${obj.groupName }"/>
				</td>
			</tr>
			<tr align="left">
				<td class="table-td_title">
				  调度时间配置:
				</td>
				<td>
				<input id="cronExpression" name="cronExpression"  value="${obj.cronExpression }"/>
				</td>
			</tr>
			<tr align="left">
				<td class="table-td_title">
				  调度类:
				</td>
				<td>
				<input id="jobClass" name="jobClass"  value="${obj.jobClass }"/>
				</td>
			</tr>
			<tr align="left">
				<td class="table-td_title">
				  指定调度运行IP:
				</td>
				<td>
				<input id="runningIp" name="runningIp" value="${obj.runningIp }" />
				</td>
			</tr>
			<tr align="left">
				<td class="table-td_title">
				  描述:
				</td>
				<td>
				<input id="description" name="description"  value="${obj.description }"/>
				</td>
			</tr>
			<tr align="left">
				<td class="table-td_title">
				  调度级别:
				</td>
				<td>
				<select id="grade" name="grade">
					<option value="1" <c:if test="${obj.grade=='1' }">selected="selected"</c:if>>重要</option>
					<option value="2" <c:if test="${obj.grade=='2' }">selected="selected"</c:if>>普通</option>
					<option value="3" <c:if test="${obj.grade=='3' }">selected="selected"</c:if>>次要</option>
				</select>
				</td>
			</tr>
			<tr align="left">
				<td class="table-td_title">
				  是否启用:
				</td>
				<td>
				<select id="enabledFlag" name="enabledFlag">
					<option value="Y" <c:if test="${obj.enabledFlag=='Y' }">selected="selected"</c:if>>是</option>
					<option value="N" <c:if test="${obj.enabledFlag=='N' }">selected="selected"</c:if>>否</option>
				</select>
				</td>
			</tr>
		</table>
		<table width="100%">
			<tr>
				<td align="right">
					<a href="javascript:void(0);" class="easyui-linkbutton" onclick="submitForm()" iconCls="icon-add">保存</a>
					<a href="javascript:void(0);" class="easyui-linkbutton" onclick="closeWin()" iconCls="icon-cancel">关闭</a>
				</td>
			</tr>
		</table>
		</div>
	</form>
</body>
</html>