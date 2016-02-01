<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@page import="java.util.*" %>   
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>微信后台管理系统</title>
<link type="text/css" href="<%=basePath %>css/login.css" rel="stylesheet" />
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="">
	if(top.location != location){
		top.location.href= location.href;
	}
</script>
	<script type="text/javascript">
	 
	  function login()
	  {
	      var name=$("#name").val();
	      var pwd=$("#password").val();
	      var str="";
		  if(name=="")
	      {
	         alert("用户名不能为空!");
	      }else if(pwd=="")
	      {
	         alert("密码不能为空!");
	      }
	      else 
	      {
	          $.ajax({
				   type:"post",
				   dataType:"html",
				   url:"user!login?userid="+name+"&password="+pwd,
				   success:function(data){
				     if(data=="N")
				     {
				       alert("用户名或密码错误!");
				          
				     }else{				    	 
				        window.location.href="index.jsp";
				     }
				   }
				});
	      }
	  }
	  
	</script>
</head>

<body>
<table  width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td bgcolor="#e5f6cf">&nbsp;
    	<input type="hidden" name="userType" value="3"/>
    	<input type="hidden" name="vcode" id="vcode" value=""/>
    </td>
  </tr>
  <tr>
    <td height="608" background="images/login/login_03.gif"><table width="862" border="0" align="center" cellpadding="0" cellspacing="0">
      <tr>
        <td height="266" background="images/login/login_04.gif">&nbsp;</td>
      </tr>
      <tr>
        <td height="95"><table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td width="424" height="95" background="images/login/login_06.gif">&nbsp;</td>
            <td width="183" background="images/login/login_07.gif">
            <form action="">
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td width="21%" height="30">
                	<div align="center">
                		<span class="STYLE3">用户</span>
                	</div>
                </td>
                <td width="79%" height="30">
                	<input type="text" name="name" id="name" value="" style="height:18px; width:130px; border:solid 1px #cadcb2; font-size:12px; color:#81b432;">
                </td>
              </tr>
              <tr>
                <td height="30"><div align="center"><span class="STYLE3">密码</span></div></td>
                <td height="30"><input type="password" name="password"  id="password" value="" style="height:18px; width:130px; border:solid 1px #cadcb2; font-size:12px; color:#81b432;"></td>
              </tr>
              <tr>
                <td height="30">&nbsp;</td>
                <td height="30">
                	<input type="button" id="signin" value="登录" onclick="login();"/>
					<span class="STYLE3"><input type="reset" value="重置"/></span>
                </td>
              </tr>
            </table>
            </form>
            </td>
            <td width="255" background="images/login/login_08.gif">&nbsp;</td>
          </tr>
        </table></td>
      </tr>
      <tr>
        <td height="247" valign="top" background="images/login/login_09.gif"><table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td width="22%" height="30">&nbsp;</td>
            <td width="56%">&nbsp;</td>
            <td width="22%">&nbsp;</td>
          </tr>
          <tr>
            <td>&nbsp;</td>
            <td height="30"><table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td width="44%" height="20">&nbsp;</td>
                <td width="56%" class="STYLE4">版本  V1.0 </td>
              </tr>
            </table></td>
            <td>&nbsp;</td>
          </tr>
        </table></td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td bgcolor="#a2d962">&nbsp;</td>
  </tr>
</table>

</body>
</html>