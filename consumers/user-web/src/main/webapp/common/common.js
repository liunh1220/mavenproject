/**
 * 打开窗口
 * url:链接地址
 * title:窗口标题
 * width:宽
 * height:高
 * callback:回调函数
 */
function openWin(url,title,width,height)
{
	/*$('#win').window({
			title: title,
			width: width,
			height: height,
			modal: true,//模态窗口
			//shadow: false,
			closed: false,
			collapsible:false,//不可收起
			minimizable:false,//不可最小化
			maximizable:true,//不可最大化
			onClose:function()
			{
				$('#win').html('');
				callback();
			}
		});
		$('#win').append('<iframe scrolling="yes" frameborder="0"  src="'+url+'" style="width:100%;height:100%;"></iframe>');
	$('#win').window('open');*/
	
	var l = (screen.width - width) / 2;  
    var t = (screen.height - height) / 2;  
    var s = 'width=' + width + ', height=' + height + ', top=' + t + ', left=' + l;  
    s += ', toolbar=no, scrollbars=yes, menubar=no, location=no, resizable=no';  
    window.open(url, title, s);
}

/**
 * 关闭模态窗口
 */
function closeWin()
{
	$('#win').window('close');
}

/**
 * 跳转到
 * url:目标地址
 */
function forword(url)
{
	window.location.href=url;
}

