<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <%@ include file="/common/include.jsp"%> 
	<script>
	document.onkeydown=function(event){
    	var e = event || window.event || arguments.callee.caller.arguments[0];
    	if(e && e.keyCode==13){ // enter 键
    		$('#search_form').submit();
    	}
	};
		function searchForm()
		{
			$('#search_form').submit();
		}
		
		// 打开新增菜单页面
		function add()
		{
			openWin('job/edit.do','新增用户',600,300);
		}
		
		// 编辑
		function edit(itemId)
		{
			openWin('job/edit.do?id='+itemId,'修改订单同步日志',600,300);
		}
		
		// 删除
		function to_remove(itemId,name)
		{
			$.messager.confirm('系统提示', '您确定要删除 '+name+' 任务吗?', function(result) {
                if (result) {
                    window.location.href='job/remove.do?id='+itemId;
                }
            });
		}
		
		// 清空
		function clearValue()
		{
			$('#id').val('');
			$('#name').val('');
			$('#groupName').val('');
			$('#cronExpression').val('');
			$('#jobClass').val('');
			$('#runningIp').val('');
			$('#status').val('');
			$('#description').val('');
			$('#createBy').val('');
			$('#createTime').val('');
			$('#updateBy').val('');
			$('#updateTime').val('');
			$('#enabledFlag').val('');
		}
		
		// 启动任务
		function startJob(itemId,enabledFlag,name)
		{
			if(enabledFlag == 'Y')
			{
				$.messager.confirm('系统提示', '您确定要启动 '+name+' 任务吗?', function(result) {
	                if (result) {
	                	$.ajax({
			                   type: "get",
			                   url: "job/startJob.do?id="+itemId,
			                   dataType:'text',
			                   success: function(msg){
			                       $.messager.alert('提示',msg,'info',function(){
			                           searchForm();
			                       });
			                   }
	                	});
	                    //window.location.href='/sysmgt/sysmgtJob!startJob.action?id='+itemId;
	                }
	            });
            }
			else
			{
				$.messager.alert('系统提示', '调度已禁用,操作无效!');
			}
		}
		
		// 立即启动任务
		function startImmediately(itemId,enabledFlag,name)
		{
			if(enabledFlag == 'Y')
			{
				$.messager.confirm('系统提示', '您确定要立即执行 '+name+' 任务吗?', function(result) {
	                if (result) {
	                	$.ajax({
			                   type: "get",
			                   url: "job/startImmediately.do?id="+itemId,
			                   dataType:'text',
			                   success: function(msg){
			                       $.messager.alert('提示',msg,'info',function(){
			                           //searchForm();
			                       });
			                   }
	                	});
	                	
	                    //window.location.href='/sysmgt/sysmgtJob!startImmediately.action?id='+itemId;
	                }
	            });
            }
			else
			{
				$.messager.alert('系统提示', '调度已禁用,操作无效!');
			}
		}
		
		// 停止任务
		function stopJob(itemId,enabledFlag,name)
		{
			if(enabledFlag == 'Y')
			{
				$.messager.confirm('系统提示', '您确定要停止 '+name+' 任务吗?', function(result) {
	                if (result) {
	                	$.ajax({
			                   type: "get",
			                   url: "job/stopJob.do?id="+itemId,
			                   dataType:'text',
			                   success: function(msg){
			                       $.messager.alert('提示',msg,'info',function(){
			                           searchForm();
			                       });
			                   }
	                	});
	                	
	                    //window.location.href='/sysmgt/sysmgtJob!stopJob.action?id='+itemId;
	                }
	            });
			}
			else
			{
				$.messager.alert('系统提示', '调度已禁用,操作无效!');
			}
		}
		
		// 启动所有调度
		function start()
		{
			$.messager.confirm('系统提示', '您确定要启动所有调度吗?', function(result) {
                if (result) {
                    window.location.href='job/start.do';
                }
            });
		}
		
		// 停止所有调度
		function stop()
		{
			$.messager.confirm('系统提示', '您确定要停止所有调度吗?', function(result) {
                if (result) {
                    window.location.href='job/stop.do';
                }
            });
		}
		
		// 启动所有调度告警
		function startMonitor()
		{
			$.messager.confirm('系统提示', '您确定要启动告警吗?', function(result) {
                if (result) {
                    window.location.href='job/startMonitor.do';
                }
            });
		}
		
		// 停止所有调度告警
		function stopMonitor()
		{
			$.messager.confirm('系统提示', '您确定要停止告警吗?', function(result) {
                if (result) {
                    window.location.href='job/stopMonitor.do';
                }
            });
		}
		
		// 调度日志
		function history(itemId,name)
		{
			openWin('job/getHistoryList.do?id='+itemId,'调度运行日志'+name,800,700);
		}
		
	</script>
