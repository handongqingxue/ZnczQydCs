<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%String basePath=request.getScheme()+"://"+request.getServerName()+":"
	+request.getServerPort()+request.getContextPath()+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script type="text/javascript" src="<%=basePath %>resource/js/jquery-3.3.1.js"></script>
<script type="text/javascript">
$(function(){
	var html='${requestScope.dyjl.html}';
	//alert(html);
	window.document.body.innerHTML=html;
	window.print();//打印上面新建的网页
	window.opener=null;
	window.open(' ', '_self', ' ');
	window.close();
});
</script>
<title></title>
</head>
<body>

</body>
</html>