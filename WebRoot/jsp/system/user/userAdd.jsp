<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<jsp:include page="../../../common/common.jsp"/>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'add.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript">
		
//员工信息添加
function subform() {
	var flag = $("#addempform").form('validate');
	if (flag) {
	  $("#tj").attr("disabled","disabled");
	  var account = $("#userId").val();
	  $.ajax( {
		type : "post",
		dataType : "html",
		url : "user!isAvailable.action?account=" + account,
		success : function(data) {
		   $("#tj").removeAttr("disabled");
			if (data != "Y") {
			   $('#addempform').form('submit', {
					url : 'user!addUser.action',
					success : function(data) {
						 if (data == "Y") {
							$.messager.alert('提示', '<br>保存成功!', 'info',function(){
								toreset();
								//back();
							});
						} else {
							$.messager.alert('提示', '<br>保存失败!', 'info');
						}
					}
				});
			}else{
				  $.messager.alert('提示', '<br>该账号已存在!', 'info');
			      $("#userId").focus();
			} 
		}
	});
	}
}

//验证账号唯一性 
function checkunique() {
	var account = $("#userId").val();
	$.ajax( {
		type : "post",
		dataType : "html",
		url : "user!isAvailable.action?account=" + account,
		success : function(data) {
			if (data == "Y") {
				$.messager.alert('提示', '<br>该账号已存在!', 'info');
			} 
		}
	});
}

//相关验证	

