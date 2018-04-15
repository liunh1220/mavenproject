$(function(){
	InitLeftMenu();
	tabClose();
	tabCloseEven();
})

//初始化左侧
function InitLeftMenu() {
	$("#nav").accordion({animate:false});
    $.ajax({
			type : "get",
			url : '/sysmgt/menu!getNav.action',
			async : false,
			dataType : "json",
			success : function(result) {
				if(result != '')
				{
					// 循环根目录
					$.each(result,function(index, element) {
						// 增加accordion
						$('#nav').accordion('add', {
					            title: element.menuname,
					            content: '<ul id="tt'+index+'" url="/sysmgt/menu!doList.action?menu.id='+element.menuid+'" animate="true"></ul>',
					            iconCls: element.icon
					        });
					    var treeElementId = '#tt'+index;
						$(treeElementId).tree({
							
								onClick:function(node){
									$(treeElementId).tree('collapse', node.target);
									// 叶子节点增加打开事件
									if(node.attributes.url != null && node.attributes.url != '')
									{
										addTab(node.text,node.attributes.url,node.iconCls);
									}
								}
						});
					});
				}
			},
			failt:function()
			{
			}
		});
    
    
    
	

	//选中第一个
	/*var panels = $('#nav').accordion('panels');
	var t;
	if(panels != null && panels.size > 0)
	{
		t = panels[0].panel('options').title;
	}
    $('#nav').accordion('select', t);*/
}

function addTab(subtitle,url,icon){
	/*if(!$('#tabs').tabs('exists',subtitle)){
		$('#tabs').tabs('add',{
			title:subtitle,
			content:createFrame(url),
			closable:true,
			icon:icon
		});
	}else{
		$('#tabs').tabs('select',subtitle);
		$('#mm-tabupdate').click();
	}
	tabClose();*/
	if($('#tabs').tabs('exists',subtitle)){
		$('#tabs').tabs('close',subtitle);
	}
	$('#tabs').tabs('add',{
			title:subtitle,
			content:createFrame(url),
			closable:true,
			icon:icon
		});
	tabClose();
	
	
}


function parentAddTab(subtitle,url,icon){
	var tab=parent.$('#tabs');
	
	if(tab.tabs('exists',subtitle)){
		tab.tabs('close',subtitle);
	}
	tab.tabs('add',{
			title:subtitle,
			content:createFrame(url),
			closable:true,
			icon:icon
		});
	tabClose();
}



function createFrame(url)
{
	var s = '<iframe scrolling="auto" frameborder="0"  src="'+url+'" style="width:100%;height:100%;"></iframe>';
	return s;
}




function tabClose()
{
	/*双击关闭TAB选项卡*/
	$(".tabs-inner").dblclick(function(){
		var subtitle = $(this).children(".tabs-closable").text();
		$('#tabs').tabs('close',subtitle);
	})
	/*为选项卡绑定右键*/
	$(".tabs-inner").bind('contextmenu',function(e){
		$('#mm').menu('show', {
			left: e.pageX,
			top: e.pageY
		});

		var subtitle =$(this).children(".tabs-closable").text();

		$('#mm').data("currtab",subtitle);
		$('#tabs').tabs('select',subtitle);
		return false;
	});
}
//绑定右键菜单事件
function tabCloseEven()
{
	//刷新
	$('#mm-tabupdate').click(function(){
		var currTab = $('#tabs').tabs('getSelected');
		var url = $(currTab.panel('options').content).attr('src');
		$('#tabs').tabs('update',{
			tab:currTab,
			options:{
				content:createFrame(url)
			}
		})
	})
	//关闭当前
	$('#mm-tabclose').click(function(){
		var currtab_title = $('#mm').data("currtab");
		$('#tabs').tabs('close',currtab_title);
	})
	//全部关闭
	$('#mm-tabcloseall').click(function(){
		$('.tabs-inner span').each(function(i,n){
			var t = $(n).text();
			$('#tabs').tabs('close',t);
		});
	});
	//关闭除当前之外的TAB
	$('#mm-tabcloseother').click(function(){
		$('#mm-tabcloseright').click();
		$('#mm-tabcloseleft').click();
	});
	//关闭当前右侧的TAB
	$('#mm-tabcloseright').click(function(){
		var nextall = $('.tabs-selected').nextAll();
		if(nextall.length==0){
			//msgShow('系统提示','后边没有啦~~','error');
			//alert('后边没有啦~~');
			return false;
		}
		nextall.each(function(i,n){
			var t=$('a:eq(0) span',$(n)).text();
			$('#tabs').tabs('close',t);
		});
		return false;
	});
	//关闭当前左侧的TAB
	$('#mm-tabcloseleft').click(function(){
		var prevall = $('.tabs-selected').prevAll();
		if(prevall.length==0){
			//alert('到头了，前边没有啦~~');
			return false;
		}
		prevall.each(function(i,n){
			var t=$('a:eq(0) span',$(n)).text();
			$('#tabs').tabs('close',t);
		});
		return false;
	});

	//退出
	$("#mm-exit").click(function(){
		$('#mm').menu('hide');
	})
}

//弹出信息窗口 title:标题 msgString:提示信息 msgType:信息类型 [error,info,question,warning]
function msgShow(title, msgString, msgType) {
	$.messager.alert(title, msgString, msgType);
}
