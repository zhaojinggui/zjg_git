
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
��;����������ַ����Ƿ�Ϊ�ջ���ȫ�����ǿո�
���룺str
���أ�
	���ȫ�ǿշ���true,���򷵻�false
*/
function isNull( str ){
	if ( str == "" ) return true;
	var regu = "^[ ]+$";
	var re = new RegExp(regu);
	return re.test(str);
}

/*
��;�������������ֵ�Ƿ������������ʽ
���룺str ������ַ���
���أ����ͨ����֤����true,���򷵻�false	
*/
function isNumber( str ){  
	var regu = /^(\d+)$/;
        return regu.test(str);
}

/*
��;�������������ֵ�Ƿ����������ʽ
���룺str ������ַ���
���أ����ͨ����֤����true,���򷵻�false	
*/
function isInteger( str ){  
	var regu = /^[-]{0,1}[0-9]{1,}$/;
        return regu.test(str);
}

/*
��;����������ַ����Ƿ�������,�����Ǹ�����С��
���룺 str���ַ���
���أ� ���ͨ����֤����true,���򷵻�false	
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
��;�������������ֵ�Ƿ���϶˿ںŸ�ʽ
���룺port ������ַ���
���أ����ͨ����֤����true,���򷵻�false	
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
��;���������������Ƿ���� yyyyMMdd ���ڸ�ʽ (1900��~2100��)
���룺str�������ַ���
���أ����ͨ����֤����true,���򷵻�false	
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
��;����������ʱ���Ƿ���� hhMMss ʱ���ʽ 
���룺str�������ַ���
���أ����ͨ����֤����true,���򷵻�false	
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
��;��ȡ��ָ���·ݵ�����
���룺year���꣬���֣�month���£�����
���أ����µ�������	
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
 * ��֤IP��ַ
 */
function checkip(IP)
{
  var ip=IP;
  var pattern=/^(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9])\.(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9]|0)\.(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9]|0)\.(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[0-9])$/;
   var flag_ip=pattern.test(IP);
   if(!flag_ip){
   alert("�Ƿ�IP");
   //document.all.ip.focus();
   return false;
   }
}
/**
 * ��֤���ڣ����ڸ�ʽΪ��
 */
function checkDate(Date){
	var date=Date;
	var pattern=/^((((1[6-9]|[2-9]\d)\d{2})(\/|\-)(0?[13578]|1[02])(\/|\-)(0?[1-9]|[12]\d|3[01]))|(((1[6-9]|[2-9]\d)\d{2})(\/|\-)(0?[13456789]|1[012])(\/|\-)(0?[1-9]|[12]\d|30))|(((1[6-9]|[2-9]\d)\d{2})(\/|\-)0?2(\/|\-)(0?[1-9]|1\d|2[0-8]))|(((1[6-9]|[2-9]\d)(0[48]|[2468][048]|[13579][26])|((16|[2468][048]|[3579][26])00))-0?2-29-))$/;
	var flag_date=pattern.test(date);
	if(!flag_date){
		alert("�Ƿ�����");
		//document.all.date.focus();
		return false;
	}
}
/**
 * ���formԪ�ز���Ϊ�գ�
 * 
 */
function checkForm(form){ 
	for(i=0;i<form.length;i++){
		if(form.elements[i].value==""){
			alert(form.elements[i].v_must+"����Ϊ�գ�");
			form.elements[i].focus();
			return false;
		}
		
	}
}
/**
 * ��֤������ַ����Ƿ��Ǻ��֣�
 * ʵ����֤������ַ����Ƿ�Ϊ���ֵ�������ʽ���£�/[^\u4E00-\u9FA5]/
 */
function checkHZ(str){
	var s=str;
	//��JavaScript�У�������ʽֻ��ʹ��"/"��ͷ�ͽ���������ʹ��˫����
	var Expression=/[^\u4E00-\u9FA5]/;
	var objExp=new RegExp(Expression);
	if(objExp.test(s)==true){
		return true;
	}else{
		return false;
	}
}

/**
 * ��֤���֤���룻
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
 * ��֤��վ��ַ�Ƿ�Ϸ���
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
��;���ı���������ʾֵ
���룺obiName������������selValue����ʾֵ��Ӧ��Value
���أ�Void	
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
��;���ַ�1�Ƿ����ַ���2����
���룺str1���ַ�����str2�����������ַ���
���أ����ͨ����֤����true,���򷵻�false	
*/
function isLastMatch(str1,str2)
{  
   var index = str1.lastIndexOf(str2);
   if(str1.length==index+str2.length) return true;
   return false;
}


/*
��;���ַ�1�Ƿ����ַ���2��ʼ
���룺str1���ַ�����str2�����������ַ���
���أ����ͨ����֤����true,���򷵻�false	
*/
function isFirstMatch(str1,str2)
{  
   var index = str1.indexOf(str2);
   if(index==0) return true;
   return false;
}

/*
��;���ַ�1�ǰ����ַ���2
���룺str1���ַ�����str2�����������ַ���
���أ����ͨ����֤����true,���򷵻�false	
*/
function isMatch(str1,str2)
{  
   var index = str1.indexOf(str2);
   if(index==-1) return false;
   return true;
}
/*
��;����������ַ����Ƿ�ֻ��Ӣ����ĸ���������
���룺str���ַ���
���أ����ͨ����֤����true,���򷵻�false	
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
��;����������ֻ������Ƿ���ȷ
���룺str���ַ���
���أ�ͨ����֤����true�����򷵻�false
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
��;�����������ڵ绰�����Ƿ���ȷ
���룺str���ַ���
���أ�ͨ����֤����true�����򷵻�false
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
��;:���Email�Ƿ���ȷ
����:str
���أ�ͨ����֤����true,���򷵻�false
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
��;�������Ѷqq���Ƿ���ȷ
���룺str���ַ���
���أ�ͨ����֤����true�����򷵻�false
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
��;�����MSN�Ƿ���ȷ
���룺str���ַ���
���أ�ͨ����֤����true�����򷵻�false
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
��;��ȥ���ַ���ǰ��Ŀո�
���룺str���ַ���
���أ�ȥ���ո����ַ���
*/
 function   trim(str){
  return  str.replace(/(^\s*)|(\s*$)/g,"");         
 }
 /*
��;��������������Ƿ���ȷ
���룺str���ַ���
���أ�ͨ����֤����true�����򷵻�false
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
��;����鴫���Ƿ���ȷ
���룺str���ַ���
���أ�ͨ����֤����true�����򷵻�false
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
