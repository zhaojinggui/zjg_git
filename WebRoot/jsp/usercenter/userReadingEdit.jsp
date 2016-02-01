<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
  <head>
    <title>个人信息修改</title>
	<meta charset="utf-8" />
	<meta http-equiv="x-ua-compatible" content="IE=edge, chrome=1" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/weixin.css" >
	<script type="text/javascript" src="<%=basePath%>js/jquery.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript">
  /* function upuser() {
	var flag = $("#addempform").form('validate');
	if (flag) {
	  $("#tj").attr("disabled","disabled");
	  var name = $("#name").val();
	  $.ajax( {
		type : "post",
		dataType : "html",
		url : "usercenter!isNameNull.action?name=" + name,
		success : function(data) {
		   $("#tj").removeAttr("disabled");
			if (data != "Y") {
			   $('#addempform').form('submit', {
					url : 'usercenter!editUser.action',
					success : function(data) {
						 if (data == "Y") {						
							//window.location.href="usercenter!viewList.action";
// 							$.messager.alert('提示', '<br>保存成功!', 'info',function(){
// 								toreset();
// 								back();
// 							});
						} else {
							$.messager.alert('提示', '<br>保存失败!', 'info');
						}
					}
				});
			}else{
				  $.messager.alert('提示', '<br>用户名已存在!', 'info');
			      $("#userId").focus();
			} 
		}
	});
	}
} */

//修改
 function upuser(){
	var name = $("#name").val();
	var age = $("#age").val();
	var studyYears = $("#studyYears").val();
	var nameid = $("#nameid").val();
		$.ajax( {
			type : "post",
			dataType : "html",
			url : "usercenter!editUser.action?name=" + encodeURI(encodeURI(name))+"&age="+age+"&studyYears="+studyYears+"&nameid="+nameid,
			success : function(data) {
				if (data == "Y") {
					var name = $("#name").val();						
					window.location.href="usercenter!viewList.action?name="+encodeURI(encodeURI(name));
				}else{
					alert("修改失败!");
				} 
			}
		});	
}
    </script>
  </head>
  <body>
  <form id="addempform" name="addempform" method="post" >
  	<div class="main_fingerPage">
		<h2>个人信息修改</h2>	
		 <input type="hidden" id="nameid" name="rmUser.id" value="${rmView.id}" >	
		<%--  <input type="hidden" id="nameid" name="rmUser.name" value="${list[0][0]}" > --%>	
		<div class="register_inputList">
			<label class="image"><span>头像</span><img title="我的头像" src="${rmView.picName}"/></label>
			<label><span>用户名</span><input id="name" name="rmUser.name"  value="${rmView.userName}" type="text" readonly=false/></label>
			<label><span>年龄段</span>
			<%-- <input id="age"  name="rmUserDetail.age" value="${list[0][4]}" type="text" />	  --%>
			 <select id="age"  name="rmUserDetail.age">
					<option value="0" <c:if test="${rmView.userAge==0}">selected='selected'</c:if>>4-14岁</option>
					<option value="2" <c:if test="${rmView.userAge==2}">selected='selected'</c:if>>14岁以上</option>
				</select>
			</label>
			<label>
			<%-- <input id="studyYears"  name="rmUserDetail.studyYears" value="${list[0][5]}" type="text" />	 --%>
			<span>学习英语年限</span>
			<select  id="studyYears" name="rmUserDetail.studyYears" >
					<option value="0" <c:if test="${rmView.userYears=='0'}">selected</c:if>>1-2</option>
					<option value="1" <c:if test="${rmView.userYears=='1'}">selected</c:if>>2-3</option>
					<option value="2" <c:if test="${rmView.userYears=='2'}">selected</c:if>>3-4</option>
					<option value="3" <c:if test="${rmView.userYears=='3'}">selected</c:if>>4-5</option>
					<option value="4" <c:if test="${rmView.userYears=='4'}">selected</c:if>>5-6</option>
					<option value="5" <c:if test="${rmView.userYears=='5'}">selected</c:if>>6-7</option>					
					<option value="6" <c:if test="${rmView.userYears=='6'}">selected</c:if>>7-8</option>
					<option value="7" <c:if test="${rmView.userYears=='7'}">selected</c:if>>8-9</option>
					<option value="8" <c:if test="${rmView.userYears=='8'}">selected</c:if>>9-10</option>
					<option value="9" <c:if test="${rmView.userYears=='9'}">selected</c:if>>10+</option>
				</select>
			</label>		
		</div>
		<button id="tj" onclick = "upuser()" type="button">保存</button>
	</div>
	 </form>
</body>
  </body>
</html>
