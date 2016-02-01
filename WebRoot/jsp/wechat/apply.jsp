<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
  <head>
    <base href="<%=basePath%>">
   	<meta charset="utf-8" />
	<meta http-equiv="x-ua-compatible" content="IE=edge, chrome=1" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>读伴儿体验卡免费领取报名</title>
	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/weixin.css" >
	<script type="text/javascript" src="<%=basePath%>js/jquery.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>jsp/wechat/init.js"></script>
	<script src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
	<script type="text/javascript">

 wx.config({
      debug: false,
      appId: 'gh_5b0e95f5569a',
      timestamp: "${jsMap['timestamp']}",
      nonceStr: "${jsMap['nonceStr']}",
      signature: "${jsMap['signature']}",
      jsApiList: [
        'checkJsApi',
        'onMenuShareTimeline',
        'onMenuShareAppMessage',
        'onMenuShareQQ',
        'onMenuShareWeibo',
        'onMenuShareQZone',
        'hideMenuItems',
        'showMenuItems',
        'hideAllNonBaseMenuItem',
        'showAllNonBaseMenuItem',
        'translateVoice',
        'startRecord',
        'stopRecord',
        'onVoiceRecordEnd',
        'playVoice',
        'onVoicePlayEnd',
        'pauseVoice',
        'stopVoice',
        'uploadVoice',
        'downloadVoice',
        'chooseImage',
        'previewImage',
        'uploadImage',
        'downloadImage',
        'getNetworkType',
        'openLocation',
        'getLocation',
        'hideOptionMenu',
        'showOptionMenu',
        'closeWindow',
        'scanQRCode',
        'chooseWXPay',
        'openProductSpecificView',
        'addCard',
        'chooseCard',
        'openCard'
      ]
  });
  
$(function(){
		var deadline='${deadline}';	
		var isWxOpenId='${isWxOpenId}';
		if(deadline=='1')
		{
 			//已结束
			var applyPage_next = document.getElementById("applyPage_next2");
			applyPage_next.style.display = "block";
		}
		if(isWxOpenId=='1')
		{
			document.getElementById("errproNo").innerHTML = MessageMap.E000005;
			
			$("#applay_submit").attr("disabled","disabled");
			$("input").attr("disabled","disabled");	
			
		}else if(isWxOpenId=='2')
		{
			//已报名未领卡
			var applyPage_next = document.getElementById("applyPage_next4");
			applyPage_next.style.display = "block";
			
		}else{
			  $("#card_applay").removeAttr("disabled");
			  $("input").removeAttr("disabled");	
		}
		
		$("#name").blur(function(){
			if(isEmpty($(this).val()))
			{
				document.getElementById("errproNo").innerHTML = MessageMap.E000001;
// 				$(this).focus();
			}else if(!checkSchool($(this).val()))
			{
				document.getElementById("errproNo").innerHTML = MessageMap.E000006;
// 				$(this).focus();
			}else if(!checkLength_name_min($(this).val()))
			{
				document.getElementById("errproNo").innerHTML = MessageMap.E000007;
// 				$(this).focus();
			}else if(!checkLength_name_max($(this).val()))
			{
				document.getElementById("errproNo").innerHTML = MessageMap.E000008;
// 				$(this).focus();
			}else{
				document.getElementById("errproNo").innerHTML = "";
			}
		});
		
		$("#mobile").blur(function(){
			if(isEmpty($(this).val()))
			{
				document.getElementById("errproNo").innerHTML = MessageMap.E000002;
// 				$(this).focus();
			}else if(!checkMobileNo($(this).val()))
			{
				document.getElementById("errproNo").innerHTML = MessageMap.E000003;
// 				$(this).focus();
			}else if(!checkMobileUnique($(this).val()))
			{
				document.getElementById("errproNo").innerHTML = MessageMap.E000013;
// 				$(this).focus();
			}else{
				document.getElementById("errproNo").innerHTML = "";
			}
		});
		
		$("#school").blur(function(){
			if(isEmpty($(this).val()))
			{
				document.getElementById("errproNo").innerHTML = MessageMap.E000010;
// 				$(this).focus();
			}else if(!checkSchool($(this).val()))
			{
				document.getElementById("errproNo").innerHTML = MessageMap.E000014;
// 				$(this).focus();
			}else if(!checkLength_school_min($(this).val()))
			{
				document.getElementById("errproNo").innerHTML = MessageMap.E000015;
// 				$(this).focus();
			}else if(!checkLength_school_max($(this).val()))
			{
				document.getElementById("errproNo").innerHTML = MessageMap.E000020;
// 				$(this).focus();
			}else{
				document.getElementById("errproNo").innerHTML = "";
			}
		});
		
		$("#teacherNo").blur(function(){
			if(isEmpty($(this).val()))
			{
				document.getElementById("errproNo").innerHTML = MessageMap.E000011;
// 				$(this).focus();
			}else if(!checkNu($(this).val()))
			{
				document.getElementById("errproNo").innerHTML = MessageMap.E000004;
// 				$(this).focus();
			}else if(!checkTeacherUnique($(this).val()))
			{
				document.getElementById("errproNo").innerHTML = MessageMap.E000012;
// 				$(this).focus();
	
			}else{
				document.getElementById("errproNo").innerHTML = "";
			}
		});
		
		$("#address").blur(function(){
			if(isEmpty($(this).val()))
			{
				document.getElementById("errproNo").innerHTML = MessageMap.E000016;
// 				$(this).focus();
			}else if(!checkSchool($(this).val()))
			{
				document.getElementById("errproNo").innerHTML = MessageMap.E000017;
// 				$(this).focus();
			}else if(!checkLength_address_min($(this).val()))
			{
				document.getElementById("errproNo").innerHTML = MessageMap.E000019;
// 				$(this).focus();
			}else if(!checkLength_address_max($(this).val()))
			{
				document.getElementById("errproNo").innerHTML = MessageMap.E000018;
// 				$(this).focus();
			}else{
				document.getElementById("errproNo").innerHTML = "";
			}
		});
		
});

