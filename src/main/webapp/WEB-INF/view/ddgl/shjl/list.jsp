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
	position: fixed;
}
.tab1_div .toolbar{
	height:64px;
}
.tab1_div .toolbar .row_div{
	height:32px;
}
.tab1_div .toolbar .row_div .ddh_span,
.tab1_div .toolbar .row_div .shlx_span,
.tab1_div .toolbar .row_div .shsj_span,
.tab1_div .toolbar .row_div .cph_span,
.tab1_div .toolbar .row_div .shr_span,
.tab1_div .toolbar .row_div .yss_span,
.tab1_div .toolbar .row_div .wzMc_span,
.tab1_div .toolbar .row_div .fhdw_span,
.tab1_div .toolbar .row_div .shbm_span,
.tab1_div .toolbar .row_div .sjxm_span,
.tab1_div .toolbar .row_div .sjsfzh_span,
.tab1_div .toolbar .row_div .search_but{
	margin-left: 13px;
}
.tab1_div .toolbar .row_div .ddh_inp,
.tab1_div .toolbar .row_div .cph_inp,
.tab1_div .toolbar .row_div .shr_inp,
.tab1_div .toolbar .row_div .yssMc_inp,
.tab1_div .toolbar .row_div .wzMc_inp,
.tab1_div .toolbar .row_div .fhdwMc_inp,
.tab1_div .toolbar .row_div .shbmMc_inp,
.tab1_div .toolbar .row_div .sjxm_inp,
.tab1_div .toolbar .row_div .sjsfzh_inp{
	width: 120px;
	height: 25px;
}
</style>
<title>Insert title here</title>
<%@include file="../../inc/js.jsp"%>
<script type="text/javascript">
var path='<%=basePath %>';
var ddglPath=path+'ddgl/';
$(function(){
	initSHLXCBB();
	initSHSJKSDTB();
	initSHSJJSDTB();
	initSearchLB();
	initRemoveLB();
	initTab1();
	showCompontByQx();
});

function showCompontByQx(){
	removeLB.hide();
	if(yhm=="admin"){
		removeLB.show();
	}
}

function initSHLXCBB(){
	var data=[];
	data.push({"value":"","text":"请选择"});
	data.push({"value":1,"text":"下单审核"});
	data.push({"value":2,"text":"一检审核"});
	data.push({"value":3,"text":"入库审核"});
	data.push({"value":4,"text":"二检审核"});
	
	shlxCBB=$("#shlx_cbb").combobox({
		valueField:"value",
		textField:"text",
		//multiple:true,
		data:data
	});
}

function initSHSJKSDTB(){
	shsjksDTB=$("#shsjks_dtb").datetimebox({
        required:false
    });
}

function initSHSJJSDTB(){
	shsjjsDTB=$("#shsjjs_dtb").datetimebox({
        required:false
    });
}

function initSearchLB(){
	$("#search_but").linkbutton({
		iconCls:"icon-search",
		onClick:function(){
			var ddh=$("#toolbar #ddh").val();
			var shlx=shlxCBB.combobox("getValue");
			var shsjks=shsjksDTB.datetimebox("getValue");
			var shsjjs=shsjjsDTB.datetimebox("getValue");
			var cph=$("#toolbar #cph").val();
			var shrYhm=$("#toolbar #shr").val();
			var yssMc=$("#toolbar #yssMc").val();
			var wzMc=$("#toolbar #wzMc").val();
			var fhdwMc=$("#toolbar #fhdwMc").val();
			var shbmMc=$("#toolbar #shbmMc").val();
			var sjxm=$("#toolbar #sjxm").val();
			var sjsfzh=$("#toolbar #sjsfzh").val();
			tab1.datagrid("load",{ddh:ddh,shlx:shlx,shsjks:shsjks,shsjjs:shsjjs,cph:cph,shrYhm:shrYhm,yssMc:yssMc,wzMc:wzMc,
				fhdwMc:fhdwMc,shbmMc:shbmMc,sjxm:sjxm,sjsfzh:sjsfzh});
		}
	});
}

function initRemoveLB(){
	removeLB=$("#remove_but").linkbutton({
		iconCls:"icon-remove",
		onClick:function(){
			deleteByIds();
		}
	});
}

