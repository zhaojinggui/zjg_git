<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<jsp:include page="../../../common/common.jsp"/>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>修改密码</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" type="text/css" href="css/new.css" >
	 <script>
 
function sub()
{
 	var flag = $("#myform").form('validate');
	if(flag==true)
	{
	   var operid=$("#userid").val();
	   var pwd=$("#confuserPwd").val();
	   $.ajax({
	      type:"post",
	      dataType:"html",
	      url:"user!changepwd.action?id="+operid+"&pwd="+pwd,
	      success:function(data){
	         
	         if(data=="Y")
	         {
	            $.messager.alert('提示','<br>修改成功','info');
	            $("#mypwd").val("");
	            $("#password").val("");
	            $("#confuserPwd").val("");
	         }
	         else{
	            $.messager.alert('提示','<br>修改失败','error');
	         }
	      }   
	   });
	}
} 
 
//相关验证	

/* 密码由字母和数字组成，至少6位 */
var safePassword = function(value) {
	return !(/^(([A-Z]*|[a-z]*|\d*|[-_\~!@#\$%\^&\*\.\(\)\[\]\{\}<>\?\\\/\'\"]*)|.{0,5})$|\s/.test(value));
}
  $.extend($.fn.validatebox.defaults.rules, {   
    safepass: {
        validator: function (value, param) {
            return safePassword(value);
        },
        message: '密码由字母和数字组成，至少6位'
    },
    localpwd:
    {
     validator: function (value, param) { 
            var str='';
            var pwd=$("#mypwd").val();
            var userid='${user.userId}';
                           $.ajax({
               type:"post",
               url:"user!pwdCompare.action?password="+pwd+"&userid="+userid,
               async:false,
               success:function(data){
                 if(data=="Y")
                 {
                   str=true;
                 }else{
                    str=false;
                 }
               }
            }); 
            return str;
        },  
        
        message: '原密码输入不正确'  
    },
    pwd: {  
        validator: function (value, param) { 
            var passward=$("#password").val();
            return passward==value; 
        },  
        message: '密码输入不一致'  
    }
    }); 
	</script>

  </head>
  
  <body>
 
   <center>
   <div style="width: 300px; margin-top: 150px;">
   <fieldset>
   <legend>
   　修改密码
   </legend>
   <form  method="post" name="myform" id="myform" >
   <table align="center" >
   <tr>
     <td class="f12">用户名称：</td>
     <td><input type="hidden" id="userid" name="userid" value="${user.id}">
						<input type="text" id="name" name="name" value="${user.name}" size="18" disabled="disabled"></td>
   </tr>
   <tr>
     <td class="f12">原&nbsp;&nbsp;密&nbsp;&nbsp;码：</td>
     <td><input type="password" id="mypwd" name="mypwd" class="easyui-validatebox" required="true" validType="localpwd" ></td>
   </tr>
   <tr>
     <td class="f12">新&nbsp;&nbsp;密&nbsp;&nbsp;码：</td>
     <td><input type="password" id="password" id="password" name="password" class="easyui-validatebox" required="true" validType="safepass"></td>
   </tr>
   <tr>
     <td class="f12">确认密码：</td>
     <td><input type="password" id="confuserPwd" name="confuserPwd"  class="easyui-validatebox" required="true" validType="pwd" ></td>
   </tr>
    <tr>
     <td>
     </td>
     <td>
     &nbsp;&nbsp;
      <input type="button" id="ok" name="ok" value="确 定" onclick="sub();">
      &nbsp;&nbsp;&nbsp;
     <input type="reset" id="reset" name="reset" value="重 置" >
     </td>
   </tr>
   </table>
 </form>
 </fieldset>
 </div>
 </center>
  </body>
  
</html>
