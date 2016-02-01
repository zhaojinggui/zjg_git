<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@page import="com.tiangong.comm.MoodleUtil" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
  <head>
    <title>订单查询</title>
	<meta charset="utf-8" />
	<meta http-equiv="x-ua-compatible" content="IE=edge, chrome=1" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/weixin.css" >
	<script type="text/javascript" src="<%=basePath%>js/jquery.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>easyui/jquery.easyui.min.js"></script>
<script type="text/javascript">
// function getYzm(){
// 	// 手机号
// 	var phone = $("#phone").val();
// 	// 手机号正则
// 	var re = /^1\d{10}$/;
//     if (re.test(phone)) {
//     	$.post("orderRecord!getYzmCode.action",{"phone":phone},
//     		function(data){
//     			if(data.isSend == "N"){
//     				alert("请输入正确的手机号!");
//     			}else if(data.isSend == "Y"){
//     				alert("验证码已发送到您的手机,请注意查收!");
//     			}
//     		}
//     	);
//     } else {
//     	alert('请输入正确的手机号!');
//     }
// }
function queryList(){
	var phone = $("#phone").val();
	// 手机号正则
	var re = /^1\d{10}$/;
	if (!re.test(phone)) {
		alert('请输入正确的手机号!');
    	return false;
    }
	var tid = $("#tid").val();
	window.location.href="orderRecord!getOrderList.action?phone=" + phone+"&tid="+tid;
}
</script>
  </head>
  <body>
	<div class="main_fingerPage" style="text-align: center;">
		<h2>购买记录查询</h2>
		<div>
			订单号：<input id="tid" name="tid" type="text" value="${tid}"/>
			手机号：<input id="phone" name="phone" type="text" value="${phone}"/>
			<input id="query" type="button" value="查询" onclick="queryList()"/>
		</div>
		<div style="text-align: center;" >
			<table style="border:soilid 1px #ccc;width: 50%">
				<tr>
					<td width="30%">创建时间</td>
					<td width="30%">卡号</td>
					<td width="30%">密码</td>
					<td width="40%">激活有效期</td>
				</tr>
				<c:if test="${orderRecordList != null}">
					<c:forEach items="${orderRecordList}" var="order">
						<tr>
							<td>
								<fmt:formatDate value="${order.payTime}" pattern="yyyy-MM-dd"/>
							</td>
							<td>${order.cardId}</td>
							<td>${order.password}</td>
							<td>
								<fmt:formatDate value="${order.usableBefore}" pattern="yyyy-MM-dd"/>
							</td>
						</tr>
					</c:forEach>
				</c:if>
			</table>
		</div>
	</div>
  </body>
</html>