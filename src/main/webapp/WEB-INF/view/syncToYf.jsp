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
	syncToQy();
});

function syncToYf(){
	$.post(path+"main/syncToYf",
		{tabArrStr:"yong_hu"},
		function(data){
		
		}
	,"json");
}

function syncToQy(){
	$.post(path+"main/syncToQy",
		{tabArrStr:"ding_dan"},
		function(data){
		
		}
	,"json");
}
</script>
</head>
<body>

</body>
</html>