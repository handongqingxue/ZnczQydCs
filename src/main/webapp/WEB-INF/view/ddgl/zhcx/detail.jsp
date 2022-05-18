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
</style>
<script type="text/javascript">
var path='<%=basePath %>';
var ddglPath=path+'ddgl/';
var dialogTop=70;
var dialogLeft=20;
var ddNum=0;
$(function(){
	initDetailDialog();//0

	initDialogPosition();//将不同窗体移动到主要内容区域
});

function initDialogPosition(){
	//基本属性组
	var ddpw=$("body").find(".panel.window").eq(ddNum);
	var ddws=$("body").find(".window-shadow").eq(ddNum);

	var ccDiv=$("#center_con_div");
	ccDiv.append(ddpw);
	ccDiv.append(ddws);
	ccDiv.css("width",setFitWidthInParent("body","center_con_div")+"px");
}

function initDetailDialog(){
	dialogTop+=20;
	$("#detail_div").dialog({
		title:"订单信息",
		width:setFitWidthInParent("body","detail_div"),
		height:325,
		top:dialogTop,
		left:dialogLeft,
		buttons:[
           {text:"审核通过",id:"shtg_but",iconCls:"icon-ok",handler:function(){
        	   checkById();
           }}
        ]
	});

	$("#detail_div table").css("width",(setFitWidthInParent("body","detail_div_table"))+"px");
	$("#detail_div table").css("magin","-100px");
	$("#detail_div table td").css("padding-left","50px");
	$("#detail_div table td").css("padding-right","20px");
	$("#detail_div table td").css("font-size","15px");
	$("#detail_div table .td1").css("width","15%");
	$("#detail_div table .td2").css("width","30%");
	$("#detail_div table tr").css("border-bottom","#CAD9EA solid 1px");
	$("#detail_div table tr").each(function(i){
		$(this).css("height","45px");
	});

	$(".panel.window").eq(ddNum).css("margin-top","20px");
	$(".panel.window .panel-title").eq(ddNum).css("color","#000");
	$(".panel.window .panel-title").eq(ddNum).css("font-size","15px");
	$(".panel.window .panel-title").eq(ddNum).css("padding-left","10px");
	
	$(".panel-header, .panel-body").css("border-color","#ddd");
	
	//以下的是表格下面的面板
	$(".window-shadow").eq(ddNum).css("margin-top","20px");
	$(".window,.window .window-body").eq(ddNum).css("border-color","#ddd");

	var shtgBut=$("#detail_div #shtg_but");
	shtgBut.css("left","45%");
	shtgBut.css("position","absolute");
	
	if('${requestScope.dd.ddztId }'==1)
		shtgBut.linkbutton('enable');
	else
		shtgBut.linkbutton('disable');
	
	$(".dialog-button").css("background-color","#fff");
	$(".dialog-button .l-btn-text").css("font-size","20px");
}

function checkById(){
	var id=$("#detail_div #id").val();
	var ddztMc;
	var sjsfzh='${requestScope.dd.sjsfzh }';
	var sjxm='${requestScope.dd.sjxm }';
	var cph='${requestScope.dd.cph }';
	if(sjsfzh==""||sjxm==""||cph=="")//在司机身份证号、司机姓名、车牌号的一者为空情况下，就算审核通过，订单状态也是已审核
		ddztMc=$("#detail_div #yshDdztMc").val();
	else//在司机身份证号、司机姓名、车牌号三者都不为空情况下，审核通过，订单状态直接跳到一检排队中
		ddztMc=$("#detail_div #yjpdzDdztMc").val();
	var shlx='${requestScope.shlx}';
	var shrId='${sessionScope.yongHu.id}';
	$.post(ddglPath + "checkDingDanByIds",
		{ids:id,ddztMc:ddztMc,shlx:shlx,shjg:true,shrId:shrId},
		function(result){
			if(result.status==1){
				alert(result.msg);
				history.go(-1);
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
	case "detail_div":
		space=340;
		break;
	case "detail_div_table":
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
		<div class="page_location_div">综合查询-订单详情</div>
		
		<div id="detail_div">
			<form id="form1" name="form1" method="post" action="" enctype="multipart/form-data">
			<input type="hidden" id="id" value="${requestScope.dd.id }"/>
			<input type="hidden" id="yshDdztMc" value="${requestScope.yshDdztMc}"/>
			<input type="hidden" id="yjpdzDdztMc" value="${requestScope.yjpdzDdztMc}"/>
			<table>
			  <tr>
				<td class="td1" align="right">
					订单号
				</td>
				<td class="td2">
					${requestScope.dd.ddh }
				</td>
				<td class="td1" align="right">
					司机身份证号
				</td>
				<td class="td2">
					${requestScope.dd.sjsfzh }
				</td>
			  </tr>
			  <tr>
				<td class="td1" align="right">
					司机姓名
				</td>
				<td class="td2">
					${requestScope.dd.sjxm }
				</td>
				<td class="td1" align="right">
					车牌号
				</td>
				<td class="td2">
					${requestScope.dd.cph }
				</td>
			  </tr>
			  <tr>
				<td class="td1" align="right">
					流向类型
				</td>
				<td class="td2">
					<c:choose>
						<c:when test="${requestScope.dd.lxlx eq 1 }">送运</c:when>
						<c:otherwise>取运</c:otherwise>
					</c:choose>
				</td>
				<td class="td1" align="right">
					物资类型
				</td>
				<td class="td2">
					${requestScope.dd.wzlxMc }
				</td>
			  </tr>
			  <tr>
				<td class="td1" align="right">
					物资名称
				</td>
				<td class="td2">
					${requestScope.dd.wzMc }
				</td>
				<td class="td1" align="right">
					运输商
				</td>
				<td class="td2">
					${requestScope.dd.yssMc }
				</td>
			  </tr>
			  <tr>
				<td class="td1" align="right">
					发货单位
				</td>
				<td class="td2">
					${requestScope.dd.fhdwMc }
				</td>
				<td class="td1" align="right">
					收货部门
				</td>
				<td class="td2">
					${requestScope.dd.shbmMc }
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