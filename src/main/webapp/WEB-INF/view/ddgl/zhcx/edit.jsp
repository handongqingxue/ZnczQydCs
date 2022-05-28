<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<%@include file="../../inc/js.jsp"%>
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
.ddh_inp,.sjsfzh_inp{
	width: 180px;
	height:30px;
}
.sjxm_inp{
	width: 150px;
	height:30px;
}
.ewm_img{
	width: 120px;
	height:120px;
}
</style>
<script type="text/javascript">
var path='<%=basePath %>';
var ddglPath=path+'ddgl/';
var wzglPath=path+'wzgl/';
var dwglPath=path+'dwgl/';
var dialogTop=70;
var dialogLeft=20;
var edNum=0;
$(function(){
	initEditDialog();//0

	initDialogPosition();//将不同窗体移动到主要内容区域
	showCompontByQx();
});

function showCompontByQx(){
	if(yhm=="admin"){
		$("#edit_div #ddh").removeAttr("disabled");
		lxlxCBB.combobox({disabled:false});
		setTimeout(function(){
			qyWzlxCBB.combobox({disabled:false});
			yssCBB.combobox({disabled:false});
			qyFhdwCBB.combobox({disabled:false});
			qyShbmCBB.combobox({disabled:false});
		},"2000");
		qyWzCBB.combobox({disabled:false});
	}
	else{
		var qxIdsArr=qxIds.split(",");
		for(var i=0;i<qxIdsArr.length;i++){
			if(qxIdsArr[i]==2){
				$("#edit_div #ddh").removeAttr("disabled");
				lxlxCBB.combobox({disabled:false});
				setTimeout(function(){
					wzlxCBB.combobox({disabled:false});
					yssCBB.combobox({disabled:false});
					qyFhdwCBB.combobox({disabled:false});
					qyShbmCBB.combobox({disabled:false});
				},"2000");
				qyWzCBB.combobox({disabled:false});
			}
		}
	}
}

function initDialogPosition(){
	//基本属性组
	var edpw=$("body").find(".panel.window").eq(edNum);
	var edws=$("body").find(".window-shadow").eq(edNum);

	var ccDiv=$("#center_con_div");
	ccDiv.append(edpw);
	ccDiv.append(edws);
	ccDiv.css("width",setFitWidthInParent("body","center_con_div")+"px");
}

function initEditDialog(){
	dialogTop+=20;
	$("#edit_div").dialog({
		title:"订单信息",
		width:setFitWidthInParent("body","edit_div"),
		height:460,
		top:dialogTop,
		left:dialogLeft,
		buttons:[
           {text:"保存",id:"ok_but",iconCls:"icon-ok",handler:function(){
        	   checkEdit();
           }}
        ]
	});

	$("#edit_div table").css("width",(setFitWidthInParent("body","edit_div_table"))+"px");
	$("#edit_div table").css("magin","-100px");
	$("#edit_div table td").css("padding-left","50px");
	$("#edit_div table td").css("padding-right","20px");
	$("#edit_div table td").css("font-size","15px");
	$("#edit_div table .td1").css("width","15%");
	$("#edit_div table .td2").css("width","30%");
	$("#edit_div table tr").css("border-bottom","#CAD9EA solid 1px");
	$("#edit_div table tr").each(function(i){
		$(this).css("height",(i==5?135:45)+"px");
	});

	$(".panel.window").eq(edNum).css("margin-top","20px");
	$(".panel.window .panel-title").eq(edNum).css("color","#000");
	$(".panel.window .panel-title").eq(edNum).css("font-size","15px");
	$(".panel.window .panel-title").eq(edNum).css("padding-left","10px");
	
	$(".panel-header, .panel-body").css("border-color","#ddd");
	
	//以下的是表格下面的面板
	$(".window-shadow").eq(edNum).css("margin-top","20px");
	$(".window,.window .window-body").eq(edNum).css("border-color","#ddd");

	$("#edit_div #ok_but").css("left","45%");
	$("#edit_div #ok_but").css("position","absolute");
	
	$(".dialog-button").css("background-color","#fff");
	$(".dialog-button .l-btn-text").css("font-size","20px");

	initXZCPHCBB();
	initLRSJCCBB();
	initLXLXCBB();
	initQYWZLXCBB();
	initQYWZCBB();
	initQYYSSCBB();
	initQYFHDWCBB();
	initQYSHBMCBB();
	setTimeout(function(){
		loadLRCPHData();
		loadQyWZCBBData();
	},"1000");
}