function initTab1(){
	tab1=$("#tab1").datagrid({
		title:"审核记录查询",
		url:ddglPath+"querySHJLList",
		toolbar:"#toolbar",
		width:setFitWidthInParent("body","tab1_div"),
		pagination:true,
		pageSize:10,
		columns:[[
			{field:"ddh",title:"订单号",width:150},
			{field:"shlx",title:"审核类型",width:100,formatter:function(value,row){
            	var str;
            	switch (value) {
				case 1:
					str="下单";
					break;
				case 2:
					str="一检";
					break;
				case 3:
					str="入库";
					break;
				case 4:
					str="二检";
					break;
				}
            	str+="审核";
            	return str;
            }},
            {field:"shsj",title:"审核时间",width:150},
			{field:"shjg",title:"审核结果",width:100,formatter:function(value,row){
            	return value==1?"合格":"不合格";
            }},
            {field:"shrYhm",title:"审核人",width:150},
			{field:"sjsfzh",title:"司机身份证号",width:200},
			{field:"sjxm",title:"司机姓名",width:100},
			{field:"cph",title:"车牌号",width:150},
			{field:"wzMc",title:"物资名称",width:150},
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
            {field:"yzxzl",title:"预装卸重量",width:100},
            {field:"sjzl",title:"实际重量",width:100},
            {field:"zlceb",title:"重量差额比",width:100}
	    ]],
        onLoadSuccess:function(data){
			if(data.total==0){
				$(this).datagrid("appendRow",{ddh:"<div style=\"text-align:center;\">暂无信息<div>"});
				$(this).datagrid("mergeCells",{index:0,field:"ddh",colspan:16});
				data.total=0;
			}
			
			$(".panel-header .panel-title").css("color","#000");
			$(".panel-header .panel-title").css("font-size","15px");
			$(".panel-header .panel-title").css("padding-left","10px");
			$(".panel-header, .panel-body").css("border-color","#ddd");
		}
	});
}

function deleteByIds() {
	var rows=tab1.datagrid("getSelections");
	if (rows.length == 0) {
		$.messager.alert("提示","请选择要删除的信息！","warning");
		return false;
	}
	
	$.messager.confirm("提示","确定要删除吗？",function(r){
		if(r){
			var ids = "";
			for (var i = 0; i < rows.length; i++) {
				ids += "," + rows[i].id;
			}
			ids=ids.substring(1);
			
			$.post(ddglPath + "deleteShenHeJiLu",
				{ids:ids},
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
	});
}

function setFitWidthInParent(parent,self){
	var space=0;
	switch (self) {
	case "center_con_div":
		space=205;
		break;
	case "tab1_div":
		space=250;
		break;
	case "check_ddxx_dialog_div":
		space=50;
		break;
	case "panel_window":
		space=355;
		break;
	}
	var width=$(parent).css("width");
	return width.substring(0,width.length-2)-space;
}
</script>
</head>
<body>
<div class="layui-layout layui-layout-admin">
	<%@include file="../../inc/side.jsp"%>
	<div class="center_con_div" id="center_con_div">
		<div class="page_location_div">订单管理-审核记录</div>
		<div class="tab1_div" id="tab1_div">
			<div class="toolbar" id="toolbar">
				<div class="row_div">
					<span class="ddh_span">订单号：</span>
					<input type="text" class="ddh_inp" id="ddh" placeholder="请输入订单号"/>
					<span class="shlx_span">审核类型：</span>
					<input id="shlx_cbb"/>
					<span class="shsj_span">审核时间：</span>
					<input id="shsjks_dtb"/>-
					<input id="shsjjs_dtb"/>
					<span class="cph_span">车牌号：</span>
					<input type="text" class="cph_inp" id="cph" placeholder="请输入车牌号"/>
					<span class="shr_span">审核人：</span>
					<input type="text" class="shr_inp" id="shr" placeholder="请输入审核人"/>
				</div>
				<div class="row_div">
					<span class="yss_span">运输商：</span>
					<input type="text" class="yssMc_inp" id="yssMc" placeholder="请输入运输商"/>
					<span class="wzMc_span">物资名称：</span>
					<input type="text" class="wzMc_inp" id="wzMc" placeholder="请输入物资名称"/>
					<span class="fhdw_span">发货单位：</span>
					<input type="text" class="fhdwMc_inp" id="fhdwMc" placeholder="请输入发货单位"/>
					<span class="shbm_span">收货部门：</span>
					<input type="text" class="shbmMc_inp" id="shbmMc" placeholder="请输入收货部门"/>
					<span class="sjxm_span">司机姓名：</span>
					<input type="text" class="sjxm_inp" id="sjxm" placeholder="请输入司机姓名"/>
					<span class="sjsfzh_span">司机身份证号：</span>
					<input type="text" class="sjsfzh_inp" id="sjsfzh" placeholder="请输入司机身份证号"/>
					<a class="search_but" id="search_but">查询</a>
					<a id="remove_but">删除</a>
				</div>
			</div>
			<table id="tab1">
			</table>
		</div>
	</div>
	
	<%@include file="../../inc/foot.jsp"%>
</div>
</body>
</html>