/* 密码由字母和数字组成，至少6位 */
var safePassword = function(value) {
	return !(/^(([A-Z]*|[a-z]*|\d*|[-_\~!@#\$%\^&\*\.\(\)\[\]\{\}<>\?\\\/\'\"]*)|.{0,5})$|\s/.test(value));
}
  $.extend($.fn.validatebox.defaults.rules, {   
    mobile:{     
        validator:function(value,param){     
            if (value){  
            var reg = /^(((13[0-9]{1})|(15[0-9]{1}))+\d{8})$/;
                return  reg.test(value);         
          	} else {     
                return true;     
            }     
        },     
        message:'请输入正确的手机号码'     
    },
    phone:{     
        validator:function(value,param){     
            if (value){ 
                var reg = /(^(\d{3,4}-)?\d{6,8}$)|(^(\d{3,4}-)?\d{6,8}(-\d{1,5})?$)|(\d{11})/;    
                return reg.test(value);     
            } else {     
                return true;     
            }     
        },     
        message:'请输入正确的电话号码'     
    },
     email: {  
        validator: function (value, param) { 
            var reg = /^(?:[a-z\d]+[_\-\+\.]?)*[a-z\d]+@(?:([a-z\d]+\-?)*[a-z\d]+\.)+([a-z]{2,})+$/i; 
            return reg.test(value); 
        },  
        message: '请输入正确的电子邮件'  
    }, 
    safepass: {
        validator: function (value, param) {
            return safePassword(value);
        },
        message: '密码由字母和数字组成，至少6位'
    },
    pwd: {  
        validator: function (value, param) { 
            var passward=$("#empPwd").val();
            return passward==value; 
        },  
        message: '密码输入不一致'  
    },
    loginName: {
        validator: function (value, param) {
            return /^[\u0391-\uFFE5\w]+$/.test(value) && value.length<18;
        },
        message: '登录名称只允许汉字、英文字母、数字及下划线,不能大于18位'
    },
    chinese: {
        validator: function (value, param) {
            return /^[\u0391-\uFFE5]+$/.test(value) && value.length<18;
        },
        message: '请输入汉字,不能大于18位'
    },
      point: {
        validator: function (value, param) {
            return /^[0-9]+\.[0-9]{2}$/.test(value) && value.length<18;
        },
        message: '请输入两位小数的数字且不能大于18位'
     }            
    }); 
  
	function toreset()
	{
		document.addempform.reset();
	}
	
	function back()
	{
		window.location.href="<%=basePath%>system/user/userList.jsp";
	}
	 </script>
  </head>
  
 <body>
<div id="p" class="easyui-panel" title="账户管理>>添加账户" border="false" align="center">
<form id="addempform" name="addempform" method="post" >
				<table class="table"  style="margin-top: 20px">
					<tr>
						<td class="f12" height="30" >账号：</td>
						<td class="f12"><input id="userId" name="user.userId"  style="width: 138px" class="easyui-validatebox" required="true" onblur="checkunique()">&nbsp;<font color="red">*</font></td>
			  			<td class="f12" height="30">姓名：</td>
			  			<td class="f12"><input id="name" name="user.name"  style="width: 138px" class="easyui-validatebox" required="true">&nbsp;<font color="red">*</font></td>
					</tr>
					<tr>
			  			<td class="f12" height="30">所在单位：</td>
			  			<td class="f12"><input id="company" name="user.company" style="width: 138px" >&nbsp;</td>
			  			<td class="f12" height="30">邮箱：</td>
			  			<td class="f12"><input id="email" name="user.email" style="width: 138px" class="easyui-validatebox" validType="email" required="true">&nbsp;<font color="red">*</font></td>
			  		</tr>
					<tr>
			  			<td class="f12" height="30">手机：</td>
			  			<td class="f12"><input id="mobile" name="user.mobile" class="easyui-validatebox" validType="mobile" style="width: 136px" ></td>
			  			<td class="f12" height="30">电话：</td>
			  			<td class="f12"><input id="phone" name="user.phone" class="easyui-validatebox" validType="phone" style="width: 136px" /></td>
			  		</tr>
<!-- 			  		<tr> -->
<!-- 			  			<td class="f12" height="30">微博/博客：</td> -->
<!-- 			  			<td class="f12"><input id="blog" name="user.blog"  style="width: 138px" value="http://"/></td> -->
<!-- 			  			<td class="f12" height="30">职称：</td> -->
<!-- 			  			<td class="f12"><input id="jobTitle" name="user.jobTitle"  style="width: 138px"/> </td> -->
<!-- 			  		</tr> -->
			  		
<!-- 			  		<tr> -->
<!-- 			  			<td class="f12" height="30">头衔：</td> -->
<!-- 			  			<td class="f12"><input id="title" name="user.title" style="width: 138px" ></td> -->
<!-- 			  			<td class="f12" height="30">研究方向：</td> -->
<!-- 			  			<td class="f12"><input id="research" name="user.research" style="width: 138px" ></td> -->
<!-- 			  		</tr> -->
<!-- 			  		<tr> -->
<!-- 			  			<td class="f12" height="30">院系：</td> -->
<!-- 			  			<td class="f12"><input id="departments" name="user.departments" style="width: 138px" ></td> -->
<!-- 			  			<td class="f12" height="30">实验室：</td> -->
<!-- 			  			<td class="f12"><input id="laboratory" name="user.laboratory" style="width: 138px"></td> -->
<!-- 			  		</tr> -->
			  		
<!-- 			  		<tr> -->
<!-- 			  			<td class="f12" height="30">地址：</td> -->
<!-- 			  			<td class="f12" colspan="3"><input id="address" name="user.address" style="width: 410px" ></td> -->
<!-- 			  		</tr> -->
<!-- 			  		<tr> -->
<!-- 			  			<td class="f12" height="30">个人主页：</td> -->
<!-- 			  			<td class="f12" colspan="3"><input id="homePage" name="user.homePage" style="width: 410px" value="http://"/></td> -->
<!-- 			  		</tr> -->
<!-- 			  		<tr> -->
<!-- 			  			<td class="f12" height="30">实验室主页：</td> -->
<!-- 			  			<td class="f12" colspan="3"><input id="labHome" name="user.labHome" style="width: 410px" value="http://"/></td> -->
<!-- 			  		</tr> -->
			  		<tr>
			  			<td class="f12" height="30">备注：</td>
			  			<td class="f12" colspan="3">
			  			<textarea rows="4" cols="49" id="remark" name="user.remark"></textarea>
			  			</td>
			  		</tr>
			  		<tr id="flow">	
		  				<td height="60px" colspan="4" align="center">
		  					<input id="tj" type="button" onclick="subform()" value="提 交">&nbsp;&nbsp;
		  					<input type="button" onclick="toreset()" value="重 置">&nbsp;&nbsp;
		  					<input type="button" onclick="back()" value="返 回">
		  				</td>
			  		</tr>
				</table>
			</form>
</div>
</body>
</html>