function initXZCPHCBB(){
	var data=[];
	data.push({"value":"","text":"请选择"});
	$.post(ddglPath+"queryXzCphCBBList",
		{page:1,rows:20,sort:"lrsj",order:"desc"},
		function(result){
			var rows=result.rows;
			for(var i=0;i<rows.length;i++){
				data.push({"value":rows[i],"text":rows[i]});
			}
			xzcphCBB=$("#xzcph_cbb").combobox({
				valueField:"value",
				textField:"text",
				data:data,
				onChange:function(){
					var cph=xzcphCBB.combobox("getValue");
					lrSjcCBB.combobox("setValue",cph.substring(0,1));
					lrWscphCBB.combobox("setValue",cph.substring(1));
				},
		    	onSelect:function(){
		    		var cph=xzcphCBB.combobox("getValue");
		    		getDingDanByCphJL(cph);
		    	}
			});
		}
	,"json");
}

function initLRSJCCBB(){
	var data=[];
	data.push({"value":"","text":"请录入"});
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
    	},
		onLoadSuccess:function(){
			initLRWSCPHCBB();//这个下拉框是联动加载，必须在省下拉框加载完后才能加载
		}
	});
}

function initLRWSCPHCBB(){
	var data=[];
	data.push({"value":"","text":"请录入"});
	lrWscphCBB=$("#lrWscph_cbb").combobox({
		width:100,
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
    	},
    	onSelect:function(){
    		var sjc=lrSjcCBB.combobox("getValue");
    		var wscph=lrWscphCBB.combobox("getValue");
    		getDingDanByCphJL(sjc+wscph);
    	}
	});
}

function loadLRCPHData(){
	var cph='${requestScope.dd.cph }';
	lrSjcCBB.combobox("setValue",cph.substring(0,1));
	lrWscphCBB.combobox("setValue",cph.substring(1));
}

function initLXLXCBB(){
	lxlxCBB=$("#lxlx_cbb").combobox({
		valueField:"value",
		textField:"text",
		data:[
			{"value":"","text":"请选择流向类型"},{"value":"1","text":"送运"},{"value":"2","text":"取运"}
		],
		onLoadSuccess:function(){
			$(this).combobox("setValue",'${requestScope.dd.lxlx }');
		},
		onSelect:function(){
			$("#lxlx").val($(this).combobox("getValue"));
		}
	});
}

function initQYWZLXCBB(){
	var data=[];
	data.push({"value":"","text":"请选择物资类型"});
	$.post(wzglPath+"queryWuZiLeiXingCBBList",
		function(result){
			var rows=result.rows;
			for(var i=0;i<rows.length;i++){
				data.push({"value":rows[i].id,"text":rows[i].mc});
			}
			qyWzlxCBB=$("#edit_div #qyWzlx_cbb").combobox({
				valueField:"value",
				textField:"text",
				data:data,
				onLoadSuccess:function(){
					$(this).combobox("setValue",'${requestScope.dd.qyWzlxId }');
				},
				onSelect:function(){
					loadQyWZCBBData();
				}
			});
		}
	,"json");
}

function initQYWZCBB(){
	var data=[];
	data.push({"value":"","text":"请选择物资名称"});
	qyWzCBB=$("#edit_div #qyWz_cbb").combobox({
		valueField:"value",
		textField:"text",
		data:data,
		onLoadSuccess:function(){
			$(this).combobox("setValue",'${requestScope.dd.qyWzId }');
		}
	});
}

function loadQyWZCBBData(){
	var data=[];
	data.push({"value":"","text":"请选择物资名称"});
	var qyWzlxId=qyWzlxCBB.combobox("getValue");
	$.post(wzglPath+"queryWuZiCBBList",
		{wzlxId:qyWzlxId},
		function(result){
			var rows=result.rows;
			for(var i=0;i<rows.length;i++){
				data.push({"value":rows[i].id,"text":rows[i].mc});
			}
			qyWzCBB.combobox("loadData",data);
		}
	,"json");
}

