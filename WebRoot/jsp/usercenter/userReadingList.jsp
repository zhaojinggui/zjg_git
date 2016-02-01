<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@page import="com.tiangong.comm.MoodleUtil" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
  <head>
    <title>个人信息</title>
	<meta charset="utf-8" />
	<meta http-equiv="x-ua-compatible" content="IE=edge, chrome=1" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/weixin.css" >
	<script type="text/javascript" src="<%=basePath%>js/jquery.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript">
  function subform() {
	var flag = $("#addempform").form('validate');
	if (flag) {
	  $("#tj").attr("disabled","disabled");
	  var account = $("#mobile").val();
	  $.ajax( {
		type : "post",
		dataType : "html",
		url : "usercenter!isAvailable.action?account=" + account,
		success : function(data) {
		   $("#tj").removeAttr("disabled");
			if (data != "Y") {
			   $('#addempform').form('submit', {
					url : 'usercenter!save.action',
					success : function(data) {
						 if (data == "Y") {
							$.messager.alert('提示', '<br>保存成功!', 'info',function(){
								toreset();
								back();
							});
						} else {
							$.messager.alert('提示', '<br>保存失败!', 'info');
						}
					}
				});
			}else{
				  $.messager.alert('提示', '<br>手机号已存在!', 'info');
			      $("#userId").focus();
			} 
		}
	});
	}
}

//
function upuserview() {
	var name = $("#name").val();
	window.location.href="usercenter!editviewUser.action?name="+encodeURI(encodeURI(name));

		
}
    </script>
  </head>
  <body>
	<div class="main_fingerPage">
		<h2>个人信息</h2>	
		<input type="hidden" id="nameid" name="rmUser.id" value="${rmView.id}" >	
		<div class="register_inputList">
			<label class="image"><span>头像</span><img title="我的头像" src="${rmView.picName}"/></label>
			<label><span>用户名</span>
			<input id="name" value="${rmView.userName}" type="text" readonly=false/>	
			</label>
			<label><span>用户角色</span>
					<%-- <input value="${list[0][2]}" type="text" /> --%>
					<%-- <select>
					<option value="0" <c:if test="${list[0][2]}==0">selected</c:if>>后台管理用户</option>
					<option value="1" <c:if test="${list[0][2]}==1">selected</c:if>>个人用户</option>
					<option value="2" <c:if test="${list[0][2]}==2">selected</c:if>>集体账户</option>
					<option value="3" <c:if test="${list[0][2]}==3">selected</c:if>>班级管理员</option>
					<option value="4" <c:if test="${list[0][2]}==4">selected</c:if>>普通学生</option>
				</select> --%>
				
				<c:if test="${rmView.userRole==0}">后台管理用户</c:if>
				<c:if test="${rmView.userRole==1}">个人用户</c:if>
				<c:if test="${rmView.userRole==2}">集体账户</c:if>
				<c:if test="${rmView.userRole==3}">班级管理员</c:if>
				<c:if test="${rmView.userRole==4}">普通学生</c:if>
			</label>
			<label>
			<%-- <span>年龄段</span><input value="${list[0][4]}" type="text" /> --%>
				<span>年龄段</span>
				<%-- <select>
					<option value="0" <c:if test="${list[0][4]}==0">selected</c:if>>4-14岁</option>
					<option value="2" <c:if test="${list[0][4]}==2">selected</c:if>>14岁以上</option>
				</select> --%>
				
				<c:if test="${rmView.userAge==0}">4-14岁</c:if>
				<c:if test="${rmView.userAge==2}">14岁以上</c:if>
			</label>
			<label>
		 <span>学习英语年限</span><%-- <input value="${rmView.userYears}" type="text" readonly /> --%>
		 <c:if test="${rmView.userYears=='-1'}"></c:if>
		 <c:if test="${rmView.userYears==0}">1-2</c:if>
		 <c:if test="${rmView.userYears==1}">2-3</c:if>
		 <c:if test="${rmView.userYears==2}">3-4</c:if>
		 <c:if test="${rmView.userYears==3}">4-5</c:if>
		 <c:if test="${rmView.userYears==4}">5-6</c:if>
		 <c:if test="${rmView.userYears==5}">6-7</c:if>
		 <c:if test="${rmView.userYears==6}">7-8</c:if>
		 <c:if test="${rmView.userYears==7}">8-9</c:if>
		 <c:if test="${rmView.userYears==8}">9-10</c:if>
		 <c:if test="${rmView.userYears==9}">10+</c:if>
				<%-- <span>英语学习年限</span>
				<select>
					<option value="${list[0][5]}">${list[0][5]}</option>
				</select> --%>
			</label>	
			<label><span>坚果数</span><input value="${rmView.userNum}颗" type="text" readonly/></label>
			<label><span>累计登录天数</span><input value="${rmView.userDay}天" type="text" readonly/></label>
			<label><span>当前阅读级别</span><input value="Level ${rmView.userLevel}" type="text" readonly/></label>
		</div>
		<button  onclick = "upuserview()" type=button">修 改</button>
	</div>
  </body>
</html>