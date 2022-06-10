<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<%@include file="inc/js.jsp"%>
<script type="text/javascript">
var path='<%=basePath %>';
$(function(){
	//syncToQy();
});

function syncToYf(tabArrStr){
	$.post(path+"gkj/syncToYf",
		{tabArrStr:tabArrStr},
		function(data){
		
		}
	,"json");
}

function syncToQy(tabArrStr){
	$.post(path+"gkj/syncToQy",
		{tabArrStr:tabArrStr},
		function(data){
		
		}
	,"json");
}
</script>
</head>
<body>
<div>从本地同步到云服务</div>
<div>
	<input type="button" value="订单" onclick="syncToYf('ding_dan')"/>
	<input type="button" value="质检记录" onclick="syncToYf('zhi_jian_ji_lu')"/>
</div>
<div>从云服务同步到本地</div>
<div>
	<input type="button" value="订单" onclick="syncToQy('ding_dan')"/>
	<input type="button" value="质检记录" onclick="syncToQy('zhi_jian_ji_lu')"/>
	<input type="button" value="排队记录" onclick="syncToQy('pai_dui_ji_lu')"/>
</div>
</body>
</html>