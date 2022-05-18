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
.tab1_div .toolbar .mc_span,.tab1_div .toolbar .wzlxmc_span,.tab1_div .toolbar .search_but{
	margin-left: 13px;
}
.tab1_div .toolbar .mc_inp,.tab1_div .toolbar .wzlxmc_inp{
	width: 120px;height: 25px;
}
</style>
<title>Insert title here</title>
<%@include file="../../inc/js.jsp"%>
<script type="text/javascript">
var path='<%=basePath %>';
var zjglPath=path+'zjgl/';
$(function(){
	initSearchLB();
	initTab1();
});

function initSearchLB(){
	$("#search_but").linkbutton({
		iconCls:"icon-search",
		onClick:function(){
			var mc=$("#toolbar #mc_inp").val();
			var wzlxmc=$("#toolbar #wzlxmc_inp").val();
			tab1.datagrid("load",{mc:mc,wzlxmc:wzlxmc});
		}
	});
}

function initTab1(){
	tab1=$("#tab1").datagrid({
		title:"质检记录查询",
		url:zjglPath+"queryZhiJianJiLuList",
		toolbar:"#toolbar",
		width:setFitWidthInParent("body"),
		pagination:true,
		pageSize:10,
		columns:[[
			{field:"mc",title:"名称",width:200},
            {field:"wzlxmc",title:"物资类型",width:200},
			{field:"bjsj",title:"编辑时间",width:200},
            {field:"id",title:"操作",width:110,formatter:function(value,row){
            	var str="<a href=\"edit?id="+value+"\">编辑</a>&nbsp;&nbsp;"
            		+"<a href=\"detail?id="+value+"\">详情</a>";
            	return str;
            }}
	    ]],
        onLoadSuccess:function(data){
			if(data.total==0){
				$(this).datagrid("appendRow",{mc:"<div style=\"text-align:center;\">暂无信息<div>"});
				$(this).datagrid("mergeCells",{index:0,field:"mc",colspan:4});
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
			<span class="mc_span">名称：</span>
			<input type="text" class="mc_inp" id="mc_inp" placeholder="请输入名称"/>
			<span class="wzlxmc_span">物资类型：</span>
			<input type="text" class="wzlxmc_inp" id="wzlxmc_inp" placeholder="请输入物资类型"/>
			<a class="search_but" id="search_but">查询</a>
		</div>
		<table id="tab1">
		</table>
	</div>
	<%@include file="../../inc/foot.jsp"%>
</div>
</body>
</html>