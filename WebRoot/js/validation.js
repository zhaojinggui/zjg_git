
var count=2;
function doTime(){
   if(count<1){
      document.all.msg.style.visibility="hidden";
      return;
   }
   count--;
   setTimeout("doTime()", 1000);
}
/*
用途：检查输入字符串是否为空或者全部都是空格
输入：str
返回：
	如果全是空返回true,否则返回false
*/
function isNull( str ){
	if ( str == "" ) return true;
	var regu = "^[ ]+$";
	var re = new RegExp(regu);
	return re.test(str);
}

/*
用途：检查输入对象的值是否符合正整数格式
输入：str 输入的字符串
返回：如果通过验证返回true,否则返回false	
*/
function isNumber( str ){  
	var regu = /^(\d+)$/;
        return regu.test(str);
}

/*
用途：检查输入对象的值是否符合整数格式
输入：str 输入的字符串
返回：如果通过验证返回true,否则返回false	
*/
function isInteger( str ){  
	var regu = /^[-]{0,1}[0-9]{1,}$/;
        return regu.test(str);
}

/*
用途：检查输入字符串是否是数字,可以是负数和小数
输入： str：字符串
返回： 如果通过验证返回true,否则返回false	
*/
function isDecimal( str ){   
         if(isInteger(str)) return true;
	var re = /^[-]{0,1}(\d+)[\.]+(\d+)$/;
	if (re.test(str)) {
	   if(RegExp.$1==0&&RegExp.$2==0) return false;
	   return true;
	} else {
	   return false;
	}
}

/*
用途：检查输入对象的值是否符合端口号格式
输入：port 输入的字符串
返回：如果通过验证返回true,否则返回false	
*/
function isValidPort(port) {
	if (port == null || port == "") 
		return false;
	if (isNaN(parseInt(port, 10)))
		return false;
	if (parseInt(port, 10) < 0)
		return false;		
	if (parseInt(port, 10) > 65535)
		return false;		
	// Leading zero is not valid on a non zero number		
	if (parseInt(port, 10) > 0 && port.substring(0,1) == "0")
		return false;

	return true;
}

/*
用途：检查输入的日期是否符合 yyyyMMdd 日期格式 (1900年~2100年)
输入：str：数字字符串
返回：如果通过验证返回true,否则返回false	
*/

function isDate( str ) {
	if(str.length!=8 || !isNumber(str)) return false;  
	var year = str.substring(0,4);
	if(year>"2100" || year< "1900")	return false;
	
	var month = str.substring(4,6);
	if(month>"12" || month< "01") return false;
	
	var day = str.substring(6,8);
	if(day>getMaxDay(year,month) || day< "01") return false;
	
	return true;  
}

/*
用途：检查输入的时间是否符合 hhMMss 时间格式 
输入：str：数字字符串
返回：如果通过验证返回true,否则返回false	
*/

function isTime( str ) {
	if(str.length!=8) return false;  
	var timeList = str.split(":");
	if(timeList.length < 1 || timeList.length > 3  ) return false;
	var hour = timeList[0];
	if(isNumber(hour)>23 || isNumber(hour)< 0)	return false;
	
	var minute = timeList[1];
	if(isNumber(minute)>60 || isNumber(minute)< 0) return false;
	
	var second = timeList[2];
	if(isNumber(second)>60 || isNumber(second)< 0) return false;
	
	if(!isNumber(hour) ||!isNumber(minute) || !isNumber(second))return false;
	
	
	return true;  
}

/*
用途：取得指定月份的天数
输入：year：年，数字；month：月，数字
返回：该月的天数；	
*/
function getMaxDay(year,month) {
	if(month==4||month==6||month==9||month==11)
		return "30";
	if(month==2)
		if(year%4==0&&year%100!=0 || year%400==0)
			return "29";
		else
			return "28";
	return "31";
}

/**
 * 验证IP地址
 */
function checkip(IP)
{
  var ip=IP;
  var pattern=/^(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9])\.(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9]|0)\.(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9]|0)\.(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[0-9])$/;
   var flag_ip=pattern.test(IP);
   if(!flag_ip){
   alert("非法IP");
   //document.all.ip.focus();
   return false;
   }
}
/**
 * 验证日期，日期格式为：
 */
function checkDate(Date){
	var date=Date;
	var pattern=/^((((1[6-9]|[2-9]\d)\d{2})(\/|\-)(0?[13578]|1[02])(\/|\-)(0?[1-9]|[12]\d|3[01]))|(((1[6-9]|[2-9]\d)\d{2})(\/|\-)(0?[13456789]|1[012])(\/|\-)(0?[1-9]|[12]\d|30))|(((1[6-9]|[2-9]\d)\d{2})(\/|\-)0?2(\/|\-)(0?[1-9]|1\d|2[0-8]))|(((1[6-9]|[2-9]\d)(0[48]|[2468][048]|[13579][26])|((16|[2468][048]|[3579][26])00))-0?2-29-))$/;
	var flag_date=pattern.test(date);
	if(!flag_date){
		alert("非法日期");
		//document.all.date.focus();
		return false;
	}
}
/**
 * 检查form元素不能为空；
 * 
 */
