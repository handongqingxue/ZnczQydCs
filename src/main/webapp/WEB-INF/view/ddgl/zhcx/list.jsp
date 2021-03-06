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
.tab1_div .toolbar .row_div .ddzt_span,
.tab1_div .toolbar .row_div .cph_span,
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
.tab1_div .toolbar .row_div .yssMc_inp,
.tab1_div .toolbar .row_div .wzMc_inp,
.tab1_div .toolbar .row_div .fhdwMc_inp,
.tab1_div .toolbar .row_div .shbmMc_inp,
.tab1_div .toolbar .row_div .sjxm_inp,
.tab1_div .toolbar .row_div .sjsfzh_inp{
	width: 120px;
	height: 25px;
}
.tab1_div .check_a{
	visibility: hidden;
}

.check_ddxx_bg_div,.input_cph_bg_div{
	width: 100%;
	height: 100%;
	background-color: rgba(0,0,0,.45);
	position: fixed;
	z-index: 9016;
	display:none;
}
.check_ddxx_div{
	width: 1000px;
	height: 435px;
	margin: 100px auto 0;
	background-color: #fff;
	border-radius:5px;
	position: absolute;
	left: 0;
	right: 0;
}

.input_cph_div{
	width: 500px;
	height: 300px;
	margin: 200px auto 0;
	background-color: #fff;
	border-radius:5px;
	position: absolute;
	left: 0;
	right: 0;
}
</style>
<title>Insert title here</title>
<%@include file="../../inc/js.jsp"%>
<script type="text/javascript">
var path='<%=basePath %>';
var ddglPath=path+'ddgl/';
var gkjPath=path+'gkj/';
var dialogTop=10;
var dialogLeft=20;
var cddxxdNum=0;
var icphdNum=1;
var dshDdztMc='${requestScope.dshDdztMc}';
var syncToYfTab='${requestScope.syncToYfTab}';
var syncToQyTab='${requestScope.syncToQyTab}';
$(function(){
	initDDZTCBB();
	initSearchLB();
	initManualLB();
	initDdfwLB();
	initAddLB();
	initRemoveLB();
	syncToQy();
	initCheckDDXXDialog();//0
	initInputCphDialog();//1
	
	initDialogPosition();//??????????????????????????????????????????
	showCompontByQx();
});

function showCompontByQx(){
	manualLB.hide();
	addLB.hide();
	removeLB.hide();
	//alert(yhm)
	if(yhm=="admin"){
		manualLB.show();
		addLB.show();
		removeLB.show();
	}
	else{
		var qxIdsArr=qxIds.split(",");
		for(var i=0;i<qxIdsArr.length;i++){
			if(qxIdsArr[i]==1){//??????
			}
			if(qxIdsArr[i]==2){//?????????
				addLB.show();
				setTimeout(function(){
					$(".tab1_div .check_a").css("visibility","visible");
				},2000)
			}
			if(qxIdsArr[i]==3){//????????????
				manualLB.show();
			}
		}
	}
}

function initDialogPosition(){
	var cddxxdpw=$("body").find(".panel.window").eq(cddxxdNum);
	var cddxxdws=$("body").find(".window-shadow").eq(cddxxdNum);

	var icphdpw=$("body").find(".panel.window").eq(icphdNum);
	var icphdws=$("body").find(".window-shadow").eq(icphdNum);

	var cddxxdDiv=$("#check_ddxx_div");
	cddxxdDiv.append(cddxxdpw);
	cddxxdDiv.append(cddxxdws);

	var icphdDiv=$("#input_cph_div");
	icphdDiv.append(icphdpw);
	icphdDiv.append(icphdws);
}