function checkMobileUnique(mobile)
{
	var flag=true;
	$.ajax({
			url:'wechat!valdateMobile.action?mobile='+mobile,
			dataType:'html',
			async:false,
			type:'post',
			success:function(data){
				if($.trim(data)=='Y'){
					flag=false;
				}
		}
	});
return flag;
}

function checkTeacherUnique(teacher)
{
	var flag=true;
	$.ajax({
			url:'wechat!valdateTeacherNo.action?teacher='+teacher,
			dataType:'html',
			async:false,
			type:'post',
			success:function(data){
				if($.trim(data)=='Y'){
					flag=false;
				}
		}
	});
	
	return flag;
}
	
	function sub()
	{
		var name = document.getElementById("name").value;
		var mobile = document.getElementById("mobile").value;
		var school = document.getElementById("school").value;
		var teacherNo = document.getElementById("teacherNo").value;
		var isWxOpenId='${isWxOpenId}';
		var address = document.getElementById("address").value;
		var chk = true;
		if(isWxOpenId=='1')
		{
			document.getElementById("errproNo").innerHTML = MessageMap.E000005;
			chk = false;
		}else if (isEmpty(name)) {
			document.getElementById("errproNo").innerHTML = MessageMap.E000001;
			chk = false;
		}else if(!checkSchool(name))
		{
			document.getElementById("errproNo").innerHTML = MessageMap.E000006;
			chk = false;
		}else if(!checkLength_name_min(name)){
			document.getElementById("errproNo").innerHTML = MessageMap.E000007;
			chk = false;
		}else if(!checkLength_name_max(name)){
			document.getElementById("errproNo").innerHTML = MessageMap.E000008;
			chk = false;
		} else if (isEmpty(mobile)) {
			document.getElementById("errproNo").innerHTML = MessageMap.E000002;
			chk = false;
		} else if (!checkMobileNo(mobile)) {
			document.getElementById("errproNo").innerHTML = MessageMap.E000003;
			chk = false;
		}else if(!checkMobileUnique(mobile))
		{
			document.getElementById("errproNo").innerHTML = MessageMap.E000013;
			chk = false;
		}else if(isEmpty(address))
		{
			document.getElementById("errproNo").innerHTML = MessageMap.E000016;
			chk = false;
		}else if(!checkSchool(address))
		{
			document.getElementById("errproNo").innerHTML = MessageMap.E000017;
			chk = false;
		}else if(!checkLength_address_min(address))
		{
			document.getElementById("errproNo").innerHTML = MessageMap.E000019;
			chk = false;
		}else if(!checkLength_address_max(address))
		{
			document.getElementById("errproNo").innerHTML = MessageMap.E000018;
			chk = false;
		}
		else if (isEmpty(school)) {
			document.getElementById("errproNo").innerHTML = MessageMap.E000010;
			chk = false;
		}else if (!checkSchool(school)) {
			document.getElementById("errproNo").innerHTML = MessageMap.E000014;
			chk = false;
		}else if(!checkLength_school_min(school)){
			document.getElementById("errproNo").innerHTML = MessageMap.E000015;
			chk = false;
		}else if(!checkLength_school_max(school)){
			document.getElementById("errproNo").innerHTML = MessageMap.E000020;
			chk = false;
		}else if (isEmpty(teacherNo)) {
			document.getElementById("errproNo").innerHTML = MessageMap.E000011;
			chk = false;
		}else if (!checkNu(teacherNo)) {
			document.getElementById("errproNo").innerHTML = MessageMap.E000004;
			chk = false;
		}else if(!checkTeacherUnique(teacherNo))
		{
			document.getElementById("errproNo").innerHTML = MessageMap.E000012;
			chk = false;
		}
// 		else if (isEmpty(address)) {
// 			document.getElementById("errproNo").innerHTML = MessageMap.E000012;
// 			chk = false;
// 		}else if (checkLength_150(address)) {
// 			document.getElementById("errproNo").innerHTML = MessageMap.E000009;
// 			chk = false;
// 		}
		
		if (!chk) {	
			return;
		}
		
	  $("#applay_submit").attr("disabled","disabled");	
		//提交
      $('#myform').form('submit', {  
	       url:'wechat!saveApply.action',
	       success:function(data){
	       $("#applay_submit").removeAttr("disabled");
	       if($.trim(data)=="NY")
                {
                	//未关注者报名成功
                	var applyPage_next = document.getElementById("applyPage_next");
					applyPage_next.style.display = "block";
                }else if($.trim(data)=="Y"){
                	//关注者报名成功
                	var applyPage_next = document.getElementById("applyPage_next3");
					applyPage_next.style.display = "block";
                	
                }else if($.trim(data)=="R"){
                
                	//此微信已参加报名
                	document.getElementById("errproNo").innerHTML = MessageMap.E000005;
                	
                }else if($.trim(data)=="Q"){
                	//已领完
                	var applyPage_next = document.getElementById("applyPage_next1");
					applyPage_next.style.display = "block";
                	
                }else if($.trim(data)=="NL")
                {
                	//报名成功，领卡失败
                	var applyPage_next = document.getElementById("applyPage_next");
					applyPage_next.style.display = "block";
					
                }else if($.trim(data)=="NB")
                {
                	alert("报名失败！");
                }
		}
	  });
	}
	
	function closeWin()
	{
		wx.closeWindow();
	}

	</script>
  </head>
