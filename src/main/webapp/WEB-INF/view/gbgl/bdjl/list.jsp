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
	position: fixed;
}
.tab1_div .toolbar{
	height:32px;
}
.tab1_div .toolbar .ddh_span{
	margin-left: 13px;
}
.tab1_div .toolbar .ddh_inp{
	width: 120px;height: 25px;
}
.tab1_div .toolbar .search_but{
	margin-left: 13px;
}

.preview_bdxx_bg_div{
	width: 100%;
	height: 100%;
	background-color: rgba(0,0,0,.45);
	position: fixed;
	z-index: 9016;
	display:none;
}
.preview_bdxx_div{
	width: 1000px;
	height: 570px;
	margin: 100px auto 0;
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
var gbglPath=path+'gbgl/';
var dialogTop=10;
var dialogLeft=20;
var pbdxxdNum=0;
var appendStr="";
$(function(){
	initSearchLB();
	initAddLB();
	initTab1();
	initPreviewBDXXDialog();//0
	
	initDialogPosition();//将不同窗体移动到主要内容区域
});

function initDialogPosition(){
	var pbdxxdpw=$("body").find(".panel.window").eq(pbdxxdNum);
	var pbdxxdws=$("body").find(".window-shadow").eq(pbdxxdNum);

	var pbdxxdDiv=$("#preview_bdxx_div");
	pbdxxdDiv.append(pbdxxdpw);
	pbdxxdDiv.append(pbdxxdws);
}

function initPreviewBDXXDialog(){
	dialogTop+=20;
	$("#preview_bdxx_dialog_div").dialog({
		title:"磅单信息",
		width:setFitWidthInParent("#preview_bdxx_div","preview_bdxx_dialog_div"),
		height:480,
		top:dialogTop,
		left:dialogLeft,
		buttons:[
           {text:"打印",id:"print_but",iconCls:"icon-ok",handler:function(){
        	   var time=new Date().getTime();
        	   var printHtml = $("#preview_bdxx_dialog_div .panel-body").html();
        	   $.post(gbglPath+"newDaYinJiLu",
        		  {time:time,html:printHtml},
        	   	  function(data){
        		   	 if(data.message=="ok")
        	        	window.open("print?time="+time);
        	      }
        	   ,"json");
        	   //window.document.body.innerHTML= $("#preview_bdxx_dialog_div .panel-body").html();
        	   //window.print();//打印上面新建的网页
        	   //window.document.body.innerHTML= pageHtml;
        	   //location.href=location.href;
           }},
           {text:"取消",id:"cancel_but",iconCls:"icon-cancel",handler:function(){
        	   openPreviewBDXXDialog(false,null);
           }}
        ]
	});

	/*
	$("#preview_bdxx_dialog_div table").css("width",(setFitWidthInParent("#preview_bdxx_div","preview_bdxx_dialog_div"))+"px");
	$("#preview_bdxx_dialog_div table").css("magin","-100px");
	$("#preview_bdxx_dialog_div table td").css("padding-left","40px");
	$("#preview_bdxx_dialog_div table td").css("padding-right","20px");
	$("#preview_bdxx_dialog_div table td").css("font-size","15px");
	$("#preview_bdxx_dialog_div table .td1").css("width","15%");
	$("#preview_bdxx_dialog_div table .td2").css("width","30%");
	$("#preview_bdxx_dialog_div table tr").css("border-bottom","#CAD9EA solid 1px");
	$("#preview_bdxx_dialog_div table tr").each(function(i){
		$(this).css("height","45px");
	});
	*/

	$(".panel.window").eq(pbdxxdNum).css("margin-top","20px");
	$(".panel.window .panel-title").eq(pbdxxdNum).css("color","#000");
	$(".panel.window .panel-title").eq(pbdxxdNum).css("font-size","15px");
	$(".panel.window .panel-title").eq(pbdxxdNum).css("padding-left","10px");
	
	$(".panel-header, .panel-body").css("border-color","#ddd");
	
	//以下的是表格下面的面板
	$(".window-shadow").eq(pbdxxdNum).css("margin-top","20px");
	$(".window,.window .window-body").eq(pbdxxdNum).css("border-color","#ddd");

	$("#preview_bdxx_dialog_div #print_but").css("left","30%");
	$("#preview_bdxx_dialog_div #print_but").css("position","absolute");

	$("#preview_bdxx_dialog_div #cancel_but").css("left","45%");
	$("#preview_bdxx_dialog_div #cancel_but").css("position","absolute");
	
	$(".dialog-button").css("background-color","#fff");
	$(".dialog-button .l-btn-text").css("font-size","20px");
	
	initPreviewModuleHtmlStr();
}

function initPreviewModuleHtmlStr(){
	var zsxm='${sessionScope.yongHu.zsxm}';
	 appendStr="<div style=\"width: 100%;height:40px;line-height:40px;text-align: center;font-size: 20px;font-weight: bold;\">山东蓝帆健康科技有限公司过磅单</div>";
  	    appendStr+="<div style=\"width: 90%;height:30px;line-height:30px;margin:auto;\">";
		appendStr+="<span style=\"margin-left: 10px;\">序号：</span>";
		appendStr+="<span id=\"xh_val_span\" style=\"margin-left: 20px;\"></span>";
	    appendStr+="<span style=\"margin-left: 50px;\">单位：公斤</span>";
    appendStr+="</div>";
	appendStr+="<table border=\"1\" style=\"width: 90%;margin:auto;text-align: center;border-color: #000;border-spacing:0;\">";
		appendStr+="<tr style=\"height: 25px;\">";
			appendStr+="<td class=\"ch_key_td\" style=\"width: 15%;\">车号</td>";
			appendStr+="<td class=\"ch_val_td\" id=\"ch_val_td\" colspan=\"3\" style=\"width: 20%;\"></td>";
			appendStr+="<td class=\"hp_key_td\" style=\"width: 10%;\">货品</td>";
			appendStr+="<td class=\"hp_val_td\" id=\"hp_val_td\" colspan=\"3\" style=\"width: 20%;\"></td>";
		appendStr+="</tr>";
		appendStr+="<tr style=\"height: 25px;\">";
			appendStr+="<td class=\"shdw_key_td\" style=\"width: 15%;\">收货单位</td>";
			appendStr+="<td class=\"shdw_val_td\" id=\"shdw_val_td\" colspan=\"3\" style=\"width: 38%;\"></td>";
			appendStr+="<td class=\"fhdw_key_td\" style=\"width: 12%;\">发货单位</td>";
			appendStr+="<td class=\"fhdw_val_td\" id=\"fhdw_val_td\" colspan=\"3\" style=\"width: 40%;\"></td>";
		appendStr+="</tr>";
		appendStr+="<tr style=\"height: 25px;\">";
			appendStr+="<td class=\"mz_key_td\" style=\"width: 15%;\">毛重</td>";
			appendStr+="<td class=\"mz_val_td\" id=\"mz_val_td\" style=\"width: 15%;\"></td>";
			appendStr+="<td class=\"mzrqsj_key_td\" style=\"width: 15%;\">日期时间</td>";
			appendStr+="<td class=\"mzrqsj_val_td\" id=\"mzrqsj_val_td\" colspan=\"3\" style=\"width: 30%;\"></td>";
			appendStr+="<td class=\"mzsby_key_td\" style=\"width: 10%;\">管理员</td>";
			appendStr+="<td class=\"mzsby_val_td\" style=\"width: 15%;\">"+zsxm+"</td>";
		appendStr+="</tr>";
		appendStr+="<tr style=\"height: 25px;\">";
			appendStr+="<td class=\"pz_key_td\" style=\"width: 15%;\">皮重</td>";
			appendStr+="<td class=\"pz_val_td\" id=\"pz_val_td\" style=\"width: 15%;\"></td>";
			appendStr+="<td class=\"pzrqsj_key_td\" style=\"width: 15%;\">日期时间</td>";
			appendStr+="<td class=\"pzrqsj_val_td\" id=\"pzrqsj_val_td\" colspan=\"3\" style=\"width: 30%;\"></td>";
			appendStr+="<td class=\"pzsby_key_td\" style=\"width: 10%;\">管理员</td>";
			appendStr+="<td class=\"pzsby_val_td\" style=\"width: 15%;\">"+zsxm+"</td>";
		appendStr+="</tr>";
		appendStr+="<tr style=\"height: 25px;\">";
			appendStr+="<td class=\"jz_key_td\" style=\"width: 15%;\">净重</td>";
			appendStr+="<td class=\"jz_val_td\" id=\"jz_val_td\" style=\"width: 15%;\"></td>";
			appendStr+="<td class=\"jzsj_key_td\" style=\"width: 10%;\">司机</td>";
			appendStr+="<td class=\"jzsj_val_td\" id=\"jzsj_val_td\" colspan=\"5\" style=\"width: 60%;\"></td>";
		appendStr+="</tr>";
	appendStr+="</table>";
}

function initSearchLB(){
	$("#search_but").linkbutton({
		iconCls:"icon-search",
		onClick:function(){
			var ddh=$("#toolbar #ddh").val();
			tab1.datagrid("load",{ddh:ddh});
		}
	});
}

function initAddLB(){
	$("#add_but").linkbutton({
		iconCls:"icon-add",
		onClick:function(){
			location.href=gbglPath+"bdjl/new";
		}
	});
}

function initTab1(){
	tab1=$("#tab1").datagrid({
		title:"磅单记录查询",
		url:gbglPath+"queryBDJLList",
		toolbar:"#toolbar",
		width:setFitWidthInParent("body"),
		pagination:true,
		pageSize:10,
		columns:[[
			{field:"ddh",title:"订单号",width:150},
			{field:"mz",title:"毛重",width:150},
			{field:"pz",title:"皮重",width:150},
			{field:"jz",title:"净重",width:150},
            {field:"rq",title:"日期",width:150},
            {field:"id",title:"操作",width:150,formatter:function(value,row){
       			var rowJson = JSON.stringify(row).replace(/"/g, '&quot;');
            	var str="<a href=\"edit?id="+value+"\">编辑</a>&nbsp;&nbsp;"
            		+"<a href=\"detail?id="+value+"\">详情</a>&nbsp;&nbsp;"
            		+"<a onclick=\"openPreviewBDXXDialog(true,"+rowJson+")\">预览</a>";
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

function openPreviewBDXXDialog(flag,row){
	var panelBody=$("#preview_bdxx_dialog_div .panel-body");
	panelBody.empty();
	if(flag){
		panelBody.append(appendStr);
		
		$("#preview_bdxx_bg_div").css("display","block");
		$("#preview_bdxx_div #xh_val_span").text(row.ddh);
		$("#preview_bdxx_div #rq_val_span").text(row.rq);
		$("#preview_bdxx_div table #ch_val_td").text(row.cph);
		$("#preview_bdxx_div table #hp_val_td").text(row.wzMc);
		$("#preview_bdxx_div table #shdw_val_td").text(row.shbmMc);
		$("#preview_bdxx_div table #fhdw_val_td").text(row.fhdwMc);
		$("#preview_bdxx_div table #mz_val_td").text(row.mz);
		$("#preview_bdxx_div table #mzrqsj_val_td").text(row.sygbsj);
		$("#preview_bdxx_div table #pz_val_td").text(row.pz);
		$("#preview_bdxx_div table #pzrqsj_val_td").text(row.qygbsj);
		$("#preview_bdxx_div table #jz_val_td").text(row.jz);
		$("#preview_bdxx_div table #jzsj_val_td").text(row.sjxm);
	}
	else{
		$("#preview_bdxx_bg_div").css("display","none");
		$("#preview_bdxx_div #xh_val_span").text("");
		$("#preview_bdxx_div #rq_val_span").text("");
		$("#preview_bdxx_div table #ch_val_td").text("");
		$("#preview_bdxx_div table #hp_val_td").text("");
		$("#preview_bdxx_div table #shdw_val_td").text("");
		$("#preview_bdxx_div table #fhdw_val_td").text("");
		$("#preview_bdxx_div table #mz_val_td").text("");
		$("#preview_bdxx_div table #mzrqsj_val_td").text("");
		$("#preview_bdxx_div table #pz_val_td").text("");
		$("#preview_bdxx_div table #pzrqsj_val_td").text("");
		$("#preview_bdxx_div table #jz_val_td").text("");
		$("#preview_bdxx_div table #jzsj_val_td").text("");
	}
}

function setFitWidthInParent(parent,self){
	var space=0;
	switch (self) {
	case "tab1_div":
		space=250;
		break;
	case "preview_bdxx_dialog_div":
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
	<div class="tab1_div" id="tab1_div">
		<div class="toolbar" id="toolbar">
			<span class="ddh_span">订单号：</span>
			<input type="text" class="ddh_inp" id="ddh" placeholder="请输入订单号"/>
			<a class="search_but" id="search_but">查询</a>
			<a id="add_but">添加</a>
		</div>
		<table id="tab1">
		</table>
	</div>
	
	<div class="preview_bdxx_bg_div" id="preview_bdxx_bg_div">
		<div class="preview_bdxx_div" id="preview_bdxx_div">
			<div class="preview_bdxx_dialog_div" id="preview_bdxx_dialog_div">
			</div>
		</div>
	</div>
	
	<%@include file="../../inc/foot.jsp"%>
</div>
</body>
</html>