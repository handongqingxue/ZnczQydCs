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
.tab1_div .toolbar .zjjg_span,
.tab1_div .toolbar .ddzt_span,
.tab1_div .toolbar .search_but{
	margin-left: 13px;
}
.tab1_div .toolbar .ddh_inp,
.tab1_div .toolbar .zjyZsxm_inp{
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
var gkjPath=path+'gkj/';
var syncToQyTab='${requestScope.syncToQyTab}';
$(function(){
	initZJJGCBB();
	initDDZTCBB();
	initSearchLB();
	syncToQy();
});

function initZJJGCBB(){
	zjjgCBB=$("#zjjg_cbb").combobox({
		valueField:"value",
		textField:"text",
		//multiple:true,
		data:[{"value":"","text":"请选择"},{"value":"1","text":"合格"},{"value":"2","text":"不合格"}]
	});
}

function initDDZTCBB(){
	var data=[];
	data.push({"value":"","text":"请选择"});
	$.post(ddglPath+"queryDingDanZhuangTaiCBBList",
		function(result){
			var rows=result.rows;
			for(var i=0;i<rows.length;i++){
				data.push({"value":rows[i].id,"text":rows[i].mc});
			}
			ddztCBB=$("#ddzt_cbb").combobox({
				valueField:"value",
				textField:"text",
				//multiple:true,
				data:data
			});
		}
	,"json");
}

function initSearchLB(){
	$("#search_but").linkbutton({
		iconCls:"icon-search",
		onClick:function(){
			var ddh=$("#toolbar #ddh_inp").val();
			var jg=zjjgCBB.combobox("getValue");
			var ddztId=ddztCBB.combobox("getValue");
			var zjyZsxm=$("#toolbar #zjyZsxm_inp").val();
			tab1.datagrid("load",{ddh:ddh,jg:jg,ddztId:ddztId,zjyZsxm:zjyZsxm});
		}
	});
}

function syncToQy(){
	$.post(gkjPath+"syncToQy",
		{tabArrStr:syncToQyTab},
		function(data){
			if(data.status=="ok"){
				initTab1();
			}
		}
	,"json");
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
			{field:"ddh",title:"订单号",width:150},
			{field:"ddztMc",title:"订单状态",width:100},
			{field:"zjyZsxm",title:"质检员",width:100},
            {field:"jg",title:"质检结果",width:100,formatter:function(value,row){
            	var str;
            	switch (value) {
				case 1:
					str="合格";
					break;
				case 2:
					str="不合格";
					break;
				}
            	return str;
            }},
			{field:"zjsj",title:"质检时间",width:180},
            {field:"id",title:"操作",width:60,formatter:function(value,row){
            	var str="<a href=\"detail?id="+value+"\">详情</a>";
            	return str;
            }}
	    ]],
        onLoadSuccess:function(data){
			if(data.total==0){
				$(this).datagrid("appendRow",{ddh:"<div style=\"text-align:center;\">暂无信息<div>"});
				$(this).datagrid("mergeCells",{index:0,field:"ddh",colspan:6});
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
		<div class="page_location_div">质检管理-质检记录</div>
		<div class="tab1_div" id="tab1_div">
			<div class="toolbar" id="toolbar">
				<span class="ddh_span">订单号：</span>
				<input type="text" class="ddh_inp" id="ddh_inp" placeholder="请输入订单号"/>
				<span class="zjjg_span">质检结果：</span>
				<input id="zjjg_cbb"/>
				<span class="ddzt_span">订单状态：</span>
				<input id="ddzt_cbb"/>
				<span class="ddh_span">质检员姓名：</span>
				<input type="text" class="zjyZsxm_inp" id="zjyZsxm_inp" placeholder="请输入质检员姓名"/>
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