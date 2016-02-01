<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<jsp:include page="../../../common/common.jsp"/>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>角色管理</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript" src="js/util.js"></script>
	 <script>
	   
		$(function(){
		
			$('#role').datagrid({
				title:'系统管理>>角色管理',
				iconCls:'user-fun',
				width:'99%',
				height:hightfit(1.0),
				nowrap: false,
				striped: true,
				url:'role!showroletab.action',
				sortOrder: 'desc',
				remoteSort: false,
				fitColumns:true,
				singleSelect:true,
				columns:[
				[
					{field:'id',hidden:true},
					{field:'name',title:'角色名称',width:132,rowspan:2,sortable:true,
						sorter:function(a,b){
							return (a>b?1:-1);
						}
					},
					{field:'remark',title:'角色描述',width:182,rowspan:2},
					{field:'pur',title:'角色权限',width:90,align:'center', rowspan:2,
						formatter:function(value,rowdata,rowindex){	
							  var str= "<a href='javascript:selpurview(\""+rowdata.id+"\")'><img src='easyui/themes/icons/search.png' border=0></img></a>";
							  return str;
						}
					}
				]],
				pagination:true,
				rownumbers:true,
				onLoadSuccess:function(data){
					$(this).datagrid('clearSelections');
					$("input[type='checkbox']").each(function(index,el){
					if(el.value == 'on'){
						el.checked = false;
					}
				});
		}
			 });
		});
	
		
   //角色添加
   function roleadd()
   {
       document.getElementById("roleadd").style.display='block';
       $('#roleadd').dialog({
				title:'角色添加',
				iconCls:'icon-add',
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
				    id:'rolesave',
					text:'保存',
					handler:function(){
					     var flag=$("#addroleform").form('validate');
					     if(flag)
					     {
					         $('#rolesave').linkbutton('disable');    
						      $('#addroleform').form('submit', {  
							       url:'role!saverole.action',
							       success:function(data){
							       if(data=="Y")
							       {
							          $('#rolesave').linkbutton('enable'); 
							          $('#roleadd').dialog('close');
							          $.messager.alert('提示','<br>保存成功!','info',function(){
							            reload();
							          });
							       }else{
							          $.messager.alert('提示','<br>保存失败!','info');
							        }
								}
							});
					     }
					}
				},{
					text:'取消',
					handler:function(){
						document.addroleform.reset();
						$('#roleadd').dialog('close');
					}
				}]
			});
					  
   }
   
   //角色修改
   function roleupdate()
   {
        var ids = [];
		var rows = $('#role').datagrid('getSelections');
		if(rows.length<1)
		{
		    $.messager.alert('提示','<br>请选择要修改的记录!','info');
		    
		}else if(rows.length>1){
		
			 $.messager.alert('提示','<br>不能同时修改多条记录!','info');
		}else{
			document.getElementById("roleadd").style.display='block';
  			var selected = $('#role').datagrid('getSelected');
            $("#name").val(selected.name);
            $("#remark").val(selected.remark);
            $("#id").val(selected.id);
			 // 修改信息
			$('#roleadd').dialog({
					title:'角色修改',
					closable:false,
				    iconCls:'icon-edit',
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
					    id:'roleup',
						text:'确定',
						handler:function(){
							   var flag=$("#addroleform").form('validate');
							   if(flag)
							   {
							      $('#addroleform').form('submit', {  
								       url:'role!updaterole.action',
								       success:function(data){
								       if(data=="Y")
								       {
								          $('#roleadd').dialog('close');
								          $.messager.alert('提示','<br>修改成功!','info',function(){
										      $('#role').datagrid("reload");								            
								          });
								          
								       }else{
								          $.messager.alert('提示','<br>修改失败!','info');
								        }
									}
								});
							   }
						}
					},{
						text:'取消',
						handler:function(){
							$('#roleadd').dialog('close');
						}
					}]
			});
		}
   }	
   //角色删除
   function roledel()
   {
   		var ids = [];
		var rows = $('#role').datagrid('getSelections');
		for(var i=0;i<rows.length;i++){
			ids.push(rows[i].id);
		}
		var getids=ids.join(',');
		
		if(ids.length<1)
		{
		   $.messager.alert('提示','<br>请选择要删除的记录!','info');
		}else
		{
			$.messager.confirm('提示', '<br>确认删除这'+ids.length+'条记录?', function(r){
			if (r){
					$.ajax({
					   type:"post",
					   dataType:"html",
					   url:"role!delrole?roleid="+getids,
					   success:function(data){
					      if(data=="Y")
					      {
					         $.messager.alert('提示','<br>删除成功!','info');
					         $('#role').datagrid('reload');
					      }else{
					         $.messager.alert('提示','<br>删除失败!','info');
					      }
					      
					   }
					   
					});	
			}
	        });
		}
      
   }
   
    //角色授权用户
    function emppurview()
    {
        var rows = $('#role').datagrid('getSelections');
		
		if(rows.length<1)
		{
		    $.messager.alert('提示','<br>请选择要授权用户的角色!','info');
		    
		}else if(rows.length>1){
		
			 $.messager.alert('提示','<br>不能同时选择多个角色!','info');
			 
		}else{
		     var selected=$('#role').datagrid('getSelected');
			 var rolename=selected.name;
		     var roleid=selected.id;
		     //将数据放在对话框中
		     $("#rolename").html(rolename);    
		     //判断该角色是否已授权
		      $.ajax({
			           type:"post",
			           dataType:"html",
			           url:"role!selempids.action?roleid="+roleid,
			           success:function(data){
			             //还没有授权
			             if(data=="Y")
			             {
							 //显示授权对话框      
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
									  var operid=$('#username').combotree('getValue');
									  if(operid=="")
									  {
									     $.messager.alert('提示','<br>请选择员工!','info');
									  }else
									  {
										   $.ajax({
										      type:"post",
										      dataType:"html",
										      url:"role!saveemp_role.action?empid="+operid+"&roleid="+roleid,
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
							       
			             }else{
			                
			                //已经授权
			                 $.messager.confirm('提示', '<br>该角色已授权用户,是否要修改?', function(r){
							 if (r){
						       var empids=[];
			                   var ids=data.split(',');
			                   for(var i=0;i<ids.length;i++)
			                   {
			                      empids.push(ids[i]);
			                   }
					           $("#username").combotree('setValues',empids);
							   document.getElementById("operpurview").style.display='block';
						              $('#operpurview').dialog({
											title:'用户授权修改',
											closable:false,
											modal: true,
											iconCls:'icon-edit',
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
												text:'保存',
												handler:function(){
												  var operid=$('#username').combotree('getValues');
												   if(operid=="")
												   {
												        $.messager.alert('提示','<br>请选择员工!','info');
												      
												   }else{
												   
													    $.ajax({
													      type:"post",
													      dataType:"html",
													      url:"role!saveorupdateeemprole.action?empid="+operid+"&roleid="+roleid,
													      success:function(data){
													         
													         if(data=="Y")
													         {
													            $.messager.alert('提示','<br>修改成功!','info');
													            $('#operpurview').dialog('close');
													            
													         }
													         else{
													            $.messager.alert('提示',data,'info');
													         }
													      }   
													   });
												      
												   }
											
												}
											},{
												text:'取消',
												handler:function(){
													$('#operpurview').dialog('close');
												}
											}]
										});
								  
							   }
						    });
			                
			              }
			             
			           }
			       });
		    
		}
    }
    
    //角色授权
    function rolepurview()
    {
    
        var rows = $('#role').datagrid('getSelections');
		if(rows.length<1)
		{
		    $.messager.alert('提示','<br>请选择要授权用户的角色!','info');
		    
		}else if(rows.length>1){
		
			 $.messager.alert('提示','<br>不能同时选择多个角色!','info');
		}else{
		
		     var selected=$('#role').datagrid('getSelected');
		     var roleid=selected.id;
		     $.ajax({
		         type:"post",
		         dataType:"html",
		         url:"role!ispurview?roleid="+roleid,
		         success:function(data){
	
		            if(data=="Y")
		            {
		               givenpur(roleid);
		               
		            }else{
		               puredit(data,roleid)
		            }
		          }
		      });
		}
    }
    
    //添加权限
    
    function givenpur(roleid)
    {
    	$("#loading3").show();
   		$('#TreeMenu').tree( {
			checkbox : true,
			cascadeCheck:false,
			url : "fun!getAllfunctions.action",
			onCheck:function(node)
			{
                 var flag=$('#TreeMenu').tree('isLeaf',node.target);
                 if(!flag)
                 { 
                   var children=$('#TreeMenu').tree('getChildren',node.target);
                   
					if(node.checked==true)
					{
					   for(var i=0; i<children.length; i++){
                        if(children[i]!=null)
                        {
	                   		$('#TreeMenu').tree('check', children[i].target);
                        }
				      }
					}else{
					    for(var i=0; i<children.length; i++){
                        if(children[i]!=null)
                        {
	                   		$('#TreeMenu').tree('uncheck', children[i].target);
                        }
				      }
					}
                 }
			    
			},
			onLoadSuccess:function()
			{
				$("#loading3").hide();
				$('#TreeMenu').tree('collapseAll');
			}
		});

        document.getElementById("puradd").style.display='block';
               $('#puradd').dialog({
				title:'角色授权',
				closable:false,
				iconCls:'icon-add',
				modal: true,
				onMove:function(left,top){  //鼠标拖动时事件
   			 	   var dom_width=document.body.offsetWidth;//body的宽度
   			 	   var dom_height=document.body.offsetHeight;//body的高度
   			 	   var dd_width= $('#puradd').panel('options').width;//dialog的宽度
   			 	   var dd_height= $('#puradd').panel('options').height;//dialog的高度
                   var myleft=(dom_width-dd_width)/2;
                   var mytop=(dom_height-dd_height)/2;
   			 	   if(left<1||left>dom_width-dd_width||top<1||top>dom_height-dd_height){	
					 $('#puradd').dialog('move',{    
						left:myleft,    
						top:mytop    
				  	});  
   			 	   }
		   		 },
				buttons:[{
					text:'确定',
					handler:function(){
					     	var nodes = $('#TreeMenu').tree('getChecked');
					     	var s = '';
							for(var i=0; i<nodes.length; i++){
								if (s != '') s += ',';
								s += nodes[i].id;
							}
						   if(nodes.length==0)
						   {
						      $.messager.alert('提示','<br>请选择相应的权限!','info');
						   }else{
						   
	                       $.ajax({
	                           type:"post",
	                           dataType:"html",
	                           url:"role!saverolefun?roleid="+roleid+"&funs="+s,
	                           success:function(data){
	                              if(data=="Y")
	                              {
	                                 $.messager.alert('提示','<br>授权成功!','info');
	                                 $('#puradd').dialog('close');
	                              }else if(data=="N")
	                              {
	                                  $.messager.alert('提示','<br>授权失败!','info');
	                              }
	                           }
	                       });
					  	 }	
				
					}
				},{
					text:'取消',
					handler:function(){
						$('#puradd').dialog('close');
						$("#TreeMenu").html("");
					}
				}],
				toolbar:[{
					text:'展开',
					iconCls:'openstatus',
					handler:function(){
					   
					   var node = $('#TreeMenu').tree('getSelected');
						if (node){
							$('#TreeMenu').tree('expandAll', node.target);
						} else {
							$('#TreeMenu').tree('expandAll');
						}
					}
				},'-',{
					text:'关闭',
					iconCls:'closestatus',
					handler:function(){
						   var node = $('#TreeMenu').tree('getSelected');
							if (node){
								$('#TreeMenu').tree('collapseAll', node.target);
							} else {
								$('#TreeMenu').tree('collapseAll');
							}
						
					}
				}]
			});    
      
    }
    
    //修改权限
     function puredit(data,roleid)
    {
        var i=0;
        $('#purtree').tree( {
		checkbox : true,
		cascadeCheck:false,
		url : "fun!getAllfunctions.action",
		onLoadSuccess:function()
		{
		 	//$('#purtree').tree('collapseAll');
			var str=data;
	        var strs= new Array();
	        var strs=str.split(',');
	        var funs='';   
	        for(var i=0;i<strs.length;i++)
	        {
	            var node = $('#purtree').tree('find',strs[i].toString());
	             if(node!=null)
			       {
			            $('#purtree').tree('check', node.target);
			       }
	        }
		}
		
	 });
			       
        document.getElementById("puredit").style.display='block';
        
               $('#puredit').dialog({
				title:'权限修改',
				iconCls:'icon-edit',
				closable:false,
				modal: true,
				onMove:function(left,top){  //鼠标拖动时事件
   			 	   var dom_width=document.body.offsetWidth;//body的宽度
   			 	   var dom_height=document.body.offsetHeight;//body的高度
   			 	   var dd_width= $('#puredit').panel('options').width;//dialog的宽度
   			 	   var dd_height= $('#puredit').panel('options').height;//dialog的高度
                   var myleft=(dom_width-dd_width)/2;
                   var mytop=(dom_height-dd_height)/2;
   			 	   if(left<1||left>dom_width-dd_width||top<1||top>dom_height-dd_height){	
					 $('#puredit').dialog('move',{    
						left:myleft,    
						top:mytop    
				  	});  
   			 	   }
		   		 },
				buttons:[{
					text:'确定',
					handler:function(){
					
				       	var nodes = $('#purtree').tree('getChecked');
					     	var s = '';
							for(var i=0; i<nodes.length; i++){
								if (s != '') s += ',';
								s += nodes[i].id;
							}
						
						if(nodes.length==0)
						{
						    $.messager.alert('提示','<br>请选择相应的权限!','info');
						    
						}else{
	                       $.ajax({
	                           type:"post",
	                           dataType:"html",
	                           url:"role!updaterolefun?roleid="+roleid+"&funs="+s,
	                           success:function(data){
	                              if(data=="Y")
	                              {
	                                 $.messager.alert('提示','<br>修改成功!','info');
	                                 $('#puredit').dialog('close');
	                                 
	                              }else
	                              {
	                                  $.messager.alert('提示','<br>修改失败!','info');
	                              }
	                           }
	                       });
						}
					}
				},{
					text:'取消',
					handler:function(){
						$('#puredit').dialog('close');
						$("#purtree").html("");
						
						
					}
				}],
				toolbar:[{
					text:'展开',
					iconCls:'openstatus',
					handler:function(){
					   
					   var node = $('#purtree').tree('getSelected');
						if (node){
							$('#purtree').tree('expandAll', node.target);
						} else {
							$('#purtree').tree('expandAll');
						}
					}
				},'-',{
					text:'关闭',
					iconCls:'closestatus',
					handler:function(){
						   var node = $('#purtree').tree('getSelected');
							if (node){
								$('#purtree').tree('collapseAll', node.target);
							} else {
								$('#purtree').tree('collapseAll');
							}
						
					}
				}]
			});    
      
    }
   

	//查看角色权限信息
	function selpurview(id)
	{
		 $.ajax({
		     type:"post",
		     dataType:"html",
		     url:"role!ispurview?roleid="+id,
		     success:function(data)
		     {
		        if(data=="Y")
		        {
		            $.messager.alert('提示','<br>该角色还没有分配权限!','info');
		           
		        }else{
	                 $('#rolepurs').tree( {
						checkbox : true,
						cascadeCheck:false,
						url : "fun!getAllfunctions.action",
						onLoadSuccess:function()
						{
						 	//$('#rolepurs').tree('collapseAll');
							var str=data;
					        var strs= new Array();
					        var strs=str.split(',');
					        var funs='';   
					        for(var i=0;i<strs.length;i++)
					        {
					            var node = $('#rolepurs').tree('find',strs[i].toString());
					             if(node!=null)
							       {
							            $('#rolepurs').tree('check', node.target);
							       }
					        }
						}
						
					 });
				     document.getElementById("selpurs").style.display='block';
				     $('#selpurs').dialog({
						title:'查看权限',
						//closable:false,
						iconCls:'icon-search',
						modal: true,
						onMove:function(left,top){  //鼠标拖动时事件
			   			 	   var dom_width=document.body.offsetWidth;//body的宽度
			   			 	   var dom_height=document.body.offsetHeight;//body的高度
			   			 	   var dd_width= $('#selpurs').panel('options').width;//dialog的宽度
			   			 	   var dd_height= $('#selpurs').panel('options').height;//dialog的高度
			                   var myleft=(dom_width-dd_width)/2;
			                   var mytop=(dom_height-dd_height)/2;
			   			 	   if(left<1||left>dom_width-dd_width||top<1||top>dom_height-dd_height){	
								 $('#selpurs').dialog('move',{    
									left:myleft,    
									top:mytop    
							  	});  
			   			 	   }
					   		 },
						buttons:[{
						text:'取消',
						handler:function(){
							$('#selpurs').dialog('close');
							$("#rolepurs").html("");
							
						}
					}],
					toolbar:[{
							text:'展开',
							iconCls:'openstatus',
							handler:function(){
							   
							   var node = $('#rolepurs').tree('getSelected');
								if (node){
									$('#rolepurs').tree('expandAll', node.target);
								} else {
									$('#rolepurs').tree('expandAll');
								}
							}
						},'-',{
							text:'关闭',
							iconCls:'closestatus',
							handler:function(){
								   var node = $('#rolepurs').tree('getSelected');
									if (node){
										$('#rolepurs').tree('collapseAll', node.target);
									} else {
										$('#rolepurs').tree('collapseAll');
									}
								
							}
						}]
				});
	        
	        }
	     }
	 });
		
}

	function checkunique()
	{
	   var rolename=$("#roleName").val();
	   var role=encodeURI(rolename);
	   $.ajax({
	      type:"post",
	      dataType:"html",
	      url:"role!checkrolename?rolename="+role,
	      success:function(data){
	         if(data=="N")
	         {
	             $.messager.alert('提示','<br>该角色名已存在!','info');
	             $('#rolesave').linkbutton('disable');
	         }else{
	               $('#rolesave').linkbutton('enable');
	         }
	
	      }
	      
	   });
	   
	}
	
//相关验证
	  $.extend($.fn.validatebox.defaults.rules, {   
		    rolename:{     
		       validator: function (value, param) {
           		 return /^[\u0391-\uFFE5\w]+$/.test(value) && value.length<18;
		        },
		        message: '只允许汉字、英文字母、数字及下划线,不能大于18位'  
		    }
    });

  function reload()
   {
       	var ops = $('#role').datagrid("options");
  	 	ops.pageNumber = 1;
 	  	var ul = ops.url;
 	  	$('#role').datagrid({url:ul});
   }
   
   function seladmin(myid,btnid)
   {
     var level=$("div[id='"+myid+"'] select option:selected").val();
       if(level=='true')
       {
         $.ajax({
	      type:"post",
	      dataType:"html",
	      url:"role!isadmin.action",
	      success:function(data){
	         if(data=="N")
	         {
	             $.messager.alert('提示','<br>不能添加多个超级管理员!','info');
	             $('#'+btnid).linkbutton('disable');
	         }else{
	               $('#'+btnid).linkbutton('enable');
	         }
	
	      }
	      
	   });
       }else{ 
            $('#'+btnid).linkbutton('enable');
       }
   }
   	   
</script>

  </head>
  
  <body style="position: relative;overflow: hidden;padding: 1px; margin: 0px;">
   <table id="role" toolbar="#tb"></table>
   
   <div id="tb" style="height:auto">  
	    <table cellpadding="0" cellspacing="0" style="width:98%">
			<tr>
				<!-- 工具 条 -->
				<td>
				    <a href="javascript:roleadd()"  class="easyui-linkbutton" iconCls="icon-add" plain="true" title="增加">添加</a> 
	        		<a href="javascript:roledel()" class="easyui-linkbutton" iconCls="icon-remove" plain="true" title="删除">删除</a>  
	       	 		<a href="javascript:roleupdate()"  class="easyui-linkbutton"  iconCls="icon-edit" plain="true" title="修改">修改</a> 
	        		<a href="javascript:emppurview()" class="easyui-linkbutton" iconCls="user-approve" plain="true" title="授权用户">授权用户</a>
	        		<a href="javascript:rolepurview()" class="easyui-linkbutton" iconCls="user-fun" plain="true" title="分配权限">分配权限</a>  
                    <a href="javascript:reload()" class="easyui-linkbutton" iconCls="icon-reload" plain="true" title="刷新">刷新</a>
				</td>
			</tr>
		</table>
	</div>
	  <!-- ----------------------------------角色添加-------------------------------------------------- -->
     <div id="roleadd" icon="icon-add" style="padding:20px;width:420px;height:275px;display:none;">
		<form  method="post" name="addroleform" id="addroleform" >
		
			            角色名称： <input type="text" id="name" name="name" class="easyui-validatebox" required="true" validType="rolename" onchange="checkunique();"/>&nbsp;&nbsp;<font color="red">*</font>
			    <br/><br/>
			  	  角色描述：
				<textarea name="remark" id="remark" rows="4" cols="30" ></textarea>
				<input type="hidden" id="id" name="id" />
		      </form>
     </div>
	 <!-- ----------------------------------添加权限-------------------------------------------------- -->
	  <div id="puradd" icon="icon-add" style="padding-left:100px;width:450px;height:400px;display:none; ">
		     <span id="loading3" style="display: none;"><img id='showloading' src='images/loading2.gif' style='margin-top:80px; margin-left:80px;'></span>
			  <ul id="TreeMenu" animate="false"  style="width: 100%;margin-top:10px;"></ul>
      </div>
     <!-- ----------------------------------修改权限-------------------------------------------------- -->
      <div id="puredit" icon="icon-add" style="padding-left:80px;width:500px;height:400px;display:none; ">
       		<span id="loading2" style="display: none;"><img id='showloading' src='images/loading2.gif' style='margin-top:80px; margin-left:80px;'></span>
			  <ul id="purtree" animate="false"  style="width: 100%;margin-top:10px;"></ul>
      </div>
     <!-- ----------------------------------查看权限-------------------------------------------------- -->
      <div id="selpurs" icon="icon-add" style="padding-left:80px;width:450px;height:400px;display:none; ">
       		  <span id="loading" style="display: none;"><img id='showloading' src='images/loading2.gif' style='margin-top:80px; margin-left:80px;'></span>
			  <ul id="rolepurs" animate="false"  style="width: 100%;margin-top:10px;"></ul>
      </div>
     <!-- ---------------------------------授权用户----------------------------------------------- -->
	    <div id="operpurview" style="padding:10px;width:420px;height:300px;display:none;">
		<span>
			  <img alt="" src="images/users_edit.png"> <span id="rolename"></span>
	    </span>
<!--			    <div id="opers"></div>-->
<br/><br/>
		<span style="margin-left: 50px;">
		选择用户:<select id="username" class="easyui-combotree" multiple="true" cascadeCheck="true" onlyLeafCheck="false" url="role!selemps.action" style="width:180px;" class="easyui-validatebox" required="true"></select>
  		</span>
	    </div>
      
      <!-- ---------------------------------授权用户修改----------------------------------------------- -->
	    <div id="upoperpurview" style="padding:10px;width:420px;height:300px;display:none;">
		<span>
			  <img alt="" src="images/users_edit.png"> <span id="uprolename"></span>
	    </span>
			 <fieldset style="margin-top:8px;">
                 <legend>
				&nbsp;<font color="red">*</font>&nbsp;选择用户
			    </legend>
			    <div id="upopers"></div>
			 </fieldset>
	    </div>
  </body>
  
</html>
