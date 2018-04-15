<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%     
  String path = request.getContextPath();     
  String basePath = request.getScheme()+"://" +request.getServerName()+":" +request.getServerPort()+path+"/" ;     
  %>     
<base href="<%=basePath%>" >

<link rel="stylesheet" type="text/css" href="jquery/css/default.css" />
<link rel="stylesheet" type="text/css" href="jquery/js/themes/default/easyui.css" />
<link rel="stylesheet" type="text/css" href="jquery/js/themes/icon.css" />
<link rel="stylesheet" type="text/css" href="jquery/css/jquery.multiSelect.css" />

<script type="text/javascript" src="jquery/js/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="jquery/js/jquery.easyui.min.1.2.2.js"></script>
<script type="text/javascript" src="jquery/js/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="jquery/js/jquery.blockUI.js"></script>

<script type="text/javascript" src="common/common.js"></script>
<script type="text/javascript" src="js/sysmgt/combox.js"></script>
<script type="text/javascript" src="jquery/js/jquery.multiSelect.js"></script>
<div id="win"></div>