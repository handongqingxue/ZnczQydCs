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
	height:32px;
}
.tab1_div .toolbar .mc_span{
	margin-left: 13px;
}
.tab1_div .toolbar .mc_inp{
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
var wzglPath=path+'wzgl/';
$(function(){
	initSearchLB();
	initAddLB();
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

function initSearchLB(){
	$("#search_but").linkbutton({
		iconCls:"icon-search",
		onClick:function(){
			var mc=$("#toolbar #mc").val();
			tab1.datagrid("load",{mc:mc});
		}
	});
}

function initAddLB(){
	$("#add_but").linkbutton({
		iconCls:"icon-add",
		onClick:function(){
			location.href=wzglPath+"wzlx/new";
		}
	});
}

function initRemoveLB(){
	removeLB=$("#remove_but").linkbutton({
		iconCls:"icon-remove",
		onClick:function(){
			checkIfExistWuZiByLxIds();
		}
	});
}

function initTab1(){
	tab1=$("#tab1").datagrid({
		title:"物资类型查询",
		url:wzglPath+"queryWZLXList",
		toolbar:"#toolbar",
		width:setFitWidthInParent("body"),
		pagination:true,
		pageSize:10,
		columns:[[
			{field:"mc",title:"类名",width:150},
			{field:"cjsj",title:"创建时间",width:150},
            {field:"bjsj",title:"编辑时间",width:150},
            {field:"px",title:"排序",width:100},
			{field:"bz",title:"备注",width:300},
            {field:"id",title:"操作",width:110,formatter:function(value,row){
            	var str="<a href=\"edit?id="+value+"\">编辑</a>&nbsp;&nbsp;"
            		+"<a href=\"detail?id="+value+"\">详情</a>";
            	return str;
            }}
	    ]],
        onLoadSuccess:function(data){
			if(data.total==0){
				$(this).datagrid("appendRow",{mc:"<div style=\"text-align:center;\">暂无信息<div>"});
				$(this).datagrid("mergeCells",{index:0,field:"mc",colspan:6});
				data.total=0;
			}
			
			$(".panel-header .panel-title").css("color","#000");
			$(".panel-header .panel-title").css("font-size","15px");
			$(".panel-header .panel-title").css("padding-left","10px");
			$(".panel-header, .panel-body").css("border-color","#ddd");
		}
	});
}

//验证物资类型id下是否存在物资
function checkIfExistWuZiByLxIds() {
	var rows=tab1.datagrid("getSelections");
	if (rows.length == 0) {
		$.messager.alert("提示","请选择要删除的信息！","warning");
		return false;
	}
	
	$.messager.confirm("提示","确定要删除吗？",function(r){
		if(r){
			var ids = "";
			var mcs = "";
			for (var i = 0; i < rows.length; i++) {
				ids += "," + rows[i].id;
				mcs += "," + rows[i].mc;
			}
			ids=ids.substring(1);
			mcs=mcs.substring(1);

			$.post(wzglPath + "checkIfExistWuZiByLxIds",
				{lxIds:ids,lxMcs:mcs},
				function(result){
					if(result.status==1){
						alert(result.msg);
						var delIds="";
						var idArr=ids.split(",");
						var wzlxList=result.data;
						for (var i = 0; i < idArr.length; i++){
							var id=idArr[i];
							if(!checkWzlxIdInList(id,wzlxList)){//若不存在，则说明该类型下没有物资，就得删除掉
								delIds+=","+id;
							}
						}
						delIds=delIds.substring(1);
						if(delIds!="")//若有没有物资的物资类型id，则删除
							deleteByIds(delIds);
					}
					else{
						deleteByIds(ids);
					}
				}
			,"json");
			
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

//验证物资类型id是否存在于集合里
function checkWzlxIdInList(wzlxId,wzlxList){
	var flag=false;
	for (var i = 0; i < wzlxList.length; i++){
		if(wzlxId==wzlxList[i].id){
			flag=true;
			break;
		}
	}
	return flag;
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
			<span class="mc_span">类名：</span>
			<input type="text" class="mc_inp" id="mc" placeholder="请输入类名"/>
			<a class="search_but" id="search_but">查询</a>
			<a id="add_but">添加</a>
			<a id="remove_but">删除</a>
		</div>
		<table id="tab1">
		</table>
	</div>
	<%@include file="../../inc/foot.jsp"%>
</div>
</body>
</html>