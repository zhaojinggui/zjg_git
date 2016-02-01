<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<jsp:include page="/common/common.jsp"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>微信后台管理系统</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript" src="js/common.js"></script>
	<script type="text/javascript">
	
   $(function() {
	$('#TreeMenu').tree( {
		onClick : function(node) {
			$(this).tree('toggle', node.target);
			var url = node.id;
			if (url != "" && typeof (url) != "undefined" && url != null) {
				addTabs(node.text, node.id, node.iconCls,node.id);
			} else {
				$('TreeMenu').tree('toggle', node.target);
			}
		}
	});
});

 function addTabs(title,url,iconCls,id){
    	   var tab=$('#tt');
		   var title=title;
		   var url=url;
		  var content='<iframe scrolling="yes" frameborder="0"  src="'+url+'" style="width:100%;height:100%;"></iframe>';
		   var exists=tab.tabs('exists',title);
			  if(exists==false){
				 tab.tabs('add',{
				 title:title,
				 content:content,
				 fit:true,
				 iconCls:iconCls,
				 closable:true
			    });
			  }else{
                 tab.tabs('select',title);				    
				 var stab=tab.tabs('getSelected');
				 tab.tabs('update',{
					 tab: stab,
				     options:{
					   title:title,
					   content:content,
				       fit:true,
				       iconCls:iconCls,
				       closable:true
				     }
				 });
			  }
       }
 
   function logout(){
		 
		var b=confirm("确定要退出系统?");
		if (b == true) {
			
			window.location.href="user!logout.action";
		} 
   }
   function alterpwd()
   {
   		addTabs("修改密码","system/user/alterpassword.jsp","","");
   }
  function closeSelectedTab(){
    	   var p=$('#tt').tabs('getSelected');
    	   var title = p.panel('options').title;
    	   $('#tt').tabs('close',title);
       }
       
       function closeAllTabs(){
    	   
    	 $('.tabs-inner span').each(function(i,n){
            var t = $(n).text();
            if(t!="" && t!='欢迎首页')
            	{
            	  $('#tt').tabs('close',t);
            	}
        }); 
       }
       function closexpcurrent()
       {
    	    var p=$('#tt').tabs('getSelected');
    	    var title = p.panel('options').title;
    	    
    	    $('.tabs-inner span').each(function(i,n){
            var t = $(n).text();
            if(t!="" && t!='欢迎首页'&& t!=title)
            	{
            	  $('#tt').tabs('close',t);
            	}
        }); 
    	    
       }
       
    $(function(){
		  $(document).bind('contextmenu',function(e){
				$('#tabs_mm').menu('show', {
					left: e.pageX,
					top: e.pageY
				});
				return false;
			});
		})
    </script>
  </head>
  
 <body class="easyui-layout">
	<div region="north" title="" split="false" style="height:63px;">
	<jsp:include page="/common/top.jsp"/>
	</div>
	<div region="west" iconCls="icon-reload" title="菜单列表" split="true" style="width:180px;">
		<ul id="TreeMenu" class="easyui-tree">
<!-- 			<li iconCls="guilei"> -->
<!-- 				<span>微信端</span> -->
<!-- 				<ul> -->
<!-- 							<li id="jsp/usercenter/userReading.jsp" iconCls="zhangjie" text="商户筛选" > -->
<!-- 								<span>注册账号</span> -->
<!-- 							</li> -->
						
<!-- 							<li id="jsp/usercenter/bind_accontsPage.jsp" iconCls="export" text="帐号绑定" > -->
<!-- 								<span>账号绑定</span> -->
<!-- 							</li> -->
						
<!-- 							<li id="usercenter!viewList.action?name=jake" iconCls="search" text="筛选条件管理" > -->
<!-- 								<span>学生个人信息</span> -->
<!-- 							</li> -->
<!-- 							<li id="teacher!toTeacherPageJsp.action" iconCls="search" text="筛选条件管理" > -->
<!-- 								<span>教师个人信息</span> -->
<!-- 							</li> -->
<!-- 							<li id="<%=basePath%>shop/screening/screeningList.jsp" iconCls="search" text="筛选条件管理" > -->
<!-- 								<span>学生列表</span> -->
<!-- 							</li> -->
<!-- 				</ul> -->
<!-- 			</li> -->
			<li iconCls="sys-conf">
				<span >后台管理</span>
				<ul>
					<li id="jsp/system/function/function.jsp" iconCls="org" text="自定义菜单管理" >自定义菜单管理</li>
					<li id="jsp/activity/rmActivityUser.jsp" iconCls="zixun" text="报名活动管理" >报名活动管理</li>
					<li id="jsp/activity/rmActivityCardRecord.jsp" iconCls="user-fun" text="发卡记录管理" >发卡记录管理</li>
					<li id="jsp/system/dictionary/dictList.jsp" iconCls="sys-dictionary" text="数据字典管理" >数据字典管理</li>
				</ul>
			</li>
		</ul>
		
	</div>
	<div region="south" title="" split="false" style="height:26px;">
		<jsp:include page="/common/down.jsp"/>
	</div>
	<div region="center" style="overflow:hidden;">
			<div class="easyui-tabs" fit="true" border="false" id="tt">
				<div title="欢迎首页" fit="true" closable="false" cache="false"> 
						<iframe scrolling="yes" name="indexpage" frameborder="0" src="welcome.jsp" style="width: 100%; height: 100%;"></iframe>
				</div>
				<div id="tabs_mm" class="easyui-menu" style="width: 150px;">
			        <div  onclick="closeSelectedTab();">
					      关闭
					</div>
					 <div  onclick="closeAllTabs();">
					      关闭所有
					</div>
					 <div  onclick="closexpcurrent();">
					      除当前选择外关闭所有
					</div>
		      </div>
			</div>
		</div>
		
</body>
</html>
