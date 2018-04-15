<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../../common/include.jsp" %>
<html>
<head>
	
</head>
<body>
	<form name="search_form" action="getHistoryList.do" id="search_form">
		<s:hidden name="job.id" />
		<div id="p" class="easyui-panel" title="当前位置: 系统管理 >> 任务调度 >> 日志列表">
		<table width="100%">
			<tr>
				<td>
					调度名称：<s:property value="job.name"/>
				</td>
			</tr>
		</table>
		
		<table class="easyui-datagrid" singleSelect="true" style="height:560px">
			<thead>
				<tr>
					<th field="rownum" width="30">行号</th>
			        <th field="fireTime" width="130">执行时间</th>
			        <th field="finishTime" width="130">执行完成时间</th>
			        <th field="nextFireTime" width="130">下次执行时间</th>
			        <th field="cost" width="80">花费</th>
			        <th field="status" width="80">状态</th>
			        <th field="desc" width="100">描述</th>
				</tr>
			</thead>
			<tbody>
				<s:iterator value="page.result" status="st">
				<tr>
					<td><s:property value='#st.index+1'/></td>
			        <td>
						<s:property value="fireTime"/>
					</td>
			        <td>
						<s:property value="finishTime"/>
					</td>
			        <td>
						<s:property value="nextFireTime"/>
					</td>
					<td>
						<s:property value="cost"/>
					</td>
					<td>
					<s:if test="status==\"成功\"">
						<font color="green"><s:property value="status"/></font>
					</s:if>
					<s:if test="status==\"失败\"">
						<font color="red"><s:property value="status"/></font>
					</s:if>
					<s:if test="status==\"运行中\"">
						<font color="blue"><s:property value="status"/></font>
					</s:if>
					</td>
			        <td>
						<s:property value="desc"/>
					</td>
				</tr>
				</s:iterator>
			</tbody>
		</table>
		<%@ include file="../../../common/pageBar.jsp" %>
	</form>
</body>
</html>