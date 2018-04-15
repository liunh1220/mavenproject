<div id="pageBar" style="background:#efefef;border:1px solid #ccc;"></div>
</div>
<!-- 	<script>
		$(function(){
			$('#pageBar').pagination({
				total:<s:property value="page.count" />,
				pageSize:<s:property value="page.limit" />,
				pageNumber:<s:property value="page.currentPage" />,
				onSelectPage:function(pageNumber, pageSize){
					$('#currentPage').attr("value",pageNumber);
					$('#limit').attr("value",pageSize);
					$(this).pagination('loading');
					$('#search_form').submit();
				}
			});
		});
	</script>
<s:hidden name="page.currentPage" id="currentPage" />
<s:hidden name="page.limit" id="limit" /> -->