<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<jsp:include page="../../../common/common.jsp"/>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>用户信息管理</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
 <script type="text/javascript" src="js/util.js"></script>
	 <script>
	 
		$(function(){
		
			$('#configuer').datagrid({
				title:'',
				width:'98%',
				height:hightfit(0.88),
				nowrap: false,
				striped: true,
				fitColumns:true,
				collapsible:false,
				singleSelect:true,
			    url:'user!userList.action',
				sortOrder: 'desc',
				remoteSort: false,
				columns:[
				[
				    {field:'id',hidden:true},	
				    {field:'userId',title:'账号',width:150,rowspan:2},
				    {field:'name',title:'姓名',width:150,rowspan:2},
					{field:'company',title:'单位',width:150,rowspan:2},
					{field:'email',title:'邮箱',width:150,rowspan:2},
					{field:'mobile',title:'手机',width:150,rowspan:2},
					{field:'phone',title:'电话',width:150,rowspan:2},
// 					{field:'state',title:'状态',width:150,rowspan:2,
// 						formatter:function(value,rowdata,rowindex){
// 							  var str="";
// 						     if(value=="0")
// 						    	 {
// 						    	   str="未审核";
// 						    	 }else if(value=="1")
// 					    		 {
// 					    		 	str="已通过";
// 					    		 }else{
// 					    			str="未通过";
// 					    		 }
// 							  return str;
// 						}
// 						},
					{field:'remark',title:'备注',width:150,rowspan:2}
				]],
				pagination:true,
				rownumbers:true
			});
			var p = $('#configuer').datagrid('getPager');
			if (p){
				$(p).pagination({
				    onBeforeRefresh:function(){
						$('#configuer').datagrid('reload');
					},
					onSelectPage:function(pageNumber, pageSize){
					tosearch(pageNumber,pageSize);
				}
				});
			}
		});
		  function tosearch(pageNumber, pageSize){
			  var userid=$.trim($("#userid").val());
			  var name=$.trim($("#name").val());
			  var email=$.trim($("#email").val());
			  var state=$.trim($("#state").val());
		    $.ajax({ 
		       type: "post",
		       dataType:"json", 
		       url:"user!userList.action?userid="+encodeURI(userid)+"&name="+encodeURI(name)+"&email="+encodeURI(email)+"&state="+state+"&page="+pageNumber+"&rows="+pageSize, 
		       success: function(msg){
				   if(msg.rows!=null){ 
				    $('#configuer').datagrid("loadData",msg); 
				    } 
		       } 
		       
		    }); 
   		 } 

