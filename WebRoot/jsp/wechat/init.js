var MessageMap = {
		E000001 : "姓名不能为空。",
		E000002 : "手机号码不能为空。",
		E000003 : "手机号输入有误,请重新输入。",
		E000004 : "教师资格证编码需为17位数字。",
		E000005 : "此微信已参加过活动,感谢支持。",
		E000006 : "姓名请输入汉字。",
		E000007 : "请输入全名。",
		E000008 : "姓名长度需控制在2-6个字之间。",
		E000009 : "录入长度不能大于150个字符",
		E000010 : "学校不能为空。",
		E000011 : "教师证编号不能为空。",
		E000012 : "此教师证编码已参加过活动，感谢支持。",
		E000013 : "此手机号已参加过活动，感谢支持。",
		E000014 : "学校输入有误，请输入汉字。",
		E000015 : "请输入学校全称。",
		E000016 : "请输入省份及城市。",
		E000017 : "所在省及城市输入有误，请输入汉字。",
		E000018 : "所在省及城市需控制在2-15个汉字之间。",
		E000019 : "请输入省份及城市全称。",
		E000020 : "学校名称需控制在4-15个汉字之间。"
	};
// 关闭页面
function doClose() {
    window.opener=null;
    window.open('','_self');
    window.close();
}
function isEmpty(value) {
	if (value == null || value.length == 0) {
		return true;
	} else {
		return false;
	}
}

//中文和英文，不能混输
function checkName(username)
{
	var reg=/^[\u4e00-\u9fa5]+$/;
	var reg2=/^[a-zA-Z]+$/;
	if(!reg.test(username)&&!reg2.test(username)){
		return false;
	}
	return true;
}

//只能输入中文
function checkSchool(name)
{
	var reg=/^[\u4e00-\u9fa5]+$/;
	if(!reg.test(name)){
		return false;
	}
	return true;
}
//17位不能相同的数字
function checkNu(num)
{
	var reg=/^\d{17}$/;
	if(!reg.test(num)){
		return false;
	}
	//每个数不相同
	var n=num.split("");
	var f=0;
	for(var i=0;i<n.length-1;i++){
		if(n[i]==n[i+1]){
			f++;
		}
	}
	if(f==n.length-1){
		return false;
	}
	return true;
	
}


function checkEn(username)
{
	var Enreg=/^[\u0391-\uFFE5A-Za-z]+$/;//中文和英文
	if (!Enreg.test(username)) {
	        return false;
	    }
	return true;
}

function checkNameLength(username)
{
	var length=username.replace(/[^\x00-\xff]/g, "**").length;
	if (length<2 || length>26) {
        return false;
    }
    return true;
}

function checkNum(name){
	var numberreg=/^[0-9]*$/;
	if (!numberreg.test(name)) {
        return false;
    }
    return true;
}

function checkLength(proNo)
{
	if (proNo.length > 30) {
		return true;
	} else {
		return false;
	}
}

function checkLength_50(proNo)
{
	if (proNo.length > 50) {
		return true;
	} else {
		return false;
	}
}

function checkLength_17(proNo)
{
	var numberreg=/^[0-9]*$/;
	if (proNo.length != 17 || !numberreg.test(proNo)) {
		return true;
	} else {
		return false;
	}
}

function checkLength_150(proNo)
{
	if (proNo.length > 150) {
		return true;
	} else {
		return false;
	}
}

function checkLength_name_min(name)
{
	if (name.length > 1) {
		return true;
	} else {
		return false;
	}
}
function checkLength_name_max(name)
{
	if (name.length < 7) {
		return true;
	} else {
		return false;
	}
}

function checkLength_school_min(school)
{
	if (school.length > 3) {
		return true;
	} else {
		return false;
	}
}
function checkLength_school_max(school)
{
	if (school.length < 16) {
		return true;
	} else {
		return false;
	}
}

function checkLength_address_min(school)
{
	if (school.length > 1) {
		return true;
	} else {
		return false;
	}
}
function checkLength_address_max(school)
{
	if (school.length <16) {
		return true;
	} else {
		return false;
	}
}
function checkIdNum(idNo)
{
	var IDreg=/^\d{15}|\d{18}$/;
	if (!IDreg.test(idNo)) {
	        return false;
	    }
	return true;
}
function checkEmail(strEmail) {
	var emailreg = /\b[a-zA-Z0-9._-]+@[a-zA-Z0-9._-]+\.[a-zA-Z]{2,4}\b/;
    if (!emailreg.test(strEmail)) {
        return false;
    }
    return true;
}
function checkMobileNo(mobileno) {
	var emailreg = /^1[0-9]{10}$/;
	//var emailreg = /^((13[0-9])|(15[^4,\D])|(18[0,5-9]))\d{8}$/;
    if (!emailreg.test(mobileno)) {
        return false;
    }
	// 不包含123456789
	if (mobileno.indexOf("123456789") >= 0) {
		return false;
	}
	// 不能都是一个数字	    	
	if(true){
	    if (mobileno.match(/^([0-9])\1*$/) != null)
	    {
	        return false;
	    }
	} 
    return true;
}
function checkVerifiCode(verifiCode) {
	var verifiCodereg = /^\d{6}$/;
    if (!verifiCodereg.test(verifiCode)) {
        return false;
    }
    return true;
}

