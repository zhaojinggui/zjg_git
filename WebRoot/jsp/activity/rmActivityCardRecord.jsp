<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<%@ include file="../../common/common.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>发卡记录管理</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
 <script type="text/javascript" src="js/util.js"></script>
 
 <script type="text/javascript">
	
	$(function(){loaddata();});
	
	//查询
	function query(){loaddata();}
	
	//加载数据
	function loaddata(){
		var name = $("input[name='name']").val();
		var mobile = $("input[name='mobile']").val();
		var cardNo = $("input[name='cardNo']").val();
		$('#configuer').datagrid({
				title:'&nbsp;',
				iconCls:'',
				width:'98%',
				height:hightfit(0.85),
				nowrap: false,
				striped: true,
				collapsible:false,
				url:'activityCardRecord!find.action',
				/* pageSize:10, */
				/* pageList:[10,20,30,40,50], */
				queryParams:{name:name,mobile:mobile,cardNo:cardNo},
				sortOrder: 'desc',
				remoteSort: false,
				fitColumns:true,
				columns:[[
				    /* {field:'id',checkbox:true}, */
				    {field:'name',title:'姓名',width:50,align:'center',rowspan:2},
				    {field:'mobile',title:'手机号码',width:50,align:'center',rowspan:2},
				    {field:'cardNo',title:'卡号',width:50,align:'center',rowspan:2},
				    {field:'addDate',title:'发卡日期',width:50,align:'center',rowspan:2,
				    	formatter:function (value,rows,index){
							return new Date(value.time).format('yyyy-MM-dd');
						}
					},
				    {field:'state',title:'状态',width:50,align:'center',rowspan:2,
				    	formatter:function(value,rows,index){
				    		if(value=='Y'){
				    			return '发送成功';
				    		}else {
				    			return '发送失败';
				    		}
				    	}
				    }
				]],
				pagination:true,
				rownumbers:true,
				onLoadSuccess:function (data){
				$(".datagrid-header .datagrid-cell").css("text-align","center");
					if(data.total==0){
						$.messager.alert('提示', '<br>暂无此查询记录','info');
					}
				}
			});
	}
	
	
 </script>

  </head>
  <body style="position: relative;overflow: hidden;padding: 1px; margin: 0px;">
     	<div id="panel" class="easyui-panel" title="发卡记录管理" icon="user-fun" collapsible="true" style="padding:17px;" border=false; >
	    	<s:textfield name="name" label="姓名"></s:textfield>
	    	<s:textfield name="mobile" label="手机号码"></s:textfield>
	    	<s:textfield name="cardNo" label="卡号"></s:textfield>
	    	<input type="button" value="查询" onclick="query();"/>
   		</div>
  	<table id="configuer" toolbar="#tb"></table>
  </body>
</html>