function initCheckDDXXDialog(){
	dialogTop+=20;
	$("#check_ddxx_dialog_div").dialog({
		title:"????????????",
		width:setFitWidthInParent("#check_ddxx_div","check_ddxx_dialog_div"),
		height:345,
		top:dialogTop,
		left:dialogLeft,
		buttons:[
           {text:"??????",id:"ok_but",iconCls:"icon-ok",handler:function(){
        	   checkById();
           }},
           {text:"??????",id:"cancel_but",iconCls:"icon-cancel",handler:function(){
        	   openCheckDDXXDialog(false,null);
           }}
        ]
	});

	$("#check_ddxx_dialog_div table").css("width",(setFitWidthInParent("#check_ddxx_div","check_ddxx_dialog_table"))+"px");
	$("#check_ddxx_dialog_div table").css("magin","-100px");
	$("#check_ddxx_dialog_div table td").css("padding-left","40px");
	$("#check_ddxx_dialog_div table td").css("padding-right","20px");
	$("#check_ddxx_dialog_div table td").css("font-size","15px");
	$("#check_ddxx_dialog_div table .td1").css("width","15%");
	$("#check_ddxx_dialog_div table .td2").css("width","30%");
	$("#check_ddxx_dialog_div table tr").css("border-bottom","#CAD9EA solid 1px");
	$("#check_ddxx_dialog_div table tr").each(function(i){
		$(this).css("height","45px");
	});

	$(".panel.window").eq(cddxxdNum).css("margin-top","20px");
	$(".panel.window .panel-title").eq(cddxxdNum).css("color","#000");
	$(".panel.window .panel-title").eq(cddxxdNum).css("font-size","15px");
	$(".panel.window .panel-title").eq(cddxxdNum).css("padding-left","10px");
	
	$(".panel-header, .panel-body").css("border-color","#ddd");
	
	//?????????????????????????????????
	$(".window-shadow").eq(cddxxdNum).css("margin-top","20px");
	$(".window,.window .window-body").eq(cddxxdNum).css("border-color","#ddd");

	$("#check_ddxx_dialog_div #ok_but").css("left","30%");
	$("#check_ddxx_dialog_div #ok_but").css("position","absolute");

	$("#check_ddxx_dialog_div #cancel_but").css("left","45%");
	$("#check_ddxx_dialog_div #cancel_but").css("position","absolute");
	
	$(".dialog-button").css("background-color","#fff");
	$(".dialog-button .l-btn-text").css("font-size","20px");
}

function initInputCphDialog(){
	$("#input_cph_dialog_div").dialog({
		title:"???????????????",
		width:setFitWidthInParent("#input_cph_div","input_cph_dialog_div"),
		height:250,
		top:5,
		left:dialogLeft,
		buttons:[
           {text:"??????",id:"ok_but",iconCls:"icon-ok",handler:function(){
        	   checkCphToClient();
           }},
           {text:"??????",id:"cancel_but",iconCls:"icon-cancel",handler:function(){
        	   openInputCphDialog(false);
           }}
        ]
	});

	$("#input_cph_dialog_div table").css("width",(setFitWidthInParent("#input_cph_div","input_cph_dialog_table"))+"px");
	$("#input_cph_dialog_div table").css("magin","-100px");
	$("#input_cph_dialog_div table td").css("padding-left","40px");
	$("#input_cph_dialog_div table td").css("padding-right","20px");
	$("#input_cph_dialog_div table td").css("font-size","15px");
	$("#input_cph_dialog_div table .td1").css("width","30%");
	$("#input_cph_dialog_div table .td2").css("width","60%");
	$("#input_cph_dialog_div table tr").css("height","45px");

	$(".panel.window").eq(icphdNum).css("margin-top","20px");
	$(".panel.window .panel-title").eq(icphdNum).css("color","#000");
	$(".panel.window .panel-title").eq(icphdNum).css("font-size","15px");
	$(".panel.window .panel-title").eq(icphdNum).css("padding-left","10px");
	
	$(".panel-header, .panel-body").css("border-color","#ddd");
	
	//?????????????????????????????????
	$(".window-shadow").eq(icphdNum).css("margin-top","20px");
	$(".window,.window .window-body").eq(icphdNum).css("border-color","#ddd");

	$("#input_cph_dialog_div #ok_but").css("left","30%");
	$("#input_cph_dialog_div #ok_but").css("position","absolute");

	$("#input_cph_dialog_div #cancel_but").css("left","50%");
	$("#input_cph_dialog_div #cancel_but").css("position","absolute");
	
	$(".dialog-button").css("background-color","#fff");
	$(".dialog-button .l-btn-text").css("font-size","20px");

	initBFHCBB();
	initXzCphCBB();
	initLrSjcCBB();
	initLrWscphCBB();
}

