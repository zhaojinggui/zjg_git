<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
  <head>
    <title>教师个人信息</title>
	<meta charset="utf-8" />
	<meta http-equiv="x-ua-compatible" content="IE=edge, chrome=1" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/weixin.css" >
	<script type="text/javascript" src="<%=basePath%>js/jquery.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>easyui/jquery.easyui.min.js"></script>
 
 <script type="text/javascript">
	function query(classNum){
		window.location.href="teacher!toClassListPageJsp.action?classNum="+classNum;
	}
 </script>

  </head>
  <body >
 	<div class="main_fingerPage">
		<h2>教师个人信息</h2>		
		<div class="register_inputList" style="margin-bottom:0;">
			<label class="image"><span>头像</span><img title="我的头像" src="${rmUserDetail.picName}"/></label>
			<label><span>姓名</span><input value="${rmUserDetail.realname }" type="text" readonly="readonly"/></label>
			<label><span>学校名称</span><input value="${school }" type="text" readonly="readonly"/></label>
			<label><span>累计积分</span><input value="${point==-1?'暂时没有积分':point }" type="text" readonly="readonly" /></label>		
		</div>
		<h2 class="nomargin">所带班级</h2>		
		<div class="register_inputList class_list nomargin">
			<c:forEach items="${request.classList }" var="cla">
				<a href="teacher!toClassListPageJsp.action?classNum=${cla.id }">
					<label>
						<div class="left_main">
							<img class="school" src="<%=basePath%>images/weixin/school.jpg"/>
							<h3>班号：${cla.classNumber }</h3>
							<span>班级阶段：
								<c:if test="${cla.classStage=='1' }">
									小学
								</c:if>
								<c:if test="${cla.classStage=='2' }">
									初中
								</c:if>
								<c:if test="${cla.classStage=='3' }">
									高中
								</c:if>
							</span>
							<%-- <span>班号：001 </span> --%>
							<span>班级人数：${cla.classTotalMember }</span>
						</div>
						<div class="right_main" onclick="query('${cla.id }');">查看</div>
					</label>
				</a>
			</c:forEach>
		</div>		
	</div>
     	
  </body>
</html>
