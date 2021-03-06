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
.tab1_div .toolbar .gbsj_span{
	margin-left: 13px;
}
.tab1_div .toolbar .ddh_inp,
.tab1_div .toolbar .cph_inp{
	width: 120px;
	height: 25px;
}
.tab1_div .toolbar .search_but{
	margin-left: 13px;
}
</style>
<title>Insert title here</title>
<%@include file="../../inc/js.jsp"%>
<script type="text/javascript">
var path='<%=basePath %>';
var gbglPath=path+'gbgl/';
var exportExcelPath=path+'exportExcel/';
$(function(){
	initGBSJKSDTB();
	initGBSJJSDTB();
	initSearchLB();
	initAddLB();
	initOutputBut();
	initTab1();
});

function initGBSJKSDTB(){
	gbsjksDTB=$("#gbsjks_dtb").datetimebox({
        required:false
    });
}

function initGBSJJSDTB(){
	gbsjjsDTB=$("#gbsjjs_dtb").datetimebox({
        required:false
    });
}

function initSearchLB(){
	$("#search_but").linkbutton({
		iconCls:"icon-search",
		onClick:function(){
			var ddh=$("#toolbar #ddh").val();
			var cph=$("#toolbar #cph").val();
			var gbsjks=gbsjksDTB.datetimebox("getValue");
			var gbsjjs=gbsjjsDTB.datetimebox("getValue");
			tab1.datagrid("load",{ddh:ddh,cph:cph,gbsjks:gbsjks,gbsjjs:gbsjjs});
		}
	});
}

function initAddLB(){
	$("#add_but").linkbutton({
		iconCls:"icon-add",
		onClick:function(){
			location.href=gbglPath+"gbjl/new";
		}
	});
}

function initOutputBut(){
	opBut=$("#output_but").linkbutton({
		iconCls:"icon-remove",
		onClick:function(){
			location.href=exportExcelPath+"exportGBJLList";
		}
	});
}

function initTab1(){
	tab1=$("#tab1").datagrid({
		title:"??????????????????",
		url:gbglPath+"queryGBJLList",
		toolbar:"#toolbar",
		width:setFitWidthInParent("body"),
		pagination:true,
		pageSize:10,
		columns:[[
			{field:"ddh",title:"?????????",width:150},
			{field:"cph",title:"?????????",width:150},
			{field:"gbzl",title:"????????????",width:200},
			{field:"gbzt",title:"????????????",width:100,formatter:function(value,row){
				return value==1?"??????":"??????";
			}},
            {field:"gblx",title:"????????????",width:100,formatter:function(value,row){
            	var str;
            	switch (value) {
				case 1:
					str="??????";
					break;
				case 2:
					str="??????";
					break;
				}
            	return str+"??????";
            }},
            {field:"gbsj",title:"????????????",width:150},
            {field:"id",title:"??????",width:110,formatter:function(value,row){
            	var str="<a href=\"edit?id="+value+"\">??????</a>&nbsp;&nbsp;"
            		+"<a href=\"detail?id="+value+"\">??????</a>";
            	return str;
            }}
	    ]],
        onLoadSuccess:function(data){
			if(data.total==0){
				$(this).datagrid("appendRow",{ddh:"<div style=\"text-align:center;\">????????????<div>"});
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
		<div class="page_location_div">????????????-????????????</div>
		<div class="tab1_div" id="tab1_div">
			<div class="toolbar" id="toolbar">
				<span class="ddh_span">????????????</span>
				<input type="text" class="ddh_inp" id="ddh" placeholder="??????????????????"/>
				<span class="cph_span">????????????</span>
				<input type="text" class="cph_inp" id="cph" placeholder="??????????????????"/>
				<span class="gbsj_span">???????????????</span>
				<input id="gbsjks_dtb"/>-
				<input id="gbsjjs_dtb"/>
				<a class="search_but" id="search_but">??????</a>
				<a id="add_but">??????</a>
	         	<a id="output_but">??????</a>
			</div>
			<table id="tab1">
			</table>
		</div>
	</div>
	<%@include file="../../inc/foot.jsp"%>
</div>
</body>
</html>