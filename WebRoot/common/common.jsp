<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!-- <link rel="stylesheet" type="text/css" href="<%=basePath%>easyui/themes/gray/easyui.css" rel="stylesheet" title="gray"> -->
<link rel="stylesheet" type="text/css" href="<%=basePath%>easyui/themes/green/easyui.css" rel="stylesheet" title="green">
<!--<link rel="stylesheet" type="text/css" href="<%=basePath%>easyui/themes/pink/easyui.css" rel="stylesheet" title="pink">-->
<!--<link rel="stylesheet" type="text/css" href="<%=basePath%>easyui/themes/orange/easyui.css" rel="stylesheet" title="orange">-->
<%--<link rel="stylesheet" type="text/css" href="<%=basePath%>easyui/themes/default/easyui.css" rel="stylesheet" title="blue">--%>
<link rel="stylesheet" type="text/css" href="<%=basePath%>easyui/themes/icon.css">
<script type="text/javascript" src="<%=basePath%>js/jquery.min.js"></script>
<script type="text/javascript" src="<%=basePath%>easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=basePath%>easyui/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="<%=basePath%>js/util.js"></script>
<link rel="stylesheet" type="text/css" href="<%=basePath%>css/new.css" >
<link rel="stylesheet" type="text/css" href="<%=basePath%>css/table.css" >


<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<c:set var="root" value="${pageContext.request.contextPath}"/>