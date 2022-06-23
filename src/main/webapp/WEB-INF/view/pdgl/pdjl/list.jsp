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
.tab1_div .toolbar .cph_span{
	margin-left: 13px;
}
.tab1_div .toolbar .ddh_inp,
.tab1_div .toolbar .cph_inp{
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
var pdglPath=path+'pdgl/';
var gkjPath=path+'gkj/';
var syncToQyTab='${requestScope.syncToQyTab}';
$(function(){
	initSearchLB();
	initRemoveLB();
	syncToQy();
	showCompontByQx();
});

function showCompontByQx(){
	removeLB.hide();
	if(yhm=="admin"){
		removeLB.show();
	}
}

function initSearchLB(){
	$("#search_but").linkbutton({
		iconCls:"icon-search",
		onClick:function(){
			var ddh=$("#toolbar #ddh").val();
			var cph=$("#toolbar #cph").val();
			tab1.datagrid("load",{ddh:ddh,cph:cph});
		}
	});
}

function initRemoveLB(){
	removeLB=$("#remove_but").linkbutton({
		iconCls:"icon-remove",
		onClick:function(){
			
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
		title:"排队记录查询",
		url:pdglPath+"queryPDJLList",
		toolbar:"#toolbar",
		width:setFitWidthInParent("body"),
		pagination:true,
		pageSize:10,
		columns:[[
			{field:"ddh",title:"订单号",width:150},
			{field:"cph",title:"车牌号",width:150},
			{field:"dlh",title:"队列号",width:100},
            {field:"pdh",title:"排队号",width:100},
            {field:"pdsj",title:"排队时间",width:150},
			{field:"zt",title:"状态",width:100,formatter:function(value,row){
				var str;
				switch (value) {
				case 1:
					str="排队中";
					break;
				case 2:
					str="已完成";
					break;
				case 3:
					str="已过号";
					break;
				}
            	return str;
			}},
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

function deleteByIds(ids){
	$.post(wzglPath + "deleteWuZiLeiXing",
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
		<div class="page_location_div">排队管理-排队记录</div>
		<div class="tab1_div" id="tab1_div">
			<div class="toolbar" id="toolbar">
				<span class="ddh_span">订单号：</span>
				<input type="text" class="ddh_inp" id="ddh" placeholder="请输入订单号"/>
				<span class="cph_span">车牌号：</span>
				<input type="text" class="cph_inp" id="cph" placeholder="请输入车牌号"/>
				<a class="search_but" id="search_but">查询</a>
				<a id="remove_but">删除</a>
			</div>
			<table id="tab1">
			</table>
		</div>
	</div>
	<%@include file="../../inc/foot.jsp"%>
</div>
</body>
</html>