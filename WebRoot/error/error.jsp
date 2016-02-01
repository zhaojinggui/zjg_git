<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>出错了....</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
    <table width="100%" align="center" height="100%" border="0" cellspacing="0" cellpadding="0" class="mConRightBg">
  <tr>
    <td valign="top">
      <div class="cRCon2">
      	<div  style="margin-top:100px;" align="center"><img src="images/error.jpg" /></div>
      </div>
      </td>
  </tr>
  <tr>
    <td valign="bottom" class="mRightFootBg">
        <table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td class="mRightFootLeft"></td>
            <td align="right" class="mRightFootBg">&nbsp;</td>
            <td class="mRightFootRight"></td>
          </tr>
        </table>
   	</td>
  </tr>
</table>
  </body>
</html>