//添加
    function confadd()
     {
 		window.location.href="<%=basePath%>system/user/userAdd.jsp";
   } 
   
   //修改
   	 function confedit()
   {
	   
	    var ids = [];
		var rows = $('#configuer').datagrid('getSelections');
		for(var i=0;i<rows.length;i++){
			ids.push(rows[i].id);
		}
		var getids=ids.join(',');
		
		if(ids.length<1)
		{
		    $.messager.alert('提示','<br>请选择要修改的用户信息','warning');
		    
		}else if(ids.length>1){
		
			 $.messager.alert('提示','<br>不能同时修改多个用户','warning');
		}else{
		     window.location.href="user!perEdit.action?id="+ids;
	   }
    }
   
   //密码重置
   function setpwd()
   {
		var ids = [];
		var rows = $('#configuer').datagrid('getSelections');
		for(var i=0;i<rows.length;i++){
			ids.push(rows[i].id);
		}
		var getids=ids.join(',');
		
		if(ids.length<1)
		{
		    $.messager.alert('提示','<br>请选择用户','warning');
		    
		}else{
		
			$.messager.confirm('提示', '<br>确定重置此用户密码?', function(r){
			if (r){
					$.ajax({
					   type:"post",
					   dataType:"html",
					   url:"user!setPwd?id="+getids,
					   success:function(data){
					      if(data=="Y")
					      {
					         $.messager.alert('提示','<br>重置成功','info');
					         $('#configuer').datagrid('reload');
					      }else{
					         $.messager.alert('提示','<br>重置失败','warning');
					      }
					   }
					});	
			}
	        });
		}
    }
     //删除
   function confdel()
   {
	    var ids = [];
		var rows = $('#configuer').datagrid('getSelections');
		for(var i=0;i<rows.length;i++){
			ids.push(rows[i].id);
		}
		var getids=ids.join(',');
		
		if(ids.length<1)
		{
		    $.messager.alert('提示','<br>请选择要删除的用户','warning');
		    
		}else{
		
			$.messager.confirm('提示', '<br>确定删除此用户?', function(r){
			if (r){
					$.ajax({
					   type:"post",
					   dataType:"html",
					   url:"user!delUser?id="+getids,
					   success:function(data){
					      if(data=="Y")
					      {
					         $.messager.alert('提示','<br>删除成功','info');
					         $('#configuer').datagrid('reload');
					      }else{
					         $.messager.alert('提示','<br>删除失败','warning');
					      }
					   }
					});	
			}
	        });
		}
   }
     
  function reload()
   {
     $('#configuer').datagrid('reload');
   }
 function search(){
	  var userid=$.trim($("#userid").val());
	  var name=$.trim($("#name").val());
	  var email=$.trim($("#email").val());
	  var state=$.trim($("#state").val());
	  $('#configuer').datagrid({
		url:"user!userList.action?userid="+encodeURI(userid)+"&name="+encodeURI(name)+"&email="+encodeURI(email)+"&state="+state,
		onLoadSuccess:function(data){
		   if(data.total==0)
		   {
		      $.messager.alert('提示','<br>暂无此查询记录','info');
		   }
		}
	}); 
   } 
    //用户审核
   function priview()
   {
    	var ids = [];
		var rows = $('#configuer').datagrid('getSelections');
		for(var i=0;i<rows.length;i++){
			ids.push(rows[i].id);
		}
		var getids=ids.join(',');
		
		if(ids.length<1)
		{
		    $.messager.alert('提示','<br>请选择要审核的用户','warning');
		    
		}else{
			
			var row = $('#configuer').datagrid('getSelected');
			if(row.state=="0")
				{
			       document.getElementById("roleadd").style.display='block';
			       $('#roleadd').dialog({
							title:'用户审核',
							closable:false,
							modal: true,
							onMove:function(left,top){  //鼠标拖动时事件
			   			 	   var dom_width=document.body.offsetWidth;//body的宽度
			   			 	   var dom_height=document.body.offsetHeight;//body的高度
			   			 	   var dd_width= $('#roleadd').panel('options').width;//dialog的宽度
			   			 	   var dd_height= $('#roleadd').panel('options').height;//dialog的高度
			                   var myleft=(dom_width-dd_width)/2;
			                   var mytop=(dom_height-dd_height)/2;
			   			 	   if(left<1||left>dom_width-dd_width||top<1||top>dom_height-dd_height){	
								 $('#roleadd').dialog('move',{    
									left:myleft,    
									top:mytop    
							  	});  
			   			 	   }
			   			 	},
							buttons:[{
							    id:'btnsave',
								text:'确定',
								handler:function(){
								$('#btnsave').linkbutton('disable'); 
								var item = $("input[name='ck']:checked").val();
									$.ajax({
									   type:"post",
									   dataType:"html",
									   url:"user!setState?id="+getids+"&state="+item,
									   success:function(data){
										  $('#btnsave').linkbutton('enable'); 
									      if(data=="Y")
									      {
									         $.messager.alert('提示','<br>审核成功','info');
									         $('#roleadd').dialog('close');
									         $('#configuer').datagrid('reload');
									      }else{
									         $.messager.alert('提示','<br>审核失败','warning');
									      }
									   }
									});
								}
							},{
								text:'取消',
								handler:function(){
									$('#roleadd').dialog('close');
								}
							}]
						});
				}else{
					   $.messager.alert('提示','<br>此用户已审核!','warning');
				}
			
		}
   }
   function setrole()
   {
	 	var ids = [];
		var rows = $('#configuer').datagrid('getSelections');
		for(var i=0;i<rows.length;i++){
			ids.push(rows[i].id);
		}
		var getids=ids.join(',');
		
		if(ids.length<1)
		{
		    $.messager.alert('提示','<br>请先选择用户','warning');
		    
		}else{
			$("#username").html(rows[0].name);
			 $.ajax({
					   type:"post",
					   dataType:"html",
					   async:false,
					   url:"role!getRoleId?id="+rows[0].userId,
					   success:function(data){
					      if(data!="N")
					      {
					    	  $('#rolename').combotree('setValue',data);
					      }else{
					    	  $('#rolename').combotree('setValue','');
					      }
					   }
				});
			     document.getElementById("operpurview").style.display='block';
							$('#operpurview').dialog({
								title:'用户授权',
								closable:false,
								modal: true,
								iconCls:'icon-add',
								onMove:function(left,top){  //鼠标拖动时事件
				   			 	   var dom_width=document.body.offsetWidth;//body的宽度
				   			 	   var dom_height=document.body.offsetHeight;//body的高度
				   			 	   var dd_width= $('#operpurview').panel('options').width;//dialog的宽度
				   			 	   var dd_height= $('#operpurview').panel('options').height;//dialog的高度
				                   var myleft=(dom_width-dd_width)/2;
				                   var mytop=(dom_height-dd_height)/2;
				   			 	   if(left<1||left>dom_width-dd_width||top<1||top>dom_height-dd_height){	
									 $('#operpurview').dialog('move',{    
										left:myleft,    
										top:mytop    
								  	});  
				   			 	   }
				   			 	},
								buttons:[{
									text:'确定',
									handler:function(){
									  var operid=$('#rolename').combotree('getValue');
									  if(operid=="")
									  {
									     $.messager.alert('提示','<br>请选择角色!','info');
									  }else
									  {
										   $.ajax({
										      type:"post",
										      dataType:"html",
										      url:"role!saveUserRole.action?empid="+rows[0].userId+"&roleid="+operid,
										      success:function(data){
										         
										         if(data=="Y")
										         {
										            $.messager.alert('提示','<br>授权成功!','info');
										            $('#operpurview').dialog('close');
										         }else{
										            $.messager.alert('提示',data,'info');
										         }
										      }   
										   });
									  	}}
									},{
										text:'取消',
										handler:function(){
											$('#operpurview').dialog('close');
										}
									}]
								});  
		}
   }
    
    function setarea()
    {
    	var ids = [];
		var rows = $('#configuer').datagrid('getSelections');
		for(var i=0;i<rows.length;i++){
			ids.push(rows[i].id);
		}
		var getids=ids.join(',');
		
		if(ids.length<1)
		{
		    $.messager.alert('提示','<br>请先选择用户','warning');
		    
		}else{
			$("#username").html(rows[0].name);
			 $.ajax({
				   type:"post",
				   dataType:"html",
				   async:false,
				   url:"user!getJobTitle?id="+rows[0].id,
				   success:function(data){
					   if(data!="" && data!='null')
					   {
					      //$('#purtree').tree('collapseAll');
						 arr = data.split(",");
					     var valueArr = new Array();
					     for (var i = 0; i < arr.length; i++) {
					       valueArr.push(arr[i]);
					     }
					     $("#area").combotree("setValues", valueArr);
					   }
				   }
			});
			  document.getElementById("operarea").style.display='block';
							$('#operarea').dialog({
								title:'分配片区',
								closable:false,
								modal: true,
								iconCls:'icon-add',
								onMove:function(left,top){  //鼠标拖动时事件
				   			 	   var dom_width=document.body.offsetWidth;//body的宽度
				   			 	   var dom_height=document.body.offsetHeight;//body的高度
				   			 	   var dd_width= $('#operarea').panel('options').width;//dialog的宽度
				   			 	   var dd_height= $('#operarea').panel('options').height;//dialog的高度
				                   var myleft=(dom_width-dd_width)/2;
				                   var mytop=(dom_height-dd_height)/2;
				   			 	   if(left<1||left>dom_width-dd_width||top<1||top>dom_height-dd_height){	
									 $('#operarea').dialog('move',{    
										left:myleft,    
										top:mytop    
								  	});  
				   			 	   }
				   			 	},
								buttons:[{
									text:'确定',
									handler:function(){
									  var operid=$('#area').combotree('getValues');
									  if(operid=="")
									  {
									     $.messager.alert('提示','<br>请选择区域!','info');
									  }else
									  {
										   $.ajax({
										      type:"post",
										      dataType:"html",
										      url:"user!setArea.action?id="+rows[0].id+"&areas="+operid,
										      success:function(data){
										         
										         if(data=="Y")
										         {
										            $.messager.alert('提示','<br>分配成功!','info');
										            $('#operarea').dialog('close');
										         }else{
										            $.messager.alert('提示','分配失败!','info');
										         }
										      }   
										   });
									  	}}
									},{
										text:'取消',
										handler:function(){
											$('#operarea').dialog('close');
										}
									}]
								});  
		}
    }
	</script>
  </head>
  
  <body style="position: relative;overflow: hidden;padding: 1px; margin: 0px;">
     	<div id="panel" class="easyui-panel" title="系统管理>>用户管理" iconCls="zixun" collapsible="true" style="padding:17px;" border=false; >
   	账号：<input type="text" id="userid" name="userid" title="请输入账号查询!" style="width: 105px;"/>&nbsp;&nbsp;&nbsp;&nbsp;
   	姓名： <input type="text" id="name" name="name" title="请输入姓名查询!" style="width: 105px;"/>&nbsp;&nbsp;&nbsp;&nbsp;
   	邮箱： <input type="text" id="email" name="email" title="请输入邮箱查询!" style="width: 105px;"/>&nbsp;&nbsp;&nbsp;&nbsp;
