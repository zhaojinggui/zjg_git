<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'MyJsp.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}
.STYLE1 {
	color: #43860c;
	font-size: 12px;
}
.STYLE2 {
	color: #43860c;
	font-size: 12px;
	float:right;
	margin-right:12px;
	margin-bottom:3px;
}
.STYLE3 {
	color: #43860c;
	text-decoration: none;
}
-->
</style>

<body style="overflow: hidden;">
<table width="100%" border="0" cellspacing="0" cellpadding="0" style="table-layout:fixed;">
  <tr>
    <td height="9" style="line-height:9px; background-image:url(images/main/main_04.gif)"><table width="100%" border="0" cellspacing="0" cellpadding="0">
     
    </table></td>
  </tr>
  <tr>
    <td height="47" background="images/main/main_09.gif"><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="38" height="47" background="images/main/main_06.gif"></td>
        <td width="59"><table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td height="29" background="images/main/main_07.gif">&nbsp;</td>
          </tr>
          <tr>
            <td height="18" background="images/main/main_14.gif"><table width="100%" border="0" cellspacing="0" cellpadding="0" style="table-layout:fixed;">
              <tr>
                <td  style="width:1px;">&nbsp;</td>
                <td ><span class="STYLE1">${user.name}</span></td>
              </tr>
            </table></td>
          </tr>
        </table>
        </td>
        <td width="237" background="images/main/main_08.gif">&nbsp;</td>
        <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td height="23" valign="bottom">&nbsp;</td>
          </tr>
        </table></td>
        <td width="200" ><table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td width="11%" height="23">&nbsp;</td>
            <td width="89%" valign="bottom"><span id="clock" class="STYLE1"></span></td>
          </tr>
          <tr>
            <td width="11%" height="23">&nbsp;</td>
            <td width="89%" valign="bottom"><span class="STYLE2"><a class="STYLE3" href="javascript:logout();">退出系统</a></span></td>
          </tr>
        </table></td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td height="5" style="line-height:5px; background-image:url(images/main/main_18.gif)"><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        
      </tr>
    </table></td>
  </tr>
</table>

</html>