function initQYYSSCBB(){
	var data=[];
	data.push({"value":"","text":"请选择运输商"});
	$.post(dwglPath+"queryYunShuShangCBBList",
		function(result){
			var rows=result.rows;
			for(var i=0;i<rows.length;i++){
				data.push({"value":rows[i].id,"text":rows[i].mc});
			}
			yssCBB=$("#edit_div #qyYss_cbb").combobox({
				valueField:"value",
				textField:"text",
				data:data,
				onLoadSuccess:function(){
					$(this).combobox("setValue",'${requestScope.dd.qyYssId }');
				}
			});
		}
	,"json");
}

function initQYFHDWCBB(){
	var data=[];
	data.push({"value":"","text":"请选择发货单位"});
	$.post(dwglPath+"queryFaHuoDanWeiCBBList",
		function(result){
			var rows=result.rows;
			for(var i=0;i<rows.length;i++){
				data.push({"value":rows[i].id,"text":rows[i].mc});
			}
			qyFhdwCBB=$("#edit_div #qyFhdw_cbb").combobox({
				valueField:"value",
				textField:"text",
				data:data,
				onLoadSuccess:function(){
					$(this).combobox("setValue",'${requestScope.dd.qyFhdwId }');
				}
			});
		}
	,"json");
}

function initQYSHBMCBB(){
	var data=[];
	data.push({"value":"","text":"请选择收货部门"});
	$.post(dwglPath+"queryShouHuoBuMenCBBList",
		function(result){
			var rows=result.rows;
			for(var i=0;i<rows.length;i++){
				data.push({"value":rows[i].id,"text":rows[i].mc});
			}
			qyShbmCBB=$("#edit_div #qyShbm_cbb").combobox({
				valueField:"value",
				textField:"text",
				data:data,
				onLoadSuccess:function(){
					$(this).combobox("setValue",'${requestScope.dd.qyShbmId }');
				}
			});
		}
	,"json");
}

function checkEdit(){
	if(checkQYWZLXId()){
		if(checkQYWZId()){
			editDingDanZongHeChaXun();
		}
	}
}

function editDingDanZongHeChaXun(){
	var sjc=lrSjcCBB.combobox("getValue");
	var wscph=lrWscphCBB.combobox("getValue");
	$("#edit_div #cph").val(sjc+wscph);
	var qyWzlxId=qyWzlxCBB.combobox("getValue");
	$("#edit_div #qyWzlxId").val(qyWzlxId);
	var qyWzId=qyWzCBB.combobox("getValue");
	$("#edit_div #qyWzId").val(qyWzId);
	var qyYssId=qyYssCBB.combobox("getValue");
	$("#edit_div #qyYssId").val(qyYssId);
	var qyFhdwId=qyFhdwCBB.combobox("getValue");
	$("#edit_div #qyFhdwId").val(qyFhdwId);
	var qyShbmId=qyShbmCBB.combobox("getValue");
	$("#edit_div #qyShbmId").val(qyShbmId);
	
	var formData = new FormData($("#form1")[0]);
	
	$.ajax({
		type:"post",
		url:ddglPath+"editDingDanZongHeChaXun",
		dataType: "json",
		data:formData,
		cache: false,
		processData: false,
		contentType: false,
		success: function (data){
			if(data.message=="ok"){
				alert(data.info);
				history.go(-1);
			}
			else{
				alert(data.info);
			}
		}
	});
}

//验证物资类型
function checkQYWZLXId(){
	var qyWzlxId=qyWzlxCBB.combobox("getValue");
	if(qyWzlxId==null||qyWzlxId==""){
	  	alert("请选择物资类型");
	  	return false;
	}
	else
		return true;
}

//验证物资
function checkQYWZId(){
	var qyWzId=qyWzCBB.combobox("getValue");
	if(qyWzId==null||qyWzId==""){
	  	alert("请选择物资");
	  	return false;
	}
	else
		return true;
}

