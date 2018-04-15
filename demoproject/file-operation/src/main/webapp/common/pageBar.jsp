<div id="pageBar" style="background:#efefef;border:1px solid #ccc;"></div>
<input type="hidden" name="pageNum" value="${page.pageNum}" id="pageNum">
<input type="hidden" name="pageSize" value="${page.pageSize}" id="pageSize">
<script>
	$(function(){
	    //alert("pageBar");
		$('#pageBar').pagination({
			total:<c:out value="${page.total}"/>,
			pageSize:<c:out value="${page.pageSize}"/>,
            pageNum:<c:out value="${page.pageNum}"/>,
			onSelectPage:function(pageNum, pageSize){
				$('#pageNum').attr("value",pageNum);
				$('#pageSize').attr("value",pageSize);
				$(this).pagination('loading');
				$('#search_form').submit();
			}
		});
	});
</script>