function initBFHCBB(){
	var data=[];
	data.push({"value":"","text":"?????????"});
	data.push({"value":1,"text":"?????????"});
	data.push({"value":2,"text":"?????????"});
	
	bfhCBB=$("#bfh_cbb").combobox({
		width:120,
		valueField:"value",
		textField:"text",
		//multiple:true,
		data:data
	});
}

function initXzCphCBB(){
	var data=[];
	data.push({"value":"","text":"?????????"});
	$.post(ddglPath+"queryXzCphCBBList",
		{page:1,rows:20,sort:"lrsj",order:"desc"},
		function(result){
			var rows=result.rows;
			for(var i=0;i<rows.length;i++){
				data.push({"value":rows[i],"text":rows[i]});
			}
			xzcphCBB=$("#xzcph_cbb").combobox({
				width:120,
				valueField:"value",
				textField:"text",
				data:data,
				onChange:function(){
					var cph=xzcphCBB.combobox("getValue");
					lrSjcCBB.combobox("setValue",cph.substring(0,1));
					lrWscphCBB.combobox("setValue",cph.substring(1));
				}
			});
		}
	,"json");
}

function initLrSjcCBB(){
	var data=[];
	data.push({"value":"","text":"?????????"});
	lrSjcCBB=$("#lrSjc_cbb").combobox({
		width:50,
		valueField:"sjc",
		textField:"sjc",
		editable:true,
        mode:'remote',
        url:ddglPath+"queryLrSjcCBBList",
        onBeforeLoad: function(param){
    		param.page = 1;
    		param.rows = 100;
    		param.sort = "lrsj";
    		param.order = "desc";
    	}
	});
}

function initLrWscphCBB(){
	var data=[];
	data.push({"value":"","text":"?????????"});
	lrWscphCBB=$("#lrWscph_cbb").combobox({
		width:70,
		valueField:"wscph",
		textField:"wscph",
		editable:true,
        mode:'remote',
        url:ddglPath+"queryLrWscphCBBList",
        onBeforeLoad: function(param){
        	var sjc=lrSjcCBB.combobox("getValue");
        	if(sjc==null||sjc==""){
        	  	return false;
        	}
    		param.sjc = sjc;
    		param.page = 1;
    		param.rows = 100;
    		param.sort = "lrsj";
    		param.order = "desc";
    	}
	});
}

function checkCphToClient(){
	if(checkBfh()){
		if(checkRglrCph()){
			sendCphToClient();
		}
	}
}

function sendCphToClient(){
	var rows=tab1.datagrid("getSelections");
	var bfNoFlag=bfhCBB.combobox("getValue");
	var jyFlag=0;
	var sjc=lrSjcCBB.combobox("getValue");
	var wscph=lrWscphCBB.combobox("getValue");
	var cph=sjc+wscph;
	if(cph!=rows[0].cph){
		alert("???????????????????????????????????????????????????");
		return false;
	}
	var ddztMc=rows[0].ddztMc;
	if(ddztMc=='${requestScope.yjpdzDdztMc}')
		jyFlag=1
	else if(ddztMc=='${requestScope.ejpdzDdztMc}')
		jyFlag=2
	else{
		alert("???????????????????????????");
		return false;
	}
	var ddId=rows[0].id;
	$.post(gkjPath+"sendCphToClient",
		{ddId:ddId,cph:cph,bfNoFlag:bfNoFlag,jyFlag:jyFlag},
		function(data){
			if(data.status=="ok"){
				openInputCphDialog(false);
			}
		}
	,"json");
}

//???????????????
function checkBfh(){
	var bfh=bfhCBB.combobox("getValue");
	if(bfh==null||bfh==""){
	  	alert("???????????????");
	  	return false;
	}
	else
		return true;
}

