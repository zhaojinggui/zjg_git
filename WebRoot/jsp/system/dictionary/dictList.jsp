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
    
    <title>数据字典管理</title>
    
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
		
			$('#dictionary').datagrid({
				title:'',
				width:'98%',
				height:hightfit(0.88),
				nowrap: false,
				striped: true,
				collapsible:false,
				url:'dict!selalldict.action',
//				sortName: 'operid',
				sortOrder: 'desc',
				remoteSort: false,
				fitColumns:true,
				columns:[
				[
				    {field:'ditctId',checkbox:true},
				    {field:'dictName',title:'项目名称',width:150,rowspan:2},
					{field:'dictKeyName',title:'项目键名',width:150,rowspan:2},
					{field:'dictKeyValue',title:'项目键值',width:150,rowspan:2},
					{field:'dictStatus',title:'状态',width:150,rowspan:2,formatter:function(value,rowDate,rowIndex){
					   var msg='';
					   if(value==true)
					   {
					      msg="启用";
					   }else{
					      msg="停用";
					   }
					   return msg; 
					}},
					{field:'sortNum',title:'序号',width:150,rowspan:2},
				{field:'dictDesc',title:'描述',width:150,rowspan:2}
				
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
			
			var p = $('#dictionary').datagrid('getPager');
			if (p){
				$(p).pagination({
				    onBeforeRefresh:function(){
						 $('#dictionary').datagrid('reload');
					},
					onSelectPage:function(pageNumber, pageSize){
					tosearch(pageNumber,pageSize);
				}
				});
			}
		   
		});
		  function tosearch(pageNumber, pageSize){
			var name=$.trim($("#name").val());
	  		var keyname=$.trim($("#keyname").val());
	  		var keyvalue=$.trim($("#keyvalue").val());
		    $.ajax({ 
		       type: "post",
		       dataType:"json", 
		       url:"dict!selalldict.action?dictname="+encodeURI(name)+"&keyname="+encodeURI(keyname)+"&keyvalue="+encodeURI(keyvalue)+"&page="+pageNumber+"&rows="+pageSize,
		       success: function(msg){ 
				   if(msg.rows!=null){ 
				    $('#dictionary').datagrid("loadData",msg); 
				    } 
		       } 
		       
		    }); 
   		 } 
   		 

//字典添加
	function dictadd()
   {
       document.getElementById("dictadd").style.display='block';
       $('#dictadd').dialog({
				title:'字典项添加',
				closable:false,
				iconCls:'icon-add',
				modal: true,
				onMove:function(left,top){  //鼠标拖动时事件
   			 	   var dom_width=document.body.offsetWidth;//body的宽度
   			 	   var dom_height=document.body.offsetHeight;//body的高度
   			 	   var dd_width= $('#dictadd').panel('options').width;//dialog的宽度
   			 	   var dd_height= $('#dictadd').panel('options').height;//dialog的高度
                   var myleft=(dom_width-dd_width)/2;
                   var mytop=(dom_height-dd_height)/2;
   			 	   if(left<1||left>dom_width-dd_width||top<1||top>dom_height-dd_height){	
					 $('#dictadd').dialog('move',{    
						left:myleft,    
						top:mytop    
				  	});  
   			 	   }
   			 	},
				buttons:[{
					text:'保存',
					id:'btnsave',
					handler:function(){
					var flag=$("#dictaddform").form('validate');
					if(flag==true)
					{
					   $('#btnsave').linkbutton('disable'); 
					   $("#dictaddform").form('submit',{
					    url:'dict!savedic.action',
					    success:function(data){
					       $('#btnsave').linkbutton('enable'); 
					      if(data=="Y")
					      {
					          $.messager.alert('提示','<br>保存成功!','info',function(){
					               $('#dictadd').dialog('close');
							       document.dictaddform.reset();
							       $('#dictionary').datagrid("reload"); 
					          });
					      }else{
					      	   $.messager.alert('提示','<br>保存失败!','info');
					      	    $('#btnsave').linkbutton('enable'); 
					      }
					      
					    }
					   });
					   
					}
					
					}
				},{
					text:'取消',
					
					handler:function(){
						$('#dictadd').dialog('close');
						document.dictaddform.reset();
					}
				}]
				
			});
					  
   } 
   
   //字典删除
   function dictdel()
   {
	    var ids = [];
		var rows = $('#dictionary').datagrid('getSelections');
		for(var i=0;i<rows.length;i++){
			ids.push(rows[i].ditctId);
		}
		var getids=ids.join(',');
		
		if(ids.length<1)
		{
		    $.messager.alert('提示','<br>请选择要删除的记录!','info');
		    
		}else{
		
			$.messager.confirm('提示', '<br>确认将这'+ids.length+'条记录删除?', function(r){
			if (r){
					
					$.ajax({
					   type:"post",
					   dataType:"html",
					   url:"dict!dictdel?dictid="+getids,
					   success:function(data){
					      if(data=="Y")
					      {
					         $.messager.alert('提示','<br>删除成功!','info',function(){
						          reload();
					         });
					      }else{
					         $.messager.alert('提示','<br>删除失败!','info');
					      }
					      
					   }
					   
					});	
			}
	        });
		  
		}
   }
   
   
   //修改字典
   	 function dictupdate()
   {
       
        var ids = [];
		var rows = $('#dictionary').datagrid('getSelections');
		for(var i=0;i<rows.length;i++){
			ids.push(rows[i].ditctId);
		}
		var getids=ids.join(',');
		
		if(ids.length<1)
		{
		    $.messager.alert('提示','<br>请选择要修改的记录!','warning');
		    
		}else if(ids.length>1){
		
			 $.messager.alert('提示','<br>不能同时修改多条记录!','warning');
		}else{

      		document.getElementById("dictedit").style.display='block';
       		var selected = $('#dictionary').datagrid('getSelected');
       
           $("#ditctId").val(selected.ditctId);
       	   $("#dictName1").val(selected.dictName);
	       $("#dictKeyName1").val(selected.dictKeyName);
	       $("#dictKeyValue1").val(selected.dictKeyValue);
	       $("#sortNum1").val(selected.sortNum);
	       if(selected.dictStatus==true)
	       {
	         $("#estate1").attr("checked",true);
	       }else
	       {
	        $("#estate2").attr("checked",true);
	       }
	       $("#dictDesc1").val(selected.dictDesc);
	       
	       $("#dictName1").attr("readonly",true);
	       
           $('#dictedit').dialog({
				title:'字典修改',
				iconCls:'icon-edit',
				closable:false,
				modal: true,
				onMove:function(left,top){  //鼠标拖动时事件
   			 	   var dom_width=document.body.offsetWidth;//body的宽度
   			 	   var dom_height=document.body.offsetHeight;//body的高度
   			 	   var dd_width= $('#dictedit').panel('options').width;//dialog的宽度
   			 	   var dd_height= $('#dictedit').panel('options').height;//dialog的高度
                   var myleft=(dom_width-dd_width)/2;
                   var mytop=(dom_height-dd_height)/2;
   			 	   if(left<1||left>dom_width-dd_width||top<1||top>dom_height-dd_height){	
					 $('#dictedit').dialog('move',{    
						left:myleft,    
						top:mytop    
				  	 });  
   			 	   }
   			 	},
				buttons:[{
					text:'保存',
					handler:function(){
					    
					    var flag=$("#dicteditform").form('validate');
				    	if(flag==true){
				    	   
				    	    $("#dicteditform").form('submit',{
						    url:'dict!dictupdate.action',
						    success:function(data){
						      
						      if(data=="Y")
						      {
							          $('#dictedit').dialog('close');
							          $.messager.alert('提示','<br>修改成功!','info',function(){
							          $('#dictionary').datagrid("reload"); 
						          });
						      }
						      
						    }
						   });
				    	
				    	}
					}
				},{
					text:'取消',
					handler:function(){
						$('#dictedit').dialog('close');
					}
				}]
			});
  
		}			  
   } 
   
   //开启字典
   function dictopen()
   {
		var ids = [];
		var rows = $('#dictionary').datagrid('getSelections');
		for(var i=0;i<rows.length;i++){
			ids.push(rows[i].ditctId);
		}
		if(ids.length==0)
		{
		        $.messager.alert('提示','<br>请选择要开启的记录','warning');
		     
		}else{
				var count=0;
				for(var i=0;i<rows.length;i++){
					if(rows[i].status=="启用")
					{
					  count ++;
					}
				}
			    if(count>0)
			    {
			          $.messager.alert('提示','<br>有'+count+'个字典项已是启用状态,请重新选择!','warning');
			           
			    }else{
			       
			           	$.messager.confirm('提示', '<br>确定要启用吗?', function(r){
			if (r){ 
			        
			        $.ajax({
					   type:"post",
					   dataType:"html",
					     url:"dict!changestatus?dictid="+ids+"&status=true",
					   success:function(data){
					      if(data=="Y")
					      {
					         $.messager.alert('提示','<br>启用成功!','info',function(){
					            $('#dictionary').datagrid('reload');
					         });
					      }else{
					         $.messager.alert('提示','<br>启用失败!','info');
					      }
					      
					   }
					   
					});	
					}
				  });
			   }
			}
						  
   }
   //字典停用
   function dictclose()
   {
   	    var ids = [];
		var rows = $('#dictionary').datagrid('getSelections');
		for(var i=0;i<rows.length;i++){
			ids.push(rows[i].ditctId);
		}
		if(ids.length==0)
		{
		        $.messager.alert('提示','<br>请选择要开启的记录!','info');
		     
		}else{
				var count=0;
				for(var i=0;i<rows.length;i++){
					if(rows[i].status=="停用")
					{
					  count ++;
					}
				}
			    if(count>0)
			    {
			          $.messager.alert('提示','<br>有'+count+'个字典项已是停用状态,请重新选择!','warning');
			           
			    }else{
			        
			        $.messager.confirm('提示', '<br>确定要停用吗?', function(r){
			if (r){ 
			      $.ajax({
					   type:"post",
					   dataType:"html",
					   url:"dict!changestatus?dictid="+ids+"&status=false",
					   success:function(data){
					      if(data=="Y")
					      {
					         $.messager.alert('提示','<br>停用成功!','info',function(){
						         $('#dictionary').datagrid('reload');
					         });
					      }else{
					         $.messager.alert('提示','<br>停用失败!','info');
					      }
					   }
					   
					});	
				   }
			     });
			   }
			}
   }
    
    function uniqdictname()
    {
        var dictname=$("#dictName").val();
        var key=$("#dictKeyName").val();
        var keys=encodeURI(key);
          $.ajax({
             type:"post",
             dataType:"html",
             url:"dict!checkdictunique.action?dictname="+dictname+"&key="+keys,
             success:function(data)
             {
               if(data=="N")
                {
                   $.messager.alert('提示','<br>该字典项键名已存在','info');
                   $('#btnsave').linkbutton('disable');    
                }else{
                    $('#btnsave').linkbutton('enable');  
                }
               
             }
          });
     }  
  
    function reload()
   {
   	    var ops = $('#dictionary').datagrid("options");
  	 	ops.pageNumber = 1;
 	  	var ul = ops.url;
 	  	$('#dictionary').datagrid({url:ul});
   }
   
  $.extend($.fn.validatebox.defaults.rules, {   

    objName: {
        validator: function (value, param) {
            return /^[\u0391-\uFFE5\w]+$/.test(value) && value.length<18;
        },
        message: '项目名称只允许汉字、英文字母、数字及下划线,不能大于18位'
    },
    maxlength: {
        validator: function (value, param) {
            return value.length<500;
        },
        message: '输入长度不能大于500'
    }
                   
    });
  
   function search(){
	  var name=$.trim($("#name").val());
	  var keyname=$.trim($("#keyname").val());
	  var keyvalue=$.trim($("#keyvalue").val());
	  $('#dictionary').datagrid({
		url:"dict!selalldict.action?dictname="+encodeURI(name)+"&keyname="+encodeURI(keyname)+"&keyvalue="+encodeURI(keyvalue),
		onLoadSuccess:function(data){
		   if(data.total==0)
		   {
		      $.messager.alert('提示','<br>暂无此查询记录','info');
		   }
		}
	}); 
   } 
	</script>

  </head>
  <body style="position: relative;overflow: hidden;padding: 1px; margin: 0px;">
   	<div id="panel" class="easyui-panel" title="数据字典管理" iconCls="sys-dictionary" collapsible="true" style="padding:17px;" border=false; >
   	项目名称：<input type="text" id="name" name="name" title="请输入项目名称查询!" style="width: 105px;"/>&nbsp;&nbsp;&nbsp;&nbsp;
   	项目键名： <input type="text" id="keyname" name="keyname" title="请输入项目键名查询!" style="width: 105px;"/>&nbsp;&nbsp;&nbsp;&nbsp;
   	项目键值： <input type="text" id="keyvalue" name="keyvalue" title="请输入项目键值查询!" style="width: 105px;"/>&nbsp;&nbsp;&nbsp;&nbsp;
   	<input type="button" value="查 询" onclick="search()"/>
   	</div>
  <table id="dictionary" toolbar="#tb"></table>
   <div id="tb" style="height:auto">  
	    <table cellpadding="0" cellspacing="0" style="width:100%">
			<tr>
				<!-- 工具 条 -->
				<td>
				    <a href="javascript:dictadd()"  class="easyui-linkbutton" iconCls="icon-add" plain="true" title="增加">添加</a> 
	        		<a href="javascript:dictdel()" class="easyui-linkbutton"  iconCls="icon-remove" plain="true" title="删除">删除</a>  
	       	 		<a href="javascript:dictupdate()"  class="easyui-linkbutton"  iconCls="icon-edit" plain="true" title="修改">修改</a> 
	        		<a href="javascript:dictopen()"  class="easyui-linkbutton"  iconCls="open" plain="true" title="启用">启用</a> 
	        		<a href="javascript:dictclose()" class="easyui-linkbutton" iconCls="pause" plain="true" title="停用">停用</a>  
                    <a href="javascript:reload()" class="easyui-linkbutton" iconCls="icon-reload" plain="true" title="刷新">刷新</a>
				</td>
			</tr>
		</table>
	</div>
	
  <div id="dictadd" style="padding:30px;width:580px;height:350px;display:none;">
		<form  method="post" name="dictaddform" id="dictaddform" >
						项目名称：
					    <input type="text" id="dictName" name="dictName" class="easyui-validatebox" required="true" validType="objName">&nbsp;&nbsp;<font color="red">*</font>
						项目键名：
						<input type="text" id="dictKeyName" name="dictKeyName" class="easyui-validatebox" required="true" validType="maxlength" >&nbsp;&nbsp;<font color="red">*</font>
						<br/><br>
						项目键值：
					    <input type="text" id="dictKeyValue" name="dictKeyValue" size="20"　 class="easyui-validatebox" required="true" validType="maxlength">&nbsp;&nbsp;<font color="red">*</font>
						字典状态：
						<input type="radio" id="state1" name="dictStatus" value="true" checked="checked"> 启用
						<input type="radio" id="state2" name="dictStatus" value="false"> 停用
						<br/><br>
						序 &nbsp;&nbsp;&nbsp;&nbsp;号：<input type="text" id="sortNum" name="sortNum" >
						<br/><br>
						备&nbsp;&nbsp;&nbsp;&nbsp;注：
						<textarea name="dictDesc" id="dictDesc" rows="4" cols="46" ></textarea>
						
		    </form>
  </div>
	    
  <div id="dictedit" style="padding:30px;width:580px;height:350px;display:none;">
		<form  method="post" name="dicteditform" id="dicteditform" >
						 项目键名：
						<input type="text" id="dictKeyName1" name="dictKeyName" size="20"　 class="easyui-validatebox" required="true" validType="objName" >&nbsp;&nbsp;<font color="red">*</font>
						项目键值：
					    <input type="text" id="dictKeyValue1" name="dictKeyValue" size="20"　 class="easyui-validatebox" required="true" validType="maxlength">&nbsp;&nbsp;<font color="red">*</font>
						<br/><br>
						字典状态：
						<input type="radio" id="estate1" name="dictStatus" value="true" > 启用
						<input type="radio" id="estate2" name="dictStatus" value="false"> 停用
						<br/><br>
						序 &nbsp;&nbsp;&nbsp;&nbsp;号：<input type="text" id="sortNum1" name="sortNum" >
						<br/><br>
						备&nbsp;&nbsp;&nbsp;&nbsp;注：
						<textarea name="dictDesc" id="dictDesc1" rows="4" cols="46" ></textarea>
						<input type="hidden" id="ditctId" name="ditctId" > 
						<input type="hidden" id="dictName1" name="dictName" >
		    </form>
  </div>
	    
  </body>
</html>
