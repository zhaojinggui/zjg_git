<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
  <head>
    <title>班级列表信息</title>
	<meta charset="utf-8" />
	<meta http-equiv="x-ua-compatible" content="IE=edge, chrome=1" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/weixin.css" >
	<script type="text/javascript" src="<%=basePath%>js/jquery.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>easyui/jquery.easyui.min.js"></script>
 
 <script type="text/javascript">
	if('${studentList.size() }'=='0'){
		alert("该班级暂时没有学生！");
	}
 </script>

  </head>
 <body class="auto_scroll">
	<div class="main_fingerPage">
		<h2 class="en_class">${classNum }班</h2>		
		<div class="register_inputList class_list nomargin">
			<c:forEach items="${studentList }" var="stu">
			<label>
				<div class="left_main" style="width:100%">
					<img class="image" src="<%=basePath%>images/weixin/pic1.jpg"/>
					<h3 class="top_mar">${stu.stuUserName }<strong>Level&nbsp;&nbsp;&nbsp;&nbsp;${stu.level }</strong></h3>
					<span>ID号：${stu.studentId }</span>
					<span>学号：${stu.studentNumber }</span>
				</div>				
			</label>
			</c:forEach>
		</div>		
	</div>
</body>
</html>
