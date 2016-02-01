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
    
    <title>菜单管理</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	 <link rel="stylesheet" type="text/css" href="css/style.css">
	 <script type="text/javascript">
	 	 $().ready(function(){	 	 
	 	 init();
	     $('#TreeMenu').tree( {
			checkbox : true,
			cascadeCheck:false,
// 			url : "fun!getTreeMenu.action?ids=-1",
			onBeforeExpand:function(node)
			{	
			  	var havechild=$('#TreeMenu').tree('getChildren',node.target);
				if(havechild=='')
				{
				     $.getJSON("fun!getTreeMenu.action?ids="+node.id,function(nodes){
					      $('#TreeMenu').tree('append', {   
		                  parent:node.target,   
		                  data:nodes   
		                 }); 
		                 
						$('#TreeMenu').tree('collapse',node.target);
						$('#TreeMenu').tree('expand',node.target);
					
			     	});
				}
			}

		});
		
		$("#type").change(function(){
			var str=$(this).val();
			var html="";
			if(str=="click")
			{
				html="主&nbsp;&nbsp;&nbsp;键：<input id='key' name='menuKey' class='easyui-validatebox' validType='length[0,50]' style='width: 200px'/>";
			}else if(str=="view")
			{
				html="URL&nbsp;&nbsp;&nbsp;：<input id='url' name='menuUrl' class='easyui-validatebox' validType='myurl' style='width: 200px'/>";
			
			}else{
				html="资源ID：<input id='mediaId' name='meunMediaid' style='width: 200px' readonly />&nbsp;<img src='images/fadd.png' border=0 style='cursor:pointer;' onclick='getmediaid();'></img>";
			}
			$("#input").html(html);
			$.parser.parse("#input");
		});

	 });
	 
	 function init()
	 {
	   	 $.ajax({
			url : "fun!getTreeMenu.action?ids=-1",
			dataType:'json',
			success:function(data){
			 $('#TreeMenu').tree('loadData',data);
			}
		});
	 }
	 function addpurview()
	 {
	     $('#pd').combotree({    
			    url:'fun!allfunstree.action'
			 
			 });
	 
	       document.getElementById("puradd").style.display='block';
		               $('#puradd').dialog({
						title:'添加菜单',
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
							text:'保存',
							handler:function(){
							var name=$("#text").val();
							var spid = $("#pd").combotree('getValue');
							var status=$("input[name='status'][type='radio'][checked]").val();
							var level=$("input[name='check'][type='radio'][checked]").val();
							var show=$("input[name='show'][type='radio'][checked]").val();
							var style=$("#iconCls").val();
							var url=$("#href").val();
							var parm ={name:encodeURI(name),spid:encodeURI(spid),status:encodeURI(status),level:encodeURI(level),style:encodeURI(style),url:encodeURI(url),show:show};
							
							var flag=$("#myform").form('validate');
							if(flag==true)
							{
						      $('#myform').form('submit', {  
							       url:'fun!savefun.action',
							       success:function(data){
							       if(data=="Y")
		                              {
		                                 $.messager.alert('提示','<br>保存成功!','info');
		                                 document.myform.reset();
		                                 $('#puradd').dialog('close');
		                                  init();
		                              }else
		                              {
		                                  $.messager.alert('提示','<br>'+data,'info');
		                              }
								}
							  });
							
							}
							}
						},{
							text:'取消',
							handler:function(){
							    document.myform.reset();
								$('#puradd').dialog('close');
								
							}
						}]
				
					}); 
	    
	 }
	 //修改
	 function editpurview()
	 {
	  
	 	   	var nodes = $('#TreeMenu').tree('getChecked');
	 	   	if(nodes=="")
	 	   	{
	 	   		$.messager.alert('提示','<br>请选择要修改的菜单!','info');
	 	   		
	 	   	}else{
	 	   	
		 	     var funid=nodes[0].id;
			   	 if(nodes.length>1)
			   	 {
			   	      $.messager.alert('提示','<br>不能同时修改多个菜单!','info');
			   	 }else if(funid=='0')
			   	 {
			   	  	  $.messager.alert('提示','<br>根菜单不允许修改!','info');
			   	 }else
			   	 {
			   	    $.ajax({
			   	       type:"post",
			   	       dataType:"json",
			   	       url:"fun!showfun?funid="+funid,
			   	       success:function(data){
		   	           $('#pd').combotree({    
						    url:'fun!allfunstree.action',
						    onLoadSuccess:function(){
						        $("#pd").combotree('setValue',data.pid);
						    }
						 });
			   	       
			   	       //set值
			   	       $("#name").val(data.menuName);
			   	       $("#type").val(data.menuType);
			   	       $("#id").val(data.id);
			   	       setAttrValue(data.menuType,data.menuKey,data.menuUrl,data.meunMediaid);
			   	       
			   	       
			   	  
			   //弹出窗口        
			   document.getElementById("puradd").style.display='block';
		      $('#puradd').dialog({
				title:'菜单修改',
				closable:false,
				iconCls:'icon-edit',
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
					var flag=$("#myform").form('validate');
					if(flag==true)
					{
					    $('#myform').form('submit', {  
					       url:'fun!updatefun.action',
					       success:function(data){
					        if(data=="Y")
		                       {
		                            $.messager.alert('提示','<br>修改成功!','info');
		                           	document.myform.reset();
		                          	$('#puradd').dialog('close');
		                            init();
		                       }else if(data=="N")
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
						$('#puradd').dialog('close');
						document.myform.reset();
					}
				}]
		
			});  
	   	       }
	   	       
	   	    });
	   	   }
	 	   	}
	 }
	 //删除菜单
	 function delpurview()
	 {
         var nodes = $('#TreeMenu').tree('getChecked');
	   	 if(nodes=="")
	   	 {
	   	       $.messager.alert('提示','<br>请选择要删除的菜单!','info');
	   	 }else
	   	 {
	   	     var funid=nodes[0].id;
		   	 if(nodes.length>1)
		   	 {
		   	      $.messager.alert('提示','<br>不能同时删除多个菜单!','info');
		   	 }else if(funid=='0')
		   	 {
		   	  	  $.messager.alert('提示','<br>根菜单不允许删除!','info');
		   	 }else
		   	 {
	  	     		$.messager.confirm('提示', '<br>确定删除此菜单?', function(r){
					if (r){
							
					 	 
					   	    $.ajax({
	                           type:"post",
	                           dataType:"html",
	                           url:"fun!delfun.action?funid="+funid,
	                           success:function(data){
	                              if(data=="Y")
	                              {
	                                 $.messager.alert('提示','<br>删除成功!','info');
	                                   init();
	                                 
	                              }else if(data=="N")
	                              {
	                                  $.messager.alert('提示','<br>删除失败!','info');
	                                  
	                              }else{
	                              
	                                  $.messager.alert('提示',data,'info');
	                              }
	                           }
	                       });
					}
			     });
		      }
	   	 
	   	 }
	 }
	 //展开
	 function opentree()
	 {
	    var node = $('#TreeMenu').tree('getSelected');
						if (node){
							$('#TreeMenu').tree('expandAll', node.target);
						} else {
							$('#TreeMenu').tree('expandAll');
						}
	   
	 }
	 //关闭
	 function closetree()
	 {
	    var node = $('#TreeMenu').tree('getSelected');
							if (node){
								$('#TreeMenu').tree('collapseAll', node.target);
							} else {
								$('#TreeMenu').tree('collapseAll');
							}
	   
	 }
	 //选择Meidid
	 function getmediaid()
	 {
	     $.ajax({
	         type:"post",
	         dataType:"json",
	         url:"fun!getMedias.action",
	         success:function(data){
	           var html="";
	           var count=0;
	           $.each(data,function(k,v){
	           if(count==0)
	           {
	           	html+="<input type='radio' id='"+k+"' name='meadids' value='"+k+"' checked='checked' >"+v+"<br/><br/>";
	           }else{
		           html+="<input type='radio' id='"+k+"' name='meadids' value='"+k+"' >"+v+"<br/><br/>";
	           }
	           count++;
	           });
	           $("#imgs").html(html);
	         }
	     });
	    
	   document.getElementById("iconshow").style.display='block';
       $('#iconshow').dialog({
				title:'选择图文消息',
				closable:true,
				iconCls:'user-man',
				modal: true,
				onMove:function(left,top){  //鼠标拖动时事件
   			 	   var dom_width=document.body.offsetWidth;//body的宽度
   			 	   var dom_height=document.body.offsetHeight;//body的高度
   			 	   var dd_width= $('#iconshow').panel('options').width;//dialog的宽度
   			 	   var dd_height= $('#iconshow').panel('options').height;//dialog的高度
                   var myleft=(dom_width-dd_width)/2;
                   var mytop=(dom_height-dd_height)/2;
   			 	   if(left<1||left>dom_width-dd_width||top<1||top>dom_height-dd_height){	
					 $('#iconshow').dialog('move',{    
						left:myleft,    
						top:mytop    
				  	});  
   			 	   }
   			 	},
				buttons:[{
					text:'确定',
					handler:function(){
						var str=$("input[name='meadids']:checked").val();
						$("#mediaId").val(str);
						$('#iconshow').dialog('close');
					}
				},{
					text:'取消',
					handler:function(){
						$('#iconshow').dialog('close');
					}
				}]
			});;
	 }
	 
	 function setimg(imgname)
	 {
	    $("#iconCls").val(imgname);
	    $('#iconshow').dialog('close');
	 }
	 
	 function publish()
	 {
	 	$.ajax({
	         type:"post",
	         dataType:"html",
	         url:"fun!publishMenu.action",
	         success:function(data){
	         if(data=="Y")
	         {
	             $.messager.alert('提示','<br>发布成功,稍等片刻或重新关注才能立即生效!','info');	         	
	         }else{
	         	 $.messager.alert('提示','<br>发布失败：'+data,'info');
	         }
	        }
	     });
	 }
	 
	 function setAttrValue(str,menuKey,menuUrl,meunMediaid)
	 {
			var html="";
			if(str=="click")
			{
				html="主&nbsp;&nbsp;&nbsp;键：<input id='key' name='menuKey' class='easyui-validatebox' validType='length[0,50]' style='width: 200px' value='"+menuKey+"' />";
			}else if(str=="view")
			{
				html="URL&nbsp;&nbsp;&nbsp;：<input id='url' name='menuUrl' class='easyui-validatebox' validType='myurl' style='width: 200px' value='"+menuUrl+"'/>";
			
			}else{
				html="资源ID：<input id='mediaId' name='meunMediaid' style='width: 200px' value='"+meunMediaid+"' readonly />&nbsp;<img src='images/fadd.png' border=0 style='cursor:pointer;' onclick='getmediaid();'></img>";
			}
			$("#input").html(html);
			$.parser.parse("#input");
	 }
	 
	 $.extend($.fn.validatebox.defaults.rules, {   
    chinese: {
        validator: function (value, param) {
           // return /[\x00-\xff]{1,8}/.test(value);
            return value.replace(/[^\x00-\xFF]/g,'**').length <= 8;
        },
        message: '四个汉字或8个字母以内'
    },
       myurl: {
        validator: function (value, param) {
        return isURL(value);
//            return /[a-zA-z]+://[^\s]*/.test(value);
        },
        message: '请输入正确的URL且长度不能大于800'
    }            
    }); 
	 
	 function isURL(str_url) {// 验证url
		var strRegex = "[a-zA-z]+://[^\s]*";
		var re = new RegExp(strRegex);
		return re.test(str_url) && str_url.length < 800;
	}

	 </script>

  </head>
  
   <body>
    <div id="p" class="easyui-panel" title="自定义菜单管理" icon="org" border="false" style="overflow-x: hidden;">
		<div style="padding:4px;background:#fafafa;width:100%;">
		<a href="javascript:addpurview()" class="easyui-linkbutton" plain="true" iconCls="icon-add">添加</a>
		<a href="javascript:delpurview()" class="easyui-linkbutton" plain="true" iconCls="icon-remove">删除</a>
		<a href="javascript:editpurview()" class="easyui-linkbutton" plain="true" iconCls="icon-edit">修改</a>
		<a href="javascript:publish()" class="easyui-linkbutton" plain="true" iconCls="icon-ok">发布</a>
		<a href="javascript:opentree()" class="easyui-linkbutton" plain="true" iconCls="openstatus">展开</a>
		<a href="javascript:closetree()" class="easyui-linkbutton" plain="true" iconCls="closestatus">关闭</a>
	</div>
	  <div id="purshow" icon="icon-add" class="easyui-panel" style="padding-left:50px;height:610px; ">
			  <ul id="TreeMenu" animate="false"  style="width: 100%;margin-top:10px;"></ul>
      </div>	
	</div>
       <div id="puradd" icon="icon-add" style="padding:20px;width:440px;height:350px; display: none; ">
		<form id="myform" name="myform" action="" method="post">
			菜单名称:
			<input type="hidden" id="id" name="id" style="width: 200px" />
			<input id="name" name="menuName" style="width: 200px"  class="easyui-validatebox" validType="chinese" required="true" />
			<br/><br/>
			上级菜单:
			<select id="pd" class="easyui-combotree" name="pid" style="width: 200px;" required="true"></select>
			<br/><br/>
			菜单类型:
			<select id="type" name="menuType">
			<option value="-">--请选择--</option>
			<option value="click">点击事件</option>
			<option value="view">跳转URL</option>
			<option value="media_id">下发图文消息</option>
			<option value="view_limited">跳转图文消息</option>
			</select>
			<br/><br/>
			<span id="input">
			主&nbsp;&nbsp;&nbsp;键：
			<input id="key" name="menuKey" class="easyui-validatebox" validType="length[0,50]" style="width: 200px"/>
			</span>
			<br/><br/>
		 </form>
      </div>
       <div id="iconshow" icon="icon-add" style="padding:10px;width:300px;height:250px;display:none;">
		      <span id="imgs"></span>
      </div>
  </body>
</html>
