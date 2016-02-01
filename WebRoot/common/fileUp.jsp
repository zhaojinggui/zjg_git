<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<jsp:include page="../common/common.jsp"/>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript">
	function upload()
	{
	   $('#form1').form('submit', {
			url : 'common!fileUpload.action',
			success : function(data) {
				 if (data == "N") {
					$.messager.alert('提示', '<br>保存失败!', 'info');
				} else {
					$.messager.alert('提示', '<br>保存成功!', 'info',function(){
						window.returnValue=data; 
					    window.close(); 
					});
				}
			}
		});
	}
	</script>
  </head>
  <body>
  <fieldset style="height: 150px;margin:10px;">
  <legend>文件上传</legend>
    <form name="form1" method="post"  id="form1" enctype="multipart/form-data">
	    <input type="file" name="image" id="image" title="选择图片" />
	    <input type="button" value="上 传" id="btnUpload" onclick="upload()"/>
    </form>
    <div style="color: red;float:right;margin-top: 30px;">注:只能上传图片文件(*.jpg,*.gif,*.bmp,*.jpeg)</div>
  </fieldset>
  </body>
</html>