<!--            状态：	<select name="state" id="state" > -->
<!-- 				<option value="">--请选择--</option> -->
<!-- 				<option value="0">未审核</option> -->
<!-- 	  			<option value="1">已通过</option> -->
<!-- 	  			<option value="2">未通过</option> -->
<!-- 			</select> -->
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<input type="hidden" id="userid" name="userid">		
   	<input type="button" value="查 询" onclick="search()"/>
   	</div>
  <table id="configuer" toolbar="#tb"></table>
   <div id="tb" style="height:auto">  
	    <table cellpadding="0" cellspacing="0" style="width:100%">
			<tr>
				<!-- 工具 条 -->
				<td>
					<a href="javascript:confadd()"  class="easyui-linkbutton"  plain="true" iconCls="icon-add" plain="true" title="添加">添加</a>
	       	 		<a href="javascript:confedit()"  class="easyui-linkbutton"  plain="true" iconCls="icon-edit" plain="true" title="修改">修改</a> 
	        		<a href="javascript:confdel()" class="easyui-linkbutton" plain="true" iconCls="icon-remove" plain="true" title="删除">删除</a>
<!-- 	        		<a href="javascript:priview()"  class="easyui-linkbutton"  plain="true" iconCls="icon-edit" plain="true" title="审核">审核</a> -->
	        		<a href="javascript:setpwd()" class="easyui-linkbutton" plain="true" iconCls="password" plain="true" title="密码重置">密码重置</a>
	        		<a href="javascript:setrole()" class="easyui-linkbutton" plain="true" iconCls="user-man1" plain="true" title="分配角色">分配角色</a>
	        		<a href="javascript:setarea()" class="easyui-linkbutton" plain="true" iconCls="role-man" plain="true" title="分配片区">分配片区</a>
                    <a href="javascript:reload()" class="easyui-linkbutton" iconCls="icon-reload" plain="true" title="刷新">刷新</a>
				</td>
			</tr>
		</table>
	</div>
    <div id="roleadd" icon="icon-edit" style="padding:20px;width:250px;height:150px;display:none;">
		<form  method="post" name="addroleform" id="addroleform" >
			<input type="radio" name="ck" checked="checked" value="1">通过<input type="radio" name="ck" value="2">不通过
		</form>
    </div>
    
        <div id="operpurview" style="padding:10px;width:420px;height:300px;display:none;">
		<span>
			  <img alt="" src="images/users_edit.png"> <span id="username"></span>
	    </span>
		<br/><br/>
		<span style="margin-left: 50px;">
		选择角色:<select id="rolename" class="easyui-combotree" url="role!selRoles.action" style="width:180px;" class="easyui-validatebox" required="true"></select>
  		</span>
	    </div>
	    
	    <div id="operarea" style="padding:10px;width:420px;height:300px;display:none;">
		<span>
			  <img alt="" src="images/users_edit.png"> <span id="username"></span>
	    </span>
		<br/><br/>
		<span style="margin-left: 50px;">
		选择片区:<select id="area" class="easyui-combotree" url="report!selSaleLines.action" multiple="true" cascadeCheck="true" onlyLeafCheck="false" style="width:180px;" class="easyui-validatebox" required="true"></select>
  		</span>
	    </div>
  </body>
</html>
