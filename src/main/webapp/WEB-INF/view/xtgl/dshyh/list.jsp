<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<style type="text/css">
.tab1_div{
	margin-top:80px;
	margin-left: 220px;
}
.tab1_div .toolbar{
	height:32px;
}
.tab1_div .toolbar .yhm_span{
	margin-left: 13px;
}
.tab1_div .toolbar .yhm_inp{
	width: 120px;height: 25px;
}
.tab1_div .toolbar .search_but{
	margin-left: 13px;
}
</style>
<title>Insert title here</title>
<%@include file="../../inc/js.jsp"%>
<script type="text/javascript">
var path='<%=basePath %>';
var xtglPath=path+'xtgl/';
var check='${requestScope.check}';
$(function(){
	initSearchLB();
	initCheckLB();
	initTab1();
});

function initSearchLB(){
	$("#search_but").linkbutton({
		iconCls:"icon-search",
		onClick:function(){
			var yhm=$("#toolbar #yhm").val();
			tab1.datagrid("load",{yhm:yhm,zt:zt});
		}
	});
}

function initCheckLB(){
	$("#check_but").linkbutton({
		iconCls:"icon-ok",
		onClick:function(){
			checkByIds();
		}
	});
}

function checkByIds() {
	var rows=tab1.datagrid("getSelections");
	if (rows.length == 0) {
		$.messager.alert("提示","请选择要审核的信息！","warning");
		return false;
	}
	
	var ids = "";
	for (var i = 0; i < rows.length; i++) {
		ids += "," + rows[i].id;
	}
	ids=ids.substring(1);

	$.post(xtglPath + "checkYHByIds",
		{ids:ids,check:true},
		function(result){
			if(result.status==1){
				alert(result.msg);
				tab1.datagrid("load");
			}
			else{
				alert(result.msg);
			}
		}
	,"json");
}

function initTab1(){
	tab1=$("#tab1").datagrid({
		title:"待审核用户查询",
		url:xtglPath+"queryYongHuList",
		toolbar:"#toolbar",
		width:setFitWidthInParent("body"),
		queryParams:{check:check},
		pagination:true,
		pageSize:10,
		columns:[[
			{field:"yhm",title:"用户名",width:150},
			{field:"zsxm",title:"真实姓名",width:150},
			{field:"cjsj",title:"创建时间",width:150},
            {field:"qsIds",title:"权限",width:100},
            {field:"id",title:"操作",width:50,formatter:function(value,row){
            	var str="<a href=\"detail?id="+value+"\">详情</a>";
            	return str;
            }}
	    ]],
        onLoadSuccess:function(data){
			if(data.total==0){
				$(this).datagrid("appendRow",{yhm:"<div style=\"text-align:center;\">暂无信息<div>"});
				$(this).datagrid("mergeCells",{index:0,field:"yhm",colspan:5});
				data.total=0;
			}
			
			$(".panel-header .panel-title").css("color","#000");
			$(".panel-header .panel-title").css("font-size","15px");
			$(".panel-header .panel-title").css("padding-left","10px");
			$(".panel-header, .panel-body").css("border-color","#ddd");
		}
	});
}

function setFitWidthInParent(o){
	var width=$(o).css("width");
	return width.substring(0,width.length-2)-250;
}
</script>
</head>
<body>
<div class="layui-layout layui-layout-admin">
	<%@include file="../../inc/side.jsp"%>
	<div class="tab1_div" id="tab1_div">
		<div class="toolbar" id="toolbar">
			<span class="yhm_span">用户名：</span>
			<input type="text" class="yhm_inp" id="yhm" placeholder="请输入用户名"/>
			<a class="search_but" id="search_but">查询</a>
			<a id="check_but">审核</a>
		</div>
		<table id="tab1">
		</table>
	</div>
	<%@include file="../../inc/foot.jsp"%>
</div>
</body>
</html>