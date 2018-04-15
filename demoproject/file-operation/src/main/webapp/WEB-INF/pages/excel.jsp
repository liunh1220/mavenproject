<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<script type="text/javascript" src="../jquery/js/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="../js/ajaxfileupload.js"></script>
<script type="text/javascript">
    function ajaxUploadFile(path){
        var excelFile=document.getElementById("excelFile").value;
        if(excelFile==""){
            alert("请选择导入文件！");
            return ;
        }
        var subName = excelFile.substring(excelFile.length - 3,excelFile.length);
        if (subName != "xls") {
            alert("请选择xls文件！");
            return;
        }
        if (!confirm("确定导入？")) {
            return;
        }

        document.getElementById("uploadMsg").innerHTML = "正在添加文件到服务器中，请稍候……"
        $.ajaxFileUpload({
            url:'importExcelDate.do',
            secureuri:false,
            fileElementId:'excelFile',
            dataType: 'text',
            beforeSend:function(){//上传前需要处理的工作，如显示Loading...
            },
            success: function (data, status){
                alert(data);
                document.getElementById("uploadMsg").innerHTML ="";
            },
            error: function (data , status ,e){
                alert("上传失败");
            }
        });
    }

    function toExport()
    {
        $.post("dataStatus.do", {},function(data) {
            if(data.msg != ""){
                alert(data.msg);
            }else{
                debugger;
                var mainForm = document.getElementById("queryForm");
                mainForm.action = "exportExcel.do";
                mainForm.submit();
                alert("正在生存excel，请稍后...");
            }
        },"json");
    }
</script>

<body>
<br>
<form id="queryForm" name="queryForm" action="" method="get">
    <input id=""  type="button" onclick="toExport()" value="导出" size="55"/>
</form>

<br>
<form id="input" method="POST" action="" enctype="multipart/form-data" >
    <input id="excelFile" name="excelFile" type="file" size="55"/>
    <input type="button" onclick="return ajaxUploadFile('${basePath}');" value="快速导入" class="" />
</form><br />
<span id="uploadMsg"></span>
<div id="img" style="display:none;">
    正在上传，请稍候……<br/>
    <img src="../image/loading_bar.gif" />
</div>
</body>
</html>
