<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
  <head>
    <title>注册读伴儿账号</title>
	<meta charset="utf-8" />
	<meta http-equiv="x-ua-compatible" content="IE=edge, chrome=1" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/weixin.css" >
	<script type="text/javascript" src="<%=basePath%>js/jquery.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript">
     var flag = true;
    //验证账号唯一性 
function checkunique() {
	var account = $("#mobile").val();
	var reg = /^((13[0-9])|(15[^4,\D])|(18[0,5-9]))\d{8}$/;
	if(reg.test(account)){
		$.ajax( {
			type : "post",
			dataType : "html",
			url : "usercenter!isAvailable.action?account=" + account,
			success : function(data) {
				if (data == "Y") {
					//$.messager.alert('提示', '<br>手机号已存在!', 'info');
					 alert("手机号已存在!");
					 flag = false;
				} 
			}
		});
	}else{
		//$.messager.alert('提示', '<br>请正确填写手机号！', 'info');
		 alert("请填写或正确填写手机号!");
		 flag =  false;
		 return false;
	}
	
}
//判断用户名是否唯一
function isNull() {
	var name = $("#name").val();
	 if(name!=null&&name!=""){
		$.ajax( {
			type : "post",
			dataType : "html",
			url : "usercenter!isNameNull.action?name=" + name,
			success : function(data) {
				if (data == "Y") {
					//$.messager.alert('提示', '<br>用户名已存在!', 'info');
					alert("用户名已存在!");
					 flag =  false;
				} 
			}
		});	
		}else{
			alert("用户名不能为空!");
			flag =  false;
		}
}
//验证码是否匹配
function yzuser(){
	var mes = $("#mes_authentication_code").val();
	var account = $("#mobile").val();
		$.ajax( {
			type : "post",
			dataType : "html",
			url : "usercenter!isTure.action?mes=" + mes+"&account="+account,
			success : function(data) {
				if (data == "N") {
					//$.messager.alert('提示', '<br>验证码不正确！', 'info');
					alert("验证码不正确!");
					flag =  false;
				} 
			}
		});	
}
//判断密码是否一致
function check(){
	var pas = $("#password").val();
	var pas2 = $("#password2").val();
	if(pas2 == ""|| pas2 == null){
		alert("确认密码不能为空!");
	}else if(pas != pas2){
		//$.messager.alert('提示', '<br>2次密码输入不一致！', 'info');
		alert("密码输入不一致!");
		flag =  false;
	}else{
		return flag;
	}
}
//密码验证
function pascheck(){
	var pas = $("#password").val();
	var vs =/^[\d_a-zA-Z]{4,12}$/;
	if(pas == ""|| pas == null){
		//$.messager.alert('提示', '<br>密码不能为空!', 'info');
		alert("密码不能为空!");
		}else if(vs.test(pas)){
			return flag;
		}else{
			alert("请正确输入4到14位字母或数字!");
			flag =  false;
		}
}
//获取短信验证
function getdx(){
	var mes = $("#mes_authentication_code").val();
	var account = $("#mobile").val();
	$.ajax( {
		async : true,
        cache : false,
		type : "post",
		dataType : "html",
		url : "usercenter!shortMessage.action?mes="+mes+"&account="+account,
		success : function(data) {
			if (data == "Y") {
				//$.messager.alert('提示', '<br>获取短信验证失败请检查手机号是否合法!', 'info');
				// 设置等待时间
              	curCount = 120;
                //设置button效果，开始计时
                $("#sms_verification").attr("disabled", true);
                $("#sms_verification").css("opacity", 0.5);
                $("#sms_verification").val(curCount + "秒后重新获取");
                //启动计时器，1秒执行一次
                InterValObj = window.setInterval(SetRemainTime, 1000);
			}else if(data == "N"){
			 $("#sms_verification").val("发送验证码");
				alert("获取短信验证失败请检查手机号是否合法!");
				
			} 
		}
	});
	 			
} 
function SetRemainTime() {
        // 显示倒计时时间
        curCount--;	
        // 若时间已到，则设置为初始状态
        if (curCount == 0) {
            window.clearInterval(InterValObj);
            $("#sms_verification").attr("disabled", false);
            $("#sms_verification").css("opacity", 1);
            $("#sms_verification").val("获取短信验证码");
        } else {
            $("#sms_verification").val(curCount + "秒后重新获取");
        }
    }
    //提交注册按钮
  function subform() {
  	var account = $("#mobile").val();
	var reg = /^((13[0-9])|(15[^4,\D])|(18[0,5-9]))\d{8}$/;
	if(account==null||account==""){
		alert("手机号不能为空！");
		flag = false;
	}else if(!reg.test(account)){
		 alert("请填写或正确填写手机号!");
		 flag =  false;
	}else if(reg.test(account)){
		var name = $("#name").val();//用户名
		$.ajax( {
			type : "post",
			dataType : "html",
			async: false,
			url : "usercenter!isAvailable.action?account=" + account,
			success : function(data) {
				if (data == "Y") {
					//$.messager.alert('提示', '<br>手机号已存在!', 'info');
					 alert("手机号已存在!");
					 flag = false;
				}else if(name==null||name==""){
					alert("用户名不能为空!");
					 flag = false;
				}else if(name!=null&&name!=""){			
					var mes = $("#mes_authentication_code").val();//验证码
					var account = $("#mobile").val();
					$.ajax( {
						type : "post",
						dataType : "html",
						async: false,
						url : "usercenter!isNameNull.action?name=" + name,
						success : function(data) {
							if (data == "Y") {
								//$.messager.alert('提示', '<br>用户名已存在!', 'info');
								alert("用户名已存在!");
								 flag =  false;
							}else if(mes==null||mes==""){
								alert("验证码不能为空!");
								flag =  false;
							}else if(mes!=null&&mes!=""){
									var pas = $("#password").val();//密码验证
									var pas2 = $("#password2").val();//确认密码
									var vs =/^[\d_a-zA-Z]{4,12}$/;
								$.ajax( {
								type : "post",
								dataType : "html",
								async: false,
								url : "usercenter!isTure.action?mes=" + mes+"&account="+account,
								success : function(data) {
								if (data == "N") {
									//$.messager.alert('提示', '<br>验证码不正确！', 'info');
									alert("验证码不正确!");
									flag =  false;
									}else if(pas==null||pas==""){
										alert("密码不能为空!");
										flag =  false;
									}else if(!vs.test(pas)){
										alert("请正确输入4到14位字母或数字!");
										flag =  false;
									}else if(pas2 == ""|| pas2 == null){
										alert("确认密码不能为空!");
										flag =  false;;
									}else if(pas != pas2){
										alert("密码输入不一致！");
										flag =  false;
									}else{
									 flag = true;
									} 
								}
							});
							 
							} 
						}
					});
					
				}
			}
		});
		
	}
	//var flag = $("#addempform").form('validate');
	if (flag) {
	  $("#tj").attr("disabled","disabled");
	  var account = $("#mobile").val();
	  $.ajax( {
		type : "post",
		dataType : "html",
		async: false,
		url : "usercenter!isAvailable.action?account=" + account,
		success : function(data) {
		   $("#tj").removeAttr("disabled");
			if (data != "Y") {	
			var password = $("#password").val();
			   $('#addempform').form('submit', {
					url : 'usercenter!save.action?password='+password,
					success : function(data) {
						 if (data == "Y") {
						 var name = $("#name").val();												
							window.location.href="usercenter!viewList.action?name="+name;
// 							$.messager.alert('提示', '<br>保存成功!', 'info',function(){
// 								toreset();
// 								back();
// 							});
						} else if(data == "N") {
							//$.messager.alert('提示', '<br>保存失败!', 'info');
							alert("保存失败!");
							return false;
						}
					}
				});
			}else{
				  //$.messager.alert('提示', '<br>手机号已存在!', 'info');
				  alert("手机号已存在!");
			      $("#userId").focus();
			      return false;
			} 
		}
	});
	}
}


