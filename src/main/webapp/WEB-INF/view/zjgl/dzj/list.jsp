<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<style type="text/css">
.center_con_div{
	height: 90vh;
	margin-left:205px;
	position: absolute;
}
.page_location_div{
	height: 50px;
	line-height: 50px;
	margin-top: 60px;
	margin-left: 20px;
	font-size: 18px;
}
.tab1_div{
	margin-left: 15px;
}
.tab1_div .toolbar{
	height:32px;
}
.tab1_div .toolbar .ddh_span,
.tab1_div .toolbar .cph_span,
.tab1_div .toolbar .search_but{
	margin-left: 13px;
}
.tab1_div .toolbar .ddh_inp,
.tab1_div .toolbar .cph_inp{
	width: 120px;
	height: 25px;
}
</style>
<title>Insert title here</title>
<%@include file="../../inc/js.jsp"%>
<script type="text/javascript">
var path='<%=basePath %>';
var ddglPath=path+'ddgl/';
var zjglPath=path+'zjgl/';
var ddztMc='${requestScope.ddztMc}';
$(function(){
	initSearchLB();
	initTab1();
});

function initSearchLB(){
	$("#search_but").linkbutton({
		iconCls:"icon-search",
		onClick:function(){
			var ddh=$("#toolbar #ddh_inp").val();
			var cph=$("#toolbar #cph_inp").val();
			tab1.datagrid("load",{ddh:ddh,cph:cph,ddztMc:ddztMc});
		}
	});
}

function initTab1(){
	tab1=$("#tab1").datagrid({
		title:"待质检查询",
		url:ddglPath+"queryZHCXList",
		toolbar:"#toolbar",
		width:setFitWidthInParent("body"),
		queryParams:{ddztMc:ddztMc},
		pagination:true,
		pageSize:10,
		columns:[[
			{field:"ddh",title:"订单号",width:150},
			{field:"cph",title:"车牌号",width:100},
			{field:"yssMc",title:"运输商",width:150},
            {field:"lxlx",title:"流向类型",width:100,formatter:function(value,row){
            	var str;
            	switch (value) {
				case 1:
					str="送运";
					break;
				case 2:
					str="取运";
					break;
				}
            	return str;
            }},
			{field:"yzxzl",title:"预装卸重量",width:100},
			{field:"bjsj",title:"编辑时间",width:180},
            {field:"id",title:"操作",width:60,formatter:function(value,row){
            	var str="<a href=\"detail?id="+value+"\">详情</a>";
            	return str;
            }}
	    ]],
        onLoadSuccess:function(data){
			if(data.total==0){
				$(this).datagrid("appendRow",{ddh:"<div style=\"text-align:center;\">暂无信息<div>"});
				$(this).datagrid("mergeCells",{index:0,field:"ddh",colspan:7});
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
	<div class="center_con_div" id="center_con_div">
		<div class="page_location_div">质检管理-待质检</div>
		<div class="tab1_div" id="tab1_div">
			<div class="toolbar" id="toolbar">
				<span class="ddh_span">订单号：</span>
				<input type="text" class="ddh_inp" id="ddh_inp" placeholder="请输入订单号"/>
				<span class="ddh_span">车牌号：</span>
				<input type="text" class="cph_inp" id="cph_inp" placeholder="请输入车牌号"/>
				<a class="search_but" id="search_but">查询</a>
			</div>
			<table id="tab1">
			</table>
		</div>
	</div>
	<%@include file="../../inc/foot.jsp"%>
</div>
</body>
</html>