function checkForm(form){ 
	for(i=0;i<form.length;i++){
		if(form.elements[i].value==""){
			alert(form.elements[i].v_must+"不能为空！");
			form.elements[i].focus();
			return false;
		}
		
	}
}
/**
 * 验证输入的字符串是否是汉字；
 * 实现验证输入的字符串是否为汉字的正则表达式如下：/[^\u4E00-\u9FA5]/
 */
function checkHZ(str){
	var s=str;
	//在JavaScript中，正则表达式只能使用"/"开头和结束，不能使用双引号
	var Expression=/[^\u4E00-\u9FA5]/;
	var objExp=new RegExp(Expression);
	if(objExp.test(s)==true){
		return true;
	}else{
		return false;
	}
}

/**
 * 验证身份证号码；
 */
function checkNO(NO){
	var str=NO;
	var Expression=/^\d{17}[\d|X]|^\d{15}$/;
	var objExp=new RegExp(Expression);
	if(objExp.test(str)==true){
		return true;
	}else{
		return false;
	}
}	
/**
 * 验证网站地址是否合法；
 */
function checkUrl(url){
	var str=url;
	var Expression=/http(s)?:\/\/([\w-]+\.)+[\w-]+(\/[\w- .\/?%&=]*)?/;
	var objExp=new RegExp(Expression);
	if(objExp.test(str)==true){
		return true;
	}else{
		return false;
	}
}

/**
用途：改变下拉框显示值
输入：obiName：下拉框名；selValue：显示值对应的Value
返回：Void	
*/
function checkBoxSelect( obiName,selValue ){
  var obj = document.all(obiName);
  var cnt = obj.options.length;
  for (var i= 0;i<obj.length;i++)
     {
       if (obj.options[i].value==selValue)
       {
           obj.options[i].selected =true;
       } 
     }
}

/*
用途：字符1是否以字符串2结束
输入：str1：字符串；str2：被包含的字符串
返回：如果通过验证返回true,否则返回false	
*/
function isLastMatch(str1,str2)
{  
   var index = str1.lastIndexOf(str2);
   if(str1.length==index+str2.length) return true;
   return false;
}


/*
用途：字符1是否以字符串2开始
输入：str1：字符串；str2：被包含的字符串
返回：如果通过验证返回true,否则返回false	
*/
function isFirstMatch(str1,str2)
{  
   var index = str1.indexOf(str2);
   if(index==0) return true;
   return false;
}

/*
用途：字符1是包含字符串2
输入：str1：字符串；str2：被包含的字符串
返回：如果通过验证返回true,否则返回false	
*/
function isMatch(str1,str2)
{  
   var index = str1.indexOf(str2);
   if(index==-1) return false;
   return true;
}
/*
用途：检查输入字符串是否只由英文字母和数字组成
输入：str：字符串
返回：如果通过验证返回true,否则返回false	
*/
function isNumberOrLetter( str ){
	var regu = "^[0-9a-zA-Z]+$";
	var re = new RegExp(regu);
	if (re.test(str)) {
	  return true;
	}else{
	  return false;
	}
}
/*
用途：检查输入手机号码是否正确
输入：str：字符串
返回：通过验证返回true，否则返回false
*/
function checkMobile(str){
 var regu="^[1][0-9][0-9]{9}$";
 var re=new RegExp(regu);
 if(re.test(str)){
  return true;
 }else{
  return false;
 }
} 
/*
用途：检查输入国内电话号码是否正确
输入：str：字符串
返回：通过验证返回true，否则返回false
*/
function checkTelephone(str){
 var regu="^(\d{3}-|\d{4}-)?(\d{8}|\d{7})?$";
 var re=new RegExp(regu);
 if(re.test(str)){
   return true;
 }else{
   return false;
 }
}
/*
用途:检查Email是否正确
输入:str
返回：通过验证返回true,否则返回false
*/
function checkEmail(str){
 var regu="^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$";
 var re=new RegExp(regu);
 if(re.test(str)){
   return true;
 }else{
   return false;
 }
}
/*
用途：检查腾讯qq号是否正确
输入：str：字符串
返回：通过验证返回true，否则返回false
*/
function checkQQ(str){
 var regu="^[1-9]*[1-9][0-9]*$";
 var re=new RegExp(regu);
 if(re.test(str)){
  return true;
 }else{
  return false;
 }
}
/*
用途：检查MSN是否正确
输入：str：字符串
返回：通过验证返回true，否则返回false
*/
function checkMSN(str){
 var regu="^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$";
 var re=new RegExp(regu);
 if(re.test(str)){
  return true;
 }else{
  return false;
 }
}
/*
用途：去掉字符串前后的空格
输入：str：字符串
返回：去掉空格后的字符串
*/
 function   trim(str){
  return  str.replace(/(^\s*)|(\s*$)/g,"");         
 }
 /*
用途：检查邮政编码是否正确
输入：str：字符串
返回：通过验证返回true，否则返回false
*/
function checkCode(str){
 var regu="^[0-9]{6}$";
 var re=new RegExp(regu);
 if(re.test(str)){
   return true;
 }else{
  return false;
 }
}
/*
用途：检查传真是否正确
输入：str：字符串
返回：通过验证返回true，否则返回false
*/
function checkFax(str){
 var regu="^[0-9]{6}$";
 var re=new RegExp(regu);
 if(re.test(str)){
   return true;
 }else{
  return false;
 }
}