//根据车牌号记录获取订单信息
function getDingDanByCphJL(cph){
	$.post(ddglPath+"getDingDanByCphJL",
		{cph:cph},
		function(result){
			if(result.status==1){
				var dd=result.data;
				lxlxCBB.combobox("setValue",dd.lxlx);
				qyYssCBB.combobox("setValue",dd.qyYssId);
				qyFhdwCBB.combobox("setValue",dd.qyFhdwId);
				qyShbmCBB.combobox("setValue",dd.qyShbmId);
			}
			else{
				lxlxCBB.combobox("setValue","");
				qyYssCBB.combobox("setValue","");
				qyFhdwCBB.combobox("setValue","");
				qyShbmCBB.combobox("setValue","");
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
	case "edit_div":
		space=340;
		break;
	case "edit_div_table":
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
		<div class="page_location_div">综合查询-编辑订单</div>
		
		<div id="edit_div">
			<form id="form1" name="form1" method="post" action="" enctype="multipart/form-data">
			<input type="hidden" id="id" name="id" value="${requestScope.dd.id }"/>
			<input type="hidden" id="ddztMc" name="ddztMc" value="${requestScope.ddztMc }"/>
			<table>
			  <tr>
				<td class="td1" align="right">
					订单号
				</td>
				<td class="td2">
					<input type="text" class="ddh_inp" id="ddh" name="ddh" value="${requestScope.dd.ddh }" placeholder="请输入订单号" disabled="disabled" onfocus="focusName()" onblur="checkName()"/>
				</td>
				<td class="td1" align="right">
					司机身份证号
				</td>
				<td class="td2">
					<input type="text" class="sjsfzh_inp" id="sjsfzh" name="sjsfzh" value="${requestScope.dd.sjsfzh }" placeholder="请输入司机身份证号" />
				</td>
			  </tr>
			  <tr>
				<td class="td1" align="right">
					司机姓名
				</td>
				<td class="td2">
					<input type="text" class="sjxm_inp" id="sjxm" name="sjxm" value="${requestScope.dd.sjxm }" placeholder="请输入司机姓名" />
				</td>
				<td class="td1" align="right">
					选择车牌号
				</td>
				<td class="td2">
					<input id="xzcph_cbb"/>
				</td>
			  </tr>
			  <tr>
				<td class="td1" align="right">
					录入车牌号
				</td>
				<td class="td2">
					<input id="lrSjc_cbb"/>
					<input id="lrWscph_cbb"/>
					<input type="hidden" id="cph" name="cph" value="${requestScope.dd.cph }"/>
				</td>
				<td class="td1" align="right">
					流向类型
				</td>
				<td class="td2">
					<input id="lxlx_cbb" disabled="disabled"/>
					<input type="hidden" id="lxlx" name="lxlx" value="${requestScope.dd.lxlx }"/>
				</td>
			  </tr>
			  <tr>
				<td class="td1" align="right">
					物资类型
				</td>
				<td class="td2">
					<input id="qyWzlx_cbb" disabled="disabled"/>
					<input type="hidden" id="qyWzlxId" name="qyWzlxId" value="${requestScope.dd.qyWzlxId }"/>
				</td>
				<td class="td1" align="right">
					物资名称
				</td>
				<td class="td2">
					<input id="qyWz_cbb" disabled="disabled"/>
					<input type="hidden" id="qyWzId" name="qyWzId" value="${requestScope.dd.qyWzId }"/>
				</td>
			  </tr>
			  <tr>
				<td class="td1" align="right">
					运输商
				</td>
				<td class="td2">
					<input id="qyYss_cbb" disabled="disabled"/>
					<input type="hidden" id="qyYssId" name="qyYssId"/>
				</td>
				<td class="td1" align="right">
					发货单位
				</td>
				<td class="td2">
					<input id="qyFhdw_cbb" disabled="disabled"/>
					<input type="hidden" id="qyFhdwId" name="qyFhdwId"/>
				</td>
			  </tr>
			  <tr>
				<td class="td1" align="right">
					收货部门
				</td>
				<td class="td2">
					<input id="qyShbm_cbb" disabled="disabled"/>
					<input type="hidden" id="qyShbmId" name="qyShbmId"/>
				</td>
				<td class="td1" align="right">
					二维码
				</td>
				<td class="td2">
					<img class="ewm_img" alt="" src="${requestScope.dd.ewmlj }">
				</td>
			  </tr>
			</table>
			</form>
		</div>

		<%@include file="../../inc/foot.jsp"%>
	</div>
</div>
</body>
</html>