/////////////////////////////////身份证号验证///////////////////////////////////////////

//以下是根据身份证号码编码规则，使用JS对其进行有效性验证代码
/**  
 * 身份证15位编码规则：dddddd yymmdd xx p   
 * dddddd：地区码   
 * yymmdd: 出生年月日   
 * xx: 顺序类编码，无法确定   
 * p: 性别，奇数为男，偶数为女  
 * <p />  
 * 身份证18位编码规则：dddddd yyyymmdd xxx y   
 * dddddd：地区码   
 * yyyymmdd: 出生年月日   
 * xxx:顺序类编码，无法确定，奇数为男，偶数为女   
 * y: 校验码，该位数值可通过前17位计算获得  
 * <p />  
 * 18位号码加权因子为(从右到左) Wi = [ 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2,1 ]  
 * 验证位 Y = [ 1, 0, 10, 9, 8, 7, 6, 5, 4, 3, 2 ]   
 * 校验位计算公式：Y_P = mod( ∑(Ai×Wi),11 )   
 * i为身份证号码从右往左数的 2...18 位; Y_P为脚丫校验码所在校验码数组位置  
 *   
 */  
  
var Wi = [ 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2, 1 ];// 加权因子   
var ValideCode = [ 1, 0, 10, 9, 8, 7, 6, 5, 4, 3, 2 ];// 身份证验证位值.10代表X   
function IdCardValidate(idCard) {
    idCard = trim(idCard.replace(/ /g, ""));   
    if (idCard.length == 15) {   
        return isValidityBrithBy15IdCard(idCard);   
    } else if (idCard.length == 18) {   
        var a_idCard = idCard.split("");// 得到身份证数组   
        if(isValidityBrithBy18IdCard(idCard)&&isTrueValidateCodeBy18IdCard(a_idCard)){   
            return true;   
        }else {   
            return false;   
        }   
    } else {   
        return false;   
    }   
}   
/**  
 * 判断身份证号码为18位时最后的验证位是否正确  
 * @param a_idCard 身份证号码数组  
 * @return  
 */  
function isTrueValidateCodeBy18IdCard(a_idCard) {   
    var sum = 0; // 声明加权求和变量   
    if (a_idCard[17].toLowerCase() == 'x') {   
        a_idCard[17] = 10;// 将最后位为x的验证码替换为10方便后续操作   
    }   
    for ( var i = 0; i < 17; i++) {   
        sum += Wi[i] * a_idCard[i];// 加权求和   
    }   
    valCodePosition = sum % 11;// 得到验证码所位置   
    if (a_idCard[17] == ValideCode[valCodePosition]) {   
        return true;   
    } else {   
        return false;   
    }   
}   
/**  
 * 通过身份证判断是男是女  
 * @param idCard 15/18位身份证号码   
 * @return 'female'-女、'male'-男  
 */  
function maleOrFemalByIdCard(idCard){   
    idCard = trim(idCard.replace(/ /g, ""));// 对身份证号码做处理。包括字符间有空格。   
    if(idCard.length==15){   
        if(idCard.substring(14,15)%2==0){   
            return 'female';   
        }else{   
            return 'male';   
        }   
    }else if(idCard.length ==18){   
        if(idCard.substring(14,17)%2==0){   
            return 'female';   
        }else{   
            return 'male';   
        }   
    }else{   
        return null;   
    }      
}   
 /**  
  * 验证18位数身份证号码中的生日是否是有效生日  
  * @param idCard 18位书身份证字符串  
  * @return  
  */  
function isValidityBrithBy18IdCard(idCard18){   
    var year =  idCard18.substring(6,10);   
    var month = idCard18.substring(10,12);   
    var day = idCard18.substring(12,14);   
    var temp_date = new Date(year,parseFloat(month)-1,parseFloat(day));   
    // 这里用getFullYear()获取年份，避免千年虫问题   
    if(temp_date.getFullYear()!=parseFloat(year)   
          ||temp_date.getMonth()!=parseFloat(month)-1   
          ||temp_date.getDate()!=parseFloat(day)){   
            return false;   
    }else{   
        return true;   
    }   
}   
  /**  
   * 验证15位数身份证号码中的生日是否是有效生日  
   * @param idCard15 15位书身份证字符串  
   * @return  
   */  
  function isValidityBrithBy15IdCard(idCard15){   
      var year =  idCard15.substring(6,8);   
      var month = idCard15.substring(8,10);   
      var day = idCard15.substring(10,12);   
      var temp_date = new Date(year,parseFloat(month)-1,parseFloat(day));   
      // 对于老身份证中的你年龄则不需考虑千年虫问题而使用getYear()方法   
      if(temp_date.getYear()!=parseFloat(year)   
              ||temp_date.getMonth()!=parseFloat(month)-1   
              ||temp_date.getDate()!=parseFloat(day)){   
                return false;   
        }else{   
            return true;   
        }   
  }   
//去掉字符串头尾空格   
function trim(str) {   
    return str.replace(/(^\s*)|(\s*$)/g, "");   
}