function userring(){

}
/*  function getdx(){
	var mes = $("#mes_authentication_code").val();
	var account = $("#mobile").val();
	window.location.href="usercenter!shortMessage.action?mes="+mes+"&account="+account;
}   */
    </script>
    <style type="text/css">
    	
    </style>	
  </head>
  <body>
  <form id="addempform" name="addempform" method="post" >
  <input type="text" id="wxOpenCommId" name="rmUser.wxOpenCommId" value="${sessionScope.openId}">
 	<div class="main_registerPage">
		<h2>注册读伴儿账号</h2>		
		<div class="register_inputList">
			<label><span>手机号</span> <input id="mobile" name="rmUser.mobile" type="text" onblur="checkunique()" maxlength='11'  value=""/></label>
			<label><span>用户名</span> <input id="name"  name="rmUser.name" type="text" onblur="isNull()"  maxlength='10' value=""/></label>
			<label><span>年龄段</span> 
			<!-- <input id="age" name="rmUserDetail.age"  type="text" value=""/> -->	
			<select id="age" name="rmUserDetail.age">
					<option value="0">4-14岁</option>
					<option value="2">14岁以上</option>
			</select>
			</label>
			<label class="yzm_input"><span>验证码</span>	
			<input id="mes_authentication_code" value="" type="text" onblur="yzuser()"/> 
			<!-- <button id="sms_verification"  onclick = "getdx()">发送验证码</button>  -->
			
			 <input id="sms_verification"  class="fsyzm"
				style="font-size:0.75em;padding:0;position: absolute;text-align:center;  right: 2%; top:0.8em;border-radius:3px;width: 25%; height: 2.2em;line-height:2.2em;color:#FFFFFF;text-decoration:none;background:url(${pageContext.request.contextPath}/images/weixin/button_background.jpg); border:0; color:#FFF; " 
				onclick = "getdx()" type="button" value="发送验证码"></input> 
			</label>
			<label><span>密码</span> <input id="password" name="rmUser.password" type="password" onblur="pascheck()" maxlength='20' value=""/></label>
			<label><span>确认密码</span><input type="password" id="password2" onblur="check()"  maxlength='20' value=""/></label>
		</div>
		<button id="tj" type="button" onclick = "subform()">注 册</button>
	</div>
	 </form>
</body>

<script type="text/javascript">
	var input_list = document.getElementsByTagName("input");
	for (var i = 0; i < input_list.length; i++) {

		input_list[i].index = i;
		input_list[i].onfocus = function(){			
			input_list[this.index].value = "";
		}
	};
</script>
</html>
