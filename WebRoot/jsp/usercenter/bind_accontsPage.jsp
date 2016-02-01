<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
  <head>
    <title>绑定帐号</title>
	<meta charset="utf-8" />
	<meta http-equiv="x-ua-compatible" content="IE=edge, chrome=1" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/weixin.css" >
	<script type="text/javascript" src="<%=basePath%>js/jquery.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>easyui/jquery.easyui.min.js"></script>
 <script type="text/javascript">
 	//验证标识
 	var flag = true;
 	
	function bind(){
		if(checkForm()){
			if(checkRmUserExist()){
				 if(checkRmUserIsBinded()){
			var wxOpenId =  $("input[name='wxOpenId']").val();
			var name =  $("input[name='name']").val();
			var pwd = $("input[name='password']").val();
			var role =  $('input:radio[name="role"]:checked').val();
			window.location.href="rmUser!bindRmUser.action?name="+encodeURI(encodeURI(name))+"&&password="+pwd+"&&role="+role+"&&openId="+wxOpenId;
			/* $.ajax({
			url:'rmUser!bindRmUser.action',
			data:{openId:wxOpenId,name:name,password:pwd,role:role},
			dataType:'html',
			async:false,
			type:'post',
			success:function(data){
				if(data=='Y'){
					alert('绑定成功');
				}else{
					alert('绑定失败');
				}
			}
		}); */
			
		}
			}
		}
		
	}
	
	//非空验证
	function checkForm(){
		var name =  $("input[name='name']").val();
		var pwd = $("input[name='password']").val();
		if((name.length==0||name==null)||(pwd.length==0||pwd==null)){
			alert('帐号或密码不能为空');
			flag = false;
			return false;
		}
		return true;
	}
	
	
	//帐号是否存在
	function checkRmUserExist(){
		var name =  $("input[name='name']").val();
		var pwd = $("input[name='password']").val();
		$.ajax({
			url:'rmUser!rmUserExist.action',
			data:{name:name,password:pwd},
			dataType:'html',
			async:false,
			type:'post',
			success:function(data){
				if(data=='N'){
					alert('帐号或密码不正确！');
					flag = false;
				}else{
					/* alert('帐号存在!'); */
					flag = true;
				}
			}
		});
		return flag;
	}
	
	//帐号是否已经绑定
	function checkRmUserIsBinded(){
		var name =  $("input[name='name']").val();
		var pwd = $("input[name='password']").val();
		$.ajax({
			url:'rmUser!rmUserIsBinded.action',
			data:{name:name,password:pwd},
			dataType:'html',
			async:false,
			type:'post',
			success:function(data){
				if(data=='Y'){
					alert('帐号已经绑定！');
					flag = false;
				}else{
					/* alert('帐号未绑定!'); */
					flag = true;
				}
			}
		});
		return flag;
	}
	
	function register()
	{
		
		window.location.href="jsp/usercenter/userReading.jsp";
	}
	
 </script>

  </head>
  <body >
 	 <div class="main_accontsPage">
		<h2>账号绑定</h2>
		<form id="bind" >
		 <input type="hidden" id="wxOpenId" name="wxOpenId" value="${sessionScope.openId}">
		<div class="acctonsPage_inputList">
			<div class="tel_yzm">
				<label class="tel"><span>账号</span><s:textfield id="name" name="name"></s:textfield> </label>
				<label class="yzm"><span>密码</span><s:password id="password" name="password"></s:password></label>
				</div>
				<label><input name="role" type="radio" value="0" checked="checked"/>学生</label>
				<label><input name="role" type="radio" value="1"/>家长</label>
				<label><input name="role" type="radio" value="3"/>教师</label>
				<label><input name="role" type="radio" value="2"/>其他</label>
			</div>
		<button type="button" onclick="bind();">立即绑定</button>
		<button type="button" onclick="register();">注 册</button>
		</form>
	</div>
     	
  </body>
</html>