//???????????????????????????
function checkRglrCph(){
	var sjc=lrSjcCBB.combobox("getValue");
	var wscph=lrWscphCBB.combobox("getValue");
	if(sjc==null||sjc==""||wscph==null||wscph==""){
	  	alert("??????????????????");
	  	return false;
	}
	else
		return true;
}

function initDDZTCBB(){
	var data=[];
	data.push({"value":"","text":"?????????"});
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
			var ddh=$("#toolbar #ddh").val();
			var ddztId=ddztCBB.combobox("getValue");
			var cph=$("#toolbar #cph").val();
			var yssMc=$("#toolbar #yssMc").val();
			var wzMc=$("#toolbar #wzMc").val();
			var fhdwMc=$("#toolbar #fhdwMc").val();
			var shbmMc=$("#toolbar #shbmMc").val();
			var sjxm=$("#toolbar #sjxm").val();
			var sjsfzh=$("#toolbar #sjsfzh").val();
			tab1.datagrid("load",{ddh:ddh,ddztId:ddztId,cph:cph,yssMc:yssMc,wzMc:wzMc,
				fhdwMc:fhdwMc,shbmMc:shbmMc,sjxm:sjxm,sjsfzh:sjsfzh});
		}
	});
}

function initManualLB(){
	manualLB=$("#manual_but").linkbutton({
		iconCls:"icon-save",
		onClick:function(){
			var rows=tab1.datagrid("getSelections");
			if (rows.length == 0) {
				$.messager.alert("??????","???????????????????????????????????????","warning");
				return false;
			}
			openInputCphDialog(true);
		}
	});
}

function initDdfwLB(){
	ddfwLB=$("#ddfw_but").linkbutton({
		iconCls:"icon-back",
		onClick:function(){
			var rows=tab1.datagrid("getSelections");
			if (rows.length == 0) {
				$.messager.alert("??????","????????????????????????????????????","warning");
				return false;
			}
			if(checkFwddZt()){
				fwddById();
			}
		}
	});
}