<body>
	<div class="main_applyPage">
		<h2>读伴儿体验卡免费领取报名</h2>		
		 <form id="myform" name="myform" action="" method="post">
		  <input type="hidden" id="wxOpenId" name="apply.wxOpenId" value="${sessionScope.openId}">
		<div class="apply_inputList">
			<label><input name="apply.name" id="name" type="text"/><span>姓名</span></label>
			<label><input name="apply.mobile" id="mobile" type="text"/><span>手机号码</span></label>
			<label><input name="apply.address" id="address" type="text"/><span>所在省及城市</span></label>
			<label><input name="apply.school" id="school" type="text" /><span>学校</span></label>
			<label><input name="apply.teacherNo" id="teacherNo" type="text" /><span>教师证编号</span></label>
		</div>
		</form>
		<span class="prompt_massge" id="errproNo"></span>		
		<button id="applay_submit" class="submit" type="button" onclick="sub();">提交报名</button>
		<div align="center">
			<h4>如有任何问题，欢迎拨打400-700-9961</h4>		
		</div>
	</div>
	
	<div id="applyPage_next" class="main_applyPage_next next_new">
		<span class="right_img"> </span>
		<h2>恭喜报名成功！</h2>
		<h4>长按指纹，关注读伴儿，马上领取！</h4>
		<img class="erweima_png" src="images/weixin/erweima.png"/>
		<h4>如有任何问题，欢迎拨打400-700-9961</h4>		
	</div>
	<div id="applyPage_next1" class="main_applyPage_next next_new">		
		<h2>很遗憾!</h2>
		<h3> 读伴儿卡已经全被领走啦！</h3>
		<h4 class="letter-3">长按指纹关注读伴儿，获取更多活动信息</h4>
		<img class="erweima_png" src="images/weixin/erweima.png"/>
		<h4>如有任何问题，欢迎拨打400-700-9961</h4>		
	</div>
	<div id="applyPage_next2" class="main_applyPage_next next_new">				
		<h3 class="next2"> 读伴儿免费领取活动结束啦!</h3>
		<h4 >欢迎关注我们，获取更多活动信息</h4>
		<img class="erweima_png" src="images/weixin/erweima.png"/>
		<h4>如有任何问题，欢迎拨打400-700-9961</h4>		
	</div>
	<div id="applyPage_next3" class="main_applyPage_next next_new">				
		<h3 class="next3"> 恭喜您已成功领取读伴儿卡!</h3>
		<h3 class="next4"> 已将如下信息发送至您的微信，快去查收吧！ </h3>
		<div class="card_info">
			<h5>开卡成功通知</h5>			
			<p class="date">8月28日</p>
			<p class="text_blue">恭喜已成功领取读伴卡，点击下方"详情"了解 如何使用！</p>
			<p class="text_blue"><strong> 卡号：</strong>*********** </p>
			<p class="text_blue"><strong> 初始密码：</strong>*********** </p>
			<p class="text_blue"><strong> 级别：</strong>普通卡</p>
			<p class="text_blue"><strong> 有效期：</strong>自激活之日起，1年内有效</p>
			<p class="text_blue">请于*年*月*日前激活本卡，过期将无法激活，本活动最终解释权归北京迈瑞科教育科技有限公司所有！</p>
			<a class="more">详情</a>
		</div>
		<h4 class="card_tel">如有任何问题，欢迎拨打400-700-9961</h4>
		<button class="ck_new" type="button" onclick="closeWin();">马上去查看</button>		
	</div>	
	<div id="applyPage_next4" class="main_applyPage_next next_new">				
		<h3 class="next2"> 欢迎回来寻回读伴儿卡!</h3>
		<h4 >长按指纹，关注读伴儿，马上领取！</h4>
		<img class="erweima_png" src="images/weixin/erweima.png"/>
		<h4>如有任何问题，欢迎拨打400-700-9961</h4>		
	</div>
	<div id="applyPage_next5" class="main_applyPage_next next_new">				
		<h3 class="next2"> 欢迎回来寻回读伴儿卡!</h3>
		<h4 >长按指纹，关注读伴儿，马上领取！</h4>
		<img class="erweima_png" src="images/weixin/erweima.png"/>
		<h4>如有任何问题，欢迎拨打400-700-9961</h4>		
	</div>
<!-- 	<div id="applyPage_next" class="main_applyPage_next"> -->
<!-- 		<span class="right_img" ></span> -->
<!-- 		<h2>报名成功</h2> -->
<!-- 		<h3>关注“读伴儿图书馆”以便获得活动后续通知</h3> -->
<!-- 		<img class="erweima" src="images/weixin/erweima.jpg"/> -->
<!-- 		<h4>关注“读伴儿图书馆”方法：  </h4> -->
<!-- 		<p>一、长按二维码，识别图中二维码，即可关注我们 </p> -->
<!-- 		<p>二、搜索公众号”读伴儿图书馆“，即可关注我们</p> -->
<!-- 		<p>如果已关注，请点击"获取读伴儿卡"，如果未关注需要扫描上方的二维码获取</p> -->
<!-- 		<button id="card_applay" class="button_card" type="button" onclick="sendCard();">点击获取读伴儿卡</button> -->
<!-- 	</div> -->
</body>
</html>
