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
	syncToYf();
});

function syncToYf(){
	$.post(path+"main/syncToYf",
		{tabArrStr:"pai_dui_ji_lu"},
		function(data){
		
		}
	,"json");
}

function syncToQy(){
	$.post(path+"main/syncToQy",
		{tabArrStr:"guo_bang_ji_lu"},
		function(data){
		
		}
	,"json");
}
</script>
</head>
<body>

</body>
</html>