function initAddLB(){
	addLB=$("#add_but").linkbutton({
		iconCls:"icon-add",
		onClick:function(){
			location.href=ddglPath+"zhcx/new";
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
		title:"????????????",
		url:ddglPath+"queryZHCXList",
		toolbar:"#toolbar",
		width:setFitWidthInParent("body","tab1_div"),
		pagination:true,
		pageSize:10,
		columns:[[
            {field:"id",title:"??????",width:150,formatter:function(value,row){
            	var str;
           		if(row.id!="<div style=\"text-align:center;\">????????????<div>"){
	            	str="<a href=\"edit?id="+value+"\">??????</a>&nbsp;&nbsp;"
	            	   +"<a href=\"detail?id="+value+"&syncToYfTab="+syncToYfTab+"\">??????</a>&nbsp;&nbsp;";
	           		if(row.ddztMc==dshDdztMc){
	           			var rowJson = JSON.stringify(row).replace(/"/g, '&quot;');
	           			str+="<a class=\"check_a\" onclick=\"openCheckDDXXDialog(true,"+rowJson+")\">??????</a>";
	           		}
           		}
           		else
           			str=value;
            	return str;
            }},
			{field:"ddh",title:"?????????",width:150},
			{field:"ddztMc",title:"????????????",width:150},
			{field:"yjzt",title:"????????????",width:100,formatter:function(value,row){
            	var str;
            	switch (value) {
				case 1:
					str="?????????";
					break;
				case 2:
					str="?????????";
					break;
				case 3:
					str="?????????";
					break;
				case 4:
					str="?????????";
					break;
				case 5:
					str="?????????";
					break;
				case 6:
					str="?????????";
					break;
				case 7:
					str="?????????";
					break;
				}
            	return str;
            }},
			{field:"ejzt",title:"????????????",width:100,formatter:function(value,row){
            	var str;
            	switch (value) {
				case 1:
					str="?????????";
					break;
				case 2:
					str="?????????";
					break;
				case 3:
					str="?????????";
					break;
				case 4:
					str="?????????";
					break;
				case 5:
					str="?????????";
					break;
				case 6:
					str="?????????";
					break;
				case 7:
					str="?????????";
					break;
				}
            	return str;
            }},
			{field:"sjxm",title:"????????????",width:100},
			{field:"cph",title:"?????????",width:150},
			{field:"wzlxMc",title:"????????????",width:150},
			{field:"wzMc",title:"????????????",width:150},
			{field:"yssMc",title:"?????????",width:150},
			{field:"fhdwMc",title:"????????????",width:150},
			{field:"shbmMc",title:"????????????",width:150},
            {field:"lxlx",title:"????????????",width:100,formatter:function(value,row){
            	var str;
            	switch (value) {
				case 1:
					str="??????";
					break;
				case 2:
					str="??????";
					break;
				}
            	return str;
            }},
            {field:"sjzl",title:"????????????",width:100},
            {field:"yjbfh",title:"????????????",width:100,formatter:function(value,row){
            	var dbm;
            	switch (value) {
				case 1:
					dbm="?????????";
					break;
				case 2:
					dbm="??????";
					break;
				}
            	return dbm;
            }},
            {field:"ejbfh",title:"????????????",width:100,formatter:function(value,row){
            	var dbm;
            	switch (value) {
				case 1:
					dbm="?????????";
					break;
				case 2:
					dbm="??????";
					break;
				}
            	return dbm;
            }},
            {field:"bjsj",title:"????????????",width:150}
	    ]],
        onLoadSuccess:function(data){
			if(data.total==0){
				$(this).datagrid("appendRow",{id:"<div style=\"text-align:center;\">????????????<div>"});
				$(this).datagrid("mergeCells",{index:0,field:"id",colspan:17});
				data.total=0;
			}
			
			$(".panel-header .panel-title").css("color","#000");
			$(".panel-header .panel-title").css("font-size","15px");
			$(".panel-header .panel-title").css("padding-left","10px");
			$(".panel-header, .panel-body").css("border-color","#ddd");
		}
	});
}

function openCheckDDXXDialog(flag,row){
	if(flag){
		$("#check_ddxx_bg_div").css("display","block");
		$("#check_ddxx_div #id").val(row.id);
		$("#check_ddxx_div #ddh_span").text(row.ddh);
		$("#check_ddxx_div #sjsfzh_span").text(row.sjsfzh);
		$("#check_ddxx_div #sjxm_span").text(row.sjxm);
		$("#check_ddxx_div #cph_span").text(row.cph);
		$("#check_ddxx_div #lxlxMc_span").text(row.lxlx==1?"??????":"??????");
		$("#check_ddxx_div #wzlxMc_span").text(row.wzlxMc);
		$("#check_ddxx_div #wzMc_span").text(row.wzMc);
		$("#check_ddxx_div #yssMc_span").text(row.yssMc);
		$("#check_ddxx_div #fhdwMc_span").text(row.fhdwMc);
		$("#check_ddxx_div #shbmMc_span").text(row.shbmMc);
	}
	else{
		$("#check_ddxx_bg_div").css("display","none");
		$("#check_ddxx_div #id").val("");
		$("#check_ddxx_div #ddh_span").text("");
		$("#check_ddxx_div #sjsfzh_span").text("");
		$("#check_ddxx_div #sjxm_span").text("");
		$("#check_ddxx_div #cph_span").text("");
		$("#check_ddxx_div #lxlxMc_span").text("");
		$("#check_ddxx_div #wzlxMc_span").text("");
		$("#check_ddxx_div #wzMc_span").text("");
		$("#check_ddxx_div #yssMc_span").text("");
		$("#check_ddxx_div #fhdwMc_span").text("");
		$("#check_ddxx_div #shbmMc_span").text("");
	}
}

function openInputCphDialog(flag){
	if(flag){
		$("#input_cph_bg_div").css("display","block");
	}
	else{
		$("#input_cph_bg_div").css("display","none");
	}
}

function checkById(){
	var id=$("#check_ddxx_div #id").val();
	var ddztMc;
	var sjsfzh=$("#check_ddxx_div #sjsfzh_span").text();
	var sjxm=$("#check_ddxx_div #sjxm_span").text();
	var cph=$("#check_ddxx_div #cph_span").text();
	if(sjsfzh==""||sjxm==""||cph=="")
		ddztMc=$("#check_ddxx_div #yshDdztMc").val();
	else
		ddztMc='${requestScope.dzjDdztMc}';
	var shlx='${requestScope.shlx}';
	var shrId='${sessionScope.yongHu.id}';
	$.post(ddglPath + "checkDingDanByIds",
		{ids:id,ddztMc:ddztMc,shlx:shlx,shjg:true,shrId:shrId},
		function(result){
			if(result.status==1){
				syncToYf(result.msg);
			}
			else{
				alert(result.msg);
			}
		}
	,"json");
}

function syncToYf(cddri){
	$.post(gkjPath+"syncToYf",
		{tabArrStr:syncToYfTab},
		function(data){
			if(data.status=="ok"){
				alert(cddri);
				openCheckDDXXDialog(false,null);
				tab1.datagrid("load");
			}
		}
	,"json");
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

function deleteByIds() {
	var rows=tab1.datagrid("getSelections");
	if (rows.length == 0) {
		$.messager.alert("??????","??????????????????????????????","warning");
		return false;
	}
	
	$.messager.confirm("??????","?????????????????????",function(r){
		if(r){
			var ids = "";
			for (var i = 0; i < rows.length; i++) {
				ids += "," + rows[i].id;
			}
			ids=ids.substring(1);
			
			$.post(ddglPath + "deleteDingDan",
				{ids:ids},
				function(result){
					if(result.status==1){
						alert(result.msg);
						location.href = location.href;
					}
					else{
						alert(result.msg);
					}
				}
			,"json");
			
		}
	});
}

function checkFwddZt(){
	var rows=tab1.datagrid("getSelections");
	var ddztMc=rows[0].ddztMc;
	var yjsbDdztMc='${requestScope.yjsbDdztMc}';
	var ejsbDdztMc='${requestScope.ejsbDdztMc}';
	if(ddztMc==yjsbDdztMc)
		return true;
	else if(ddztMc==ejsbDdztMc)
		return true;
	else{
		alert("????????????????????????");
		return false;
	}
}

function fwddById(){
	var jyFlag=0;
	var rows=tab1.datagrid("getSelections");
	var id=rows[0].id;
	var ddztMc=rows[0].ddztMc;
	var yjsbDdztMc='${requestScope.yjsbDdztMc}';
	var ejsbDdztMc='${requestScope.ejsbDdztMc}';
	var xddztMc;
	var yjzt;
	var ejzt;
	if(ddztMc==yjsbDdztMc){
		jyFlag=1
		yjzt=1;
		xddztMc='${requestScope.yjpdzDdztMc}';
	}
	else if(ddztMc==ejsbDdztMc){
		jyFlag=2
		ejzt=1;
		xddztMc='${requestScope.ejpdzDdztMc}';
	}
	$.post(ddglPath+"fwddById",
		{id:id,jyFlag:jyFlag,ddztMc:xddztMc,yjzt:yjzt,ejzt:ejzt},
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
	case "input_cph_dialog_div":
		space=50;
		break;
	case "check_ddxx_dialog_table":
	case "input_cph_dialog_table":
		space=68;
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
		<div class="page_location_div">????????????-????????????</div>
		<div class="tab1_div" id="tab1_div">
			<div class="toolbar" id="toolbar">
				<div class="row_div">
					<span class="ddh_span">????????????</span>
					<input type="text" class="ddh_inp" id="ddh" placeholder="??????????????????"/>
					<span class="ddzt_span">???????????????</span>
					<input id="ddzt_cbb"/>
					<span class="cph_span">????????????</span>
					<input type="text" class="cph_inp" id="cph" placeholder="??????????????????"/>
					<span class="yss_span">????????????</span>
					<input type="text" class="yssMc_inp" id="yssMc" placeholder="??????????????????"/>
					<span class="wzMc_span">???????????????</span>
					<input type="text" class="wzMc_inp" id="wzMc" placeholder="?????????????????????"/>
				</div>
				<div class="row_div">
					<span class="fhdw_span">???????????????</span>
					<input type="text" class="fhdwMc_inp" id="fhdwMc" placeholder="?????????????????????"/>
					<span class="shbm_span">???????????????</span>
					<input type="text" class="shbmMc_inp" id="shbmMc" placeholder="?????????????????????"/>
					<span class="sjxm_span">???????????????</span>
					<input type="text" class="sjxm_inp" id="sjxm" placeholder="?????????????????????"/>
					<span class="sjsfzh_span">?????????????????????</span>
					<input type="text" class="sjsfzh_inp" id="sjsfzh" placeholder="???????????????????????????"/>
					<a class="search_but" id="search_but">??????</a>
					<a id="manual_but">??????</a>
					<a id="ddfw_but">????????????</a>
					<a id="add_but">??????</a>
					<a id="remove_but">??????</a>
				</div>
			</div>
			<table id="tab1">
			</table>
		</div>
	
		<div class="check_ddxx_bg_div" id="check_ddxx_bg_div">
			<div class="check_ddxx_div" id="check_ddxx_div">
				<div class="check_ddxx_dialog_div" id="check_ddxx_dialog_div">
					<input type="hidden" id="id"/>
					<input type="hidden" id="yshDdztMc" value="${requestScope.yshDdztMc}"/>
					<table>
					  <tr>
						<td class="td1" align="right">
							?????????
						</td>
						<td class="td2">
							<span id="ddh_span"></span>
						</td>
						<td class="td1" align="right">
							??????????????????
						</td>
						<td class="td2">
							<span id="sjsfzh_span"></span>
						</td>
					  </tr>
					  <tr>
						<td class="td1" align="right">
							????????????
						</td>
						<td class="td2">
							<span id="sjxm_span"></span>
						</td>
						<td class="td1" align="right">
							?????????
						</td>
						<td class="td2">
							<span id="cph_span"></span>
						</td>
					  </tr>
					  <tr>
						<td class="td1" align="right">
							????????????
						</td>
						<td class="td2">
							<span id="lxlxMc_span"></span>
						</td>
						<td class="td1" align="right">
							????????????
						</td>
						<td class="td2">
							<span id="wzlxMc_span"></span>
						</td>
					  </tr>
					  <tr>
						<td class="td1" align="right">
							????????????
						</td>
						<td class="td2">
							<span id="wzMc_span"></span>
						</td>
						<td class="td1" align="right">
							?????????
						</td>
						<td class="td2">
							<span id="yssMc_span"></span>
						</td>
					  </tr>
					  <tr>
						<td class="td1" align="right">
							????????????
						</td>
						<td class="td2">
							<span id="fhdwMc_span"></span>
						</td>
						<td class="td1" align="right">
							????????????
						</td>
						<td class="td2">
							<span id="shbmMc_span"></span>
						</td>
					  </tr>
					</table>
				</div>
			</div>
		</div>
	
		<div class="input_cph_bg_div" id="input_cph_bg_div">
			<div class="input_cph_div" id="input_cph_div">
				<div class="input_cph_dialog_div" id="input_cph_dialog_div">
					<table>
					  <tr>
						<td class="td1" align="right">
							??????
						</td>
						<td class="td2">
							<input id="bfh_cbb"/>
						</td>
					  </tr>
					  <tr>
						<td class="td1" align="right">
							??????
						</td>
						<td class="td2">
							<input id="xzcph_cbb"/>
						</td>
					  </tr>
					  <tr>
						<td class="td1" align="right">
							?????????
						</td>
						<td class="td2">
							<input id="lrSjc_cbb"/>
							<input id="lrWscph_cbb"/>
						</td>
					  </tr>
					</table>
				</div>
			</div>
		</div>
	</div>
	
	<%@include file="../../inc/foot.jsp"%>
</div>
</body>
</html>