</head>
<body>
	<form name="search_form" action="job/getList.do" id="search_form">
	<div id="p" class="easyui-panel" title="当前位置: 系统管理 >> 任务调度列表">
		<table>
			<tr align="left">
		        <td class="table-td_title">
				 调度名称:
				</td>
				<td>
				<input name="name" id="name" />
				</td>
		        <td class="table-td_title">
				 调度组:
				</td>
				<td>
				<input name="groupName" id="groupName" />
				</td>
		        <td class="table-td_title">
				 调度时间配置:
				</td>
				<td>
				<input name="cronExpression" id="cronExpression" />
				</td>
		        <td class="table-td_title">
				 调度类:
				</td>
				<td>
				<input name="jobClass" id="jobClass" />
				</td>
			</tr>
			<tr>
		        <td class="table-td_title">
				 指定调度运行IP:
				</td>
				<td>
				<input name="runningIp" id="runningIp" />
				</td>
		        <td class="table-td_title">
				 状态:
				</td>
				<td>
					<select id="status" name="status">
						<option value="">请选择</option>
						<option value="RUNNING" <c:if test="status=='RUNNING'">selected="selected"</c:if>>运行中</option>
						<option value="STOP" <c:if test="status=='STOP'">selected="selected"</c:if>>停止</option>
					</select>
				</td>
		        <td class="table-td_title">
				 描述:
				</td>
				<td>
				<input name="description" id="description" />
				</td>
		        <td class="table-td_title">
				 是否启用:
				</td>
				<td>
					<select id="enabledFlag" name="enabledFlag">
						<option value="">请选择</option>
						<option value="Y" <c:if test="enabledFlag=='Y'">selected="selected"</c:if>>启用</option>
						<option value="N" <c:if test="enabledFlag=='N'">selected="selected"</c:if>>禁用</option>
					</select>
				</td>
			</tr>
		</table>
		<table width="100%">
			<tr>
				<td align="right">
					<a href="javascript:void(0);" class="easyui-linkbutton" onclick="searchForm()" iconCls="icon-search">查询</a>
					<a href="javascript:void(0);" class="easyui-linkbutton" onclick="clearValue()" iconCls="icon-reload">清空</a>
				</td>
			</tr>
		</table>
		<table width="100%">
			<tr>
				<td style="padding:1px;background:#fafafa;width:100%;" align="left">
					<a href="javascript:void(0);" class="easyui-linkbutton" plain="true" iconCls="icon-add" onclick="add()">新增调度</a>
					<a href="javascript:void(0);" class="easyui-linkbutton" plain="true" iconCls="icon-start" onclick="start()">启动所有调度</a>
					<a href="javascript:void(0);" class="easyui-linkbutton" plain="true" iconCls="icon-stop" onclick="stop()">停止所有调度</a>
					<a href="javascript:void(0);" class="easyui-linkbutton" plain="true" iconCls="icon-start" onclick="startMonitor()">启动告警</a>
					<a href="javascript:void(0);" class="easyui-linkbutton" plain="true" iconCls="icon-stop" onclick="stopMonitor()">停止告警</a>
				</td>
			</tr>
	    </table>
	<table class="easyui-datagrid" singleSelect="true" style="height:560px">
		<thead>
			<tr>
				<th field="rownum" width="30">行号</th>
		        <th field="name" width="200">调度名称</th>
		        <th field="groupName" width="80">调度组</th>
		        <th field="cronExpression" width="100">调度时间配置</th>
		        <th field="jobClass" width="200">调度类</th>
		        <th field="runningIp" width="80">调度运行IP</th>
		        <th field="status" width="40">状态</th>
		        <th field="description" width="50">描述</th>
		        <th field="enabledFlag" width="60">是否启用</th>
				<th field="opt" width="220">操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="item" items="${list}" varStatus="i">
			
			<tr>
				<td>${i.index+1 }</td>
		        <td>
					${item.name }
				</td>
		        <td>
					${item.groupName }
				</td>
		        <td>
					${item.cronExpression }
				</td>
		        <td>
					${item.jobClass }
				</td>
		        <td>
					${item.runningIp }
				</td>
		        <td>
		        	<c:if test="${item.status=='RUNNING' }">
						<font color="green">运行中</font>
					</c:if>
		        	<c:if test="${item.status!='RUNNING' }">
						<font color="red">停止</font>
					</c:if>
				</td>
		        <td>
					${item.description }
				</td>
		        <td>
					<c:if test="${item.enabledFlag=='Y' }">
						<font color="green">启用</font>
					</c:if>
					<c:if test="${item.enabledFlag!='Y' }">
						<font color="red">禁用</font>
					</c:if>
				</td>
				<td>
					<a href="javascript:void(0);" onclick="startJob('${item.id}','${item.enabledFlag}','${item.name}')">启动</a>
					<a href="javascript:void(0);" onclick="stopJob('${item.id}','${item.enabledFlag}','${item.name}')">停止</a>
					<a href="javascript:void(0);" onclick="startImmediately('${item.id}','${item.enabledFlag}','${item.name}')">立即执行</a>
					<a href="javascript:void(0);" onclick="edit('${item.id}')">修改</a>
                    <a href="javascript:void(0);" onclick="to_remove('${item.id}','${item.name}')">删除</a>
                    <a href="javascript:void(0);" onclick="history('${item.id}','${item.name}')">日志</a>
				</td>
			</tr>
			
			</c:forEach>
		</tbody>
	</table>
	<%@ include file="/common/pageBar.jsp" %>
	</form>
</body>
</html>