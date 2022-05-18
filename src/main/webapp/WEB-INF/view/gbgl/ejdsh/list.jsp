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
	height:64px;
}
.tab1_div .toolbar .row_div{
	height:32px;
}
.tab1_div .toolbar .row_div .ddh_span,
.tab1_div .toolbar .row_div .sjxm_span,
.tab1_div .toolbar .row_div .sjsfzh_span,
.tab1_div .toolbar .row_div .cph_span,
.tab1_div .toolbar .row_div .yss_span,
.tab1_div .toolbar .row_div .fhdw_span,
.tab1_div .toolbar .row_div .gbsj_span,
.tab1_div .toolbar .row_div .shbm_span,
.tab1_div .toolbar .row_div .search_but{
	margin-left: 13px;
}
.tab1_div .toolbar .row_div .ddh_inp,
.tab1_div .toolbar .row_div .sjxm_inp,
.tab1_div .toolbar .row_div .sjsfzh_inp,
.tab1_div .toolbar .row_div .cph_inp,
.tab1_div .toolbar .row_div .yssMc_inp,
.tab1_div .toolbar .row_div .fhdwMc_inp,
.tab1_div .toolbar .row_div .shbmMc_inp{
	width: 120px;
	height: 25px;
}
</style>
<title>Insert title here</title>
<%@include file="../../inc/js.jsp"%>
<script type="text/javascript">
var path='<%=basePath %>';
var gbglPath=path+'gbgl/';
var ddglPath=path+'ddgl/';
var exportExcelPath=path+'exportExcel/';
var ddztMc='${requestScope.ejdshDdztMc}';
var gblx='${requestScope.gblx}';
$(function(){
	initGBSJKSDTB();
	initGBSJJSDTB();
	initSearchLB();
	initCheckLB();
	initSHBTGLB();
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
			var sjxm=$("#toolbar #sjxm").val();
			var sjsfzh=$("#toolbar #sjsfzh").val();
			var cph=$("#toolbar #cph").val();
			var yssMc=$("#toolbar #yssMc").val();
			var fhdwMc=$("#toolbar #fhdwMc").val();
			var shbmMc=$("#toolbar #shbmMc").val();
			var gbsjks=gbsjksDTB.datetimebox("getValue");
			var gbsjjs=gbsjjsDTB.datetimebox("getValue");
			tab1.datagrid("load",{ddztMc:ddztMc,ddh:ddh,sjxm:sjxm,sjsfzh:sjsfzh,cph:cph,yssMc:yssMc,fhdwMc:fhdwMc,shbmMc:shbmMc,gbsjks:gbsjks,gbsjjs:gbsjjs,gblx:gblx});
		}
	});
}

function initCheckLB(){
	$("#check_but").linkbutton({
		iconCls:"icon-ok",
		onClick:function(){
			checkByIds(true);
		}
	});
}

function initSHBTGLB(){
	$("#shbtg_but").linkbutton({
		iconCls:"icon-remove",
		onClick:function(){
			checkByIds(false);
		}
	});
}

function checkByIds(shjg) {
	var rows=tab1.datagrid("getSelections");
	if (rows.length == 0) {
		$.messager.alert("提示","请选择要审核的信息！","warning");
		return false;
	}
	
	var ddIds = "";
	for (var i = 0; i < rows.length; i++) {
		ddIds += "," + rows[i].ddId;
	}
	ddIds=ddIds.substring(1);
	
	var ddztMc='${requestScope.ywcDdztMc}';
	var shlx='${requestScope.shlx}';
	var shrId='${sessionScope.yongHu.id}';
	$.post(ddglPath + "checkDingDanByIds",
		{ids:ddIds,ddztMc:ddztMc,shlx:shlx,shjg:shjg,shrId:shrId,jyFlag:2},
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
		title:"二检待审核查询",
		url:gbglPath+"queryDJYList",
		toolbar:"#toolbar",
		width:setFitWidthInParent("body"),
		queryParams:{ddztMc:ddztMc,gblx:gblx},
		pagination:true,
		pageSize:10,
		columns:[[
			{field:"ddh",title:"订单号",width:150},
			{field:"sjxm",title:"司机姓名",width:100},
			{field:"sjsfzh",title:"司机身份证号",width:200},
			{field:"cph",title:"车牌号",width:150},
			{field:"yssMc",title:"运输商",width:150},
			{field:"fhdwMc",title:"发货单位",width:150},
			{field:"shbmMc",title:"收货部门",width:150},
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
			{field:"gbzl",title:"过磅重量",width:200},
			{field:"gbzt",title:"过磅状态",width:100,formatter:function(value,row){
				return value==1?"正常":"异常";
			}},
            {field:"gbsj",title:"过磅时间",width:150},
            {field:"id",title:"操作",width:50,formatter:function(value,row){
            	//var str="<a href=\"detail?id="+value+"\">详情</a>";
            	//return str;
            	return "";
            }}
	    ]],
        onLoadSuccess:function(data){
			if(data.total==0){
				$(this).datagrid("appendRow",{ddh:"<div style=\"text-align:center;\">暂无信息<div>"});
				$(this).datagrid("mergeCells",{index:0,field:"ddh",colspan:12});
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
			<div class="row_div">
				<span class="ddh_span">订单号：</span>
				<input type="text" class="ddh_inp" id="ddh" placeholder="请输入订单号"/>
				<span class="sjxm_span">司机姓名：</span>
				<input type="text" class="sjxm_inp" id="sjxm" placeholder="请输入司机姓名"/>
				<span class="sjsfzh_span">司机身份证号：</span>
				<input type="text" class="sjsfzh_inp" id="sjsfzh" placeholder="请输入司机身份证号"/>
				<span class="cph_span">车牌号：</span>
				<input type="text" class="cph_inp" id="cph" placeholder="请输入车牌号"/>
			</div>
			<div class="row_div">
				<span class="yss_span">运输商：</span>
				<input type="text" class="yssMc_inp" id="yssMc" placeholder="请输入运输商"/>
				<span class="fhdw_span">发货单位：</span>
				<input type="text" class="fhdwMc_inp" id="fhdwMc" placeholder="请输入发货单位"/>
				<span class="shbm_span">收货部门：</span>
				<input type="text" class="shbmMc_inp" id="shbmMc" placeholder="请输入收货部门"/>
				<span class="gbsj_span">过磅时间：</span>
				<input id="gbsjks_dtb"/>-
				<input id="gbsjjs_dtb"/>
				<a class="search_but" id="search_but">查询</a>
				<a id="check_but">审核</a>
				<a id="shbtg_but">审核不通过</a>
			</div>
		</div>
		<table id="tab1">
		</table>
	</div>
	<%@include file="../../inc/foot.jsp"%>
</div>
</body>
</html>